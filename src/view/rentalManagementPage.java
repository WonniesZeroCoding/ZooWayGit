package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import dao.rentalDAO;

public class rentalManagementPage {
	public rentalManagementPage() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean  check = true;
		while(check) {
			System.out.println("");
			System.out.println("✿​━━∞━━∞━━∞━━∞━━✿   MENU   ✿​━━∞━━∞━━∞━━∞━━✿");
			System.out.println("1.렌탈신청목록  |2.반납임박목록 |3.뒤로가기 ");
			System.out.println("-------------------------------------");
			System.out.print("메뉴를 선택해주세요 >>");
			int menuNum = Integer.parseInt(br.readLine());
			if(menuNum==1) {
				///렌탈신청목록 들어가기
				new rentalDAO().rentalSelectAdmin();
				//ZOOCAREPLUS 있는 사람들 목록(먼저 배치)/ ZOOCAREPLUST 없는 사람(그다음 배치)
				//신청목록(ordernum이 작은 것이 맨 위로 가게(먼저 신청한 순서대로)/visit 테이블에 onum이 안들어있는것들 
				
				
				
				
				break;
			}else if(menuNum==2) {
				new rentalDAO().rentalLimitAdmin();
				break;
			}else if(menuNum==3) {
				check = false;
				break;
			}
			
			
			
		}
	
	}
	
}	
