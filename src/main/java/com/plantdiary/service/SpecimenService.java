package com.plantdiary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.plantdiary.dao.IPhotoDAO;
import com.plantdiary.dao.IPlantDAO;
import com.plantdiary.dao.ISpecimenDAO;
import com.plantdiary.dto.PhotoDTO;
import com.plantdiary.dto.PlantDTO;
import com.plantdiary.dto.SpecimenDTO;

@Component
public class SpecimenService implements ISpecimenService {

	@Autowired
	IPlantDAO plantDAO;
	
	@Autowired
	ISpecimenDAO specimenDAO;
	
	@Autowired
	IPhotoDAO photoDAO;
	
	
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
	@Cacheable("fetchPlants")
	public List<PlantDTO> fetchPlants(String searchTerm) throws Exception {
		// Delegate a call to the PlantDAO 
		return plantDAO.fetch(searchTerm); // from plantplaces.com 
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
	
	@Override
	public Iterable<SpecimenDTO> fetchAllSpecimens() throws Exception{
		return specimenDAO.fetchAll();
	}

	@Override
	public void saveImage(MultipartFile imageFile, PhotoDTO photoDTO) throws Exception {
		// TODO 
		photoDAO.savePhotoImage(photoDTO, imageFile); // produce the filePath
		photoDAO.save(photoDTO); // consume the filePath
	}
	

}
