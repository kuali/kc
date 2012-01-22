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
package org.kuali.kra.award.home.fundingproposal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.institutionalproposal.InstitutionalProposalConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

public class AwardFundingProposalBean implements Serializable {
    
    private static final long serialVersionUID = 7278945841002454778L;
    
    private static final String FUNDING_PROPOSAL_ERROR_KEY = "fundingProposalBean.newFundingProposal";
    private static final String FUNDING_PROPOSAL_NOT_FOUND_ERROR_KEY = "error.fundingproposal.not.found";
    private static final String PENDING_FUNDING_PROPOSAL_VERSION_EXISTS = "error.fundingproposal.pendingVersion";
    private static final String FUNDING_PROPOSAL_INADEQUATE_PERMISSIONS = "error.fundingproposal.noPermission";
    private static final String FUNDING_PROPOSAL_ALREADY_ADDED= "error.fundingProposal.alreadyAdded";
    private static final String FUNDING_PROPOSAL_INVALID_STATUS= "error.fundingProposal.invalidStatus";
    private static final String FUNDING_PROPOSAL_INVALID_DEVELOPMENTPROPOSAL_STATUS = "error.fundingProposal.developmentProposal.approvalPendingSubmitted";
    private AwardForm awardForm;
    private InstitutionalProposal newFundingProposal;
    private String mergeTypeCode;

    
    private List<Award> allAwardsForAwardNumber;
    
    public AwardFundingProposalBean(AwardForm awardForm) {
        this.awardForm = awardForm;
        createNewFundingProposal();
    }

    AwardFundingProposalBean() {
        
    }
    
    /**
     * This method adds a Funding Proposal
     */
    public void addFundingProposal() {
        
        if (getNewFundingProposal() != null) {
            if (validateForAdd()) {
                getAward().add(newFundingProposal);                
                performDataFeeds(getAward(), newFundingProposal);
            }
            // regardless of whether proposal is valid or not, reset back to step 1
            createNewFundingProposal();
        }
    }

    /**
     * This method deletes a Funding proposal for the specified index
     * @param index
     */
    public void deleteAwardFundingProposal(int index) {
        if(index >= 0) {
            getAward().removeFundingProposal(index);
        }
    }

    /**
     * This method returns all Award versions for the awardNumber of the Award associated found from the AwardDocument on the associated AwardForm.
     * @return
     */
    public List<Award> getAllAwardsForAwardNumber() {
        if(allAwardsForAwardNumber == null) {
            Award thisAward = getAward();
            allAwardsForAwardNumber = getAwardService().findAwardsForAwardNumber(thisAward.getAwardNumber());
            if(thisAward.isPersisted()) {
                replaceThisAwardInListOfFoundAwards(thisAward);
            } else {
                addUnsavedAwardToListOfAwards(thisAward);
            }
        }
        return allAwardsForAwardNumber;
    }

    private void replaceThisAwardInListOfFoundAwards(Award thisAward) {
        int lastIndex = allAwardsForAwardNumber.size() - 1; 
        if(lastIndex >= 0 && allAwardsForAwardNumber.get(lastIndex).getAwardId().equals(thisAward.getAwardId())) {
            allAwardsForAwardNumber.set(lastIndex, thisAward);
        }
    }

    public void setAllAwardsForAwardNumber(List<Award> allAwardsForAwardNumber) {
        this.allAwardsForAwardNumber = allAwardsForAwardNumber;
    }

    private void addUnsavedAwardToListOfAwards(Award thisAward) {
        if(thisAward.getAwardId() == null) {
            allAwardsForAwardNumber.add(thisAward);
        }
    }
    
    /**
     * This method returns the size of the allAwardsForAwardNumber collection 
     * @return
     */
    public int getAllAwardsForAwardNumberSize() {
        return getAllAwardsForAwardNumber().size();
    }

    /**
     * Gets the newFundingProposal attribute. 
     * @return Returns the newFundingProposal.
     */
    public InstitutionalProposal getNewFundingProposal() {
        lazilyLoadFundingProposal();
        return newFundingProposal;
    }

