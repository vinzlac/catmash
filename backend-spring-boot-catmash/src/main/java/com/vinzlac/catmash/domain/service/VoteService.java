package com.vinzlac.catmash.domain.service;

import com.vinzlac.catmash.infrastructure.entity.Cat;
import com.vinzlac.catmash.infrastructure.entity.CatsProposalEvent;
import com.vinzlac.catmash.infrastructure.entity.VoteEvent;
import com.vinzlac.catmash.infrastructure.service.CatService;
import com.vinzlac.catmash.infrastructure.service.CatsProposalEventService;
import com.vinzlac.catmash.infrastructure.service.VoteEventService;
import com.vinzlac.catmash.web.converter.VoteConverter;
import com.vinzlac.catmash.web.model.CatWithVoteCount;
import com.vinzlac.catmash.web.model.CatsProposalRequest;
import com.vinzlac.catmash.web.model.CatsProposalResponse;
import com.vinzlac.catmash.web.model.Vote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class VoteService {

	private final CatService catService;

	private final VoteEventService voteEventService;

	private final CatsProposalEventService catsProposalEventService;

	private final VoteConverter voteConverter;

	public Stream<CatWithVoteCount> getCatsRankingReverseOrdeyByVoteCount(){
		return getCatsRanking(Comparator.comparingInt(CatWithVoteCount::getVoteCount).reversed());
	}

	private Stream<CatWithVoteCount> getCatsRanking(Comparator<CatWithVoteCount> comparator){
		List<Cat> cats = catService.getCats();
		return cats
				.stream()
				.map(voteConverter::toCatWithVoteCount)
				.sorted(comparator);
	}

	public Vote vote(String catId, CatsProposalRequest catsProposalRequest) {
		Cat foundCat = catService.findById(catId);
		CatsProposalEvent foundCatsProposalEvent = catsProposalEventService.findById(catsProposalRequest.getUuid());
		VoteEvent voteEvent = voteEventService.createVoteEvent(foundCat, foundCatsProposalEvent);
		return voteConverter.toVote(voteEvent);
	}

	public CatsProposalResponse createCatsProposal(int nbRequestedCats) {
		CatsProposalEvent catsProposalEvent = catsProposalEventService.createCatsProposalEvent(nbRequestedCats);
		return voteConverter.toCatsProposal(catsProposalEvent);
	}
}
