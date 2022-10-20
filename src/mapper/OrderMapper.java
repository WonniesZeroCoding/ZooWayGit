package mapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.MemberDTO;
import dto.OrderDTO;
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
   public void insertOrder(int mnum,Date date,int tnum,int poldate,int pnum,int polprice,int ostatus,Date endDate)throws Exception{
      Connection conn = null;
      PreparedStatement pstmt = null;
      try {
         String SQL = "insert into `ORDER` values(onum,?,?,?,?,?,?,?,?)";
         conn = DBAction.getInstance().getConnection();
         pstmt = conn.prepareStatement(SQL);
         pstmt.setInt(1, mnum);
         pstmt.setDate(2, date);
         pstmt.setInt(3, tnum);
         pstmt.setInt(4, poldate);
         pstmt.setInt(5, pnum);
         pstmt.setInt(6, polprice);
         pstmt.setInt(7, ostatus);
         pstmt.setDate(8, endDate);
         pstmt.executeUpdate();
      }catch(Exception e) {
         System.out.println("insertOrder 오류");
         throw e;
      }finally {
         if(pstmt!=null) {pstmt.close();}
      }   
   }
   /* 로그인한 회원이 주무한 렌탈 목록 전체 리스트 (해지상품 제외)*/
      public List<OrderDTO> memberOrderList(MemberDTO member) {
         Connection conn = DBAction.getInstance().getConnection();
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         List<OrderDTO> memberOrders = new ArrayList<OrderDTO>();

         try {
            pstmt = conn.prepareStatement("SELECT o.onum, o.mnum, o.vdate, o.tnum, t.tcontent , o.poldate, o.pnum, o.polprice, o.ostatus\r\n"
                  + "from ORDER o, Time t\r\n"
                  + "where o.tnum = t.tnum\r\n"
                  + "and o.mnum=?");
            pstmt.setInt(1, member.getMnum());
            rs = pstmt.executeQuery();

            while (rs.next()) {
               OrderDTO memberOrder = new OrderDTO();
               memberOrder.setOnum(rs.getInt(1));
               memberOrder.setMnum(rs.getInt(2));
               memberOrder.setVdate(rs.getString(3));
               memberOrder.setTnum(rs.getInt(4));
               memberOrder.setTcontent(rs.getString(5));
               memberOrder.setPoldate(rs.getInt(6));
               memberOrder.setPnum(rs.getInt(7));
               memberOrder.setPolprice(rs.getInt(8));
               memberOrder.setOstatus(rs.getInt(9));
               memberOrders.add(memberOrder);
            }
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            try {
               if (rs != null)
                  rs.close();
               if (pstmt != null)
                  pstmt.close();
            } catch (Exception e2) {
               e2.printStackTrace();
            }
         } // try 끝

         return memberOrders;
      }

      //로그인 회원이 가진 주문번호에 해당하는지 검사
      public boolean memberOrderCheck(int mnum, int onum) {
         Connection conn= DBAction.getInstance().getConnection();
         PreparedStatement pstmt=null;
         ResultSet rs= null;
         int check=0;
         try {
            pstmt=conn.prepareStatement("select count(*) from ORDER where onum=? and mnum=?");
            pstmt.setInt(1, onum);
            pstmt.setInt(2, mnum);
            rs=pstmt.executeQuery();
            while(rs.next()) check=rs.getInt(1);
            return check>0 ? true:false;
            }catch (Exception e){e.printStackTrace();}
            finally {
               try {if(pstmt!=null) pstmt.close();} 
               catch (Exception e2) {e2.printStackTrace();}
         }//try 끝
         return false;
      }
      public OrderDTO selectResult(int mnum,int pnum)throws Exception{
  		Connection conn = null;
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;
  		OrderDTO order = null;
  		try {
  			String SQL = "select o.onum,(select pname from PRODUCT where pnum = ?)as pname,o.mnum,o.vdate,o.poldate,o.ostatus,t.tcontent from `ORDER` o inner join Time t on o.tnum = t.tnum where mnum = ?";
  			conn =DBAction.getInstance().getConnection();
  			pstmt  = conn.prepareStatement(SQL);
  			pstmt.setInt(1, pnum);
  			pstmt.setInt(2, mnum);
  			rs = pstmt.executeQuery();
  			while(rs.next()) {
  				int onum = rs.getInt("onum");
  				String pname = rs.getString("pname");
  				String vdate = String.valueOf(rs.getDate("vdate"));
  				int poldate = rs.getInt("poldate");
  				int ostatus = rs.getInt("ostatus");
  				String tcontent = rs.getString("tcontent");
  				order = new OrderDTO(onum,pname,vdate,poldate,ostatus,tcontent);
  			}
  			
  			
  			
  			
  		}catch(Exception e) {
  			System.out.println("selectResult 오류");
  			throw e;
  		}finally {
  			if(rs!=null) {rs.close();}
  			if(pstmt!=null) {pstmt.close();}
  		}
  		
  		return order;
  	}
  	public int selectProCount(int num) throws SQLException {
  		Connection conn = null;
  		PreparedStatement pstmt = null;		
  		ResultSet rs = null;
  		int count = 0;
  		try {
  			String SQL = "select pcount from PRODUCT where pnum = ?";
  			conn = DBAction.getInstance().getConnection();
  			pstmt = conn.prepareStatement(SQL);
  			pstmt.setInt(1, num);
  			rs = pstmt.executeQuery();
  			while(rs.next()) {
  				count = rs.getInt("pcount");
  			}
  		}catch(Exception e) {
  			System.out.println("selectProcount 오류");
  			throw e;
  		}finally {
  			if(rs!=null) {rs.close();}
  			if(pstmt!=null) {pstmt.close();}
  		}
  		return count;
  		
  	}
  	
  	public void MinusCountAndStatus(int num,int count) throws SQLException {
  		// TODO Auto-generated method stub
  		Connection conn = null;
  		PreparedStatement pstmt = null;
  		try {
  			String SQL = "update PRODUCT set pcount=?,pstatus=0 where pnum = ?";
  			conn = DBAction.getInstance().getConnection();
  			pstmt = conn.prepareStatement(SQL);
  			pstmt.setInt(1, count);
  			pstmt.setInt(2, num);
  			pstmt.executeUpdate();
  			
  		}catch(Exception e) {
  			System.out.println("MinusCountAndStatus 오류");
  			throw e;
  		}finally {
  			if(pstmt!=null) {pstmt.close();}
  		}
  		
  		
  	}
  	
  	
  	public void MinusCount(int num,int count) throws Exception {
  		Connection conn = null;
  		PreparedStatement pstmt = null;
  		try {
  			String SQL = "update PRODUCT set pcount=? where pnum = ?";
  			conn = DBAction.getInstance().getConnection();
  			pstmt = conn.prepareStatement(SQL);
  			pstmt.setInt(1, count);
  			pstmt.setInt(2, num);
  			pstmt.executeUpdate();
  			
  		}catch(Exception e) {
  			System.out.println("MinusCount 오류");
  			throw e;
  		}finally {
  			if(pstmt!=null) {pstmt.close();}
  		}
  	}

  	

  	
}
