import java.security.Timestamp;
import java.sql.*;
import java.util.*;

public class Database {
	
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
				String assignedTo = result.getString("assigned_to");
				int startDate = result.getInt("start_date");
				int endDate = result.getInt("end_date");	
				int expectedTimeTaken = result.getInt("expected_time");
				Task task = new Task(id, title, priority, status, assignedTo, startDate, endDate, expectedTimeTaken);
				allTasks.add(task);
			}
			
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return allTasks;
	}
	
	public List<User> getUsers()
	{
		List<User> allUsers = new ArrayList<User>();
		
		try (Connection connection = this.connect()) {
			
			Statement statement = connection.createStatement();
			String SQL = "SELECT * FROM users";
			ResultSet result;
			
			result =  statement.executeQuery(SQL);
			while(result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				String password = result.getString("password");
				int permissions = result.getInt("permissions");
				
				User user;
				switch(permissions) {
					case 1: 
						user = new Caretaker(id, name, password, permissions);
						break;
					case 2:
						user = new Administrator(id, name, password, permissions);
						break;
					case 3:
						user = new Manager(id, name, password, permissions);
						break;
					default:
						user = new Caretaker(id, name, password, permissions);
						break;
				}
				
				allUsers.add(user);
			}
			
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return allUsers;
	}
	
	public void update(String table, int id, String field, String value)
	{
		String SQL = "UPDATE " + table + " SET " + field + " = ? WHERE id = ?";
		try (Connection connection = this.connect()) {
			
			PreparedStatement statement = connection.prepareStatement(SQL);
				
			statement.setString(1, value);	
			statement.setInt(2, id);
				
			statement.executeUpdate();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
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
