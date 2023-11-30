package com.libraryservice.libraryservice.api.controllers;


import com.libraryservice.libraryservice.api.dto.BookDto;

import com.libraryservice.libraryservice.api.entity.Book;
import com.libraryservice.libraryservice.api.exceptions.BookNotFoundException;
import com.libraryservice.libraryservice.api.services.BookService;
import com.sun.net.httpserver.HttpsServer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("rest/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @GetMapping()
    public ResponseEntity<List<Book>> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping()
    public HttpStatus addBooks(@RequestBody List<BookDto> bookDtoList){
        return bookService.addBooks(bookDtoList);
    }

    @DeleteMapping("/{bookId}")
    public HttpStatus deleteBookByName(@PathVariable("bookId") UUID bookId) throws BookNotFoundException{
        return bookService.deleteBookById(bookId);
    }

    @DeleteMapping()
    public HttpStatus deleteAllBooks(){
        return bookService.deleteAllBooks();
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookByName(@PathVariable("bookId") UUID bookId) throws BookNotFoundException{
        return bookService.getBookById(bookId);
    }

    @PutMapping("")
    public HttpStatus updateBook(@RequestBody() BookDto bookDto) throws BookNotFoundException{
        return bookService.updateBook(bookDto);
    }

    @GetMapping("/{bookName}")
    public List<BookDto> getBooksByName(@PathVariable("bookName") String bookName) throws BookNotFoundException {
        return bookService.findBooksByName(bookName);
    }

}
