package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbutils.GetConnect;
import pojo.DaoFactory;
import pojo.TblDeptDao;

public class TblDeptDaoImp implements ITblDeptDao{
	@Override
	public ArrayList<TblDeptDao> getDeptList() {
		TblDeptDao DeptDao=DaoFactory.createTbldeptDao();
		ArrayList<TblDeptDao> DeptList=new ArrayList<TblDeptDao>();
		Connection conn=GetConnect.getConnection();
		
		String sql="select * from tbldept";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet res=ps.executeQuery();
			while(res.next()) {
				DeptDao=DaoFactory.createTbldeptDao(res.getInt(1), res.getString(2));
				DeptList.add(DeptDao);
			}
			return DeptList;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return DeptList;
	}

	@Override
	public TblDeptDao getOneById(String deptId) {
		TblDeptDao DeptDao=null;
		Connection conn=GetConnect.getConnection();
		
		String sql="select * from tbldept where deptid=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(deptId));
			ResultSet res=ps.executeQuery();
			while(res.next()) {
				DeptDao=DaoFactory.createTbldeptDao(res.getInt(1), res.getString(2));
				
			}
			return DeptDao;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return DeptDao;
	}

	@Override
	public int getOneByName(String dName) {
		int deptid=0;
		Connection conn=GetConnect.getConnection();
		
		String sql="select deptid from tbldept where dName=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, dName);
			ResultSet res=ps.executeQuery();
			while(res.next()) {
				deptid=res.getInt(1);
				
			}
			return deptid;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return 0;
	}

}

