package dao;

import java.util.ArrayList;

import pojo.TblEmpDao;

public interface ITblEmpDao {

	ArrayList<TblEmpDao> getEmployeeList();

	TblEmpDao getgetOneById(String id);

	int updataEmployeeDao(TblEmpDao employeeDao);

	int addEmployeeDao(TblEmpDao employeeDao);

	int delByEmpId(String empId);

}
