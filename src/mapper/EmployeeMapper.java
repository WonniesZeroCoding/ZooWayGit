package mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.EmployeeDTO;
public class EmployeeMapper {

	// 탈퇴한 사원을 제외한 모든 사원 조회
	public List<EmployeeDTO> employeeAllList() {
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();

		try {
			pstmt = conn.prepareStatement("SELECT e.emnum, e.eid, e.epw, e.ename, e.ephone, s.Address, e.estatus  \r\n"
					+ "FROM EMPLOYEE e, SEOULADDR s\r\n"
					+ "where e.eplace = s.AddrNum\r\n"
					+ "and estatus != 1");
			rs = pstmt.executeQuery();

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

		return employees;
	}

	/* 사원 수정 */
	public void employeeUpdate(int emnum, String address) {
		System.out.println("입력한 사원번호" + emnum);
		System.out.println("입력한 oo구" + address);
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;

		try {
			String sql = "update EMPLOYEE set eplace=(SELECT ADDRNUM FROM SEOULADDR s WHERE s.ADDRESS=?) WHERE EMNUM = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, address);
			pstmt.setInt(2, emnum);
			int result = pstmt.executeUpdate();
			String msg = result > -1 ? "사원담당구역 변경이 완료되었습니다." : "담당구역 변경 실패";
			System.out.println(msg);
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

	}

	/* 사원 탈퇴처리 */
	public void employeeDelete(int emnum) {
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		System.out.println("입력한 사원번호" + emnum);
		try {
			pstmt = conn.prepareStatement("UPDATE EMPLOYEE SET ESTATUS=1 WHERE EMNUM=?");
			pstmt.setInt(1, emnum);
			int result = pstmt.executeUpdate();
			String msg = result > -1 ? "사원탈퇴처리가 완료되었습니다." : "사원탈퇴처리 실패";
			System.out.println(msg);
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

	}

	/* 사원 아이디 중복 검사 */
	public boolean eIdDupCheck(String eid) {
		Connection conn= DBAction.getInstance().getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		int check=0;
		try {
			pstmt=conn.prepareStatement("select count(*) from EMPLOYEE where eid=?");
			pstmt.setString(1, eid);
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

	public boolean employeeInsert(EmployeeDTO newEmployee) {
		Connection conn= DBAction.getInstance().getConnection();
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement("insert into zooway.EMPLOYEE(eid,epw,ename,ephone,eplace,estatus) values(?,?,?,?,(select addrnum from SEOULADDR where Address=?),3)");
			pstmt.setString(1, newEmployee.getEid());
			pstmt.setString(2, newEmployee.getEpw());
			pstmt.setString(3, newEmployee.getEname());
			pstmt.setString(4, newEmployee.getEphone());
			pstmt.setString(5, newEmployee.getEplace());
			
			return pstmt.executeUpdate()>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)
				try {
					pstmt.close();
				} catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}
}
