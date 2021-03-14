package com.jrp.dao;

import com.jrp.pma.ProjectManagementApplication;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

//Here we specify correct application context so that all the required beans are loaded
@ContextConfiguration(classes = ProjectManagementApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest
//Specify the files we want to be run
//Runs configured SQL files at a particular phase
//We dont need to run drop.sql because hibernate automatically tries to drop tables existing in schema.sql just in case they already exist
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts={"classpath:schema.sql", "classpath:data.sql"})/*,
           @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:drop.sql")*/})
public class ProjectRepositoryIntegrationTest {

    @Autowired
    ProjectRepository proRepo;

    @Test
    public void ifNewProjectSaved_thenSuccess() {
        Project project = new Project("New Test Project", "COMPLETE", "Test Description");
        proRepo.save(project);

        //After this insertion we should have 5 projects
        assertEquals(5, proRepo.findAll().size());
    }


}
