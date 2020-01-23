package com.mbouhda.producer.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestExceptionInfo {

    private String message;
    private String cause;
}
