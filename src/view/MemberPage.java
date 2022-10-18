package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dao.MemberDAOImpl;
import dto.MemberDTO;

public class MemberPage {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void memberPageView(MemberDTO member) throws NumberFormatException, IOException {
		
		System.out.println(member.getMname()+"님 반갑습니다!(´･ω･`)");
		System.out.println("");
		MemberDAOImpl dao=new MemberDAOImpl();
		
		boolean run = true;
		do {System.out.println("");
			System.out.println("✿​━━∞━━∞━━∞━━∞━━∞━━∞━━✿​━━∞━━∞━━∞━━∞━━∞━━∞━━✿   MENU   ✿━━∞━━∞━━∞━━∞━━∞━━∞━━✿​━━∞━━∞━━∞━━∞━━✿");
			System.out.println("1.렌탈신청 | 2.렌탈현황 | 3.주케어플러스 | 4.1:1채팅문의 | 5.회원정보 수정 | 6.회원탈퇴 | 7.로그아웃");
			System.out.println("------------------------------------------------------------------------------");
			System.out.print("메뉴를 선택해주세요");
			int num=Integer.parseInt(br.readLine()); // 선택 번호
			switch (num) {
			case 1://렌탈신청(제품목록으로 이동)
				
				break;
			case 2:// 렌탈현황
				
				break;
			case 3:// 주케어플러스
				
				break;
			case 4:// 1:1채팅 문의
				
				break;
			case 5:// 회원정보 수정
				
				break;
			case 6:// 회원탈퇴
				
				break;
			case 7: //로그아웃->첫 화면으로
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
