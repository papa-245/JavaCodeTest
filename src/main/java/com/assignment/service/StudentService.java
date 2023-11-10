package com.assignment.service;

import com.assignment.model.Student;
import com.assignment.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    //getting all student information from database
    @Override
    public List<Student> getAllStudentList() {
        return studentRepository.findAll();
    }

    //getting student information by id from database
    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    //saving new student information to database
    @Override
    public Student addNewStudent(Student student) {
        Student savedStu = studentRepository.save(student);
        log.info("Saved Student to database", savedStu.getId());
        return savedStu;
    }

    //editing student information to database
    @Override
    public Student editStudent(Student student) {
        boolean found =studentRepository.existsById(student.getId());
        if(found){
        log.info("Updated Student to database", student.getId());
        Student editedStu = studentRepository.saveAndFlush(student);
        }
        return student;
    }

    //deleting student information from database
    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
        log.info("Deleted Student to database", id );

    }

}
