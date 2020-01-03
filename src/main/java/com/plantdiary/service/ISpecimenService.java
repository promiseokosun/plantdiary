package com.plantdiary.service;

import com.plantdiary.dto.SpecimenDTO;

public interface ISpecimenService {

	/**
	 * Get specimens from persistence layer.
	 * @param id
	 * @return specimen
	 */
	SpecimenDTO fetchById(int id);
	
	/**
	 * Persist the given specimenDTO
	 * @param specimenDTO
	 */
	void save(SpecimenDTO specimenDTO);

}