package com.polarbookshop.catalogservice.domain;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

public class BookValidationTests {
    private static Validator validator;

    @BeforeAll  // 클래스 내의 테스트를 실행하기 전에 가장 먼저 실행할 코드 블록임을 나타냄
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // 유효한 ISBN으로 책을 생성
    @Test   // 테스트케이스
    void whenAllFieldsCorrectThenValidationSucceeds() {
        var book = new Book("1234567890", "Title", "Author", 9.90);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isEmpty();   // 유효성 검사에서 오류가 없음을 확인
    }

    // 유효하지 않은 ISBN 코드로 책을 생성
    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        var book = new Book("a234567890", "titile", "Author", 9.90);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        // 유효성 검사 제약 조건 위반이 잘못된 ISBN에 대한 것인지 확인 (isEqualTo 안에 내용이 Validation message 랑 다르면 테스트 오류 발생)
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid.");
    }
}
