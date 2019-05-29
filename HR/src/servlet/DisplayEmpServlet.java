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

@WebServlet("/DisplayEmpServlet")
public class DisplayEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private  TblEmpDao employeeDao=DaoFactory.createEmpDao();
	private ITblEmpService employeeService=new TblEmpServiceImp();
	
	private  TblDeptDao DeptDao=DaoFactory.createTbldeptDao();
	private ITblDeptDaoService DeptService=new TblDeptDaoServiceImp();
	
    public DisplayEmpServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");//����ʱ��Ҫ���ǰ��listmp.jsp��Ա��id
		String deptId=request.getParameter("deptId");
		//����Ա��id�����л���ʾ
		employeeDao=employeeService.getOneById(id);
		DeptDao=DeptService.getOneById(deptId);
		request.setAttribute("employeeOne", employeeDao);
		request.setAttribute("DeptOne", DeptDao);
		request.getRequestDispatcher("/jsp/displayemp.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
