package com.plantdiary.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.plantdiary.dto.SpecimenDTO;

public interface ISpecimenRepository extends CrudRepository<SpecimenDTO, Integer> {
	List<SpecimenDTO> findByPlantId(int plantId); // findByXXX() is the magic

}
