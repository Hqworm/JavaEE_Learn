package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import dao.AdministratorDaoImp;
import dao.IAdministratorDao;
import dbutils.GetConnect;
import pojo.AdministratorDao;
import pojo.DaoFactory;
import service.AdminServiceImp;
import service.IAdminService;
public class LoginFilter implements Filter {

	//通过工厂获取管理员对象 只创建一个 减少了空间的开销
	AdministratorDao administrator=DaoFactory.createAdministratorDao();
	IAdminService IAdminService=new AdminServiceImp();
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");//设置服务器编码
		response.setContentType("text/html;charset=utf-8");//设置客户端编码
		String name = request.getParameter("AdminName");
		String pwd = request.getParameter("AdminPwd");
		String verity=request.getParameter("verify");
		
		int n=0;
		n=IAdminService.isExist(name,pwd);
	    String rand=(String) ((HttpServletRequest) request).getSession().getAttribute("rand");
	    if(n==1&&verity!=null&&rand.trim().equals(verity)) {
	    	//如果验证码 且管理员密码 姓名都正确 则放行 不过滤
	    	HttpSession session=((HttpServletRequest) request).getSession();
	    	session.setAttribute("AdminName", name);
	    	session.setAttribute("AdminPwd", pwd);
	    	session.setAttribute("verity", verity);
	    	chain.doFilter(request, response);
	    }else if(n==0){
	    	response.getWriter().println("<lable>用户不存在或密码错误！</label>");
	    }else {
	    	response.getWriter().println("<label>验证码错误，请重新输入！</label>");
	    }
	}

}