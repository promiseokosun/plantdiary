package com.plantdiary.service;

import org.springframework.stereotype.Component;

import com.plantdiary.dto.SpecimenDTO;

@Component
public class SpecimenServiceStub implements ISpecimenService {
	
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
	public void save(SpecimenDTO specimenDTO){
		
	}

}
