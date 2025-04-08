package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.repository.AllocationRepository;

@Service
public class AllocationService {
	private final AllocationRepository repository;
	private final AllocationService allocationService;

	public AllocationService(AllocationRepository repository, AllocationService allocationService) {
		this.repository = repository;
		this.allocationService = allocationService;
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

		allocation = allocationService.findById(allocation.getAllocation().getId());

		allocation.setAllocation(allocation);

		return allocation;
	}

	public List<Allocation> findAll(String name) {
		return repository.findAll();
	}

}
