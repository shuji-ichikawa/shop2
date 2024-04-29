package shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Page1to2Controller
 */
@WebServlet("/Login_ProductsListController")
public class Login_ProductsListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login_ProductsListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		System.out.println("Login_ProductsListController");

		try{


			//常に表示予定の3ページ部分で使う変数
			int pageFrom;
			int pageTo;
			//1ページあたりの件数(コントローラから取ってくる)
			int recordPerPage;
			//表示される商品総数(DBから取ってくる)
			int totalRecordCount;
			//ページの総数(商品総数によって変化する)
			int totalPageCount;

			request.setCharacterEncoding("UTF-8");
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			HttpSession session = httpRequest.getSession();

			String Login = (String)session.getAttribute("Login");
			String Login_id = request.getParameter("Login_id");
			int pagecount = Integer.parseInt(request.getParameter("pagecount"));
			int sort_number = Integer.parseInt(request.getParameter("sort_number"));

			if(!(Login.equals(Login_id))) {
				session.setAttribute("Login", Login_id);
			}else {
				session.setAttribute("Login", Login);
			}
			ArrayList<ProductTableModel>List = ProductTableModel.selectAllList();
			ArrayList<ProductTableModel>SubList = ProductTableModel.selectSUBList(pagecount, sort_number);

			totalRecordCount = List.size();
			recordPerPage = 3;

			//計算のあまりが0以上だったら、1を足してページの総数を求める
			if(totalRecordCount % recordPerPage > 0) {
				totalPageCount = totalRecordCount / recordPerPage + 1;
			} else {
				totalPageCount = totalRecordCount / recordPerPage;
			}

			//「pageFrom」が「2」より小さくなることはない
			pageFrom = Math.max(pagecount - 1, 2);
			if (totalPageCount > 4) {
				//「pageTo」の求め方は、pageFromに「2」をたす、ただし「totalPageCount-1」を超えることはない
				pageTo = Math.min(pageFrom + 3, totalPageCount - 1);
			} else {
				//「totalPageCount」が「2」以下ならば、計算は不要
				pageTo = totalPageCount;
			}
			//「pageFrom」は「pageTo - 2」または「2」の大きい方なので、格納しなおす
			pageFrom = Math.max(pageTo - 3, 2);

			session.setAttribute("product_list", List);
			session.setAttribute("product_sublist", SubList);
			session.setAttribute("totalPageCount", totalPageCount);
			session.setAttribute("pagecount", pagecount);
			session.setAttribute("pageFrom", pageFrom);
			session.setAttribute("pageTo", pageTo);
			session.setAttribute("sort_number", sort_number);

		}catch(Exception ex){
			ex.printStackTrace();
		}
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/jsp/ProductsList.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String Path = null;

		System.out.println("Login_ProductsListController");

		try{
			String ki = request.getParameter("ki");
			String ps = request.getParameter("ps");
			Pattern p1 = Pattern.compile("^([A-Za-z0-9]{1,8})$");
			Matcher m1 = p1.matcher(ki);
			boolean check = false;

			if(ki.length() == 0) {
				String msgki1 = "・ユーザーIDが入力されていません。";
				request.setAttribute("msgki1", msgki1);
				check = true;
			}else if(!(m1.find())){
				String msgki2 = "・半角英数字1～8文字で入力してください。";
				request.setAttribute("msgki2", msgki2);
				check = true;
			}

			if(ps.length() == 0) {
				String msgps1 = "・パスワードが入力されていません。";
				request.setAttribute("msgps1", msgps1);
				check = true;
			}else if(ps.length() > 16){
				String msgps2 = "・16文字以内で入力してください。";
				request.setAttribute("msgps2", msgps2);
				check = true;
			}

			if(check) {
				Path = "/jsp/Login.jsp";
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("Login", ki);
				int checks = CustomerTableModel.LoginCheck(ki, ps);
				if(checks == CustomerTableModel.match) {
					Path = "/jsp/LoginDone.jsp";
				}else if(checks == CustomerTableModel.difference) {
					Path = "/jsp/LoginFail.jsp";
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(Path);
		dispatcher.forward(request, response);
	}
}
