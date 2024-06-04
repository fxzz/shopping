package shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import shopping.command.EmployeeCommand;
import shopping.service.employees.EmployeeAutoNumService;
import shopping.service.employees.EmployeeInsertService;
import shopping.service.employees.EmployeeListService;
import shopping.service.employees.EmployeesDeleteService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	
	@Autowired
	EmployeeAutoNumService employeeAutoNumService;
	
	@Autowired
	EmployeeInsertService employeeInsertService;
	
	@Autowired
	EmployeeListService employeeListService;
	
	@Autowired
	EmployeesDeleteService employeesDeleteService;
	
	@GetMapping("employeeList")
	public String empList(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "searchWord", required = false) String searchWord,
			Model model) {
		employeeListService.execute(page, searchWord, model);
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
	
	@PostMapping("empsDelete")
	public String empsDelete(@RequestParam(value = "empDels") String empsDel[]) {
		employeesDeleteService.execute(empsDel);
		return "redirect:employeeList";
	}

}
