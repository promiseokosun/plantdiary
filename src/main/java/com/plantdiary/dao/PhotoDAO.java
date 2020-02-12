package com.plantdiary.dao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.plantdiary.dto.PhotoDTO;

@Component
public class PhotoDAO implements IPhotoDAO {
	
	@Autowired
	private IPhotoRepository photoRepository;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	/* (non-Javadoc)
	 * @see com.plantdiary.dao.IPhotoDAO#savePhotoImage(org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public void savePhotoImage(MultipartFile imageFile) throws Exception {
		String folder = "/photos/";
		byte[] bytes = imageFile.getBytes(); // get photo in bytes
		Path path = Paths.get(folder + imageFile.getOriginalFilename()); // build a path for the photo
		Files.write(path, bytes); // save photo in byte at path
	}
	
	/* (non-Javadoc)
	 * @see com.plantdiary.dao.IPhotoDAO#save(com.plantdiary.dto.PhotoDTO)
	 */
	@Override
	public void save(PhotoDTO photoDTO) throws Exception{
		photoRepository.save(photoDTO);
	}

	@Override
	public void savePhotoImage(PhotoDTO photoDTO, MultipartFile imageFile) throws Exception {
		// TODO Auto-generated method stub
		Path currentPath = Paths.get("."); // any directory
		Path absolutePath = currentPath.toAbsolutePath(); // app root path
		photoDTO.setPath(absolutePath + "/src/main/resources/static/photos/"); // photos directory
		byte[] bytes = imageFile.getBytes(); // get photo in bytes
		Path path = Paths.get(photoDTO.getPath() + imageFile.getOriginalFilename()); // build a path for the photo
		Files.write(path, bytes); // save photo in byte at a given path
		//queue message to Activemq
		jmsTemplate.convertAndSend("photos", path.normalize().toString());
		kafkaTemplate.send("test2", path.normalize().toString());
		
		int i = 2;
	}
	
	

}
