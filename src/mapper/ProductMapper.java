package mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.ProductDTO;
  
public class ProductMapper {
	public ArrayList<ProductDTO> selectAllProduct() throws SQLException{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		try {
		
		String SQL = "SELECT * FROM PRODUCT WHERE PSTATUS=1"; //status가 1인 경우(판매중인 상품일 경우) 만 전부 select
		conn = DBAction.getInstance().getConnection();
		st = conn.createStatement();
		rs  = st.executeQuery(SQL);
		while(rs.next()) {
			int pnum = rs.getInt("pnum");
			String pname = rs.getString("pname");
			int pcount = rs.getInt("pcount");
			int pstatus = rs.getInt("pstatus");
			list.add(new ProductDTO(pnum,pname,pcount,pstatus));
		}
		}catch(Exception e) {
			System.out.println("SelectALLPRODUCT 오류");
			e.printStackTrace();
		}finally {
			if(rs!=null) {rs.close();}
			if(st!=null) {st.close();}
		}
		return list;	
	}
	
	// 제품의 pname이 Unique 값 이므로, DupCheck
	public boolean ProductDupCheck(String pname) {
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check=0;
		try {
			pstmt = conn.prepareStatement("select count(*) from PRODUCT where pname=?");
			pstmt.setString(1, pname);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				check = rs.getInt(1);
				return check>0?true:false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt!= null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean insertProduct(ProductDTO insertProduct) {
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into PRODUCT values(pnum,?,?,1)");
			pstmt.setString(1, insertProduct.getPname()); // 제품 명 입력
			pstmt.setInt(2, insertProduct.getPcount()); // 제품의 재고 수량
			
			return pstmt.executeUpdate()>0?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
	
	// 삭제를 업데이트 방식으로
	public void updateProduct(int pnum) {
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update PRODUCT set pstatus=0 where pnum=?");
			pstmt.setInt(1, pnum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public ArrayList<ProductDTO> AdminSelectAllProduct() throws SQLException{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		try {
		
		String SQL = "SELECT * FROM PRODUCT"; //status가 1인 경우(판매중인 상품일 경우) 만 전부 select
		conn = DBAction.getInstance().getConnection();
		st = conn.createStatement();
		rs  = st.executeQuery(SQL);
		while(rs.next()) {
			int pnum = rs.getInt("pnum");
			String pname = rs.getString("pname");
			int pcount = rs.getInt("pcount");
			int pstatus = rs.getInt("pstatus");
			list.add(new ProductDTO(pnum,pname,pcount,pstatus));
		}
		}catch(Exception e) {
			System.out.println("SelectALLPRODUCT 오류");
			e.printStackTrace();
		}finally {
			if(rs!=null) {rs.close();}
			if(st!=null) {st.close();}
		}
		return list;	
	}
	// 재고 개수를 받아오는 메소드
	public int selectPcount(int pnum) {
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = conn.prepareStatement("select pcount from PRODUCT where pnum=?");
			pstmt.setInt(1, pnum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt("pcount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return count;
	}
	
	// 재고 추가 
	public void plusProductStock(int pnum, int plusStock) {
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update PRODUCT set  pcount=? where pnum = ?");
			pstmt.setInt(1, plusStock);
			pstmt.setInt(2, pnum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
