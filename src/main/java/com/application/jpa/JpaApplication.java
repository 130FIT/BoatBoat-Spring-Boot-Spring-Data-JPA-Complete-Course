package com.application.jpa;

import com.application.jpa.entities.Author;
import com.application.jpa.repositories.AuthorRepository;
import com.application.jpa.repositories.VideoRepository;
import com.application.jpa.specification.AuthorSpecification;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;

@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthorRepository authorRepository,
            VideoRepository videoRepository
    ) {
        return args -> {
/*            for (int i = 0; i < 10; i++) {
                Faker faker = new Faker();
                var author = Author.builder()
                        .firstName(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .email(faker.internet().emailAddress())
                        .age(faker.number().numberBetween(19, 50))
                        .build();
                authorRepository.save(author);
            }*/
/*            var video = Video.builder()
                    .name("Java 101")
                    .length(5)
                    .build();
            videoRepository.save(video);*/
//            authorRepository.updateAuthorAge(100, 1);

//            Specification<Author> spec = Specification
//                    .where(AuthorSpecification.hasAge(43))
//                    .or(AuthorSpecification.firstNameLike("a"));
//            authorRepository.findAll(spec).forEach(System.out::println);
        };
    }
}
