package service;

import java.util.ArrayList;

import pojo.TblDeptDao;
import pojo.TblEmpDao;

public interface ITblDeptDaoService {



	ArrayList<TblDeptDao> getDeptList();

	TblDeptDao getOneById(String deptId);

	int getOneByName(String dName);

	

	

}
