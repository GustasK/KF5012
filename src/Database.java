import java.sql.*;
import java.util.*;

public class Database {
	
	public void selectAll(String table)
	{
		try (Connection connection = this.connect()) {
			
			Statement statement = connection.createStatement();
			String SQL = "SELECT * FROM " + table;
			
			statement.executeUpdate(SQL);
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Task> getTasks()
	{
		List<Task> allTasks = new ArrayList<Task>();
		
		try (Connection connection = this.connect()) {
			
			Statement statement = connection.createStatement();
			String SQL = "SELECT * FROM tasks";
			ResultSet result;
			
			result =  statement.executeQuery(SQL);
			while(result.next()) {
				int id = result.getInt("id");
				String title = result.getString("title");
				int priority = result.getInt("priority");
				boolean status = result.getBoolean("status");
				int expectedTimeTaken = result.getInt("expected_time");
				System.out.println(id + title + " " + status);
				Task task = new Task(id, title, priority, status, expectedTimeTaken);
				allTasks.add(task);
			}
			
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return allTasks;
	}
	
	public void delete(String table, String field, String operator, String value)
	{
		try (Connection connection = this.connect()) {
			
			Statement statement = connection.createStatement();
			String SQL = "DELETE FROM " + table + " WHERE " + field + " " + operator + " " + value;
			
			statement.executeUpdate(SQL);
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insert(String table, String[] fields, String[] values)
	{
		if(fields.length == values.length)
		{
			String joinedFields = String.join(", ", fields);
			String joinedValues = String.join("', '", values);

			
			try (Connection connection = this.connect()) {
				
				Statement statement = connection.createStatement();
				String SQL = "INSERT INTO " + table + "(" + joinedFields + ") " + "VALUES('" + joinedValues + "')";

				statement.executeUpdate(SQL);
				statement.close();
				connection.close();
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private Connection connect()
	{
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:database.db");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return connection;
	}

}
