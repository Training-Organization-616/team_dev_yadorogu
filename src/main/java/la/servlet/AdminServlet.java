package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.CustomersBean;
import la.dao.AdminDAO;
import la.dao.DAOException;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String action = request.getParameter("action");

			/*
			 *会員すべて表示
			 */
			if (action == null || action.length() == 0) {
				//現在のセッションを取得
				HttpSession session = request.getSession(false);
				CustomersBean bean = (CustomersBean) session.getAttribute("user");

				AdminDAO dao = new AdminDAO();
				List<CustomersBean> list = dao.findAll();
				request.setAttribute("customers", list);
				session.setAttribute("user", bean);
				gotoPage(request, response, "/admintop.jsp");

				/*
				 * 会員の退会
				 */
			} else if (action.equals("delete")) {
				int id = Integer.parseInt(request.getParameter("id"));
				AdminDAO dao = new AdminDAO();
				dao.deleteCustomer(id);
				List<CustomersBean> list = dao.findAll();
				request.setAttribute("customers", list);
				gotoPage(request, response, "/admintop.jsp");

				/*
				 * 会員の状態を変更（管理者に）
				 */
			} else if (action.equals("update")) {
				int id = Integer.parseInt(request.getParameter("id"));
				AdminDAO dao = new AdminDAO();
				dao.updateCustomer(id);
				List<CustomersBean> list = dao.findAll();
				request.setAttribute("customers", list);
				gotoPage(request, response, "/admintop.jsp");

				/*
				 * 会員検索
				 */
			} else if (action.equals("search")) {
				int searchAdmin = Integer.parseInt(request.getParameter("searchAdmin"));
				AdminDAO dao = new AdminDAO();
				List<CustomersBean> list = dao.searchCustomer(searchAdmin);
				request.setAttribute("customers", list);
				gotoPage(request, response, "/admintop.jsp");
			}
		} catch (

		DAOException e) {
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
