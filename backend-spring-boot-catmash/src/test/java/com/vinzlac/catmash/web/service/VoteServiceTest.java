package com.vinzlac.catmash.web.service;

import com.vinzlac.catmash.infrastructure.entity.Cat;
import com.vinzlac.catmash.infrastructure.entity.VoteEvent;
import com.vinzlac.catmash.infrastructure.service.CatService;
import com.vinzlac.catmash.infrastructure.service.CatsProposalEventService;
import com.vinzlac.catmash.infrastructure.service.VoteEventService;
import com.vinzlac.catmash.web.converter.VoteConverter;
import com.vinzlac.catmash.web.model.CatWithVoteCount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceTest {

	@Mock
	CatService catService;

	@Mock
	VoteEventService voteEventService;

	@Mock
	CatsProposalEventService catsProposalEventService;

	VoteConverter voteConverter = new VoteConverter();

	@Test
	public void testGetCatsRanking() {

		// GIVEN
		VoteService voteService = new VoteService(catService, voteEventService, catsProposalEventService, voteConverter);

		Cat cat1 = new Cat();
		cat1.setId("catId1");
		cat1.setUrl("url1");
		HashSet<VoteEvent> voteEvents = new HashSet<>();
		voteEvents.add(new VoteEvent());
		cat1.setVoteEvents(voteEvents);


		Cat cat2 = new Cat();
		cat2.setId("catId2");
		cat2.setUrl("url2");
		cat2.setVoteEvents(new HashSet<>());

		Mockito.when(catService.getCats()).thenReturn(Arrays.asList(cat1, cat2));

		// WHEN
		List<CatWithVoteCount> catsRanking = voteService.getCatsRankingReverseOrdeyByVoteCount();

		// THEN
		assertThat(catsRanking).hasSize(2);
		assertThat(catsRanking.get(0)).isEqualTo(new CatWithVoteCount("catId1", "url1", 1));
		assertThat(catsRanking.get(1)).isEqualTo(new CatWithVoteCount("catId2", "url2", 0));


	}
}