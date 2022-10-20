package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import dao.OrderDAO;
import dto.MemberDTO;
import functions.calendar;
import functions.proNumCheck;
import mapper.ProductMapper;

public class OrderMain {
	
	MemberDTO member;
	
	
	public OrderMain(MemberDTO member) {
		this.member = member;
	}
	
	
	public static void main(String[] args) throws Exception {
		int num = 0; //
		int homeNum = 0; //자가인지,방문인지
		String visitDate; //방문날짜
		int timeNum = 0;
		
		
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("주문할 정수기 번호를 선택해주세요");
		//존재하는 정수기 번호 확인
		System.out.print(">>");
		num = Integer.parseInt(br.readLine());
		boolean check = new proNumCheck().productCheck(num);
		while(!check) {
			System.out.println("상품번호가 존재하지 않습니다. 다시 입력해 주세요");
			System.out.print(">>");
			num = Integer.parseInt(br.readLine());
			check = new proNumCheck().productCheck(num);
			//number 로 정수기 번호를 받음. => 정수기 번호가 아니면 다시 출력
		}
	    while(true) {
	    	
	    	System.out.println(" -----------------------------------------------");
	    	System.out.println("|           정수기관리 선택                        ");
			System.out.println("|           1. 자가 2. 방문 3.메인페이지로           ");
			System.out.println(" -----------------------------------------------");
			System.out.print(">>");
			homeNum = Integer.parseInt(br.readLine());
			if(homeNum==1||homeNum==2) {
				break;
			}else if(homeNum==3) {
				//메인 메뉴로 보내주기
			}
		}
	    int[] arr = new int[3];
	    while(true) { //날짜 선택
	    	
	    	calendar cal = new calendar();
	    	arr = cal.calendarCheck();
	    	System.out.println(" -----------------------------------------------");
	    	System.out.println("|           선택하신 날짜는 "+arr[0]+"년"+arr[1]+"월 "+arr[2]+"일입니다 맞습니까?             ");
			System.out.println("|           1.확인  2.다른 날짜 선택  3.메인페이지로                 ");
			System.out.println(" -----------------------------------------------");
			int num3 = Integer.parseInt(br.readLine());
	    	if(num3==1) {
	    		visitDate = arr[0]+"-"+arr[1]+"-"+arr[2];
	    		break;
	    	}else if(num3==3) {
	    		//메인 페이지로
	    	}
		}
	    	while(true) {//시간 테이블에서 시간 받기
	    	calendar cal = new calendar();
	    	timeNum = cal.timeCheck(arr[0], arr[1], arr[2]);
	     	System.out.println(" -----------------------------------------------");
	    	System.out.println("|           선택하신 시간번호는 "+timeNum+"입니다 맞습니까?");
			System.out.println("|           1.확인  2.다른 시간 선택  3.메인페이지로     ");
			System.out.println(" -----------------------------------------------");
	    	int num4 = Integer.parseInt(br.readLine());
	    	if(num4==1) {//확인을 눌렀을시
	    		break;
	    	}if(num4==3) {
	    		//메인페이지로
	    	}
	    	
	    	}
	    	int[] arr2 = new int[2]; 
	    	arr2 = new OrderDAO().polSelect();  //policy 받기
	    	
	    	
	    	
	    	System.out.println("최종 insert 전 확인");
	    	
	    	int mnum = 4;
	    	System.out.println(visitDate);//방문날짜
	    	System.out.println(timeNum);//시간번호
	    	System.out.println(arr2[0]);//개월수
	    	System.out.println(num);//상품번호
	    	System.out.println(arr2[1]);//개월당 금액
	    	System.out.println(homeNum); //자가 or 방문
	    	
	    	new OrderDAO().insertOrder(mnum,visitDate,timeNum,arr2[0],num,arr2[1],homeNum);
	    	//VISIT 테이블에 추가해주기 
	    	//신청확인내역 띄워주기
	    	//신청시 상품의 재고 -1
	    
	    		    	
	    	///이후에 이제.... 그... 기사한테 메세지 보내는 거?? ㅋ
	    

	}

}
