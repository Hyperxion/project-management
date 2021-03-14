package com.jrp.pma.controllers;

import java.util.List;

import com.jrp.pma.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectRepository proRepo;

	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("allEmployees", employees);
		model.addAttribute("project", new Project());

		return "projects/new-project";
	}
	
	@GetMapping("/list-projects")
	public String listProjects(Model model) {
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projects", projects);
		
		return "/projects/list-projects";
	}

	@PostMapping("/save")
	/*
	Here, @RequestParam must me named exactly as parameter in request body. It is of type Long because we are saving IDs of employees.
	We needed it for @OneToMany relationship to get IDs of employees so we know, which employees to modify
	 */
	public String createProject(Project project, /*@RequestParam List<Long> employees, */ Model model) {
		proRepo.save(project);

		//This is for @OneToMany/@ManyToOne relation
//		//find all employees to which we want to assign project
//		Iterable<Employee> chosenEmployees = empRepo.findAllById(employees);
//
//		for (Employee emp : chosenEmployees) {
//			emp.setProjects(project);//hibernate knows about the relation between project and employee and it will pick and assign projectId only
//			empRepo.save(emp);
//		}

		//use a redirect to prevent duplicate submissions
		//redirects us to specific URL not file
		return "redirect:/projects/list-projects";
	}
}
