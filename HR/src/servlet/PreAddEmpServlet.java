package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.DaoFactory;
import pojo.TblDeptDao;
import pojo.TblEmpDao;
import service.ITblDeptDaoService;
import service.ITblEmpService;
import service.TblDeptDaoServiceImp;
import service.TblEmpServiceImp;

@WebServlet("/PreAddEmpServlet")
public class PreAddEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  TblDeptDao DeptDao=DaoFactory.createTbldeptDao();
	private ITblDeptDaoService DeptService=new TblDeptDaoServiceImp();
	
	private  TblEmpDao employeeDao=DaoFactory.createEmpDao();
	private ITblEmpService employeeService=new TblEmpServiceImp();
	
    public PreAddEmpServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ArrayList<TblDeptDao> DeptList=DeptService.getDeptList();
		request.setAttribute("DeptList", DeptList);
		String m=request.getParameter("m");
		if(m.trim().equals("display")) {
			request.getRequestDispatcher("/jsp/addemp.jsp").forward(request,response);
		}else if(m.trim().equals("update")) {
			String id=request.getParameter("id");//回显时需要获得前面listmp.jsp的员工id
			String deptId=request.getParameter("deptId");
			//根据员工id来进行回显示
			employeeDao=employeeService.getOneById(id);
			DeptDao=DeptService.getOneById(deptId);
			request.setAttribute("employeeOne", employeeDao);
			request.setAttribute("DeptOne", DeptDao);
			
			request.getRequestDispatcher("/jsp/editemp.jsp").forward(request,response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
