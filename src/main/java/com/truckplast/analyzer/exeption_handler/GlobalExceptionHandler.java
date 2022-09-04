package com.truckplast.analyzer.exeption_handler;



import com.truckplast.analyzer.exeption_handler.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(WriteByteArrayToFileExeption.class)
    public ResponseEntity<IncorrectData> handleException(WriteByteArrayToFileExeption exception) {

        IncorrectData incorrectData = incorrectDataFilling(exception);

        return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidContentException.class)
    public ResponseEntity<IncorrectData> handleException(InvalidContentException exception) {

        IncorrectData incorrectData = incorrectDataFilling(exception);

        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongPartStorageNameException.class)
    public ResponseEntity<IncorrectData> handleException(WrongPartStorageNameException exception) {

        IncorrectData incorrectData = incorrectDataFilling(exception);

        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WorkBookCreationIOException.class)
    public ResponseEntity<IncorrectData> handleException(WorkBookCreationIOException exception) {

        IncorrectData incorrectData = incorrectDataFilling(exception);

        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DeleteFileException.class)
    public ResponseEntity<IncorrectData> handleException(DeleteFileException exception) {

        IncorrectData incorrectData = incorrectDataFilling(exception);

        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyFileNotFoundException.class)
    public ResponseEntity<IncorrectData> handleException(EmptyFileNotFoundException exception) {

        IncorrectData incorrectData = incorrectDataFilling(exception);

        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongPartStorageKeyException.class)
    public ResponseEntity<IncorrectData> handleException(WrongPartStorageKeyException exception) {

        IncorrectData incorrectData = incorrectDataFilling(exception);

        return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
    }

    private IncorrectData incorrectDataFilling(Exception exception) {

        IncorrectData incorrectData = new IncorrectData();
        log.error("Message: {} Error UUID code: {}", exception.getMessage(), incorrectData.getErrorCode());
        exception.printStackTrace();
        incorrectData.setMessage(exception.getMessage());

        return incorrectData;
    }
}