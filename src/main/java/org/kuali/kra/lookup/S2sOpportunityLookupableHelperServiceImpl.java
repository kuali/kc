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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.bo.BusinessObject;
import org.kuali.core.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.core.lookup.LookupUtils;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.service.S2SService;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * This class implements a custom lookup for S2S Grants.gov Opportunity Lookup
 */
@Transactional
public class S2sOpportunityLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {
    
    private S2SService s2SService;
    
    /**
     * 
     * @see org.kuali.core.lookup.KualiLookupableHelperServiceImpl#getSearchResults(java.util.Map)
     * It calls the S2sService#searchOpportunity service to look up the opportunity
     */
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        
        super.getSearchResults(LookupUtils.forceUppercase(getBusinessObjectClass(), fieldValues));
        List<S2sOpportunity> s2sOpportunity=new ArrayList<S2sOpportunity>();
        List<S2sOpportunity> s2sOpportunityTemp=new ArrayList<S2sOpportunity>();
        if(fieldValues!=null && (fieldValues.get("cfdaNumber")!=null && !StringUtils.equals(fieldValues.get("cfdaNumber").trim(),""))||(fieldValues.get("opportunityId")!=null && !StringUtils.equals(fieldValues.get("opportunityId").trim(),""))){
            s2sOpportunityTemp = s2SService.searchOpportunity(fieldValues.get("cfdaNumber"),fieldValues.get("opportunityId"),"");
            if(s2sOpportunityTemp!=null){
                s2sOpportunity = s2sOpportunityTemp;
            }else{
                if(fieldValues.get("cfdaNumber")!=null && !StringUtils.equals(fieldValues.get("cfdaNumber").trim(),"")){                    
                    errorMap.removeFromErrorPath("document");
                    GlobalVariables.getErrorMap().putError("document.cfdaNumber", KeyConstants.ERROR_IF_CFDANUMBER_IS_INVALID);                            
                    errorMap.addToErrorPath("document");                    
                }
                if(fieldValues.get("opportunityId")!=null && !StringUtils.equals(fieldValues.get("opportunityId").trim(),"")){                    
                    errorMap.removeFromErrorPath("document");
                    GlobalVariables.getErrorMap().putError("document.cfdaNumber", KeyConstants.ERROR_IF_OPPORTUNITY_ID_IS_INVALID);                            
                    errorMap.addToErrorPath("document");                    
                }
            }
            return s2sOpportunity;
        }else{            
                errorMap.removeFromErrorPath("document");
                GlobalVariables.getErrorMap().putError("document.cfdaNumber", KeyConstants.ERROR_IF_CFDANUMBER_AND_OPPORTUNITY_ID_IS_NULL);                            
                errorMap.addToErrorPath("document");              
        }        
        return s2sOpportunity;        
    }

    public S2SService getS2SService() {
        return s2SService;
    }

    public void setS2SService(S2SService service) {
        s2SService = service;
    }    
}
