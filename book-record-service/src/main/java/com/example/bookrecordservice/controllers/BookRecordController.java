package com.example.bookrecordservice.controllers;

import com.example.bookrecordservice.dto.BookDto;
import com.example.bookrecordservice.entity.Book;
import com.example.bookrecordservice.exceptions.BookIsPresentException;
import com.example.bookrecordservice.exceptions.BookNotFoundException;
import com.example.bookrecordservice.services.BookRecordService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest/books-record")
public class BookRecordController {

    private final BookRecordService bookRecordService;

    @PostMapping("/add-book")
    public HttpStatus addBook(@RequestBody BookDto bookDto) throws BookIsPresentException {
       return bookRecordService.addBook(bookDto);
    }

    @DeleteMapping("/delete-book-by-id/{id}")
    public HttpStatus deleteBook(@PathVariable("id") UUID bookId) throws BookNotFoundException {
        return bookRecordService.deleteBook(bookId);
    }

    @GetMapping("/get-all-books")
    public List<Book> getAllBooks(){
        return bookRecordService.getAllBooks();
    }

    @GetMapping("/get-book-by-isbn/{ISBN}")
    public Book findBookByName(@PathVariable("ISBN") String ISBN) throws BookNotFoundException{
        return bookRecordService.findByISBN(ISBN);
    }

    @DeleteMapping("/delete-book-by-isbn/{ISBN}")
    public HttpStatus deleteBookByIsbn(@PathVariable("ISBN") String ISBN) throws BookNotFoundException{
        return bookRecordService.deleteBookByIsbn(ISBN);
    }



}
