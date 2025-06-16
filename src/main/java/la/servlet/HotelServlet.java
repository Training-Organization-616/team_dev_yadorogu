package la.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import la.bean.TaskBean;
//import la.bean.UserBean;
//import la.dao.DAOException;
//import la.dao.TaskDAO;

@WebServlet("/HotelServlet")
public class HotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HotelServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//		//日本語形式に変更
		//		request.setCharacterEncoding("UTF-8");
		//		try {
		//			String action = request.getParameter("action");
		//			/*
		//			 *タスクすべて表示
		//			 */
		//			if (action == null || action.length() == 0) {
		//				serBean info = (UserBean) session.getAttribute("info");
		//				if (info == null) {
		//					request.setAttribute("message", "セッション情報が失われました。");
		//					gotoPage(request, response, "/errInternal.jsp");
		//					return;
		//				}
		//				TaskDAO dao = new TaskDAO();
		//				List<TaskBean> list = dao.findAll(info.getUser_id());
		//				request.setAttribute("tasks", list);
		//				gotoPage(request, response, "/showTask.jsp");
		//			}
		//		} catch (DAOException e) {
		//			e.printStackTrace();
		//			request.setAttribute("message", "内部エラーが発生しました。");
		//			gotoPage(request, response, "/errInternal.jsp");
		//		}
	}

	//	/*
	//	 * gotopage
	//	 */
	//	private void gotoPage(HttpServletRequest request,
	//			HttpServletResponse response, String page) throws ServletException,
	//			IOException {
	//		RequestDispatcher rd = request.getRequestDispatcher(page);
	//		rd.forward(request, response);
	//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
