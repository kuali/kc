/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.service.impl;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.service.AwardHierarchyUIService;
import org.kuali.kra.service.MedusaService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

public class MedusaServiceImpl implements MedusaService {
    
    BusinessObjectService businessObjectService;
    AwardHierarchyUIService awardHierarchyUIService;

    public String getMedusaByAward() throws ParseException {
        StringBuilder sb = new StringBuilder();
        sb.append("<h3>");
        Long awardId = new Long(1);        
        Collection<AwardFundingProposal> instituteProposals = businessObjectService.findMatching(AwardFundingProposal.class, getFieldValues("awardId", awardId));
        for(AwardFundingProposal awardFundingProposal : instituteProposals){         
            sb.append("Institutional Proposal " + awardFundingProposal.getProposalId() + "%3A" );
            
            sb.append("%5A");
            Collection<AwardFundingProposal> awards = businessObjectService.findMatching(AwardFundingProposal.class, getFieldValues("proposalId", awardFundingProposal.getProposalId()));
            for(AwardFundingProposal award : awards){
                sb.append("%5C1 ");
                sb.append("Award" + award.getAwardId());
                sb.append(" %5C2");
            }   
            sb.append("%5B");
        }
        sb.append("</h3>");
       
        return sb.toString();
    }

    public String getMedusaByProposal() {
        StringBuilder sb = new StringBuilder();
        Long awardId = new Long(1);
        sb.append("<h3>");
        Collection<AwardFundingProposal> awards = businessObjectService.findMatching(AwardFundingProposal.class, getFieldValues("awardId", awardId));
        for(AwardFundingProposal award : awards){
            sb.append("Award " + award.getAwardId() + "%3A" );
            sb.append("%5A");
            Collection<AwardFundingProposal> instituteProposals = businessObjectService.findMatching(AwardFundingProposal.class, getFieldValues("proposalId", award.getAwardId()));
            for(AwardFundingProposal instituteProposal : instituteProposals){
                sb.append("%5C1 ");
                sb.append("Institutional Proposal " + instituteProposal.getProposalId());
                sb.append(" %5C2");
            }
            sb.append("%5B");
        }
        sb.append("</h3>");
        return sb.toString();
    }

    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    protected Map<String, Object> getFieldValues(String key, Object value){
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(key, value);
        return fieldValues;
    }

    /**
     * Gets the awardHierarchyUIService attribute. 
     * @return Returns the awardHierarchyUIService.
     */
    public AwardHierarchyUIService getAwardHierarchyUIService() {
        return awardHierarchyUIService;
    }

    /**
     * Sets the awardHierarchyUIService attribute value.
     * @param awardHierarchyUIService The awardHierarchyUIService to set.
     */
    public void setAwardHierarchyUIService(AwardHierarchyUIService awardHierarchyUIService) {
        this.awardHierarchyUIService = awardHierarchyUIService;
    }

}
