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
	private int poldate; //기간
	private int pnum; //모델번호
	private int polprice; //렌탈비
	private int ostatus; //주문 status (방문점검인지 자가점검인지)
	private String endDate;//약정 해지 예정일
	

	//외래키 테이블
	private String pname;
	
	public OrderDTO(int onum, String pname, String vdate, int poldate, int ostatus) {
		super();
		this.onum = onum;
		this.pname = pname;
		this.vdate = vdate;
		this.poldate = poldate;
		this.ostatus = ostatus;
	}
	
	public OrderDTO(String vdate, int poldate) {
		super();
		this.vdate = vdate;
		this.poldate = poldate;
	}
}
