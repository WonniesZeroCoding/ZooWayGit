package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;


import dao.ProductDAO;

// 제품 관련 사항 -> 관리자 부분 
public class ProductManagementPage {
	
	public void productMangement() throws Exception {
		
		while(true) {
		System.out.println(" -----------------------------------------------");
		System.out.println("| 1. 제품목록 2. 제품 등록 3. 제품 재고 추가 4. 제품 삭제 0. 뒤로 |");
		System.out.println(" -----------------------------------------------");
		System.out.print("숫자 입력 >>");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int menuNum = Integer.parseInt(br.readLine());
		if(menuNum ==1) {
			new ProductDAO().AdminSelectProduct();
			//리스트 출력 메소드 가져오기
		} else if(menuNum==2) {
			new ProductDAO().insertProduct();
		} else if(menuNum==3) {
			new ProductDAO().plusProductStock();
		} else if(menuNum==4) {
			new ProductDAO().updateProduct();
		} else if(menuNum==0) {
			//메인 메뉴로 보내주기
		}else {
			System.out.println("번호를 잘못 입력하셨습니다. 다시 입력해 주세요");
		}
		
		
		
		}
	}
}
