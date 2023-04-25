package com.cos.book.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity //서버 실행시에 테이블이 db에 생성됨
@Table(name = "book") //db에 book 테이블 매핑
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	@Id //pk를 해당 변수로 하겠다는 뜻
    @GeneratedValue(strategy = GenerationType.IDENTITY) //해당 데이터베이스 번호증가 전략을 따라가겠다는 뜻
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    private LocalDateTime publishedAt;

    @Builder
    public Book(String title, String author, LocalDateTime publishedAt) {
        this.title = title;
        this.author = author;
        this.publishedAt = publishedAt;
    }
}
