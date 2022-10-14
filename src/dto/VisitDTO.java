package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitDTO {
	private int vnum; //방문번호
	private int mnum; //회원번호  : 추가
	private int emnum; //직원번호
	private int onum; //주문번호
	private int asnum; //as번호
	private String vdate; //방문일
	private int tnum; //시간번호 
	private int vstatus; //방문status (방문했는지,안했는지)
}
