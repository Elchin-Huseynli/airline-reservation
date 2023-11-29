package com.airline.airplane_ms.config;

import com.airline.common_exception.handler.GlobalHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(GlobalHandler.class)
@RequiredArgsConstructor
@Configuration
public class ApplicationConfig {
}
