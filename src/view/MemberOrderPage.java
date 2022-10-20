package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import dao.EmployeeDAOImpl;
import dao.OrderDAOImpl;
import dto.MemberDTO;
import dto.OrderDTO;

public class MemberOrderPage {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	OrderDAOImpl orderDao = new OrderDAOImpl();
public void memberOrderPage(MemberDTO member) throws NumberFormatException, IOException {
		
		System.out.println(member.getMname()+"님의 렌탈현황 페이지 입니다.");

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
				List<OrderDTO> memberOrders = orderDao.memberOrderList(member);
				if (memberOrders.size()==0) {
					run = false;
				}
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

public void memberOrderPage2(int onum) throws NumberFormatException, IOException {
	System.out.println("✿​━━∞━━∞━━∞━━∞━━✿   MENU   ✿​━━∞━━∞━━∞━━∞━━✿");
	System.out.println("1.렌탈 연장신청  | 2.렌탈 해지신청  | 3.뒤로가기");
	System.out.println("-------------------------------------");
	System.out.print("메뉴를 선택해주세요");
	int num = Integer.parseInt(br.readLine()); // 선택 번호
	switch (num) {
	case 1:// 렌탈 연장신청
		//orderDao.contractExtension(onum);
		break;
	case 2:// 렌탈 해지신청
		//orderDao.contractTermination(onum);
		break;
	case 3:// 뒤로가기
		break;
	default:
		System.out.println("|￣￣￣￣￣￣￣|\r\n" + "|잘못된번호에요|\r\n" + "|＿＿＿＿＿＿＿|\r\n" + "(\\_/)||\r\n"
				+ "(•ㅅ•)||\r\n" + "/....づ\r\n" + "");
	}
	System.out.println();
	
}
}
