package pojo;

/**
 * @author worm
 *
 */
public class AdministratorDao {
	private int id;
	private String nameString;
	private int password;
	public AdministratorDao(int id, String nameString, int password) {
		this.id = id;
		this.nameString = nameString;
		this.password = password;
	}
	public AdministratorDao() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "AdministratorDao [id=" + id + ", nameString=" + nameString + ", password=" + password + "]";
	}
	
}
