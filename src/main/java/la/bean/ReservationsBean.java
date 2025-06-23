package la.bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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

	public boolean ischeckday() {
		LocalDate day = LocalDate.now();
		LocalDate twoday = day.plusDays(1);
		LocalDate test = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return test.isAfter(twoday);
	}

	public int getCheck() {
		int cs = 100;
		//現在の日付の取得
		LocalDate day = LocalDate.now();
		//予約日をdate型に直す
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate resdate = LocalDate.parse(date, formatter);
		long daysBetween = ChronoUnit.DAYS.between(day, resdate);

		if (daysBetween <= 3) {
			cs = 50;
		} else if (daysBetween <= 7) {
			cs = 20;
		} else {
			cs = 0;
		}

		return cs;
	}

}
