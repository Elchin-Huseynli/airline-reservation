package com.airline.airplane_ms.config;

import com.airline.common_exception.util.MessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@RequiredArgsConstructor
@Configuration
@Import(MessagesUtil.class)
public class ApplicationConfig {

}
