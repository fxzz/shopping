package shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shopping.service.EmailCheckService;
import shopping.service.UserEmailCheckService;

@Controller
public class CheckRestController {

	@Autowired
	EmailCheckService emailCheckService;
	
	@Autowired
	UserEmailCheckService userEmailCheckService;
	
	@PostMapping("checkRest/userEmailCheck")
	@ResponseBody
	public String userEmailCheck(@RequestParam(value="userEmail") String userEmail) {
		String resultEmail = emailCheckService.execute(userEmail);
		if(resultEmail == null) {
			return "사용가능한 Email입니다.";
		}else {
			return "사용중인 Email입니다.";
		}
	}
	
	@RequestMapping("userConfirm")
	@ResponseBody
	public String userConfirm(@RequestParam(value = "chk") String chk) {
		int i = userEmailCheckService.execute(chk);
		if(i == 0) {
			return "이미 인증되었습니다.";
		} else {
			return "인증되었습니다.";
		}
	}
	
	
}
