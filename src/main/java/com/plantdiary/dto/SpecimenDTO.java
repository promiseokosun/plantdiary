package com.plantdiary.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="specimens", schema="plants")
public class SpecimenDTO {
	
	@Id
	@GeneratedValue
	@Column(name="specimen_id")
	private int specimenId;
	@Column(name="latitude")
	private String latitude;
	@Column(name="longitude")
	private String longitude;
	@Column(name="description")
	private String description;
	@Column(name="plant_Id")
	private int plantId;
	@Column(name="plant_name")
	private String plantName;
	
	// photo_id coming soon...
	
	public int getSpecimenId() {
		return specimenId;
	}
	public void setSpecimenId(int specimenId) {
		this.specimenId = specimenId;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getPlantId() {
		return plantId;
	}
	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}
	public String getPlantName() {
		return plantName;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	@Override
	public String toString() {
		
		return specimenId+" "+latitude+" "+longitude+" "+description;
	}

	@Override
	public boolean equals(Object obj) {
		// assume they don't match.
		boolean returnValue = false;
		if(obj instanceof SpecimenDTO) {
			SpecimenDTO incomingSpecimen = (SpecimenDTO) obj;
			returnValue = incomingSpecimen.getSpecimenId() == getSpecimenId();
		}
		return returnValue;
	}
}
