package functions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import dto.MemberDTO;
import dto.ProductDTO;
import mapper.MemberMapper;
import mapper.ProductMapper;

public class memNumCheck {
	public boolean memberCheck(int num) throws SQLException {
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		list = new MemberMapper().selectAllMemberAdmin();//전체 list 받아오기
		int[] numbers = new int[list.size()];
		for(int i = 0; i<list.size();i++) {
			numbers[i] = list.get(i).getMnum();
		}
		Arrays.sort(numbers);
		boolean check  = Arrays.binarySearch(numbers, num) >= 0;
		//없을 경우 true;
		//있을경우 false 반환
		return check;
		
	}
	
}
