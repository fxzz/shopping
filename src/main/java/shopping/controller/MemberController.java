package shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;
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
	public String form(@Valid  MemberCommand memberCommand, BindingResult result) {
		if (result.hasErrors()) {
			return "thymeleaf/member/memberForm";
		}
		
		if (!memberCommand.isMemberPwEqualsMemberPwCon()) {
			// result.rejectValue(필드명, 에러코드, 메세지)
			result.rejectValue("memberPwCon", "memberCommand.memberPwCon", "비밀번호 확인이 틀렸습니다.");
			return "thymeleaf/member/memberForm";
		} else {
			memberInsertService.execute(memberCommand);
			return "redirect:memberList";
		}
		
	}
	

}
