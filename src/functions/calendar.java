package functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import dto.OrderDTO;
import dto.TimeDTO;
import mapper.OrderMapper;

public class calendar {
	public int[] calendarCheck() throws NumberFormatException, IOException { //연도,월을 입력하여 calander를 보여주고, day를 입력받고, [year,month,day] 배열을 return 하는 메소드
		int[] arr = new int[3];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Calendar cal = Calendar.getInstance();
    	System.out.println("연도를 입력해 주세요");
    	System.out.print(">>");
    	int year = Integer.parseInt(br.readLine());
    	System.out.println("월을 입력해 주세요");
    	System.out.print(">>");
    	int month = Integer.parseInt(br.readLine());
    	
    	
    	
    	System.out.println("---------["+year+"년 "+month+"월]---------");
		System.out.println("  일  월   화  수  목   금  토");
		cal.set(year,month-1,1); //입력받은 월의 1일로 세팅
        //month는 0이 1월이므로 -1을 해준다
		int end = cal.getActualMaximum(Calendar.DATE); //해당 월 마지막 날짜
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); //해당 날짜의 요일(1:일요일 … 7:토요일)
		for(int i=1; i<=end; i++) {
			if(i==1) {
				for(int j=1; j<dayOfWeek; j++) {
					System.out.print("    ");
				}
			}
			if(i<10) { //한자릿수일 경우 공백을 추가해서 줄맞추기
				System.out.print(" ");
			}
			System.out.print(" "+i+" ");
			if(dayOfWeek%7==0) { //한줄에 7일씩 출력
				System.out.println();
			}
			dayOfWeek++;
		}
		System.out.println("");
		System.out.println("-----------------------------");
    	System.out.println("일을 입력해 주세요");
    	System.out.print(">>");
		int day = Integer.parseInt(br.readLine());

    	arr[0] = year;
    	arr[1] = month;
    	arr[2] = day;
    	
		return arr;
		
	}
	public int timeCheck(int year,int month,int day) throws Exception{ //year,month,day를 입력하여 시간번호를 입력받는 메소드
		//select * from VISIT where vdate = ?+?
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<TimeDTO> list = new ArrayList<TimeDTO>();
		list = new OrderMapper().TimeSelect(year, month, day);	
		int[] arr;
		int num = 0;
		if(list.size()!=0){//테이블에 하나라도 있을 때
			arr = new int[list.size()]; //이상한 번호값을 입력할때 방지를 위한 num을 배열에 넣는다
			System.out.println(" -----------------------------------------------");
	    	System.out.println("|           "+year+"년"+month+"월"+day+"일의 시간 선택          ");
	    	System.out.println("|          	번호\t시간          ");
			for(int i = 0; i<list.size();i++) {
				arr[i] = list.get(i).getTnum();
				
				
	    	System.out.println("|           "+list.get(i).getTnum()+"\t"+list.get(i).getTcontent()+"시|");
			}
			System.out.println(" -----------------------------------------------");
			Arrays.sort(arr);
			System.out.print("시간 선택>>>");
			num = Integer.parseInt(br.readLine());
			boolean check  = Arrays.binarySearch(arr, num) >= 0;
			while(!check) {
				System.out.print("목록에 있는 시간 번호가 아닙니다. 다시 입력해 주세요 >> ");
				num = Integer.parseInt(br.readLine());
				check = Arrays.binarySearch(arr, num) >= 0;
			}
			
			
		}else {//테이블에 하나도 없을때
			System.out.println(" -----------------------------------------------");
	    	System.out.println("|           "+year+"년"+month+"월"+day+"일의 시간 선택          ");
	    	System.out.println("|          	번호\t시간          ");
	    	System.out.println("|           1 \t 09-12시");
	    	System.out.println("|           2 \t 12-15시");
	    	System.out.println("|           3 \t 15-18시");
			System.out.println(" -----------------------------------------------");
			System.out.print("시간 선택>>>");
			while(true) {
			num = Integer.parseInt(br.readLine());
			if(num==1||num==2||num==3) {
				break;
			}else{
				System.out.print("목록에 있는 시간 번호가 아닙니다. 다시 입력해 주세요 >> ");
			}
			
			}
			
		}
		return num;
	}
	
	//날짜 비교 메소드
    public String CompareDate(OrderDTO order) {
    int poldate =order.getPoldate()*30;
    String vdate=order.getVdate();
    Calendar cal = Calendar.getInstance();
    
      DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      Date date = null;
      try {
          date = df.parse(vdate);
      } catch (ParseException e) {
          e.printStackTrace();
      }
      cal.setTime(date);
      cal.add(Calendar.DATE, poldate);
      //계산할 날짜
      String date1=df.format(cal.getTime());
      
      cal.setTime(new Date());
      //오늘날짜
      String date2=df.format(cal.getTime());
     
      //System.out.println("계산할 날짜: "+date1+"오늘날짜: "+date2);
      
      try {
       Date dateCom1 = df.parse(date1);
       Date dateCom2 = df.parse(date2);
       //계산한날짜 수정
       return date1;
       //if(dateCom1.after(dateCom2)) System.out.println("as가능");
    } catch (ParseException e) {
       e.printStackTrace();
    }
      
      return null;
    
 }
}
