package dao;

import java.util.ArrayList;

import pojo.TblDeptDao;

public interface ITblDeptDao {

	ArrayList<TblDeptDao> getDeptList();

	TblDeptDao getOneById(String deptId);

	int getOneByName(String dName);

}
