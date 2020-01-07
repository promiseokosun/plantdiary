package com.plantdiary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.plantdiary.dao.ISpecimenDAO;
import com.plantdiary.dto.PlantDTO;
import com.plantdiary.dto.SpecimenDTO;
import com.plantdiary.service.ISpecimenService;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class SpecimenServiceTest {
	
	@Autowired
	ISpecimenService specimenService;
	private List<PlantDTO> plants;
	private SpecimenDTO specimen;
	
	// mock a variable
	@MockBean
	private ISpecimenDAO specimenDAO; // mock to ignore this dependency
	
	@Before
	public void setup() throws Exception{
		// here specimenService will work independently of the specimenDAO
		// because it has been mocked
		SpecimenDTO specimen = new SpecimenDTO();
		specimen.setDescription("A beautiful Redbud i planted this year");
		specimen.setSpecimenId(83);
		Mockito.when(specimenDAO.save(specimen)).thenReturn(true);
		specimenService.setSpecimenDAO(specimenDAO);
	}
	
	@Test
	public void saveSpecimen_whenRedbudEntered(){
		givenUserIsLoggedInToMyPlantDiary();
		whenUserSearchesForEasternRedbud();
		whenUserAddsTextDetails();
		thenSpecimenIsSaved();
	}
	
	private void givenUserIsLoggedInToMyPlantDiary() {
		
		try {
			plants = specimenService.fetchPlants("kdjddidod;odod");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void whenUserSearchesForEasternRedbud() {
		try {
			plants = specimenService.fetchPlants("Eastern Redbud");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void whenUserAddsTextDetails() {
		specimen = new SpecimenDTO();
		PlantDTO plantDTO = plants.get(0);
		specimen.setSpecimenId(plantDTO.getGuid());
		specimen.setDescription("A beautiful Redbud");
		specimen.setSpecimenId(83);
	}

	private void thenSpecimenIsSaved() {
		try{
			boolean result = specimenService.save(specimen);
			// if we have made it to this line the test passed
			assertTrue(result);
		} catch (Exception e) {
			// we should not get here if the test fails
			fail();
		}
		
	}

	@Test
	public void fetchPlants_validateNoResultsForJunkData(){
		givenUserIsLoggedInToMyPlantDiary();
		whenTheUserSearchesForJunk();
		thenMyPlantDiaryReturnsZeroResult();
		
	}


	private void whenTheUserSearchesForJunk() {
		assertEquals("Number of plants returned", 0, plants.size());
	}

	private void thenMyPlantDiaryReturnsZeroResult() {
		
	}

}
