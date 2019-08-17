package com.vinzlac.catmash.web.converter;

import com.vinzlac.catmash.infrastructure.entity.Cat;
import com.vinzlac.catmash.infrastructure.entity.CatsProposalEvent;
import com.vinzlac.catmash.infrastructure.entity.VoteEvent;
import com.vinzlac.catmash.web.model.*;
import org.springframework.stereotype.Component;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Component
public class VoteConverter {
	public CatWithVoteCount toCatWithVoteCount(Cat cat) {
		return new CatWithVoteCount(cat.getId(), cat.getUrl(), cat.getVoteEvents().size());
	}

	public Vote toVote(VoteEvent voteEvent) {
		return new Vote(voteEvent.getId());
	}

	public CatsProposalResponse toCatsProposal(CatsProposalEvent catsProposalEvent) {
		return new CatsProposalResponse(catsProposalEvent.getUuid(), toCatProposals(catsProposalEvent.getCats()));
	}

	private Set<CatProposalResponse> toCatProposals(Set<Cat> cats) {
		return cats.stream().map(this::toCatProposal).collect(toSet());
	}

	private CatProposalResponse toCatProposal(Cat cat) {
		return new CatProposalResponse(cat.getId(), cat.getUrl());
	}

	public CatsProposalEvent toCatsProposalEvent(CatsProposalRequest catsProposalRequest) {
		CatsProposalEvent catsProposalEvent = new CatsProposalEvent();
		catsProposalEvent.setUuid(catsProposalRequest.getUuid());
		return catsProposalEvent;
	}
}
