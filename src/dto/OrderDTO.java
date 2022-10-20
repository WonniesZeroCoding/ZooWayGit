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
	private String endDate;
	
	private String tcontent; //주문내역 같은거 할떄 tnum 대신에 tcontent 쓴다 
	private String pname;// 주문내역에서 이름 가져올떄 사용(10/20일)
	
	
	public OrderDTO(int onum, String pname, String vdate, int poldate, int ostatus, String tcontent) {// 주문내역에서 이름 가져올떄 사용(10/20일)
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
