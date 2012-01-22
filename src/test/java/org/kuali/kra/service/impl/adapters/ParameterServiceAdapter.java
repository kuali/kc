package org.kuali.kra.service.impl.adapters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kuali.rice.coreservice.api.parameter.Parameter;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

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
    
}
