package kr.ac.shinhan.csp.Login;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable(identityType = IdentityType.APPLICATION) 
public class UserLoginToken { //�������� üũ������ �� Ŭ������ ������ �־���
	@PrimaryKey 
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) //�� ����Ǵ� �ʵ屸�� 
	private Long key; //�����̸Ӹ� 
	 	 
	@Persistent 
	private String token; //��Ű�� �־��� ��ū
	 	 
	@Persistent 
	private String userAccount; // userId
	 	 
	@Persistent 
	private String expireDate; //�����Ѽ���
	public UserLoginToken(String token, String userAccount, String expireDate) {
		super();
		this.token = token;
		this.userAccount = userAccount;
		this.expireDate = expireDate;
	}
	/*�������� üũ �ǰ� �α����� �ϸ�
	���� ��������� �� ���� �ش纯���� �־��ְ�
	��ū�� ��Ű�� �����Ѵ�.
	�׸��� �ٽ� �������� ���� �ٽ� ų��
	��Ű�� ����� ��ū�� �ִ��� Ȯ���ϰ�(��Ʈ��)
	������ index.html ������ login.html
	
	�α��� ������ �α��Τ������� �����ְ�
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