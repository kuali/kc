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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krms.api.repository.function.FunctionDefinition;
import org.kuali.rice.krms.api.repository.function.FunctionParameterDefinition;

import java.lang.reflect.Method;
import java.util.*;

/**
 * This class is for resolving terms for StoredFuncions. It extract values from prerequisites, execute Stored Function 
 * and resolves the result.
 */
public class JavaFunctionResolver extends FunctionTermResolver {
    
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
    
    private static HashSet<Class<?>> getWrapperTypes()
    {
        HashSet<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(Boolean.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        return ret;
    }
    private static final HashSet<Class<?>> WRAPPER_TYPES = getWrapperTypes();
    @SuppressWarnings("rawtypes")
    private Object callFunction(String serviceName, String methodName,List<Object> orderedParamValues) {
        try {
            List<Object> functionParamObjects = new ArrayList<Object>();
            List<FunctionParameterDefinition> functionParams = getFunctionTerm().getParameters();
            List<FunctionParameterDefinition> modifiableParams = new ArrayList<FunctionParameterDefinition>(functionParams);
            Collections.sort(modifiableParams, new FunctionParamComparator());
            Class[] classtypes = new Class[orderedParamValues.size()];
            for (int i = 0; i < orderedParamValues.size(); i++) {
                Object objValue = orderedParamValues.get(i);
                String paramClassType = modifiableParams.get(i).getParameterType();
                Class paramClass = Class.forName(paramClassType);
//                if(ClassUtils.isAssignable(objValue.getClass(),paramClass)){
                    
//                }else
                if(WRAPPER_TYPES.contains(paramClass)){
                    Object convertedObject = wrapValue(objValue,paramClassType);
                    classtypes[i] = paramClass;
                    functionParamObjects.add(convertedObject);
                }else{
                    classtypes[i] = paramClass;
                    functionParamObjects.add(objValue);
//                    throw new RuntimeException(paramClassType+" in "+serviceName+"."+methodName+" not defined properly");
                }
                
            }
            Object javaFucntionService = KcServiceLocator.getService(serviceName);
            Class javaFucntionServiceClass = javaFucntionService.getClass();
            Method method = javaFucntionServiceClass.getMethod(methodName, classtypes);
            return method.invoke(javaFucntionService,functionParamObjects.toArray());
        }catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private Object wrapValue(Object objValue, String paramClassType) {
        Object retObj = objValue;
        if(objValue==null){
            return null;
        }
        if(paramClassType.equals("java.lang.Integer")){
            retObj = new Integer(objValue.toString());
        }else if(paramClassType.equals("java.lang.Long")){
            retObj = new Long(objValue.toString());
        }else if(paramClassType.equals("java.lang.Boolean")){
            retObj = new Boolean(objValue.toString());
        }else if(paramClassType.equals("java.lang.Float")){
            retObj = new Float(objValue.toString());
        }else if(paramClassType.equals("java.lang.Double")){
            retObj = new Double(objValue.toString());
        }
        return retObj;
    }
}
