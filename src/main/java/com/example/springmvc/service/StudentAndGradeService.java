package com.example.springmvc.service;

import com.example.springmvc.models.CollegeStudent;
import com.example.springmvc.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentAndGradeService {

    @Autowired
    private StudentDao studentDao;

    public void createStudent(String firstName, String lastName, String email){
        CollegeStudent collegeStudent = new CollegeStudent(firstName,lastName,email);
        collegeStudent.setId(0);
        studentDao.save(collegeStudent);
    }
}
