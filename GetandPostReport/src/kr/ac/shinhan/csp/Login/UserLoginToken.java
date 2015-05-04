package kr.ac.shinhan.csp.Login;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable(identityType = IdentityType.APPLICATION) 
public class UserLoginToken { //사용기한을 체크했을때 이 클래스에 정보를 넣어줌
	@PrimaryKey 
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) //아 저장되는 필드구나 
	private Long key; //프라이머리 
	 	 
	@Persistent 
	private String token; //쿠키에 넣어줄 토큰
	 	 
	@Persistent 
	private String userAccount; // userId
	 	 
	@Persistent 
	private String expireDate; //사용기한설정
	public UserLoginToken(String token, String userAccount, String expireDate) {
		super();
		this.token = token;
		this.userAccount = userAccount;
		this.expireDate = expireDate;
	}
	/*사용기한이 체크 되고 로그인을 하면
	위에 멤버변수에 다 각자 해당변수를 넣어주고
	토큰을 쿠키에 저장한다.
	그리고 다시 브라우저를 끄고 다시 킬때
	쿠키에 저장된 토큰이 있는지 확인하고(앤트리)
	있으면 index.html 없으면 login.html
	
	로그인 누르면 로그인ㅅ블렛에서 비교해주고
	*/
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

}