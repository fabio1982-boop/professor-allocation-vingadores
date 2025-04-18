package com.project.professor.allocation.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;

@Service
public class DepartmentService {
	private final DepartmentRepository repository;
	/*
	Isso aqui gera uma referência cíclica (courseService -> courseService) o que impede
	o backend de subir, ou seja, não foi testado antes de entregar
	
	private final DepartmentService departmentService;

	public DepartmentService(DepartmentRepository repository, DepartmentService departmentService) {
		this.repository = repository;
		this.departmentService = departmentService;
	}
	*/
	public DepartmentService(DepartmentRepository repository) {
		this.repository = repository;
	}

	public Department findById(Long id) {
	    return repository.findById(id).orElse(null);
	}
	


	public void deleteById(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		}
	}

	public void deleteAll() {
		repository.deleteAllInBatch();

	}

	public Department save(Department department) {

		department.setId(null);

		return saveInternal(department);
	}

	public Department update(Department department) {
		if(repository.existsById(department.getId())) {
		return saveInternal(department);
		}else {
			return null;
		}
	}
	
	private Department saveInternal(Department department) {
		department = repository.save(department);

		/*
		A classe de departamento não contém relacionamento dentro dela,
		ou seja, não precisa preencher nada
		
		department = departmentService.findById(department.getDepartment().getId());

		department.setDepartment(department);
		*/

		return department;
	}

	public List<Department> findAll(String name) {
		return repository.findAll();
	}
}
