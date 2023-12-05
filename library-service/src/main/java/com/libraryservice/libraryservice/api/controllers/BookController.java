package com.libraryservice.libraryservice.api.controllers;


import com.libraryservice.libraryservice.api.dto.BookDto;

import com.libraryservice.libraryservice.api.entity.Book;
import com.libraryservice.libraryservice.api.exceptions.BookIsPresentException;
import com.libraryservice.libraryservice.api.exceptions.BookIsTakenException;
import com.libraryservice.libraryservice.api.exceptions.BookNotFoundException;
import com.libraryservice.libraryservice.api.exceptions.MessageSenderException;
import com.libraryservice.libraryservice.api.services.BookService;
import com.sun.net.httpserver.HttpsServer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("rest/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @GetMapping("/get-all-books")
    public ResponseEntity<List<BookDto>> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping("/add-book")
    public HttpStatus addBooks(@RequestBody List<BookDto> bookDtoList) throws BookIsPresentException {
        return bookService.addBooks(bookDtoList);
    }

    @DeleteMapping("/delete-book-by-id/{bookId}")
    public HttpStatus deleteBookByName(@PathVariable("bookId") UUID bookId) throws BookNotFoundException{
        return bookService.deleteBookById(bookId);
    }

    @DeleteMapping("/delete-book-by-isbn/{ISBN}")
    public HttpStatus deleteBookByIsbn(@PathVariable("ISBN") String ISBN) throws BookNotFoundException{
        return bookService.deleteBookByIsbn(ISBN);
    }

    @DeleteMapping("/delete-all-books")
    public HttpStatus deleteAllBooks(){
        return bookService.deleteAllBooks();
    }

    @GetMapping("/get-book-by-id/{bookId}")
    public ResponseEntity<BookDto> getBookByName(@PathVariable("bookId") UUID bookId) throws BookNotFoundException{
        return bookService.getBookById(bookId);
    }

    @PutMapping("/update-book")
    public HttpStatus updateBook(@RequestBody BookDto bookDto) throws BookNotFoundException{
        return bookService.updateBook(bookDto);
    }

    @GetMapping("/take-book-by-isbn/{ISBN}")
    public ResponseEntity<BookDto> takeBookByIsbn(@PathVariable("ISBN") String ISBN,@RequestHeader("Authorization") String token)
            throws BookNotFoundException,
            MessageSenderException,
            UnsupportedEncodingException,
            BookIsTakenException {
        return bookService.takeBookByIsbn(ISBN, token);
    }

    @GetMapping("/get-book-by-isbn/{ISBN}")
    public ResponseEntity<BookDto> getBookByIsbn(@PathVariable("ISBN") String ISBN) throws BookNotFoundException{
        return bookService.getBookByIsbn(ISBN);
    }

        @DeleteMapping("/return-book-to-library/{ISBN}")
    public HttpStatus returnBook(@PathVariable("ISBN") String ISBN, @RequestHeader("Authorization") String token)  throws BookNotFoundException,
            MessageSenderException{
        return bookService.returnBook(ISBN , token);
    }

}
