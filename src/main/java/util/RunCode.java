package util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mocking.auth.WebApplication;

public class RunCode {
	private Path userFolderPath;
	private String sourceFilePath;
	private ProcessBuilder builder; // Use for compiling and running
	private SourceCode sourceCode;
	
	private static String javaJVM;
	private static String pythonJVM;
	private static String kotlinJVM;
	private static String nodeJsJVM;
	private static final Logger log = LoggerFactory.getLogger(RunCode.class);
	
	static {
		ApplicationContext context = new AnnotationConfigApplicationContext(ConfigProperties.class);
		ConfigProperties sc = context.getBean(ConfigProperties.class);
		javaJVM = sc.getJavaJVM();
		pythonJVM = sc.getPythonJVM();
		kotlinJVM = sc.getKotlinJVM();
		nodeJsJVM = sc.getNodeJsJVM();
	}

	public RunCode(SourceCode sourceCode) {
		this.sourceCode = sourceCode;
		builder = new ProcessBuilder();
		
		
		/*TODO, the following directory should be assigned to a user uniquely upon their account creation
		 * We should only grab the userFoledPath string from the UserService and then set it here.
		 * */
		
		userFolderPath = Paths.get(System.getProperty("user.dir") + "/src/main/resources/user/" + getUserId());
		sourceFilePath = userFolderPath.toString() + "/" + sourceCode.getTitle() + "." + sourceCode.getFileExt();
		// Create working directory
		createWorkingDirectory(userFolderPath);
		// Set working directory
		builder.directory(userFolderPath.toFile());
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

	/* TODO should also get a UserID+ UUID
	 * Unpon user creation we will have to create a unique path for the user and save 
	 * it to the database table.
	 */
	private String getUserId() {
		return UUID.randomUUID().toString();
	}

	private void generateSourceFile(String sourceFilePath) {
		try (FileWriter fileWriter = new FileWriter(sourceFilePath)) {
			fileWriter.write(sourceCode.getCode());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	// generate std1 sterr to a txt file
	private void generateOutput() throws IOException {
		Process p = builder.start();
		CodeResult codeResult = new CodeResult();
		System.out.println("User Folder Path:" + userFolderPath);
	
		// TODO, need to fix this
		PrintWriter printWriter = new PrintWriter(userFolderPath + "/" + "log.txt");
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

		// read the output from the command
		System.out.println("Here is the standard output of the command:\n");
		printWriter.println("Ouput: Here is the standard output of the command:\n");
		StringBuilder output = new StringBuilder();
		String line;
		while ((line = stdInput.readLine()) != null) {
			printWriter.println(line);
			output.append(line);
			output.append(System.getProperty("line.separator"));
		}
		codeResult.setStdout(output.toString());
		System.out.println(codeResult.getStdout());

		// read any errors from the attempted command
		System.out.println("Here is the standard error of the command (if any):\n");
		printWriter.println("Error: Here is the standard error of the command (if any):\n");
		StringBuilder error = new StringBuilder();
		while ((line = stdError.readLine()) != null) {
			printWriter.println(line);
			error.append(line);
			error.append(System.getProperty("line.separator"));
		}
		codeResult.setException(error.toString());
		System.out.println(codeResult.getException());
		printWriter.close();
	}

	public Path executeCode() {
		try {
			System.out.println("Java Home: " + System.getProperty("java.home") + "\\bin/java");
			System.out.println("User Home: " + System.getProperty("user.home"));
			System.out.println("User Directory: " + System.getProperty("user.dir"));

			// Generate java file in the file system
			generateSourceFile(sourceFilePath);

			// TODO check file exists?
			File sourceFile = new File(sourceFilePath);
			if (!sourceFile.exists() || sourceFile.isDirectory()) {
				System.out.println("source file creation failed");
			}

			if (sourceCode.getFileExt().equals("java")) {
				String compileCommand = "javac " + sourceCode.getTitle() + "." + sourceCode.getFileExt();
				String runCommand = "java " + sourceCode.getTitle();

				System.out.println(compileCommand);
				System.out.println(runCommand);

				builder.command("/bin/sh", "-c", compileCommand + ";" + runCommand);
			} else {
				builder.command("/bin/sh", "-c", "python " + sourceFilePath.toString());
			}
			generateOutput();
		} catch (IOException e) {
			System.out.println("IOException happened - here's what I know: ");
			e.printStackTrace();
			// System.exit(-1);
		} catch (Exception e) {
			System.out.println("Exception happened - here's what I know: ");
			e.printStackTrace();
			// System.exit(-1);
		}
		return userFolderPath;
	}

	public static String readFileAsString(String fileName) throws Exception {
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fileName)));
		return data;
	}

}