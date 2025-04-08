package com.project.professor.allocation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "/professores")


public class ProfessorContoller {
	

	 @GetMapping
	 (produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<Professor>> findAll() {
	        List<Professor> professores = new ArrayList<>();

	        Professor prof = new Professor();
	        Department department = new Department(0, null);
	        prof.setId(1L);
	        prof.setName("João Silva");
	        department.setName("Matematica"); 
	        prof.setDepartment(department);
	        prof.setEmail("joao.silva@universidade.com");

	        professores.add(prof);

	        return new ResponseEntity<>(professores, HttpStatus.OK);  
	        
	 }
	  @GetMapping(path = "/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Professor> findById(@PathVariable(name = "professor_id") Long id) {
		  Professor professor = new Professor();
	        Department department = new Department(id, null);
		  professor.setId(id);
	        professor.setName("Dr. João Silva");
	        department.setName("Matematica"); 
	        professor.setDepartment(department); 
	        professor.setEmail("joao.silva@universidade.com");
	        if (id % 2 == 0) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        } else {
	            return new ResponseEntity<>(professor, HttpStatus.OK);
	        }
	    }
	  
@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Professor> save(@RequestBody Professor professor) {
    professor.setId(100L);  
    return ResponseEntity.ok(professor);
}
@Operation(summary = "Update a professor")
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "OK"),
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
})
@PutMapping(path = "/{professor_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Professor> update(@PathVariable(name = "professor_id") Long id, @RequestBody Professor professor) {
    professor.setId(id);
    professor.setName("Dr. João Silva (Atualizado)");
    return ResponseEntity.ok(professor);
}
@DeleteMapping(path = "/{professor_id}")
public ResponseEntity<Void> deleteById(@PathVariable(name = "professor_id") Long id) {
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

@DeleteMapping
public ResponseEntity<Void> deleteAll() {
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
}








