package com.polarbookshop.catalogservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

// 데이터의 생성, 변경, 삭제가 일어날 때마다 감사 이벤트가 생성된다.
@Configuration
@EnableJdbcAuditing // 지속성 엔티티에 대한 감사를 활성화(언제수정했고 누가수정했고 체크)
public class DataConfig {}
