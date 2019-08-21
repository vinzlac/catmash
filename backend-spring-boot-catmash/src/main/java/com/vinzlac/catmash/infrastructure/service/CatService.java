package com.vinzlac.catmash.infrastructure.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinzlac.catmash.infrastructure.converter.CatConverter;
import com.vinzlac.catmash.infrastructure.entity.Cat;
import com.vinzlac.catmash.infrastructure.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class CatService {

	private final ObjectMapper objectMapper = new ObjectMapper();

	private final CatConverter catConverter;

	private final CatRepository catRepository;

	private final RandomService randomService;

	public List<Cat> getCats() {
		List<Cat> cats = catRepository.findAll();
		return cats;
	}

	public List<Cat> importCats() throws IOException {
		List<Cat> cats = convertCatsJsonFileToCatsEntities();

		return cats
				.stream()
				.map(cat -> catRepository.save(cat))
				.collect(Collectors.toList());
	}

	public List<Cat> convertCatsJsonFileToCatsEntities() throws IOException {
		CatsJsonFileRoot catsJsonFileRoot = parseCatsJsonFile();

		return catConverter.toCatEntities(catsJsonFileRoot);
	}

	public CatsJsonFileRoot parseCatsJsonFile() throws IOException {

		Resource resource = new ClassPathResource("data/cats.json");

		InputStream input = resource.getInputStream();

		return objectMapper.readValue(input, CatsJsonFileRoot.class);
	}

	public Cat findById(String catId) {
		return catRepository
				.findById(catId)
				.orElseThrow(() -> new IllegalArgumentException("cat: "+ catId+ " not found!"));
	}

	public Set<Cat> getRandomCats(int requestedCats) {
		List<Cat> availableCats = getCats();
		int catsCount = availableCats.size();
		Set<Integer> distinctRandomNumbers = randomService.getDistinctRandomNumbers(requestedCats, catsCount);

		return distinctRandomNumbers
				.stream()
				.map(availableCats::get)
				.collect(toSet());
	}

	@Value
	public static class CatsJsonFileRoot {
		@NotNull
		List<CatsJsonFileImage> images;
	}

	@Value
	public static class CatsJsonFileImage {
		@NotNull
		String id;

		@NotNull
		String url;
	}
}
