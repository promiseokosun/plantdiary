package com.plantdiary.dao;

import com.plantdiary.dto.PlantList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface IGetPlants {
	
	@GET("/perl/mobile/viewplantsjson.pl")
	Call<PlantList> getAllPlants(@Query("Combined_Name") String combinedName);

}
