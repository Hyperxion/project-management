package com.jrp.pma.dao;



import java.util.List;

import com.jrp.pma.dto.ChartData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jrp.pma.entities.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
	@Override
	public List<Project> findAll();

	@Query(nativeQuery = true, value = "SELECT stage as label, COUNT(*) as value FROM project GROUP BY stage")
	public List<ChartData> getProjectStatus();
}
