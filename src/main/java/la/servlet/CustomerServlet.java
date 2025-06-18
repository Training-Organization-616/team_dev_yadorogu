package la.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.CustomersBean;
import la.dao.CustomerDAO;
import la.dao.DAOException;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 今日の日付を取得
		LocalDate today = LocalDate.now();

		try {
			// パラメータの解析
			String action = request.getParameter("action");
			if (action == null || action.length() == 0) {
				// パラメータなしの場合は予約確認画面を表示
				// 今日の日付を送る
				request.setAttribute("today", today);
				gotoPage(request, response, "addCustomer.jsp");

			} else if (action.equals("add")) {
				// 
				String customerName = request.getParameter("customerName");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				String email = request.getParameter("email");
				String birthday = request.getParameter("birthday");
				String password = request.getParameter("password");
				String passwordCheck = request.getParameter("passwordCheck");
				if (customerName == null || customerName.length() == 0 || address == null || address.length() == 0 ||
						tel == null || tel.length() == 0 || email == null || email.length() == 0 ||
						birthday == null || birthday.length() == 0 || password == null || password.length() == 0 ||
						passwordCheck == null || passwordCheck.length() == 0) {
					request.setAttribute("message", "入力されていない項目があります");
					// 今日の日付を送る
					request.setAttribute("today", today);
					gotoPage(request, response, "addCustomer.jsp");
					return;
				}
				CustomerDAO dao = new CustomerDAO();
				if (dao.findByEmail(email)) {
					request.setAttribute("message", "このメールアドレスはすでに使用されています");
					// 今日の日付を送る
					request.setAttribute("today", today);
					gotoPage(request, response, "addCustomer.jsp");
					return;
				}
				if(password.contains("\s")) {
					request.setAttribute("message", "パスワードに空白が含まれています");
					// 今日の日付を送る
					request.setAttribute("today", today);
					gotoPage(request, response, "addCustomer.jsp");
					return;
				}
				if (!password.equals(passwordCheck)) {
					request.setAttribute("message", "パスワードが一致しません");
					// 今日の日付を送る
					request.setAttribute("today", today);
					gotoPage(request, response, "addCustomer.jsp");
					return;
				}
				dao.addCustomer(customerName, address, tel, email, birthday, password);
				request.setAttribute("message", "新規登録が完了しました。ログインしてください。");
				gotoPage(request, response, "/login.jsp");

			} else if (action.equals("update")) {
				// 
				// 今日の日付を送る
				request.setAttribute("today", today);
				gotoPage(request, response, "updateCustomer.jsp");

			} else if (action.equals("updateCustomer")) {
				// 
				HttpSession session = request.getSession(false);
				if (session == null) {
					request.setAttribute("message", "セッションが切れています。もう一度ログインして下さい。");
					gotoPage(request, response, "login.jsp");
					return;
				}
				String customerName = request.getParameter("customerName").strip();
				String address = request.getParameter("address").strip();
				String tel = request.getParameter("tel").strip();
				String email = request.getParameter("email").strip();
				String birthday = request.getParameter("birthday").strip();
				String password = request.getParameter("password");
				String passwordCheck = request.getParameter("passwordCheck");
				if (customerName == null || customerName.length() == 0 || address == null || address.length() == 0 ||
						tel == null || tel.length() == 0 || email == null || email.length() == 0 ||
						birthday == null || birthday.length() == 0 || password == null || password.length() == 0 ||
						passwordCheck == null || passwordCheck.length() == 0) {
					request.setAttribute("message", "入力されていない項目があります");
					// 今日の日付を送る
					request.setAttribute("today", today);
					gotoPage(request, response, "updateCustomer.jsp");
					return;
				}
				CustomerDAO dao = new CustomerDAO();
				if (dao.findByEmail(email)) {
					request.setAttribute("message", "このメールアドレスはすでに使用されています");
					// 今日の日付を送る
					request.setAttribute("today", today);
					gotoPage(request, response, "updateCustomer.jsp");
					return;
				}
				if(password.contains("\s")) {
					request.setAttribute("message", "パスワードに空白が含まれています");
					// 今日の日付を送る
					request.setAttribute("today", today);
					gotoPage(request, response, "updateCustomer.jsp");
					return;
				}
				if (!password.equals(passwordCheck)) {
					request.setAttribute("message", "パスワードが一致しません");
					// 今日の日付を送る
					request.setAttribute("today", today);
					gotoPage(request, response, "updateCustomer.jsp");
					return;
				}	
				CustomersBean customer = (CustomersBean) session.getAttribute("user");
				CustomersBean bean = new CustomersBean(customer.getId(), customerName, address, tel, email, birthday,
						customer.getMembershipdate(), password, customer.isAdmin());
				dao.updateCustomer(bean);
				session.setAttribute("user", bean);
				if (customer.isAdmin()) {
					response.sendRedirect("HotelServlet");
				} else {
					response.sendRedirect("AdminServlet");
				}

			} else if (action.equals("delete")) {
				// 
				HttpSession session = request.getSession(false);
				if (session == null) {
					request.setAttribute("message", "セッションが切れています。もう一度ログインして下さい。");
					gotoPage(request, response, "login.jsp");
					return;
				}
				CustomersBean customer = (CustomersBean) session.getAttribute("user");
				CustomerDAO dao = new CustomerDAO();
				dao.deleteCustomer(customer.getId());
				session.invalidate();
				response.sendRedirect("HotelServlet");

			} else {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} catch (DAOException e) {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
