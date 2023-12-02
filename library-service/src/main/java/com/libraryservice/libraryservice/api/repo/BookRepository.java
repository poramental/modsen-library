package com.libraryservice.libraryservice.api.repo;


import com.libraryservice.libraryservice.api.entity.Book;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWarDeployment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {


    Optional<Book> findByISBN(String ISBN);




}
