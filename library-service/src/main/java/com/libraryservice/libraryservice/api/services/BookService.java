package com.libraryservice.libraryservice.api.services;


import com.libraryservice.libraryservice.api.BookRecordServiceMessageSender;
import com.libraryservice.libraryservice.api.dto.BookDto;
import com.libraryservice.libraryservice.api.entity.Book;
import com.libraryservice.libraryservice.api.exceptions.BookIsPresentException;
import com.libraryservice.libraryservice.api.exceptions.BookIsTakenException;
import com.libraryservice.libraryservice.api.exceptions.BookNotFoundException;
import com.libraryservice.libraryservice.api.exceptions.MessageSenderException;
import com.libraryservice.libraryservice.api.mappers.BookMapper;
import com.libraryservice.libraryservice.api.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.event.HyperlinkEvent;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper mapper;
    private final BookRecordServiceMessageSender messageSender;

    public ResponseEntity<List<BookDto>> getBooks(){
        return new ResponseEntity<>(bookRepository.findAll().stream()
                .map(book -> mapper.entityToDto(book)).collect(Collectors.toList()), HttpStatus.OK);
    }

    public HttpStatus addBooks(List<BookDto> bookDtoList) throws BookIsPresentException{
        for(BookDto bookDto : bookDtoList){
            Optional<Book> book_opt = bookRepository.findByISBN(bookDto.getISBN());
            if(book_opt.isPresent()) throw new BookIsPresentException(
                    String.format("book with ISBN : %s is present",bookDto.getISBN()));
        }
        bookDtoList.forEach(bookDto -> {
            Book book = mapper.dtoToEntity(bookDto);
            bookRepository.save(book);
        });
        return HttpStatus.OK;
    }


    public HttpStatus deleteAllBooks(){
        bookRepository.deleteAll();
        return HttpStatus.OK;
    };

    public HttpStatus deleteBookById(UUID bookId) throws BookNotFoundException{
        Optional<Book> opt_book = bookRepository.findById(bookId);
        if(opt_book.isPresent()) {
            bookRepository.delete(opt_book.get());
            return HttpStatus.OK;
        } else throw new BookNotFoundException(String.format("Book with ID = %s not found",bookId));
    }

    public ResponseEntity<BookDto> getBookById(UUID bookId) throws BookNotFoundException{
        Optional<Book> opt_book = bookRepository.findById(bookId);
        if(opt_book.isPresent()){
            return new ResponseEntity<>(mapper.entityToDto(opt_book.get()),HttpStatus.OK);
        } else throw new BookNotFoundException(String.format("Book with ID = %s not found",bookId));

    }

    public HttpStatus updateBook(BookDto bookDto) throws BookNotFoundException{
        Optional<Book> opt_book = bookRepository.findByISBN(bookDto.getISBN());
        if(opt_book.isPresent()){
            Book bookDb = opt_book.get();
            if(Objects.nonNull(bookDb.getDescription()) && !"".equals(bookDto.getDescription())){
                bookDb.setDescription(bookDto.getDescription());
            }
            if(Objects.nonNull(bookDb.getName()) && !"".equals(bookDto.getName())){
                bookDb.setName(bookDto.getName());
            }
            if(Objects.nonNull(bookDb.getAuthor()) && !"".equals(bookDto.getAuthor())){
                bookDb.setAuthor(bookDto.getAuthor());
            }
            if(Objects.nonNull(bookDb.getGenre()) && !"".equals(bookDto.getGenre())){
                bookDb.setGenre(bookDto.getGenre());
            }
            bookRepository.save(bookDb);
            return HttpStatus.OK;
        } else throw new BookNotFoundException(String.format("Book with ISBN %s not found", bookDto.getISBN()));
    }


    public ResponseEntity<BookDto> takeBookByIsbn(String ISBN)
            throws BookNotFoundException,
            MessageSenderException,
            UnsupportedEncodingException,
            BookIsTakenException{
        Optional<Book> book_opt = bookRepository.findByISBN(ISBN);
        if(book_opt.isPresent()){
            HttpStatus status = messageSender.sendMessageToAddBookInRecordService(book_opt.get());
            log.info(status.toString());
            if(status != HttpStatus.OK) throw new BookIsTakenException(String.format("book with ISBN : %s is taken.",ISBN));
            return new ResponseEntity<>(mapper.entityToDto(book_opt.get()),HttpStatus.OK);
        }else throw new BookNotFoundException(String.format("Book with ISBN : %s is not found", ISBN));
    }

    public ResponseEntity<BookDto> getBookByIsbn(String ISBN) throws BookNotFoundException{
        Optional<Book> book_opt = bookRepository.findByISBN(ISBN);
        if(book_opt.isPresent()){
            return new ResponseEntity<>(mapper.entityToDto(book_opt.get()),HttpStatus.OK);
        }else throw new BookNotFoundException(String.format("book with ISBN : %s is not found.",ISBN));
    }

    public HttpStatus deleteBookByIsbn(String ISBN) throws BookNotFoundException{
        Optional<Book> book_opt = bookRepository.findByISBN(ISBN);
        if(book_opt.isPresent()) {
            bookRepository.delete(book_opt.get());
            return HttpStatus.OK;
        }
        else throw new BookNotFoundException(String.format("book with ISBN : %s not found.", ISBN));
    }

}
