package com.jrp.pma.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import java.util.List;

@Entity
public class Employee {
	
	@Id
	//AUTO - hibernate keeps track of IDs and increments values
	//@GeneratedValue(strategy = GenerationType.AUTO)

	//IDENTITY - Hibernate lets database to manage ID value generation
	/*
	Using IDENTITY we loose hibernate batch feature. If we are about to update hundreds or thousands of records,
	it will be a bit slower
	 */
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
	@SequenceGenerator(name = "employee_seq", allocationSize = 1)
	private long employeeId;
	
	private String firstName;
	private String lastName;
	private String email;

	//effect of actions is applied to childs (i. e. if you do CascadeType.REMOVE and you remove project, also all records with respective foreign key
	//will be deleted
	//LAZY approach does not load associated children into memory
	//EAGER loads associated children into memory. It will load projects but also all employees related to projects - this slows down application

	//For @ManyToOne
//	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
//			   fetch = FetchType.LAZY)
//	@JoinColumn(name="project_id")
//	private Project project;
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
			   fetch = FetchType.LAZY)
	@JoinTable(name="project_employee",
			joinColumns = @JoinColumn(name = "employee_id"),
			//inverse join column from employee perspective will be project_id
			inverseJoinColumns = @JoinColumn(name = "project_id"))
	private List<Project> projects;


	public Employee() {
		
	}
	
	public Employee(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> project) {
		this.projects = project;
	}
}
