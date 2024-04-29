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
/**
 * Servlet implementation class RegisterCustomer_Done
 */
@WebServlet("/RegisterCustomer_DoneController")
public class RegisterCustomer_DoneController extends HttpServlet {

	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCustomer_DoneController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
 //--------------------------------------------------------------------------------------------------------------------
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		try {

			System.out.println("RegisterCustomer_DoneController");

            String forwardPath = null;
	     //サーブレットクラスの動作を決定する「ation」の値をﾘｸｴｽﾄﾊﾟﾗﾒｰﾀｰから取得
            // String action = request.getParameter("action");

			String ki = request.getParameter("ki");
  	        String km = request.getParameter("km");
  	        String ps = request.getParameter("ps");
  	        String bin =request.getParameter("bin");
  	        String jyu = request.getParameter("jyu");
  	        String den = request.getParameter("den");

  	        //登録開始をﾘｸｴｽﾄされた時の処理
			if( (ki == null) ||
				(km == null) ||
				(ps == null) ||
				(bin== null) ||
				(jyu== null) ||
				(den== null)) {
				forwardPath="/jsp/LoginFail.jsp/";

            }else {
            	//登録後のフォワード先を設定する
            	forwardPath="/jsp/RegisterDone.jsp/";
              }

            //doGetの設定されたフォワード先にフォワードするコード
            RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
            dispatcher.forward(request, response);
		}catch(Exception e) {
		e.printStackTrace();
		}
}
//-----------------------------------------------------------------------------------------------------
            /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		System.out.println("RegisterCustomer_DoneController");

		String path = null;
		 try {
			 	// リクエストパラメータの取得
			 	request.setCharacterEncoding("UTF-8");
				ArrayList<CustomerTableModel> list = new ArrayList<CustomerTableModel>();
			 	String km = request.getParameter("km");
			 	String ki = request.getParameter("ki");
			 	String ps = request.getParameter("ps");
			 	String bin = request.getParameter("bin");
			 	String jyu = request.getParameter("jyu");
			 	String den = request.getParameter("den");
				boolean check = false;

				Pattern p1 = Pattern.compile("^([a-zA-Z0-9]{1,8})$");
				Matcher m1 = p1.matcher(ki);

				Pattern p2 = Pattern.compile("^([0-9]{1,9})$");
				Matcher m2 = p2.matcher(bin);

				Pattern p3 = Pattern.compile("^([0-9]{1,11})$");
				Matcher m3 = p3.matcher(den);

				if(km.length() == 0) {
					String msgkm1 = "・名前が入力されていません。";
					request.setAttribute("msgkm1", msgkm1);
					check = true;
				}else if(km.length() > 60){
					String msgkm2 = "・60文字以内で入力してください。";
					request.setAttribute("msgkm2", msgkm2);
					check = true;
				}

				if(ki.length() == 0) {
					String msgki1 = "・ユーザーIDが入力されていません。";
					request.setAttribute("msgki1", msgki1);
					check = true;
				}else if(!(m1.find())){
					String msgki2 = "・半角英数字8文字以内で入力してください。";
					request.setAttribute("msgki2", msgki2);
					check = true;
				}else if(CustomerTableModel.LoginCheck(ki)) {
					String msgki3 = "・既に登録しているユーザーIDです。";
					request.setAttribute("msgki3", msgki3);
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

				if(bin.length() == 0) {
					String msgbin1 = "・郵便番号が入力されていません。";
					request.setAttribute("msgbin1", msgbin1);
					check = true;
				}else if(!(m2.find())){
					String msgbin2 = "・半角数字7文字以内で入力してください。";
					request.setAttribute("msgbin2", msgbin2);
					check = true;
				}

				if(jyu.length() == 0) {
					String msgjyu1 = "・住所が入力されていません。";
					request.setAttribute("msgjyu1", msgjyu1);
					check = true;
				}else if(jyu.length() > 200){
					String msgjyu2 = "・200文字以内で入力してください。";
					request.setAttribute("msgjyu2", msgjyu2);
					check = true;
				}

				if(den.length() == 0) {
					String msgden1 = "・電話番号が入力されていません。";
					request.setAttribute("msgden1", msgden1);
					check = true;
				}else if(!(m3.find())){
					String msgden2 = "・半角数字11文字以内で入力してください。";
					request.setAttribute("msgden2", msgden2);
					check = true;
				}

				if(check){
					path = "/jsp/RegisterCustomer.jsp";
				} else {
					CustomerTableModel ru = new CustomerTableModel();
					ru.setKokyaku_mei(km);
					ru.setKokyaku_id(ki);
					ru.setPassword(ps);
					ru.setYubin_bango(Integer.parseInt(bin));
					ru.setJyusho(jyu);
					ru.setDenwa(den);
					list.add(ru);
					CustomerTableModel.InsertCustomerData(list);
					path = "/jsp/RegisterDone.jsp";
				}
	    }catch(Exception e){
	  		e.printStackTrace();
	    }
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(path);
		rd.forward(request, response);
		}
	}


