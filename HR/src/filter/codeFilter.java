package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

	public class codeFilter implements Filter {
		String encode ;
		@Override
		public void init(FilterConfig filterConfig) throws ServletException {
			 encode = filterConfig.getInitParameter("encode");
		}
		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			// 在执行目标资源之前设置编码
					// 针对中文 需要设置编码
			request.setCharacterEncoding("utf-8");//设置服务器编码
			response.setContentType("text/html;charset=utf-8");//设置客户端编码
			chain.doFilter(request, response);
		}
		@Override
		public void destroy() {

		}
}
