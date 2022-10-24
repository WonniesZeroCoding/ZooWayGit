package dto;
   
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	private int mnum; //회원번호
	private String mid; //회원아이디
	private String mpw; //회원패스워드
	private String mname; //회원이름
	private String mphone; //회원전화번호
	private String maddress1; //회원주소1
	private String maddress2; //회원주소2
	private int mstatus; //회원상태(탈퇴했는지, 안했는지)
	private int ZOOCAREPLUS; //즈.케어.플러스.
}
