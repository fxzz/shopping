package shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import shopping.command.MemberCommand;
import shopping.service.member.MemberAutoNumService;
import shopping.service.member.MemberInsertService;
import shopping.service.member.MemberListService;

@Controller
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	MemberInsertService memberInsertService;
	
	@Autowired
	MemberAutoNumService memberAutoNumService;
	
	@Autowired
	MemberListService memberListService;
	
	@GetMapping("memberList")
	public String list(@RequestParam(required = false, value = "searchWord") String searchWord, Model model) {
		memberListService.execute(searchWord, model);
		return "thymeleaf/member/memberList";
	}
	
	@GetMapping("memberRegist")
	public String form(Model model) {
		// 회원 번호 자동 입력
		memberAutoNumService.execute(model);
		return "thymeleaf/member/memberForm";
	}
	
	@PostMapping("memberRegist")
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
