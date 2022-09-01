package com.truckplast.analyzer.exeption_handler.exception;

import lombok.Data;

@Data
public class DeleteFileException extends RuntimeException {

    public DeleteFileException(String message) {
        super(message);
    }

}
