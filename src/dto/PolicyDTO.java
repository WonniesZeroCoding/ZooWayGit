package dto;
  
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyDTO {
	//렌탈 기간에 따른
	private int poldate; // 기간  ex) 6(개월), 12, 36
	private int polprice; // 가격  ex) 25000, 21000, 18000
}
