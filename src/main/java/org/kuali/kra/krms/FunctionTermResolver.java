/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.krms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kuali.rice.krms.api.engine.TermResolver;

/**
 * This class is for resolving terms for StoredFuncions. It extract values from prerequisites, execute Stored Function 
 * and resolves the result.
 * @param <Object>
 */
public abstract class FunctionTermResolver implements TermResolver<Object> {

    private List<String> orderedInputParams;
    public List<String> getOrderedInputParams() {
        return orderedInputParams;
    }
    private String output;
    private Set<String> parameterNames;
    public FunctionTermResolver(List<String> orderedInputParams,Set<String> parameterNames,String output){
        this.orderedInputParams = orderedInputParams;
        this.parameterNames = parameterNames;
        this.output = output;
    }
    
    @Override
    public Set<String> getPrerequisites() {
        Set<String> prereqs = new HashSet<String>();
        for (String param : orderedInputParams) {
            if(!parameterNames.contains(param)){
                prereqs.add(param);
            }
        }
        return new HashSet<String>(prereqs);
    }
    /**
     * Returns the name of the function needs to be executed as part of the Term
     * @see org.kuali.rice.krms.api.engine.TermResolver#getOutput()
     */
    @Override
    public String getOutput() {
        return this.output;
    }

    @Override
    public Set<String> getParameterNames() {
        return parameterNames;
    }

    @Override
    public int getCost() {
        return 1;
    }

    /**
     * This method executes the stored function and returns the result. Parameters to Stored Function are 
     * getting passed through resolvedPrereqs.
     * @see org.kuali.rice.krms.api.engine.TermResolver#resolve(java.util.Map, java.util.Map)
     */
    @Override
    public String resolve(Map<String, Object> resolvedPrereqs, Map<String, String> parameters) {
        String result = executeFunction(resolvedPrereqs,parameters);
        return result;
    }
    /**
     * 
     * This method execute the function/method and return 'true' or 'false'
     * @param resolvedPrereqs
     * @return
     */
    protected abstract String executeFunction(Map<String, Object> resolvedPrereqs,Map<String,String> resolvedParameters);
    
    protected List<Object> extractParamValues(Map<String, Object> resolvedPrereqs,Map<String,String> resolvedParameters) {
        List<String> parameters = getOrderedInputParams();
        List<Object> extractedParams = new ArrayList<Object>();
        for (String parameter : parameters) {
            Object paramValue = resolvedPrereqs.get(parameter);
            if(paramValue==null)
                paramValue = resolvedParameters.get(parameter);
            extractedParams.add(paramValue);
        }
        return extractedParams;
    }
    
    
}
