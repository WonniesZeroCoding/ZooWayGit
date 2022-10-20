package mapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class VisitMapper {

	public void insertVisit(int mnum, int onum, Date vdate, int timeNum) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String SQL = "insert into VISIT(vnum,mnum,onum,vdate,tnum,vstatus) values(vnum,?,?,?,?,1)";
			conn = DBAction.getInstance().getConnection();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setInt(1, mnum);
			pstmt.setInt(2, onum);
			pstmt.setDate(3, vdate);
			pstmt.setInt(4, timeNum);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("insertVisit 오류");
			throw e;
		}
		
		
		
	}

}
