package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import dao.OrderDAO;
import dao.VisitDAO;
import dto.MemberDTO;
import dto.OrderDTO;
import functions.calendar;
import functions.proNumCheck;
import mapper.ProductMapper;

public class OrderMain {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	
	
	
	public OrderMain(MemberDTO member) throws Exception {
		int num = 0; //
		int homeNum = 0; //자가인지,방문인지
		String visitDate; //방문날짜
		//int timeNum = 0;
		
		
		
		
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
				if(homeNum==1) {
					homeNum=2;
				}else if(homeNum==2) {
					homeNum=3;
				}
				
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
			System.out.print(">>");
			int num3 = Integer.parseInt(br.readLine());
	    	if(num3==1) {
	    		visitDate = arr[0]+"-"+arr[1]+"-"+arr[2];
	    		break;
	    	}else if(num3==3) {
	    		//메인 페이지로
	    	}
		}
	    
		    /*
		    2022 - 10 -23일 시간입력 삭제
	    
	    
	    
	    	while(true) {//시간 테이블에서 시간 받기
	    	calendar cal = new calendar();
	    	timeNum = cal.timeCheck(arr[0], arr[1], arr[2]);
	     	System.out.println(" -----------------------------------------------");
	    	System.out.println("|           선택하신 시간번호는 "+timeNum+"입니다 맞습니까?");
			System.out.println("|           1.확인  2.다른 시간 선택  3.메인페이지로     ");
			System.out.println(" -----------------------------------------------");
			System.out.print(">>");

	    	int num4 = Integer.parseInt(br.readLine());
	    	if(num4==1) {//확인을 눌렀을시
	    		break;
	    	}if(num4==3) {
	    		//메인페이지로
	    	}
	    	
	    	}
	    	
	    	*/
	    	
	    	int[] arr2 = new int[2]; 
	    	arr2 = new OrderDAO().polSelect();  //policy 받기

	    	int mnum = member.getMnum();
//	    	System.out.println(visitDate);//방문날짜
//	    	System.out.println(timeNum);//시간번호
//	    	System.out.println(arr2[0]);//개월수
//	    	System.out.println(num);//상품번호
//	    	System.out.println(arr2[1]);//개월당 금액
//	    	System.out.println(homeNum); //자가 or 방문
	    	
	    	new OrderDAO().productMinus(num); //재고 마이너스
	    	
	    	OrderDTO order = new OrderDTO(visitDate,arr2[0]); //visitDate와 개월수 저장
	    	
	    	String endDate = new functions.calendar().MakeEndDate(visitDate,arr2[0]); //endDate 받아오기
	    	
	    	new OrderDAO().insertOrder(mnum,visitDate,arr2[0],num,arr2[1],homeNum,endDate); //주문테이블에 추가
	    	
	    	
	    	int onum = new OrderDAO().selectOrderNum(member.getMnum());//가장 최신 onum 가져오기

	    	new OrderDAO().showResult(onum,visitDate); // 신청확인내역 띄워주기
	    	
	    	//2022 - 10 - 23 회의4 이후 수정  => 관리자로 이동
	    	/*
	    	
	    	int onum = new OrderDAO().selectOrderNum(member.getMnum());//가장 최신 onum 가져오기
	    	 
	    	new VisitDAO().insertVisit(mnum,onum,visitDate,timeNum);//VISIT 테이블에추가 
	    	*/
	    	
	    	///이후에 이제.... 그... 기사한테 메세지 보내는 거?? ㅋ
	    
	    
	    
	    
	    
	    
	    
	    
	    
		
		
	}
	



}
