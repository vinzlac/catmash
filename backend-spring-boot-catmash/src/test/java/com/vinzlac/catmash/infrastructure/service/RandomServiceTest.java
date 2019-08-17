package com.vinzlac.catmash.infrastructure.service;

import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomServiceTest {

	@Test
	public void test(){
		RandomService randomService = new RandomService();
		Set<Integer> distinctRandomNumbers = randomService.getDistinctRandomNumbers(5, 5);
		distinctRandomNumbers.stream().forEach(System.out::println);
		assertThat(distinctRandomNumbers).containsOnly(0,1,2,3,4);
	}
}