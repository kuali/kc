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

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.krms.api.repository.function.FunctionDefinition;

/**
 * This class is for resolving terms for StoredFuncions. It extract values from prerequisites, execute Stored Function 
 * and resolves the result.
 * @param <Object>
 */
public class JavaFunctionResolver extends FunctionTermResolver{
    
    protected final Log LOG = LogFactory.getLog(JavaFunctionResolver.class);

    public JavaFunctionResolver(List<String> orderedInputParams, Set<String> parameterNames, String output,FunctionDefinition functionTerm) {
        super(orderedInputParams, parameterNames, output);
        setFunctionTerm(functionTerm);
    }

    protected Object executeFunction(String serviceName,String methodName,Map<String, Object> resolvedPrereqs,Map<String,String> resolvedParameters) {
        List<Object> orderedParamValues = extractParamValues(resolvedPrereqs,resolvedParameters);
        if(serviceName==null)
            throw new RuntimeException("Service name is not defined for the term:"+getOutput());
        return callFunction(serviceName,methodName,orderedParamValues);
    }
    
    @SuppressWarnings("rawtypes")
    private Object callFunction(String serviceName, String methodName,List<Object> orderedParamValues) {
        try {
            Class[] classtypes = new Class[orderedParamValues.size()];
            for (int i = 0; i < orderedParamValues.size(); i++) {
                Object objValue = orderedParamValues.get(i);
                classtypes[i] = objValue.getClass();
            }
            Object javaFucntionService = KraServiceLocator.getService(serviceName);
            Class javaFucntionServiceClass = javaFucntionService.getClass();
            Method method = javaFucntionServiceClass.getMethod(methodName, classtypes);
            return method.invoke(javaFucntionService,orderedParamValues.toArray());
        }catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
