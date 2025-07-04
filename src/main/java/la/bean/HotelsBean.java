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
	private double avgevaluation;
	private int commentCount;

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

	public HotelsBean(int id, String name, int price, String category_name, String checkin, String checkout,
			double avgevaluation, int commentCount) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category_name = category_name;
		this.checkin = checkin;
		this.checkout = checkout;
		this.avgevaluation = avgevaluation;
		this.commentCount = commentCount;
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

	public HotelsBean(int id, String name, String checkin, String checkout, int price, int maxperson,
			String category_name, double avgevaluation) {
		this.id = id;
		this.name = name;
		this.checkin = checkin;
		this.checkout = checkout;
		this.price = price;
		this.maxperson = maxperson;
		this.category_name = category_name;
		this.avgevaluation = avgevaluation;
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

	public double getAvgevaluation() {
		return avgevaluation;
	}

	public void setAvgevaluation(double avgevaluation) {
		this.avgevaluation = avgevaluation;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int count) {
		this.commentCount = count;
	}

}
