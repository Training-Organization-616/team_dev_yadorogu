package la.bean;

public class ReviewsBean {
	private int id;
	private int hotel_id;
	private int customer_id;
	private int evaluation;
	private String comment;
	private String customer_name;

	public ReviewsBean() {

	}

	public ReviewsBean(int id, int hotel_id, int evaluation, String comment, String customer_name) {
		this.id = id;
		this.hotel_id = hotel_id;
		this.evaluation = evaluation;
		this.comment = comment;
		this.customer_name = customer_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
}
