package com.github.pellaton.jazoon2012;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public final class SomeConfiguration {

	@Bean
	public String fooBean() {
		return "foo";
	}

}
