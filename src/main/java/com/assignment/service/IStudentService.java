package com.assignment.service;

import com.assignment.model.Student;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface IStudentService {
    public List<Student> getAllStudentList();
    public Student getStudentById(Long id);
    public Student addNewStudent(Student student);
    public Student editStudent(Student student);
    public void deleteStudent(Long id);

}
