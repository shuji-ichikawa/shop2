package shop;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class RegisterItemDoneController
 */
@WebServlet("/RegisterItemDoneController")
@MultipartConfig
public class RegisterItemDoneController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterItemDoneController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("RegisterItemDoneController");

		String Path = null;
		String a = request.getParameter("a");
		try{
			HttpSession session = request.getSession();
			Path = "/jsp/Administrator_ProductsList.jsp";

        	   ArrayList<ProductTableModel> list = ProductTableModel.selectAllList();
        	   session.setAttribute("product_list", list);

		}catch(Exception ex){
			ex.printStackTrace();
		}

		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(Path);
		dispatcher.forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("RegisterItemDoneController");

		String path = null;

		Part part=request.getPart("pict");
		//ファイル名を取得
		String shashin=Paths.get(part.getSubmittedFileName()).getFileName().toString();
		//アップロードするフォルダ
		String path1=getServletContext().getRealPath("/img");
		//実際にファイルが保存されるパス確認

		if(shashin.length() == 0) {
			shashin = "noimage.png";
		}else{
			part.write(path1+File.separator+shashin);
		}

		try {
				// リクエストパラメータの取得
			 	request.setCharacterEncoding("UTF-8");
				HttpSession session = request.getSession();
				ArrayList<ProductTableModel> list = new ArrayList<ProductTableModel>();

				String shohin_mei = request.getParameter("shohin_mei");
				String tanka = request.getParameter("tanka");
				String zaiko = request.getParameter("zaiko");
				String setsumei = request.getParameter("setsumei");
				boolean check = false;

				Pattern p2 = Pattern.compile("^([0-9]{1,9})$");
				Matcher m2 = p2.matcher(tanka);

				Pattern p3 = Pattern.compile("^([0-9]{1,3})$");
				Matcher m3 = p3.matcher(zaiko);

				if(shohin_mei.length() == 0) {
					String msgshohin_mei1 = "・商品IDが入力されていません。";
					request.setAttribute("msgshohin_mei1", msgshohin_mei1);
					check = true;
				}else if(shohin_mei.length() > 60){
					String msgshohin_mei2 = "・60文字以内で入力してください。";
					request.setAttribute("msgshohin_mei2", msgshohin_mei2);
					check = true;
				}

				if(tanka.length() == 0) {
					String msgtanka1 = "・単価が入力されていません。";
					request.setAttribute("msgtanka1", msgtanka1);
					check = true;
				}else if(!(m2.find())){
					String msgtanka2 = "・半角数字9桁以内で入力してください。";
					request.setAttribute("msgtanka2", msgtanka2);
					check = true;
				}

				if(zaiko.length() == 0) {
					String msgzaiko1 = "・在庫数が入力されていません。";
					request.setAttribute("msgzaiko1", msgzaiko1);
					check = true;
				}else if(!(m3.find())){
					String msgzaiko2 = "・半角数字3桁以内で入力してください。";
					request.setAttribute("msgzaiko2", msgzaiko2);
					check = true;
				}

				if(check){
					path = "/jsp/Register_Item.jsp";
				} else {
					ProductTableModel productmodel = new ProductTableModel();
					productmodel.setShohin_mei(shohin_mei);
					productmodel.setTanka(Integer.parseInt(tanka));
					productmodel.setZaiko(Integer.parseInt(zaiko));
					productmodel.setShashin(shashin);
					productmodel.setSetsumei(setsumei);
					list.add(productmodel);
					ProductTableModel.InsertProductData(list);
					path = "/jsp/RegisterItemDone.jsp";
					ArrayList<ProductTableModel>List = ProductTableModel.selectAllList();
					session.setAttribute("product_list", List);
				}
	    }catch(Exception e){
			e.printStackTrace();
		}

		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(path);
		rd.forward(request, response);

	}

}
