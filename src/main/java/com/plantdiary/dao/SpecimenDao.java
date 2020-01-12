package com.plantdiary.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plantdiary.dto.SpecimenDTO;

@Component
public class SpecimenDao implements ISpecimenDAO {
	
	@Autowired
	ISpecimenRepository specimenRepository;
	
	@Override
	public boolean save(SpecimenDTO specimenDTO) throws Exception {
		// TODO Auto-generated method stub
		specimenRepository.save(specimenDTO);
		return false;
	}
	
	@Override
	public Iterable<SpecimenDTO> fetchAll() throws Exception {
		return specimenRepository.findAll();
	}

	@Override
	public List<SpecimenDTO> fetchSpecimensByPlantId(int plantId) {
		// TODO Auto-generated method stub
		return specimenRepository.findByPlantId(plantId);
	}


}
