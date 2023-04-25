package com.cos.book.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cos.book.dto.Book;

//@Repository 적어야 스프링 IOC에 빈으로 등록되는데
//jpaRepository를 extends하면 생략 가능
//jpaRepository는 CRUD함수를 사용할 수 있음
public interface BookRepository extends JpaRepository<Book, Long> {
	@Query("SELECT b FROM Book b WHERE b.author = :author")
    List<Book> findByAuthor(@Param("author") String author);
}