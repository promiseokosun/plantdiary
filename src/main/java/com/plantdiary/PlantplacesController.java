package com.plantdiary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.plantdiary.dto.SpecimenDTO;
import com.plantdiary.service.ISpecimenService;

@Controller
public class PlantplacesController {
	
	@Autowired
	private ISpecimenService specimenServiceStub;
	private SpecimenDTO specimenDTO;
	
	
	@RequestMapping(value="/savespecimen")
	public String saveSpecimen(SpecimenDTO specimenDTO) {
		specimenDTO.setPlantId(13);
		//specimenDTO.setSpecimenId(80);
		
		return "start";
	}
	
	/**
	 * Handle the /start endpoint
	 * @return
	 */
	
//	@RequestMapping(value="/start", method=RequestMethod.GET, headers="{content-type=text/json}")
//	@ResponseBody // to return an object in json format
//	public SpecimenDTO read(Model model, @RequestParam(value="latitude", required=false, defaultValue="0.0") String latitude){ 
//		specimenDTO = specimenServiceStub.fetchById(43);
//		model.addAttribute("specimenDTO", specimenDTO);
//		return specimenDTO; 
//	}
	
	@RequestMapping(value="/start", method=RequestMethod.GET)
	public String read(Model model){ 
		model.addAttribute("specimenDTO", new SpecimenDTO());
		return "start"; 
	}

		
	@RequestMapping(value="/start", params={"loyalty=blue"})
	public ModelAndView create(){ 
		
		specimenDTO = specimenServiceStub.fetchById(43);
		specimenDTO.setLatitude("-29.3");
		specimenDTO.setLongitude("-78.6");
		specimenDTO.setDescription("A pink flower");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("start");
		modelAndView.addObject("specimenDTO", specimenDTO);
		
		
		return modelAndView; 
	}
	
}
