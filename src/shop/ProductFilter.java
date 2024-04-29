package shop;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Page1Filter
 */
//@WebFilter("/jsp/ProductsList.jsp")
public class ProductFilter implements Filter {

    /**
     * Default constructor.
     */
    public ProductFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		//常に表示予定の3ページ部分で使う変数
		int pageFrom;
		int pageTo;
		//現在表示しているページ番号
		int currentPage;
		//1ページあたりの件数(コントローラから取ってくる)
		int recordPerPage;
		//表示される商品総数(DBから取ってくる)
		int totalRecordCount;
		//ページの総数(商品総数によって変化する)
		int totalPageCount;



		System.out.println("ProductFilter");
		request.setCharacterEncoding("UTF-8");
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();

		String Login = (String)session.getAttribute("Login");
		String Login_id = request.getParameter("Login_id");
		int pagecount = Integer.parseInt(request.getParameter("pagecount"));

		if(!(Login.equals(Login_id))) {
			session.setAttribute("Login", Login_id);
		}else {
			session.setAttribute("Login", Login);
		}
		ArrayList<ProductTableModel>List = ProductTableModel.selectAllList();
		ArrayList<ProductTableModel>SubList = ProductTableModel.selectSUBList(pagecount);

		totalRecordCount = List.size();
		recordPerPage = 3;

		//計算のあまりが0以上だったら、1を足してページの総数を求める
		if(totalRecordCount % recordPerPage > 0) {
			totalPageCount = totalRecordCount / recordPerPage + 1;
		} else {
			totalPageCount = totalRecordCount / recordPerPage;
		}

		System.out.println(totalPageCount);
		System.out.println(pagecount);


		//現在のページを更新
		currentPage = pagecount;
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

		System.out.println(pageFrom);
		System.out.println(pageTo);

		session.setAttribute("product_list", List);
		session.setAttribute("product_sublist", SubList);
		session.setAttribute("totalPageCount", totalPageCount);
		session.setAttribute("pagecount", pagecount);
		session.setAttribute("pageFrom", pageFrom);
		session.setAttribute("pageTo", pageTo);

		chain.doFilter(httpRequest, httpResponse);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
