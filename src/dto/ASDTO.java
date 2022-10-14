package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ASDTO {
	private int asnum; //as번호
	private int onum; //주문번호
	private String vdate; //방문일  :새로 추가
	private int tnum; //시간번호  :새로 추가
	private int poldate; //기간  :새로추가
	 

}
