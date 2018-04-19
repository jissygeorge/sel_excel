package com.testproject.servicelayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

public class FileUtil {
	String path;
	String MimeType;

	public FileUtil(String p, String m) {
		path = p;
		MimeType = m;
	}

	public FileUtil() {

	}

	public List<FileProperty> getFilesofMimeType(String p, String m) {

		List<FileProperty> lfp = new LinkedList<FileProperty>();

		String fileType = "Undetermined";

		File dir = new File(p);
		File[] directoryListing = dir.listFiles();

		for (File file : directoryListing) {

			try {
				fileType = Files.probeContentType(file.toPath());
			} catch (IOException ioException) {
				System.out.println("ERROR: Unable to determine file type for " + file.getName() + " due to exception "
						+ ioException);
			}

			FileProperty fp = new FileProperty(p, file.getName(), getFileExtension(file), file.length(), fileType);
			lfp.add(fp);

		}
		return lfp;
	}

	private String getFileExtension(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

}
