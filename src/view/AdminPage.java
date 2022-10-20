package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dto.EmployeeDTO;


public class AdminPage {
	
	public void adminPageView(EmployeeDTO employee) throws Exception {
		System.out.println("관리자님 반갑습니다.");
		EmployeeManagementPage employeeManagementPage = new EmployeeManagementPage();
		ProductManagementPage productManagementPage = new ProductManagementPage();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
//		@SuppressWarnings("null")
//		public void memberPageView(MemberDTO member) throws NumberFormatException, IOException {
			
			
			
			//System.out.println(member.getMname()+"님 반갑습니다!(´･ω･`)");
			System.out.println("");
			
			
			boolean run = true;
			do {System.out.println("");
				System.out.println("✿​━━∞━━∞━━∞━━∞━━∞━━∞━━✿​━━∞━━∞━━∞━━∞━━∞━━∞━━✿   MENU   ✿━━∞━━∞━━∞━━∞━━∞━━∞━━✿​━━∞━━∞━━∞━━∞━━✿");
				System.out.println("1.상품관리 | 2.회원관리 | 3.사원관리 | 4.렌탈관리 | 5.A/S관리 | 6.1:1채팅문의 | 7.로그아웃");
				System.out.println("------------------------------------------------------------------------------");
				System.out.print("메뉴를 선택해주세요");
				int num=Integer.parseInt(br.readLine()); // 선택 번호
				switch (num) {
				case 1://상품관리
					productManagementPage.productMangement();
					break;
				case 2:// 회원관리				
					new memberManagementPage();

					break;
				case 3:// 사원관리
					employeeManagementPage.employeeManagement();
					break;
				case 4:// 렌탈관리
					
					break;
				case 5:// A/S관리
					
					break;
				case 6:// 1:1채팅문의
					
		
					break;
				case 7: //로그아웃->첫 화면으로
					employee=null;
					run = false;
					break;
				default:	
					System.out.println("|￣￣￣￣￣￣￣|\r\n"
									 + "|잘못된번호에요|\r\n"
									 + "|＿＿＿＿＿＿＿|\r\n"
									 + "(\\_/)||\r\n"
									 + "(•ㅅ•)||\r\n"
									 + "/....づ\r\n"
									 + "");
				}
				System.out.println();
	
			} while (run);
		}
	}
//}
