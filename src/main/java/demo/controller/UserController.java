package demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.domain.TUser;
import demo.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "/newUser")
	public String newUser() {
		return "add_user";
	}
	
	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request, Model model) {
		int userId = Integer.parseInt(request.getParameter("id"));
		TUser user = userService.getUserById(userId);
		model.addAttribute("user", user);
		return "user_detail";
	}
	
	@RequestMapping(value = "/all")
	public String all(HttpServletRequest request, Model model) {
		List<TUser> userList = userService.getAllUsers();
		model.addAttribute("userList", userList);
		return "all_user";
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addUser(HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		TUser tUser = new TUser();
		tUser.setAge(age);
		tUser.setName(name);
		try {
			userService.addUser(tUser);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		map.put("success", true);
		return map;
	}
}
