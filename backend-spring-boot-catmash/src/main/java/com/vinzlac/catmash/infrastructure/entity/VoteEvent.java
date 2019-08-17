package com.vinzlac.catmash.infrastructure.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VoteEvent {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Cat cat;

}
