package org.kuali.coeus.sys.framework.controller.rest;

import java.util.List;
import java.util.Map;

public class ErrorMessageMap {
    private Map<String, List<String>> errors;

    public ErrorMessageMap() {
    }

    public ErrorMessageMap(Map<String, List<String>> errors) {
        this.errors = errors;
    }

	public Map<String, List<String>> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, List<String>> errors) {
		this.errors = errors;
	}
}