    /**
     * Sets the newFundingProposal attribute value.
     * @param newFundingProposal The newFundingProposal to set.
     */
    public void setNewFundingProposal(InstitutionalProposal newFundingProposal) {
        this.newFundingProposal = newFundingProposal;
    }
    
    public String getMergeTypeCode() {
        return mergeTypeCode;
    }

    public void setMergeTypeCode(String mergeTypeCode) {
        this.mergeTypeCode = mergeTypeCode;
    }

    public FundingProposalMergeType getMergeType() {
        return FundingProposalMergeType.getFundingProposalMergeType(getMergeTypeCode());
    }

    public void setMergeType(FundingProposalMergeType mergeType) {
        mergeTypeCode = mergeType.getKey();
    }    

    /**
     * @return
     */
    BusinessObjectService getBusinessObjectService() {
        return (BusinessObjectService) KraServiceLocator.getService(BusinessObjectService.class);
    }
    
    AwardService getAwardService() {
        return (AwardService) KraServiceLocator.getService(AwardService.class);
    }
    
    private void createNewFundingProposal() {
        newFundingProposal = new InstitutionalProposal();
        newFundingProposal.setProposalNumber(null);
        mergeTypeCode = FundingProposalMergeType.NOCHANGE.getKey();
    }
    
    /**
     * @return
     */
    Award getAward() {
        return awardForm.getAwardDocument().getAward();
    }
    
    private void lazilyLoadFundingProposal() {
        Long proposalId = newFundingProposal.getProposalId();
        String proposalNumber = newFundingProposal.getProposalNumber();
        InstitutionalProposal foundProposal = null;
        if (proposalId != null && proposalNumber == null) {
            foundProposal = findProposalById(proposalId);
        } else if (proposalNumber != null && proposalId == null) {
            foundProposal = findProposalByProposalNumber(proposalNumber);
        }
        if (foundProposal != null) {
            newFundingProposal = foundProposal; 
        }
    }

    private InstitutionalProposal findProposalById(Long proposalId) {
        Map<String, Object> identifiers = new HashMap<String, Object>();
        identifiers.put("proposalId", proposalId);
        return (InstitutionalProposal) getBusinessObjectService().findByPrimaryKey(InstitutionalProposal.class, identifiers);
    }
    
    private InstitutionalProposal findProposalByProposalNumber(String proposalNumber) {
        return getInstitutionalProposalService().getActiveInstitutionalProposalVersion(proposalNumber);
    }

    protected void performDataFeeds(Award award, InstitutionalProposal proposal) {
        FundingProposalMergeType mergeType = getMergeType();
        new BaseFieldsDataFeedCommand(award, proposal, mergeType).performDataFeed();
        new SponsorDataFeedCommand(award, proposal, mergeType).performDataFeed();
        new CommentsDataFeedCommand(award, proposal, mergeType).performDataFeed();
        new SpecialReviewDataFeedCommand(award, proposal, mergeType).performDataFeed();
        proposal.refreshReferenceObject("institutionalProposalCostShares");
        new CostSharingDataFeedCommand(award, proposal, mergeType).performDataFeed();
        proposal.refreshReferenceObject("institutionalProposalUnrecoveredFandAs");
        new FandARatesDataFeedCommand(award, proposal, mergeType).performDataFeed();
        new KeywordsDataFeedCommand(award, proposal, mergeType).performDataFeed();
        new LeadUnitDataFeedCommand(award, proposal, mergeType).performDataFeed();
        initializeAwardCustomDataIfNecessary(award);
        new CustomDataDataFeedCommand(award, proposal, mergeType).performDataFeed();
        new ProjectPersonnelDataFeedCommand(award, proposal, mergeType).performDataFeed();
    }

