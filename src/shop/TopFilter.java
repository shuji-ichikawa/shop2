package shop;

import java.io.IOException;

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
 * Servlet Filter implementation class TopFilter
 */
@WebFilter({"/jsp/CartList.jsp","/jsp/Error.jsp","/jsp/LoginDone.jsp","/jsp/LoginFail.jsp","/jsp/ProductDetails.jsp","/jsp/ProductsList.jsp","/jsp/RegisterDone.jsp","/jsp/Thanks.jsp"})
public class TopFilter implements Filter {

    /**
     * Default constructor.
     */
    public TopFilter() {
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
		System.out.println("TopFilter");
		request.setCharacterEncoding("UTF-8");
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();

		System.out.println(session.getAttribute("Login"));
		if (session.getAttribute("Login") == null) {
			httpResponse.sendRedirect("/shop/jsp/Login.jsp");
		} else {
			chain.doFilter(request, response);
		}

		// pass the request along the filter chain
		//chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
