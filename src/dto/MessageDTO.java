package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
	private int mesnum; //메세지 번호
	private int emnum; //직원번호
	private String mescontent; //내용
	private String mesdate; //보낸날짜
	private int mnum; //회원번호
}
