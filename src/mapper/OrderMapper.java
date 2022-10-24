package mapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import dto.ASDTO;
import dto.EmployeeDTO;
import dto.MemberDTO;
import dto.OrderDTO;
import dto.PolicyDTO;

public class OrderMapper {
	  
	public ArrayList<PolicyDTO> PolicySelect() throws Exception {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<PolicyDTO> list = new ArrayList<PolicyDTO>();
		try {
			String SQL = "Select * from POLICY";
			conn = DBAction.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(SQL);
			while (rs.next()) {
				int poldate = rs.getInt("poldate");
				int polprice = rs.getInt("polprice");
				list.add(new PolicyDTO(poldate, polprice));
			}

		} catch (Exception e) {
			System.out.println("PolicySelect 오류");
			throw e;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public void insertOrder(int mnum,Date date,int poldate,int pnum,int polprice,int ostatus,Date endDate)throws Exception{
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      try {
	         String SQL = "insert into `ORDER` values(onum,?,?,?,?,?,?,?)";
	         conn = DBAction.getInstance().getConnection();
	         pstmt = conn.prepareStatement(SQL);
	         pstmt.setInt(1, mnum);
	         pstmt.setDate(2, date);
	         pstmt.setInt(3, poldate);
	         pstmt.setInt(4, pnum);
	         pstmt.setInt(5, polprice);
	         pstmt.setInt(6, ostatus);
	         pstmt.setDate(7, endDate);
	         pstmt.executeUpdate();
	      }catch(Exception e) {
	         System.out.println("insertOrder 오류");
	         throw e;
	      }finally {
	         if(pstmt!=null) {pstmt.close();}
	      }   
	   }

	/* 로그인한 회원이 주문한 렌탈 목록 전체 리스트 (해지상품 제외) */
	public List<OrderDTO> memberOrderList(MemberDTO member) {
		System.out.println("회원번호->" + member.getMnum());
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<OrderDTO> memberOrders = new ArrayList<OrderDTO>();

		try {
			pstmt = conn.prepareStatement(
					"SELECT onum, mnum, vdate, poldate, pnum, polprice, ostatus, endDate\r\n"
					+ "from zooway.ORDER \r\n"
					+ "where mnum=? \r\n"
					+ "and ostatus!=0 \r\n"
					+ "and ostatus!=4");
			pstmt.setInt(1, member.getMnum());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderDTO memberOrder = new OrderDTO();
				memberOrder.setOnum(rs.getInt(1));
				memberOrder.setMnum(rs.getInt(2));
				memberOrder.setVdate(rs.getString(3));
				memberOrder.setPoldate(rs.getInt(4));
				memberOrder.setPnum(rs.getInt(5));
				memberOrder.setPolprice(rs.getInt(6));
				memberOrder.setOstatus(rs.getInt(7));
				memberOrder.setEndDate(rs.getString(8));
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

	// 로그인 회원이 가진 주문번호에 해당하는지 검사
	public boolean memberOrderCheck(int mnum, int onum) {
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check = 0;
		try {
			pstmt = conn.prepareStatement("select count(*) from zooway.ORDER where onum=? and mnum=?");
			pstmt.setInt(1, onum);
			pstmt.setInt(2, mnum);
			rs = pstmt.executeQuery();
			while (rs.next())
				check = rs.getInt(1);
			return check > 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} // try 끝
		return false;
	}
	
	//방문일 끌고오는 메소드
	public OrderDTO getVDATE(int oNum) {
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		OrderDTO dto= new OrderDTO();
		try {
			Connection conn=DBAction.getInstance().getConnection();
			pstmt=conn.prepareStatement("select vdate,poldate from zooway.ORDER where onum=?");
			pstmt.setInt(1, oNum);
			rs=pstmt.executeQuery();
				while(rs.next()) {
					dto.setVdate(rs.getString(1));
					dto.setPoldate(rs.getInt(2));
					}
				return dto;
		}catch (Exception e){e.printStackTrace();}
		finally {
			try {if(pstmt!=null) pstmt.close();
				if(rs!=null) rs.close();
				
			} 
			catch (Exception e2) {e2.printStackTrace();}
		}//try 끝
		//값을 못가지고 왔다면 null 리턴
		return null;
	}
	
	
	
	//해지일 끌고오는 메소드
	public String GetEndDate(int oNum) {
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		String enddate=null;
		try {
			Connection conn=DBAction.getInstance().getConnection();
			pstmt=conn.prepareStatement("select endDate from zooway.ORDER where onum=?");
			pstmt.setInt(1, oNum);
			rs=pstmt.executeQuery();
				while(rs.next()) {
					enddate=rs.getString(1);
					}
				return enddate;
		}catch (Exception e){e.printStackTrace();}
		finally {
			try {if(pstmt!=null) pstmt.close();
				if(rs!=null) rs.close();
			} 
			catch (Exception e2) {e2.printStackTrace();}
		}//try 끝
		//값을 못가지고 왔다면 null 리턴
		return null;
	}
	
	//as신청하는 메소드
	public int ASService(int onum,ASDTO as) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Date vdate = Date.valueOf(as.getVdate());
		try {
			String SQL = "insert into `AS`(onum,vdate) values(?,?)";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, onum);
			pstmt.setDate(2, vdate);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
		
		return 0;

	}

	public OrderDTO selectResult(int onum) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderDTO order = null;
		try {
			String SQL = "select o.*,p.pname from `ORDER` o inner join PRODUCT p on o.pnum = p.pnum where onum = ?";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, onum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String pname = rs.getString("pname");
				String vdate = String.valueOf(rs.getDate("vdate"));
				int poldate = rs.getInt("poldate");
				int ostatus = rs.getInt("ostatus");
				order = new OrderDTO(onum, pname, vdate, poldate, ostatus);
			}

		} catch (Exception e) {
			System.out.println("selectResult 오류");
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
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
			while (rs.next()) {
				count = rs.getInt("pcount");
			}
		} catch (Exception e) {
			System.out.println("selectProcount 오류");
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		}
		return count;

	}

	public void MinusCountAndStatus(int num, int count) throws SQLException {
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

		} catch (Exception e) {
			System.out.println("MinusCountAndStatus 오류");
			throw e;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}

	}

