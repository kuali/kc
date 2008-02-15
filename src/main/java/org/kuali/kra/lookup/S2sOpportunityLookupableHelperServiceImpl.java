/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.lookup;

import java.util.List;
import java.util.Map;

import org.kuali.core.bo.BusinessObject;
import org.kuali.core.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.core.lookup.LookupUtils;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.service.S2SService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class S2sOpportunityLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {
    
    private S2SService s2SService;

    public S2SService getS2SService() {
        return s2SService;
    }

    public void setS2SService(S2SService service) {
        s2SService = service;
    }

    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        
        
        //List<S2sOpportunity> s2sOpportunity2 = (List<S2sOpportunity>) super.getSearchResults(LookupUtils.forceUppercase(getBusinessObjectClass(), fieldValues));
        super.getSearchResults(LookupUtils.forceUppercase(getBusinessObjectClass(), fieldValues));        
        List<S2sOpportunity> s2sOpportunity = s2SService.searchOpportunity(fieldValues.get("cfdaNumber"),fieldValues.get("opportunityId"),"");
        //List<S2sOppForms> s2sOppForms = s2SService.parseOpportunityForms(s2sOpportunity);
        return s2sOpportunity;
        //return super.getSearchResults(LookupUtils.forceUppercase(getBusinessObjectClass(), fieldValues));
    }    
}
