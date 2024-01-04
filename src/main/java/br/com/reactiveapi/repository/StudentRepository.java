package br.com.reactiveapi.repository;

import br.com.reactiveapi.model.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {
    Flux<Student> findStudentsByFirstnameIgnoreCase(String firstName);
}
