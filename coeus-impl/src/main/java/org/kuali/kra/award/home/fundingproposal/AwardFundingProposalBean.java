/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.home.fundingproposal;

import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.sys.api.model.AbstractDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.institutionalproposal.InstitutionalProposalConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.Serializable;
import java.util.*;

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
    
    private transient DataObjectService dataObjectService;
    private transient BusinessObjectService businessObjectService;
    private transient AwardService awardService;
    private transient InstitutionalProposalService institutionalProposalService;
    private transient PermissionService permissionService;
    
    public AwardFundingProposalBean(AwardForm awardForm) {
        this.awardForm = awardForm;
        createNewFundingProposal();
    }

    public AwardFundingProposalBean() {
        createNewFundingProposal();
    }
    
    /**
     * This method adds a Funding Proposal
     */
    public void addFundingProposal() {
       
        if (getNewFundingProposal() != null) {
            validateAndPerformFeed(awardForm.getFundingProposalBean().getAllAwardsForAwardNumber(awardForm.getAwardDocument().getAward()), getAward());
            // regardless of whether proposal is valid or not, reset back to step 1
            createNewFundingProposal();
        }
    }

    public void validateAndPerformFeed(List<Award> awards, Award awardToAdd) {
        if (validateForAdd(awards)) {
            awardToAdd.add(newFundingProposal);
            performDataFeeds(awardToAdd, newFundingProposal);
        }
    }

    /**
     * This method deletes a Funding proposal for the specified index
     */
    public void deleteAwardFundingProposal(int index) {
        if(CollectionUtils.validIndexForList(index, getAward().getAllFundingProposalsSortedBySequence())) {
            getAward().removeFundingProposal(index);
        }
    }

    /**
     * This method returns all Award versions for the awardNumber of the Award associated found from the AwardDocument on the associated AwardForm.
     */
    public List<Award> getAllAwardsForAwardNumber(Award award) {
        if(allAwardsForAwardNumber == null) {
            allAwardsForAwardNumber = getAwardService().findAwardsForAwardNumber(award.getAwardNumber());
            if(award.isPersisted()) {
                replaceThisAwardInListOfFoundAwards(award);
            } else {
                addUnsavedAwardToListOfAwards(award);
            }
        }
        return allAwardsForAwardNumber;
    }
    
    /**
     * This method calculates the total cost of all funding proposals
     */
    public ScaleTwoDecimal getTotalCostOfFundingProposals() {
        return getAward().getAllFundingProposals().stream()
        		.filter(AwardFundingProposal::isActive).map(afp -> afp.getProposal().getTotalCost())
        		.reduce(ScaleTwoDecimal.ZERO, AbstractDecimal::add);
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
     */
    public int getAllAwardsForAwardNumberSize() {
        return getAllAwardsForAwardNumber(awardForm.getAwardDocument().getAward()).size();
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
    
    private void createNewFundingProposal() {
        newFundingProposal = new InstitutionalProposal();
        newFundingProposal.setProposalNumber(null);
        mergeTypeCode = FundingProposalMergeType.NOCHANGE.getKey();
    }
    

    Award getAward() {
        return awardForm.getAwardDocument().getAward();
    }


    private void lazilyLoadFundingProposal() {
        Long proposalId = newFundingProposal.getProposalId();
        String proposalNumber = newFundingProposal.getProposalNumber();
        InstitutionalProposal foundProposal = null;
        if (proposalId != null) {
            foundProposal = findProposalById(proposalId);
        } else if (proposalNumber != null) {
            foundProposal = findProposalByProposalNumber(proposalNumber);
        }
        if (foundProposal != null) {
            newFundingProposal = foundProposal; 
        }
    }

    private InstitutionalProposal findProposalById(Long proposalId) {
        Map<String, Object> identifiers = new HashMap<>();
        identifiers.put("proposalId", proposalId);
        return getBusinessObjectService().findByPrimaryKey(InstitutionalProposal.class, identifiers);
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

    private boolean validateForAdd(List<Award> awards) {
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
        if(proposalAlreadyAdded(awards)) {
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
        Collection<DevelopmentProposal> devProposals = new ArrayList<>();
        List<ProposalAdminDetails> proposalAdminDetails = (List<ProposalAdminDetails>) getBusinessObjectService().findMatchingOrderBy(ProposalAdminDetails.class, 
                                                                getFieldValues("instProposalId", instProposal.getProposalId()), "devProposalNumber", true);
        if(proposalAdminDetails.size() > 0) {
            String latestDevelopmentProposalDocNumber = proposalAdminDetails.get(proposalAdminDetails.size() - 1).getDevProposalNumber();
            DevelopmentProposal devProp = getDataObjectService().find(DevelopmentProposal.class, latestDevelopmentProposalDocNumber);
            devProposals.add(devProp);
        }
        return devProposals;
    }
    
    protected Map<String, Object> getFieldValues(String key, Object value){
        Map<String, Object> fieldValues = new HashMap<>();
        fieldValues.put(key, value);
        return fieldValues;
    }
    
    
    private boolean validProposalStatus() {
        int proposalStatusCode = newFundingProposal.getProposalStatus().getProposalStatusCode();
        Collection<String> validCodes = getInstitutionalProposalService().getValidFundingProposalStatusCodes();
        for (String validCode : validCodes) {
            int code = Integer.parseInt(validCode);
            if(proposalStatusCode == code) {
                return true;
            }
        }
        
        return false;
    }

    private boolean proposalAlreadyAdded(List<Award> awardVersions) {
        Long proposalId = newFundingProposal.getProposalId();
        
        for (Award currentAward : awardVersions) {
            List<AwardFundingProposal> fundingProposals= currentAward.getFundingProposals();

            for (AwardFundingProposal currentFundingProposal : fundingProposals) {
                Long id = currentFundingProposal.getProposalId();

                if (id.equals(proposalId)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void initializeAwardCustomDataIfNecessary(Award award) {
        if (award.getAwardCustomDataList().isEmpty() && awardForm != null) {
            Map<String, CustomAttributeDocument> customAttributeDocuments = awardForm.getCustomDataHelper().getCustomAttributeDocuments();
            for (Map.Entry<String, CustomAttributeDocument> entry : customAttributeDocuments.entrySet()) {
                CustomAttributeDocument customAttributeDocument = entry.getValue();
                AwardCustomData awardCustomData = new AwardCustomData();
                awardCustomData.setCustomAttributeId(customAttributeDocument.getId());
                awardCustomData.setCustomAttribute(customAttributeDocument.getCustomAttribute());
                awardCustomData.setValue("");
                awardCustomData.setAward(award);
                award.getAwardCustomDataList().add(awardCustomData);
            }
        }
    }

    private boolean userCanCreateProposal() {
        return getPermissionService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(),
                InstitutionalProposalConstants.INSTITUTIONAL_PROPOSAL_NAMESPACE,
                PermissionConstants.CREATE_INSTITUTIONAL_PROPOSAL);
    }
    
    private boolean userCanSubmitProposal() {
        return getPermissionService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), 
                InstitutionalProposalConstants.INSTITUTIONAL_PROPOSAL_NAMESPACE, 
                PermissionConstants.SUBMIT_INSTITUTIONAL_PROPOSAL);
    }

	protected DataObjectService getDataObjectService() {
		if (dataObjectService == null) {
			dataObjectService = KcServiceLocator.getService(DataObjectService.class);
		}
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }

        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public AwardService getAwardService() {
        if (awardService == null) {
            awardService = KcServiceLocator.getService(AwardService.class);
        }

        return awardService;
    }

    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }

    public InstitutionalProposalService getInstitutionalProposalService() {
        if (institutionalProposalService == null) {
            institutionalProposalService = KcServiceLocator.getService(InstitutionalProposalService.class);
        }

        return institutionalProposalService;
    }

    public void setInstitutionalProposalService(InstitutionalProposalService institutionalProposalService) {
        this.institutionalProposalService = institutionalProposalService;
    }

    public PermissionService getPermissionService() {
        if (permissionService == null) {
            permissionService = KcServiceLocator.getService(PermissionService.class);
        }

        return permissionService;
    }

    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
}
