package com.plantdiary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plantdiary.dao.IPlantDAO;
import com.plantdiary.dao.ISpecimenDAO;
import com.plantdiary.dto.PlantDTO;
import com.plantdiary.dto.SpecimenDTO;

@Component
public class SpecimenService implements ISpecimenService {

	@Autowired
	IPlantDAO plantDAO;
	
	@Autowired
	ISpecimenDAO specimenDAO;
	
	@Override
	public SpecimenDTO fetchById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(SpecimenDTO specimenDTO) throws Exception {
		// TODO Auto-generated method stub
		specimenDAO.save(specimenDTO);
		return false;
	}

	@Override
	public List<PlantDTO> fetchPlants(String searchTerm) throws Exception {
		// Delegate a call to the PlantDAO 
		return plantDAO.fetch(searchTerm); // Oak will be changed to searchTerm
	}

	@Override
	public void setSpecimenDAO(ISpecimenDAO specimenDAO) {
		// TODO Auto-generated method stub

	}

	@Override
	public ISpecimenDAO getSpecimenDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
