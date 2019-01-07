package com.mocking.vm.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.mocking.vm.VMFactory;
import com.mocking.vm.component.CodeResult;
import com.mocking.vm.component.ConfigProperties;
import com.mocking.vm.component.SourceCode;

/*
 * xiaofeng li
 * xlics05@gmail.com
 * */
@Service
public class RunCodeService {
	private Path userFolderPath;
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

	public void init() {
		ConfigProperties sc = context.getBean(ConfigProperties.class);
		javaJVM = sc.getJavaJVM();
		pythonJVM = sc.getPythonJVM();
		kotlinJVM = sc.getKotlinJVM();
		nodeJsJVM = sc.getNodeJsJVM();
		shellPath = sc.getShellPath();
		System.out.println("debugging:" + nodeJsJVM);
		System.out.println("debugging:" + javaJVM);
		System.out.println("debugging:" + pythonJVM);
	}

	public void config(SourceCode sourceCode) {
		init();
		this.sourceCode = sourceCode;
		builder = new ProcessBuilder();

		/*
		 * TODO, the following directory should be assigned to a user uniquely upon
		 * their account creation We should only grab the userFoledPath string from the
		 * UserService and then set it here.
		 */

		userFolderPath = Paths
				.get(System.getProperty("user.dir") + "/src/main/resources/user/" + runcodeUtil.getUserId());
		sourceFilePath = userFolderPath.toString() + "/" + sourceCode.getFileName() + "." + sourceCode.getFileExt();
		// Create working directory
		createWorkingDirectory(userFolderPath);
		// Set working directory
		builder.directory(userFolderPath.toFile());
	}

	public String getUserFolderPath() {
		return this.userFolderPath.toString();
	}

	private void createWorkingDirectory(Path path) {
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}
	}

	public CodeResult executeCode() {
		CodeResult result = new CodeResult();
		try {
			System.out.println("User Directory: " + System.getProperty("user.dir"));

			// Generate java file in the file system
			runcodeUtil.generateSourceFile(sourceCode,sourceFilePath);

			// TODO check file exists? make our own exception
			File sourceFile = new File(sourceFilePath);
			if (!sourceFile.exists() || sourceFile.isDirectory()) {
				System.out.println("source file creation failed");
			}

			
			if (sourceCode.getFileExt().equals("java")) {
				String compileCommand = vmFactory.getVM("java").getCompileCommand(sourceCode);
				String runCommand = vmFactory.getVM("java").getRunCommand(sourceCode);
				builder.command(shellPath, "-c", compileCommand + ";" + runCommand);
			} else if (sourceCode.getFileExt().equalsIgnoreCase("python")) {
				builder.command(shellPath, "-c", "python " + sourceFilePath.toString());
			} else if (sourceCode.getFileExt().equalsIgnoreCase("javascript")) {
				
			} else if (sourceCode.getFileExt().equalsIgnoreCase("kotlin")) {
				
			}
			
			result = generateOutput(false);
		} catch (IOException e) {
			System.out.println("IOException happened - here's what I know: ");
			log.error(e.getMessage());
	
		} catch (Exception e) {
			System.out.println("Exception happened - here's what I know: ");
			log.error(e.getMessage());
		}
		return result;
	}

	// Generate stdout sterr and persist them into object, and optionally it can be
	// saved on file system
	private CodeResult generateOutput(boolean saveOutput) throws IOException {
		Process p = builder.start();
		CodeResult codeResult = new CodeResult();
		System.out.println("User Folder Path:" + userFolderPath);

		// TODO, need to fix this
		PrintWriter printWriter = new PrintWriter(userFolderPath + "/" + "output.txt");
		BufferedReader stdOutput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

		// read the output from the command
		if (saveOutput) {
			printWriter.println("Ouput: Here is the standard output of the command:\n");
		}
		StringBuilder output = runcodeUtil.readFromStreams(stdOutput, saveOutput, printWriter);
		codeResult.setStdOut(output.toString());

		// read any errors from the attempted command
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