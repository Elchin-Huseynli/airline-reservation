package com.airline.booking_ms.config;

import com.airline.common_exception.handler.GlobalHandler;
import com.airline.common_exception.util.MessagesUtil;
import com.airline.common_file_generator.service.PdfGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({GlobalHandler.class, PdfGenerator.class, MessagesUtil.class})
@Configuration
public class ApplicationConfig {
}
