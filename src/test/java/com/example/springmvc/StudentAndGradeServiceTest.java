package com.example.springmvc;

import com.example.springmvc.models.CollegeStudent;
import com.example.springmvc.repository.StudentDao;
import com.example.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
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

    @Sql("/insertData.sql")
    @Test
    void loadDataFromSQL(){
        Iterable<CollegeStudent> iterable = studentService.getGradeBook();
        List<CollegeStudent> collegeStudents = new ArrayList<>();

        for(CollegeStudent student : iterable){
            collegeStudents.add(student);
        }
        assertEquals(5, collegeStudents.size());
    }

    @AfterEach
    void tearDownDb(){
        jdbcTemplate.execute("delete from student");
    }
}
