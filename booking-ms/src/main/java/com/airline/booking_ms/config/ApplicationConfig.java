package com.airline.booking_ms.config;

import com.airline.common_exception.handler.GlobalHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(GlobalHandler.class)
@Configuration
public class ApplicationConfig {
}
