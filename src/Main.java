import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Main {

	public static void main(String[] args) {

		Database database = new Database();
		
		System.out.println("PROGRAM STARTS");
		String[] fields = new String[] {"name"};
		String[] values = new String[] {"Gustenkox"};
//		database.insert("users", fields, values);
//		database.delete("users", "id", "=", "1");
	
		List<Task> tasks = database.getTasks();
		System.out.println(tasks);
	}
}
