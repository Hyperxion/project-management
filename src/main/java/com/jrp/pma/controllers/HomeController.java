package com.jrp.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;



@Controller
public class HomeController {

	@Value("${version}")
	private String ver;

	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {

		model.addAttribute("versionNumber", ver);
		//Map that contains column name and the actual data - whatever that data is
		Map<String, Object> map = new HashMap<>();

		List<Project> projects = proRepo.findAll();
		model.addAttribute("projects", projects);

		List<ChartData> projectData = proRepo.getProjectStatus();

		//Converting projectData object into JSON to use in java script
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);

		model.addAttribute("projectStatusCnt", jsonString);

		//Before performing custom query
		//List<Employee> employees = empRepo.findAll();

		//After performing custom query
		List<EmployeeProject> employeesProjectCount = empRepo.employeeProjects();
		model.addAttribute("employeesListProjectCount", employeesProjectCount);
		
		return "main/home";
	}
	
}
