package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.HotelsBean;
import la.dao.DAOException;
import la.dao.HotelDAO;

@WebServlet("/HotelServlet")
public class HotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HotelServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//日本語形式に変更
		request.setCharacterEncoding("UTF-8");
		try {
			//actionパラメータ取得
			String action = request.getParameter("action");

			/*
			 *宿すべて表示（会員トップ画面）
			 */
			if (action == null || action.length() == 0) {
				HotelDAO dao = new HotelDAO();
				//"findAll"で宿情報一覧取得
				List<HotelsBean> list = dao.findAll();
				request.setAttribute("hotels", list);
				gotoPage(request, response, "/customertop.jsp");

				/*
				 * 宿の新規登録
				 */
				//「登録」ボタン押下
			} else if (action.equals("addHotel")) {
				HotelDAO dao = new HotelDAO();
				//パラメータ取得
				String hotelName = request.getParameter("hotelName").strip();
				String categoryId = request.getParameter("categoryId").strip();
				String price = request.getParameter("price").strip();
				String checkin = request.getParameter("checkin").strip();
				String checkout = request.getParameter("checkout").strip();
				String maxpersons = request.getParameter("maxpersons").strip();
				//入力項目に空欄がないとき
				if (hotelName != null && hotelName.length() != 0
						&& categoryId != null && categoryId.length() != 0
						&& price != null && price.length() != 0
						&& checkin != null && checkin.length() != 0
						&& checkout != null && checkout.length() != 0
						&& maxpersons != null && maxpersons.length() != 0) {
					//カテゴリーID,価格、最大宿泊可能人数を整数化
					int cid = Integer.parseInt(categoryId);
					int pri = Integer.parseInt(price);
					int max = Integer.parseInt(maxpersons);
					//"addHotel"で宿の新規登録
					dao.addHotel(hotelName, cid, pri, checkin, checkout, max);
					List<HotelsBean> list = dao.findAll();
					request.setAttribute("hotels", list);
					gotoPage(request, response, "/AdminServlet?action=");

					//入力項目に空きがあるとき
				} else {
					request.setAttribute("mess", "正しく入力してください");
					gotoPage(request, response, "/addHotel.jsp");
				}

				/*
				 * 宿削除機能
				 */
				//「宿情報」リンク押下
			} else if (action.equals("delete")) {
				//宿情報取得（ID,名前のみ）
				HotelDAO dao = new HotelDAO();
				List<HotelsBean> list = dao.findAllHotelsAdmin();
				request.setAttribute("hotels", list);
				gotoPage(request, response, "/delteHotel.jsp");

				//宿削除ボタン押下
			} else if (action.equals("deleteHotel")) {
				int id = Integer.parseInt(request.getParameter("id"));
				HotelDAO dao = new HotelDAO();
				//"deleteHotel"で宿情報削除
				dao.deleteHotel(id);
				List<HotelsBean> list = dao.findAllHotelsAdmin();
				request.setAttribute("hotels", list);
				gotoPage(request, response, "/delteHotel.jsp");
			}

		} catch (

		DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	/*
	 * gotopage
	 */
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
