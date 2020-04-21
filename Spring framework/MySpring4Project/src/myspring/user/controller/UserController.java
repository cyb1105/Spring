package myspring.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import myspring.user.service.UserService;
import myspring.user.vo.UserVO;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	// ����� ��� ��ȸ
	@RequestMapping("/userList.do")
	public ModelAndView userList() {
		List<UserVO> userList = userService.getUserList();
		// 2. DAO�� �޾ƿ� List��ü�� JSP���� ����� �� �ֵ��� request��ü �����մϴ�.
//		request.setAttribute("userList", users);
//		// 3. ����� ������� JSP - userList.jsp�� ������
//		rd = request.getRequestDispatcher("userList.jsp");
//		rd.forward(request, response);
		return new ModelAndView("userList", "userList", userList);
	}

	// ����� �� ���� ��ȸ
	@RequestMapping("/userDetail.do")
	// @RequestParam = request.getParameter()
	// QueryString ?key1=value&key2=value2�� RequestParam
	public String userDetail(@RequestParam String userid, Model model) {
		UserVO user = userService.getUser(userid);
		model.addAttribute("user", user);
		return "userDetail";
	}

	// ����� ���form��ȸ
	@RequestMapping("/userInsertForm.do")
	public String insertUserForm(HttpSession session) {
		List<String> genderList = new ArrayList<String>();
		genderList.add("��");
		genderList.add("��");
		// session��ü�� genderList ��ü�� ����
		session.setAttribute("genderList", genderList);

		List<String> cityList = new ArrayList<String>();
		cityList.add("����");
		cityList.add("�λ�");
		cityList.add("�뱸");
		cityList.add("����");
		// session��ü��cityList ��ü�� ����
		session.setAttribute("cityList", cityList);

		return "userInsert";
	}

	// ����� ��� ó��
	@RequestMapping(value = "/userInsert.do", method = RequestMethod.POST)
	public String userInsert(@ModelAttribute UserVO user) {
		System.out.println(">>UserVO " + user);
		userService.insertUser(user);
		// ����� ��� ��ȸ�� ó���ϴ� ��û���� ������
		return "redirect:/userList.do";
	}

	// ����� ��ü ó��
	// userDelete.do?userid=gildong (querystring
	// userDelete.do/gildong (url�� /�� base�� append�ϴ� ���) : @PathVariable ���
	@RequestMapping("/userDelete.do/{id}")
	public String userDelete(@PathVariable("id") String userid) {
		userService.deleteUser(userid);
		return "redirect:/userList.do";
	}

	// ����� ����ó��
	@RequestMapping("/updateUserForm.do")
	public ModelAndView updateUserForm(@RequestParam String id) {
		UserVO user = userService.getUser(id);
		List<String> genderList = new ArrayList<String>();
		genderList.add("��");
		genderList.add("��");
		List<String> cityList = new ArrayList<String>();
		cityList.add("����");
		cityList.add("�λ�");
		cityList.add("�뱸");
		cityList.add("����");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("genderList", genderList);
		map.put("cityList", cityList);
		map.put("user", user);
		return new ModelAndView("userUpdate", "map", map);
	}

	@RequestMapping("/updateUser.do")
	public String updateUser(@ModelAttribute UserVO user) {
		userService.updateUser(user);
		return "redirect:/userList.do";
	}
//	

}
