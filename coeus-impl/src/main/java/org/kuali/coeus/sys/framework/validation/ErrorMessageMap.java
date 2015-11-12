package org.kuali.coeus.sys.framework.validation;

import org.apache.commons.collections4.map.HashedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ErrorMessageMap {
    private Map<String, List<String>> errors;

    public ErrorMessageMap() {
        super();
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

    public void addError(String key, String value) {
        if (errors == null) {
            errors = new HashedMap<>();
        }

        List<String> values = errors.get(key);
        if (values == null) {
            values = new ArrayList<>();
            errors.put(key, values);
        }

        values.add(value);
    }

    public void mergeErrorMessageMap(ErrorMessageMap errorMessageMap) {
        errorMessageMap.errors.entrySet().forEach(entry -> {
            List<String> values = errors.get(entry.getKey());
            if (values == null) {
                values = new ArrayList<>();
                errors.put(entry.getKey(), values);
            }

            values.addAll(entry.getValue());
        });
    }
}
