package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
  
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private int pnum; //모델번호
	private String pname; //모델명
	private int pcount; //재고
	private int pstatus; // 제품status (제품 삭제했는지 안했는지) : 새로추가한거임
}
