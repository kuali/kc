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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.service.AwardHierarchyUIService;
import org.kuali.kra.service.MedusaService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ObjectUtils;

public class MedusaServiceImpl implements MedusaService {
    
    BusinessObjectService businessObjectService;
    AwardHierarchyUIService awardHierarchyUIService;
    AwardAmountInfoService awardAmountInfoService;
    

    public String getMedusaByProposal(String moduleName, Long moduleIdentifier) {
        StringBuilder sb = new StringBuilder();
        
        Collection<InstitutionalProposal> proposals = getProposals(moduleName, moduleIdentifier);
        if (proposals != null) {
            if (proposals.size() > 0) {
                sb.append("<h3>");
                sb.append("P %2A ");
            }
            for (InstitutionalProposal proposal : proposals) {
                sb.append("Institutional Proposal ").append(proposal.getProposalNumber()).append("%3A");
                sb.append(" %31 ");
                    appendInstitutionalProposalDetails(sb, proposal);
                sb.append(" %32 ");
                sb.append("%5A");
                Collection<DevelopmentProposal> devProposals = getDevelopmentProposals(proposal);
                for (DevelopmentProposal devProposal : devProposals) {
                    sb.append("%5C1 ");
                    sb.append("Development Proposal  ").append(devProposal.getProposalNumber()).append("%3A");
                    sb.append(" %31 ");
                        appendDevelopmentProposalDetails(sb, devProposal);
                    sb.append(" %32 ");
                    sb.append(" %5C2");
                }   
                
                Collection<Award> awards = getAwards(proposal);
                for (Award award : awards) {
                    AwardAmountInfo awardAmountInfo = awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
                    sb.append("%5C1 ");
                    sb.append("Award ").append(award.getAwardNumber()).append("%3A");
                    sb.append(" %31 ");
                    appendAwardDetails(sb, award, awardAmountInfo);
                    sb.append(" %32 ");
                    sb.append(" %5C2");
                }   
                sb.append("%5B");
            }
            if (proposals.size() > 0) {
                sb.append("</h3>");
            }
        }
        
       
        return sb.toString();
    }

    public String getMedusaByAward(String moduleName, Long moduleIdentifier) {
        StringBuilder sb = new StringBuilder();
        
        Collection<Award> awards = getAwards(moduleName, moduleIdentifier);
        
        if (awards != null) {
            if (awards.size() > 0) {
                sb.append("<h3>");
                sb.append("A %2A ");
            }
            
            for(Award award : awards){
                AwardAmountInfo awardAmountInfo = awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
                sb.append("Award " + award.getAwardNumber() + "%3A" );
                sb.append(" %31 ");
                    appendAwardDetails(sb, award, awardAmountInfo);
                sb.append(" %32 ");
                sb.append("%5A");
                Collection<InstitutionalProposal> proposals = getProposals(award);
                for (InstitutionalProposal proposal : proposals) {
                    sb.append("%5C1 ");
                    sb.append("Institutional Proposal ").append(proposal.getProposalNumber()).append("%3A");
                    sb.append(" %31 ");
                        appendInstitutionalProposalDetails(sb, proposal);
                    sb.append(" %32 ");
                    sb.append(" %5C2");
                    Collection<DevelopmentProposal> devProposals = getDevelopmentProposals(proposal);
                    sb.append(" %6A ");
                    for (DevelopmentProposal devProposal : devProposals) {
                        sb.append(" %6C1 ");
                        sb.append("Development Proposal  ").append(devProposal.getProposalNumber()).append("%3A");
                        sb.append(" %31 ");
                            appendDevelopmentProposalDetails(sb, devProposal);
                        sb.append(" %32 ");
                        sb.append(" %6C2");
                    }
                    sb.append(" %6B ");
                }
                sb.append("%5B");
            }
            if (awards.size() > 0) {
                sb.append("</h3>");
            }
        }    
        return sb.toString();
    }
    
    private void appendDevelopmentProposalDetails(StringBuilder sb, DevelopmentProposal dp) {
        sb.append("Development Proposal ").append(dp.getProposalNumber()).append(":").append(dp.getProposalNumber()).append(":").append("status").append(":");
        sb.append(dp.getOwnedByUnitNumber()).append(" ; ").append(dp.getOwnedByUnit().getUnitName()).append(":").append(dp.getRequestedStartDateInitial());
        sb.append(":").append(dp.getRequestedEndDateInitial()).append(":").append(dp.getTitle()).append(":").append(dp.getProposalType().getDescription());
        sb.append(":").append(dp.getNsfCode()).append(":").append(dp.getSponsorCode()).append(dp.getPrimeSponsorCode()).append(":").append(dp.getSponsorProposalNumber());
        sb.append(":").append(dp.getActivityType().getDescription()).append(":").append(dp.getProgramAnnouncementTitle()).append(":");
        sb.append(dp.getNoticeOfOpportunityCode()).append(":").append(dp.getProgramAnnouncementNumber()).append(":").append("Narrative").append(":");
        sb.append(dp.getBudgetStatus()).append(":");
        StringBuilder investigators = new StringBuilder();
        StringBuilder units = new StringBuilder();
        for(ProposalPerson proposalPerson :dp.getInvestigators()){
            investigators.append(proposalPerson.getFullName());
            for(ProposalPersonUnit unit: proposalPerson.getUnits()){
                units.append(unit.getUnit().getUnitName());
                units.append(" ; ");
            }
            investigators.append(" ; ");
        }
        sb.append(investigators).append(":").append(units);
    }    

