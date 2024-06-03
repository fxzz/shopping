package shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import shopping.service.memberMyPage.MemberInfoService;
import shopping.service.memberMyPage.MemberPwModifyService;
import shopping.service.memberMyPage.MyPassConfirmService;

@Controller
@RequestMapping("mypage")
public class MemberMyPageController {
	
	@Autowired
	MemberInfoService memberInfoService;
	
	@Autowired
	MemberPwModifyService memberPwModifyService;
	
	@Autowired
	MyPassConfirmService myPassConfirmService;
	
	@GetMapping("myDetail")
	public String myDetail(HttpSession session, Model model) {
		
		memberInfoService.execute(session, model);
		return "thymeleaf/memberShip/myInfo";
	}
	
	@GetMapping("userPwModify")
	public String userPwModify() {
		return "thymeleaf/memberShip/myPwCon";
	}
	
	@PostMapping("memberPwModify")
	public String userPwModify(@RequestParam("memberPw") String memberPw, HttpSession session, Model model ) {
		return memberPwModifyService.execute(session, model, memberPw);
	}
	
	@PostMapping("myPwUpdate")
	@ResponseBody
	public boolean myPwUpdate(@RequestParam(value =  "oldPw") String oldPw,
								@RequestParam(value =  "newPw") String newPw,
								HttpSession session) {
		return myPassConfirmService.execute(newPw, oldPw, session);
	}
}
