package com.vinzlac.catmash.infrastructure.service;

import com.vinzlac.catmash.infrastructure.entity.Cat;
import com.vinzlac.catmash.infrastructure.entity.CatsProposalEvent;
import com.vinzlac.catmash.infrastructure.repository.CatRepository;
import com.vinzlac.catmash.infrastructure.repository.CatsProposalEventRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CatsProposalResponseEventServiceTest {

	@Autowired
	private CatRepository catRepository;

	@Autowired
	private CatsProposalEventRepository catsProposalEventRepository;

	@MockBean
	private CatService catService;

	@Test
	public void testcreateCatsProposalEvent()  {
		// GIVEN

		Cat cat1 = new Cat();
		cat1.setUrl("catUrl1");
		cat1.setId("catId1");
		Cat createdCat1 = catRepository.save(cat1);

		Cat cat2 = new Cat();
		cat2.setUrl("catUrl2");
		cat2.setId("catId2");
		Cat createdCat2 = catRepository.save(cat2);

		HashSet<Cat> cats = new HashSet<>();
		cats.add(cat1);
		cats.add(cat2);

		Mockito.when(catService.getRandomCats(2)).thenReturn(cats);

		CatsProposalEventService catsProposalEventService = new CatsProposalEventService(catService, catsProposalEventRepository);

		// WHEN
		CatsProposalEvent catsProposalEvent = catsProposalEventService.createCatsProposalEvent(2);

		// THEN
		assertThat(catsProposalEvent).isNotNull();
		assertThat(catsProposalEvent.getUuid()).isNotNull();
		assertThat(catsProposalEvent.getCats()).contains(createdCat1, createdCat2);

	}


}