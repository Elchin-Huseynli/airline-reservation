package com.airline.common_ms.config;

import com.airline.common_exception.handler.GlobalHandler;
import com.airline.common_exception.util.MessagesUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({GlobalHandler.class, MessagesUtil.class})
public class ApplicationConfig {

}
