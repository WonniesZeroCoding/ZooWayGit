package mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dto.EmployeeDTO;
import dto.MemberDTO;


public class EmployeeMapper {
	
	//일반 회원 로그인
	public List<EmployeeDTO> employeeAllList() {
		Connection conn= DBAction.getInstance().getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		List<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();
		
		
		try {
			pstmt=conn.prepareStatement("SELECT * FROM EMPLOYEE");
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				EmployeeDTO employee = new EmployeeDTO();
				employee.setEmnum(rs.getInt(1));
				employee.setEid(rs.getString(2));
				employee.setEpw(rs.getString(3));
				employee.setEname(rs.getString(4));
				employee.setEphone(rs.getString(5));
				employee.setEplace(rs.getString(6));
				employee.setEstatus(rs.getInt(7));
				employees.add(employee);
			}			
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) rs.close();
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {e2.printStackTrace();}
		}//try 끝
		
		return employees;
	}
	
	/* 사원 수정 */
	public void employeeUpdate(int emnum, int eplace) {
		Connection conn= DBAction.getInstance().getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		
		try {
			pstmt=conn.prepareStatement("SELECT * FROM EMPLOYEE");
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				EmployeeDTO employee = new EmployeeDTO();
				employee.setEmnum(rs.getInt(1));
				employee.setEid(rs.getString(2));
				employee.setEpw(rs.getString(3));
				employee.setEname(rs.getString(4));
				employee.setEphone(rs.getString(5));
				employee.setEplace(rs.getString(6));
				employee.setEstatus(rs.getInt(7));
			}			
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) rs.close();
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {e2.printStackTrace();}
		}//try 끝
		
	}

	/* 사원 탈퇴처리 */
	public void employeeDelete(int emnum) {
		Connection conn= DBAction.getInstance().getConnection();
		PreparedStatement pstmt=null;
		System.out.println("입력한 사원번호"+emnum);
		try {
			pstmt=conn.prepareStatement("UPDATE EMPLOYEE SET ESTATUS=1 WHERE EMNUM=?");
			pstmt.setInt(1, emnum);
			int result = pstmt.executeUpdate();
			String msg = result > -1 ? "사원탈퇴처리가 완료되었습니다." : "사원탈퇴처리 실패";
			System.out.println(msg);	
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {e2.printStackTrace();}
		}//try 끝
		
	}
}
