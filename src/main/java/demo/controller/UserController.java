package demo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.domain.TUser;
import demo.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;
	
	@RequestMapping("/detail")
	public String detail(HttpServletRequest request, Model model) {
		int userId = Integer.parseInt(request.getParameter("id"));
		TUser user = userService.getUserById(userId);
		model.addAttribute("user", user);
		return "user_detail";
	}
	
	@RequestMapping("/all")
	public String all(HttpServletRequest request, Model model) {
		List<TUser> userList = userService.getAllUsers();
		model.addAttribute("userList", userList);
		return "all_user";
	}
}
