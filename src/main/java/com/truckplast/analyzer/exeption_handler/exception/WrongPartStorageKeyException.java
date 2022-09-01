package com.truckplast.analyzer.exeption_handler.exception;

import lombok.Data;

@Data
public class WrongPartStorageKeyException extends RuntimeException {

    public WrongPartStorageKeyException(String message) {
        super(message);
    }

}
