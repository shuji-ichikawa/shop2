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
 * Servlet implementation class CartList_ThanksController
 */
@WebServlet("/CartList_ThanksController")
public class CartList_ThanksController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartList_ThanksController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{

			System.out.println("CartList_ThanksController");

			HttpSession session=request.getSession();
			ArrayList<ProductTableModel> listA = new ArrayList<ProductTableModel>();
			ArrayList<ProductTableModel> chumon_list = new ArrayList<ProductTableModel>();
			ArrayList<ProductTableModel> product_list = (ArrayList<ProductTableModel>)session.getAttribute("product_list");
			ArrayList<ProductTableModel> zaiko_list = new ArrayList<ProductTableModel>();
			String Login = (String)session.getAttribute("Login");

			chumon_list = ProductTableModel.selectrireki(Login);
			session.setAttribute("chumonlist", chumon_list);

			for(var bean1:listA) {
				int shohin_id1 = bean1.getShohin_id();
				int kosu = bean1.getKosu();
				for(var bean2:product_list) {
					int shohin_id2 = bean2.getShohin_id();
					int zaiko = bean2.getZaiko();
					if(shohin_id1 == shohin_id2) {
						zaiko = zaiko - kosu;
						ProductTableModel cartmodel = new ProductTableModel();
						cartmodel.setShohin_id(shohin_id2);
						cartmodel.setZaiko(zaiko);
						zaiko_list.add(cartmodel);
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/jsp/Order_History.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("CartList_ThanksController");

		HttpSession session=request.getSession();
		ArrayList<ProductTableModel> list = (ArrayList<ProductTableModel>)session.getAttribute("cartlist");
		ArrayList<ProductTableModel> product_list = (ArrayList<ProductTableModel>)session.getAttribute("product_list");
		ArrayList<ProductTableModel> listA = new ArrayList<ProductTableModel>();
		ArrayList<ProductTableModel> chumon_list = new ArrayList<ProductTableModel>();
		ArrayList<ProductTableModel> zaiko_list = new ArrayList<ProductTableModel>();
		try {
			String Login = (String)session.getAttribute("Login");

			for(var bean:list) {
				if(bean.getLogin_id().equals(Login)) {
					ProductTableModel cartmodel = new ProductTableModel();
					cartmodel.setLogin_id(bean.getLogin_id());
					cartmodel.setShohin_id(bean.getShohin_id());
					cartmodel.setShohin_mei(bean.getShohin_mei());
					cartmodel.setTanka(bean.getTanka());
					cartmodel.setKosu(bean.getKosu());
					cartmodel.setShashin(bean.getShashin());
					listA.add(cartmodel);
				}
			}
			ProductTableModel.InsertData(listA, Login);
			chumon_list = ProductTableModel.selectrireki(Login);
			session.setAttribute("chumonlist", chumon_list);

			for(var bean1:listA) {
				int shohin_id1 = bean1.getShohin_id();
				int kosu = bean1.getKosu();
				for(var bean2:product_list) {
					int shohin_id2 = bean2.getShohin_id();
					int zaiko = bean2.getZaiko();
					if(shohin_id1 == shohin_id2) {
						zaiko = zaiko - kosu;
						ProductTableModel cartmodel = new ProductTableModel();
						cartmodel.setShohin_id(shohin_id2);
						cartmodel.setZaiko(zaiko);
						zaiko_list.add(cartmodel);
					}
				}
			}
			ProductTableModel.UpdateProductData(zaiko_list);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		ServletContext context=getServletContext();
		RequestDispatcher rd=context.getRequestDispatcher("/jsp/Thanks.jsp");
		rd.forward(request,response);
	}

}
