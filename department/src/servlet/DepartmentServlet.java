package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.env.IUpdatableModule.UpdateKind;

import dao.DepartmentDaoImp;
import dao.IDepartmentDao;
import javafx.scene.control.Alert;
import pojo.DepartmentDao;

@WebServlet("/DepartmentServlet")
public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IDepartmentDao dao=new DepartmentDaoImp();
    public DepartmentServlet() {
        super();
    }
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//怎么处理乱码 如果是get请求 
		//
		
	//	new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
		//如果是post请求 设置 	request.setCharacterEncoding("utf-8");
		
		String m=request.getParameter("m");
		String number=request.getParameter("number");
		String name=request.getParameter("name");
		String location=request.getParameter("location");
		String  id=request.getParameter("id");
		switch (m) {
		case "":
		case "list"://查询所有
			number=request.getParameter("number");
			name=request.getParameter("name");
			List<DepartmentDao> list=dao.getLists(number,name);
			request.setAttribute("list", list);
			request.setAttribute("number", number);
			request.setAttribute("name", name);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			break;
		case "del"://删除
			//给用户弹出一个框
		//	dao.delById()
			id=request.getParameter("id");
			int k2=dao.delById(id);
			if(k2>0) {
				response.sendRedirect("/department/DepartmentServlet?m=list");
			}else {
				response.getWriter().println("<script>alert('删除失败! ')</script> ");
			}
			break;
		case "add"://添加
			number=request.getParameter("number");
			name=request.getParameter("name");
			location=request.getParameter("location");
			int k1=dao.insert(new DepartmentDao(0,number,name,location));
			if(k1>0) {//k>0说明插入成功
				//request.getRequestDispatcher("/DepartmentServlet?m=list").forward(request, response);//会带参数回去，所以使用重定向好点
				response.sendRedirect("/department/DepartmentServlet?m=list");
			}else {
				response.getWriter().println("<script>alert('插入失败或部门编号不能为空!')</script>");
			}
			
			break;
			//更新之前把数据查询出来
		case "updateBefore":
			String id1=request.getParameter("id");
			number=request.getParameter("number");
			name=request.getParameter("name");
			location=request.getParameter("location");
			DepartmentDao d=dao.getDepartmentById(id1);
			request.setAttribute("d", d);
			System.out.println(d.getId());
			request.getRequestDispatcher("/jsp/updateDept.jsp").forward(request, response);
			
			break;
		case "update"://更新
			String id3=request.getParameter("id");
			number=request.getParameter("number");
			name=request.getParameter("name");
			location=request.getParameter("location");
			DepartmentDao dept=new DepartmentDao(Integer.parseInt(id3),number,name,location);
			int n=dao.update(dept,Integer.parseInt(id3));
			if(n>0) {
				response.sendRedirect("/department/DepartmentServlet?m=list");
			}else {
				response.getWriter().println("部门编号不能为空或更新失败！");
			}
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
