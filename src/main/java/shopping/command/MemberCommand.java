package shopping.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberCommand {

	
		String memberNum;
		@NotEmpty(message = "아이디를 입력해 주세요.")
		String memberId;
		// 영문자와 특수문자 그리고 숫자를 사용하고 4글자 이상
		@Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#!@$%^&*-+?~]).{4,}$",
				message = "영문자와 숫자 그리고 특수문자가 포함된 4글자 이상")
		String memberPw;
		@NotBlank(message = "비밀번호 확인을 입력해 주세요.")
		String memberPwCon;
		String memberName;
		@NotBlank(message = "주소를 입력해 주세요.")
		String memberAddr;
		String memberAddr2;
		String memberPost;
		String memberGender;
		@NotEmpty(message = "연락처를 입력해 주세요.")
		String memberPhone1;
		String memberPhone2;
		@NotEmpty(message = "이메일을 입력해 주세요.")
		String memberEmail;
		
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@NotNull(message = "생년월일을 입력해 주세요.")
		Date memberBirth;
		
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		Date memberRegiDate;
		
		Integer point;
		public boolean isMemberPwEqualsMemberPwCon() {
			return memberPw.equals(memberPwCon);
		}

}
