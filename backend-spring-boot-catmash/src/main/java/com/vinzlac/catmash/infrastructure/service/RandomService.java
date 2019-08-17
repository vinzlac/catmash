package com.vinzlac.catmash.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RandomService {

	public Set<Integer> getDistinctRandomNumbers(int requestedItemCount, int totalItemCount) {

		if (requestedItemCount > totalItemCount) throw new IllegalArgumentException("Can't get more that "+ totalItemCount + " random items");

		return ThreadLocalRandom
				.current()
				.ints(0, totalItemCount)
				.distinct()
				.limit(requestedItemCount)
				.boxed()
				.collect(Collectors.toSet());
	}
}