    private void appendInstitutionalProposalDetails(StringBuilder sb, InstitutionalProposal proposal) {
        proposal.refreshReferenceObject("primeSponsor");
        sb.append("Institutinoal Proposal ").append(proposal.getProposalNumber()).append(":").append(proposal.getProposalId()).append(":");
        sb.append(proposal.getProposalNumber()).append(":");
        sb.append(proposal.getTitle()).append(":").append(proposal.getStatusCode()).append(":").append(proposal.getProposalTypeCode()).append(":");
        sb.append(proposal.getSponsorProposalNumber()).append(":").append("a/c no").append(":").append(proposal.getActivityTypeCode()).append(":");
        sb.append(proposal.getNsfCode()).append(":").append(proposal.getNoticeOfOpportunityCode()).append(":").append(proposal.getSponsorCode());
        
        String primeSponsorCode = " ";
        String primeSponsorName = " ";
        if(proposal.getPrimeSponsorCode()!=null){
            primeSponsorCode = proposal.getPrimeSponsor().getSponsorCode();
            primeSponsorName = proposal.getPrimeSponsor().getSponsorName();
        }
        
        sb.append(" ").append(proposal.getSponsorName()).append(":").append(primeSponsorCode).append(" ");
        sb.append(primeSponsorName).append(":").append(proposal.getRequestedStartDateInitial()).append(":");
        sb.append(proposal.getRequestedStartDateTotal()).append(":").append(proposal.getRequestedEndDateInitial()).append(":").append(proposal.getRequestedEndDateTotal());
        sb.append(":").append(proposal.getTotalDirectCostInitial()).append(":").append(proposal.getTotalDirectCostTotal()).append(":").append(proposal.getTotalIndirectCostInitial());
        sb.append(":").append(proposal.getTotalIndirectCostTotal()).append(":").append(proposal.getTotalInitialCost()).append(":");
        sb.append(proposal.getTotalCost()).append(":");
        sb.append(ObjectUtils.isNull(proposal.getPrincipalInvestigator()) ? " " : proposal.getPrincipalInvestigator().getFullName()).append(":");
        sb.append(proposal.getLeadUnit().getUnitNumber()).append(" ; ").append(proposal.getLeadUnit().getUnitName()).append(":");
    }

    /**
     * This method...
     * @param sb
     * @param award
     * @param awardAmountInfo
     */
    private void appendAwardDetails(StringBuilder sb, Award award, AwardAmountInfo awardAmountInfo) {
        sb.append("Award ").append(award.getAwardNumber()).append(":").append(award.getAwardId()).append(":");
        sb.append(award.getAwardId()).append(":").append(award.getAwardTypeCode()).append(":");
        sb.append(award.getSponsorAwardNumber()).append(":").append(award.getActivityType().getDescription()).append(":");
        sb.append(award.getAwardStatus().getDescription()).append(":").append(award.getTitle()).append(":");
        sb.append(award.getSponsorCode()).append(" - ").append(award.getSponsorName()).append(":").append(award.getBeginDate()).append(":");
        sb.append(awardAmountInfo.getCurrentFundEffectiveDate()).append(":").append(awardAmountInfo.getFinalExpirationDate()).append(":");
        sb.append(awardAmountInfo.getObligationExpirationDate()).append(":").append(awardAmountInfo.getAnticipatedTotalAmount()).append(":");
        sb.append(awardAmountInfo.getAmountObligatedToDate()).append(":").append(award.getAwardApprovedSubawards().size()==0?"N":"Y").append(":").append(award.getPaymentScheduleItems().size()==0?"N":"Y");
        sb.append(":").append(award.getApprovedEquipmentItems().size()==0?"N":"Y").append(":").append(award.getAwardTransferringSponsors().size()==0?"N":"Y").append(":").append(award.getApprovedForeignTravelTrips().size()==0?"N":"Y");
        sb.append(":").append(award.getCostSharingIndicator()).append(":").append(award.getAwardFandaRate().size()==0?"N":"Y").append(":");
        
        StringBuilder units = new StringBuilder();
        for(AwardPerson person: award.getProjectPersons()){
            sb.append(person.getFullName());
            if(person.isPrincipalInvestigator()){
                sb.append(" (Principal Investigator)");
            }
            sb.append(":");
            units = new StringBuilder();
            for(AwardPersonUnit unit: person.getUnits()){
                units.append(unit.getUnit().getUnitName());
                if(unit.isLeadUnit()){
                    units.append(" (Lead Unit)");    
                }
                units.append(" ; ");
            }
            sb.append(units);
            sb.append(":");
        }
        
    }
    
