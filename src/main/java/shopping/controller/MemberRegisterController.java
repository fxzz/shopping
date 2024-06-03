package shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import shopping.command.MemberCommand;
import shopping.service.memberRegister.UserWriteService;

@Controller
@RequestMapping("register")
public class MemberRegisterController {
	
	@Autowired
	UserWriteService userWriteService;
	


	@GetMapping("userAgree")
	public String agree() {
		return "thymeleaf/memberRegist/userAgree";
	}
	
	@PostMapping("userWrite")
	public String userWrite(@RequestParam(value = "agree", defaultValue = "false") boolean agree, Model model) {
		if (agree == false) {
			return "thymeleaf/memberRegist/userAgree";
		}
		model.addAttribute("memberCommand", new MemberCommand());
		return "thymeleaf/memberRegist/userForm";
	}
	
	@PostMapping("userRegist")
	public String userRegist(@Valid MemberCommand memberCommand, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "thymeleaf/memberRegist/userForm";
		}
		userWriteService.execute(memberCommand, model);
		return "thymeleaf/memberRegist/welcome";
	}
}
