package shopping.service.employees;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import shopping.domain.EmployeeDTO;
import shopping.mapper.EmployeeMapper;

@Service
public class EmployeeListService {
	
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(Model model) {
		List<EmployeeDTO> list = employeeMapper.employeeAllSelect();
		model.addAttribute("list", list);
	}
}
