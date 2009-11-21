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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.service.AwardHierarchyUIService;
import org.kuali.kra.service.MedusaService;
import org.kuali.rice.kns.service.BusinessObjectService;

public class MedusaServiceImpl implements MedusaService {
    
    BusinessObjectService businessObjectService;
    AwardHierarchyUIService awardHierarchyUIService;

    public String getMedusaByProposal(String moduleName, Long moduleIdentifier) {
        StringBuilder sb = new StringBuilder();
        
        
        Collection<AwardFundingProposal> awardFundingProposals1 = getAwardFundingProposals(moduleName, moduleIdentifier);
        if(awardFundingProposals1.size()>0){
            sb.append("<h3>");
            sb.append("P %2A ");
        }
        for(AwardFundingProposal awardFundingProposal : awardFundingProposals1){
            awardFundingProposal.refreshReferenceObject("proposal");
            sb.append("Institutional Proposal " + awardFundingProposal.getProposal().getProposalNumber() + "%3A" );
            
            sb.append("%5A");
            Collection<ProposalAdminDetails> proposalAdminDetails = businessObjectService.findMatching(ProposalAdminDetails.class, getFieldValues("instProposalId", awardFundingProposal.getProposalId()));
            for(ProposalAdminDetails proposalAdminDetail : proposalAdminDetails){
                proposalAdminDetail.refreshReferenceObject("developmentProposal");
                sb.append("%5C1 ");
                sb.append("Development Proposal " + proposalAdminDetail.getDevelopmentProposal().getProposalNumber());
                sb.append(" %5C2");
            }   
            
            Collection<AwardFundingProposal> awardFundingProposals2 = businessObjectService.findMatching(AwardFundingProposal.class, getFieldValues("proposalId", awardFundingProposal.getProposalId()));
            for(AwardFundingProposal awardFundingPropsal : awardFundingProposals2){
                awardFundingPropsal.refreshReferenceObject("award");
                sb.append("%5C1 ");
                sb.append("Award " + awardFundingPropsal.getAward().getAwardNumber());
                sb.append(" %5C2");
            }   
            sb.append("%5B");
        }
        if(awardFundingProposals1.size()>0){
            sb.append("</h3>");
        }
       
        return sb.toString();
    }

    public String getMedusaByAward(String moduleName, Long moduleIdentifier) {
        StringBuilder sb = new StringBuilder();
        
        Collection<AwardFundingProposal> awardFundingProposals1 = getAwardFundingProposals(moduleName, moduleIdentifier);
        
        if(awardFundingProposals1.size()>0){
            sb.append("<h3>");
            sb.append("A %2A ");
        }
        
        for(AwardFundingProposal awradFundingProposal1 : awardFundingProposals1){
            awradFundingProposal1.refreshReferenceObject("award");
            Award award = awradFundingProposal1.getAward();
            sb.append("Award " + award.getAwardNumber() + "%3A" );
            sb.append("%31");
                sb.append("%TB1");
                    sb.append("Award " + award.getAwardNumber());
                sb.append("!TB1");
                sb.append("%TB2");
                    sb.append("Summary ").append(":").append(award.getAwardId()).append(":").append(award.getAwardTypeCode()).append(":");
                    sb.append(award.getSponsorAwardNumber()).append(award.getAwardTypeCode()).append(":").append(award.getSponsorAwardNumber());
                    sb.append(":").append(award.getActivityType().getDescription()).append(":").append(award.getAwardStatus()).append(":").append(award.getTitle());
                sb.append("!TB2");
                sb.append("%TB3");
                sb.append("!TB3");
                sb.append("%TB4");
                sb.append("!TB4");
                sb.append("%TB5");
                sb.append("!TB5");            
            sb.append("%32");
            sb.append("%5A");
            Collection<AwardFundingProposal> awardFundingProposals2 = businessObjectService.findMatching(AwardFundingProposal.class, getFieldValues("proposalId", awradFundingProposal1.getProposalId()));
            for(AwardFundingProposal awardFundingProposal2 : awardFundingProposals2){
                awradFundingProposal1.refreshReferenceObject("proposal");
                sb.append("%5C1 ");
                sb.append("Institutional Proposal " + awardFundingProposal2.getProposal().getProposalNumber());
                sb.append(" %5C2");
                Collection<ProposalAdminDetails> proposalAdminDetails = businessObjectService.findMatching(ProposalAdminDetails.class, getFieldValues("instProposalId", awardFundingProposal2.getProposalId()));
                sb.append(" %6A ");
                for(ProposalAdminDetails proposalAdminDetail: proposalAdminDetails){
                    proposalAdminDetail.refreshReferenceObject("developmentProposal");
                    sb.append(" %6C1 ");
                    sb.append("Development Proposal " + proposalAdminDetail.getDevelopmentProposal().getProposalNumber());
                    sb.append(" %6C2");
                }
                sb.append(" %6B ");
            }
            sb.append("%5B");
        }
        if(awardFundingProposals1.size()>0){
            sb.append("</h3>");
        }
        return sb.toString();
    }

    private Collection<AwardFundingProposal> getAwardFundingProposals(String moduleName, Long moduleIdentifier) {
        
        Collection<AwardFundingProposal> awardFundingProposals1 = null;
        
        if(StringUtils.equalsIgnoreCase("award", moduleName)){
            awardFundingProposals1 = businessObjectService.findMatching(AwardFundingProposal.class, getFieldValues("awardId", moduleIdentifier));    
        }else if(StringUtils.equalsIgnoreCase("IP", moduleName)){
            awardFundingProposals1 = businessObjectService.findMatching(AwardFundingProposal.class, getFieldValues("proposalId", moduleIdentifier));
        }else if(StringUtils.equalsIgnoreCase("DP", moduleName)){
            Collection<ProposalAdminDetails> proposalAdminDetails = businessObjectService.findMatching(ProposalAdminDetails.class, getFieldValues("devProposalNumber", moduleIdentifier));
            for(ProposalAdminDetails proposalAdminDetail: proposalAdminDetails){
                awardFundingProposals1 = businessObjectService.findMatching(AwardFundingProposal.class, getFieldValues("proposalId", proposalAdminDetail.getInstProposalId()));    
                break;
            }
        }
        return awardFundingProposals1;
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
