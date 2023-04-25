package com.cos.book.sevice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.book.dao.BookRepository;
import com.cos.book.dto.Book;

//기능을 정의할 수 있고 트랜잭션을 관리할 수 있음
@Service
public class BookService {

	// 의존성 주입(DI)
	@Autowired
	private BookRepository bookRepository;

	// 모든 책 가져오기
	@Transactional(readOnly = true) // JPA 변경감지라는 내부기능 활성화X, update 시에 정합성을 유지해줌, insert의 유령데이터현상(팬텀현상) 못막음
	public List<Book> findAllBook() {
		return bookRepository.findAll();
	}

	// 책 저장
	@Transactional // 서비스 함수가 종료될 때 커밋할지 롤백할지 트랜잭션 관리하겠다는 뜻
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	// 책 한권 가져오기
	// 못찾는 경우가 있기때문에 예외처리 필수
	@Transactional(readOnly = true)
	public Book findBook(Long id) {
		Optional<Book> bookOptional = bookRepository.findById(id);
		return bookOptional.orElseThrow(() -> new RuntimeException("해당 도서를 찾을 수 없습니다."));
	}

	// 책 수정하기
	@Transactional
	public Book modifyBook(Long id, Book book) {
		Book bookEntity = bookRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 도서를 찾을 수 없습니다."));
		bookEntity.setTitle(book.getTitle());
		bookEntity.setAuthor(book.getAuthor());
		bookEntity.setPublishedAt(book.getPublishedAt());
		return bookEntity;
	}

	// 책 지우기
	@Transactional
	public String deleteBook(Long id) {
		bookRepository.deleteById(id);
		return "delete OK";
	}

}
