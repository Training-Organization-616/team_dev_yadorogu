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
import la.bean.HotelsBean;
import la.bean.ReservationsBean;
import la.dao.DAOException;
import la.dao.ReserveDAO;

@WebServlet("/ReserveServlet")
public class ReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReserveServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			// パラメータの解析
			String action = request.getParameter("action");
			if (action == null || action.length() == 0) {
				// パラメータなしの場合は予約確認画面を表示
				HttpSession session = request.getSession(false);
				if(session == null) {
					request.setAttribute("message", "セッションが切れています。もう一度ログインして下さい。");
					gotoPage(request, response, "login.jsp");
					return;
				}
				CustomersBean customer = (CustomersBean)session.getAttribute("user");
				if(customer == null) {
					request.setAttribute("message", "ログインして下さい。");
					gotoPage(request, response, "login.jsp");
					return;
				}
				int hotel_id = Integer.parseInt(request.getParameter("hotel_id"));
				ReserveDAO dao = new ReserveDAO();
				HotelsBean bean = dao.findByHotel_id(hotel_id);
				request.setAttribute("hotel", bean);
				gotoPage(request, response, "reserve.jsp");

			} else if (action.equals("add")) {
				// 
				String personsString = request.getParameter("persons");
				String date = request.getParameter("date");
				if(personsString == null || personsString.length() == 0 || date == null || date.length() == 0) {
					request.setAttribute("message", "人数とチェックイン日時が入力されていません");
					gotoPage(request, response, "errInternal.jsp");
					return;
				}
				int persons = Integer.parseInt(personsString);
				ReserveDAO dao = new ReserveDAO();
				int hotel_id = Integer.parseInt(request.getParameter("hotel_id"));
				HttpSession session = request.getSession();
				CustomersBean bean = (CustomersBean)session.getAttribute("user");
				dao.addReserve(hotel_id, bean.getId(), persons, date);
				gotoPage(request, response, "reserveCommit.jsp");

			} else if (action.equals("history")) {
				HttpSession session = request.getSession(false);
				if(session == null) {
					request.setAttribute("message", "セッションが切れています。もう一度ログインして下さい。");
					gotoPage(request, response, "login.jsp");
					return;
				}
				CustomersBean customer = (CustomersBean)session.getAttribute("user");
				if(customer == null) {
					request.setAttribute("message", "ログインして下さい。");
					gotoPage(request, response, "login.jsp");
					return;
				}
				ReserveDAO dao = new ReserveDAO();
				List<ReservationsBean> list = dao.findByCustomer_id(customer.getId());
				request.setAttribute("reservation", list);
				gotoPage(request, response, "history.jsp");

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
