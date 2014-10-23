package eit;

import java.lang.*;
import java.io.File;
import spark.*;

import static spark.Spark.*;

public class Bootstrap {
	public static void main(String[] args) {
		Service service = new Service();
		staticFileLocation("/public");
		setPort(8080);
		File baseFile = new File("src/main/resources/public/"); 
		get("/fileList", "application/json", (req, res) -> service.listFiles(baseFile));
	}
}
