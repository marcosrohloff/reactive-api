package br.com.reactiveapi.controller;

import br.com.reactiveapi.model.Student;
import br.com.reactiveapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/save")
    public Mono<Student> saveStudent(@RequestBody Student student){
        return studentService.saveSingleStudent(student);
    }

    @PostMapping("/saveAll")
    public Flux<Student> saveAll(@RequestBody Flux<Student> students) {
        return studentService.saveAllStudents(students);
    }

    // Fetch single entity
    @GetMapping("/student/{id}")
    public Mono<Student> fetchStudent(@PathVariable Long id){
        return studentService.findStudentById(id);
    }

    // Fetch Multiple students from database
    @GetMapping("/allStudents")
    public Flux<Student> getAllStudents(){
        return studentService.findAllStudents();
    }

    @PutMapping("/student/{id}")
    public Mono<Student> updateStudent(@RequestBody Student student, @PathVariable Long id){
        return studentService.updateStudent(student,id);
    }

    @DeleteMapping("student/{id}")
    public String deleteStudent(@PathVariable Long id){
        return studentService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllDatabase(){
        studentService.deleteAll();
    }

}
