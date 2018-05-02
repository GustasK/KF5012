
public class Task {
	
	private int id;
	private String title;
	private int priority;
	private boolean status;
//	private Caretaker assignedTo;
//	private Timestamp startDate;
//	private Timestamp endDate;
	private int expectedTimeTaken;
	
	public Task(int id, String title, int priority, boolean status, /* Caretaker assignedTo, Timestamp startdate, Timestamp endDate, */int expectedTimeTaken)
	{
		this.id = id;
		this.title = title;
		this.priority = priority;
		this.status = this.status;
//		this.assignedTo = assignedTo;
//		this.startDate = startDate;
//		this.endDate = endDate;
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

