package br.com.reactiveapi.service.impl;

import br.com.reactiveapi.exception.ResourceNotFoundException;
import br.com.reactiveapi.model.Student;
import br.com.reactiveapi.repository.StudentRepository;
import br.com.reactiveapi.service.StudentService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Flux<Student> saveAllStudents(Flux<Student> students) {
        return studentRepository.saveAll(students);
    }

    @Override
    public Mono<Student> updateStudent(Student student, Long studentId) {
        return studentRepository.findById(studentId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("No such student with id: " + studentId)))
                .flatMap(existingStudent -> {
                    //update student new values here
                    existingStudent.setFirstname(student.getFirstname());
                    existingStudent.setLastname(student.getLastname());
                    existingStudent.setAge(student.getAge());

                    return studentRepository.save(existingStudent);
                });
    }

    @Override
    public Mono<Student> saveSingleStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Mono<Student> findStudentById(Long id) {
        return studentRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("student not found with Id: " + id)));
    }

    @Override
    public Flux<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public String deleteById(Long id) {
        return studentRepository.deleteById(id)
                .then(Mono.just("student with id: " + id + "deleted"))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("No such student with id: " + id)))
                .block();
    }

    @Override
    public  void deleteAll() {
        studentRepository.deleteAll()
                .then(Mono.just("All data deleted"))
                .subscribe();
    }
}
