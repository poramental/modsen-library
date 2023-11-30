package com.example.bookrecordservice.services;

import com.example.bookrecordservice.dto.BookDto;
import com.example.bookrecordservice.entity.Book;
import com.example.bookrecordservice.exceptions.BookNotFoundException;
import com.example.bookrecordservice.mappers.BookMapper;
import com.example.bookrecordservice.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookRecordService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public HttpStatus addBook(BookDto bookDto){
        Book book = bookMapper.dtoToEntity(bookDto);
        try{
           bookRepository.save(book);
           return HttpStatus.OK;
        }catch (Exception e){
            return HttpStatus.CONFLICT;
        }
    }

    public HttpStatus deleteBook(UUID bookId) throws BookNotFoundException{
        Optional<Book> book_opt = bookRepository.findById(bookId);
        if(book_opt.isPresent()){
            bookRepository.delete(book_opt.get());
            return HttpStatus.OK;
        }else throw new BookNotFoundException("book bot found");
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book findByName(String bookName)  throws BookNotFoundException{
        Optional<Book> book_opt = bookRepository.findByName(bookName);
        if(book_opt.isPresent()){
            return book_opt.get();
        }else throw new BookNotFoundException("book with name " + bookName +" not found.");

    }
}
