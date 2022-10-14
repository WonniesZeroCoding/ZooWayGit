package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
	private int emnum; //직원번호
	private String ename; //직원이름 :추가
	private String eid; //직원아이디
	private String epw; //직원비밀번호
	private String ephone; //직원전화번호
	private String eplace; //담당구역
	private int estatus; //직원 status (0:퇴사 1:기사  2: 관리자)
}
