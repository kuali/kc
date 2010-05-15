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
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.medusa.MedusaNode;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.service.AwardHierarchyUIService;
import org.kuali.kra.service.VersionHistoryService;
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
    
    /**
     * 
     * Generates and returns a collection of all awards linked to the 
     * institutional proposal
     * @param ip
     * @return
     */
    private Collection<Award> getAwards(InstitutionalProposal ip) {
        Collection<Award> awards = new ArrayList<Award>();
        Collection<InstitutionalProposal> institutionalProposalVersions = businessObjectService.findMatching(InstitutionalProposal.class, getFieldValues("proposalNumber", ip.getProposalNumber()));
        for (InstitutionalProposal curIp : institutionalProposalVersions) {
            Collection<AwardFundingProposal> awardFundingProposals = curIp.getAwardFundingProposals();
            for (AwardFundingProposal awardFunding : awardFundingProposals) {
                if (awardFunding.isActive()) {
                    awardFunding.refreshReferenceObject("award");
                    addOnlyNewestAwardVersion(awards, awardFunding.getAward());
                }
            }
        }
        InstitutionalProposal activeProposal = getNewestActiveProposal(ip.getProposalNumber());
        if (activeProposal != null && StringUtils.isNotBlank(activeProposal.getCurrentAwardNumber()) && activeProposal.getStatusCode() != INST_PROPOSAL_STATUS_FUNDED) {
            Collection<Award> proposalCurrentAwards = businessObjectService.findMatching(Award.class, getFieldValues("awardNumber", activeProposal.getCurrentAwardNumber()));
            for (Award curAward : proposalCurrentAwards) {
                addOnlyNewestAwardVersion(awards, curAward);
            }
        }
        return awards;
    }
    
    /**
     * 
     * Gets and returns the newest active proposal version for the proposal number.
     * @param proposalNumber
     * @return
     */
    private InstitutionalProposal getNewestActiveProposal(String proposalNumber) {
        Collection<InstitutionalProposal> versions = getBusinessObjectService().findMatching(InstitutionalProposal.class, getFieldValues("proposalNumber", proposalNumber));
        InstitutionalProposal newestProposal = null;
        for (InstitutionalProposal curProposal : versions) {
            if (newestProposal == null && curProposal.isActiveVersion()) {
                newestProposal = curProposal;
            } else if (curProposal.isActiveVersion() && curProposal.getSequenceNumber() > newestProposal.getSequenceNumber()) {
                newestProposal = curProposal;
            }
        }
        return newestProposal;
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
     * Get all versions of the new award and add the newest verison of the award
     * @param currentList
     * @param newItem
     */
    private void addOnlyNewestAwardVersion(Collection<Award> currentList, Award newItem) {
        Collection<Award> awardVersions = businessObjectService.findMatching(Award.class, getFieldValues("awardNumber", newItem.getAwardNumber()));
        Award newestAward = null;
        //loop through and grab only the newest award
        for (Award curAward : awardVersions) {
            if (newestAward == null) {
                newestAward = curAward;
            } else if (curAward.getSequenceNumber() > newestAward.getSequenceNumber()) {
                newestAward = curAward;
            }
        }
        //now make sure we don't have another award version in the list already
        if (newestAward != null) {
            Award awardToBeRemoved = null;
            for (Award curAward : currentList) {
                if (curAward.getAwardNumber().equals(newestAward.getAwardNumber())) {
                    awardToBeRemoved = curAward;
                }
            }
            if (awardToBeRemoved != null) {
                currentList.remove(awardToBeRemoved);
            }
            currentList.add(newestAward);
        }
    }
    
    private Collection<InstitutionalProposal> getProposals(Award award) {
        Collection<InstitutionalProposal> ips = new ArrayList<InstitutionalProposal>();
        Collection<Award> awardVersions = businessObjectService.findMatching(Award.class, getFieldValues("awardNumber", award.getAwardNumber()));
        for (Award curAward : awardVersions) {
            Collection<AwardFundingProposal> awardFundingProposals = curAward.getFundingProposals();
            for (AwardFundingProposal awardFunding : awardFundingProposals) {
                if (awardFunding.isActive()) {
                    awardFunding.refreshReferenceObject("proposal");
                    addOnlyNewerIpVersion(ips, awardFunding.getProposal());
                }
            }
        }
        
        Collection <InstitutionalProposal> curAwardIps = businessObjectService.findMatching(InstitutionalProposal.class, getFieldValues("currentAwardNumber", award.getAwardNumber()));
        for (InstitutionalProposal proposal : curAwardIps) {
            if (proposal.getStatusCode() != INST_PROPOSAL_STATUS_FUNDED && proposal.isActiveVersion()) {
                addOnlyNewerIpVersion(ips, proposal);
            }
        }
        return ips;
    }
        
    /**
     * Only add the newest institutional proposal(based on sequence number) 
     * to the list and remove older versions if they exist 
     * @param currentList
     * @param newItem
     */
    private void addOnlyNewerIpVersion(Collection<InstitutionalProposal> currentList, InstitutionalProposal newItem) {
        Collection<InstitutionalProposal> ipVersions = businessObjectService.findMatching(InstitutionalProposal.class, getFieldValues("proposalNumber", newItem.getProposalNumber()));
        InstitutionalProposal newestIP = null;
        for (InstitutionalProposal curIP : ipVersions) {
            if (newestIP == null) {
                newestIP = curIP;
            } else if (curIP.getSequenceNumber() > newestIP.getSequenceNumber()) {
                newestIP = curIP;
            }
        }
        if (newestIP != null) {
            InstitutionalProposal proposalToRemove = null;
            for (InstitutionalProposal curIP : currentList) {
                if (curIP.getProposalNumber().equals(newestIP.getProposalNumber())) {
                    proposalToRemove = curIP;
                }
            }
            if (proposalToRemove != null) {
                currentList.remove(proposalToRemove);
            }
            currentList.add(newestIP);
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
