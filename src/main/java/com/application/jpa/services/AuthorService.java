package com.application.jpa.services;

import com.application.jpa.entities.Author;
import com.application.jpa.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public void create(String firstName, String lastName, String email, String createdBy, int age) {
        Author author = Author.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .createdBy(createdBy)
                .age(age)
                .build();
        authorRepository.save(author);
    }
}