    private boolean validateForAdd() {
        boolean valid =  newFundingProposal.getProposalId() != null;
        if (!valid) {
            String msgArg = newFundingProposal.getProposalNumber();
            if (msgArg == null) {
                msgArg = "(empty)";
            }
            GlobalVariables.getMessageMap().putError(FUNDING_PROPOSAL_ERROR_KEY, FUNDING_PROPOSAL_NOT_FOUND_ERROR_KEY, msgArg);
        }
        InstitutionalProposal pendingVersion = 
            getInstitutionalProposalService().getPendingInstitutionalProposalVersion(newFundingProposal.getProposalNumber());
        if (pendingVersion != null) {
            valid = false;
            GlobalVariables.getMessageMap().putError(FUNDING_PROPOSAL_ERROR_KEY, 
                    PENDING_FUNDING_PROPOSAL_VERSION_EXISTS, 
                    newFundingProposal.getProposalNumber(),
                    pendingVersion.getInstitutionalProposalDocument().getDocumentNumber(),
                    pendingVersion.getUpdateUser());
        }
        if (!userCanCreateProposal()) {
            valid = false;
            GlobalVariables.getMessageMap().putError(FUNDING_PROPOSAL_ERROR_KEY, 
                    FUNDING_PROPOSAL_INADEQUATE_PERMISSIONS, 
                    GlobalVariables.getUserSession().getPrincipalName(),
                    PermissionConstants.CREATE_INSTITUTIONAL_PROPOSAL);
        }
        if (!userCanSubmitProposal()) {
            valid = false;
            GlobalVariables.getMessageMap().putError(FUNDING_PROPOSAL_ERROR_KEY, 
                    FUNDING_PROPOSAL_INADEQUATE_PERMISSIONS, 
                    GlobalVariables.getUserSession().getPrincipalName(),
                    PermissionConstants.SUBMIT_INSTITUTIONAL_PROPOSAL);
        }
        if(proposalAlreadyAdded()) {
            valid=false;
            GlobalVariables.getMessageMap().putError(FUNDING_PROPOSAL_ERROR_KEY, 
                        FUNDING_PROPOSAL_ALREADY_ADDED);
            
        }
        if(!validProposalStatus()) {
            valid=false;
            String proposalStatus = newFundingProposal.getProposalStatus().getDescription();
            GlobalVariables.getMessageMap().putError(FUNDING_PROPOSAL_ERROR_KEY, 
                    FUNDING_PROPOSAL_INVALID_STATUS, proposalStatus);
        }if(isInvalidAssociatedDevelopmentProposal()) {
            valid=false;
            GlobalVariables.getMessageMap().putError(FUNDING_PROPOSAL_ERROR_KEY, 
                    FUNDING_PROPOSAL_INVALID_DEVELOPMENTPROPOSAL_STATUS);
        }
        return valid;
    }
    
    private boolean isInvalidAssociatedDevelopmentProposal() {
        boolean isApprovePending = false;
        Collection<DevelopmentProposal> devProposals = getDevelopmentProposals(getNewFundingProposal());
        for (DevelopmentProposal developmentProposal : devProposals) {
            if ("5".equals(developmentProposal.getProposalStateTypeCode())) {
                isApprovePending = true;
                break;
            }
        }
        return isApprovePending;
    }
    
    /*
     * find any version of IP that has PD with approve pending
     */
    @SuppressWarnings("unchecked")
    protected Collection<DevelopmentProposal> getDevelopmentProposals(InstitutionalProposal instProposal) {
        //find any dev prop linked to any version of this inst prop
        Collection<DevelopmentProposal> devProposals = new ArrayList<DevelopmentProposal>();
        List<ProposalAdminDetails> proposalAdminDetails = (List<ProposalAdminDetails>) getBusinessObjectService().findMatchingOrderBy(ProposalAdminDetails.class, 
                                                                getFieldValues("instProposalId", instProposal.getProposalId()), "devProposalNumber", true);
        if(proposalAdminDetails.size() > 0) {
            String latestDevelopmentProposalDocNumber = proposalAdminDetails.get(proposalAdminDetails.size() - 1).getDevProposalNumber();
            DevelopmentProposal devProp = (DevelopmentProposal)getBusinessObjectService().findBySinglePrimaryKey(DevelopmentProposal.class, latestDevelopmentProposalDocNumber);
            devProposals.add(devProp);
        }
        return devProposals;
    }
    
