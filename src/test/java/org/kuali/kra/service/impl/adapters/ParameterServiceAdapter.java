package org.kuali.kra.service.impl.adapters;

import org.kuali.rice.kns.bo.Parameter;
import org.kuali.rice.kns.service.ParameterEvaluator;
import org.kuali.rice.kns.service.ParameterService;

import java.util.List;
import java.util.Map;

/**
 * Adapter for ParameterService
 */
public class ParameterServiceAdapter implements ParameterService {
    public boolean parameterExists(Class<? extends Object> componentClass, String parameterName) {
        return false;
    }

    public boolean getIndicatorParameter(Class<? extends Object> componentClass, String parameterName) {
        return false;
    }

    public boolean getIndicatorParameter(String namespaceCode, String detailTypeCode, String parameterName) {
        return false;
    }

    public Parameter retrieveParameter(String namespaceCode, String detailTypeCode, String parameterName) {
        return null;
    }

    public String getParameterValue(Class<? extends Object> componentClass, String parameterName) {
        return null;
    }

    public String getParameterValue(Class<? extends Object> componentClass, String parameterName, String constrainingValue) {
        return null;
    }

    public String getParameterValue(String namespaceCode, String detailTypeCode, String parameterName) {
        return null;
    }

    public List<String> getParameterValues(Class<? extends Object> componentClass, String parameterName) {
        return null;
    }

    public List<String> getParameterValues(Class<? extends Object> componentClass, String parameterName, String constrainingValue) {
        return null;
    }

    public List<String> getParameterValues(String namespaceCode, String detailTypeCode, String parameterName) {
        return null;
    }

    public ParameterEvaluator getParameterEvaluator(Class<? extends Object> componentClass, String parameterName) {
        return null;
    }

    public ParameterEvaluator getParameterEvaluator(String namespaceCode, String detailTypeCode, String parameterName) {
        return null;
    }

    public ParameterEvaluator getParameterEvaluator(Class<? extends Object> componentClass, String parameterName, String constrainedValue) {
        return null;
    }

    public ParameterEvaluator getParameterEvaluator(String namespaceCode, String detailTypeCode, String parameterName, String constrainedValue) {
        return null;
    }

    public ParameterEvaluator getParameterEvaluator(Class<? extends Object> componentClass, String parameterName, String constrainingValue, String constrainedValue) {
        return null;
    }

    public ParameterEvaluator getParameterEvaluator(Class<? extends Object> componentClass, String allowParameterName, String denyParameterName, String constrainingValue, String constrainedValue) {
        return null;
    }

    public void setParameterForTesting(Class<? extends Object> componentClass, String parameterName, String parameterText) {
      
    }

    public void clearCache() {
      
    }

    public String getNamespace(Class<? extends Object> documentOrStepClass) {
        return null;
    }

    public String getDetailType(Class<? extends Object> documentOrStepClass) {
        return null;
    }

    public List<Parameter> retrieveParametersGivenLookupCriteria(Map<String, String> fieldValues) {
        return null;
    }
}
