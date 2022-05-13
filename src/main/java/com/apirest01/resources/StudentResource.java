package com.apirest01.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.apirest01.entities.Student;
import com.apirest01.exceptions.StudentException;
import com.apirest01.repositories.StudentRepository;

@RestController
public class StudentResource {

	@Autowired //injetar repositorio
	private StudentRepository repository;
	
	@GetMapping("/students") //mostrar todos estudantes
	public List<Student> findAll(){
		return repository.findAll();
	}
	
	@GetMapping("/students/{id}") //mostrar estudante especifico
	public Student findById(@PathVariable long id) {
		Optional<Student> student = repository.findById(id);
		
		if(!student.isPresent()) {
			throw new StudentException("Id: " + id);
		}
		
		return student.get();
	}
	
	@DeleteMapping("/students/{id}")//excluir estudante especifico
	public void deleteStudent(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	@PostMapping("/students") //criar estudante
	public ResponseEntity<Object> createStudent(@RequestBody Student student){
		Student savedStudent = repository.save(student);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/students/{id}")//atualizar estudante especifico
	public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id){
		Optional<Student> studentOptional = repository.findById(id);
		
		if(!studentOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		student.setId(id);
		
		repository.save(student);
		
		return ResponseEntity.ok().body(student);
		
	}
}
