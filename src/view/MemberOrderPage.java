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

	public void memberOrderPage(MemberDTO member) throws Exception {

		System.out.println(member.getMname() + "님의 렌탈현황 페이지 입니다.");

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
				if (memberOrders.size() == 0) {
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


public void memberOrderPage2(int onum,MemberDTO member) throws Exception {
	System.out.println("✿​━━∞━━∞━━∞━━∞━━∞━━∞✿   MENU ✿​∞━━∞━━∞━━∞━━∞━━∞━━✿");
	System.out.println("1.렌탈 연장신청  | 2.렌탈 해지신청  | 3.AS신청 | 4.뒤로가기");
	System.out.println("-----------------------------------------------");
	System.out.print("메뉴를 선택해주세요");
	int num = Integer.parseInt(br.readLine()); // 선택 번호
	switch (num) {
	case 1:// 렌탈 연장신청
		contractExtend(onum);
		break;
	case 2:// 렌탈 해지신청
		contractTerminate(onum, member.getMnum());
		break;
	case 3:
		//AS신청
		orderDao.ASService(onum, member);
		break;
	case 4:// 뒤로가기
		break;
	default:
		System.out.println("|￣￣￣￣￣￣￣|\r\n" + "|잘못된번호에요|\r\n" + "|＿＿＿＿＿＿＿|\r\n" + "(\\_/)||\r\n"
				+ "(•ㅅ•)||\r\n" + "/....づ\r\n" + "");


	}

}
	// 렌탈 해지 신청
	public void contractTerminate(int onum,int mnum) throws Exception {
		System.out.println("정말 해지하시겠습니까? 해지하시려면 '해지', 뒤로가시려면 0을 입력해주세요.");
		String choice = br.readLine();
		switch (choice) {
		case "해지":
			System.out.println("해지하기");
			orderDao.contractTerminate(onum,mnum);
			break;
		case "0":
			System.out.println("취소");
			break;
		default:
			System.out.println("잘못된 입력입니다.");
			break;

		}

	}
	
	//렌탈 약정 연장
	private void contractExtend(int onum) throws Exception {
		System.out.println("약정 기간을 연장하시겠습니까? 연장하시려면 '연장', 뒤로가시려면 0을 입력해주세요.");
		String choice = br.readLine();
		switch (choice) {
		case "연장":
			System.out.println("연장하기");
			orderDao.contractExtend(onum);
			break;
		case "0":
			System.out.println("취소");
			break;
		default:
			System.out.println("잘못된 입력입니다.");
			break;

		}
		
	}
}
