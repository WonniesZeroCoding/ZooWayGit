package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.ProductDTO;
import mapper.ProductMapper;

public class ProductDAO {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	ProductMapper productMapper = new ProductMapper();
	
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
	
	public void insertProduct() throws Exception {
		ProductDTO insertProduct = new ProductDTO();
		
		try {
			while(true) {
				System.out.println("등록할 제품명을 입력하세요");
				String pname = br.readLine();
				
				if(ProductDupCheck(pname)) { System.out.println("등록할 제품명이 이미 존재합니다"); }
				else {
					insertProduct.setPname(pname);
					break;
					}
				}
				System.out.println("등록할 제품의 재고 수량을 입력해주세요");
				int pcount = Integer.parseInt(br.readLine());
				insertProduct.setPcount(pcount);
				
				if(productMapper.insertProduct(insertProduct)) {
					System.out.println("제품 등록 완료");
				
				} else {
					System.out.println("제품 등록 실패");
					
				}
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	///////
	public boolean ProductDupCheck(String pname) {
		boolean check = productMapper.ProductDupCheck(pname);
		return check;
	}
}
