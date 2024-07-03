package com.application.jpa;

import com.application.jpa.entities.Author;
import com.application.jpa.exceptions.BaseException;
import com.application.jpa.repositories.AuthorRepository;
import com.application.jpa.services.AuthorService;
import com.application.jpa.specification.AuthorSpecification;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class AuthorSpecificationTest {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    public void setUp() throws BaseException {
        authorRepository.deleteAll();
        authorService.create(
                AuthorData.FIRST_NAME,
                AuthorData.LAST_NAME,
                AuthorData.EMAIL,
                AuthorData.CREATE_BY,
                AuthorData.AGE
        );
    }
    @Test
    @Transactional
    public void testQuerySpecificationHasAge() {
        Specification<Author> spec = Specification.where(
                AuthorSpecification.hasAge(AuthorData.AGE)
        );
        List<Author> authors = authorRepository.findAll(spec);

        assertEquals(1, authors.size());
    }

    interface AuthorData {
        Faker faker = new Faker();
        String FIRST_NAME = faker.name().firstName();
        String LAST_NAME = faker.name().lastName();
        String EMAIL = faker.internet().emailAddress();
        String CREATE_BY = faker.name().fullName();
        int AGE = faker.number().numberBetween(19, 50);
    }
}