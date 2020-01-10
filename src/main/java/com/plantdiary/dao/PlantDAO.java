package com.plantdiary.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plantdiary.dto.PlantDTO;
import com.plantdiary.dto.PlantList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class PlantDAO implements IPlantDAO {
	
	@Autowired
	NetworkDAO networkDAO;
	
	/* (non-Javadoc)
	 * @see com.plantdiary.dao.IPlantDAO#fetch(java.lang.String)
	 */
	public List<PlantDTO> fetchManual(String searchFilter) throws Exception{
		List<PlantDTO> allPlants = new ArrayList<PlantDTO>();
		
		String rawJson = networkDAO.request("http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=Oak");
		//convert data to JSON array
		JSONObject root = new JSONObject(rawJson);
		JSONArray plants = root.getJSONArray("plants");
		
		for (int i = 0; i < plants.length(); i++) {
			// use JSON Object to pull up the array data into variables
			JSONObject jsonObject = plants.getJSONObject(i);
			int guid = jsonObject.getInt("id");
			String genus = jsonObject.getString("genus");
			String species = jsonObject.getString("species");
			String cultivar = jsonObject.getString("cultivar");
			String common = jsonObject.getString("common");
			
			// Populate the PlantDTO 
			PlantDTO plant = new PlantDTO();
			plant.setGuid(guid);
			plant.setGenus(genus);
			plant.setSpecies(species);
			plant.setCultivar(cultivar);
			plant.setCommon(common);
			
			// add to collection
			allPlants.add(plant);
			
		}
		
		return allPlants;
	}
	
	@Override
	public List<PlantDTO> fetch(String searchFilter) throws Exception{
		// Note you have to be connected to the Internet to pull out this json data
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://plantplaces.com")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		
		IGetPlants getPlants = retrofit.create(IGetPlants.class);
		Call<PlantList> allPlants = getPlants.getAllPlants(searchFilter);
		Response<PlantList> execute = allPlants.execute();
		PlantList plantList = execute.body();
		List<PlantDTO> plants = plantList.getPlants();
		
		return plants;
	}

}



