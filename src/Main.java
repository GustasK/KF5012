import java.util.*;

public class Main {

	public static void main(String[] args) {

		Database database = new Database();
		
		System.out.println("PROGRAM STARTS");
		MainMenu menu = new MainMenu();
		List<Task> tasks = database.getTasks();
		AssignTask assignTask = new AssignTask(tasks);
		
	}    
}
