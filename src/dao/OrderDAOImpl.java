package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import dto.MemberDTO;
import dto.OrderDTO;
import mapper.OrderMapper;
import view.MemberOrderPage;

public class OrderDAOImpl {

	OrderMapper orderMapper = new OrderMapper();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


	/* 로그인한 회원의 렌탈현황 목록 조회 */
	public List<OrderDTO> memberOrderList(MemberDTO member) throws IOException {
		System.out.println(member.getMname() + "님의 렌탈 중인 목록 조회");
		List<OrderDTO> memberOrders = orderMapper.memberOrderList(member);
		if (memberOrders.size() != 0) {

			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			System.out.println("주문번호\t\t신청일\t\t방문시간\t\t약정기간\t\t\t제품번호\t\t렌탈비용");
			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			for (int i = 0; i < memberOrders.size(); i++) {
				System.out.println(memberOrders.get(i).getOnum() + "\t\t" + memberOrders.get(i).getVdate() + "\t\t"
						+ memberOrders.get(i).getTcontent() + "\t\t" + memberOrders.get(i).getPoldate() + "\t\t"
						+ memberOrders.get(i).getPnum() + "\t\t" + memberOrders.get(i).getPolprice());
			}
			while (true) {
				System.out.println("원하시는 주문번호를 입력해주세요. 뒤로가려면 0번을 눌러주세요");
				String onum = br.readLine();// 주문번호선택

				if (Integer.parseInt(onum) == 0) {
					break;
				} else if (Integer.parseInt(onum) > 0) {
					//회원번호에 맞는 주문번호인지 체크
					if (memberOrderCheck(member.getMnum(),Integer.parseInt(onum))) {
						//회원이 신청한 주문이 맞으면, memberOrderPage로 이동
						MemberOrderPage memberOrderPage = new MemberOrderPage();
						memberOrderPage.memberOrderPage2(Integer.parseInt(onum));
					break;
					}else {//목록에 있지않은 주문번호 입력시
						System.out.println("잘못된 주문번호 입니다. 다시 한번 확인하세요");
					}
				} else {//문자입력시
					System.out.println("숫자만 입력해주세요.");
				}
			}
		} else {
			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			System.out.println("신청한 렌탈이 없습니다.");
		}
		return memberOrders;

	}
	
	//로그인 회원이 가진 주문번호에 해당하는지 검사
		public boolean memberOrderCheck(int mnum, int onum) {
			boolean check = orderMapper.memberOrderCheck(mnum,onum);
			return check;
			
		}

}
