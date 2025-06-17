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
			//actionパラメータ取得
			String action = request.getParameter("action");

			/*
			 *会員すべて表示（管理者トップ画面）
			 */
			if (action == null || action.length() == 0) {
				//現在のセッションを取得
				HttpSession session = request.getSession(false);
				//"user"をゲットする
				CustomersBean bean = (CustomersBean) session.getAttribute("user");

				AdminDAO dao = new AdminDAO();
				//"findAll()"で会員情報取得
				List<CustomersBean> list = dao.findAll();
				//リクエストスコープとセッションスコープに保存
				request.setAttribute("customers", list);
				session.setAttribute("user", bean);
				gotoPage(request, response, "/admintop.jsp");

				/*
				 * 会員の退会
				 */
				//「退会」ボタン押下
			} else if (action.equals("delete")) {
				int id = Integer.parseInt(request.getParameter("id"));
				AdminDAO dao = new AdminDAO();

				//"deleteCustomer()"->sqlでdelete実行
				dao.deleteCustomer(id);

				List<CustomersBean> list = dao.findAll();
				request.setAttribute("customers", list);
				gotoPage(request, response, "/admintop.jsp");

				/*
				 * 会員の状態を変更（管理者に）
				 */
				//「管理者に変更」ボタン押下
			} else if (action.equals("update")) {
				int id = Integer.parseInt(request.getParameter("id"));
				AdminDAO dao = new AdminDAO();

				//"updateCustomer()"->sqlでupdate実行
				dao.updateCustomer(id);

				List<CustomersBean> list = dao.findAll();
				request.setAttribute("customers", list);
				gotoPage(request, response, "/admintop.jsp");

				/*
				 * 会員検索
				 */
				//「検索」ボタン押下
			} else if (action.equals("search")) {
				String searchAdmin = request.getParameter("searchAdmin").strip();
				AdminDAO dao = new AdminDAO();
				List<CustomersBean> list;
				//検索欄が空欄の場合、会員一覧のまま
				if (searchAdmin == null || searchAdmin.length() == 0) {
					list = dao.findAll();
					//エラーメッセージとともに会員一覧画面のまま
					request.setAttribute("message", "会員IDを入力してください");
					request.setAttribute("customers", list);
					gotoPage(request, response, "/admintop.jsp");
				} else {
					try {
						//IDが入力された場合、IDのよって検索
						// 数字として解釈し検索
						int searchIndex = Integer.parseInt(searchAdmin);
						list = dao.searchCustomer(searchIndex);
					} catch (NumberFormatException e) {
						// 数字に変換できなかった場合のエラー処理
						list = dao.findAll(); // 会員一覧を表示したまま
						request.setAttribute("message", "IDは半角数字で入力してください");
					}
				}
				request.setAttribute("customers", list);
				gotoPage(request, response, "/admintop.jsp");
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
