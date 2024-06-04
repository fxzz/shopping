package shopping.service.employees;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import shopping.domain.EmployeeDTO;
import shopping.domain.StartEndPageDTO;
import shopping.mapper.EmployeeMapper;
import shopping.service.StartEndPageService;

@Service
public class EmployeeListService {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	StartEndPageService startEndPageService;
	
	String searchWord;
	public void execute(int page, String searchWord, Model model) {
		
		if(searchWord != null) {
			this.searchWord = searchWord.trim();
		}
		
		StartEndPageDTO sepDTO = startEndPageService.execute(page, this.searchWord);
		List<EmployeeDTO> list = employeeMapper.employeeAllSelect(sepDTO);
		
		int count = employeeMapper.employeeCount(this.searchWord);
		startEndPageService.execute(page, count, model, list, this.searchWord);
		model.addAttribute("list", list);
	}
}
