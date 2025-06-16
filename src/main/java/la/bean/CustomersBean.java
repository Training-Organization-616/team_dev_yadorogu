package la.bean;

public class CustomersBean {
	//プロパティ
	private int id;
	private String name;
	private String address;
	private String tel;
	private String email;
	private String birthday;
	private String membershipdate;
	private String password;
	private boolean isAdmin;

	//コンストラクタ
	public CustomersBean() {

	}

	public CustomersBean(int id, String name, boolean isadmin) {
		this.id = id;
		this.name = name;
		this.isAdmin = isadmin;
	}

	public CustomersBean(int id, String name, String address, String tel,
			String email, String birthday, String membershipdate, String password, boolean isadmin) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.birthday = birthday;
		this.membershipdate = membershipdate;
		this.isAdmin = isadmin;
	}

	//ゲッターセッター
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getTel() {
		return tel;
	}

	public String getEmail() {
		return email;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getMembershipdate() {
		return membershipdate;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setMembershipdate(String membershipdate) {
		this.membershipdate = membershipdate;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
