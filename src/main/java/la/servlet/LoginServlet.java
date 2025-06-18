package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.CustomersBean;
import la.dao.DAOException;
import la.dao.LoginDAO;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		try {
			String action = request.getParameter("action");
			HttpSession session = request.getSession();

			//アクションがNULLならログインページを表示
			if (action.equals(null) || action.length() == 0) {
				gotoPage(request, response, "/login.jsp");
			}
			//アクションがloginならログイン処理を実行
			else if (action.equals("login")) {
				String email = request.getParameter("email").strip();
				String password = request.getParameter("password").strip();
				if (email.equals(null) || email.length() == 0 || password.equals(null) || password.length() == 0) {
					request.setAttribute("message", "メールアドレスとパスワードを入力してください");
					gotoPage(request, response, "/login.jsp");
					return;
				}
				LoginDAO dao = new LoginDAO();
				CustomersBean bean = dao.login(email, password);
				if (bean == null) {
					request.setAttribute("message", "メールアドレス又はパスワードが一致しません");
					gotoPage(request, response, "/login.jsp");
					return;
				}
				session.setAttribute("user", bean);
				if (bean.isAdmin()) {
					response.sendRedirect("HotelServlet");
				} else {
					response.sendRedirect("AdminServlet");
				}
			}
			//アクションがlogoutならログアウト処理を実行
			else if (action.equals("logout")) {
				session.invalidate();
				response.sendRedirect("HotelServlet");
			}

		} catch (DAOException e) {

			// DAOのDB処理が失敗（エラー）となった場合
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
