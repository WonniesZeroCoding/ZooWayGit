package mapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VisitMapper {

	public void insertVisit(int mnum, int onum,int asnum, Date vdate) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String str=null;
		if(onum==0) str="asnum"; 
		else if(asnum==0) str="onum";
		try {
			String SQL = "insert into VISIT(vnum,mnum,"+str+",vdate,vstatus) values(vnum,?,?,?,1)";
			conn = DBAction.getInstance().getConnection();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setInt(1, mnum);
			if(asnum==0) pstmt.setInt(2, onum);
			else if(onum==0) pstmt.setInt(2, asnum);
			pstmt.setDate(3, vdate);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("insertVisit 오류");
			throw e;
		}
		
	}
	
	
	public int getASNum() {
		Connection conn=DBAction.getInstance().getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		int check=0;
		try {
			pstmt=conn.prepareStatement("select asnum from `AS` order by asnum desc limit 1");
			rs=pstmt.executeQuery();
			while(rs.next()) check=rs.getInt(1);
			return check;
		}catch (Exception e){e.printStackTrace();}
		finally {
			try {if(pstmt!=null) pstmt.close();
				
			} 
			catch (Exception e2) {e2.printStackTrace();}
		}//try 끝
		return 0;
	}
}
