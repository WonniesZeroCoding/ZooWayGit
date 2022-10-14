package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	private int onum; // 주문번호
	private int mnum; // 회원번호 
	private String vdate; //방문일
	private int tnum; //시간번호 :새로 추가한거
	private int poldate; //기간
	private int pnum; //모델번호
	private int polprice; //렌탈비
	private int ostatus; //주문 status (방문점검인지 자가점검인지)
}