	public void MinusCount(int num, int count) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String SQL = "update PRODUCT set pcount=? where pnum = ?";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, count);
			pstmt.setInt(2, num);
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("MinusCount 오류");
			throw e;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}
	public int selectOrderNum(int mnum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int Onum = 0;
			try {
				String SQL = "select onum from `ORDER` where mnum =?";
				conn = DBAction.getInstance().getConnection();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, mnum);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Onum = rs.getInt("onum");
				}
			}catch(Exception e) {
				System.out.println("selectOrderNum오류");
				throw e;
			}finally {
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
			}

		return Onum;
	}
	
	 // 약정해지신청 -> ostatus를 4반납신청으로 변경
	   public void contractTerminate(int onum) throws Exception {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      try {
	         String SQL = "update `ORDER` set ostatus=4 where onum = ?";
	         conn = DBAction.getInstance().getConnection();
	         pstmt = conn.prepareStatement(SQL);
	         pstmt.setInt(1, onum);
	         pstmt.executeUpdate();

	      } catch (Exception e) {
	         System.out.println("반납신청 오류");
	         throw e;
	      } finally {
	         if (pstmt != null) {
	            pstmt.close();
	         }
	      }

	   }
	   
	   
	   // 렌탈 약정 연장
	   public void contractExtend(int onum, int[] arr, String realEndDate) throws Exception {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      int polprice = arr[1];
	      try {
	         String SQL = "UPDATE `ORDER`\r\n"
	               + " SET polprice=polprice+?,\r\n"
	               + " endDate=?\r\n"
	               + " WHERE onum=?;";
	         conn = DBAction.getInstance().getConnection();
	         pstmt = conn.prepareStatement(SQL);
	         pstmt.setInt(1, polprice);
	         pstmt.setString(2, realEndDate);
	         pstmt.setInt(3, onum);
	         pstmt.executeUpdate();

	      } catch (Exception e) {
	         System.out.println("약정연장 실패");
	         throw e;
	      } finally {
	         if (pstmt != null) {
	            pstmt.close();
	         }
	      }
	   }
		public OrderDTO selectZVA(int onum) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			OrderDTO ZVA = null;
			try {
				String SQL = "select m.ZOOCAREPLUS,m.maddress1,o.* from `ORDER` o inner join MEMBER m on o.mnum=m.mnum where onum = ?";
				conn = DBAction.getInstance().getConnection();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, onum);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int zcp = rs.getInt("ZOOCAREPLUS");
					int maddress1 = rs.getInt("maddress1");
					int mnum =rs.getInt("mnum");
					String vdate = String.valueOf(rs.getDate("vdate"));
					int poldate = rs.getInt("poldate");
					int pnum = rs.getInt("pnum");
					int polprice =rs.getInt("polprice");
					int ostatus = rs.getInt("ostatus");
					String endDate = String.valueOf(rs.getDate("endDate"));
					
					
					
					ZVA = new OrderDTO(zcp,maddress1,onum,mnum,vdate,poldate,pnum,polprice,ostatus,endDate);
				}
				
				
				
			}catch(Exception e) {
				System.out.println("selectZVA 오류");
				throw e;
			}finally {
				if(rs!=null){rs.close();}
				if(pstmt!=null) {pstmt.close();}
			}
			
			
			
			return ZVA;
		}

		public ArrayList<EmployeeDTO> selectEmployee(OrderDTO ZVA)throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
			try {
				conn = DBAction.getInstance().getConnection();
				String SQL = null;
				
				if(ZVA.getZcp()==2) {//주케플이 있는 경우
					System.out.println(Date.valueOf(ZVA.getVdate()));
					SQL = "select distinct e.*,s.Address from EMPLOYEE e inner join VISIT v inner join SEOULADDR s inner join (select count(*) from VISIT WHERE vdate=? having count(*)<3) as b on  e.eplace = s.AddrNum where eplace = ? and estatus = 3"; 
					pstmt = conn.prepareStatement(SQL);
					pstmt.setDate(1,Date.valueOf(ZVA.getVdate()));
					pstmt.setInt(2, ZVA.getMaddress());
				}else {
					SQL = "select distinct e.*,s.Address from EMPLOYEE e inner join VISIT v inner join SEOULADDR s inner join (select count(*) from VISIT WHERE vdate=? having count(*)<3) as b on  e.eplace = s.AddrNum where estatus = 3";
					pstmt = conn.prepareStatement(SQL);
					pstmt.setDate(1,Date.valueOf(ZVA.getVdate()));
				}
				rs = pstmt.executeQuery();
				while(rs.next()) {
					int emnum = rs.getInt("emnum");
					String eid = rs.getString("eid");
					String epw = rs.getString("epw");
					String ename = rs.getString("ename");
					String ephone = rs.getString("ephone");
					String eplace = rs.getString("Address");
					int estatus =rs.getInt("estatus");
					list.add(new EmployeeDTO(emnum,eid,epw,ename,ephone,eplace,estatus));
				}
			
			
			
			}catch(Exception e) {
				System.out.println("selectEmployee오류");
				throw e;
			}finally {
				if(rs!=null){rs.close();}
				if(pstmt!=null) {pstmt.close();}
			}
			
			return list;
		}
	
}
