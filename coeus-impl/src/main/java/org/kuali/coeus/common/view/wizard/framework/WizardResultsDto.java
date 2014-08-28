package org.kuali.coeus.common.view.wizard.framework;

import java.util.HashMap;
import java.util.Map;

public class WizardResultsDto {
    private Map<String,Object> parameterMap;

    public WizardResultsDto() {
        parameterMap = new HashMap<String,Object>();
        parameterMap.put("select",false);
    }

    public Map<String, Object> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, Object> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public Object getParameter(String key) {
        return getParameterMap().get(key);
    }

    public void addParameter(String key, Object value) {
        getParameterMap().put(key,value);
    }
}
