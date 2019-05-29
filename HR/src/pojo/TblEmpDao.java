package pojo;

public class TblEmpDao {

	private int empId;
	private String eName;
	private byte  egendar;
	private int deptId;
	public TblEmpDao(int empId, String eName, byte egendar, int deptId) {
		super();
		this.empId = empId;
		this.eName = eName;
		this.egendar = egendar;
		this.deptId = deptId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public byte getEgendar() {
		return egendar;
	}
	public void setEgendar(byte egendar) {
		this.egendar = egendar;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public TblEmpDao() {
	}
	@Override
	public String toString() {
		return "TblEmpDao [empId=" + empId + ", eName=" + eName + ", egendar=" + egendar + ", deptId=" + deptId + "]";
	}
	
	
}
