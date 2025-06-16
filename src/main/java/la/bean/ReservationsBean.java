package la.bean;

public class ReservationsBean {
	//プロパティ
	private int id;
	private int hotel_id;
	private int customer_id;
	private int persons;
	private String date;

	//コンストラクタ
	public ReservationsBean() {

	}

	public ReservationsBean(int id, int customer_id, int hotel_id, int persons, String date) {
		this.id = id;
		this.customer_id = customer_id;
		this.hotel_id = hotel_id;
		this.persons = persons;
		this.date = date;
	}

	//ゲッターセッター
	public int getId() {
		return id;
	}

	public int getHotel_id() {
		return hotel_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public int getPersons() {
		return persons;
	}

	public String getDate() {
		return date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public void setPersons(int persons) {
		this.persons = persons;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
