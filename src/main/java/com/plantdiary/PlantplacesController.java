package com.plantdiary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.plantdiary.dto.PlantDTO;
import com.plantdiary.dto.SpecimenDTO;
import com.plantdiary.service.ISpecimenService;

@Controller
public class PlantplacesController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
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
		
		try {
			specimenService.save(specimenDTO);
		} catch (Exception e) {
			// TODO log error message
			log.error("Unable to save specimen", e);
			e.printStackTrace();
			return "error";
		}
		
		return "start";
	}
	
	/**
	 * Handle the /start endpoint
	 * @return model
	 * @return start template
	 */
	@RequestMapping(value="/start", method=RequestMethod.GET)
	public String read(Model model){ 
		log.info("User has entered the /start endpoint");
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
	public ModelAndView searchPlants(@RequestParam(value="searchTerm") String searchTerm){
		log.debug("Entering search plants");
		ModelAndView modelAndView = new ModelAndView();
		List<PlantDTO> plants = new ArrayList<PlantDTO>();
		try {
			plants = specimenService.fetchPlants(searchTerm);
			modelAndView.setViewName("plantResults");
			if(plants.size() == 0){
				log.warn("Received 0 result for search string: " + searchTerm);
			}
			// save fetchPlants to a model for the view to use
		} catch (Exception e) {
			// log error
			log.error("Error happened in /searchplants endpoint", e);
			e.printStackTrace();
			modelAndView.setViewName("error");
		}
		modelAndView.addObject("plants", plants);
		log.debug("Exiting search plants");
		return modelAndView;
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
