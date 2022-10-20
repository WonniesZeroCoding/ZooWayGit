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
	private int tnum; //시간번호 :새로 추가한거  실제로는 int tnum을 입력하지만 서브쿼리써서 DTO에는 tcontent 넣어줌 
	private int poldate; //기간
	private int pnum; //모델번호
	private int polprice; //렌탈비
	private int ostatus; //주문 status (방문점검인지 자가점검인지)
	
	//외래키 테이블
	private String tcontent;
	private String pname;
	
	public OrderDTO(int onum, String pname, String vdate, int poldate, int ostatus, String tcontent) {
		super();
		this.onum = onum;
		this.pname = pname;
		this.vdate = vdate;
		this.poldate = poldate;
		this.ostatus = ostatus;
		this.tcontent = tcontent;
	}
	
	public OrderDTO(String vdate, int poldate) {
		super();
		this.vdate = vdate;
		this.poldate = poldate;
	}
}
