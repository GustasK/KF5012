import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
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
