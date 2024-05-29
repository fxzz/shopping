package shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shopping.command.MemberCommand;
import shopping.service.member.MemberAutoNumService;
import shopping.service.member.MemberInsertService;

@Controller
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	MemberInsertService memberInsertService;
	
	@Autowired
	MemberAutoNumService memberAutoNumService;
	
	@RequestMapping(value = "memberList")
	public String list() {
		return "thymeleaf/member/memberList";
	}
	
	@RequestMapping(value = "memberRegist", method = RequestMethod.GET)
	public String form(Model model) {
		// 회원 번호 자동 입력
		memberAutoNumService.execute(model);
		return "thymeleaf/member/memberForm";
	}
	
	@RequestMapping(value = "memberRegist", method = RequestMethod.POST)
	public String form(MemberCommand memberCommand) {
		memberInsertService.execute(memberCommand);
		return "redirect:memberList";
	}
	

}
