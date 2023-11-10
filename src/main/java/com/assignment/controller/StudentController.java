package com.assignment.controller;

import com.assignment.model.Student;
import com.assignment.service.IStudentService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;
import java.util.List;



@Controller
@Slf4j
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping({"/","/backHome"})
    public String openStudentRegForm(Model model) {
        model.addAttribute("student", new Student());
        return "studentRegistrationForm";

    }

    //register new student
    @PostMapping({"/register"})
    public String registerStudent(@ModelAttribute("student") Student student, Model model) {
        Student savedStu = studentService.addNewStudent(student);
        List<Student> studentList = studentService.getAllStudentList();
        model.addAttribute("studentList", studentList);
        return "studentData";
    }

    //editing student information
    @PostMapping("/edit/{student}")
    //@RequestMapping(value = { "/edit" }, method = RequestMethod.GET) /register/{student}/edit
    public String editStudent(@RequestBody Student student, Model model) {
        Student editedStu = studentService.editStudent(student);
        List<Student> studentList = studentService.getAllStudentList();
        model.addAttribute("studentList", studentList);
        return "studentData";
    }

    @GetMapping("/register/delete/{id}")
    public String deleteStudent(@PathVariable Long id,  Model model) {
        studentService.deleteStudent(id);
        String  message = "Deleted  successfully !";
        List<Student> studentList = studentService.getAllStudentList();
        model.addAttribute("studentList", studentList);
        return "studentData";
    }

    //printing student information
    @GetMapping("/register/print")
    public String printJasperReport(Model model) throws JRException, FileNotFoundException {
        List<Student> studentList = studentService.getAllStudentList();
        model.addAttribute("studentList", studentList);
        return "forexView";
    }

}
