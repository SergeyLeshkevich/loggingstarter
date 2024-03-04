package ru.clevertec.loggingstarter.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Aspect class for logging.
 * Implements logging logic around methods annotated with @Loggable.
 *
 * @author Sergey Leshkevich
@version 1.0
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class LoggingAspect {

    /**
     * Pointcut definition for methods within classes annotated with @Loggable.
     */
    @Pointcut("within(@ru.clevertec.loggingstarter.annotation.Loggable *)")
    public void isLoggableClass() {
    }


    /**
     * Advice method for logging around methods annotated with @Loggable.
     *
     * @param joinPoint The ProceedingJoinPoint for the intercepted method.
     * @return The result of the intercepted method.
     * @throws Throwable Throws any exceptions that the intercepted method might throw.
     */
    @Around("isLoggableClass()")
    public Object loggingMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalDateTime before = LocalDateTime.now();
        Object result = joinPoint.proceed(joinPoint.getArgs());
        LocalDateTime after = LocalDateTime.now();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String logMessage = """
                %s%s.%s :
                Request : %s
                Response : %s
                Method duration in millis : %s""".formatted("\n",
                className,
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()),
                result,
                Duration.between(before, after).toMillis());
        if (className.endsWith("Handler")) {
            log.error(logMessage);
        } else {
            log.info(logMessage);
        }
        return result;
    }
}
