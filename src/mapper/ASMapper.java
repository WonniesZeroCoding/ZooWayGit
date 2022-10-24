package mapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dto.ASDTO;

public class ASMapper {

	public ArrayList<ASDTO> selectAllAS() throws Exception {
		ArrayList<ASDTO> list = new ArrayList<ASDTO>();
		Connection conn  = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			String SQL = "SELECT * FROM `AS` ";
			conn  = DBAction.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(SQL);
			while(rs.next()) {
				int asnum = rs.getInt("asnum");
				int onum = rs.getInt("onum");
				String vdate = String.valueOf(rs.getDate("vdate"));
				int poldate = rs.getInt("poldate");
				
				list.add(new ASDTO(asnum,onum,vdate,poldate));
			}
		}catch(Exception e) {
			System.out.println("selectAllAs 오류");
			throw e;
		}finally {
			if(rs!=null) {rs.close();}
			if(st!=null) {st.close();}
		}
		return list;
	}
	
}
