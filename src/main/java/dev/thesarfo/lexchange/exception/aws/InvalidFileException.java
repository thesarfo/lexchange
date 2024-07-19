package dev.thesarfo.lexchange.exception.aws;

public class InvalidFileException extends RuntimeException{

    public InvalidFileException(String message){
        super(message);
    }
}
