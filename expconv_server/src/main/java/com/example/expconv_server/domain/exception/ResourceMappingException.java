package com.example.expconv_server.domain.exception;

import java.sql.SQLException;

public class ResourceMappingException extends RuntimeException {
    public ResourceMappingException(String message) {
        super(message);
    }
}
