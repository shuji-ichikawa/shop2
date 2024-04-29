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
 * Servlet implementation class LogoutController
 */
@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		System.out.println("LogoutController");

		try{
			HttpSession session = request.getSession();
			String Login_id = request.getParameter("Login_id");
			if(!(session.getAttribute("Login").equals(Login_id))) {
				session.setAttribute("Login", Login_id);
			}
			String Login = (String)session.getAttribute("Login");
			ArrayList<ProductTableModel> list = (ArrayList<ProductTableModel>)session.getAttribute("cartlist");
			list.removeIf(bean -> bean.getLogin_id().equals(Login));
			session.setAttribute("cartlist", list);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/jsp/Login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("LogoutController");

		doGet(request, response);
	}

}
