package com.plantdiary.dao;

import java.util.List;

import com.plantdiary.dto.SpecimenDTO;

public interface ISpecimenDAO {
	
	boolean save(SpecimenDTO specimenDTO) throws Exception;

	Iterable<SpecimenDTO> fetchAll() throws Exception;

	List<SpecimenDTO> fetchSpecimensByPlantId(int plantId);

}
