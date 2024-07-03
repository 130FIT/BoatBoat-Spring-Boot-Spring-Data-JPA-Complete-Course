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
        AuthorException exception = assertThrows(
                AuthorException.class,
                () -> authorService.create(
                        AuthorData.FIRST_NAME,
                        AuthorData.LAST_NAME,
                        null, // This should cause the AuthorException
                        AuthorData.CREATE_BY,
                        AuthorData.AGE
                )
        );
        assertEquals(AuthorException.createEmailNull().getMessage(), exception.getMessage());
    }

    @Test
    @Transactional
    public void testCreateAuthorWithEmptyFirstName() {
        AuthorException exception = assertThrows(
                AuthorException.class,
                () -> authorService.create(
                        null,
                        AuthorData.LAST_NAME, // This should cause the AuthorException
                        AuthorData.EMAIL,
                        AuthorData.CREATE_BY,
                        AuthorData.AGE
                )
        );

        assertEquals(AuthorException.createFirstNameNull().getMessage(), exception.getMessage());
    }

    @Test
    @Transactional
    public void testCreateAuthorWithEmptyLastName() {
        AuthorException exception = assertThrows(
                AuthorException.class,
                () -> authorService.create(
                        AuthorData.FIRST_NAME,
                        null, // This should cause the AuthorException
                        AuthorData.EMAIL,
                        AuthorData.CREATE_BY,
                        AuthorData.AGE
                )
        );

        assertEquals(AuthorException.createLastNameNull().getMessage(), exception.getMessage());
    }

    @Test
    @Transactional
    public void testCreateAuthorWithEmptyCreateBy() {
        AuthorException exception = assertThrows(
                AuthorException.class,
                () -> authorService.create(
                        AuthorData.FIRST_NAME,
                        AuthorData.LAST_NAME, // This should cause the AuthorException
                        AuthorData.EMAIL,
                        null,
                        AuthorData.AGE
                )
        );

        assertEquals(AuthorException.createCreateByNull().getMessage(), exception.getMessage());
    }
    @Test
    @Transactional
    public void testCreateAuthorWithEmailExists() throws BaseException {
        Faker faker = new Faker();
        authorService.create(
                faker.name().firstName(),
                faker.name().lastName(), // This should cause the AuthorException
                AuthorData.EMAIL,
                faker.name().fullName(),
                faker.number().numberBetween(19, 50)
        );
        AuthorException exception = assertThrows(
                AuthorException.class,
                () -> authorService.create(
                        AuthorData.FIRST_NAME,
                        AuthorData.LAST_NAME, // This should cause the AuthorException
                        AuthorData.EMAIL,
                        AuthorData.CREATE_BY,
                        AuthorData.AGE
                )
        );

        assertEquals(AuthorException.createEmailExists().getMessage(), exception.getMessage());
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
