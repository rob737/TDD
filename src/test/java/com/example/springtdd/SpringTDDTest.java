package com.example.springtdd;

import com.example.springtdd.models.CollegeStudent;
import com.example.springtdd.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
public class SpringTDDTest {

    private static int count = 0;

    @Value("${info.app.name}")
    private String appInfo;

    @Autowired
    CollegeStudent collegeStudent;
    @Autowired
    StudentGrades studentGrades;
    @Autowired
    ApplicationContext context;

    @BeforeEach
    void beforeEach(){
        count = count + 1;
        System.out.println("Testing : " + appInfo + " count: " + count);
        collegeStudent.setFirstname("Robin");
        collegeStudent.setEmailAddress("xyz@abc.com");
        collegeStudent.setLastname("Srivastava");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0, 91.23, 87.9, 91.5)));
        collegeStudent.setStudentGrades(studentGrades);
    }

    @Test
    void basicTest(){
        System.out.println("App name");
        System.out.println(appInfo);
    }

    @Test
    void computeGrade(){
        assertEquals(370.63, studentGrades.addGradeResultsForSingleClass(collegeStudent.getStudentGrades().getMathGradeResults()));
    }

    @Test
    void createStudentWithoutGradesInit(){
        CollegeStudent collegeStudentTwo = context.getBean("collegeStudent",CollegeStudent.class);
        collegeStudentTwo.setFirstname("Ruchika");
        assertEquals("Ruchika",collegeStudentTwo.getFirstname());
        assertAll("Practicing assert all",
                ()->assertEquals(2,2),
                ()->assertFalse(2<0));
    }
}
