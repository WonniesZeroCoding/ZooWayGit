package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dao.MemberDAOImpl;
import dto.MemberDTO;

  
public class ZooCarePlusPage {
	MemberDAOImpl memberDao=new MemberDAOImpl();
	public void SignInZooCarePlus(MemberDTO member) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		MemberPage memberPage=new MemberPage();
		boolean run=true;
		
			System.out.println(" ✿​━━∞━━∞━━∞━━∞━━∞━━∞━━✿​  MENU ✿​━━∞━━∞━━∞━━∞━━∞━━∞━━✿");
			System.out.println("|           ZOO CARE PLUS에 가입하세요 !			      |");
			System.out.println("|       A/S 및 방문 서비스를 무료로 사용할 수 있습니다 !       |");
			System.out.println("|   		 가입하고 싶다면  yes를 입력해주세요		      |");
			System.out.println("|  			  (이전 메뉴로 돌아가기 : M) 			      |");
			System.out.println(" -------------------------------------------------");
	
			do {				
				try {
					String answer = br.readLine();
					if(answer.equalsIgnoreCase("yes")) {
						//가입하는 메소드
						memberDao.SignInZCP(member.getMid());
					}else if(answer.equalsIgnoreCase("m")) {
						run=false;
					}else{
						System.out.println("올바른 값을 입력해주세요.");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}while(run);

	}
	
	public void ZooCarePlusMenu(MemberDTO member) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		MemberPage memberPage=new MemberPage();
		boolean run=true;
		
		System.out.println(" ✿​━━∞━━∞━━∞━━∞━━∞━━∞━━✿​  MENU ✿​━━∞━━∞━━∞━━∞━━∞━━∞━━✿");
		System.out.println("|      		 현재 ZooCarePlus에 가입되어 있습니다    	     |");
		System.out.println("|    		 	탈퇴하고 싶으시면 yes를,  	         |");
		System.out.println("|     		메뉴로 돌아가고 싶으시면 m을 눌러주세요.        |");
		System.out.println(" -------------------------------------------------");
		
		do {
			try {
				String answer = br.readLine();
				if(answer.equalsIgnoreCase("yes")) {
					//탈퇴 메소드
					memberDao.DeleteZCP(member.getMid());
				}else if(answer.equalsIgnoreCase("m")) {
					run=false;
				}else{
					System.out.println("올바른 값을 입력해주세요.");
				}
				
			} catch (IOException e) {e.printStackTrace();}//try구문 끝				
			
		}while(run);//while구문 끝				
		
	}
	
	
}
