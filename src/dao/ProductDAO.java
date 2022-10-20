package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.ProductDTO;
import functions.proNumCheck;
import mapper.ProductMapper;

public class ProductDAO {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	ProductMapper productMapper = new ProductMapper();
	
	public void selectProduct() throws SQLException {
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		list = new ProductMapper().selectAllProduct();
		System.out.println(" -----------------------------------------------");
		for(int i = 0 ; i<list.size(); i++) {
		System.out.println("|상품번호\t  상품이름\t\t상품재고");
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
	
	
	public void updateProduct() {
		
		try {
			System.out.print("변경(삭제)할 상품 번호를 입력해주세요");
			int pnum = Integer.parseInt(br.readLine());
			boolean pnumCheck = new proNumCheck().productCheck(pnum);
			
			while(!pnumCheck) {
				System.out.println("해당 번호의 상품이 존재하지 않습니다.");
				System.out.println("번호를 다시 입력해주세요 >");
				pnum = Integer.parseInt(br.readLine());
				pnumCheck = new proNumCheck().productCheck(pnum);
			}	
			productMapper.updateProduct(pnum);
			System.out.println("변경이 완료 되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void AdminSelectProduct() throws SQLException {
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		list = new ProductMapper().AdminSelectAllProduct();
		System.out.println(" -----------------------------------------------");
		for(int i = 0 ; i<list.size(); i++) {
		System.out.println("|상품번호\t  상품이름\t\t상품재고\t\t상품현황");
		if(list.get(i).getPstatus()==1) {
			System.out.println(list.get(i).getPnum()+"\t"+list.get(i).getPname()+"\t"+list.get(i).getPcount()+"\t\t판매중");
		} else {
			System.out.println(list.get(i).getPnum()+"\t"+list.get(i).getPname()+"\t"+list.get(i).getPcount()+"\t\t판매중지");			
			}
		}
		System.out.println(" -----------------------------------------------");
	}
	
	
	public void plusProductStock() {
		try {

			System.out.println("재고를 추가할 상품 번호를 입력해주세요");
			int pnum = Integer.parseInt(br.readLine());
			int pcount = productMapper.selectPcount(pnum);
			
			System.out.println("추가할 재고 수를 입력해주세요");
			int count = Integer.parseInt(br.readLine());
			
			int plusStock = pcount+count;
			productMapper.plusProductStock(pnum, plusStock);
			System.out.println("재고 추가 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
