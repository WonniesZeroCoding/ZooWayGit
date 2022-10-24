package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import dto.EmployeeDTO;
import dto.OrderDTO;
import mapper.OrderMapper;
import mapper.rentalMapper;

public class rentalDAO {
	public void rentalSelectAdmin() throws Exception {
		//렌탈 CONNECT
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<OrderDTO> list = new ArrayList<OrderDTO>();
		list = new rentalMapper().selectAllrental();
		int[] checknum = new int[list.size()];
		System.out.println("✿​━━∞━━∞━━∞━━∞━━━━∞━━∞━━∞━━✿ 렌탈신청목록  ✿​━━∞━━∞━━∞━━∞━━━━∞━━∞━━∞━━✿");
		System.out.println("|주문번호\t회원번호\t방문날짜\t\t등록기간\t상품번호\t가격\t상태\t만기날짜\t\t주소번호\t구이름\t주캐플여부");
		for(int i = 0 ; i<list.size();i++) {
			checknum[i] = list.get(i).getOnum();
			if(list.get(i).getZcp()==2) {
				System.out.println(list.get(i).getOnum()+"\t"+list.get(i).getMnum()+"\t"+list.get(i).getVdate()+"\t비어있음\t"+list.get(i).getPoldate()+"\t"+list.get(i).getPnum()+"\t"+list.get(i).getPolprice()+"\t"+list.get(i).getOstatus()+"\t"+list.get(i).getEndDate()+"\t"+list.get(i).getMaddress()+"\t"+list.get(i).getAddress()+"\t가입함");
			}else {
				System.out.println(list.get(i).getOnum()+"\t"+list.get(i).getMnum()+"\t"+list.get(i).getVdate()+"\t비어있음\t"+list.get(i).getPoldate()+"\t"+list.get(i).getPnum()+"\t"+list.get(i).getPolprice()+"\t"+list.get(i).getOstatus()+"\t"+list.get(i).getEndDate()+"\t"+list.get(i).getMaddress()+"\t"+list.get(i).getAddress()+"\t가입안함");
			}
			
		}
		System.out.println("----------------------------------------------------------------");
		Arrays.sort(checknum);
		System.out.print("기사 배치하실 주문번호를 입력해 주세요(맨 위에서부터 입력하기를 권장합니다.) >> ");
		int onum = Integer.parseInt(br.readLine());
		boolean check = Arrays.binarySearch(checknum, onum) >=0;
		while(!check) {
			System.out.println("존재하지 않는 주문번호입니다. 다시 입력해 주세요");
			onum = Integer.parseInt(br.readLine());
			check = Arrays.binarySearch(checknum, onum) >=0;
		}
			//onum에 대한 주케플,신청날짜,지역번호 를 받아옴
		
			OrderDTO ZVA;
			ZVA = new OrderMapper().selectZVA(onum);
			
 			//기사 선택
			System.out.println("✿​━━∞━━∞━━∞━━∞━━━━∞━━∞━━∞━━✿ 기사목록  ✿​━━∞━━∞━━∞━━∞━━━━∞━━∞━━∞━━✿");
			//주케플이 있는 경우 => 같은 구의 기사 SELECT (시간이 꽉찬 경우) 다른 지역 기사 SELECT 
			
				ArrayList<EmployeeDTO> em = new ArrayList<EmployeeDTO>();
				em = new OrderMapper().selectEmployee(ZVA);
				int[] checkNum = new int[em.size()];
				System.out.println("|사원번호\t사원아이디\t사원이름\t사원휴대폰\t\t근무지");
				for(int i=0; i<em.size();i++) {
					checkNum[i] = em.get(i).getEmnum();
					System.out.println(em.get(i).getEmnum()+"\t"+em.get(i).getEid()+"\t"+em.get(i).getEname()+"\t"+em.get(i).getEphone()+"\t"+em.get(i).getEplace());
				}
				System.out.println("----------------------------------------------------------------");
			Arrays.sort(checkNum);
			System.out.print("컨택하실 사원번호를 입력하세요");
			int emNum = Integer.parseInt(br.readLine());
			boolean check1 = Arrays.binarySearch(checkNum, emNum) >=0;
			while(!check1) {
				System.out.println("선택하신 사원번호는 없는 사원번호입니다.");
				emNum = Integer.parseInt(br.readLine());
				Arrays.sort(checkNum);
				check1 = Arrays.binarySearch(checkNum, emNum) >=0;
			}
//			System.out.println(ZVA.getVdate());
//			//emNum에 대한 시간 select 
//			//ArrayList<TimeDTO> timeList = new ArrayList<TimeDTO>();
//			//timeList = new OrderMapper().selectTime(emNum, ZVA.getVdate());
//			System.out.println("✿​━━∞━━∞━━∞━━∞━━━━∞━━∞━━∞━━✿ "+emNum+"번 사원의 잔여 시간 목록  ✿​━━∞━━∞━━∞━━∞━━━━∞━━∞━━∞━━✿");
//			System.out.println("시간번호\t시간상세");
//			for(int i = 0 ;i<timeList.size();i++) {
//				System.out.println("|"+timeList.get(i).getTnum()+"\t"+timeList.get(i).getTcontent());
//			}
//			System.out.println("----------------------------------------------------------------");
//			System.out.println("시간번호를 입력하세요");
//			System.out.print(">>");
//			int timenum;
//			while(true) {
//				timenum = Integer.parseInt(br.readLine());
//				if(timenum==1||timenum==2||timenum==3) {
//					break;
//				}
//				System.out.println("잘못된 번호를 입력하셨습니다.");
//			}
			//사원을 선택해서 VISIT 테이블에 추가
			//vnum : autoIncre
			//mnum : ZVA.getMnum();
			//emNum : emNum
			//onum : onum
			//asnum  : null
			//vdate : ZVA.getVdate()
			//tnum : timenum
			//vstatus : 1
			//new VisitMapper().insertVisit(ZVA.getMnum(), emNum, onum, ZVA.getVdate(),timenum);
			
			System.out.println("VISIT 등록 완료!!");
			
			
			
			//기사에게 연락
				
				
			
		
		
	}

	public void rentalLimitAdmin() throws Exception {
		//ArrayList<VisitDTO> list = new rentalMapper().selectAllrental();
		ArrayList<OrderDTO> list = new rentalMapper().selectLimitRental();
		System.out.println("✿​━━∞━━∞━━∞━━∞━━✿ 반납임박목록  ✿​━━∞━━∞━━∞━━∞━━✿");
		System.out.println("|주문번호\t회원번호\t방문날짜\t\t등록기간\t상품번호\t가격\t상태\t만기날짜\t\t잔여일");
		for(int i = 0; i<list.size();i++) {
			System.out.println(list.get(i).getOnum()+"\t"+list.get(i).getMnum()+"\t"+list.get(i).getVdate()+"\t"+list.get(i).getPoldate()+"\t"+list.get(i).getPnum()+"\t"+list.get(i).getPolprice()+"\t"+list.get(i).getOstatus()+"\t"+list.get(i).getEndDate()+"\t"+list.get(i).getEndDateCount());
		}
		System.out.println("-------------------------------------");
		/*
		System.out.print(">>연락하실 번호를 입력하세요 >>");
		int number = Integer.parseInt(br.readLine());
		
		===> 메세지 보내기
		*/
	}
}
