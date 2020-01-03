package com.plantdiary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.plantdiary.dto.SpecimenDTO;
import com.plantdiary.service.ISpecimenService;

@Controller
public class PlantplacesController {
	
	@Autowired
	private ISpecimenService specimenServiceStub;
	
	/**
	 * Handle the /start endpoint
	 * @return
	 */
	
	

	@RequestMapping(value="/start", method=RequestMethod.GET)
	public String read(){ 
		
		SpecimenDTO specimenDTO = specimenServiceStub.fetchById(43);
		
		return "start"; 
	}
	
	@RequestMapping(value="/start", method=RequestMethod.GET, headers="{content-type=text/json}")
	public String startJson(){ 
		return "start"; 
	}
	
	@RequestMapping(value="/start", params={"loyalty=blue"})
	public String create(){ 
		return "start"; 
	}
	
}
