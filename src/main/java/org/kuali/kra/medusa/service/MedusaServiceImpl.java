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
package org.kuali.kra.medusa.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.kra.Sequenceable;
import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.bo.NoticeOfOpportunity;
import org.kuali.kra.bo.NsfCode;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.medusa.MedusaNode;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.service.AwardHierarchyUIService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ObjectUtils;

public class MedusaServiceImpl implements MedusaService {
    
    private static final int INST_PROPOSAL_STATUS_FUNDED = 2;
    
    BusinessObjectService businessObjectService;
    AwardHierarchyUIService awardHierarchyUIService;
    AwardAmountInfoService awardAmountInfoService;
    
    public MedusaNode getMedusaNode(String moduleName, Long moduleId) {
        MedusaNode curNode = new MedusaNode();
        curNode.setType(moduleName);
        if (StringUtils.equalsIgnoreCase("award", moduleName)) {
            Award award = (Award)businessObjectService.findByPrimaryKey(Award.class, getFieldValues("awardId", moduleId));
            curNode.setBo(award);
            curNode.setExtraInfo(awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos()));
        } else if (StringUtils.equalsIgnoreCase("IP", moduleName)) {
           InstitutionalProposal proposal = (InstitutionalProposal)businessObjectService.findByPrimaryKey(InstitutionalProposal.class, getFieldValues("proposalId", moduleId));
           proposal.setNsfCodeBo(getNsfCode(proposal.getNsfCode()));
           curNode.setBo(proposal);
        }else if(StringUtils.equalsIgnoreCase("DP", moduleName)){
           DevelopmentProposal devProp = (DevelopmentProposal) businessObjectService.findByPrimaryKey(DevelopmentProposal.class, getFieldValues("proposalNumber", moduleId));
           devProp.setNsfCodeBo(getNsfCode(devProp.getNsfCode()));
           curNode.setBo(devProp);
        }
        return curNode;
    }
    
    /**
     * 
     * Helper function as nsfCode has been wired up such that it needs
     * outside help getting the actual BO loaded.
     * @param nsfCode
     * @return
     */
    private NsfCode getNsfCode(String nsfCode) {
        Collection<NsfCode> bos = businessObjectService.findMatching(NsfCode.class, getFieldValues("nsfCode", nsfCode));
        for (NsfCode nsfBo : bos) {
            return nsfBo;
        }
        return null;
    }

    public List<MedusaNode> getMedusaByProposal(String moduleName, Long moduleIdentifier) {
        List<MedusaNode> medusaParentNodes = new ArrayList<MedusaNode>();
        
        Collection<InstitutionalProposal> proposals = getProposals(moduleName, moduleIdentifier);
        if (proposals != null) {
            for (InstitutionalProposal proposal : proposals) {
                proposal.refresh();
                MedusaNode curNode = new MedusaNode();
                curNode.setBo(proposal);
                curNode.setType("IP");
                medusaParentNodes.add(curNode);
                Collection<DevelopmentProposal> devProposals = getDevelopmentProposals(proposal);
                for (DevelopmentProposal devProposal : devProposals) {
                    MedusaNode childNode = new MedusaNode();
                    childNode.setBo(devProposal);
                    childNode.setType("DP");
                    curNode.getChildNodes().add(childNode);
                }
                
                Collection<Award> awards = getAwards(proposal);
                for (Award award : awards) {
                    AwardAmountInfo awardAmountInfo = awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
                    MedusaNode childNode = new MedusaNode();
                    childNode.setBo(award);
                    childNode.setType("award");
                    childNode.setExtraInfo(awardAmountInfo);
                    curNode.getChildNodes().add(childNode);                    
                }   
            }
        }
        
       
        return medusaParentNodes;
    }

    public List<MedusaNode> getMedusaByAward(String moduleName, Long moduleIdentifier) {
        List<MedusaNode> medusaParentNodes = new ArrayList<MedusaNode>();
        
        Collection<Award> awards = getAwards(moduleName, moduleIdentifier);
        if (awards != null) {
            for(Award award : awards){
                AwardAmountInfo awardAmountInfo = awardAmountInfoService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
                MedusaNode curNode = new MedusaNode();
                curNode.setBo(award);
                curNode.setType("award");
                curNode.setExtraInfo(awardAmountInfo);
                medusaParentNodes.add(curNode);
                Collection<InstitutionalProposal> proposals = getProposals(award);
                for (InstitutionalProposal proposal : proposals) {
                    MedusaNode childNode = new MedusaNode();
                    childNode.setBo(proposal);
                    childNode.setType("IP");
                    curNode.getChildNodes().add(childNode);
                    Collection<DevelopmentProposal> devProposals = getDevelopmentProposals(proposal);
                    for (DevelopmentProposal devProposal : devProposals) {
                        MedusaNode devNode = new MedusaNode();
                        devNode.setBo(devProposal);
                        devNode.setType("DP");
                        childNode.getChildNodes().add(devNode);                        

                    }
                }
            }
        }    
        return medusaParentNodes;
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
    
    private Collection<DevelopmentProposal> getDevelopmentProposals(InstitutionalProposal instProposal) {
        //find any dev prop linked to any version of this inst prop
        Collection<DevelopmentProposal> devProposals = new ArrayList<DevelopmentProposal>();
        Collection<InstitutionalProposal> proposalVersions = businessObjectService.findMatching(InstitutionalProposal.class, getFieldValues("proposalNumber", instProposal.getProposalNumber()));
        for (InstitutionalProposal ip : proposalVersions) {
            Collection<ProposalAdminDetails> proposalAdminDetails = businessObjectService.findMatching(ProposalAdminDetails.class, getFieldValues("instProposalId", ip.getProposalId()));
            for (ProposalAdminDetails proposalAdminDetail : proposalAdminDetails) {
                proposalAdminDetail.refreshReferenceObject("developmentProposal");
                devProposals.add(proposalAdminDetail.getDevelopmentProposal());
            }
        }
        return devProposals;
    }
    
    private Collection<Award> getAwards(InstitutionalProposal ip) {
        Collection<AwardFundingProposal> awardFundingProposals = businessObjectService.findMatching(AwardFundingProposal.class, getFieldValues("proposalId", ip.getProposalId()));
        Collection<Award> awards = new ArrayList<Award>();
        for (AwardFundingProposal awardFunding : awardFundingProposals) {
            awardFunding.refreshReferenceObject("award");
            addOnlyNewestAwardVersion(awards, awardFunding.getAward());
        }
        if (StringUtils.isNotBlank(ip.getCurrentAwardNumber()) && ip.getStatusCode() != INST_PROPOSAL_STATUS_FUNDED) {
            Collection<Award> proposalCurrentAwards = businessObjectService.findMatching(Award.class, getFieldValues("awardNumber", ip.getCurrentAwardNumber()));
            for (Award curAward : proposalCurrentAwards) {
                addOnlyNewestAwardVersion(awards, curAward);
            }
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
    
    /**
     * Only add the newest award(based on sequence number) to the list and remove older versions
     * if they exist 
     * @param currentList
     * @param newItem
     */
    private void addOnlyNewestAwardVersion(Collection<Award> currentList, Award newItem) {
        boolean dontAddThisVersion = false;
        Award removeOld = null;
        for (Award award : currentList) {
            if (StringUtils.equals(award.getAwardNumber(), newItem.getAwardNumber())) {
                if (award.getSequenceNumber() < newItem.getSequenceNumber()) {
                    removeOld = award;
                } else {
                    dontAddThisVersion = true;
                }
            }
        }
        if (removeOld != null) {
            currentList.remove(removeOld);
        }
        if (!dontAddThisVersion) {
            currentList.add(newItem);
        }
    }
    
    private Collection<InstitutionalProposal> getProposals(Award award) {
        Collection<AwardFundingProposal> awardFundingProposals = businessObjectService.findMatching(AwardFundingProposal.class, getFieldValues("awardId", award.getAwardId()));
        Collection<InstitutionalProposal> ips = new ArrayList<InstitutionalProposal>();
        for (AwardFundingProposal awardFunding : awardFundingProposals) {
            awardFunding.refreshReferenceObject("proposal");
            addOnlyNewerIpVersion(ips, awardFunding.getProposal());
        }
        Collection <InstitutionalProposal> curAwardIps = businessObjectService.findMatching(InstitutionalProposal.class, getFieldValues("currentAwardNumber", award.getAwardNumber()));
        for (InstitutionalProposal proposal : curAwardIps) {
            if (proposal.getStatusCode() != INST_PROPOSAL_STATUS_FUNDED) {
                addOnlyNewerIpVersion(ips, proposal);
            }
        }
        return ips;
    }
    
    /**
     * Only add the newest inst prop(based on sequence number) to the list and remove older versions
     * if they exist 
     * @param currentList
     * @param newItem
     */
    private void addOnlyNewerIpVersion(Collection<InstitutionalProposal> currentList, InstitutionalProposal newItem) {
        boolean dontAddThisVersion = false;
        InstitutionalProposal removeOld = null;
        for (InstitutionalProposal proposal : currentList) {
            if (StringUtils.equals(proposal.getProposalNumber(), newItem.getProposalNumber())) {
                if (proposal.getSequenceNumber() < newItem.getSequenceNumber()) {
                    removeOld = proposal;
                } else {
                    dontAddThisVersion = true;
                }
            }
        }
        if (removeOld != null) {
            currentList.remove(removeOld);
        }
        if (!dontAddThisVersion) {
            currentList.add(newItem);
        }
    }
    
    private Collection<InstitutionalProposal> getProposals(DevelopmentProposal devProposal) {
        Collection<ProposalAdminDetails> proposalAdminDetails = businessObjectService.findMatching(ProposalAdminDetails.class, getFieldValues("devProposalNumber", devProposal.getProposalNumber()));
        Collection<InstitutionalProposal> instProposals = new ArrayList<InstitutionalProposal>();
        for (ProposalAdminDetails proposalAdminDetail : proposalAdminDetails) {
            //find the newest version of the institutional proposal that is linked
            proposalAdminDetail.refreshReferenceObject("institutionalProposal");
            Collection<InstitutionalProposal> propVersions = businessObjectService.findMatching(InstitutionalProposal.class, getFieldValues("proposalNumber", proposalAdminDetail.getInstitutionalProposal().getProposalNumber()));
            InstitutionalProposal highestVersion = null;
            for (InstitutionalProposal curVersion : propVersions) {
                if (highestVersion == null) {
                    highestVersion = curVersion;
                } else if (curVersion.getSequenceNumber() > highestVersion.getSequenceNumber()){
                    highestVersion = curVersion;
                }
            }
            instProposals.add(highestVersion);
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
