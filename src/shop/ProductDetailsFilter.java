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
 * Servlet Filter implementation class ProductDetailsFilter
 */
@WebFilter(dispatcherTypes = {
		DispatcherType.REQUEST,
		DispatcherType.FORWARD
}
			, urlPatterns = { "/jsp/ProductDetails.jsp" })
public class ProductDetailsFilter implements Filter {

    /**
     * Default constructor.
     */
    public ProductDetailsFilter() {
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
		System.out.println("ProductDetailsFilter");
		request.setCharacterEncoding("UTF-8");
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();
		int shohin_id = Integer.parseInt(request.getParameter("shohin_id"));
		ProductTableModel prbean = new ProductTableModel();
		prbean = ProductTableModel.SelectDetails(shohin_id);
		httpRequest.setAttribute("product_detail", prbean);

		ProductTableModel oldprbean = new ProductTableModel();
		ArrayList<ProductTableModel> list = (ArrayList<ProductTableModel>)session.getAttribute("cartlist");
		if(list != null) {
			list.forEach(bean -> {if(shohin_id==bean.getShohin_id())oldprbean.setKosu(bean.getKosu());});
			httpRequest.setAttribute("oldprbean", oldprbean);
		}

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
