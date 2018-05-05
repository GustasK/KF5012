import java.security.Timestamp;

public class Task {
	
	private int id;
	private String title;
	private int priority;
	private boolean status;
	private String assignedTo;
	private int startDate;
	private int endDate;
	private int expectedTimeTaken;
	
	public Task(int id, String title, int priority, boolean status,  String assignedTo, int startDate, int endDate, int expectedTimeTaken)
	{
		this.id = id;
		this.title = title;
		this.priority = priority;
		this.status = status;
		this.assignedTo = assignedTo;
		this.startDate = startDate;
		this.endDate = endDate;
		this.expectedTimeTaken = expectedTimeTaken;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public int getStartDate() {
		return startDate;
	}

	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}

	public int getEndDate() {
		return endDate;
	}

	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getExpectedTimeTaken() {
		return expectedTimeTaken;
	}

	public void setExpectedTimeTaken(int expectedTimeTaken) {
		this.expectedTimeTaken = expectedTimeTaken;
	}
	
	
}

