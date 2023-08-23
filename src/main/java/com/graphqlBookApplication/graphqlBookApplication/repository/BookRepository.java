package com.graphqlBookApplication.graphqlBookApplication.repository;

import com.graphqlBookApplication.graphqlBookApplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,String> {

}
