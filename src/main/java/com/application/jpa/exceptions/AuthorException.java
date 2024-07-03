package com.application.jpa.exceptions;

public class AuthorException extends BaseException {
    public AuthorException(String message) {
        super("author." + message);
    }

    public static AuthorException createFirstNameNull() {
        return new AuthorException("create.firstname.null");
    }

    public static AuthorException createLastNameNull() {
        return new AuthorException("create.lastname.null");
    }

    public static AuthorException createEmailNull() {
        return new AuthorException("create.email.null");
    }

    public static AuthorException createCreateByNull() {
        return new AuthorException("create.createBy.null");
    }

    public static AuthorException createAgeNull() {
        return new AuthorException("create.age.null");
    }

    public static AuthorException createEmailExists() {
        return new AuthorException("create.email.exists");
    }
}
