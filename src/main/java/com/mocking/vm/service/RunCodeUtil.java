package com.mocking.vm.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.mocking.vm.component.SourceCode;

/*
 * @author: xiaofeng li
 * 
 * */

@Component
public class RunCodeUtil {
	private static final Logger log = LoggerFactory.getLogger(RunCodeUtil.class);

	private RunCodeUtil() {
	}

	protected StringBuilder readFromStreams(BufferedReader br, boolean saveOutput, PrintWriter printWriter)
			throws IOException {
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

	public boolean fileExecutable(String filePath) {
		File file = new File(filePath);
		System.out.println(filePath);
		System.out.println(file.isFile());
		System.out.println(file.canExecute());
		return file.isFile() && file.canExecute();
	}
	
	public void generateSourceFile(SourceCode sourceCode, String sourceFilePath) {
		try (FileWriter fileWriter = new FileWriter(sourceFilePath)) {
			fileWriter.write(sourceCode.getCode());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}
