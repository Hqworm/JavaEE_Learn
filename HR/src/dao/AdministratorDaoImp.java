package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutils.GetConnect;

/**
 * ʵ�ֽӿ�AdministratorDaoImp
 * @author worm
 *
 */
public class AdministratorDaoImp implements IAdministratorDao{

	/**
	 * ����name pwd�鿴����Ա�Ƿ����
	 */
	@Override
	public int isExist(String name, String pwd) {
		//1.��ȡ���ݿ�����
		Connection conn=GetConnect.getConnection();
		int n=0;
		//2.sql
		String sql="select * from  Administrator where admin_name=? and admin_password=?";
		//3.����Ԥ���� ��ʽSQLע��
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
