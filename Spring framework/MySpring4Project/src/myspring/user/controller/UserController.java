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
	
	//사용자 목록 조회
	@RequestMapping("/userList.do")
	public ModelAndView userList() {
		List<UserVO> userList = userService.getUserList();
		//2. DAO로 받아온 List객체를 JSP에서 사용할 수 있도록 request객체 저장합니다.
//		request.setAttribute("userList", users);
//		// 3. 결과를 출력해줄 JSP - userList.jsp를 포워딩
//		rd = request.getRequestDispatcher("userList.jsp");
//		rd.forward(request, response);
		return new ModelAndView("userList.jsp", "userList", userList);
	}
	
	//사용자 상세 정보 조회
	@RequestMapping("/userDetail.do")
	//@RequestParam = request.getParameter()
	public String userDetail(@RequestParam String userid, Model model) {
		UserVO user = userService.getUser(userid);
		model.addAttribute("user", user);
		return "userDetail.jsp";
	}
	
}
