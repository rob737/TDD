package com.example.springmvc;

import com.example.springmvc.models.CollegeStudent;
import com.example.springmvc.repository.StudentDao;
import com.example.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@PropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    private StudentAndGradeService studentService;
    @Autowired
    private StudentDao studentDao;

    @Test
    void createStudentService(){
        studentService.createStudent("Robin","Srivastava","robin@srivastava.com");
        CollegeStudent collegeStudent = studentDao.findByEmailAddress("robin@srivastava.com");
        assertEquals("robin@srivastava.com",collegeStudent.getEmailAddress());
    }
}
