package dao;

import java.util.List;

import pojo.DepartmentDao;

public interface IDepartmentDao {

	public List<DepartmentDao> getLists(String number, String name);

	public int insert(DepartmentDao departmentDao);

	public int delById(String id);


	public int update(DepartmentDao dept, int id2);

	DepartmentDao getDepartmentById(String id);

	public List<DepartmentDao> getListByNowPage(String nowPage, int pageCount);


}
