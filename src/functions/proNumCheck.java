package functions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import dto.ProductDTO;
import mapper.ProductMapper;

public class proNumCheck {
	public boolean productCheck(int num) throws SQLException {
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		list = new ProductMapper().SelectAllProduct();//전체 list 받아오기
		int[] numbers = new int[list.size()];
		for(int i = 0; i<list.size();i++) {
			numbers[i] = list.get(i).getPnum();
		}
		Arrays.sort(numbers);
		boolean check  = Arrays.binarySearch(numbers, num) >= 0;
		//없을 경우 true;
		//있을경우 false 반환
		return check;
		
	}
	

}
