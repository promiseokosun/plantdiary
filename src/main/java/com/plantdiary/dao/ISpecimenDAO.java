package com.plantdiary.dao;

import com.plantdiary.dto.SpecimenDTO;

public interface ISpecimenDAO {
	
	boolean save(SpecimenDTO specimenDTO) throws Exception;

	Iterable<SpecimenDTO> fetchAll() throws Exception;

}
