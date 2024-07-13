package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testdata")    // testdata 프로파일 일때만 활성화 된다.
public class BookDataLoader {

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @EventListener(ApplicationReadyEvent.class) // 이 이벤트는 애플리케이션 시작단계가 완료되면 발생한다.
    public void loadBookTestData() {
        var book1 = new Book("1234567891", "Northern Lights", "Lyra Silverstar", 9.90);
        var book2 = new Book("1234567892", "Polar Journey", "Iorek Polarson", 12.90);

        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
