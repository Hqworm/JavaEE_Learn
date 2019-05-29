package servlet;

import java.io.IOException;
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

@WebServlet("/DeleteEmpServlet")
public class DeleteEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private  TblEmpDao employeeDao=null;
	private ITblEmpService employeeService=new TblEmpServiceImp();
	
	private  TblDeptDao DeptDao=DaoFactory.createTbldeptDao();
	private ITblDeptDaoService DeptService=new TblDeptDaoServiceImp();
   
    public DeleteEmpServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId=request.getParameter("empId");//通过empId来进行删除
		
		
		int n=delByEmpId(empId);
		if(n>0) {
			response.sendRedirect("/HR/ListEmpServlet?m=list");
		}else {
			response.getWriter().print("<label>删除是失败！操作不正确</label>");
		}
		
	}
	private int delByEmpId(String empId) {
		int n=employeeService.delByEmpId(empId);
		return n;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
