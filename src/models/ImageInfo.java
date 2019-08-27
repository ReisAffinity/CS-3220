package models;

public class ImageInfo {
	int id;
	static int count = 0;
	String fileName;
	
	public ImageInfo(int id, String fileName) {
		super();
		this.id = count++;
		this.fileName = fileName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
