package com.example.springmvc.service;

import com.example.springmvc.models.CollegeStudent;
import com.example.springmvc.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StudentAndGradeService {

    @Autowired
    StudentDao studentDao;

    public void createStudent(String firstName, String lastName, String email){
        CollegeStudent collegeStudent = new CollegeStudent(firstName,lastName,email);
        collegeStudent.setId(2);
        studentDao.save(collegeStudent);
    }

    public boolean checkIfStudentIsNull(int id) {
        Optional<CollegeStudent> student = studentDao.findById(id);
        return student.isEmpty();
    }

    public void deleteStudentById(int id) {
        studentDao.deleteById(id);
    }
}
