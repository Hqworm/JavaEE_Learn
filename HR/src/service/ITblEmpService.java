package service;

import java.util.ArrayList;

import pojo.TblEmpDao;

public interface ITblEmpService {

	ArrayList<TblEmpDao> getEmployeeList();

	TblEmpDao getOneById(String id);

	int updataEmployeeDao(TblEmpDao employeeDao);

	int addEmployeeDao(TblEmpDao employeeDao);

	int delByEmpId(String empId);

}
