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
import org.kuali.RiceConstants;
import org.kuali.core.bo.BusinessObject;
import org.kuali.core.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.core.lookup.LookupUtils;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.Constants;
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
        LookupUtils.removeHiddenCriteriaFields( getBusinessObjectClass(), fieldValues );
        setBackLocation(fieldValues.get(RiceConstants.BACK_LOCATION));
        setDocFormKey(fieldValues.get(RiceConstants.DOC_FORM_KEY));
        setReferencesToRefresh(fieldValues.get(RiceConstants.REFERENCES_TO_REFRESH));
        List<S2sOpportunity> s2sOpportunity=new ArrayList<S2sOpportunity>();        
        if(fieldValues!=null && (fieldValues.get(Constants.CFDA_NUMBER)!=null && !StringUtils.equals(fieldValues.get(Constants.CFDA_NUMBER).trim(),""))||(fieldValues.get(Constants.OPPORTUNITY_ID)!=null && !StringUtils.equals(fieldValues.get(Constants.OPPORTUNITY_ID).trim(),""))){
            s2sOpportunity = s2SService.searchOpportunity(fieldValues.get(Constants.CFDA_NUMBER),fieldValues.get(Constants.OPPORTUNITY_ID),"");
            if(s2sOpportunity!=null){
                return s2sOpportunity;
            }else{
                if(fieldValues.get(Constants.CFDA_NUMBER)!=null && !StringUtils.equals(fieldValues.get(Constants.CFDA_NUMBER).trim(),"")){
                    GlobalVariables.getErrorMap().putError(Constants.CFDA_NUMBER, KeyConstants.ERROR_IF_CFDANUMBER_IS_INVALID);
                }
                if(fieldValues.get(Constants.OPPORTUNITY_ID)!=null && !StringUtils.equals(fieldValues.get(Constants.OPPORTUNITY_ID).trim(),"")){
                    GlobalVariables.getErrorMap().putError(Constants.OPPORTUNITY_ID, KeyConstants.ERROR_IF_OPPORTUNITY_ID_IS_INVALID);
                }
            }
            return new ArrayList<S2sOpportunity>();
        }else{
            GlobalVariables.getErrorMap().putError(Constants.NO_FIELD, KeyConstants.ERROR_IF_CFDANUMBER_AND_OPPORTUNITY_ID_IS_NULL);              
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
