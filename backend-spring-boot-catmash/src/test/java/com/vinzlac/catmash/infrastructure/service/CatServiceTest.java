package com.vinzlac.catmash.infrastructure.service;

import com.vinzlac.catmash.infrastructure.converter.CatConverter;
import com.vinzlac.catmash.infrastructure.entity.Cat;
import com.vinzlac.catmash.infrastructure.repository.CatRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CatServiceTest {

	@Autowired
	private CatRepository catRepository;

	@MockBean
	private RandomService randomService;

	@Test
	public void testParseCatsJsonFile() throws IOException {
		// GIVEN
		CatConverter catConverter = new CatConverter();
		CatService catService = new CatService(catConverter, catRepository, randomService);

		// WHEN
		CatService.CatsJsonFileRoot catsJsonFileRoot = catService.parseCatsJsonFile();

		// THEN
		assertThat(catsJsonFileRoot).isNotNull();
		assertThat(catsJsonFileRoot.getImages()).hasSize(100);
	}


	@Test
	public void testConvertCatsJsonFileToCatsEntities() throws IOException {
		// GIVEN
		CatConverter catConverter = new CatConverter();
		CatService catService = new CatService(catConverter, catRepository, randomService);

		// WHEN
		List<Cat> cats = catService.convertCatsJsonFileToCatsEntities();

		// THEN
		assertThat(cats).isNotNull();
		assertThat(cats).hasSize(100);
		Cat expectedFirstCat = new Cat();
		expectedFirstCat.setId("MTgwODA3MA");
		expectedFirstCat.setUrl("http://24.media.tumblr.com/tumblr_m82woaL5AD1rro1o5o1_1280.jpg");
		expectedFirstCat.setVoteEvents(Collections.emptySet());
		assertThat(cats.get(0)).isEqualTo(expectedFirstCat);
	}


	@Test
	public void testImportCats() throws IOException {
		// GIVEN
		CatConverter catConverter = new CatConverter();
		CatService catService = new CatService(catConverter, catRepository, randomService);

		// WHEN
		List<Cat> cats = catService.importCats();

		// THEN
		assertThat(cats).isNotNull();
		assertThat(cats).hasSize(100);
		Cat expectedFirstCat = new Cat();
		expectedFirstCat.setId("MTgwODA3MA");
		expectedFirstCat.setUrl("http://24.media.tumblr.com/tumblr_m82woaL5AD1rro1o5o1_1280.jpg");
		expectedFirstCat.setVoteEvents(Collections.emptySet());
		assertThat(cats.get(0)).isEqualTo(expectedFirstCat);
	}

	@Test
	public void testGetCats() throws IOException {
		// GIVEN
		CatConverter catConverter = new CatConverter();
		CatService catService = new CatService(catConverter, catRepository, randomService);
		catService.importCats();

		// WHEN
		List<Cat> cats = catService.getCats();

		// THEN
		assertThat(cats).isNotNull();
		assertThat(cats).hasSize(100);
		Cat expectedFirstCat = new Cat();
		expectedFirstCat.setId("MTgwODA3MA");
		expectedFirstCat.setUrl("http://24.media.tumblr.com/tumblr_m82woaL5AD1rro1o5o1_1280.jpg");
		expectedFirstCat.setVoteEvents(Collections.emptySet());
		assertThat(cats.get(0)).isEqualTo(expectedFirstCat);
	}

	@Test
	public void testGetRandomCats() throws IOException {
		// GIVEN
		CatConverter catConverter = new CatConverter();
		CatService catService = new CatService(catConverter, catRepository, randomService);
		List<Cat> importedCats = catService.importCats();
		HashSet<Integer> distinctRandomNumbers = new HashSet<>();
		distinctRandomNumbers.add(1);
		distinctRandomNumbers.add(3);
		Mockito.when(randomService.getDistinctRandomNumbers(2, 100)).thenReturn(distinctRandomNumbers);

		// WHEN
		Set<Cat> randomCats = catService.getRandomCats(2);

		// THEN
		assertThat(randomCats).isNotNull();
		assertThat(randomCats).hasSize(2);

		Cat expectedCat1 = importedCats.get(1);
		Cat expectedFirst = importedCats.get(3);
		HashSet<Object> expectedCats = new HashSet<>();
		expectedCats.add(expectedCat1);
		expectedCats.add(expectedFirst);

		assertThat(randomCats).isEqualTo(expectedCats);
	}
}