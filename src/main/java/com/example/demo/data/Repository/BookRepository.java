package com.example.demo.data.Repository;

import com.example.demo.data.Entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long>{

}
