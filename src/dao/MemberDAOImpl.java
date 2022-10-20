package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dto.MemberDTO;
import mapper.MemberMapper;

public class MemberDAOImpl {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	MemberMapper membermapper=new MemberMapper();
	

	public MemberDTO MemberLogin() throws IOException{
		System.out.print("아이디를 입력해주세요");
		String mid=br.readLine();
		System.out.print("비밀번호를 입력해주세요");
		String mpw=br.readLine();
		
		MemberDTO member=membermapper.Memberlogin(mid, mpw);
		if(member!=null) {
			if(member.getMstatus()==2) {
				System.out.println("로그인이 완료되었습니다. "+member.getMname()+"님, 환영합니다! \n");
				return member;}
			else{System.out.println("아이디나 비밀번호를 확인해주세요.");}
		}
		return null;
	}
	
	public boolean MemberSignIn() {
		MemberDTO newMember= new MemberDTO();				
		try {
			//아이디 중복체크
			while(true) {
				System.out.print("아이디를 입력해주세요\n");
				String mid=br.readLine();
				if(IdDupCheck(mid)) {System.out.println("중복된 아이디입니다. 다시 한번 확인해주세요");}
				else {newMember.setMid(mid); break;}
			}
			//비밀번호 확인문
			while(true) {
				System.out.print("비밀번호를 입력해주세요\n");
				String mpw=br.readLine();
				System.out.print("비밀번호를 다시 한번 입력해주세요\n");
				String mpwCheck=br.readLine();
				if(mpw.equals(mpwCheck)) {newMember.setMpw(mpwCheck); break;}
				else System.out.println("비밀번호를 확인해주세요."); 				
			}
			System.out.print("이름을 입력해주세요\n");
			newMember.setMname(br.readLine());
			while(true) {				
				System.out.print("전화번호를 입력해주세요\n");
				Pattern pattern = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");
				String mphone=br.readLine();
				Matcher matcher = pattern.matcher(mphone);
				if(matcher.matches()) { newMember.setMphone(mphone); break;}
				else System.out.println("010-XXXX-XXXX 형식에 맞춰 전화번호를 입력해주세요.\n");
			}
			//주소 체크
			while(true) {
				System.out.print("주거하고 계신 구를 입력해주세요. ex)성북구, 강남구\n");
				String mAddr1=br.readLine();
				if(checkAddr(mAddr1)) {newMember.setMaddress1(mAddr1); break;}
				else {System.out.println("구를 정확하게 입력해주세요.\n");}
			}
			System.out.print("상세주소를 입력해주세요\n");
			newMember.setMaddress2(br.readLine());
			
			//System.out.println("값이 잘 입력되나 테스트"+newMember.toString() );
			//잘 입력되면 데이터베이스에 넣어주자
			if(membermapper.MembeSignIn(newMember)) System.out.println("회원가입이 완료되었습니다!");
			else System.out.println("회원가입이 완료되지 않았습니다..");
		} catch (IOException e) {e.printStackTrace();}
		return false;
	}

	
	//아이디 중복체크
	public boolean IdDupCheck(String id) {
		boolean check =membermapper.IdDupCheck(id);
		return check;
	}
	//주소 유효성 검사
	public boolean checkAddr(String address) {
		boolean check =membermapper.checkAddr(address);
		if(!check) System.out.println("올바르지 않은 주소입니다. 서울 주소지의 ' XX구 ' 를 입력해주세요.");
		return check;
	}
	//즈.케어.플러스. 가입했는지 안했는지 체크
	public boolean checkZCP(String mid) {
		return membermapper.checkZCP(mid)>0?true:false;
	}
	//즈.케어.플러스. 가입시켜주기
	public boolean SignInZCP(String mid) {
		return membermapper.SignInZCP(mid)>0?true:false;
	}
	//즈.케어.플러스. 탈퇴시키기
	public boolean DeleteZCP(String mid) {
		return membermapper.DeleteZCP(mid)>0?true:false;
	}
	
	//회원정보 수정해주기 (컬럼값, 변경할 값 받아와서 바꿔줄거임)
	public boolean UpdateMember(String mid) {
		boolean check=false;
		String column=null;
		try {
			System.out.println("----------------------------------");
			System.out.println("1. 비밀번호 2. 전화번호 3. XX구  4.상세주소 ");
			System.out.println("----------------------------------");
			System.out.print("변경하실 회원정보 번호를 입력해주세요");
			String num=br.readLine();
			System.out.print("변경하고싶은 정보를 입력해주세요");
			String data=br.readLine();
			switch(num){
			case "1":
				column="mpw";
				break;
			case "2":
				column="mphone";
				break;
			case "3":
				while(true) {
					if(checkAddr(data)) {column="maddress1"; break;}
					else {System.out.println("구를 정확하게 입력해주세요. ex)성북구, 강남구\n");
						data=br.readLine();
					}
				}
				break;
			case "4":
				column="maddress2";
				break;
			default:
				System.out.println("올바른 값을 입력해주세요..");
				
			}
			System.out.print("비밀번호를 입력해주세요");
			String mpw=br.readLine();
			check=membermapper.UpdateMember(column, data, mid,mpw)>0?true:false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return check;
	}
	
	//회원탈퇴
	public boolean DeleteMember(String mid) {
		boolean check=false;
		try {
			System.out.print("비밀번호를 입력해주세요\n");
			String mpw=br.readLine();
			System.out.print("비밀번호를 다시 한번 입력해주세요\n");
			String mpwCheck=br.readLine();
			if(mpw.equals(mpwCheck)) {
				check=membermapper.deleteMember(mid, mpwCheck)>0?true:false;				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return check;
	}
	
}
