package com.example.springmvc.controller;

import com.example.springmvc.models.CollegeStudent;
import com.example.springmvc.models.Gradebook;
import com.example.springmvc.service.StudentAndGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GradebookController {

	@Autowired
	private Gradebook gradebook;

	@Autowired
	private StudentAndGradeService studentAndGradeService;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getStudents(Model m) {
		Iterable<CollegeStudent> collegeStudents = studentAndGradeService.getGradeBook();
		m.addAttribute("students",collegeStudents);
		return "index";
	}

	@GetMapping("/studentInformation/{id}")
	public String studentInformation(@PathVariable int id, Model m) {
		return "studentInformation";
		}

	@PostMapping("/")
	public String createStudent(@ModelAttribute("student") CollegeStudent student, Model model){
		studentAndGradeService.createStudent(student.getFirstname(),student.getLastname(),student.getEmailAddress());
		Iterable<CollegeStudent> collegeStudents = studentAndGradeService.getGradeBook();
		model.addAttribute("students",collegeStudents);
		return "index";
	}

	@DeleteMapping("/student/{id}")
	public String deleteStudentById(@PathVariable int id, Model m){
		studentAndGradeService.deleteStudentById(id);
		Iterable<CollegeStudent> collegeStudents = studentAndGradeService.getGradeBook();
		m.addAttribute("students",collegeStudents);
		return "index";
	}
}
