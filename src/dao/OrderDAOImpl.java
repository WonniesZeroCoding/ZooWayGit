package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.util.Date;  지금 얘때문에 에러나는거같음
import java.util.List;
import dto.ASDTO;
import dto.MemberDTO;
import dto.OrderDTO;
import functions.calendar;
import mapper.OrderMapper;
import mapper.VisitMapper;
import view.MemberOrderPage;

public class OrderDAOImpl {

	OrderMapper orderMapper = new OrderMapper();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/* 로그인한 회원의 렌탈현황 목록 조회 */
	public List<OrderDTO> memberOrderList(MemberDTO member) throws NumberFormatException, Exception {
		System.out.println(member.getMname() + "님의 렌탈 중인 목록 조회");
		List<OrderDTO> memberOrders = orderMapper.memberOrderList(member);
		if (memberOrders.size() != 0) {

			System.out.println(
					"------------------------------------------------------------------------------------------------------------");
			System.out.println("주문번호\t\t신청일\t\t약정기간\t\t제품번호\t\t렌탈비용\t\t약정 해지일");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------");
			for (int i = 0; i < memberOrders.size(); i++) {
				System.out.println(memberOrders.get(i).getOnum() + "\t\t" + memberOrders.get(i).getVdate() + "\t"
						+ memberOrders.get(i).getPoldate() + "\t\t"
						+ memberOrders.get(i).getPnum() + "\t\t" + memberOrders.get(i).getPolprice() +"\t\t"
						+ memberOrders.get(i).getEndDate());
			}
			while (true) {
				System.out.println("원하시는 주문번호를 입력해주세요. 뒤로가려면 0번을 눌러주세요");
				String onum = br.readLine();// 주문번호선택

				if (Integer.parseInt(onum) == 0) {
					break;
				} else if (Integer.parseInt(onum) > 0) {
					// 회원번호에 맞는 주문번호인지 체크
					if (memberOrderCheck(member.getMnum(), Integer.parseInt(onum))) {
						// 회원이 신청한 주문이 맞으면, memberOrderPage로 이동
						MemberOrderPage memberOrderPage = new MemberOrderPage();

						memberOrderPage.memberOrderPage2(Integer.parseInt(onum),member);
						break;
					} else {// 목록에 있지않은 주문번호 입력시

						System.out.println("잘못된 주문번호 입니다. 다시 한번 확인하세요");
					}
				} else {// 문자입력시
					System.out.println("숫자만 입력해주세요.");
				}
			}
		} else {
			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			System.out.println("신청한 렌탈이 없습니다.");
		}
		return memberOrders;

	}

