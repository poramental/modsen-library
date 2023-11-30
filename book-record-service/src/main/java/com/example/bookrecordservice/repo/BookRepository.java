package com.example.bookrecordservice.repo;



import com.example.bookrecordservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {


    Optional<Book> findByISBN(String ISBN);

    List<Book> findBooksByName(String name);

    Optional<Book> findByName(String name);

}
