/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.impl.krms;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class is for resolving terms for StoredFuncions. It extract values from prerequisites, execute Stored Function 
 * and resolves the result.
 */
public class StoredFunctionResolver extends FunctionTermResolver {

    public StoredFunctionResolver(List<String> orderedInputParams, Set<String> parameterNames, String output) {
        super(orderedInputParams, parameterNames, output);
    }

    protected String executeFunction(String packageName,String functionName,Map<String, Object> resolvedPrereqs,Map<String,String> resolvedParameters) {
        if(packageName!=null) {
            functionName = packageName+"."+functionName;
        }
        List<Object> orderedParamValues = extractParamValues(resolvedPrereqs,resolvedParameters);
        return callFunction(functionName,orderedParamValues);
    }
    
    private String callFunction(String functionName, List<Object> orderedParamValues) {
        StoredFunctionDao storedFunctionDao = getStoredFucntionDao();
        return storedFunctionDao.executeFunction(functionName, orderedParamValues);
    }
    private StoredFunctionDao getStoredFucntionDao() {
        return KcServiceLocator.getService(StoredFunctionDao.class);
    }
}