	// 로그인 회원이 가진 주문번호에 해당하는지 검사
	public boolean memberOrderCheck(int mnum, int onum) {
		boolean check = orderMapper.memberOrderCheck(mnum, onum);
		return check;

	}

	
	//A/S신청하기(calendar 펑션 사용)
	public void ASService(int onum,MemberDTO member) {
		MemberDAOImpl memberDao=new MemberDAOImpl();
		VisitMapper visitmapper=new VisitMapper(); 
		OrderMapper ordermapper=new OrderMapper();
		ASDTO as=new ASDTO();
		//주문번호로 방문일을 끌고와서 기간이 지났는지 안지났는지 검사한다
		//OrderDTO order=orderMapper.getVDATE(onum);
		calendar cal=new calendar();
		int[] memCal = null;
		//만약 기간이 지나지 않았다면?
			//zoocareplus가 있는지 없는지 검사한다
			if(memberDao.checkZCP(member.getMid())) {
				//방문날짜, 시간을 입력받는다
				try {
					//날짜선택
					memCal=cal.calendarCheck();
					//System.out.println("날짜어떤식으로나오는지체크"+memCal[0]+ memCal[1]+ memCal[2]);
					String memCal1=null;
					String memCal2=null;
					if(memCal[1]<10) memCal1="0"+memCal[1];
					else memCal1=Integer.toString(memCal[1]);
					if(memCal[2]<10) memCal2="0"+memCal[2];
					else memCal2=Integer.toString(memCal[2]);
					//시간선택
						try {
							as.setVdate(memCal[0]+"-"+memCal1+"-"+memCal2);
						} catch (Exception e) {
							e.printStackTrace();
						}
					
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}//try구문 끝
			}//즈.케어.플러스. 확인
		as.setOnum(onum);
		//as table에 삽입
		ordermapper.ASService(onum, as);
		//visit table에 삽입
		Date vdate = Date.valueOf(as.getVdate());
		int asnum=visitmapper.getASNum();
	        //String을 date형식으로 변경시켜줬음
		try {
			visitmapper.insertVisit(member.getMnum(), 0,asnum, vdate);
		} catch (Exception e) {
			System.out.println("visittable에 제대로 안들어감");
			e.printStackTrace();
		}
		System.out.println(member.getMname()+"님,");
		System.out.println(memCal[0]+"년"+memCal[1]+"월"+memCal[2]+"일에");
		System.out.println("AS신청이 완료되었습니다. 감사합니다!");
		
	}//그대로 데이터베이스에 넣어주면 됨
		
	
	// 렌탈 해지 신청
	public void contractTerminate(int onum, int mnum) throws Exception {
		// 해지를 신청하면 order의 status가 4.반납신청으로 변경-> visit table로 이동
		// System.out.println("남은 계약기간은"++"입니다.");
		System.out.println("약정 해지 시 정수기를 반납해야합니다. 정수기 반납일을 지정해 주세요.");
		String visitDate; //방문날짜
		int timeNum = 0; //방문시간
		int[] arr = new int[3];
		while (true) { // 날짜 선택

			calendar cal = new calendar();
			arr = cal.calendarCheck();
			System.out.println(" -----------------------------------------------");
			System.out.println(
					"|           선택하신 날짜는 " + arr[0] + "년" + arr[1] + "월 " + arr[2] + "일입니다 맞습니까?             ");
			System.out.println("|           1.확인  2.다른 날짜 선택  3.메인페이지로                 ");
			System.out.println(" -----------------------------------------------");
			int num3 = Integer.parseInt(br.readLine());
			if (num3 == 1) {
				visitDate = arr[0] + "-" + arr[1] + "-" + arr[2];
				break;
			} else if (num3 == 3) {
				// 메인 페이지로
			}
		}
	
		new VisitDAO().insertVisit(mnum,onum,0,visitDate);
		//방문신청완료 후 ostatus 4반납신청으로 변경
		orderMapper.contractTerminate(onum);
		System.out.println("해지신청이 완료되었습니다. 신청하신 날짜에 기사님이 방문하실 예정입니다.");
	}	



	//렌탈 약정 연장
		public void contractExtend(int onum) throws Exception {
			System.out.println("원하시는 연장 개월 수를 선택해주세요");
			String realEndDate = null;
	    	try {
				int[] arr = new OrderDAO().polSelect();
		    	if (arr.length > 0) {
		    		realEndDate = UpdateEndDate(onum,arr);//연장에 따른 해지일 계산
		    		orderMapper.contractExtend(onum,arr,realEndDate);//연장에 따른 추가 금액 업데이트
					System.out.println("약정연장이 완료되었습니다. 변경된 약정 해지일은 "+realEndDate+" 입니다.");
				}
			} catch (Exception e) {
				System.out.println("연장 실패_개월 수가 올바르게 입력되지 않았습니다.");
				throw e;
			}  //policy 받기
			
		}
		
		//해지일 업데이트해주는 메소드
	    public String UpdateEndDate(int oNum, int[] arr) {
	       OrderMapper ordermapper=new OrderMapper();
	       String predate=ordermapper.GetEndDate(oNum);
	       //endDate를 끌어와서 그걸로 계산해주면 됨
	       
	       //String vdate =order.getVdate();
	       int poldate= arr[0]/12;
	       Calendar cal = Calendar.getInstance();
	       
	         DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	         java.util.Date date = null;
	         try {
	             date = df.parse(predate);
	         } catch (ParseException e) {
	             e.printStackTrace();
	         }
	         cal.setTime(date);
	         cal.add(Calendar.YEAR, poldate);
	         
	         String realEndDate = df.format(cal.getTime());
	         return realEndDate;
	    }


}
