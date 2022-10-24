package mapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//그냥 매번 이걸 이용해서 열어주도록 하자
  
public class DBAction {
	private Connection conn;
	private static DBAction instance;
	public DBAction() {
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	         conn=DriverManager.getConnection("jdbc:mysql://zoowaydb.cnwnq4esimiu.us-west-1.rds.amazonaws.com/zooway?useSSL=false&autoReconnect=true","admin","zooway195");
		} catch (ClassNotFoundException |SQLException e) {e.printStackTrace();	}
	}
	
	public static DBAction getInstance() {
		if(instance==null)
			instance=new DBAction();
		return instance;
	}
	
	public Connection getConnection() {
		return this.conn;
	}
}
