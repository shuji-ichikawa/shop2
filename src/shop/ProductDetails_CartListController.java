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
 * Servlet implementation class ProductDetails_CartListController
 */
@WebServlet("/ProductDetails_CartListController")
public class ProductDetails_CartListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetails_CartListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		System.out.println("ProductDetails_CartListController");

		try{
			String Login_id = request.getParameter("Login_id");
			request.setAttribute("Login_id",Login_id );
		}catch(Exception ex){
			ex.printStackTrace();
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
		request.setCharacterEncoding("UTF-8");

		System.out.println("ProductDetails_CartListController");

		try{
			HttpSession session = request.getSession();
			ArrayList<ProductTableModel> list = new ArrayList<ProductTableModel>();

			String Login_id = request.getParameter("Login_id");
			int shohin_id = Integer.parseInt(request.getParameter("shohin_id"));
			String shohin_mei = request.getParameter("shohin_mei");
			int tanka = Integer.parseInt(request.getParameter("tanka"));
			int kosu = 0;
			if(request.getParameter("kosu")!=null) {
				kosu = Integer.parseInt(request.getParameter("kosu"));
			}
			String shashin = request.getParameter("shashin");
			if(session.getAttribute("cartlist")==null) {
			//ショッピングカートがない場合
				ProductTableModel cartmodel = new ProductTableModel();
				cartmodel.setLogin_id(Login_id);
				cartmodel.setShohin_id(shohin_id);
				cartmodel.setShohin_mei(shohin_mei);
				cartmodel.setTanka(tanka);
				cartmodel.setKosu(kosu);
				cartmodel.setShashin(shashin);
				list.add(cartmodel);
			}else {
			//ショッピングカートがある場合
				list = (ArrayList<ProductTableModel>)session.getAttribute("cartlist");
				boolean check = false ;
				for(var b:list) {
					if(b.getShohin_id()==shohin_id && b.getLogin_id().equals(Login_id)) {
					//同じ品物がカートにある場合
						b.setKosu(b.getKosu() + kosu);
						check = true;
					}
				}
				if(!check) {
					ProductTableModel cartmodel = new ProductTableModel();
					cartmodel.setLogin_id(Login_id);
					cartmodel.setShohin_id(shohin_id);
					cartmodel.setShohin_mei(shohin_mei);
					cartmodel.setTanka(tanka);
					cartmodel.setKosu(kosu);
					cartmodel.setShashin(shashin);
					list.add(cartmodel);
				}
			}
			session.setAttribute("cartlist", list);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/jsp/CartList.jsp");
		rd.forward(request, response);
	}
}
