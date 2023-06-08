package com.example.springtddmock;

import com.example.springtddmock.dao.ApplicationDao;
import com.example.springtddmock.models.CollegeStudent;
import com.example.springtddmock.models.StudentGrades;
import com.example.springtddmock.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/*
* Use SpringBoot @MockBean when you need to inject mocks
* and inject regular beans from app context.
* */
@SpringBootTest
public class SpringMockTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    CollegeStudent collegeStudent;

    @Autowired
    StudentGrades studentGrades;

   /* @Mock
    private ApplicationDao applicationDao;

    // Will inject the objects annotated with @Mock or @Spy into applicationService.
    @InjectMocks
    private ApplicationService applicationService;*/

    @MockBean
    private ApplicationDao applicationDao;

    @Autowired
    private ApplicationService applicationService;

    @BeforeEach
    void beforeEach(){
        collegeStudent.setFirstname("Robin");
        collegeStudent.setEmailAddress("robin@xyz.com");
        collegeStudent.setStudentGrades(studentGrades);
    }

    @Test
    void assertEqualsTestAddGrades(){
        when(applicationDao.addGradeResultsForSingleClass(studentGrades.getMathGradeResults()))
                .thenReturn(100.00);
        assertEquals(100,applicationService.addGradeResultsForSingleClass(
                collegeStudent.getStudentGrades().getMathGradeResults())
        );
        verify(applicationDao).addGradeResultsForSingleClass(studentGrades.getMathGradeResults());
    }

    @DisplayName("Test Exception")
    @Test
    void testException(){
        CollegeStudent nullStudent = (CollegeStudent) context.getBean("collegeStudent");
        doThrow(new RuntimeException()).when(applicationDao).checkNull(nullStudent);
        assertThrows(RuntimeException.class,
                ()->{
                    applicationService.checkNull(nullStudent);
                });
    }

}
