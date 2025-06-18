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
import la.bean.ReservationsBean;

public class ReserveDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:yadolog";
	private String user = "student";
	private String pass = "himitu";

	public ReserveDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	//　ホテルをIDで検索
	public HotelsBean findByHotel_id(int hotel_id) throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM hotels WHERE id = ?";
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
					int category_id = rs.getInt("category_id");
					//localtime型で取得
					LocalTime checkinTime = rs.getTime("checkin").toLocalTime();
					LocalTime checkoutTime = rs.getTime("checkout").toLocalTime();
					// 文字列に変換
					String checkin = checkinTime.format(formatter);
					String checkout = checkoutTime.format(formatter);
					int price = rs.getInt("price");
					int maxperson = rs.getInt("maxperson");
					bean = new HotelsBean(hotel_id, name, category_id, checkin, checkout, price, maxperson);
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

	//　会員の予約履歴を検索
	public List<ReservationsBean> findByCustomer_id(int customer_id) throws DAOException {
		// SQL文の作成
		String sql = "SELECT r.id, h.name, h.price, r.persons, r.date FROM reservations r JOIN hotels h ON r.hotel_id = h.id WHERE r.customer_id = ? ORDER BY id DESC LIMIT 5";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setInt(1, customer_id);

			try (
					// SQLの実行
					ResultSet rs = st.executeQuery();) {
				// 結果の取得および表示
				List<ReservationsBean> list = new ArrayList<ReservationsBean>();
				while (rs.next()) {
					int id = rs.getInt("id");
					String hotel_name = rs.getString("name");
					int price = rs.getInt("price");
					int persons = rs.getInt("persons");
					String date = rs.getString("date");
					ReservationsBean bean = new ReservationsBean(id, hotel_name, price, persons, date);
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
	
	// 同じ日に予約しているか検索
		public boolean findByCustomer_idAndDate(int customer_id, String date) throws DAOException {
			// SQL文の作成
			String sql = "SELECT * FROM reservations WHERE customer_id=? AND date=?::DATE";

			try (// データベースへの接続
					Connection con = DriverManager.getConnection(url, user, pass);
					// PreparedStatementオブジェクトの取得
					PreparedStatement st = con.prepareStatement(sql);) {

				st.setInt(1, customer_id);
				st.setString(2, date);

				try (
						// SQLの実行
						ResultSet rs = st.executeQuery();) {
					// 結果の取得
					boolean isExist = false;
					if (rs.next()) {
						isExist = true;
					}
					// 結果を返す
					return isExist;
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DAOException("レコードの取得に失敗しました。");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		}

	// 予約情報を追加
	public void addReserve(int hotel_id, int customer_id, int persons, String date) throws DAOException {
		// SQL文の作成
		String sql = "INSERT INTO reservations (hotel_id, customer_id, persons, date) VALUES(?, ?, ?, ?::DATE)";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setInt(1, hotel_id);
			st.setInt(2, customer_id);
			st.setInt(3, persons);
			st.setString(4, date);
			// SQLの実行
			st.executeUpdate();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
	
	
}
