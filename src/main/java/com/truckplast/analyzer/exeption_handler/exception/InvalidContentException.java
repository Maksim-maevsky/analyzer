package com.truckplast.analyzer.exeption_handler.exception;

import lombok.Data;

@Data
public class InvalidContentException extends RuntimeException {

    public InvalidContentException(String message) {
        super(message);
    }

}
