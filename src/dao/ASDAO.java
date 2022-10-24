package dao;

import java.util.ArrayList;

import dto.ASDTO;
import mapper.ASMapper;

public class ASDAO {
	public void selectAllAS() throws Exception {
		
		ArrayList<ASDTO> list = new ASMapper().selectAllAS();
		System.out.println("✿​━━∞━━∞━━∞━━∞━━✿   렌탈신청목록   ✿​━━∞━━∞━━∞━━∞━━✿");
		System.out.println("AS번호\t주문번호\t방문일\t\t\t기간");
		for(int i = 0;i<list.size();i++) {
			System.out.println(list.get(i).getAsnum()+"\t"+list.get(i).getOnum()+"\t"+list.get(i).getVdate()+"\t\t"+list.get(i).getPoldate());
		}
		System.out.println("✿​━━∞━━∞━━∞━━━∞━━━∞━━━∞━━∞━━∞━━∞━━∞━━∞━━∞━━∞━━━✿");

	}

	

}
