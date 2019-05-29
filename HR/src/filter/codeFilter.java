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
			// ��ִ��Ŀ����Դ֮ǰ���ñ���
					// ������� ��Ҫ���ñ���
			request.setCharacterEncoding("utf-8");//���÷���������
			response.setContentType("text/html;charset=utf-8");//���ÿͻ��˱���
			chain.doFilter(request, response);
		}
		@Override
		public void destroy() {

		}
}
