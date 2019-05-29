package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbutils.GetConnect;
import pojo.DaoFactory;
import pojo.TblEmpDao;

public class TblEmpDaoImp implements ITblEmpDao{

	@Override
	public ArrayList<TblEmpDao> getEmployeeList() {
		TblEmpDao employeeDao=DaoFactory.createEmpDao();
		ArrayList<TblEmpDao> employeeList=new ArrayList<TblEmpDao>();
		Connection conn=GetConnect.getConnection();
		
		String sql="select * from tblemp";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet res=ps.executeQuery();
			while(res.next()) {
				employeeDao=DaoFactory.createEmpDao(res.getInt(1), res.getString(2), res.getByte(3), res.getInt(4));
				employeeList.add(employeeDao);
			}
			
			return employeeList;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return employeeList;
	}

	@Override
	public TblEmpDao getgetOneById(String id) {//通过id获取员工
		TblEmpDao employeeDao=null;
		Connection conn=GetConnect.getConnection();
		String sql="select * from tblemp where empid=?";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet res=ps.executeQuery();
			while(res.next()) {
				employeeDao=DaoFactory.createEmpDao(res.getInt(1), res.getString(2), res.getByte(3), res.getInt(4));
				
			}
			System.out.println("2");
			return employeeDao;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return employeeDao;
	}

	@Override
	public int updataEmployeeDao(TblEmpDao employeeDao) {
		int n=0;
		Connection conn=GetConnect.getConnection();
		String sql="update  tblemp set ename=? , egendar=? ,  deptid=? where empid=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, employeeDao.geteName());
			ps.setByte(2, employeeDao.getEgendar());
			ps.setInt(3, employeeDao.getDeptId());
			ps.setInt(4, employeeDao.getEmpId());
			
			n=ps.executeUpdate();
			return n;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int addEmployeeDao(TblEmpDao employeeDao) {
		int n=0;
		Connection conn=GetConnect.getConnection();
		String sql="insert into tblemp values(default,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, employeeDao.geteName());
			ps.setByte(2, employeeDao.getEgendar());
			ps.setInt(3, employeeDao.getDeptId());
			
			n=ps.executeUpdate();
			return n;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int delByEmpId(String empId) {
		
		int n=0;
		Connection conn=GetConnect.getConnection();
		String sql="delete from tblemp where empid=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(empId));
			n=ps.executeUpdate();
			return n;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return n;
	}

}
