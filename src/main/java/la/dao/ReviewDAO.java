package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import la.bean.HotelsBean;
import la.bean.ReviewsBean;

public class ReviewDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:yadolog";
	private String user = "student";
	private String pass = "himitu";

	public ReviewDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	//　ホテルをIDで検索
	public HotelsBean findHotelByHotel_id(int hotel_id) throws DAOException {
		// SQL文の作成
		String sql = "SELECT h.id, h.name, h.checkin, h.checkout, h.price, h.maxperson, c.name AS category_name, r.avg FROM hotels h JOIN categories c ON h.category_id = c.id LEFT JOIN (SELECT hotel_id, round(avg(evaluation),1) as avg FROM reviews GROUP BY hotel_id) r ON h.id = r.hotel_id WHERE h.id = ? ORDER BY h.id;";
		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setInt(1, hotel_id);

			try (
					// SQLの実行
					ResultSet rs = st.executeQuery();) {
				// 結果の取得および表示
				HotelsBean bean = new HotelsBean();
				// フォーマット指定
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
				if (rs.next()) {
					String name = rs.getString("name");
					//localtime型で取得
					LocalTime checkinTime = rs.getTime("checkin").toLocalTime();
					LocalTime checkoutTime = rs.getTime("checkout").toLocalTime();
					// 文字列に変換
					String checkin = checkinTime.format(formatter);
					String checkout = checkoutTime.format(formatter);
					int price = rs.getInt("price");
					int maxperson = rs.getInt("maxperson");
					String category_name = rs.getString("category_name");
					double avgevaluation = rs.getDouble("avg");
					bean = new HotelsBean(hotel_id, name, checkin, checkout, price, maxperson, category_name,
							avgevaluation);
				}
				// ホテル情報を返す
				return bean;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	//　宿の口コミを検索
	public List<ReviewsBean> findReviewsByHotel_id(int hotel_id) throws DAOException {
		// SQL文の作成
		String sql = "SELECT r.id, r.evaluation, r.comment, c.name FROM reviews r JOIN customers c ON r.customer_id = c.id WHERE hotel_id = ? ORDER BY r.id";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setInt(1, hotel_id);

			try (
					// SQLの実行
					ResultSet rs = st.executeQuery();) {
				// 結果の取得および表示
				List<ReviewsBean> list = new ArrayList<ReviewsBean>();
				while (rs.next()) {
					int id = rs.getInt("id");
					int evaluation = rs.getInt("evaluation");
					String comment = rs.getString("comment");
					String customer_name = rs.getString("name");
					ReviewsBean bean = new ReviewsBean(id, hotel_id, evaluation, comment, customer_name);
					list.add(bean);
				}
				// 予約履歴をListとして返す
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
	
	// 口コミ情報を追加
	public void addReserve(int hotel_id, int customer_id, int evaluation, String comment) throws DAOException {
		// SQL文の作成
		String sql = "INSERT INTO reviews (hotel_id, customer_id, evaluation, comment) VALUES(?, ?, ?, ?)";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setInt(1, hotel_id);
			st.setInt(2, customer_id);
			st.setInt(3, evaluation);
			st.setString(4, comment);
			// SQLの実行
			st.executeUpdate();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
}
