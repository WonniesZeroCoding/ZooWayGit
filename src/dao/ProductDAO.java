package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.ProductDTO;
import mapper.ProductMapper;

public class ProductDAO {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public void selectProduct() throws SQLException {
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		list = new ProductMapper().SelectAllProduct();
		System.out.println(" -----------------------------------------------");
		for(int i = 0 ; i<list.size(); i++) {
		System.out.println("|상품번호\t  상품이름\t\t상품재고|");
		System.out.println(list.get(i).getPnum()+"\t"+list.get(i).getPname()+"\t"+list.get(i).getPcount());
		}
		System.out.println(" -----------------------------------------------");
	}
	
}