    private Collection<InstitutionalProposal> getProposals(String moduleName, Long moduleId) {
        Collection<InstitutionalProposal> proposals = null;
        if (StringUtils.equalsIgnoreCase("award", moduleName)) {
            proposals = getProposals((Award)businessObjectService.findByPrimaryKey(Award.class, getFieldValues("awardId", moduleId)));
        } else if (StringUtils.equalsIgnoreCase("IP", moduleName)) {
            proposals = businessObjectService.findMatching(InstitutionalProposal.class, getFieldValues("proposalId", moduleId));
        }else if(StringUtils.equalsIgnoreCase("DP", moduleName)){
            proposals = getProposals((DevelopmentProposal)businessObjectService.findByPrimaryKey(DevelopmentProposal.class, getFieldValues("proposalNumber", moduleId)));
        }
        
        return proposals;
    }
    
    private Collection<Award> getAwards(String moduleName, Long moduleId) {
        Collection<Award> awards = null;
        if (StringUtils.equalsIgnoreCase("award", moduleName)) {
            awards = businessObjectService.findMatching(Award.class, getFieldValues("awardId", moduleId));
        } else if (StringUtils.equalsIgnoreCase("IP", moduleName)) {
            awards = getAwards((InstitutionalProposal)businessObjectService.findByPrimaryKey(InstitutionalProposal.class, getFieldValues("proposalId", moduleId)));
        }else if(StringUtils.equalsIgnoreCase("DP", moduleName)){
            awards = getAwards((DevelopmentProposal)businessObjectService.findByPrimaryKey(DevelopmentProposal.class, getFieldValues("proposalNumber", moduleId)));
        }
        
        return awards;
    }
    
    private Collection<DevelopmentProposal> getDevelopmentProposals(InstitutionalProposal ip) {
        Collection<ProposalAdminDetails> proposalAdminDetails = businessObjectService.findMatching(ProposalAdminDetails.class, getFieldValues("instProposalId", ip.getProposalId()));
        Collection<DevelopmentProposal> devProposals = new ArrayList<DevelopmentProposal>();
        for (ProposalAdminDetails proposalAdminDetail : proposalAdminDetails) {
            proposalAdminDetail.refreshReferenceObject("developmentProposal");
            devProposals.add(proposalAdminDetail.getDevelopmentProposal());
        }
        return devProposals;
    }
    
    private Collection<Award> getAwards(InstitutionalProposal ip) {
        Collection<AwardFundingProposal> awardFundingProposals = businessObjectService.findMatching(AwardFundingProposal.class, getFieldValues("proposalId", ip.getProposalId()));
        Collection<Award> awards = new ArrayList<Award>();
        for (AwardFundingProposal awardFunding : awardFundingProposals) {
            awardFunding.refreshReferenceObject("award");
            awards.add(awardFunding.getAward());
        }
        return awards;
    }
    
    private Collection<Award> getAwards(DevelopmentProposal devProposal) {
        //must have an inst proposal to have any awards
        Collection<Award> awards = new ArrayList<Award>();
        Collection <InstitutionalProposal> proposals = getProposals(devProposal);
        if (proposals != null && !proposals.isEmpty()) {
            for (InstitutionalProposal proposal : proposals) {
                awards.addAll(getAwards(proposal));
            }
        }
        return awards;
    }
    
    private Collection<InstitutionalProposal> getProposals(Award award) {
        Collection<AwardFundingProposal> awardFundingProposals = businessObjectService.findMatching(AwardFundingProposal.class, getFieldValues("awardId", award.getAwardId()));
        Collection<InstitutionalProposal> ips = new ArrayList<InstitutionalProposal>();
        for (AwardFundingProposal awardFunding : awardFundingProposals) {
            awardFunding.refreshReferenceObject("proposal");
            ips.add(awardFunding.getProposal());
        }
        return ips;    
    }
    
    private Collection<InstitutionalProposal> getProposals(DevelopmentProposal devProposal) {
        Collection<ProposalAdminDetails> proposalAdminDetails = businessObjectService.findMatching(ProposalAdminDetails.class, getFieldValues("devProposalNumber", devProposal.getProposalNumber()));
        Collection<InstitutionalProposal> instProposals = new ArrayList<InstitutionalProposal>();
        for (ProposalAdminDetails proposalAdminDetail : proposalAdminDetails) {
            proposalAdminDetail.refreshReferenceObject("institutionalProposal");
            instProposals.add(proposalAdminDetail.getInstitutionalProposal());
        }
        return instProposals;        
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

    /**
     * Gets the awardAmountInfoService attribute. 
     * @return Returns the awardAmountInfoService.
     */
    public AwardAmountInfoService getAwardAmountInfoService() {
        return awardAmountInfoService;
    }

    /**
     * Sets the awardAmountInfoService attribute value.
     * @param awardAmountInfoService The awardAmountInfoService to set.
     */
    public void setAwardAmountInfoService(AwardAmountInfoService awardAmountInfoService) {
        this.awardAmountInfoService = awardAmountInfoService;
    }

}
