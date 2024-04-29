package shop;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteController
 */
@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		System.out.println("DeleteController");

		try {
			HttpSession session = request.getSession();
			String Login_id = request.getParameter("Login_id");
			String shohin_mei = request.getParameter("shohin_mei");
			ArrayList<ProductTableModel> list;
			list = (ArrayList<ProductTableModel>)session.getAttribute("cartlist");
			list.removeIf(bean -> bean.getLogin_id().equals(Login_id) && bean.getShohin_mei().equals(shohin_mei));
			int sum = ProductTableModel.Sum(list);
			request.setAttribute("sum", sum);
			request.setAttribute("Login_id", Login_id);
			session.setAttribute("cartlist", list);
		}catch(Exception e) {
		e.printStackTrace();
		}
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/jsp/CartList.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("DeleteController");

		doGet(request, response);
	}

}
