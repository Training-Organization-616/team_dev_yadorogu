package la.bean;

public class ReservationsBean {
	//プロパティ
	private int id;
	private int hotel_id;
	private int customer_id;
	private int persons;
	private String date;
	private String hotel_name;
	private int price;

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
	
	public ReservationsBean(int id, String hotel_name, int price, int persons, String date) {
		this.id = id;
		this.hotel_name = hotel_name;
		this.price = price;
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

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
