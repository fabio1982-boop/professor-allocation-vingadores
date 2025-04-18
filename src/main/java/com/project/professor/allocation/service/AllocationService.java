package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;

@Service
public class AllocationService {
	private final AllocationRepository repository;
	/*
	Isso aqui gera uma referência cíclica (AllocationService -> AllocationService) o que impede
	o backend de subir, ou seja, não foi testado antes de entregar
	
	private final AllocationService allocationService;

	public AllocationService(AllocationRepository repository, AllocationService allocationService) {
		this.repository = repository;
		this.allocationService = allocationService;
	}
	*/
	
	private final ProfessorService professorService;
    private final CourseService courseService;

    public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService,
                             CourseService courseService) {
        this.repository = allocationRepository;
        this.professorService = professorService;
        this.courseService = courseService;
    }

	public Allocation findById(Long id) {
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

	public Allocation save(Allocation allocation) {

		allocation.setId(null);

		return saveInternal(allocation);
	}

	public Allocation update(Allocation allocation) {
		if (repository.existsById(allocation.getId())) {
			return saveInternal(allocation);
		} else {
			
			return null;
		}
	}

	private Allocation saveInternal(Allocation allocation) {
		allocation = repository.save(allocation);

		/*
		Eu entendi que era para fazer a mesma coisa que é feito no serviço de professor,
		mas os relacionamentos de alocação são com professor e curso, então o certo é fazer como
		coloquei a seguir
		
		allocation = allocationService.findById(allocation.getAllocation().getId());

		allocation.setAllocation(allocation);
		*/
		
		Professor professor = professorService.findById(allocation.getProfessor().getId());
        allocation.setProfessor(professor);

        Course course = courseService.findById(allocation.getCourse().getId());
        allocation.setCourse(course);

		return allocation;
	}

	public List<Allocation> findAll(String name) {
		return repository.findAll();
	}

}
