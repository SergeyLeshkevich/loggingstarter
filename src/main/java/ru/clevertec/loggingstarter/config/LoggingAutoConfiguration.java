package ru.clevertec.loggingstarter.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.loggingstarter.aspect.LoggingAspect;

/**
 * Configuration class for logging.
 * Configures and initializes logging based on conditions.
 *
 * @author Sergey Leshkevich
 * @version 1.0
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(LoggingProperties.class)
@ConditionalOnClass(LoggingProperties.class)
@ConditionalOnProperty(prefix = "aop.logging", name = "enabled", havingValue = "true")
public class LoggingAutoConfiguration {

    /**
     * Initialization method called after bean creation.
     * Logs a message indicating that LoggingAutoConfiguration has been initialized.
     */
    @PostConstruct
    void init() {
        log.info("LoggingAutoConfiguration initialized");
    }


    /**
     * Bean creation method for LoggingAspect.
     * Conditional on the absence of a bean of type LoggingAspect.
     *
     * @return The LoggingAspect bean.
     */
    @Bean
    @ConditionalOnMissingBean(LoggingAspect.class)
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}
