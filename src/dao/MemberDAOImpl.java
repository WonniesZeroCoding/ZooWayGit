package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dto.MemberDTO;
import mapper.MemberMapper;

public class MemberDAOImpl implements MemberDAO {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	MemberMapper membermapper=new MemberMapper();
	
	//일반 회원 로그인
	public MemberDTO MemberLogin() throws IOException{
		System.out.print("아이디를 입력해주세요");
		String mid=br.readLine();
		System.out.print("비밀번호를 입력해주세요");
		String mpw=br.readLine();
		System.out.println("아이디2: "+mid+" 비밀번호2: "+mpw);
		
		MemberDTO member=membermapper.Memberlogin(mid, mpw);
		if(member!=null) {
			System.out.println("로그인이 완료되었습니다. "+member.getMname()+"님, 환영합니다! \n");
			return member;
		}
		return null;
	}
}
