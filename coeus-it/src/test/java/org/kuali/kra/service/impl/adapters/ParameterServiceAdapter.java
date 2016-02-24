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
package org.kuali.kra.service.impl.adapters;

import org.kuali.rice.coreservice.api.parameter.Parameter;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Adapter for ParameterService
 */
public class ParameterServiceAdapter implements ParameterService {
    public String getNamespace(Class<? extends Object> documentOrStepClass) {
        return null;
    }

    public String getDetailType(Class<? extends Object> documentOrStepClass) {
        return null;
    }

    public List<Parameter> retrieveParametersGivenLookupCriteria(Map<String, String> fieldValues) {
        return null;
    }

    @Override
    public Parameter createParameter(Parameter parameter) {
        return null;
    }

    @Override
    public Parameter getParameter(Class<?> componentClass, String parameterName) {
        return null;
    }

    @Override
    public Parameter getParameter(String namespaceCode, String componentCode, String parameterName) {
        return null;
    }

    @Override
    public Boolean getParameterValueAsBoolean(Class<?> componentClass, String parameterName, Boolean defaultValue) {
        return null;
    }

    @Override
    public Boolean getParameterValueAsBoolean(Class<?> componentClass, String parameterName) {
        return null;
    }

    @Override
    public Boolean getParameterValueAsBoolean(String namespaceCode, String componentCode, String parameterName, Boolean defaultValue) {
        return null;
    }

    @Override
    public Boolean getParameterValueAsBoolean(String namespaceCode, String componentCode, String parameterName) {
        return null;
    }

    @Override
    public String getParameterValueAsString(Class<?> componentClass, String parameterName, String defaultValue) {
        return null;
    }

    @Override
    public String getParameterValueAsString(Class<?> componentClass, String parameterName) {
        return null;
    }

    @Override
    public String getParameterValueAsString(String namespaceCode, String componentCode, String parameterName, String defaultValue) {
        return null;
    }

    @Override
    public String getParameterValueAsString(String namespaceCode, String componentCode, String parameterName) {
        return null;
    }

    @Override
    public Collection<String> getParameterValuesAsString(Class<?> componentClass, String parameterName) {
        return null;
    }

    @Override
    public Collection<String> getParameterValuesAsString(String namespaceCode, String componentCode, String parameterName) {
        return null;
    }

    @Override
    public String getSubParameterValueAsString(Class<?> componentClass, String parameterName, String subParameterName) {
        return null;
    }

    @Override
    public String getSubParameterValueAsString(String namespaceCode, String componentCode, String parameterName,
            String subParameterName) {
        return null;
    }

    @Override
    public Collection<String> getSubParameterValuesAsString(Class<?> componentClass, String parameterName, String subParameterName) {
        return null;
    }

    @Override
    public Collection<String> getSubParameterValuesAsString(String namespaceCode, String componentCode, String parameterName,
            String subParameterName) {
        return null;
    }

    @Override
    public Boolean parameterExists(Class<?> componentClass, String parameterName) {
        return null;
    }

    @Override
    public Boolean parameterExists(String namespaceCode, String componentCode, String parameterName) {
        return null;
    }

    @Override
    public Parameter updateParameter(Parameter parameter) {
        return null;
    }

	@Override
	public String getParameterValueAsFilteredString(Class<?> arg0, String arg1) {
		return null;
	}

	@Override
	public String getParameterValueAsFilteredString(String arg0, String arg1,
			String arg2) {
		return null;
	}

	@Override
	public String getParameterValueAsFilteredString(Class<?> arg0, String arg1,
			String arg2) {
		return null;
	}

	@Override
	public String getParameterValueAsFilteredString(String arg0, String arg1,
			String arg2, String arg3) {
		return null;
	}

	@Override
	public Collection<String> getParameterValuesAsFilteredString(Class<?> arg0,
			String arg1) {
		return null;
	}

	@Override
	public Collection<String> getParameterValuesAsFilteredString(String arg0,
			String arg1, String arg2) {
		return null;
	}

	@Override
	public String getSubParameterValueAsFilteredString(Class<?> arg0,
			String arg1, String arg2) {
		return null;
	}

	@Override
	public String getSubParameterValueAsFilteredString(String arg0,
			String arg1, String arg2, String arg3) {
		return null;
	}

	@Override
	public Collection<String> getSubParameterValuesAsFilteredString(
			Class<?> arg0, String arg1, String arg2) {
		return null;
	}

	@Override
	public Collection<String> getSubParameterValuesAsFilteredString(
			String arg0, String arg1, String arg2, String arg3) {
		return null;
	}
    
}
