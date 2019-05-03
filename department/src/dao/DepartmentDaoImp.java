package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import pojo.DepartmentDao;
import util.GetConnect;

public class DepartmentDaoImp implements IDepartmentDao{

	@Override
	public List<DepartmentDao> getLists(String number, String name) {
		
		Connection conn=GetConnect.getConnection();
		String sql="select * from department where 1=1  ";
		List<DepartmentDao>list=new ArrayList<DepartmentDao>();
		if(number==null ||number.equals("")) {//如果为空
			
		}else {
			sql+=" and d_number= ?";
		}
		if(name==null||name.equals("")) {
			
		}else {
			sql+=" and d_name=?";
		}
		
		try {
			PreparedStatement	ps=conn.prepareStatement(sql);
			//为？赋值要判断
			if(number==null||number.equals("")) {
				if(name==null||name.equals("")) {//两个都为空 不设置值
					
				}else {//没number 有name
					ps.setString(1, name);
				}
			}else {
				ps.setString(1, number);
				if(name==null||name.equals("")) {//有number  无 name
					
				}else {//既有number,又有name
					
					ps.setString(2, name);
				}
			}
			ResultSet resultset=ps.executeQuery();
			
			
			while(resultset.next()) {
				DepartmentDao department=new DepartmentDao(resultset.getInt(1),resultset.getString(2),resultset.getString(3),resultset.getString(4));
				list.add(department);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			GetConnect.close(conn);
		}
		return list;
	}

	@Override
	public int insert(DepartmentDao departmentDao) {//添加一个部门
		if(departmentDao.getNumber()==null|| departmentDao.getNumber().equals("")) {
			return 0;
		}
		Connection conn=GetConnect.getConnection();
		String sql="insert into department values(null,?,?,?) ";
		PreparedStatement ps;
		int k=0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, departmentDao.getNumber());
			ps.setString(2, departmentDao.getName());
			ps.setString(3, departmentDao.getLocation());
			 k=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			GetConnect.close(conn);
		}
		return k;
	}

	
	@Override
	public int delById(String id){//删除
		Connection conn=GetConnect.getConnection();
		String sql="delete from department  where id=?";
		PreparedStatement ps;
		int k=0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));
			k=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			GetConnect.close(conn);
		}
		return k;
	}

	@Override
	public DepartmentDao getDepartmentById(String id) {
		Connection conn=GetConnect.getConnection();
		String sql="select * from department  where id=?";
		PreparedStatement ps;
		int k=0;
		DepartmentDao department=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet set=ps.executeQuery();
			if(set.next()) {
				department=new DepartmentDao(set.getInt(1),set.getString(2),set.getString(3),set.getString(4));
			}
			return department;
		}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				GetConnect.close(conn);
			}
			
			return null;
	}

	@Override
	public int update(DepartmentDao dept, int id2) {
		if(dept.getNumber()==null|| dept.getNumber().equals("")) {
			return 0;
		}
		Connection conn=GetConnect.getConnection();
		String sql="UPDATE  department SET d_number=?,d_name=?,d_location=? WHERE id=? ";
		PreparedStatement ps;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, dept.getNumber());
			ps.setString(2, dept.getName());
			ps.setString(3, dept.getLocation());
			ps.setInt(4, dept.getId());
			int  k=ps.executeUpdate();
			 return k;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			GetConnect.close(conn);
		}
		return 0;
		
	}

	@Override
	public List<DepartmentDao> getListByNowPage(String nowPage, int pageCount) {
		
		//nowPage 当前页面
		// pageCount:页总数
		Connection conn=GetConnect.getConnection();
		String sql="SELECT * FROM department  limit ?,?";
		PreparedStatement ps;
		List<DepartmentDao>list=new ArrayList<DepartmentDao>();//用来存放显示的数据
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, 10*(Integer.parseInt(nowPage)-1));
			ps.setInt(2, 10);
			ResultSet set=ps.executeQuery();
			DepartmentDao dept;
			while(set.next()) {
				dept=new DepartmentDao(set.getInt(1),set.getString(2),set.getString(3),set.getString(4));  
				list.add(dept);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			GetConnect.close(conn);
		}
		return list;
	}
}


