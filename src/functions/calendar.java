package functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import dto.OrderDTO;
import mapper.OrderMapper;

public class calendar {
	public int[] calendarCheck() throws NumberFormatException, IOException { //연도,월을 입력하여 calander를 보여주고, day를 입력받고, [year,month,day] 배열을 return 하는 메소드
		int[] arr = new int[3];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Calendar cal = Calendar.getInstance();
		//현재 날짜 받기
		LocalDate localnow = LocalDate.now();
		Date now = java.sql.Date.valueOf(localnow);
		
		
		System.out.println("현재날짜 >> "+ now);
		int year  = 0;
		int month = 0;
		int day = 0;
		
		
		while(true) {
		
		System.out.println("연도를 입력해 주세요");
    	System.out.print(">>");
    	year = Integer.parseInt(br.readLine());
    	System.out.println("월을 입력해 주세요");
    	System.out.print(">>");
    	month = Integer.parseInt(br.readLine());
    	if(month<1||month>12) {
			System.out.println("잘못된 날짜 정보를 입력하셨습니다 처음부터 다시 시도해 주세요");
			continue;
    	}
    	
    	
    	
    	String calyear = String.valueOf(year);
    	String calmonth = String.valueOf(month);
    	
    	
    	
		
    	
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
		day = Integer.parseInt(br.readLine());
		if(day>31) {
			System.out.println("잘못된 날짜 정보를 입력하셨습니다 처음부터 다시 시도해 주세요");
			continue;
		}
		String calDay = null;
		if((day-9)<=0) {
			calDay = "0"+String.valueOf(day);
		}else {
			calDay = String.valueOf(day);
		}
		System.out.println(calyear);
		System.out.println(calmonth);
		System.out.println(calDay);
		
		
		LocalDate newLocalDate = LocalDate.parse(calyear+"-"+calmonth+"-"+calDay);
		
		Date newDate = java.sql.Date.valueOf(newLocalDate);
		
		
		
		if(newDate.after(now)) {
		break;
		}
		System.out.println("현재 시간보다 나중 시간을 선택해 주세요");

		}
    	arr[0] = year;
    	arr[1] = month;
    	arr[2] = day;
    	
		return arr;
		
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
    //해지일 만들어주는 메소드
    public String MakeEndDate(String visitdate,int poldate) {
       OrderMapper ordermapper=new OrderMapper();
       poldate=poldate/12;
       //최초방문일 끌어오기
       //OrderDTO order=ordermapper.getVDATE(oNum);
       //String vdate =order.getVdate();
       //System.out.println(visitdate);
       System.out.println(poldate);
       
       
       Calendar cal = Calendar.getInstance();
       
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         Date date = null;
         try {
             date = df.parse(visitdate);
         } catch (ParseException e) {
             e.printStackTrace();
         }
         cal.setTime(date);
         cal.add(Calendar.YEAR, poldate);
         
         String realEndDate = df.format(cal.getTime());
         return realEndDate;
    }
	
}
