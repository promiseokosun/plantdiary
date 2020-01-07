package com.plantdiary.dao;

import java.util.List;

import com.plantdiary.dto.PlantDTO;

public interface IPlantDAO {

	List<PlantDTO> fetch(String searchFilter) throws Exception;

}