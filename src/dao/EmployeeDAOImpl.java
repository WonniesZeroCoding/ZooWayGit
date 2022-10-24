package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dto.EmployeeDTO;
import dto.MemberDTO;
import mapper.EmployeeMapper;
import view.EmployeeManagementPage;

public class EmployeeDAOImpl {
	MemberDAOImpl memberDao = new MemberDAOImpl();
	EmployeeMapper employeeMapper = new EmployeeMapper();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   
	/* 1-1.전체 사원 목록 조회 */
	public void employeeAllList() throws NumberFormatException, IOException {
		System.out.println("사원 조회 시작");
		List<EmployeeDTO> employees = employeeMapper.employeeAllList();
  
		if (employees.size() != 0) {

			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			System.out.println("사원번호\t\t사원아이디\t\t이름\t\t전화번호\t\t\t담당구역\t\t");
			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			for (int i = 0; i < employees.size(); i++) {
				System.out.println(employees.get(i).getEmnum() + "\t\t" + employees.get(i).getEid() + "\t\t"
						+ employees.get(i).getEname() + "\t\t" + employees.get(i).getEphone() + "\t\t"
						+ employees.get(i).getEplace() + "\t\t");
			}
			while (true) {
				System.out.println("원하시는 사원번호를 입력해주세요. 뒤로가려면 0번을 눌러주세요");
				String emnum = br.readLine();// 회원번호선택

				if (Integer.parseInt(emnum) == 0) {
					break;
				} else if (Integer.parseInt(emnum) > 0) {
					EmployeeManagementPage employeeManagementPage = new EmployeeManagementPage();
					employeeManagementPage.employeeManagement2(Integer.parseInt(emnum));
					break;
				} else {
					System.out.println("숫자만 입력해주세요.");
				}
			}
		} else {
			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			System.out.println("사원이 없습니다.");
		}

	}

	/* 사원 담당구역 수정 */
	public void employeeUpdate(int emnum) throws IOException {
		while (true) {
			System.out.println("변경하실 담당구역 구를 입력해주세요. ex)성북구,강남구");
			String address = br.readLine();// 구 입력
			if (memberDao.checkAddr(address)) {
				employeeMapper.employeeUpdate(emnum, address);
				break;
			} else {
				System.out.println("구를 정확하게 입력해주세요.\n");
			}
		}
	}

	/* 사원 탈퇴 처리(estatus를 1로 변경) */
	public void employeeDelete(int emnum) {
		employeeMapper.employeeDelete(emnum);

	}

	/* 사원 등록 */
	public boolean employeeInsert() {
		EmployeeDTO newEmployee = new EmployeeDTO();

		try {
			// 아이디 중복체크
			while (true) {
				System.out.print("아이디를 입력해주세요\n");
				String eid = br.readLine();
				if (eIdDupCheck(eid)) {
					System.out.println("중복된 아이디입니다. 다시 한번 확인해주세요");
				} else {
					newEmployee.setEid(eid);
					break;
				}
			}
			// 비밀번호 확인문
			while (true) {
				System.out.print("비밀번호를 입력해주세요\n");
				String epw = br.readLine();
				System.out.print("비밀번호를 다시 한번 입력해주세요\n");
				String epwCheck = br.readLine();
				if (epw.equals(epwCheck)) {
					newEmployee.setEpw(epwCheck);
					break;
				} else
					System.out.println("비밀번호를 확인해주세요.");
			}
			System.out.print("이름을 입력해주세요\n");
			newEmployee.setEname(br.readLine());
			while (true) {
				System.out.print("전화번호를 입력해주세요\n");
				Pattern pattern = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");
				String ephone = br.readLine();
				Matcher matcher = pattern.matcher(ephone);
				if (matcher.matches()) {
					newEmployee.setEphone(ephone);
					break;
				} else
					System.out.println("010-XXXX-XXXX 형식에 맞춰 전화번호를 입력해주세요.\n");
			}
			// 주소 체크
			while (true) {
				System.out.print("기사님의 담당구역을 입력해주세요. ex)성북구, 강남구\n");
				String eplace = br.readLine();
				if (memberDao.checkAddr(eplace)) {
					newEmployee.setEplace(eplace);
					break;
				} else {
					System.out.println("구를 정확하게 입력해주세요.\n");
				}
			}

			if (employeeMapper.employeeInsert(newEmployee)) {
				System.out.println("기사등록이 완료되었습니다!");
			} else {
				System.out.println("기사등록에 실패했습니다.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;

	}

	// 사원아이디 중복체크
	public boolean eIdDupCheck(String eid) {
		boolean check = employeeMapper.eIdDupCheck(eid);
		return check;
	}
	
	//관리자 또는 기사 로그인
	public EmployeeDTO employeeLogin() throws IOException {
		System.out.print("아이디를 입력해주세요");
		String eid=br.readLine();
		System.out.print("비밀번호를 입력해주세요");
		String epw=br.readLine();
		
		EmployeeDTO employee =employeeMapper.employeeLogin(eid, epw);
		if(employee!=null) {
				return employee;
		}else if(employee==null){
				System.out.println("");
		}
		return null;
	}

}
