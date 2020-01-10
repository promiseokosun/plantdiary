package com.plantdiary.service;

import java.util.List;

import com.plantdiary.dao.ISpecimenDAO;
import com.plantdiary.dto.PlantDTO;
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
	boolean save(SpecimenDTO specimenDTO) throws Exception;
	
	/**
	 * Return a list of plants that contain this String.
	 * @param string is the search criteria: can be genus, species, or common.
	 * @return a list of matching plants.
	 */
	List<PlantDTO> fetchPlants(String searchTerm) throws Exception;

	void setSpecimenDAO(ISpecimenDAO specimenDAO);

	ISpecimenDAO getSpecimenDAO();

	Iterable<SpecimenDTO> fetchAllSpecimens() throws Exception;

}