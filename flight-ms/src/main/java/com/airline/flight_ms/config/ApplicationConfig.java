package com.airline.flight_ms.config;

import com.airline.common_exception.handler.GlobalHandler;
import com.airline.common_exception.util.MessagesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({GlobalHandler.class, MessagesUtil.class})
@RequiredArgsConstructor
@Configuration
public class ApplicationConfig {

}
