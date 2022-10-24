package mapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.OrderDTO;
import dto.VisitDTO;

public class rentalMapper {
	public ArrayList<OrderDTO> selectAllrental() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<OrderDTO> list = new ArrayList<OrderDTO>();
		try {
			String SQL = "select distinct o.*,m.maddress1,s.Address,m.ZOOCAREPLUS from `ORDER` o inner join MEMBER m inner join SEOULADDR s on o.mnum = m.mnum and m.maddress1 = s.AddrNum  order by ZOOCAREPLUS desc,onum asc";
			conn =DBAction.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(SQL);
			
			while(rs.next()) {
				int onum = rs.getInt("onum");
				int mnum = rs.getInt("mnum");
				String vdate  = String.valueOf(rs.getDate("vdate"));
				int poldate = rs.getInt("poldate");
				int pnum = rs.getInt("pnum");
				int polprice = rs.getInt("polprice");
				int ostatus = rs.getInt("ostatus");
				String endDate = String.valueOf(rs.getDate("endDate"));
				int maddress = rs.getInt("maddress1");
				String address = rs.getString("Address");
				int ZOOCAREPLUS = rs.getInt("ZOOCAREPLUS");
				list.add(new OrderDTO(onum,mnum,vdate,poldate,pnum,polprice,ostatus,endDate,maddress,address,ZOOCAREPLUS));
			}
		}catch(Exception e) {
			System.out.println("selectAllrental오류");
			throw e;
		}finally {
			if(rs!=null) {rs.close();}
			if(st!=null) {st.close();}
		}
		return list;
		
		
		
		
	}
	public ArrayList<OrderDTO> selectLimitRental()throws Exception {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<OrderDTO> list = new ArrayList<OrderDTO>();
		try {
			String SQL = "SELECT o.*,datediff(o.endDATE,sysdate()) FROM `ORDER` o where datediff(endDATE,sysdate()) < 100 ";
			conn =DBAction.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(SQL);
			while(rs.next()) {
				int onum = rs.getInt("onum");
				int mnum = rs.getInt("mnum");
				String vdate = String.valueOf(rs.getDate("vdate"));
				int poldate = rs.getInt("poldate");
				int pnum = rs.getInt("pnum");
				int polprice = rs.getInt("polprice");
				int ostatus = rs.getInt("ostatus");
				String endDate = String.valueOf(rs.getDate("endDate"));
				int endDateCount = rs.getInt("datediff(o.endDATE,sysdate())");
				list.add(new OrderDTO(onum,mnum,vdate,poldate,pnum,polprice,ostatus,endDate,endDateCount));
				
				
			}
			
		}catch(Exception e) {
			System.out.println("selectLimitRental 오류");
			throw e;
		}
		
		
		
		return list;
	}
	
	
}
