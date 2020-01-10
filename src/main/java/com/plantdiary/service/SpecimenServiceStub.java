package com.plantdiary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plantdiary.dao.ISpecimenDAO;
import com.plantdiary.dto.PlantDTO;
import com.plantdiary.dto.SpecimenDTO;

@Component
public class SpecimenServiceStub implements ISpecimenService {
	
	@Autowired
	private ISpecimenDAO specimenDAO;
	
	/* (non-Javadoc)
	 * @see com.plantdiary.service.ISpecimenService#fetchById(int)
	 */
	@Override
	public SpecimenDTO fetchById(int id){
		
		SpecimenDTO specimenDTO = new SpecimenDTO();
		specimenDTO.setSpecimenId(43);
		return specimenDTO;
	}
	
	/* (non-Javadoc)
	 * @see com.plantdiary.service.ISpecimenService#save(com.plantdiary.dto.SpecimenDTO)
	 */
	@Override
	public boolean save(SpecimenDTO specimenDTO) throws Exception{
		boolean result = specimenDAO.save(specimenDTO);
		return result;
	}


	@Override
	public List<PlantDTO> fetchPlants(String searchTerm) {
		// stub out a plant fetch mechanism.
		List<PlantDTO> matchingPlants = new ArrayList<PlantDTO>();
		if(searchTerm.contains("edbud") || searchTerm.contains("ercis")) {
			PlantDTO plant = new PlantDTO();
			plant.setGenus("Cercis");
			plant.setSpecies("Canadensis");
			plant.setCommon("Eastern Redbud");
			
			matchingPlants.add(plant); 
		}
			
		return matchingPlants;
		}
	
	@Override
	public ISpecimenDAO getSpecimenDAO() {
		return specimenDAO;
	}

	@Override
	public void setSpecimenDAO(ISpecimenDAO specimenDAO) {
		this.specimenDAO = specimenDAO;
	}

	public Iterable<SpecimenDTO> fetchAllPlants() throws Exception {
		return null;
	}

	@Override
	public Iterable<SpecimenDTO> fetchAllSpecimens() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}
