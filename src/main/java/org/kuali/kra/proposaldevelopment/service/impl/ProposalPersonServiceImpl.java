/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonService;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.krad.service.BusinessObjectService;
/**
 * 
 * This class...
 */
public class ProposalPersonServiceImpl implements ProposalPersonService {
    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;
     
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * Sets the KC Person Service.
     * @param kcPersonService the kc person service
     */
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
    
    public String getPersonName(ProposalDevelopmentDocument doc, String userId) {
        String propPersonName = null;
        List<ProposalPerson> proposalPersons = doc.getDevelopmentProposal().getProposalPersons();
        if(proposalPersons.isEmpty()){
            Map<String,String> queryMap = new HashMap<String,String>();
            queryMap.put("proposalNumber", doc.getDevelopmentProposal().getProposalNumber());
            proposalPersons = (List)getBusinessObjectService().findMatching(ProposalPerson.class, queryMap);
        }
        for (ProposalPerson proposalPerson : proposalPersons) {
            if(StringUtils.equals(proposalPerson.getPersonId(), userId)){
                propPersonName = proposalPerson.getFullName();
                break;
            }
        }
        if(StringUtils.isBlank(propPersonName)){
            KcPerson person = this.kcPersonService.getKcPersonByPersonId(userId);
            propPersonName = person.getFullName();
        }
        return propPersonName;
    }
    
    public KcPerson getPerson(String loggedInUser) {
        return this.kcPersonService.getKcPersonByUserName(loggedInUser);
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalPersonService#getProposalPersonById(java.lang.Integer, java.lang.Integer)
     */
    public ProposalPerson getProposalPersonById(String proposalNumber, Integer proposalPersonNumber) {
        Map<String, String> keys = new HashMap<String, String>();
        keys.put("proposalNumber", proposalNumber.toString());
        keys.put("proposalPersonNumber", proposalPersonNumber.toString());
        
        return (ProposalPerson) getBusinessObjectService().findByPrimaryKey(ProposalPerson.class, keys);
    }
    
    public List<ProposalPerson> getProposalKeyPersonnel(String proposalNumber, String roleCode) {
        Map<String, String> keys = new HashMap<String, String>();
        keys.put("proposalNumber", proposalNumber.toString());
        keys.put("proposalPersonRoleId", roleCode);
          
        return (List<ProposalPerson>) getBusinessObjectService().findMatching(ProposalPerson.class, keys);
    }
}
