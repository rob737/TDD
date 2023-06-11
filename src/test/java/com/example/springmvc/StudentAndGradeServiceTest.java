package com.example.springmvc;

import com.example.springmvc.models.CollegeStudent;
import com.example.springmvc.repository.StudentDao;
import com.example.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/*
* Since, we have included H2 as a dependency in pom,
* spring boot will auto configure H2 as our primary db for test cases.
* So, save method that we have used below is storing data in H2 DB
* and corresponding fetch is fetching it from H2 DB.
* */
@PropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    StudentAndGradeService studentService;
    @Autowired
    StudentDao studentDao;

    @BeforeEach
    void setupDb(){
        jdbcTemplate.execute("insert into student(ID, firstname, lastname, email_address)" +
                " values (0, 'Robin', 'Srivastava', 'robin@srivastav.com')");
    }

    @Test
    void createStudentService(){
        studentService.createStudent("Robin","Srivastava","robin@srivastava.com");
        CollegeStudent collegeStudent = studentDao.findByEmailAddress("robin@srivastava.com");
        assertEquals("robin@srivastava.com",collegeStudent.getEmailAddress());
    }

    @Test
    void checkIsStudentNullCheck(){
        assertFalse(studentService.checkIfStudentIsNull(0));
        assertTrue(studentService.checkIfStudentIsNull(1));
    }

    @Test
    void deleteStudentById(){
        Optional<CollegeStudent> student = studentDao.findById(0);
        assertTrue(student.isPresent());
        studentService.deleteStudentById(0);
    }

    @AfterEach
    void tearDownDb(){
        jdbcTemplate.execute("delete from student");
    }
}
