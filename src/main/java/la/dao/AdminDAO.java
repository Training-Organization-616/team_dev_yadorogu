package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.CustomersBean;

public class AdminDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:yadolog";
	private String user = "student";
	private String pass = "himitu";

	public AdminDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	/*
	 * 会員一覧表示
	 */
	public List<CustomersBean> findAll() throws DAOException {

		// SQL文の作成
		String sql = "select id, name,isadmin from customers order by id ";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
				// SQLの実行
				ResultSet rs = st.executeQuery();) {
			// 結果の取得および表示
			List<CustomersBean> list = new ArrayList<CustomersBean>();
			// フォーマット指定
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				boolean isadmin = rs.getBoolean("isadmin");

				CustomersBean bean = new CustomersBean(id, name, isadmin);
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
	 * 会員の削除
	 */
	public int deleteCustomer(int id) throws DAOException {
		String sql = "DELETE FROM customers WHERE id = ?";
		try (
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, id);
			// SQLの実行
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	/*
	 * 管理者の権限変更
	 */
	public int updateCustomer(int id, boolean admin) throws DAOException {
		String sql = "update customers set isadmin=false where id=?";
		try (
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, id);
			// SQLの実行
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}
}
