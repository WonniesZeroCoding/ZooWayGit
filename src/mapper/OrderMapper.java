package mapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dto.PolicyDTO;
import dto.TimeDTO;

public class OrderMapper {
	public ArrayList<TimeDTO> TimeSelect(int year,int month,int day)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TimeDTO> list = new ArrayList<TimeDTO>();
		String year1 = String.valueOf(year);
		String month1 = String.valueOf(month);
		String day1 = String.valueOf(day);
		
		
		
		try {// 하루라도 시간이 차있으면 나머지 가능한 시간들이 나오고 ,모든 시간이 프리하면 아무것도 나오지 않음
			String SQL = "select t.tnum,t.tcontent from Time t inner join VISIT v on t.tnum!=v.tnum where vdate =??? and vstatus=1";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, year1+"-");
			pstmt.setString(2, month1+"-");
			pstmt.setString(3, day1);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int tNum =  rs.getInt("tnum");
				String tContent = rs.getString("tcontent");
				list.add(new TimeDTO(tNum,tContent));
			}
		
		}catch(Exception e) {
			System.out.println("timeSelect오류");
			throw e;
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
		return list;
		
	}
	
	public ArrayList<PolicyDTO> PolicySelect() throws Exception{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<PolicyDTO> list = new ArrayList<PolicyDTO>();
		try {
			String SQL = "Select * from POLICY";			
			conn = DBAction.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(SQL);
			while(rs.next()) {
				int poldate  = rs.getInt("poldate");
				int polprice = rs.getInt("polprice");
				list.add(new PolicyDTO(poldate,polprice));
 			}
		
			
		}catch(Exception e) {
			System.out.println("PolicySelect 오류");
			throw e;
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(st!=null) {st.close();}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		return list;
	}
	public void insertOrder(int mnum,Date date,int tnum,int poldate,int pnum,int polprice,int ostatus)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String SQL = "insert into `ORDER` values(onum,?,?,?,?,?,?,?)";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, mnum);
			pstmt.setDate(2, date);
			pstmt.setInt(3, tnum);
			pstmt.setInt(4, poldate);
			pstmt.setInt(5, pnum);
			pstmt.setInt(6, polprice);
			pstmt.setInt(7, ostatus);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("insertOrder 오류");
			throw e;
		}finally {
			if(pstmt!=null) {pstmt.close();}
		}
		
		
		
	}
	
}
