package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import la.bean.CustomersBean;

public class CustomerDAO {

	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:yadolog";
	private String user = "student";
	private String pass = "himitu";

	public CustomerDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	//　同じemailの人がいるか検索
	public boolean findByEmail(String email) throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM customers WHERE email = ?";
		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, email);

			try (
					// SQLの実行
					ResultSet rs = st.executeQuery();) {
				// 結果の取得
				boolean isExist = false;
				if (rs.next()) {
					isExist = true;
				}
				// カテゴリ一覧をListとして返す
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

	// 会員情報を追加
	public void addCustomer(String customrName, String address, String tel, String email, String birthday,
			String password) throws DAOException {
		// SQL文の作成
		String sql = "INSERT INTO customers (name, address, tel, email, birthday, membershipdate, password, isadmin) VALUES(?, ?, ?, ?, ?::DATE, CURRENT_DATE, ?, TRUE)";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, customrName);
			st.setString(2, address);
			st.setString(3, tel);
			st.setString(4, email);
			st.setString(5, birthday);
			st.setString(6, password);
			// SQLの実行
			st.executeUpdate();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	// 会員情報を変更
	public void updateCustomer(CustomersBean bean) throws DAOException {
		// SQL文の作成
		String sql = "UPDATE customers SET name=?, address=?, tel=?, email=?, birthday=?::DATE, password=?  WHERE id=?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, bean.getName());
			st.setString(2, bean.getAddress());
			st.setString(3, bean.getTel());
			st.setString(4, bean.getEmail());
			st.setString(5, bean.getBirthday());
			st.setString(6, bean.getPassword());
			st.setInt(7, bean.getId());
			// SQLの実行
			st.executeUpdate();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	// 会員情報を削除
	public int deleteCustomer(int id) throws DAOException {
		String sql1 = "DELETE FROM reservations WHERE customer_id = ?";
		String sql2 = "DELETE FROM customers WHERE id = ?";
		try (
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st1 = con.prepareStatement(sql1);
				PreparedStatement st2 = con.prepareStatement(sql2);) {
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
}
