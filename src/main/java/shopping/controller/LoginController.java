package shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;
import shopping.service.login.IdCheckService;

@Log4j2
@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	IdCheckService idCheckService;
	
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
}
