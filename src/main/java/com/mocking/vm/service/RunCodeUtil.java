package com.mocking.vm.service;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.mocking.vm.SourceCode;

@Component
public class RunCodeUtil {
	private static final Logger log = LoggerFactory.getLogger(RunCodeUtil.class);
	
	private RunCodeUtil() {}
	
	//TODO throw exceptions
	protected void checkVMs() {
		
	}
	
	protected  StringBuilder readFromStreams(BufferedReader br, boolean saveOutput, PrintWriter printWriter) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
				if (saveOutput) {
					printWriter.println(line);
				}
				sb.append(line);
				sb.append(System.getProperty("line.separator"));
		}
		return sb;
	}
	
	/* TODO should also get a UserID+ UUID
	 * Unpon user creation we will have to create a unique path for the user and save 
	 * it to the database table. Move the following two functions to UserUtil.java
	 */
	protected  String getUserId() {
		return UUID.randomUUID().toString();
	}

	public  void generateSourceFile(SourceCode sourceCode, String sourceFilePath) {
		try (FileWriter fileWriter = new FileWriter(sourceFilePath)) {
			fileWriter.write(sourceCode.getCode());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}
