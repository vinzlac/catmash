package com.vinzlac.catmash.infrastructure.service;

import com.vinzlac.catmash.infrastructure.entity.CatsProposalEvent;
import com.vinzlac.catmash.infrastructure.repository.CatsProposalEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CatsProposalEventService {

	private final CatService catService;

	private final CatsProposalEventRepository catsProposalEventRepository;

	public CatsProposalEvent createCatsProposalEvent(int nbRequestedCats) {
		CatsProposalEvent catsProposalEvent = new CatsProposalEvent();
		catsProposalEvent.setUuid(UUID.randomUUID().toString());
		catsProposalEvent.setCats(catService.getRandomCats(nbRequestedCats));
		return catsProposalEventRepository.save(catsProposalEvent);
	}

	public CatsProposalEvent findById(String catsProposalUuid) {
		return catsProposalEventRepository
				.findById(catsProposalUuid)
				.orElseThrow(() -> new IllegalArgumentException("catsProposal: "+ catsProposalUuid+ " not found!"));
	}
}
