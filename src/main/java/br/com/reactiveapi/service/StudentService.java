package br.com.reactiveapi.service;

import br.com.reactiveapi.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {

    // save all students to the database
    Flux<Student> saveAllStudents(Flux<Student> students);

    Mono<Student> updateStudent(Student student, Long id);

    // save multiple entries to the database
    Mono<Student> saveSingleStudent(Student student);

    // Fetch student record from database
    Mono<Student> findStudentById(Long id);

    // fetch multiple records from database
    Flux<Student> findAllStudents();

    //delete single student by id referenced
    public String deleteById(Long id);

    // delete all entries in the database
    void deleteAll();
}
