package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

import dao.EmployeeDAOImpl;
import dao.MemberDAOImpl;
import dto.EmployeeDTO;
import dto.MemberDTO;
import mapper.DBAction;

public class LoginMain {
	static Connection conn=DBAction.getInstance().getConnection();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		MemberDAOImpl memberdao=new MemberDAOImpl();
		EmployeeDAOImpl employeedao=new EmployeeDAOImpl();
		MemberPage memberPage=new MemberPage();
		AdminPage adminPage = new AdminPage(); 
		boolean run=true;
		do{
			System.out.println(" ✿​━━∞━━∞━━∞━━∞━━∞━━∞━━✿​  MENU ✿​━━∞━━∞━━∞━━∞━━∞━━∞━━✿");
			System.out.println("|     1. 로그인  2. 회원가입  3. 관리자 로그인    4.종료          |");
			System.out.println(" -------------------------------------------------");
			System.out.print("번호를 입력해주세요.  :  ");
			try {
				String num=br.readLine();				
				switch (num){
					case "1": 
						//로그인
						MemberDTO loginMember=memberdao.MemberLogin();
						if(loginMember!=null) {
							if(loginMember.getMstatus()==2) {
								memberPage.memberPageView(loginMember);
							}
						}
						break;
					case "2": 
						//회원가입
						System.out.println("회원가입");
						memberdao.MemberSignIn();
						break;
					case "3":
						EmployeeDTO loginEmployee = employeedao.employeeLogin();
						if(loginEmployee.getEstatus()==2) {//관리자인 경우
							adminPage.adminPageView(loginEmployee);
						}else if(loginEmployee.getEstatus()==3) {//기사인 경우
							//기사 메인 페이지 필요한 사람이 만드셈
						}else if(loginEmployee.getEstatus()==1) { //탈퇴한 관리자/기사인 경우
							System.out.println("탈퇴회원 입니다.");
						}else if(loginEmployee.getEstatus()==0) {
							System.out.println("아이디 비밀번호를 확인하세요");
						}
						break;
					case "4":
						System.out.println("프로그램을 종료합니다.");
						run=false;
						break; 
					default:
						System.out.println("올바른 번호를 입력해주세요..");
						break;
				}//switch문 끝
						
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.out.println("번호를 입력해주세요.");
			}
			
		}while(run);
	}

}
		
	

