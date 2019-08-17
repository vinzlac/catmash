package com.vinzlac.catmash.init;

import com.vinzlac.catmash.infrastructure.entity.Cat;
import com.vinzlac.catmash.infrastructure.service.CatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CatmashCommandLineRunner implements CommandLineRunner {

	private final CatService catService;

	@Override
	public void run(String... strings) throws Exception {
		List<Cat> cats = catService.importCats();
		log.info("{} cats imported!", cats.size());
	}
}