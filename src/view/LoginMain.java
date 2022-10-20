package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

import dao.MemberDAOImpl;
import dto.MemberDTO;
import mapper.DBAction;

public class LoginMain {
	static Connection conn=DBAction.getInstance().getConnection();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		MemberDAOImpl memberdao=new MemberDAOImpl();
		MemberPage memberPage=new MemberPage();
		boolean run=true;
		do{
			System.out.println(" ✿​━━∞━━∞━━∞━━∞━━∞━━∞━━✿​  MENU ✿​━━∞━━∞━━∞━━∞━━∞━━∞━━✿");
			System.out.println("|     1. 로그인  2. 회원가입  3. 로그아웃  4. 종료                  |");
			System.out.println("|     5. 관리자 로그인                                                           |");
			System.out.println(" -------------------------------------------------");
			System.out.print("번호를 입력해주세요.  :  ");
			try {
				int num=Integer.parseInt(br.readLine());				
				switch (num){
					case 1: 
						//로그인
						MemberDTO loginMember=memberdao.MemberLogin();
						if(loginMember.getMstatus()==2) {
							memberPage.memberPageView(loginMember);
						}
						
						break;
					case 2: 
						//회원가입
						System.out.println("회원가입");
						memberdao.MemberSignIn();
						break;
					case 3:
						//로그아웃
						loginMember=null;
						break;
					case 4:
						run=false;
						break; 
					case 5:
						break;
					default:
						break;
				}//switch문 끝
						
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.out.println("번호를 입력해주세요.");
			}
			
		}while(run);
	}

}
		
	

