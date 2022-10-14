package mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemberDTO;


public class MemberMapper {
	
	//일반 회원 로그인
	public MemberDTO Memberlogin(String id,String password) {
		Connection conn= DBAction.getInstance().getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		MemberDTO member= new MemberDTO();
		
		try {
			pstmt=conn.prepareStatement("select mid,mname,mstatus from MEMBER where mid=? and mpw=?");
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.getString(1)!=null) {
				while(rs.next()) {
					member.setMid(rs.getString(1));
					member.setMname(rs.getString(2));
					member.setMstatus(rs.getInt(3));
				}				
			}else {
				System.out.println("아이디나 비밀번호를 다시 한번 확인해주세요.");
				return null;
			}
			//가져오는 값이 없으면 바로 null로 리턴
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {e2.printStackTrace();}
		}//try 끝
		
		//탈퇴한 회원일 경우
		if(member.getMstatus()==2) {
			System.out.println("탈퇴한 회원입니다.");
			return null;
		}
		//값을 잘 가지고 왔다면 로그인한 member객체 리턴
		return member;
	}
}
