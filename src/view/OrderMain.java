package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.SQLException;

import functions.proNumCheck;
import mapper.ProductMapper;

public class OrderMain {

	public static void main(String[] args) throws NumberFormatException, IOException, SQLException {
		int num = 0;
		int homeNum = 0; //자가인지,방문인지
		Date visitDate; //방문날짜
		int agreement = 0; //약정기간
		
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("주문할 정수기 번호를 선택해주세요");
		//존재하는 정수기 번호 확인
		System.out.print(">>");
		num = Integer.parseInt(br.readLine());
		boolean check = new proNumCheck().productCheck(num);
		while(!check) {
			System.out.println("상품번호가 존재하지 않습니다. 다시 입력해 주세요");
			System.out.print(">>");
			int num2 = Integer.parseInt(br.readLine());
			check = new proNumCheck().productCheck(num2);
			//number 로 정수기 번호를 받음. => 정수기 번호가 아니면 다시 출력
		}
	    while(true) {
	    	
	    	
	    	
	    	
	    }
		
		
	}

}
