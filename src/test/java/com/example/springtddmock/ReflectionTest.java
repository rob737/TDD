package com.example.springtddmock;

import com.example.springtddmock.models.CollegeStudent;
import com.example.springtddmock.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReflectionTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    CollegeStudent collegeStudent;

    @Autowired
    StudentGrades studentGrades;

    @BeforeEach
    void studentBeforeEach(){
        collegeStudent.setFirstname("Robin");
        collegeStudent.setEmailAddress("robin@robin.com");
        collegeStudent.setStudentGrades(studentGrades);

        // using reflection to access private members
        ReflectionTestUtils.setField(collegeStudent,"lastname","Srivastava");
        ReflectionTestUtils.setField(collegeStudent,"id",1);
    }

    @Test
    void getPrivateFieldData(){
        assertEquals("Srivastava",ReflectionTestUtils.getField(collegeStudent,"lastname"));
    }

    @Test
    void invokePrivateMethod(){
    assertEquals("Robin : 1", ReflectionTestUtils.invokeMethod(collegeStudent,"getFirstNameAndId"));
    }
}
