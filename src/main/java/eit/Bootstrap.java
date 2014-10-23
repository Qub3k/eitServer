package eit;

import java.lang.*;
import spark.*;

import static spark.Spark.*;

public class Bootstrap {
	public static void main(String[] args) {
		Service service = new Service();
		staticFileLocation("/public");
		setPort(8080); 
		get("/mainFiles", "application/json", (req, res) -> service.listFiles("src/main/resources/public/"));
	}
}
