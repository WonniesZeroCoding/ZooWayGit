package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import dao.MemberDAOImpl;
import mapper.MemberMapper;
  
public class memberManagementPage {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public memberManagementPage() throws Exception{
		System.out.println("사원관리 페이지 입니다.");

		boolean run = true;
		do {
			System.out.println("");
			System.out.println("✿​━━∞━━∞━━∞━━∞━━✿   MENU   ✿​━━∞━━∞━━∞━━∞━━✿");
			System.out.println("1.전체회원 조회  | 2.회원탈퇴 | 3.뒤로가기");
			System.out.println("-------------------------------------");
			System.out.print("메뉴를 선택해주세요");
			int num = Integer.parseInt(br.readLine()); // 선택 번호
			switch (num) {
			case 1:// 전체회원 조회
				new MemberDAOImpl().selectMemberAdmin();
				break;
			case 2:// 회원탈퇴
				new MemberDAOImpl().deletMemberAdmin();
				break;
			case 3:// 뒤로가기
				run = false;
				break;
			default:
				System.out.println("|￣￣￣￣￣￣￣|\r\n" + "|잘못된번호에요|\r\n" + "|＿＿＿＿＿＿＿|\r\n" + "(\\_/)||\r\n" + "(•ㅅ•)||\r\n"
						+ "/....づ\r\n" + "");
			}
			System.out.println();

		} while (run);

		
		
		
	}
	
	
}
