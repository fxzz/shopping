package shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import shopping.command.EmployeeCommand;
import shopping.service.employees.EmployeeAutoNumService;
import shopping.service.employees.EmployeeInsertService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	
	@Autowired
	EmployeeAutoNumService employeeAutoNumService;
	
	@Autowired
	EmployeeInsertService employeeInsertService;
	
	@GetMapping("employeeList")
	public String empList() {
		return "thymeleaf/employee/employeeList";
	}
	
	@GetMapping("empRegist")
	public String from(Model model) {
		employeeAutoNumService.execute(model, new EmployeeCommand());
		return "thymeleaf/employee/employeeForm";
	}
	
	@PostMapping("empRegist")
	public String from(@Valid EmployeeCommand employeeCommand, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return "thymeleaf/employee/employeeForm";
		}
		
		if (!employeeCommand.isEmpPwEqualsEmpPwCon()) {
			
			return "thymeleaf/employee/employeeForm";
		}
		
		employeeInsertService.execute(employeeCommand);
		return "redirect:employeeList";
	}

}
