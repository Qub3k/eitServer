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

public class Service {
	private List<File> myFiles;

	public Service() {
		myFiles = new ArrayList<>();
	}

	public String listFiles(File baseFile) {;
		listAllFiles(baseFile);
		String json = toJson(myFiles);
		return json;
	}

	private void listAllFiles(File baseFile) {
		if(baseFile.isDirectory()) {
			File[] filesInside = baseFile.listFiles();
			for(File file : filesInside) {
				listAllFiles(file);
			}
		}
		else myFiles.add(new File(baseFile.getAbsolutePath()));
	}

	private String toJson(List<File> myFile) {
		StringBuilder jsonBuilder = new StringBuilder();
		jsonBuilder.append('{');
		for(File file : myFile) {
			// adding the key
			jsonBuilder.append("\"" + file.getName() + "\"");
			jsonBuilder.append(":{");
			// adding key-value properties
			jsonBuilder.append("\"path\":" + "\"" + file.getAbsolutePath() + "\"" + ',');
			jsonBuilder.append("\"size\":" + "\"" + getLengthOf(file) + "\"" + ',');
			jsonBuilder.append("\"lastMod\":" + "\"" + file.lastModified() + "\"");
			jsonBuilder.append("},");
		}
		jsonBuilder.append('}');
		String json = jsonBuilder.toString();
		return json;
	}

	private String getLengthOf(File file) {
		StringBuilder sizeBuilder = new StringBuilder();
		DecimalFormat formatter = new DecimalFormat();
		formatter.setMaximumSignificantDigits(2);
		if (file.size/1000000.0) {
			sizeBuilder.append(file.size/1000000.0 + "MB")
			return sizeBuilder.toString();
		}
		else {
			sizeBuilder.append(file.size/1000.0 + "KB")
			return sizeBuilder.toString();
		}
	}
}
