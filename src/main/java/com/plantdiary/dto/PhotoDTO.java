package com.plantdiary.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="photos")
public class PhotoDTO {
	
	@Id
	@GeneratedValue
	private int photoId;
	private String photographer;
	private String fileName;
	private String path;
	private String comment;
	@ManyToOne
	@JoinColumn(name="specimen_id")
	private SpecimenDTO specimenDTO;
	
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public String getPhotographer() {
		return photographer;
	}
	public void setPhotographer(String photographer) {
		this.photographer = photographer;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public SpecimenDTO getSpecimenDTO() {
		return specimenDTO;
	}
	public void setSpecimenDTO(SpecimenDTO specimenDTO) {
		this.specimenDTO = specimenDTO;
	}
	
	@Override
	public String toString() {
		return "PhotoDTO [photoId=" + photoId + ", photographer=" + photographer + ", fileName=" + fileName + ", path="
				+ path + ", comment=" + comment + ", specimenDTO=" + specimenDTO + "]";
	}
	
	
	

}
