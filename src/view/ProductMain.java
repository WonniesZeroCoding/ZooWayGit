package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import dao.ProductDAO;
import dto.MemberDTO;

public class ProductMain {
	
	public ProductMain(MemberDTO member) throws Exception {
		System.out.println(" -----------------------------------------------");
		System.out.println("|           1. 제품목록 2. 뒤로                                   ");
		System.out.println(" -----------------------------------------------");
		while(true) {
		System.out.print("숫자 입력 >>");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int menuNum = Integer.parseInt(br.readLine());
		if(menuNum ==1) {
			new ProductDAO().selectProduct();
			new OrderMain(member);
			break;
			//리스트 출력 메소드 가져오기
		}else if(menuNum==2) {
			//메인 메뉴로 보내주기
		}else {
			System.out.println("번호를 잘못 입력하셨습니다. 다시 입력해 주세요");
		}
		
		
		
		}
		
		
	}
}
