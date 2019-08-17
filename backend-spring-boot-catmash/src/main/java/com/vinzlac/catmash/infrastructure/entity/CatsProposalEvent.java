package com.vinzlac.catmash.infrastructure.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CatsProposalEvent {

	@EqualsAndHashCode.Include
	@Id
	private String uuid;

	@ManyToMany
	private Set<Cat> cats;

	@OneToOne
	VoteEvent voteEvent;
}
