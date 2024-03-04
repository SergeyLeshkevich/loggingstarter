package ru.clevertec.loggingstarter.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration class for logging properties.
 * Uses Lombok annotations: @Slf4j for logger, @Data for getters, setters, and more.
 * Initialized through Spring's @ConfigurationProperties with prefix "aop.logging".
 *
 * @author Sergey Leshkevich
 * @version 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "aop.logging")
public class LoggingProperties {

    /**
     * Flag indicating whether logging is enabled.
     */
    private boolean enabled;

    /**
     * Initialization method called after bean creation.
     * Logs a message indicating that LoggingProperties has been initialized.
     */
    @PostConstruct
    void init() {
        log.info("LoggingProperties initialized: {}", this);
    }
}
