package com.example.bookrecordservice.controllers;

import com.example.bookrecordservice.dto.BookDto;
import com.example.bookrecordservice.entity.Book;
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
@RequestMapping("bookRecordService")
public class BookRecordController {

    private final BookRecordService bookRecordService;

    @PostMapping("/add-book")
    public HttpStatus addBook(@RequestBody BookDto bookDto){
       return bookRecordService.addBook(bookDto);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteBook(@PathVariable("id") UUID bookId) throws BookNotFoundException {
        return bookRecordService.deleteBook(bookId);
    }

    @GetMapping("/get-all-books")
    public List<Book> getAllBooks(){
        return bookRecordService.getAllBooks();
    }

}
