package shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import shopping.command.MemberCommand;
import shopping.service.member.MemberAutoNumService;
import shopping.service.member.MemberDetailService;
import shopping.service.member.MemberInsertService;
import shopping.service.member.MemberListService;
import shopping.service.member.MemberUpdateService;
import shopping.service.member.MembersDeleteService;
import shopping.service.member.MemberDeleteService;

@Controller
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	MemberInsertService memberInsertService;
	
	@Autowired
	MemberAutoNumService memberAutoNumService;
	
	@Autowired
	MemberListService memberListService;
	
	@Autowired
	MembersDeleteService membersDeleteService;
	
	@Autowired
	MemberDetailService memberDetailService;
	
	@Autowired
	MemberUpdateService memberUpdateService;
	
	@Autowired
	MemberDeleteService memberDeleteService;
	
	@GetMapping("memberdelete/{memberNum}")
	public String memberdelete(
			@PathVariable(value="memberNum") String memberNum) {
		memberDeleteService.execute(memberNum);
		return "redirect:/member/memberList";
	}
	
	@PostMapping("memberModify")
	public String memberModify(@Valid MemberCommand memberCommand, BindingResult result) {
		if (result.hasErrors()) {
			return "thymeleaf/member/memberModify";
		}
		memberUpdateService.execute(memberCommand);
		return "redirect:memberDetail?memberNum="+memberCommand.getMemberNum();
	}
	
	@GetMapping("memberUpdate")
	public String memberUpdate(@RequestParam(value = "memberNum") String memberNum, Model model) {
		memberDetailService.execute(memberNum, model);
		return "thymeleaf/member/memberModify";
	}
	
	@GetMapping("memberDetail")
	public String memberDetail(@RequestParam(value = "memberNum") String memberNum, Model model) {
		memberDetailService.execute(memberNum, model);
		return "thymeleaf/member/memberInfo";
	}
	
	@PostMapping("membersDelete")
	public String dels(@RequestParam(value = "memDels") String memDels[]) {
		membersDeleteService.execute(memDels);
		return "redirect:memberList";
	}
	
	@GetMapping("memberList")
	public String list(@RequestParam(required = false, value = "searchWord") String searchWord,
					   @RequestParam(required = false, value = "page", defaultValue = "1") int page,
					   Model model) {
		memberListService.execute(searchWord, model, page);
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
