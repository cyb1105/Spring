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

	// 사용자 목록 조회
	@RequestMapping("/userList.do")
	public ModelAndView userList() {
		List<UserVO> userList = userService.getUserList();
		// 2. DAO로 받아온 List객체를 JSP에서 사용할 수 있도록 request객체 저장합니다.
//		request.setAttribute("userList", users);
//		// 3. 결과를 출력해줄 JSP - userList.jsp를 포워딩
//		rd = request.getRequestDispatcher("userList.jsp");
//		rd.forward(request, response);
		return new ModelAndView("userList", "userList", userList);
	}

	// 사용자 상세 정보 조회
	@RequestMapping("/userDetail.do")
	// @RequestParam = request.getParameter()
	// QueryString ?key1=value&key2=value2는 RequestParam
	public String userDetail(@RequestParam String userid, Model model) {
		UserVO user = userService.getUser(userid);
		model.addAttribute("user", user);
		return "userDetail";
	}

	// 사용자 등록form조회
	@RequestMapping("/userInsertForm.do")
	public String insertUserForm(HttpSession session) {
		List<String> genderList = new ArrayList<String>();
		genderList.add("남");
		genderList.add("여");
		// session객체에 genderList 객체를 저장
		session.setAttribute("genderList", genderList);

		List<String> cityList = new ArrayList<String>();
		cityList.add("서울");
		cityList.add("부산");
		cityList.add("대구");
		cityList.add("제주");
		// session객체에cityList 객체를 저장
		session.setAttribute("cityList", cityList);

		return "userInsert";
	}

	// 사용자 등록 처리
	@RequestMapping(value = "/userInsert.do", method = RequestMethod.POST)
	public String userInsert(@ModelAttribute UserVO user) {
		System.out.println(">>UserVO " + user);
		userService.insertUser(user);
		// 사용자 목록 조회를 처리하는 요청으로 포워딩
		return "redirect:/userList.do";
	}

	// 사용자 삭체 처리
	// userDelete.do?userid=gildong (querystring
	// userDelete.do/gildong (url에 /로 base로 append하는 방식) : @PathVariable 사용
	@RequestMapping("/userDelete.do/{id}")
	public String userDelete(@PathVariable("id") String userid) {
		userService.deleteUser(userid);
		return "redirect:/userList.do";
	}

	// 사용자 수정처리
	@RequestMapping("/updateUserForm.do")
	public ModelAndView updateUserForm(@RequestParam String id) {
		UserVO user = userService.getUser(id);
		List<String> genderList = new ArrayList<String>();
		genderList.add("남");
		genderList.add("여");
		List<String> cityList = new ArrayList<String>();
		cityList.add("서울");
		cityList.add("부산");
		cityList.add("대구");
		cityList.add("제주");
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
