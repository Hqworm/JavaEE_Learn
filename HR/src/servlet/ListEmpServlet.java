package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.druid.sql.dialect.oracle.ast.clause.ModelClause.ReturnRowsClause;

import pojo.DaoFactory;
import pojo.TblDeptDao;
import pojo.TblEmpDao;
import service.ITblDeptDaoService;
import service.ITblEmpService;
import service.TblDeptDaoServiceImp;
import service.TblEmpServiceImp;

@WebServlet("/ListEmpServlet")
public class ListEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TblEmpDao employeeDao = DaoFactory.createEmpDao();
	private ITblEmpService employeeService = new TblEmpServiceImp();

	private TblDeptDao DeptDao = DaoFactory.createTbldeptDao();
	private ITblDeptDaoService DeptService = new TblDeptDaoServiceImp();

	public ListEmpServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 设置服务器编码
		response.setContentType("text/html;charset=utf-8");// 设置客户端编码
		String m = request.getParameter("m");// m为前面页免传递的参数，用来判断操作
		ArrayList arr[] = getList();
		ArrayList<TblEmpDao> employeeList = arr[0];
		ArrayList<TblDeptDao> DeptList = arr[1];
		if (m.equals("list")) {
			request.setAttribute("employeeList", employeeList);
			request.setAttribute("DeptList", DeptList);
			request.getRequestDispatcher("/jsp/listemp.jsp").forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public ArrayList[] getList() {
		ArrayList<TblEmpDao> employeeList = employeeService.getEmployeeList();
		ArrayList<TblDeptDao> DeptList = DeptService.getDeptList();
		ArrayList arr[] = new ArrayList[2];
		arr[0] = employeeList;
		arr[1] = DeptList;
		return arr;

	}

	public void modifyBefore() {

	}
}
