package com.vinzlac.catmash.infrastructure.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString(onlyExplicitlyIncluded = true)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cat {

	@ToString.Include
	@EqualsAndHashCode.Include
	@Id
	String id;

	String url;

	@OneToMany(mappedBy = "cat", cascade = CascadeType.ALL)
	Set<VoteEvent> voteEvents = new HashSet<>();

	public void add(VoteEvent voteEvent) {
		voteEvents.add(voteEvent);
	}
}
