package com.plantdiary.dao;

import org.springframework.data.repository.CrudRepository;

import com.plantdiary.dto.PhotoDTO;

public interface IPhotoRepository extends CrudRepository<PhotoDTO, Integer> {

}
