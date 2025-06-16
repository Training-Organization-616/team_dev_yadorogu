package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import la.bean.CustomersBean;

public class LoginDAO {
	private String url = "jdbc:postgresql:yadolog";
	private String user = "student";
	private String pass = "himitu";

	public LoginDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	public CustomersBean login(String e_mail, String password) throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM  customers where email=? and password=?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// SQLの実行
			st.setString(1, e_mail);
			st.setString(2, password);
			try (ResultSet rs = st.executeQuery();) {
				// 結果の取得および表示
				if (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String address = rs.getString("address");
					String tel = rs.getString("tel");
					String email = rs.getString("email");
					String birthday = rs.getString("birthday");
					String membershipdate = rs.getString("membershipdate");
					String pass = rs.getString("password");
					boolean isadmin = rs.getBoolean("isadmin");

					CustomersBean bean = new CustomersBean(id, name, address, tel, email, birthday, membershipdate,
							pass, isadmin);
					return bean;
				} else {
					return null;
				}

				// カテゴリ一覧をListとして返す

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
