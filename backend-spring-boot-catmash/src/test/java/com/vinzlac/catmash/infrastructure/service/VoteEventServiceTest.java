package com.vinzlac.catmash.infrastructure.service;

import com.vinzlac.catmash.infrastructure.entity.Cat;
import com.vinzlac.catmash.infrastructure.entity.CatsProposalEvent;
import com.vinzlac.catmash.infrastructure.entity.VoteEvent;
import com.vinzlac.catmash.infrastructure.repository.CatRepository;
import com.vinzlac.catmash.infrastructure.repository.CatsProposalEventRepository;
import com.vinzlac.catmash.infrastructure.repository.VoteEventRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VoteEventServiceTest {

	@Autowired
	private CatRepository catRepository;

	@Autowired
	private VoteEventRepository voteEventRepository;

	@Autowired
	private CatsProposalEventRepository catsProposalEventRepository;

	@Test
	public void testCreateVoteEvent_whenVoteNotAlreadyCommitted() {
		// GIVEN
		Cat cat1 = new Cat();
		cat1.setUrl("catUrl1");
		cat1.setId("catId1");
		Cat createdCat1 = catRepository.save(cat1);

		Cat cat2 = new Cat();
		cat2.setUrl("catUrl2");
		cat2.setId("catId2");
		Cat createdCat2 = catRepository.save(cat2);

		CatsProposalEvent catsProposalEvent = new CatsProposalEvent();
		catsProposalEvent.setUuid(UUID.randomUUID().toString());
		catsProposalEvent.setVoteEvent(null);
		HashSet<Cat> createdCats = new HashSet<>();
		createdCats.add(createdCat1);
		createdCats.add(createdCat2);
		catsProposalEvent.setCats(createdCats);

		CatsProposalEvent createdCatsProposalEvent = catsProposalEventRepository.save(catsProposalEvent);

		VoteEventService voteEventService = new VoteEventService(voteEventRepository, catRepository, catsProposalEventRepository);

		// WHEN
		VoteEvent createdVoteEvent = voteEventService.createVoteEvent(createdCat1, createdCatsProposalEvent);

		// THEN
		assertThat(createdVoteEvent).isNotNull();
		assertThat(createdVoteEvent.getId()).isNotNull();
		assertThat(createdVoteEvent.getCat()).isEqualTo(createdCat1);

		Cat electedCat = catRepository.getOne(cat1.getId());
		assertThat(electedCat).isEqualTo(createdCat1);
		assertThat(electedCat.getVoteEvents()).contains(createdVoteEvent);

		CatsProposalEvent foundCatsProposalEvent = catsProposalEventRepository.getOne(catsProposalEvent.getUuid());
		assertThat(foundCatsProposalEvent).isEqualTo(createdCatsProposalEvent);
	}

	@Test
	public void testCreateVoteEvent_whenVoteAlreadyCommitted() {
		// GIVEN
		Cat cat1 = new Cat();
		cat1.setUrl("catUrl1");
		cat1.setId("catId1");
		Cat createdCat1 = catRepository.save(cat1);

		Cat cat2 = new Cat();
		cat2.setUrl("catUrl2");
		cat2.setId("catId2");
		Cat createdCat2 = catRepository.save(cat2);

		VoteEvent s = new VoteEvent();
		VoteEvent createdVoteEvent = voteEventRepository.save(s);

		CatsProposalEvent catsProposalEvent = new CatsProposalEvent();
		catsProposalEvent.setUuid(UUID.randomUUID().toString());
		catsProposalEvent.setVoteEvent(createdVoteEvent);
		HashSet<Cat> createdCats = new HashSet<>();
		createdCats.add(createdCat1);
		createdCats.add(createdCat2);
		catsProposalEvent.setCats(createdCats);

		CatsProposalEvent createdCatsProposalEvent = catsProposalEventRepository.save(catsProposalEvent);

		VoteEventService voteEventService = new VoteEventService(voteEventRepository, catRepository, catsProposalEventRepository);

		// WHEN // THEN // IllegalArgumentException
		assertThatThrownBy(() -> voteEventService.createVoteEvent(createdCat1, createdCatsProposalEvent))
				.isInstanceOf(IllegalArgumentException.class);
	}


	@Test
	public void testCreateVoteEvent_whenVoteNotAlreadyCommittedButCatNotInProposal() {
		// GIVEN
		Cat cat1 = new Cat();
		cat1.setUrl("catUrl1");
		cat1.setId("catId1");
		Cat createdCat1 = catRepository.save(cat1);

		Cat cat2 = new Cat();
		cat2.setUrl("catUrl2");
		cat2.setId("catId2");
		Cat createdCat2 = catRepository.save(cat2);

		Cat cat3 = new Cat();
		cat3.setUrl("catUrl3");
		cat3.setId("catId3");
		Cat createdCat3 = catRepository.save(cat3);

		CatsProposalEvent catsProposalEvent = new CatsProposalEvent();
		catsProposalEvent.setUuid(UUID.randomUUID().toString());
		catsProposalEvent.setVoteEvent(null);
		HashSet<Cat> createdCats = new HashSet<>();
		createdCats.add(createdCat1);
		createdCats.add(createdCat2);
		catsProposalEvent.setCats(createdCats);

		CatsProposalEvent createdCatsProposalEvent = catsProposalEventRepository.save(catsProposalEvent);

		VoteEventService voteEventService = new VoteEventService(voteEventRepository, catRepository, catsProposalEventRepository);

		// WHEN // THEN // IllegalArgumentException
		assertThatThrownBy(() -> voteEventService.createVoteEvent(createdCat3, createdCatsProposalEvent))
				.isInstanceOf(IllegalArgumentException.class);
	}
}