package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeDTO {
	private int tnum; //시간번호 ex) 1, 2 , 3 
	private String tcontent; //내용 10:00~12:00, 14:00~16:00, 17:00~19:00 ) 
}
