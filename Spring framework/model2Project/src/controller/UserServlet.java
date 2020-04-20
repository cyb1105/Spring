package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.user.dao.UserDAO;
import jdbc.user.vo.UserVO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("*.do")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;
	private RequestDispatcher rd;
	
	@Override
	public void init() throws ServletException {
		System.out.println("UserServlet init() method called...");
		dao = new UserDAO();
	}
	@Override
	public void destroy() {
		System.out.println("UserServlet destroy() method called...");
	}
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserServlet doGet() method called..."+request.getMethod());
		//��û �������� ���ڵ�
		request.setCharacterEncoding("UTF-8");
		
		String cmd = request.getParameter("cmd");
		System.out.println(">>>cmd:"+cmd);
		//�б� ����
		if(cmd.equals("userList")) {
		userList(request, response);
		}else if(cmd.equals("userDetail")) {
			userDetail(request, response);
		}else if(cmd.equals("userForm")) {
			userForm(request, response);
		}else if(cmd.equals("userInsert")) {
			userInsert(request, response);
		}
	}
	
	private void userInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.Form�����͸� �����ؼ� userVO�� �����Ѵ�.
		UserVO user = new UserVO(request.getParameter("userid"), 
									request.getParameter("name"), 
									request.getParameter("gender").charAt(0),
									request.getParameter("city"));
		System.out.println(">>>Form data" + user);
		//2. DAO�� insertUSerȣ��
		int cnt = dao.insertUser(user);
		if(cnt == 1 ) { //��� ����
			userList(request, response);
		}
		
		
	}
	
	private void userForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("userForm.html");
		
	}
	private void userDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userid = request.getParameter("id");
		System.out.println(">>>userid"+userid);
		
		//1. DAO ȣ��
		UserVO userVO = dao.getUser(userid);
		//2. DAO�� �޾ƿ� List��ü�� JSP���� ����� �� �ֵ��� request��ü �����մϴ�.
		request.setAttribute("user",userVO);
		//3. ����� ������� JSP - userList.jsp�� ������
		rd = request.getRequestDispatcher("userDetail.jsp");
		rd.forward(request, response);
	}
	
	private void userList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1. DAO ȣ��
		List<UserVO> users = dao.getUsers();
		//2. DAO�� �޾ƿ� List��ü�� JSP���� ����� �� �ֵ��� request��ü �����մϴ�.
		request.setAttribute("userList",users);
		//3. ����� ������� JSP - userList.jsp�� ������
		rd = request.getRequestDispatcher("userList.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserServlet doPost() method called..."+request.getMethod());
		doGet(request, response);
	}

}