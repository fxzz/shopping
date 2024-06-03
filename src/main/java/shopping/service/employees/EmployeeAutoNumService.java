package shopping.service.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import shopping.command.EmployeeCommand;
import shopping.mapper.EmployeeMapper;

@Service
public class EmployeeAutoNumService {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	public void execute(Model model, EmployeeCommand employeeCommand) {
		String empNum = employeeMapper.autoNum();
		employeeCommand.setEmpNum(empNum);
		model.addAttribute("employeeCommand", employeeCommand);
	}
}
