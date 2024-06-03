package shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import shopping.command.MemberCommand;
import shopping.service.memberMyPage.MemberDropService;
import shopping.service.memberMyPage.MemberInfoService;
import shopping.service.memberMyPage.MemberInfoUpdateService;
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
	
	@Autowired
	MemberDropService memberDropService;
	
	@Autowired
	MemberInfoUpdateService memberInfoUpdateService;
	
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
	
	@GetMapping("memberDrop")
	public String memberDrop() {
		return "thymeleaf/memberShip/memberDrop";
	}
	
	@PostMapping("memberDropOk")
	public String memberDrop(@RequestParam("memberPw") String memberPw, Model model, HttpSession session) {
		int i = memberDropService.execute(memberPw, session, model);
		if (i == 1) {
			return "redirect:/login/logout";
		} else {
			return "thymeleaf/memberShip/memberDrop";
		}
	}
	
	@GetMapping("memberUpdate")
	public String memberUpdate(HttpSession session, Model model) {
		memberInfoService.execute(session, model);
		return "thymeleaf/memberShip/myModify";
	}
	
	@PostMapping("memberUpdate")
	public String memberUpdate(@Valid MemberCommand memberCommand, BindingResult result, HttpSession session) {
		memberInfoUpdateService.execute(memberCommand, session, result);
		
		if (result.hasErrors()) {
			return "thymeleaf/memberShip/myModify";
		}
		return "redirect:myDetail";
	}
}
