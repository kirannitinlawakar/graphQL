package com.graphqlBookApplication.graphqlBookApplication;

import com.graphqlBookApplication.graphqlBookApplication.model.Book;
import com.graphqlBookApplication.graphqlBookApplication.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllBooksDataFetcher implements DataFetcher<List<Book>> {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> get(DataFetchingEnvironment environment) throws Exception {
        return bookRepository.findAll();
    }
}
