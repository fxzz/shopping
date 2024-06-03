package shopping.service.member;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import shopping.domain.MemberDTO;
import shopping.domain.StartEndPageDTO;
import shopping.mapper.MemberMapper;
import shopping.service.StartEndPageService;

@Service
public class MemberListService {
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	StartEndPageService startEndPageService;
	
	String searchWord;
	
	public void execute(String searchWord, Model model, int page) {
		
		if (searchWord != null) {
			this.searchWord = searchWord.trim();
		}
		
		StartEndPageDTO sepDTO = startEndPageService.execute(page, this.searchWord);
		List<MemberDTO> list = memberMapper.selectAll(sepDTO);
		int count = memberMapper.memberCount(this.searchWord);
		startEndPageService.execute(page, count, model, list, this.searchWord);
		
		
	}
}
