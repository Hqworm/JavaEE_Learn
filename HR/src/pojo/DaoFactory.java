package pojo;

public class DaoFactory {
	public static AdministratorDao createAdministratorDao() {
		return new AdministratorDao();
	}
	public static AdministratorDao createAdministratorDao(int id,String name,int password) {
		return new AdministratorDao();
	}
	public static TblDeptDao createTbldeptDao(int deptId, String dName) {
		return new TblDeptDao(deptId,  dName);
	}
	public static TblDeptDao createTbldeptDao() {
		return new TblDeptDao();
	}
	
	public static TblEmpDao createEmpDao(int empId, String eName, byte egendar, int deptId) {
		return new TblEmpDao(empId, eName, egendar,deptId);
	}
	
	public static TblEmpDao createEmpDao() {
		return new TblEmpDao();
	}
}
