package shopping.service.memberMyPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import shopping.domain.AuthInfoDTO;
import shopping.mapper.MemberMyMapper;

@Service
public class MemberPwModifyService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	MemberMyMapper memberMyMapper;
	
	public String execute(HttpSession session, Model model, String memberPw) {
	
		AuthInfoDTO authInfoDTO = (AuthInfoDTO) session.getAttribute("auth");
		
		if (passwordEncoder.matches(memberPw, authInfoDTO.getUserPw())) {
			return "thymeleaf/memberShip/myNewPw";
		} else {
			model.addAttribute("errPw", "비밀번호가 틀렸습니다");
			return "thymeleaf/memberShip/myPwCon";
		}
		
	}
}
