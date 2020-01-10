package com.plantdiary.dao;

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


}
