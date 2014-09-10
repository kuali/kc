package org.kuali.coeus.propdev.impl.core;

import org.kuali.coeus.propdev.impl.person.ProposalPerson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddLineHelper {

    private String lineType;
    private Map<String, String> lookupFieldValues;
    private List<Object> results;
    private Map<String,Object> parameterMap;

    public AddLineHelper() {
        lineType = "";
        lookupFieldValues = new HashMap<String, String>();
        results = new ArrayList<Object>();
        parameterMap = new HashMap<String, Object>();
    }

    public void reset() {
        lineType = "";
        lookupFieldValues.clear();
        results.clear();
        parameterMap.clear();
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public Map<String, String> getLookupFieldValues() {
        return lookupFieldValues;
    }

    public void setLookupFieldValues(Map<String, String> lookupFieldValues) {
        this.lookupFieldValues = lookupFieldValues;
    }

    public List<Object> getResults() {
        return results;
    }

    public void setResults(List<Object> results) {
        this.results = results;
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
}
