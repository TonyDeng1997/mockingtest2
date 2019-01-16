package com.mocking.vm.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.mocking.auth.service.SecurityService;
import com.mocking.auth.service.UserServiceImpl;
import com.mocking.vm.VMFactory;
import com.mocking.vm.component.CodeResult;
import com.mocking.vm.component.ConfigProperties;
import com.mocking.vm.component.SourceCode;

/*
 * @author: xiaofeng li
 * 
 * */

@Service
public class RunCodeService {
	private String userHomeDirecory;
	private String sourceFilePath;
	
	private ProcessBuilder builder; // Use for compiling and running
	
	private SourceCode sourceCode;
	private String javaJVM;
	private String pythonJVM;
	private String kotlinJVM;
	private String nodeJsJVM;
	private String shellPath;
	private static final Logger log = LoggerFactory.getLogger(RunCodeService.class);

	@Autowired
	ApplicationContext context;

	@Autowired
	RunCodeUtil runcodeUtil;

	@Autowired
	VMFactory vmFactory;
	
	@Autowired
	UserServiceImpl userService;

	@Autowired
	SecurityService securityService;
	private RunCodeService() {}
	
	public void init() {
		ConfigProperties sc = context.getBean(ConfigProperties.class);
		shellPath = sc.getShellPath();
		if (!runcodeUtil.fileExecutable(shellPath)) {
			// TODO make our own exception class
			throw new RuntimeException("Shell is not configured properly");
		}
	}
	
	public void config(SourceCode sourceCode) {
		init();
		this.sourceCode = sourceCode;
		builder = new ProcessBuilder();
		userHomeDirecory = userService.findHomePathByUsername(securityService.findLoggedInUsername());
		sourceFilePath = getSourceFilePath(userHomeDirecory, this.sourceCode);
		// Set working directory
		builder.directory(new File(userHomeDirecory));
	}

	private String getSourceFilePath(String userHomeDirectory, SourceCode sourceCode) {
		return  userHomeDirecory + "/" + 
				sourceCode.getFileName() + "." + sourceCode.getFileExt();
	}

	public CodeResult executeCode() {
		CodeResult result = new CodeResult();
		try {
		
			// Generate source file in the file system
			runcodeUtil.generateSourceFile(sourceCode, sourceFilePath);

			File sourceFile = new File(sourceFilePath);
			if (!sourceFile.exists() || sourceFile.isDirectory()) {
				System.out.println("source file creation failed");
				log.error("source file already exists!");
			}

			/*
			 * TODO Java has been done. Python requires testing and cleaning. Kotlin is not
			 * done. JS is not done.
			 */
			
			
			if (sourceCode.getFileExt().equals("java")) {
				String compileCommand = vmFactory.getVM("java").getCompileCommand(sourceCode);
				String runCommand = vmFactory.getVM("java").getRunCommand(sourceCode);
				builder.command(shellPath, "-c", compileCommand + ";" + runCommand);
			} else if (sourceCode.getFileExt().equals("py")) {
				String runCommand = vmFactory.getVM("python").getRunCommand(sourceCode);	
				System.out.println(runCommand);
				builder.command(shellPath, "-c", runCommand);
			} else if (sourceCode.getFileExt().equalsIgnoreCase("js")) {
				String runCommand = vmFactory.getVM("javascript").getRunCommand(sourceCode);	
				System.out.println(runCommand);
				builder.command(shellPath, "-c", runCommand);

			} else if (sourceCode.getFileExt().equalsIgnoreCase("kotlin")) {

			}
			result = generateOutput(false);
		} catch (IOException e) {
			System.out.println("exception here" + e.getMessage());
			log.error(e.getMessage());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e.getMessage());
		}
		return result;
	}

	// Generate stdout sterr and persist them into object, and optionally it can be
	// saved on file system
	private CodeResult generateOutput(boolean saveOutput) throws IOException {
		Process p = builder.start();
		System.out.println("hello debugging here");
		CodeResult codeResult = new CodeResult();
		System.out.println("User Folder Path:" + this.userHomeDirecory);

		// Don't hardcode file name
		PrintWriter printWriter = new PrintWriter(this.userHomeDirecory + "/" + "output.txt");
		BufferedReader stdOutput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

		// Read the output from the command
		if (saveOutput) {
			printWriter.println("Ouput: Here is the standard output of the command:\n");
		}
		StringBuilder output = runcodeUtil.readFromStreams(stdOutput, saveOutput, printWriter);
		codeResult.setStdOut(output.toString());

		// Read any errors from the attempted command
		if (saveOutput) {
			printWriter.println("Error: Here is the standard error of the command (if any):\n");
		}
		StringBuilder error = runcodeUtil.readFromStreams(stdError, saveOutput, printWriter);
		codeResult.setStdErr(error.toString());
		if (saveOutput) {
			printWriter.close();
		}
		return codeResult;
	}
}