package shopping.service.help;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import shopping.domain.AuthInfoDTO;
import shopping.mapper.FindMapper;
import shopping.service.EmailSendService;

@Service
public class FindPwService {
	
	@Autowired
	FindMapper findMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	EmailSendService emailSendService;
	
	public void execute(String userId, String userPhone, Model model) {
		AuthInfoDTO au = new AuthInfoDTO();
		AuthInfoDTO dto = findMapper.userEmail(userId, userPhone);
		if (dto != null) {
			String newPw = UUID.randomUUID().toString().substring(0, 8);
			dto.setUserId(userId);
			dto.setUserPw(passwordEncoder.encode(newPw));
			if (dto.getGrade().equals("mem")) { //회원인 경우
				dto.setTableName("members");
				dto.setPwColumName("member_pw");
				dto.setUserIdColumName("member_id");
			} else { // 직원인 경우
				dto.setTableName("employees");
				dto.setPwColumName("emp_pw");
				dto.setUserIdColumName("emp_id");
			}
			
			int i = findMapper.pwUpdate(dto);
			model.addAttribute("dto", dto);
			String html = "<html><body>"
					+ dto.getUserEmail() + "님의 임시 번호는 " + newPw
					+ "입니다. </body></html>";
			String subject = dto.getUserEmail() + "의 임시비밀번호";
			String fromEmail = "fxzzy3@gmail.com";
			String toEmail = dto.getUserEmail();
			emailSendService.mailsend(html, subject, fromEmail, toEmail);
		}
	}
	
}
