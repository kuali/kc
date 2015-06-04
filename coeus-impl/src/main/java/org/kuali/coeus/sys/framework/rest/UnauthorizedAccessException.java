package org.kuali.coeus.sys.framework.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedAccessException extends RuntimeException {

	public UnauthorizedAccessException() {
		super();
	}
	
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
