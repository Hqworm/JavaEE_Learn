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

@WebServlet("/EditEmpServlet")
public class EditEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private  TblDeptDao DeptDao=DaoFactory.createTbldeptDao();
	private ITblDeptDaoService DeptService=new TblDeptDaoServiceImp();
	
	private  TblEmpDao employeeDao=DaoFactory.createEmpDao();
	private ITblEmpService employeeService=new TblEmpServiceImp();
	
    public EditEmpServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//设置服务器编码
		response.setContentType("text/html;charset=utf-8");//设置客户端编码
		String empId=request.getParameter("empId");
		String eName=request.getParameter("addName");
		String egendar=request.getParameter("gender");
		String dName=request.getParameter("addDeptName");
		
		
		int deptId=DeptService.getOneByName(dName);
		
		employeeDao=DaoFactory.createEmpDao(Integer.parseInt(empId), eName, Byte.parseByte(egendar), deptId);
		int m=0;
		m=updataEmployeeDao(employeeDao,dName) ;
		if(eName==null || eName.equals("")|| dName==null || dName.equals("")) {
			response.getWriter().print("<label>添加失败！</label>");
		}else {	
			
			response.sendRedirect("/HR/ListEmpServlet?m=list");
		}
		
		
	}

	private int updataEmployeeDao(TblEmpDao employeeDao,String dName) {
		
		
		int m=employeeService.updataEmployeeDao(employeeDao);
		return m;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
