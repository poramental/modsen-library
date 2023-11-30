package com.libraryservice.libraryservice.api.services;


import com.libraryservice.libraryservice.api.dto.BookDto;
import com.libraryservice.libraryservice.api.entity.Book;
import com.libraryservice.libraryservice.api.exceptions.BookNotFoundException;
import com.libraryservice.libraryservice.api.mappers.BookMapper;
import com.libraryservice.libraryservice.api.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper mapper;

    public ResponseEntity<List<Book>> getBooks(){
        return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.OK);
    }

    public HttpStatus addBooks(List<BookDto> bookDtoList){
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

    public ResponseEntity<Book> getBookById(UUID bookId) throws BookNotFoundException{
        Optional<Book> opt_book = bookRepository.findById(bookId);
        if(opt_book.isPresent()){
            return new ResponseEntity<Book>(opt_book.get(),HttpStatus.OK);
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

    public List<BookDto> findBooksByName(String bookName) throws BookNotFoundException{
        List<Book> books_db = bookRepository.findBooksByName(bookName);
        if(!books_db.isEmpty()){
            List<BookDto> bookDtos = new ArrayList<>();
            for(Book book : books_db){
                bookDtos.add(mapper.entityToDto(book));
            }
            return bookDtos;
        }else throw new BookNotFoundException(String.format("Book with name %s not found",bookName));


    }

}
