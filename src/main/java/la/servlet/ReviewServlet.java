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
import la.bean.ReviewsBean;
import la.dao.DAOException;
import la.dao.ReviewDAO;

@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReviewServlet() {
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
				int hotel_id;
				if(session == null || session.getAttribute("hotel_id") == null) {
					hotel_id = Integer.parseInt(request.getParameter("hotel_id"));
				} else {
					hotel_id = Integer.parseInt(session.getAttribute("hotel_id").toString());
					session.removeAttribute("hotel_id");
				}
				// 宿情報の取得
				ReviewDAO dao = new ReviewDAO();
				HotelsBean bean = dao.findHotelByHotel_id(hotel_id);
				request.setAttribute("hotel", bean);
				List<ReviewsBean> list = dao.findReviewsByHotel_id(hotel_id);
				request.setAttribute("reviews", list);
				gotoPage(request, response, "detailHotel.jsp");

			} else if (action.equals("add")) {
				// 宿情報の取得
				// セッションが切れていないかチェック
				HttpSession session = request.getSession(false);
				if(session == null) {
					request.setAttribute("message", "セッションが切れています。もう一度ログインして下さい。");
					gotoPage(request, response, "login.jsp");
					return;
				}
				CustomersBean customer = (CustomersBean)session.getAttribute("user");
				// ログインしているかチェック
				if(customer == null) {
					request.setAttribute("message", "ログインして下さい。");
					gotoPage(request, response, "login.jsp");
					return;
				}
				int hotel_id = Integer.parseInt(request.getParameter("hotel_id"));
				ReviewDAO dao = new ReviewDAO();
				HotelsBean bean = dao.findHotelByHotel_id(hotel_id);
				request.setAttribute("hotel", bean);
				
				gotoPage(request, response, "review.jsp");

			} else if (action.equals("addReview")) {
				// 予約一覧画面を表示
				// セッションが切れていないかチェック
				HttpSession session = request.getSession(false);
				if(session == null) {
					request.setAttribute("message", "セッションが切れています。もう一度ログインして下さい。");
					gotoPage(request, response, "login.jsp");
					return;
				}
				CustomersBean customer = (CustomersBean)session.getAttribute("user");
				int hotel_id = Integer.parseInt(request.getParameter("hotel_id"));
				int evaluation = Integer.parseInt(request.getParameter("evaluation"));
				String comment = request.getParameter("comment");
				ReviewDAO dao = new ReviewDAO();
				dao.addReserve(hotel_id, customer.getId(), evaluation, comment);
				session.setAttribute("hotel_id", hotel_id);
				response.sendRedirect("ReviewServlet");

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
