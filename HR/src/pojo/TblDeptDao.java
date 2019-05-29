package pojo;

public class TblDeptDao {

	private int deptId;
	private String dName;
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public TblDeptDao() {
	}
	public TblDeptDao(int deptId, String dName) {
		this.deptId = deptId;
		this.dName = dName;
	}
	@Override
	public String toString() {
		return "TbldeptDao [deptId=" + deptId + ", dName=" + dName + "]";
	}
	
	
}
