package ru.itgirl.libraryproject.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
    @Override
    public AuthorDto getAuthorByName1(String name) {
        Author author = authorRepository.findAuthorByName(name).orElseThrow();
        return convertEnityToDto(author);
    }

    @Override
    public AuthorDto getAuthorByName2(String name) {
        Author author = authorRepository.findAuthorByNameBySql(name).orElseThrow();
        return convertEnityToDto(author);
    }

    @Override
    public AuthorDto getAuthorByName3(String name) {
        Specification<Author> specification = Specification.where(new Specification<Author>() {
            @Override
            public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("name"), name);
            }
        });
        Author author = authorRepository.findOne(specification).orElseThrow();
        return convertEnityToDto(author);
    }

    public AuthorDto convertEnityToDto(Author author) {
        List<BookDto> bookDtoList = null;
        if (author.getBooks() != null){
            bookDtoList = author.getBooks()
                    .stream()
                    .map(b->BookDto.builder()
                            .id(b.getId())
                            .name(b.getName())
                            .genre(b.getGenre().getName())
                            .build()
                    ).toList();
        }
        return AuthorDto.builder()
                .id(author.getId())
                .surname(author.getSurname())
                .name(author.getName())
                .books(bookDtoList)
                .build();
    }


}