    protected Map<String, Object> getFieldValues(String key, Object value){
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(key, value);
        return fieldValues;
    }
    
    
    private boolean validProposalStatus() {
        
        int proposalStatusCode = newFundingProposal.getProposalStatus().getProposalStatusCode();
        List<String> validCodes= new ArrayList<String>( KraServiceLocator.getService(ParameterService.class).getParameterValuesAsString(Constants.MODULE_NAMESPACE_INSTITUTIONAL_PROPOSAL, ParameterConstants.DOCUMENT_COMPONENT, "validFundingProposalStatusCodes") );
        ListIterator itr= validCodes.listIterator();
        while(itr.hasNext()) {
            Object currentCode= itr.next();
            String[] codes= ((String) currentCode).split(","); 
            for(int i=0; i < codes.length; i++) {
                int code= Integer.parseInt(codes[i]);
                if(proposalStatusCode == code) {
                    return true;
                }
            }
        }
        
        return false;
    }

    private boolean proposalAlreadyAdded() {
        String proposalNumber = newFundingProposal.getProposalNumber();
        Long proposalId = newFundingProposal.getProposalId();
        
        List<Award> awardVersions = awardForm.getFundingProposalBean().getAllAwardsForAwardNumber();
        for (Award currentAward : awardVersions) {
            List<AwardFundingProposal> fundingProposals= currentAward.getFundingProposals();
            ListIterator itr= fundingProposals.listIterator();
            
            while(itr.hasNext()) {
                AwardFundingProposal currentFundingProposal= (AwardFundingProposal) itr.next();
                Long id= currentFundingProposal.getProposalId();
                
                if(id.equals(proposalId)) {
                   return true;
                }
            }
        }
        return false;
    }

    private void initializeAwardCustomDataIfNecessary(Award award) {
        if (award.getAwardCustomDataList().isEmpty()) {
            Map<String, CustomAttributeDocument> customAttributeDocuments = awardForm.getAwardDocument().getCustomAttributeDocuments();
            for (Map.Entry<String, CustomAttributeDocument> entry : customAttributeDocuments.entrySet()) {
                CustomAttributeDocument customAttributeDocument = entry.getValue();
                AwardCustomData awardCustomData = new AwardCustomData();
                awardCustomData.setCustomAttributeId((long) customAttributeDocument.getCustomAttributeId());
                awardCustomData.setCustomAttribute(customAttributeDocument.getCustomAttribute());
                awardCustomData.setValue("");
                awardCustomData.setAward(award);
                award.getAwardCustomDataList().add(awardCustomData);
            }
        }
    }
    
    private boolean userCanCreateProposal() {
        Map<String,String> permissionDetails =new HashMap<String,String>();
        permissionDetails.put(PermissionConstants.DOCUMENT_TYPE_ATTRIBUTE_QUALIFIER, InstitutionalProposalConstants.INSTITUTIONAL_PROPOSAL_DOCUMENT_NAME);
        return getPermissionService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), 
                InstitutionalProposalConstants.INSTITUTIONAL_PROPOSAL_NAMESPACE, 
                PermissionConstants.CREATE_INSTITUTIONAL_PROPOSAL, 
                permissionDetails);
    }
    
    private boolean userCanSubmitProposal() {
        Map<String,String> permissionDetails =new HashMap<String,String>();
        permissionDetails.put(PermissionConstants.DOCUMENT_TYPE_ATTRIBUTE_QUALIFIER, InstitutionalProposalConstants.INSTITUTIONAL_PROPOSAL_DOCUMENT_NAME);
        return getPermissionService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), 
                InstitutionalProposalConstants.INSTITUTIONAL_PROPOSAL_NAMESPACE, 
                PermissionConstants.SUBMIT_INSTITUTIONAL_PROPOSAL, 
                permissionDetails);
    }
    
    protected InstitutionalProposalService getInstitutionalProposalService() {
        return KraServiceLocator.getService(InstitutionalProposalService.class);
    }
    
    protected IdentityService getIdentityService() {
        return KraServiceLocator.getService(IdentityService.class);
    }
    
    protected PermissionService getPermissionService() {
        return KraServiceLocator.getService(PermissionService.class);
    }    
}
