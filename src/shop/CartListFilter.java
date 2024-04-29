package shop;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class CartListFilter
 */
@WebFilter(dispatcherTypes = {
		DispatcherType.REQUEST,
		DispatcherType.FORWARD
}
			, urlPatterns = { "/jsp/CartList.jsp" })
public class CartListFilter implements Filter {

    /**
     * Default constructor.
     */
    public CartListFilter() {
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
		System.out.println("CartListFilter");
		request.setCharacterEncoding("UTF-8");
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();

		String Login_id = request.getParameter("Login_id");
		if(!(session.getAttribute("Login").equals(Login_id))) {
			session.setAttribute("Login", Login_id);
		}
		String Login = (String)session.getAttribute("Login");
		ArrayList<String> msglist = new ArrayList<String>();
		ArrayList<ProductTableModel> list = new ArrayList<ProductTableModel>();
		ArrayList<ProductTableModel> listA = new ArrayList<ProductTableModel>();
		if(session.getAttribute("cartlist") != null) {
			list = (ArrayList<ProductTableModel>)session.getAttribute("cartlist");
		}
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

			if(bean.getZaiko() > ProductTableModel.SelectStock(bean.getShohin_id())) {
				String msg = bean.getShohin_mei() + "の在庫が希望数量を下回りましたので削除しました";
				msglist.add(msg);
			}
		}
		listA.removeIf(bean -> bean.getKosu() > ProductTableModel.SelectStock(bean.getShohin_id()));
		int sum = ProductTableModel.Sum(listA);
		request.setAttribute("sum", sum);
		request.setAttribute("msglist", msglist);
		request.setAttribute("cartlist", listA);

		// pass the request along the filter chain
		chain.doFilter(httpRequest, httpResponse);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
