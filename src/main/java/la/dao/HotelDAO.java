package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import la.bean.HotelsBean;

public class HotelDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:yadolog";
	private String user = "student";
	private String pass = "himitu";

	public HotelDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	/*
	 * 宿一覧表示
	 */
	public List<HotelsBean> findAll() throws DAOException {

		// SQL文の作成
		String sql = "select h.id,h.name,h.price,c.name as category_name,h.checkin,h.checkout from hotels h "
				+ "join categories c on h.category_id = c.id;";

		try (
				// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
				// SQLの実行
				ResultSet rs = st.executeQuery();) {
			// 結果の取得および表示
			List<HotelsBean> list = new ArrayList<HotelsBean>();

			// フォーマット指定
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

			//レコード取得
			while (rs.next()) {
				int id = rs.getInt("id");
				String hotel_name = rs.getString("name");
				int price = rs.getInt("price");
				String name = rs.getString("category_name");

				/*
				 * Time型
				 */
				//localtime型で取得
				LocalTime checkinTime = rs.getTime("checkin").toLocalTime();
				LocalTime checkoutTime = rs.getTime("checkout").toLocalTime();
				// 文字列に変換
				String in = checkinTime.format(formatter);
				String out = checkoutTime.format(formatter);

				HotelsBean bean = new HotelsBean(id, hotel_name, price, name, in, out);
				list.add(bean);
			}
			// カテゴリ一覧をListとして返す
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	/*
	 * 宿名チェック
	 */
	public boolean checkHotel(String indexname) throws DAOException {
		// SQL文の作成
		String sql = "SELECT name FROM hotels WHERE name = ?";

		try (
				Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, indexname);
			try (ResultSet rs = st.executeQuery()) {
				return rs.next(); // レコードが1件でもあれば true（＝宿名が存在）
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	/*
	 * 宿の新規登録
	 */
	public int addHotel(String name, int code, int price, String checkin, String checkout, int maxperson)
			throws DAOException {
		String sql = "INSERT INTO hotels ( name, category_id, checkin, checkout, price,maxperson) "
				+ "VALUES( ?,?,?,?,?,?)";
		try (
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			/*
			 * Time型
			 */
			//フォーマットを時間と分の形でlocaltimeに変換にする
			LocalTime in = LocalTime.parse(checkin, DateTimeFormatter.ofPattern("HH:mm"));
			LocalTime out = LocalTime.parse(checkout, DateTimeFormatter.ofPattern("HH:mm"));
			//DBに保存できるTimeに変換
			Time checkinTime = Time.valueOf(in);
			Time checkoutTime = Time.valueOf(out);

			st.setString(1, name);
			st.setInt(2, code);
			st.setTime(3, checkinTime);
			st.setTime(4, checkoutTime);
			st.setInt(5, price);
			st.setInt(6, maxperson);
			// SQLの実行
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	/*
	 * 宿情報表示（宿削除）
	 */
	public List<HotelsBean> findAllHotelsAdmin() throws DAOException {

		// SQL文の作成
		String sql = "select id,name  from hotels";

		try (
				// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
				// SQLの実行
				ResultSet rs = st.executeQuery();) {
			// 結果の取得および表示
			List<HotelsBean> list = new ArrayList<HotelsBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				HotelsBean bean = new HotelsBean(id, name);
				list.add(bean);
			}
			// カテゴリ一覧をListとして返す
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	/*
	 * 宿情報削除
	 */
	public int deleteHotel(int id) throws DAOException {
		//宿情報（子）のデータ削除
		String sql1 = "DELETE FROM reservations WHERE customer_id = ?";
		//宿情報（親）のデータ削除	
		String sql2 = "DELETE FROM hotels WHERE id = ?";
		try (
				// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st1 = con.prepareStatement(sql1);
				PreparedStatement st2 = con.prepareStatement(sql2);) {
			//「?」に代入
			st1.setInt(1, id);
			st2.setInt(1, id);
			// SQLの実行
			st1.executeUpdate();
			int rows2 = st2.executeUpdate();
			return rows2;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	/*
	 * 宿一覧ソート機能
	 */
	public List<HotelsBean> sortByHotels(String sortHotels) throws DAOException {

		// SQL文の作成
		String sql = null;
		//評価高い順
		if (sortHotels.equals("evaluationDesc")) {
			sql = "SELECT \n"
					+ "  h.id,\n"
					+ "  h.name,\n"
					+ "  c.name AS category_name,\n"
					+ "  h.checkin,\n"
					+ "  h.checkout,\n"
					+ "  h.price,\n"
					+ "  r.avg\n"
					+ "FROM hotels h\n"
					+ "LEFT JOIN (\n"
					+ "  SELECT hotel_id, AVG(evaluation) AS avg \n"
					+ "  FROM reviews \n"
					+ "  GROUP BY hotel_id\n"
					+ ") r ON h.id = r.hotel_id\n"
					+ "JOIN categories c ON h.category_id = c.id\n"
					+ "ORDER BY r.avg DESC NULLS LAST";
			//評価低い順
		} else if (sortHotels.equals("evaluationAsc")) {
			sql = "SELECT \n"
					+ "  h.id,\n"
					+ "  h.name,\n"
					+ "  c.name AS category_name,\n"
					+ "  h.checkin,\n"
					+ "  h.checkout,\n"
					+ "  h.price,\n"
					+ "  r.avg\n"
					+ "FROM hotels h\n"
					+ "LEFT JOIN (\n"
					+ "  SELECT hotel_id, AVG(evaluation) AS avg \n"
					+ "  FROM reviews \n"
					+ "  GROUP BY hotel_id\n"
					+ ") r ON h.id = r.hotel_id\n"
					+ "JOIN categories c ON h.category_id = c.id\n"
					+ "ORDER BY r.avg asc NULLS LAST";
			//料金高い順
		} else if (sortHotels.equals("feeDesc")) {
			sql = "SELECT \n"
					+ "  h.id,\n"
					+ "  h.name,\n"
					+ "  c.name AS category_name,\n"
					+ "  h.checkin,\n"
					+ "  h.checkout,\n"
					+ "  h.price,\n"
					+ "  r.avg\n"
					+ "FROM hotels h\n"
					+ "LEFT JOIN (\n"
					+ "  SELECT hotel_id, AVG(evaluation) AS avg \n"
					+ "  FROM reviews \n"
					+ "  GROUP BY hotel_id\n"
					+ ") r ON h.id = r.hotel_id\n"
					+ "JOIN categories c ON h.category_id = c.id\n"
					+ "ORDER BY price desc";
			//料金低い順
		} else if (sortHotels.equals("feeAsc")) {
			sql = "SELECT \n"
					+ "  h.id,\n"
					+ "  h.name,\n"
					+ "  c.name AS category_name,\n"
					+ "  h.checkin,\n"
					+ "  h.checkout,\n"
					+ "  h.price,\n"
					+ "  r.avg\n"
					+ "FROM hotels h\n"
					+ "LEFT JOIN (\n"
					+ "  SELECT hotel_id, AVG(evaluation) AS avg \n"
					+ "  FROM reviews \n"
					+ "  GROUP BY hotel_id\n"
					+ ") r ON h.id = r.hotel_id\n"
					+ "JOIN categories c ON h.category_id = c.id\n"
					+ "ORDER BY price asc";
			//チェックイン時間遅い順
		} else if (sortHotels.equals("checkinDesc")) {
			sql = "SELECT \n"
					+ "  h.id,\n"
					+ "  h.name,\n"
					+ "  c.name AS category_name,\n"
					+ "  h.checkin,\n"
					+ "  h.checkout,\n"
					+ "  h.price,\n"
					+ "  r.avg\n"
					+ "FROM hotels h\n"
					+ "LEFT JOIN (\n"
					+ "  SELECT hotel_id, AVG(evaluation) AS avg \n"
					+ "  FROM reviews \n"
					+ "  GROUP BY hotel_id\n"
					+ ") r ON h.id = r.hotel_id\n"
					+ "JOIN categories c ON h.category_id = c.id\n"
					+ "ORDER BY checkin desc";
			//チェックイン時間早い順
		} else if (sortHotels.equals("checkinAsc")) {
			sql = "SELECT \n"
					+ "  h.id,\n"
					+ "  h.name,\n"
					+ "  c.name AS category_name,\n"
					+ "  h.checkin,\n"
					+ "  h.checkout,\n"
					+ "  h.price,\n"
					+ "  r.avg\n"
					+ "FROM hotels h\n"
					+ "LEFT JOIN (\n"
					+ "  SELECT hotel_id, AVG(evaluation) AS avg \n"
					+ "  FROM reviews \n"
					+ "  GROUP BY hotel_id\n"
					+ ") r ON h.id = r.hotel_id\n"
					+ "JOIN categories c ON h.category_id = c.id\n"
					+ "ORDER BY checkin asc";
		}
		try (
				// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
				// SQLの実行
				ResultSet rs = st.executeQuery();) {
			// 結果の取得および表示
			List<HotelsBean> list = new ArrayList<HotelsBean>();

			// フォーマット指定
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

			//レコード取得
			while (rs.next()) {
				int id = rs.getInt("id");
				String hotel_name = rs.getString("name");
				String name = rs.getString("category_name");
				/*
				 * Time型
				 */
				//localtime型で取得
				LocalTime checkinTime = rs.getTime("checkin").toLocalTime();
				LocalTime checkoutTime = rs.getTime("checkout").toLocalTime();
				// 文字列に変換
				String in = checkinTime.format(formatter);
				String out = checkoutTime.format(formatter);

				int price = rs.getInt("price");
				double ave = rs.getDouble("avg");
				HotelsBean bean = new HotelsBean(id, hotel_name, price, name, in, out, ave);
				list.add(bean);
			}
			// カテゴリ一覧をListとして返す
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
}
