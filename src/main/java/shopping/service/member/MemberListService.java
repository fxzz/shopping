package shopping.service.member;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import shopping.domain.MemberDTO;
import shopping.mapper.MemberMapper;

@Service
public class MemberListService {
	
	@Autowired
	MemberMapper memberMapper;
	
	public void execute(String searchWord, Model model) {
		List<MemberDTO> list = memberMapper.selectAll(searchWord);
		model.addAttribute("dtos", list);
		
		model.addAttribute("searchWord", searchWord);
	}
}
