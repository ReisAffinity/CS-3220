package models;

public class PhotoInfo {
	String name;
	String description;
	int id;
	static int count = 0;
	public PhotoInfo(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.id = count++;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	
}
