package com.semicolon.maverickshub.exceptions;

public class MediaUploadFailedException extends RuntimeException {
    public MediaUploadFailedException(String message) {
        super(message);
    }
}
