package la.bean;

public class HotelsBean {
	private int id;
	private String name;
	private int category_id;
	private String checkin;
	private String checkout;
	private int price;
	private int maxperson;
	private String category_name;

	public HotelsBean(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public HotelsBean(int id, String name, int price, String category_name, String checkin, String checkout) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category_name = category_name;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public HotelsBean() {
	}

	public HotelsBean(String name, int price, int category_id, String checkin, String checkout) {
		this.name = name;
		this.price = price;
		this.category_id = category_id;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public HotelsBean(int id, String name, int category_id, String checkin, String checkout, int price, int maxperson) {
		this.id = id;
		this.name = name;
		this.category_id = category_id;
		this.checkin = checkin;
		this.checkout = checkout;
		this.price = price;
		this.maxperson = maxperson;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMaxperson() {
		return maxperson;
	}

	public void setMaxperson(int maxperson) {
		this.maxperson = maxperson;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
}
