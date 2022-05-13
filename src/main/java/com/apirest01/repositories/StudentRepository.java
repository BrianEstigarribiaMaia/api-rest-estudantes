package com.apirest01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirest01.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
