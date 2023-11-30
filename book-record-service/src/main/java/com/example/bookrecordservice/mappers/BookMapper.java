package com.example.bookrecordservice.mappers;



import com.example.bookrecordservice.dto.BookDto;
import com.example.bookrecordservice.entity.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BookMapper {
    private final ModelMapper mapper;

    public BookMapper(){
        this.mapper = new ModelMapper();
    }

    public BookDto entityToDto(Book book){
        return mapper.map(book,BookDto.class);
    }

    public Book dtoToEntity(BookDto bookDto){
        Book book = mapper.map(bookDto,Book.class);
        book.setBookId(UUID.randomUUID());
        return book;
    }
}