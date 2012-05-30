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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kuali.kra.dao.ojb.StoredFunctionDao;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.krms.service.KcKrmsJavaFunctionTermService;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krms.api.engine.TermResolutionException;
import org.kuali.rice.krms.api.engine.TermResolver;

/**
 * This class is for resolving terms for StoredFuncions. It extract values from prerequisites, execute Stored Function 
 * and resolves the result.
 * @param <Object>
 */
public class JavaFunctionResolver extends FunctionTermResolver{

    public JavaFunctionResolver(List<String> orderedInputParams, Set<String> parameterNames, String output) {
        super(orderedInputParams, parameterNames, output);
    }

    protected String executeFunction(Map<String, Object> resolvedPrereqs,Map<String,String> resolvedParameters) {
        String methodName = getOutput();
        List<Object> orderedParamValues = extractParamValues(resolvedPrereqs,resolvedParameters);
        return callFunction(methodName,orderedParamValues);
    }
    
    @SuppressWarnings("rawtypes")
    private String callFunction(String methodName,List<Object> orderedParamValues) {
        try {
            Class[] classtypes = new Class[orderedParamValues.size()];
            for (int i = 0; i < orderedParamValues.size(); i++) {
                Object objValue = orderedParamValues.get(i);
                classtypes[i] = objValue.getClass();
            }
            Object javaFucntionService = KraServiceLocator.getService(KcKrmsJavaFunctionTermService.class);
            Class javaFucntionServiceClass = javaFucntionService.getClass();
            Method method = javaFucntionServiceClass.getMethod(methodName, classtypes);
            return (String)method.invoke(javaFucntionService,orderedParamValues.toArray());
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
