package service;

import java.util.ArrayList;

import dao.ITblEmpDao;
import dao.TblEmpDaoImp;
import pojo.DaoFactory;
import pojo.TblEmpDao;

public class TblEmpServiceImp implements ITblEmpService {

 TblEmpDao employeeDao=DaoFactory.createEmpDao();
	 ITblEmpDao EmpDao=new TblEmpDaoImp();
	@Override
	public ArrayList<TblEmpDao> getEmployeeList() {
		return EmpDao.getEmployeeList();
	}
	
	@Override
	public TblEmpDao getOneById(String id) {
		// TODO Auto-generated method stub
		return EmpDao.getgetOneById( id);
	}

	@Override
	public int updataEmployeeDao(TblEmpDao employeeDao) {
		
		return EmpDao.updataEmployeeDao(employeeDao) ;
	}

	@Override
	public int addEmployeeDao(TblEmpDao employeeDao) {
		
		return EmpDao.addEmployeeDao(employeeDao);
	}

	@Override
	public int delByEmpId(String empId) {
		// TODO Auto-generated method stub
		return EmpDao.delByEmpId(empId) ;
	}

}
