package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import dao.ASDAO;


public class asManagementpage {
	public asManagementpage() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean  check = true;
		while(check) {
			System.out.println("");
			System.out.println("✿​━━∞━━∞━━∞━━∞━━✿   MENU   ✿​━━∞━━∞━━∞━━∞━━✿");
			System.out.println("1.AS신청목록  |2.뒤로가기 ");
			System.out.println("-------------------------------------");
			System.out.print("메뉴를 선택해주세요 >>");
			int menuNum = Integer.parseInt(br.readLine());
			if(menuNum==1) {
				//AS신청목록 들어가기
				new ASDAO().selectAllAS();
				break;
			}else if(menuNum==2) {
				check = false;
				break;
			}
			
			
			
		}
	
		
		
		
		
	}
	
	
}
