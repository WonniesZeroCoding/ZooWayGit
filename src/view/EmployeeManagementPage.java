package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dao.EmployeeDAOImpl;

/*사원관리 페이지1*/
public class EmployeeManagementPage {
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	EmployeeDAOImpl employeeDao = new EmployeeDAOImpl();
	
	public void employeeManagement() throws NumberFormatException, IOException {
		
		System.out.println("사원관리 페이지 입니다.");

		boolean run = true;
		do {
			System.out.println("");
			System.out.println("✿​━━∞━━∞━━∞━━∞━━✿   MENU   ✿​━━∞━━∞━━∞━━∞━━✿");
			System.out.println("1.전체사원 조회  | 2.사원등록  | 3.뒤로가기");
			System.out.println("-------------------------------------");
			System.out.print("메뉴를 선택해주세요");
			int num = Integer.parseInt(br.readLine()); // 선택 번호
			switch (num) {
			case 1:// 전체사원 조회
				employeeDao.employeeAllList();
				break;
			case 2:// 사원등록
				employeeDao.employeeInsert();
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
	
	
	/*사원 담당구역 수정/ 사원 삭제 페이지*/
	public void employeeManagement2(int emnum) throws NumberFormatException, IOException {
		
			
			System.out.println("✿​━━∞━━∞━━∞━━∞━━✿   MENU   ✿​━━∞━━∞━━∞━━∞━━✿");
			System.out.println("1.사원정보 수정  | 2.사원탈퇴  | 3.취소");
			System.out.println("-------------------------------------");
			System.out.print("메뉴를 선택해주세요");
			int num = Integer.parseInt(br.readLine()); // 선택 번호
			switch (num) {
			case 1:// 사원정보수정
				employeeDao.employeeUpdate(emnum);
				break;
			case 2:// 회원탈퇴
				employeeDao.employeeDelete(emnum);
				break;
			case 3:// 취소
				break;
			default:
				System.out.println("|￣￣￣￣￣￣￣|\r\n" + "|잘못된번호에요|\r\n" + "|＿＿＿＿＿＿＿|\r\n" + "(\\_/)||\r\n"
						+ "(•ㅅ•)||\r\n" + "/....づ\r\n" + "");
			}
			System.out.println();

		
	}
}
