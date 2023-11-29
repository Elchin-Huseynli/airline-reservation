package com.airline.notification_ms.config;

import com.airline.common_file_generator.service.PdfGenerator;
import com.airline.common_notification.config.CommonApplicationConfig;
import com.airline.common_notification.service.EmailService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Import({EmailService.class, CommonApplicationConfig.class, PdfGenerator.class})
@Configuration
public class ApplicationConfig {

}