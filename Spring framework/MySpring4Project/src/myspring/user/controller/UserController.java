package myspring.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import myspring.user.service.UserService;
import myspring.user.vo.UserVO;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//����� ��� ��ȸ
	@RequestMapping("/userList.do")
	public ModelAndView userList() {
		List<UserVO> userList = userService.getUserList();
		//2. DAO�� �޾ƿ� List��ü�� JSP���� ����� �� �ֵ��� request��ü �����մϴ�.
//		request.setAttribute("userList", users);
//		// 3. ����� ������� JSP - userList.jsp�� ������
//		rd = request.getRequestDispatcher("userList.jsp");
//		rd.forward(request, response);
		return new ModelAndView("userList.jsp", "userList", userList);
	}
	
	//����� �� ���� ��ȸ
	@RequestMapping("/userDetail.do")
	//@RequestParam = request.getParameter()
	public String userDetail(@RequestParam String userid, Model model) {
		UserVO user = userService.getUser(userid);
		model.addAttribute("user", user);
		return "userDetail.jsp";
	}
	
}
