package dbutils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.Statement;

public class GetConnect {
	//
	private static ComboPooledDataSource dataSource=new ComboPooledDataSource();
	public static Connection getConnection(){
		Connection connection  = null;
			try {
				connection =dataSource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}//获取连接
	
		return connection;
	}
	
	public static void close(Connection connection) {
		if(connection==null) {
			try {
				//连接池中获取的，但是并不会真正的关闭
				connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
