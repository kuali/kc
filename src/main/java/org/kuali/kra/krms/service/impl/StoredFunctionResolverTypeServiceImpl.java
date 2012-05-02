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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kuali.kra.krms.KcKrmsTermFunction;
import org.kuali.kra.krms.KcKrmsTermFunctionParamSpec;
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
public class StoredFunctionResolverTypeServiceImpl implements TermResolverTypeService {
    private BusinessObjectService businessObjectService;
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    @Override
    public TermResolver<?> loadTermResolver(TermResolverDefinition termResolverDefinition) {
        String termId = termResolverDefinition.getOutput().getId();
        List<String> params = getStoredFunctionParameters(termId);
        Set<String> paramsSet = new HashSet<String>(params);
        return new StoredFunctionResolver(paramsSet,termResolverDefinition.getOutput().getName());
    }
    /**
     * 
     * This method uses term id and populate the parameters from KcKrmsTermFunction objects.
     * @param termFunctionId
     * @return
     */
    private List<String> getStoredFunctionParameters(String termFunctionId) {
        Map<String,String> fieldValues = new HashMap<String, String>();
        fieldValues.put("krmsTermId", termFunctionId);
        List<KcKrmsTermFunction> termFunctions = (List<KcKrmsTermFunction>)
                            getBusinessObjectService().findMatching(KcKrmsTermFunction.class, fieldValues);
        if(termFunctions.isEmpty()) return new ArrayList<String>();
        KcKrmsTermFunction termFunction = termFunctions.get(0);
        List<KcKrmsTermFunctionParamSpec> functionParams = termFunction.getTermFunctionParams();
        List<String> params = new ArrayList<String>();
        for (KcKrmsTermFunctionParamSpec termResolverFunctionParamDefinition : functionParams) {
            params.add(termResolverFunctionParamDefinition.getParamName());
        }
        return params;
    }

}
