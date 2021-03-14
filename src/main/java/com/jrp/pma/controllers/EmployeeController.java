package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		
		model.addAttribute("employee", new Employee());
		
		return "employees/new-employee";
	}
	
	@GetMapping("/list-employees")
	public String listEmployees(Model model) {
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("employees", employees);
		//System.out.println(employees.get(0).getFirstName());
		return "/employees/list-employees";
	}
	
	@PostMapping("/save")
	public String createEmployee(Employee employee, Model model) {
		empRepo.save(employee);
		
		//use a redirect to prevent duplicate submissions
		return "redirect:/employees/new";
	}
}
