package com.example.springmvc;

import com.example.springmvc.models.CollegeStudent;
import com.example.springmvc.models.GradebookCollegeStudent;
import com.example.springmvc.repository.StudentDao;
import com.example.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class GradebookControllerTest {

    private static MockHttpServletRequest request;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentDao studentDao;

    @Mock
    private StudentAndGradeService studentCreateServiceMock;


    @BeforeAll
    public static void setupServlet(){
        request = new MockHttpServletRequest();
        request.setParameter("firstname","Scooby");
        request.setParameter("lastname","Dooby");
        request.setParameter("emailAddress","scooby@dooby.com");
    }

    @BeforeEach
    void setupDb(){
        jdbcTemplate.execute("insert into student(ID, firstname, lastname, email_address)" +
                " values (0, 'Robin', 'Srivastava', 'robin@srivastav.com')");
    }

    @Test
    void getStudentHttpResponse() throws Exception {
        CollegeStudent collegeStudent = new GradebookCollegeStudent("Robin","Srivastava","robin@srivastav.com");
        CollegeStudent collegeStudentTwo = new GradebookCollegeStudent("Ruchika","Sinha","ruchika@sinha.com");

        List<CollegeStudent> collegeStudents = new ArrayList<>(Arrays.asList(collegeStudent,collegeStudentTwo));
        when(studentCreateServiceMock.getGradeBook()).thenReturn(collegeStudents);

        assertIterableEquals(collegeStudents, studentCreateServiceMock.getGradeBook());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                               .andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        assert modelAndView != null;
        ModelAndViewAssert.assertViewName(modelAndView,"index");
    }


    @Test
    void createStudentHttpRequest() throws Exception{
        CollegeStudent collegeStudentMock = new GradebookCollegeStudent("Robin","Srivastava","robin@srivastav.com");
        List<CollegeStudent> collegeStudents = new ArrayList<>(Collections.singletonList(collegeStudentMock));

        when(studentCreateServiceMock.getGradeBook()).thenReturn(collegeStudents);
        assertIterableEquals(collegeStudents,studentCreateServiceMock.getGradeBook());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .param("firstname",request.getParameterValues("firstname"))
                .param("lastname",request.getParameterValues("lastname"))
                .param("emailAddress",request.getParameterValues("emailAddress"))
        ).andExpect(status().isOk()).andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();
        assert modelAndView != null;
        ModelAndViewAssert.assertViewName(modelAndView,"index");

        CollegeStudent collegeStudent = studentDao.findByEmailAddress("scooby@dooby.com");
        assertNotNull(collegeStudent, "Scooby must be present in DB");
    }

    @Test
    void deleteStudentHttpRequest() throws Exception{
        assertTrue(studentDao.findById(0).isPresent());
        MvcResult mvcResult = mockMvc.
                perform(MockMvcRequestBuilders.delete("/student/{id}",0))
                .andExpect(status().isOk())
                .andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        assert modelAndView != null;
        ModelAndViewAssert.assertViewName(modelAndView,"index");
        assertFalse(studentDao.findById(1).isPresent());
    }

    @AfterEach
    void tearDownDb(){
        jdbcTemplate.execute("delete from student");
    }
}
