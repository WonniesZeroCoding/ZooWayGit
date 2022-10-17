package mapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.ProductDTO;

public class ProductMapper {
	public ArrayList<ProductDTO> SelectAllProduct() throws SQLException{
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
			if(conn!=null) {conn.close();}
			
			
		}
		return list;
		
		
		
	}
}
