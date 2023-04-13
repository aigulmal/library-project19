package ru.itgirl.libraryproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.libraryproject.dto.AuthorDto;
import ru.itgirl.libraryproject.service.AuthorService;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

//    @GetMapping("/author/{id}")
//    AuthorDto getAuthorById(@PathVariable("id") Long id) {
//        return authorService.getAuthorById(id);
//    }
    @GetMapping("/author")
    AuthorDto getAuthorByName1(@RequestParam("name") String name) {
        return authorService.getAuthorByName1(name);
    }
}