package com.vinzlac.catmash.web.rest;

import com.vinzlac.catmash.web.model.CatWithVoteCount;
import com.vinzlac.catmash.web.model.CatsProposalRequest;
import com.vinzlac.catmash.web.model.CatsProposalResponse;
import com.vinzlac.catmash.web.model.Vote;
import com.vinzlac.catmash.web.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CatController {

	private final VoteService voteService;

	@PostMapping("/cats/proposal")
	public CatsProposalResponse catsProposal(@RequestParam(required = false, name = "count", defaultValue = "2") int catsCount){
		log.info("call cats proposal - requested item count: {}", catsCount);
		return voteService.createCatsProposal(catsCount);
	}

	@PostMapping("/cats/{id}/vote")
	public Vote vote(@PathVariable("id") String catId,
					 @RequestBody CatsProposalRequest catsProposalRequest){
		log.info("call vote catId: {} for catsProposal: {}", catId, catsProposalRequest);
		return voteService.vote(catId, catsProposalRequest);
	}

	@GetMapping("/cats/ranking")
	public List<CatWithVoteCount> catsRankingReverseOrdeyByVoteCount() {
		log.info("call cats ranking reverse ordey by vote count");
		return voteService.getCatsRankingReverseOrdeyByVoteCount();
	}

}