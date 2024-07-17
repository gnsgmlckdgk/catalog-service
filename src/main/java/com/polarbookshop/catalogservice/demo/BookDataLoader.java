package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata")    // testdata 프로파일 일때만 활성화 된다.
public class BookDataLoader {

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @EventListener(ApplicationReadyEvent.class) // 이 이벤트는 애플리케이션 시작단계가 완료되면 발생한다.
    public void loadBookTestData() {

        bookRepository.deleteAll(); // 빈 데이터베이스로 시작하기 위해 기존 책이 있다면 모두 삭제한다.

        var book1 = Book.of("1234567891", "Northern Lights", "Lyra Silverstar", 9.90, "Polarsophia");
        var book2 = Book.of("1234567892", "Polar Journey", "Iorek Polarson", 12.90, "Polarsophia");

        bookRepository.saveAll(List.of(book1, book2));  // 여러 객체를 한꺼번에 저장한다.
    }
}
