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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kuali.kra.dao.ojb.StoredFunctionDao;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krms.api.engine.TermResolutionException;
import org.kuali.rice.krms.api.engine.TermResolver;

/**
 * This class is for resolving terms for StoredFuncions. It extract values from prerequisites, execute Stored Function 
 * and resolves the result.
 * @param <Object>
 */
public class StoredFunctionResolver implements TermResolver<Object> {

    private Set<String> prerequisites;
    private String output;;
    public StoredFunctionResolver(Set<String> prerequisites,String output){
        this.prerequisites = prerequisites;
        this.output = output;
    }
    @Override
    public Set<String> getPrerequisites() {
        return this.prerequisites;
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
        return Collections.emptySet();
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
        String result = executeFunction(resolvedPrereqs);
        return result;
    }
    private String executeFunction(Map<String, Object> resolvedPrereqs) {
        String functionName = getOutput();
        List<Object> orderedParamValues = extractParamValues(resolvedPrereqs);
        return callFunction(functionName,orderedParamValues);
    }
    
    private String callFunction(String functionName, List<Object> orderedParamValues) {
        StoredFunctionDao storedFunctionDao = getStoredFucntionDao();
        return storedFunctionDao.executeFunction(functionName, orderedParamValues);
    }
    private StoredFunctionDao getStoredFucntionDao() {
        return KraServiceLocator.getService(StoredFunctionDao.class);
    }
    private List<Object> extractParamValues(Map<String, Object> resolvedPrereqs) {
        Set<String> parameters = getPrerequisites();
        List<Object> extractedParams = new ArrayList<Object>();
        for (String parameter : parameters) {
            Object paramValue = resolvedPrereqs.get(parameter);
            extractedParams.add(paramValue);
        }
        return extractedParams;
    }
    
}
