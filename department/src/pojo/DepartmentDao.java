package pojo;

public class DepartmentDao {
private int id;
private String number;
private String name;
private String location;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public DepartmentDao(int id, String number, String name, String location) {
	super();
	this.id = id;
	this.number = number;
	this.name = name;
	this.location = location;
}
public DepartmentDao() {
	
}

}
