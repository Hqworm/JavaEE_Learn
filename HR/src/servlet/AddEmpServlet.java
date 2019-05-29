package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.UpdatableResultSet;

import pojo.DaoFactory;
import pojo.TblDeptDao;
import pojo.TblEmpDao;
import service.ITblDeptDaoService;
import service.ITblEmpService;
import service.TblDeptDaoServiceImp;
import service.TblEmpServiceImp;

@WebServlet("/AddEmpServlet")
public class AddEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  TblEmpDao employeeDao=null;
	private ITblEmpService employeeService=new TblEmpServiceImp();
	
	private  TblDeptDao DeptDao=DaoFactory.createTbldeptDao();
	private ITblDeptDaoService DeptService=new TblDeptDaoServiceImp();
 
    public AddEmpServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//设置服务器编码
		response.setContentType("text/html;charset=utf-8");//设置客户端编码
		
		String eName=request.getParameter("addName");
		String egendar=request.getParameter("gender");
		String dName=request.getParameter("addDeptName");
		
		
		int deptId=DeptService.getOneByName(dName);
		
		employeeDao=DaoFactory.createEmpDao(0, eName, Byte.parseByte(egendar), deptId);
		int m=updataEmployeeDao(employeeDao,dName) ;
		if(eName==null || eName.equals("")|| dName==null || dName.equals("")) {
			response.getWriter().print("<label>修改失败！</label>");
		}else {	
			
			response.sendRedirect("/HR/ListEmpServlet?m=list");
		}
		
		
	}

	private int updataEmployeeDao(TblEmpDao employeeDao,String dName) {
		
		
		int m=employeeService.addEmployeeDao(employeeDao);
		return m;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	
}
