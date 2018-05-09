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
				String type = result.getString("type");
				String priority = result.getString("priority");
				boolean status = result.getBoolean("status");
				int assignedTo = result.getInt("assigned_to");
				String startDate = result.getString("start_date");
				String endDate = result.getString("end_date");	
				int expectedTimeTaken = result.getInt("expected_time");
				Task task = new Task(id, title, type, priority, status, assignedTo, startDate, endDate, expectedTimeTaken);
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
	
	public User getUser(int id)
	{	
		User user = null;
		String SQL = "SELECT * FROM users WHERE id = ?";
		try(Connection connection = this.connect()) {
		
			PreparedStatement statement = connection.prepareStatement(SQL);
			
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				String name = result.getString("name");
				String password = result.getString("password");
				int permissions = result.getInt("permissions");
				
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
			}	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
	
	public void update(String table, int id, String field, String value)
	{
		String SQL = "UPDATE " + table + " SET " + field + " = ? WHERE id = ?";
		try (Connection connection = this.connect()) {
			
			PreparedStatement statement = connection.prepareStatement(SQL);
				
			if (field.equals("permissions")) {
				statement.setInt(1, Integer.parseInt(value));
				statement.setInt(2, id);
			}
			else {
				statement.setString(1, value);
				statement.setInt(2,id);
			}
				
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
