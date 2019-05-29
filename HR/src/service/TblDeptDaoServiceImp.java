package service;

import java.util.ArrayList;

import dao.ITblDeptDao;
import dao.ITblEmpDao;
import dao.TblDeptDaoImp;
import dao.TblEmpDaoImp;
import pojo.DaoFactory;
import pojo.TblDeptDao;
import pojo.TblEmpDao;

public class TblDeptDaoServiceImp implements ITblDeptDaoService {
	private  TblDeptDao DeptDao=DaoFactory.createTbldeptDao();
	private ITblDeptDao TblDeptDao=new TblDeptDaoImp();
	@Override
	public ArrayList<TblDeptDao> getDeptList() {
		
		return TblDeptDao.getDeptList();
	}
	@Override
	public pojo.TblDeptDao getOneById(String deptId) {
		
		return TblDeptDao. getOneById(deptId);
	}
	@Override
	public int getOneByName(String dName) {
		// TODO Auto-generated method stub
		return TblDeptDao.getOneByName(dName);
	}


}
