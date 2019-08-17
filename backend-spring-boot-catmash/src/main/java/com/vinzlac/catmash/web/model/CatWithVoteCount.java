package com.vinzlac.catmash.web.model;

import lombok.Value;

@Value
public class CatWithVoteCount {
	String id;

	String url;

	int voteCount;
}
