package com.example.bookrecordservice.services;

import com.example.bookrecordservice.dto.BookDto;
import com.example.bookrecordservice.entity.Book;
import com.example.bookrecordservice.exceptions.BookIsPresentException;
import com.example.bookrecordservice.exceptions.BookNotFoundException;
import com.example.bookrecordservice.mappers.BookMapper;
import com.example.bookrecordservice.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookRecordService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public HttpStatus addBook(BookDto bookDto) throws BookIsPresentException{
        Optional<Book> book_opt = bookRepository.findByISBN(bookDto.getISBN());
        log.info(bookMapper.dtoToEntity(bookDto).toString());
        if(book_opt.isEmpty()) {
            bookRepository.save(bookMapper.dtoToEntity(bookDto));
            return HttpStatus.OK;
        } else throw new BookIsPresentException(String.format("book with ISBN : %s is present.",bookDto.getISBN()));
    }

    public HttpStatus deleteBook(UUID bookId) throws BookNotFoundException{
        Optional<Book> book_opt = bookRepository.findById(bookId);
        if(book_opt.isPresent()){
            bookRepository.delete(book_opt.get());
            return HttpStatus.OK;
        }else throw new BookNotFoundException("book bot found");
    }

    public List<Book> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        books.forEach(book -> log.info(book.toString()));
        return bookRepository.findAll();
    }

    public Book findByISBN(String ISBN)  throws BookNotFoundException{
        Optional<Book> book_opt = bookRepository.findByISBN(ISBN);
        if(book_opt.isPresent()){
            return book_opt.get();
        }else throw new BookNotFoundException(String.format("book with ISBN : %s not found.",ISBN));

    }

    public HttpStatus deleteBookByIsbn(String ISBN) throws BookNotFoundException{
        Optional<Book> book_opt = bookRepository.findByISBN(ISBN);
        if(book_opt.isPresent()){
            bookRepository.delete(book_opt.get());
            return HttpStatus.OK;
        }else throw new BookNotFoundException(String.format("book with ISBN : %s not found.",ISBN));
    }
}
