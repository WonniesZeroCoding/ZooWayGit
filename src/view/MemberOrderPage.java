package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dao.EmployeeDAOImpl;
import dao.OrderDAOImpl;
import dto.MemberDTO;

public class MemberOrderPage {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	OrderDAOImpl rentalDao = new OrderDAOImpl();
public void memberOrderPage(MemberDTO member) throws NumberFormatException, IOException {
		
		System.out.println("나의렌탈 페이지 입니다.");

		boolean run = true;
		do {
			System.out.println("");
			System.out.println("✿​━━∞━━∞━━∞━━∞━━✿   MENU   ✿​━━∞━━∞━━∞━━∞━━✿");
			System.out.println("1.나의 렌탈현황 조회  | 2.뒤로가기");
			System.out.println("-------------------------------------");
			System.out.print("메뉴를 선택해주세요");
			int num = Integer.parseInt(br.readLine()); // 선택 번호
			switch (num) {
			case 1:// 나의 렌탈현황
				rentalDao.memberOrderList(member);
				break;
			case 2:// 뒤로가기
				run = false;
				break;
			default:
				System.out.println("|￣￣￣￣￣￣￣|\r\n" + "|잘못된번호에요|\r\n" + "|＿＿＿＿＿＿＿|\r\n" + "(\\_/)||\r\n" + "(•ㅅ•)||\r\n"
						+ "/....づ\r\n" + "");
			}
			System.out.println();

		} while (run);

	}
//약정
public void memberOrderPage2(int parseInt) {
	//내일 만들어야징~
	
}
}
