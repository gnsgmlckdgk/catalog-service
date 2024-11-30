package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.config.PolarProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@Slf
@SpringBootApplication
@ConfigurationPropertiesScan	// 스프링 콘텍스트에 설정 데이터 빈을 로드한다.
public class CatalogServiceApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(CatalogServiceApplication.class, args);

		// Bean이 제대로 로드되었는지 확인
		var properties = ctx.getBean(PolarProperties.class);
		log.info("Main: ClientProperties loaded = {}", properties);
	}

}
