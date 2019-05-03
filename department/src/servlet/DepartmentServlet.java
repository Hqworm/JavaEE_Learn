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
		//��ô�������� �����get���� 
		//
		
	//	new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
		//�����post���� ���� 	request.setCharacterEncoding("utf-8");
		
		String m=request.getParameter("m");
		String number=request.getParameter("number");
		String name=request.getParameter("name");
		String location=request.getParameter("location");
		String  id=request.getParameter("id");
		switch (m) {
		case "":
		case "list"://��ѯ����
			number=request.getParameter("number");
			name=request.getParameter("name");
			List<DepartmentDao> list=dao.getLists(number,name);
			request.setAttribute("list", list);
			request.setAttribute("number", number);
			request.setAttribute("name", name);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			break;
		case "del"://ɾ��
			//���û�����һ����
		//	dao.delById()
			id=request.getParameter("id");
			int k2=dao.delById(id);
			if(k2>0) {
				response.sendRedirect("/department/DepartmentServlet?m=list");
			}else {
				response.getWriter().println("<script>alert('ɾ��ʧ��! ')</script> ");
			}
			break;
		case "add"://���
			number=request.getParameter("number");
			name=request.getParameter("name");
			location=request.getParameter("location");
			int k1=dao.insert(new DepartmentDao(0,number,name,location));
			if(k1>0) {//k>0˵������ɹ�
				//request.getRequestDispatcher("/DepartmentServlet?m=list").forward(request, response);//���������ȥ������ʹ���ض���õ�
				response.sendRedirect("/department/DepartmentServlet?m=list");
			}else {
				response.getWriter().println("<script>alert('����ʧ�ܻ��ű�Ų���Ϊ��!')</script>");
			}
			
			break;
			//����֮ǰ�����ݲ�ѯ����
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
		case "update"://����
			String id3=request.getParameter("id");
			number=request.getParameter("number");
			name=request.getParameter("name");
			location=request.getParameter("location");
			DepartmentDao dept=new DepartmentDao(Integer.parseInt(id3),number,name,location);
			int n=dao.update(dept,Integer.parseInt(id3));
			if(n>0) {
				response.sendRedirect("/department/DepartmentServlet?m=list");
			}else {
				response.getWriter().println("���ű�Ų���Ϊ�ջ����ʧ�ܣ�");
			}
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
