package shopping.service.memberMyPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpSession;
import shopping.command.MemberCommand;
import shopping.domain.AuthInfoDTO;
import shopping.domain.MemberDTO;
import shopping.mapper.MemberMyMapper;

@Service
public class MemberInfoUpdateService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	MemberMyMapper memberMyMapper;
	
	public int execute(MemberCommand memberCommand, HttpSession session, BindingResult result) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		
		if (!passwordEncoder.matches(memberCommand.getMemberPw(), auth.getUserPw())) {
			result.rejectValue("memberPw", "memberCommand.memberPw", "비밀번호가 틀렸습니다.");
			return 0;
		} else {
			MemberDTO dto = new MemberDTO();
			dto.setMemberAddr(memberCommand.getMemberAddr());
			dto.setMemberAddrDetail(memberCommand.getMemberAddr2());
			dto.setMemberEmail(memberCommand.getMemberEmail());
			dto.setGender(memberCommand.getMemberGender());
			dto.setMemberId(memberCommand.getMemberId());
			dto.setMemberName(memberCommand.getMemberName());
			dto.setMemberPhone1(memberCommand.getMemberPhone1());
			dto.setMemberPhone2(memberCommand.getMemberPhone2());
			dto.setMemberPost(memberCommand.getMemberPost());
			dto.setMemberBirth(memberCommand.getMemberBirth());
			memberMyMapper.memberInfoUpdate(dto);
			return 1;
		}
	}
}
