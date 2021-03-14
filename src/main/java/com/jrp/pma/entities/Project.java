package com.jrp.pma.entities;

import java.util.ArrayList;
import java.util.List;

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


@Entity
public class Project {

	@Id
	//AUTO - hibernate keeps track of IDs and increments values
	//@GeneratedValue(strategy = GenerationType.AUTO)

	//IDENTITY - Hibernate lets database to manage ID value generation
	/*
	Using IDENTITY we loose hibernate batch feature. If we are about to update hundreds or thousands of records,
	it will be a bit slower

	//SEQUENCE - Application will rely on database sequence with name specified in generator parameter created by dbadmin.
	We do not loose hibernate feature of batch updates using this option
	 */
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
	@SequenceGenerator(name = "project_seq", allocationSize = 1)
	private long projectId;
	private String name;
	private String stage; //NOTSTARTED, COMPLETED, INRPOGRESS
	private String description;

	//For @OneToMany relation
//	//mapped by field in Employee entity
//	@OneToMany(mappedBy="project")
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
			fetch = FetchType.LAZY)
	//this will be 3rd table created by JPA that manages relation between projects and employees
	//We have to specify column on which data will be joined (usually primary keys) with joinColumns (column of current table/entity
	//and inverseJoinColumns - column of foreign table/entity)
	//We have to specify this in employee entity too
	@JoinTable(name="project_employee",
				joinColumns = @JoinColumn(name = "project_id"),
				inverseJoinColumns = @JoinColumn(name = "employee_id"))
	private List<Employee> employees;
	
	public Project() {
		
	}	
	
	public Project(String name, String stage, String description) {
		super();
		this.name = name;
		this.stage = stage;
		this.description = description;
	}

	public void addEmployee(Employee e){
		if (employees == null){
			employees = new ArrayList<>();
		}
		this.employees.add(e);
	}

	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}
