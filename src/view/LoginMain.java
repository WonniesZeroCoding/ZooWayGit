package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import dao.MemberDAOImpl;
import dto.MemberDTO;

public class LoginMain {	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		MemberDAOImpl dao=new MemberDAOImpl();
		while(true){
			System.out.println(" -----------------------------------------------");
			System.out.println("|      1. 로그인  2. 회원가입  3. 로그아웃  4. 종료            |");
			System.out.println(" -----------------------------------------------");
			System.out.print("번호를 입력해주세요.  :  ");
			try {
				int num=Integer.parseInt(br.readLine());				
				
				
				switch (num){
					case 1: 
						//로그인
						System.out.println("로그인");
						MemberDTO member=dao.MemberLogin();
						if(member!=null) {}
						break;
					case 2: 
						//회원가입
						System.out.println("회원가입");
						break;
					case 3:
						break;
					case 4:
						break;
					default:
						break;
				}//switch문 끝
						
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.out.println("번호를 입력해주세요.");
			}
			
		}//while문 끝	
	}
	
}
