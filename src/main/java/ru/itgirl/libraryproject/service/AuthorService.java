package ru.itgirl.libraryproject.service;

import ru.itgirl.libraryproject.dto.AuthorDto;

public interface AuthorService {
    //AuthorDto getAuthorById(Long id);

    AuthorDto getAuthorByName1(String name);

    AuthorDto getAuthorByName2(String name);

    AuthorDto getAuthorByName3(String name);
}
