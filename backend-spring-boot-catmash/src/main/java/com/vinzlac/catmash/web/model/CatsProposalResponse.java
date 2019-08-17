package com.vinzlac.catmash.web.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.Set;

@Value
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CatsProposalResponse {
	String uuid;

	@JsonProperty("cats")
	Set<CatProposalResponse> CatProposalsResponse;
}
