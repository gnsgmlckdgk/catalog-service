package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(	// 완전한 스프링 웹 애플리케이션 콘텍스트와 임의의 포트를 듣는 서블릿 컨테이너를 로드한다.
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureTestDatabase( // 테스트컨테이너를 이용해야 하기 때문에 내장 테스트 데이터베이스 사용을 비활성화한다.
		replace = AutoConfigureTestDatabase.Replace.NONE
)
@ActiveProfiles("integration")	// application-integration.yml 에서 설정을 로드하기 위해 integration 프로파일을 활성화한다.
class CatalogServiceApplicationTests {

	@Autowired	// 생성자 기반 의존성 주입이 권장되나 테스트 클래스에서는 이 방식이 허용된다.
	private WebTestClient webTestClient;

	@Test
	void whenPostRequestThenBookCreated() {
		var expectedBook = Book.of("1231231231", "Title", "Author", 9.90, "Polarsophia");

		webTestClient
				.post()
				.uri("/books")
				.bodyValue(expectedBook)	// 요청 바디
				.exchange()	// 요청전송
				.expectStatus().isCreated()	// HTTP 응답이 "201생성" 상태를 갖는지 확인한다.
				.expectBody(Book.class).value(actualBook -> {
					assertThat(actualBook).isNotNull();	// HTTP 응답의 본문이 널 값이 아닌지 확인한다.
					assertThat(actualBook.isbn())
							.isEqualTo(expectedBook.isbn());	// 생성된 객체가 예상과 동일한지 확인한다.
				});
	}

	@Test
	void contextLoads() {

	}

}
