package mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.MemberDTO;


public class MemberMapper {
	
	//일반 회원 로그인
	public MemberDTO Memberlogin(String id,String password) {
		
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		MemberDTO member= new MemberDTO();
		try {
			Connection conn=DBAction.getInstance().getConnection();
			pstmt=conn.prepareStatement("select mid,mname,mphone,(select Address from SEOULADDR where AddrNum=maddress1) 'maddress1',maddress2,mstatus,ZOOCAREPLUS,mnum from MEMBER where mid=? and mpw=?");
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
				while(rs.next()) {
					member.setMid(rs.getString(1));
					member.setMname(rs.getString(2));
					member.setMphone(rs.getString(3));
					member.setMaddress1(rs.getString(4));
					member.setMaddress2(rs.getString(5));
					member.setMstatus(rs.getInt(6));
					member.setZOOCAREPLUS(rs.getInt(7));
					member.setMnum(rs.getInt(8));
					
				}	
				return member;
		}catch (Exception e){e.printStackTrace();}
		finally {
			try {if(pstmt!=null) pstmt.close();
				if(rs!=null) rs.close();
				
			} 
			catch (Exception e2) {e2.printStackTrace();}
		}//try 끝
		//탈퇴한 회원일 경우
		if(member.getMstatus()==1) 
		{System.out.println("탈퇴한 회원입니다.");
			return null;}
		//값을 잘 가지고 왔다면 로그인한 member객체 리턴
		return null;
	}

	//일반 회원가입
	public boolean MembeSignIn(MemberDTO newMember) {
		Connection conn=DBAction.getInstance().getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement("insert into MEMBER(mid,mpw,mname,mphone,maddress1,maddress2) values(?,?,?,?,(select addrnum from SEOULADDR where Address=?),?)");
			pstmt.setString(1, newMember.getMid());
			pstmt.setString(2, newMember.getMpw());
			pstmt.setString(3, newMember.getMname());
			pstmt.setString(4, newMember.getMphone());
			pstmt.setString(5, newMember.getMaddress1());
			pstmt.setString(6, newMember.getMaddress2());
			
			return pstmt.executeUpdate()>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if(pstmt!=null) pstmt.close();
					
				} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}
	
	//중복검사
	public boolean IdDupCheck(String id) {
		Connection conn=DBAction.getInstance().getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		int check=0;
		try {
			pstmt=conn.prepareStatement("select count(*) from MEMBER where mid=?");
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()) check=rs.getInt(1);
			return check>0?true:false;
			}catch (Exception e){e.printStackTrace();}
			finally {
				try {if(pstmt!=null) pstmt.close();
				
				} 
				catch (Exception e2) {e2.printStackTrace();}
		}//try 끝
		return false;
	}
	
	//주소 유효성 검사
	public boolean checkAddr(String address) {
		Connection conn=DBAction.getInstance().getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		int check=0;
		try {
			pstmt=conn.prepareStatement("select count(*) from SEOULADDR where Address=?");
			pstmt.setString(1, address);
			rs=pstmt.executeQuery();
			while(rs.next()) check=rs.getInt(1);
			return check==1?true:false; //주소에 해당된다는 표시
		}catch (Exception e){e.printStackTrace();}
		finally {
			try {if(pstmt!=null) pstmt.close();
			} 
			catch (Exception e2) {e2.printStackTrace();}
		}//try 끝
		return false;
	}
	
	//즈.케어.플러스. 가입했는지 체크
	public int checkZCP(String mid) {
		Connection conn=DBAction.getInstance().getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		int check=0;
		try {
			pstmt=conn.prepareStatement("select count(*) from MEMBER where ZOOCAREPLUS=2 and mid=?");
			pstmt.setString(1, mid);
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
	
	//즈.케어.플러스. 가입하는 메소드
	public int SignInZCP(String mid) {
		Connection conn=DBAction.getInstance().getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement("update MEMBER set ZOOCAREPLUS=2 where mid=?");
			pstmt.setString(1, mid);
			return pstmt.executeUpdate(); //성공하면 1 return
		} catch (Exception e) {e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return 0;
	}
	
	//즈.케어.플러스. 탈퇴하는 메소드
	public int DeleteZCP(String mid) {
		Connection conn=DBAction.getInstance().getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement("update MEMBER set ZOOCAREPLUS=1 where mid=?");
			pstmt.setString(1, mid);
			return pstmt.executeUpdate(); //성공하면 1 return
		} catch (Exception e) {e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return 0;
	}
	
	//회원정보 수정해주기 (컬럼값, 변경할 값 받아와서 바꿔줄거임)
		public int UpdateMember(String column,String data,String mid,String mpw) {
			Connection conn=DBAction.getInstance().getConnection();
			String str="?";
			if(column.equals("maddress1")) {
				str="(select addrnum from SEOULADDR where Address=?)";
			}
			PreparedStatement pstmt=null;
			try {
				pstmt=conn.prepareStatement("update MEMBER set "+column+"="+str+" where mid=? and mpw=?");
	
				pstmt.setString(1, data);
				pstmt.setString(2, mid);
				pstmt.setString(3, mpw);
				System.out.println("회원정보 수정이 완료되었습니다");
				return pstmt.executeUpdate(); //성공하면 1 return
			} catch (Exception e) {e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return 0;
		}
		
		//회원 탈퇴시키는 메소드
		public int deleteMember(String mid,String mpw) {
			Connection conn=DBAction.getInstance().getConnection();
			PreparedStatement pstmt=null;
			try {
				pstmt=conn.prepareStatement("delete from MEMBER where mid=? and mpw=?");
				pstmt.setString(1, mid);
				pstmt.setString(2, mpw);
				return pstmt.executeUpdate(); //성공하면 1 return
			} catch (Exception e) {e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return 0;
		}
		//hamsik~ 모든 회원 전부 가져오기 + address한글로
		public ArrayList<MemberDTO> selectAllMemberAdmin() throws SQLException {
			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;
			ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
			try {
				String SQL = "select m.*,s.address from MEMBER m inner join SEOULADDR s on m.maddress1 = s.addrnum;";
				conn = DBAction.getInstance().getConnection();
				st = conn.createStatement();
				rs = st.executeQuery(SQL);
				while(rs.next()) {
				int mNum = rs.getInt("mnum");
				String mId = rs.getString("mid");
				String mPw = rs.getString("mpw");
				String mName = rs.getString("mname");
				String mPhone = rs.getString("mphone");
				String address1 = rs.getString("address");
				String address2 = rs.getString("maddress2");
				int mstatus = rs.getInt("mstatus");
				int ZCP = rs.getInt("ZOOCAREPLUS");
					
				list.add(new MemberDTO(mNum,mId,mPw,mName,mPhone,address1,address2,mstatus,ZCP));
				}
			}catch(Exception e) {
				System.out.println("selectAllMemberAdmin 오류");
				throw e;
			}finally {
				if(rs!=null) {rs.close();}
				if(st!=null) {st.close();}
			}
			return list;
		}
		//hamsik = > 회원 한명 탈퇴 => status 바꿔주기
		public void deleteMemberAdmin(int num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				String SQL = "update MEMBER set mstatus = 0 where mnum = ?";
				conn = DBAction.getInstance().getConnection();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
			}catch(Exception e) {
				System.out.println("deleteMemberAdmin 오류");
				throw e;
			}finally {
				if(pstmt!=null) {pstmt.close();}
			}
			
		}
	

	
}