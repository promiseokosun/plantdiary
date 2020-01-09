package com.plantdiary.dao;

import org.springframework.data.repository.CrudRepository;

import com.plantdiary.dto.SpecimenDTO;

public interface ISpecimenRepository extends CrudRepository<SpecimenDTO, Integer> {

}
