/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.sys.framework.validation;

import java.util.ArrayList;
import java.util.HashMap;
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
            errors = new HashMap<>();
        }

        List<String> values = errors.get(key);
        if (values == null) {
            values = new ArrayList<>();
            errors.put(key, values);
        }

        values.add(value);
    }

    public ErrorMessageMap merge(ErrorMessageMap errorMessageMap) {
        errorMessageMap.errors.entrySet().forEach(entry -> {
            List<String> values = errors.get(entry.getKey());
            if (values == null) {
                values = new ArrayList<>();
                errors.put(entry.getKey(), values);
            }

            values.addAll(entry.getValue());
        });
        return this;
    }
}
