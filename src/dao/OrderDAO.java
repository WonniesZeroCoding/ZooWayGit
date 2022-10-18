package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;

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
	
	
	
}
