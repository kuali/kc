package org.kuali.coeus.sys.framework.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class DataDictionaryValidationException extends RuntimeException {
	private Map<String, List<String>> errors = new HashMap<>();
	public DataDictionaryValidationException(Map<String, List<String>> errors) {
		this.errors = errors;
	}
	public Map<String, List<String>> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, List<String>> errors) {
		this.errors = errors;
	}
}
