package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DepartmentDaoImp;
import dao.IDepartmentDao;
import pojo.DepartmentDao;

/**
 * Servlet implementation class pageServlet
 */
@WebServlet("/pageServlet")
public class pageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IDepartmentDao dao=new DepartmentDaoImp();
    public pageServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//currentPage--当前页面
		//nowPage  --下一页面或上一页
		String currentPage=request.getParameter("currentPage");
		String nowPage=request.getParameter("nowPage");
		System.out.println("nowPage::"+nowPage);
		System.out.println("currentPage:"+currentPage);
		List<DepartmentDao> listAll=dao.getLists(null, null);
		int count=listAll.size();//总的数量
		int pageCount=0;
		if(count%10==0) {
			pageCount=count/10;
		}else {
			pageCount=count/10+1;
		}
		List<DepartmentDao> list=dao.getListByNowPage(nowPage,pageCount);//跳转到新的一页获取数据
		HttpSession session=request.getSession();
		session.setAttribute("list", list);
		//request.setAttribute("list", list);
		request.setAttribute("pageCount", pageCount);
		//request.getRequestDispatcher("/index.jsp").forward(request, response);
		response.sendRedirect("/department/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
