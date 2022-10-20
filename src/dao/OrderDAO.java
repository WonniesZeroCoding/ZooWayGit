package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.OrderDTO;
import dto.PolicyDTO;
import mapper.OrderMapper;

public class OrderDAO {
	public int[] polSelect() throws Exception { //POlicy 테이블을 출력하고, 테이블에 대한 number return
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[2];
		int num = 0;
		ArrayList<PolicyDTO> list = new ArrayList<PolicyDTO>();
		list = new OrderMapper().PolicySelect();
		System.out.println(" -----------------------------------------------");
		System.out.println("          < 렌탈 가격표 >           ");
		System.out.println("  개월\t가격");	
		for(int i=0;i<list.size();i++) {
		System.out.println("  "+list.get(i).getPoldate()+"\t"+list.get(i).getPolprice());	
		}
		System.out.println(" -----------------------------------------------");
		while(true) {
			System.out.println("개월 수를 선택하세요");
			System.out.print(">>");
			num = Integer.parseInt(br.readLine());
			if(num==12||num==24||num==36) {
				break;
			}else {
				System.out.println("잘못된 개월수를 입력하셨습니다.");
			}
		}
		if(num==12) {
			arr[0] = 12;
			arr[1] = 500000;
		}else if(num==24) {
			arr[0] = 24;
			arr[1] = 900000;
		}else if(num==36) {
			arr[0] = 36;
			arr[1] = 1200000;
		}
		return arr;
		
	}
	
	public void insertOrder(int mnum,String date,int tnum,int poldate,int pnum,int polprice,int ostatus) throws Exception{
		 Date newDate = Date.valueOf(date);
		 new OrderMapper().insertOrder(mnum, newDate, tnum, poldate, pnum, polprice, ostatus);
	}
	
	public void showResult(int mnum,int pnum) throws Exception {
		OrderDTO order = new OrderMapper().selectResult(mnum, pnum);
		
		System.out.println(" -----------------------------------------------");
		System.out.println("|\t\t주문 내역");
		System.out.println("|주문번호 : "+order.getOnum());
		System.out.println("|주문상품 : "+order.getPname());
		System.out.println("|주문기간 : "+order.getPoldate()+"개월");
		if(order.getOstatus()==1) {
		System.out.println("|점검상태 : 방문");
		}else {
		System.out.println("|점검상태 : 자가");
		}
		System.out.println("|방문일시 : "+order.getVdate()+"일"+order.getTcontent()+"시");
		System.out.println(" -----------------------------------------------");
		
		
		
		
	}

	public void productMinus(int num) throws Exception {
		int count =new OrderMapper().selectProCount(num);//count 가 1일 경우 status 가 0 이 되게 만든다.
		if(count ==1) { //제품이 하나 남았을 경우
			int newCount = 0;
			new OrderMapper().MinusCountAndStatus(num,newCount);
		}else {
			int newCount = count-1;
			new OrderMapper().MinusCount(num,newCount);
		}
	}
	
	
	
}
