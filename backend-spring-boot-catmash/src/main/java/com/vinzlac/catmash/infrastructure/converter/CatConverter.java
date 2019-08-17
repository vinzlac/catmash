package com.vinzlac.catmash.infrastructure.converter;

import com.vinzlac.catmash.infrastructure.entity.Cat;
import com.vinzlac.catmash.infrastructure.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CatConverter {
	public List<Cat> toCatEntities(CatService.CatsJsonFileRoot catsJsonFileRoot) {
		return catsJsonFileRoot
				.getImages()
				.stream()
				.map(image -> toCatEntity(image))
				.collect(Collectors.toList());
	}

	private Cat toCatEntity(CatService.CatsJsonFileImage image) {
		Cat cat = new Cat();
		cat.setId(image.getId());
		cat.setUrl(image.getUrl());
		return cat;
	}
}
