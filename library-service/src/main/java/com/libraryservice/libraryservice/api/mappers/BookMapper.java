package com.libraryservice.libraryservice.api.mappers;


import com.libraryservice.libraryservice.api.dto.BookDto;
import com.libraryservice.libraryservice.api.entity.Book;
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