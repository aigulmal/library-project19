package ru.itgirl.libraryproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itgirl.libraryproject.dto.AuthorDto;
import ru.itgirl.libraryproject.dto.BookDto;
import ru.itgirl.libraryproject.model.Author;
import ru.itgirl.libraryproject.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

//    @Override
//    public AuthorDto getAuthorById(Long id) {
//        Author author = authorRepository.findById(id).orElseThrow();
//        return convertEntityToDto(author);
//    }
    @Override
    public AuthorDto getAuthorByName1(String name) {
        Author author = authorRepository.findAuthorByName(name).orElseThrow();

  //  private AuthorDto convertEntityToDto(String name) {
//        List<AuthorDto> authorDtoList = author.getBooks()
//                .stream()
//                .map(book -> BookDto.builder()
//                        .genre(book.getGenre().getName())
//                        .name(book.getName())
//                        .id(book.getId())
//                        .authors(book.getAuthors())
//                        .build()
//                ).toList();
//        return AuthorDto.builder()
//                .books(bookDtoList)
//                .id(author.getId())
//                .name(author.getName())
//                .surname(author.getSurname())
//                .build();
                List<BookDto> bookDtoList = author.getBooks()
                .stream()
                .map(book -> BookDto.builder()
                        .genre(book.getGenre().getName())
                        .name(book.getName())
                        .id(book.getId())
                        .authors(book.getAuthors()
                                .stream()
                                .peek(author1->AuthorDto.builder()
                                        .id(book.getId())
                                        .name(book.getName()))
                                .collect(Collectors.toList()))
                        .build())
                .toList();
//        return AuthorDto.builder()
//                .id(author.getId())
//                .name(author.getName())
//                .surname(author.getSurname())
//                .books(book->BookDto.)
//                .build();
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setName(author.getName());
        authorDto.setBooks(bookDtoList);
        return genreDto;
    }

}