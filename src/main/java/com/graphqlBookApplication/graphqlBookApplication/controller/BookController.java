package com.graphqlBookApplication.graphqlBookApplication.controller;

import com.graphqlBookApplication.graphqlBookApplication.model.Book;
import com.graphqlBookApplication.graphqlBookApplication.repository.BookRepository;
import com.graphqlBookApplication.graphqlBookApplication.service.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/books")
@RestController
public class BookController {

    @Autowired
    GraphQLService graphQLService;

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PostMapping
    public ResponseEntity<Object> getAllBooks(@RequestBody String query){
         ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
         return new ResponseEntity<>(executionResult, HttpStatus.OK);
    }
}
