package com.plantdiary.dao;

import org.springframework.web.multipart.MultipartFile;

import com.plantdiary.dto.PhotoDTO;

public interface IPhotoDAO {

	void savePhotoImage(MultipartFile imageFile) throws Exception;

	void save(PhotoDTO photoDTO) throws Exception;

	void savePhotoImage(PhotoDTO photoDTO, MultipartFile imageFile) throws Exception;

}