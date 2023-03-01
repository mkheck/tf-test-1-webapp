package com.thehecklers.tftest1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TfTest1Application {

	public static void main(String[] args) {
		SpringApplication.run(TfTest1Application.class, args);
	}

	@Bean
	CommandLineRunner loadData(AuthorRepository repo) {
		return args -> {
			repo.deleteAll();
			repo.saveAll(List.of(new Author("Mark"), new Author("Laur"), new Author("Thomas")));
			repo.findAll().forEach(System.out::println);
		};
	}
}

