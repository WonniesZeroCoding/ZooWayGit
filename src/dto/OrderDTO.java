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
 //시간번호 :새로 추가한거  실제로는 int tnum을 입력하지만 서브쿼리써서 DTO에는 tcontent 넣어줌 
	private int poldate; //기간
	private int pnum; //모델번호
	private int polprice; //렌탈비
	private int ostatus; //주문 status (방문점검인지 자가점검인지)
	
	//외래키 테이블

	private String pname;
	private String endDate;
	private int endDateCount;
	private int maddress;
	private String address;
	private int zcp;
	
	
	
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

	public OrderDTO(int onum, int mnum, String vdate,  int poldate, int pnum, int polprice, int ostatus,
			String endDate,int endDateCount) {
		super();
		this.onum = onum;
		this.mnum = mnum;
		this.vdate = vdate;
		this.poldate = poldate;
		this.pnum = pnum;
		this.polprice = polprice;
		this.ostatus = ostatus;
		this.endDate = endDate;
		this.endDateCount = endDateCount;
	}

	public OrderDTO(int onum, int mnum, String vdate,int poldate, int pnum, int polprice, int ostatus,
			String endDate, int maddress, String address, int zcp) {
		super();
		this.onum = onum;
		this.mnum = mnum;
		this.vdate = vdate;
		this.poldate = poldate;
		this.pnum = pnum;
		this.polprice = polprice;
		this.ostatus = ostatus;
		this.endDate = endDate;
		this.maddress = maddress;
		this.address = address;
		this.zcp = zcp;
	}

	public OrderDTO(int zcp, int maddress, int onum, int mnum, String vdate,int poldate, int pnum,
			int polprice, int ostatus, String endDate) {
		super();
		this.zcp = zcp;
		this.maddress = maddress;
		this.onum = onum;
		this.mnum = mnum;
		this.vdate = vdate;
	
		this.poldate = poldate;
		this.pnum = pnum;
		this.polprice = polprice;
		this.ostatus = ostatus;
		this.endDate = endDate;
	}

	
	
}
