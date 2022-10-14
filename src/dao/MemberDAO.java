package dao;

import java.io.IOException;

import dto.MemberDTO;

public interface MemberDAO {
	
	public MemberDTO MemberLogin() throws IOException;
}
