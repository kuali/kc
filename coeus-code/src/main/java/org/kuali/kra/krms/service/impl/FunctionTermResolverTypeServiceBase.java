/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.krms.service.impl;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.krms.FunctionParamComparator;
import org.kuali.kra.krms.FunctionTermResolver;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krms.api.engine.TermResolver;
import org.kuali.rice.krms.api.repository.function.FunctionDefinition;
import org.kuali.rice.krms.api.repository.function.FunctionParameterDefinition;
import org.kuali.rice.krms.api.repository.function.FunctionRepositoryService;
import org.kuali.rice.krms.api.repository.term.TermResolverDefinition;
import org.kuali.rice.krms.framework.type.TermResolverTypeService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 
 * This class implements <code>TermResolverTypeService</code> for resolving Stored Functions or Java Functions as Terms.
 */
public abstract class FunctionTermResolverTypeServiceBase implements TermResolverTypeService {
    private BusinessObjectService businessObjectService;
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    public abstract FunctionTermResolver createFunctionResolver(List<String> orderedInputParams, Set<String> parameterNames, 
                                                                    String output, FunctionDefinition functionTerm);
    
    @Override
    public TermResolver<?> loadTermResolver(TermResolverDefinition termResolverDefinition) {
        String functionId = termResolverDefinition.getOutput().getName();
        FunctionRepositoryService functionRepositoryService = KcServiceLocator.getService("functionRepositoryService");
        FunctionDefinition functionTerm = functionRepositoryService.getFunction(functionId);
        if(functionTerm==null){
            throw new RuntimeException("Not able to find Term for function : "+functionId);
        }
        List<String> params = getFunctionParameters(functionTerm);
        Set<String> paramNames = termResolverDefinition.getParameterNames();
        return createFunctionResolver(params,paramNames,functionId,functionTerm);
    }
    private List<String> getFunctionParameters(FunctionDefinition functionTerm) {
        List<FunctionParameterDefinition> functionParams = functionTerm.getParameters();
        List<FunctionParameterDefinition> modifiableParams = new ArrayList<FunctionParameterDefinition>(functionParams);
        Collections.sort(modifiableParams, new FunctionParamComparator());
        List<String> params = new ArrayList<String>();
        for (FunctionParameterDefinition termResolverFunctionParamDefinition : modifiableParams) {
            params.add(termResolverFunctionParamDefinition.getName());
        }
        return params;
    }
}
