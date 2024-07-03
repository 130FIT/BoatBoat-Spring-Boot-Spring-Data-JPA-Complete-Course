package com.application.jpa;

import com.application.jpa.entities.Author;
import com.application.jpa.exceptions.AuthorException;
import com.application.jpa.exceptions.BaseException;
import com.application.jpa.repositories.AuthorRepository;
import com.application.jpa.services.AuthorService;
import com.github.javafaker.Faker;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class AuthorServiceTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;

    @BeforeEach
    public void setUp() {
        authorRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testCreateAuthor() throws BaseException {
        // When
        authorService.create(
                AuthorData.FIRST_NAME,
                AuthorData.LAST_NAME,
                AuthorData.EMAIL,
                AuthorData.CREATE_BY,
                AuthorData.AGE
        );

        // Then
        assertEquals(1, authorRepository.count());
        Author authorCheck = authorRepository.findByEmail(AuthorData.EMAIL);
        assertNotNull(authorCheck);
        assertEquals(AuthorData.EMAIL, authorCheck.getEmail());
    }
    @Test
    @Transactional
    public void testCreateAuthorWithNullEmail() {
        // Expect AuthorException to be thrown
        assertThrows(
                AuthorException.class,
                () -> authorService.create(
                        AuthorData.FIRST_NAME,
                        AuthorData.LAST_NAME,
                        null, // This should cause the AuthorException
                        AuthorData.CREATE_BY,
                        AuthorData.AGE
                ),
                AuthorException.createEmailNull().getMessage()
        );
    }

    @Test
    @Transactional
    public void testCreateAuthorWithEmptyFirstName() {
        // Expect AuthorException to be thrown
        assertThrows(
                AuthorException.class,
                () -> authorService.create(
                        "", // This should cause the AuthorException
                        AuthorData.LAST_NAME,
                        AuthorData.EMAIL,
                        AuthorData.CREATE_BY,
                        AuthorData.AGE
                ),
                AuthorException.createFirstNameNull().getMessage()
        );
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
