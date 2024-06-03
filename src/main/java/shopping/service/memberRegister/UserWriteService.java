package shopping.service.memberRegister;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import shopping.command.MemberCommand;
import shopping.domain.MemberDTO;
import shopping.mapper.UserMapper;
import shopping.service.EmailSendService;

@Service
public class UserWriteService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserMapper userMapper;
	@Autowired
	EmailSendService emailSendService;

	public void execute(MemberCommand memberCommand, Model model) {
		String memberId = memberCommand.getMemberId();
		String memberPw = memberCommand.getMemberPw();
		String memberName = memberCommand.getMemberName();
		String memberPhone1 = memberCommand.getMemberPhone1();
		String memberPhone2 = memberCommand.getMemberPhone2();
		String memberAddr = memberCommand.getMemberAddr();
		String memberAddrDetail = memberCommand.getMemberAddr2();
		String memberPost = memberCommand.getMemberPost();
		String memberEmail = memberCommand.getMemberEmail();
		Date memberBirth = memberCommand.getMemberBirth();
		String memberGender = memberCommand.getMemberGender();
		
		MemberDTO dto = new MemberDTO();
		dto.setMemberAddr(memberAddr);
		dto.setMemberAddrDetail(memberAddrDetail);
		dto.setMemberEmail(memberEmail);
		dto.setGender(memberGender);
		dto.setMemberId(memberId);
		dto.setMemberName(memberName);
		dto.setMemberPhone1(memberPhone1);
		dto.setMemberPhone2(memberPhone2);
		dto.setMemberPost(memberPost);
		dto.setMemberPw(passwordEncoder.encode(memberPw));//비밀번호 암호화 하기
		dto.setMemberBirth(memberBirth);
		int i = userMapper.userInsert(dto);
		model.addAttribute("userName", dto.getMemberName());
		model.addAttribute("userEmail", dto.getMemberEmail());
		
		if(i >= 1) {
			/// 메일링
			String html= "<html><body>"
					+ "    회원 가입을 축하합니다. <br />"
					+ " 가입을 완료하시려면 "
					+ "<a href='http://localhost:8088/userConfirm?chk=" + dto.getMemberEmail() 
					+ "'>여기</a>"
					+ "를 눌러주세요";
			String subject = "환영 인사입니다.";
			String fromEmail = "fxzzy3@gmail.com"; // 보내는 사람
			String toEmail = dto.getMemberEmail(); // 받는 사람
			
			
			
			emailSendService.mailsend(html, subject, fromEmail, toEmail);
			
		}
	}
}
