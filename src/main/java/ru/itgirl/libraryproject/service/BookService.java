package ru.itgirl.libraryproject.service;

import ru.itgirl.libraryproject.dto.BookDto;

public interface BookService {
    BookDto getByNameV1(String name);
}
