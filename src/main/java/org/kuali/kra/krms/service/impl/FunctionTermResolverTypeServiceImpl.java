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
package org.kuali.kra.krms.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kuali.kra.krms.JavaFunctionResolver;
import org.kuali.kra.krms.KcKrmsTermFunction;
import org.kuali.kra.krms.KcKrmsTermFunctionParam;
import org.kuali.kra.krms.StoredFunctionResolver;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krms.api.engine.TermResolver;
import org.kuali.rice.krms.api.repository.term.TermResolverDefinition;
import org.kuali.rice.krms.framework.type.TermResolverTypeService;

/**
 * 
 * This class implements <code>TermResolverTypeService</code> for resolving StoreFunctions as Terms.
 * It takes parameters defined in kc_krms_term_function table and load the StoredFunctionResolver to resolve the term.
 */
public class FunctionTermResolverTypeServiceImpl implements TermResolverTypeService {
    private static final String JAVA_FUNCTION = "2";
    private static final String STORED_FUNCTION = "1";
    private BusinessObjectService businessObjectService;
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    @Override
    public TermResolver<?> loadTermResolver(TermResolverDefinition termResolverDefinition) {
        String termName = termResolverDefinition.getOutput().getName();
        Map<String,String> queryMaps = new HashMap<String,String>();
        queryMaps.put("krmsTermName", termName);
        List<KcKrmsTermFunction> terms = (List<KcKrmsTermFunction>) getBusinessObjectService().findMatching(KcKrmsTermFunction.class, queryMaps);
        KcKrmsTermFunction functionTerm = terms.isEmpty() ? null : terms.get(0);
        if(functionTerm==null) return null;
        List<String> params = getFunctionParameters(functionTerm);
        Set<String> paramNames = termResolverDefinition.getParameterNames();
        String outputName = termResolverDefinition.getOutput().getName();
        if(functionTerm.getFunctionType()==null || functionTerm.getFunctionType().equals(STORED_FUNCTION)) {
            return new StoredFunctionResolver(params,paramNames,outputName);
        }else if(functionTerm.getFunctionType().equals(JAVA_FUNCTION)) {
            return new JavaFunctionResolver(params,paramNames,outputName);
        }
        return null;
    }
    private List<String> getFunctionParameters(KcKrmsTermFunction functionTerm) {
        List<KcKrmsTermFunctionParam> functionParams = functionTerm.getTermFunctionParams();
        Collections.sort(functionParams);
        List<String> params = new ArrayList<String>();
        for (KcKrmsTermFunctionParam termResolverFunctionParamDefinition : functionParams) {
            params.add(termResolverFunctionParamDefinition.getParamName());
        }
        return params;
    }
}
