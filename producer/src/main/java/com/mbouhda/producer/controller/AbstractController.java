package com.mbouhda.producer.controller;

import com.mbouhda.producer.exceptions.HTTP400Exception;
import com.mbouhda.producer.exceptions.HTTP404Exception;
import com.mbouhda.producer.exceptions.RestExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class AbstractController implements ApplicationEventPublisherAware {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    protected ApplicationEventPublisher eventPublisher;

    protected static final String DEFAULT_PAGE_SIZE = "10";
    protected static final String DEFAULT_PAGE_NUMBER = "0";

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher  = eventPublisher;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HTTP400Exception.class)
    public @ResponseBody RestExceptionInfo handleBadRequestException(HTTP400Exception exception) {
        log.info("Bad request exception: {}", exception.getLocalizedMessage());
        return new RestExceptionInfo("Bad request received", exception.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HTTP404Exception.class)
    public @ResponseBody RestExceptionInfo handleResourceNotFoundException(HTTP404Exception exception) {
        log.info("Resource not found: {}", exception.getLocalizedMessage());
        return new RestExceptionInfo("Resource not found", exception.getLocalizedMessage());
    }

    public static <T> void checkResourceFound(final T resource) {
        if (resource == null) {
            throw new HTTP404Exception("Resource Not Found");
        }
    }

}
