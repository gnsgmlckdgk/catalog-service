package com.polarbookshop.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

public record Book (
        @Id     // 이 필드를 엔티티에 대한 기본 키로 식별한다.
        Long id,
        @NotBlank(message = "The book ISBN must be defined.")
        @Pattern(
                regexp = "^([0-9]{10}|[0-9]{13})$",     // 10자리 or 13자리
                message = "The ISBN format must be valid."
        )
        String isbn,
        @NotBlank(message = "The book title must be defined.")
        String title,
        @NotBlank(message = "The book author must be defined.")
        String author,
        // Null이 아니고 0보다 큰 값을 가져야 한다.
        @NotNull(message = "The book price must be defined.")
        @Positive(
                message = "The book price must be greater than zero."
        )
        Double price,
        String publisher,

        @CreatedDate    // 엔티티가 생성된 때
        Instant createdDate,
        @LastModifiedDate       // 엔티티가 마지막으로 수정된 때
        Instant lastModifiedDate,
        // 낙관적 잠금을 위해 사용되는 엔티티 버전 번호(데이터베이스 트랜잭션에서 동시에 같은 데이터를 업데이트하는 충돌을 방지하기 위한 방법 중 하나)
        @Version
        int version

) {
        public static Book of(
                String isbn, String title, String author, Double price, String publisher
        ) {
                // id가 null이고 버전이 0 이면 새로운 엔티티로 인식한다.
                return new Book(null, isbn, title, author, price, publisher, null, null, 0);
        }
}
