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
			//レコード取得
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				boolean isadmin = rs.getBoolean("isadmin");
				//会員一覧表示のためのコンストラクタ
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
		//宿情報（子）のデータ削除
		String sql1 = "DELETE FROM reviews WHERE customer_id = ?";
		//予約情報（子）のデータ削除
		String sql2 = "DELETE FROM reservations WHERE customer_id = ?";
		//予約情報（親）のデータ削除
		String sql3 = "DELETE FROM customers WHERE id = ?";
		try (
				// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st1 = con.prepareStatement(sql1);
				PreparedStatement st2 = con.prepareStatement(sql2);
				PreparedStatement st3 = con.prepareStatement(sql3);) {
			//「?」に代入
			st1.setInt(1, id);
			st2.setInt(1, id);
			st3.setInt(1, id);
			// SQLの実行
			st1.executeUpdate();
			st2.executeUpdate();
			int rows3 = st3.executeUpdate();
			return rows3;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	/*
	 * 会員の権限変更
	 */
	public int updateCustomer(int id) throws DAOException {
		String sql = "update customers set isadmin=false where id=?";
		try (
				// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			//「?」に代入
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
	 * 会員の検索
	 */
	public List<CustomersBean> searchCustomer(int index) throws DAOException {

		// SQL文の作成
		String sql = "select id, name,isadmin from customers where id=? ";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, index);
			// SQLの実行
			try (
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
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
}
