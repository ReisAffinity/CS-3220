package ToDoLab;

public class ToDoEntry {

	int id;
	String description;
	String state;
	
	public ToDoEntry (int id, String description, String state) {
		super();
		this.id = id;
		this.description = description;
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}
	
	public boolean isNotDone(){
		return state.equals("notdone");
	}
	
	public String getOppositeState(){
		return state.equals("done") || state.equals("archived") ? "notdone" : "done";
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getId() {
		return id;
	}
}