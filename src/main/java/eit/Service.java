package eit;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.lang.String;
import java.lang.StringBuilder;
import com.ibm.icu.text.DecimalFormat;
import java.util.Date;

public class Service {
	private List<File> myFiles;
	private File inputFile;

	public Service() {
		myFiles = new ArrayList<>();
	}

	public String listFiles(String path) {
		myFiles.clear();
		inputFile = new File(path);
		listAllFiles(inputFile);
		String json = toJson(myFiles);
		return json;
	}

	private void listAllFiles(File baseFile) {
		File[] filesInside = baseFile.listFiles();
		for(File file : filesInside)
			myFiles.add(new File(file.getAbsolutePath()));
	}

	private String toJson(List<File> myFile) {
		StringBuilder jsonBuilder = new StringBuilder();
		jsonBuilder.append('[');
		for(File file : myFile) {
			jsonBuilder.append('{');
			// adding the key
			File currentDir = new File("src/main/resources/public");
			Path currentPath = Paths.get(currentDir.getAbsolutePath());
			// relativizing file path to the resoureces location in src/main/resources/public
			Path filePath = currentPath.relativize(Paths.get(file.getAbsolutePath()));
			jsonBuilder.append("\"name\":" + "\"" + file.getName() + "\"" + ',');
			// adding key-value properties
			jsonBuilder.append("\"path\":" + "\"" + filePath.toString() + "\"" + ',');
			jsonBuilder.append("\"size\":" + "\"" + getLengthOf(file) + "\"" + ',');
			// getting the date of last modification and chenging it to the string value
			Date lastModified = new Date(file.lastModified());
			jsonBuilder.append("\"lastMod\":" + "\"" + lastModified.toString() + "\"" + ',');
			jsonBuilder.append("\"isDir\":" + file.isDirectory());
			jsonBuilder.append("},");
		}
		// getting rid of redundant comma
		jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);
		jsonBuilder.append(']');
		String json = jsonBuilder.toString();
		return json;
	}

	private String getLengthOf(File file) {
		StringBuilder sizeBuilder = new StringBuilder();
		DecimalFormat formatter = new DecimalFormat();
		formatter.setMaximumSignificantDigits(2);
		if (file.length()/1000000.0 > 1) {
			sizeBuilder.append(formatter.format(file.length()/1000000.0) + "MB");
			return sizeBuilder.toString();
		}
		else {
			sizeBuilder.append(formatter.format(file.length()/1000.0) + "KB");
			return sizeBuilder.toString();
		}
	}
}
