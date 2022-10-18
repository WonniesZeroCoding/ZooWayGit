package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import dto.EmployeeDTO;
import mapper.EmployeeMapper;

public class EmployeeDAOImpl implements EmployeeDAO {

	EmployeeMapper employeeMapper = new EmployeeMapper();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/* 1.사원 관리 */
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
				employeeAllList();
				break;
			case 2:// 사원등록

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

	/* 1-1.전체 사원 목록 조회 */
	public void employeeAllList() throws NumberFormatException, IOException {
		System.out.println("사원 조회 시작");
		List<EmployeeDTO> employees = employeeMapper.employeeAllList();

		if (employees.size() != 0) {

			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			System.out.println("사원번호\t\t사원아이디\t\t이름\t\t전화번호\t\t\t담당구역\t\t탈퇴여부\t");
			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			for (int i = 0; i < employees.size(); i++) {
				System.out.println(employees.get(i).getEmnum() + "\t\t" + employees.get(i).getEid() + "\t\t"
						+ employees.get(i).getEname() + "\t\t" + employees.get(i).getEphone() + "\t\t"
						+ employees.get(i).getEplace() + "\t\t" + employees.get(i).getEstatus() + "\t");
			}

			boolean run = true;
			do {
				System.out.println("원하시는 회원번호를 입력해주세요.");
				int emnum = Integer.parseInt(br.readLine());// 회원번호선택
				System.out.println("✿​━━∞━━∞━━∞━━∞━━✿   MENU   ✿​━━∞━━∞━━∞━━∞━━✿");
				System.out.println("1.사원정보 수정  | 2.사원탈퇴  | 3.취소");
				System.out.println("-------------------------------------");
				System.out.print("메뉴를 선택해주세요");
				int num = Integer.parseInt(br.readLine()); // 선택 번호
				switch (num) {
				case 1:// 사원정보수정
					System.out.println("변경하실 담당구역 번호를 입력해주세요");
					int eplace = Integer.parseInt(br.readLine());// 담당구역 번호
					employeeMapper.employeeUpdate(emnum,eplace);
					run = false;
					break;
				case 2:// 회원탈퇴
					employeeMapper.employeeDelete(emnum);
					run = false;
					break;
				case 3:// 취소
					run = false;
					break;
				default:
					System.out.println("|￣￣￣￣￣￣￣|\r\n" + "|잘못된번호에요|\r\n" + "|＿＿＿＿＿＿＿|\r\n" + "(\\_/)||\r\n"
							+ "(•ㅅ•)||\r\n" + "/....づ\r\n" + "");
				}
				System.out.println();

			} while (run);

		} else {
			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			System.out.println("사원이 없습니다.");
		}

	}

}
