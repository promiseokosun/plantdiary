package com.plantdiary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.plantdiary.dto.LabelValue;
import com.plantdiary.dto.PhotoDTO;
import com.plantdiary.dto.PlantDTO;
import com.plantdiary.dto.SpecimenDTO;
import com.plantdiary.service.ISpecimenService;

@Controller
public class PlantPlacesController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ISpecimenService specimenService;
	private SpecimenDTO specimenDTO;

	private List<PlantDTO> allPlants;

	private String firstThreeCharacters;
	
	/**
	 * Handle the /savespecimen endpoint
	 * @param imageFile
	 * @param specimenDTO
	 * @return start template
	 */
	@PostMapping(value="/savespecimen")
	public ModelAndView saveSpecimen(@RequestParam("imageFile") MultipartFile imageFile, SpecimenDTO specimenDTO) {
		ModelAndView modelAndView = new ModelAndView();
		// specimen
		try {
			specimenService.save(specimenDTO);
		} catch (Exception e) {
			// TODO log error message
			log.error("Unable to save specimen", e);
			e.printStackTrace();
			modelAndView.setViewName("error");
			return modelAndView;
		}
		
		// photo
		
		PhotoDTO photoDTO = new PhotoDTO();
		photoDTO.setFileName(imageFile.getOriginalFilename());
		//photoDTO.setPath("/photo/");
		photoDTO.setSpecimenDTO(specimenDTO); // linked data
		modelAndView.setViewName("success");
		
		try {
			specimenService.saveImage(imageFile, photoDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Error saving photos", e);
			modelAndView.setViewName("error");
			return modelAndView;
		}
		// add specimenDTO AND photoDTO
		modelAndView.addObject("photoDTO", photoDTO);
		modelAndView.addObject("specimenDTO", specimenDTO);
		
		return modelAndView;
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
	
	/**
	 * Retrieve specimens and display
	 * @return showSpecimens template
	 */
	@RequestMapping("/showSpecimens")
	public ModelAndView showSpecimens(){
		ModelAndView modelAndView = new ModelAndView();
		try {
			Iterable<SpecimenDTO> allSpecimens = specimenService.fetchAllSpecimens();
			modelAndView.addObject("allSpecimens", allSpecimens);
			modelAndView.setViewName("showSpecimens");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Unable to retrieve all specimens", e);
			modelAndView.setViewName("error");
		}
		
		return modelAndView;
	}
	
	/**
	 * Handles the auto-complete
	 * @param term - http://localhost:8080/plantNamesAutocomplete?term=Redbud
	 * @return plants JSON data from plantplaces.com API
	 */
	@RequestMapping(value="/plantNamesAutocomplete")
	@ResponseBody // using response body so that this endpoint will only give me data and not another view
	public List<LabelValue> plantNamesAutocomplete(@RequestParam(value="term", required=false, defaultValue="") String term){
		List<LabelValue> suggestions = new ArrayList<LabelValue>(); // a new list object is created for every 3+ word entered
		try {
			if(term.length() == 3){
				firstThreeCharacters = term;
				allPlants = specimenService.fetchPlants(term); //fetchPlants should be called once as the user types
			}
			
			for (PlantDTO plantDTO : allPlants) {
				// filter the plant result in the allPlants list	
				if(plantDTO.toString().contains(term)){
					LabelValue labelValue = new LabelValue(); 
					labelValue.setLabel(plantDTO.toString());// convert data to JSON format
					labelValue.setValue(Integer.toString(plantDTO.getGuid()));
					suggestions.add(labelValue); //cache data
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Unable to fetch json data from plantplaces.com", e);
		}
			
		return suggestions;
	}
	
	@PostMapping("/uploadImage")
	public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile){
		String returnValue = "start";
		PhotoDTO photoDTO = new PhotoDTO();
		photoDTO.setFileName(imageFile.getOriginalFilename());
		photoDTO.setPath("/photo/");
		photoDTO.setSpecimenDTO(specimenDTO); // linked data
		
		try {
			specimenService.saveImage(imageFile, photoDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Error saving photos", e);
			returnValue = "error";
		}
		
		return returnValue;
	}

	
}
