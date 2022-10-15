package com.nbd.service.impl.book.mapper;

import com.nbd.repository.api.book.BookModel;
import com.nbd.service.api.book.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "serialNumber", expression = "java(book.getSerialNumber())")
    Book bookModelToBook(BookModel book);
}
