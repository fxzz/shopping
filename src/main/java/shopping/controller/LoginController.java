package shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import shopping.command.LoginCommand;
import shopping.service.login.IdCheckService;
import shopping.service.login.UserLoginService;

@Log4j2
@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	IdCheckService idCheckService;
	
	@Autowired
	UserLoginService userLoginService;
	
	@PostMapping("userIdCheck")
	@ResponseBody
	public String userIdCheck(@RequestParam(value="userId") String userId) {
		log.info("로그인 체크 " +userId);
		String resultId = idCheckService.execute(userId);
		if (resultId == null) {
			return "사용가능한 아이디입니다.";
		} else {
			return "사용중인 아이디입니다.";
		}
	}
	
	@PostMapping("login")
	public String login(LoginCommand loginCommand, BindingResult result, HttpSession session) {
		userLoginService.execute(loginCommand, session, result);
		if (result.hasErrors()) {
			return "thymeleaf/index";
		}
		return "redirect:/";
		
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
