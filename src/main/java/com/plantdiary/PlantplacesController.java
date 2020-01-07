package com.plantdiary;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.plantdiary.dto.PlantDTO;
import com.plantdiary.dto.SpecimenDTO;
import com.plantdiary.service.ISpecimenService;

@Controller
public class PlantplacesController {
	
	@Autowired
	private ISpecimenService specimenService;
	private SpecimenDTO specimenDTO;
	
	/**
	 * Handle the /savespecimen endpoint
	 * @param specimenDTO
	 * @return start template
	 */
	@RequestMapping(value="/savespecimen")
	public String saveSpecimen(SpecimenDTO specimenDTO) {
		specimenDTO.setPlantId(13);
		specimenDTO.setSpecimenId(71);
		return "start";
	}
	
	/**
	 * Handle the /start endpoint
	 * @return model
	 * @return start template
	 */
	@RequestMapping(value="/start", method=RequestMethod.GET)
	public String read(Model model){ 
		model.addAttribute("specimenDTO", new SpecimenDTO());
		return "start"; 
	}
	
	/**
	 * Handl the /start endpoint.
	 * @param model
	 * @param latitude
	 * @return start template
	 */
	@RequestMapping("/addspecimen")
	public String addSpecimen(Model model, @RequestParam(value="latitude", required=false, defaultValue="0.0") String latitude) {
		specimenDTO = specimenService.fetchById(44);
		specimenDTO.setLatitude(latitude);
		model.addAttribute("specimenDTO", specimenDTO );
		return "start";
	}
	
	/**
	 * Handle the /searchplants endpoint
	 * @param searchTerm
	 * @return start template
	 */
	@RequestMapping(value="/searchplants")
	public String searchPlants(@RequestParam(value="searchTerm") String searchTerm){
		
		try {
			List<PlantDTO> fetchPlants = specimenService.fetchPlants(searchTerm);
			
			// save fetchPlants to a model for the view to use
		} catch (Exception e) {
			// log error
			e.printStackTrace();
			return "error";
		}
		
		return "redirect:start";
	}
	
	@RequestMapping(value="/searchplantall")
	public String searchPlantAll(@RequestParam Map<String, String> requestParam){
		
		int param = requestParam.size();
		requestParam.get("searchTerm");
	
		return "redirect:start";
	}
	
	@RequestMapping("/sustainability")
	public String sustainability(){
		return "sustainability";
	}
	

	
}
