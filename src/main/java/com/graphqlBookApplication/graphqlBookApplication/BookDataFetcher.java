package com.graphqlBookApplication.graphqlBookApplication;

import com.graphqlBookApplication.graphqlBookApplication.model.Book;
import com.graphqlBookApplication.graphqlBookApplication.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookDataFetcher implements DataFetcher<Optional<Book>> {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Optional<Book> get(DataFetchingEnvironment environment) throws Exception {
        String isn = environment.getArgument("id");
        return bookRepository.findById(isn);
    }
}
