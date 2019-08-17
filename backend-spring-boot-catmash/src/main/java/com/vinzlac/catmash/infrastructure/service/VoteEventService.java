package com.vinzlac.catmash.infrastructure.service;

import com.vinzlac.catmash.infrastructure.entity.Cat;
import com.vinzlac.catmash.infrastructure.entity.CatsProposalEvent;
import com.vinzlac.catmash.infrastructure.entity.VoteEvent;
import com.vinzlac.catmash.infrastructure.repository.CatRepository;
import com.vinzlac.catmash.infrastructure.repository.CatsProposalEventRepository;
import com.vinzlac.catmash.infrastructure.repository.VoteEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class VoteEventService {

	private final VoteEventRepository voteEventRepository;

	private final CatRepository catRepository;

	private final CatsProposalEventRepository catsProposalEventRepository;

	@Transactional
	public VoteEvent createVoteEvent(Cat cat, CatsProposalEvent catsProposalEvent) {
		if (catsProposalEvent.getVoteEvent() !=  null) throw new IllegalArgumentException("Proposal uuid:" + catsProposalEvent.getUuid()+ " already voted!");

		if (!catsProposalEvent.getCats().contains(cat)) throw new IllegalArgumentException(cat + " not present in proposal: "+ catsProposalEvent.getUuid());

		VoteEvent voteEvent = new VoteEvent();
		voteEvent.setCat(cat);
		VoteEvent createdVoteEvent = voteEventRepository.save(voteEvent);

		catsProposalEvent.setVoteEvent(voteEvent);
		catsProposalEventRepository.save(catsProposalEvent);

		cat.add(createdVoteEvent);
		catRepository.save(cat);

		return createdVoteEvent;
	}
}
