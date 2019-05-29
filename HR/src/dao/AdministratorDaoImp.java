package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutils.GetConnect;

/**
 * 实现接口AdministratorDaoImp
 * @author worm
 *
 */
public class AdministratorDaoImp implements IAdministratorDao{

	/**
	 * 根据name pwd查看管理员是否存在
	 */
	@Override
	public int isExist(String name, String pwd) {
		//1.获取数据库连接
		Connection conn=GetConnect.getConnection();
		int n=0;
		//2.sql
		String sql="select * from  Administrator where admin_name=? and admin_password=?";
		//3.利用预编译 方式SQL注入
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pwd);
			ResultSet resultSet=ps.executeQuery();
			while(resultSet.next()) {
				n++;
			}
			return n;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return n;
	}

}
