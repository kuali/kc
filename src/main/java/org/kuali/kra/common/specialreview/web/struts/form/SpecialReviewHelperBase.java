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
package org.kuali.kra.common.specialreview.web.struts.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.bo.SpecialReviewType;
import org.kuali.kra.common.specialreview.bo.SpecialReview;
import org.kuali.kra.common.specialreview.bo.SpecialReviewExemption;
import org.kuali.kra.common.specialreview.service.SpecialReviewService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.actions.submit.ProtocolExemptStudiesCheckListItem;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Defines the base class of all Special Review Helpers.
 * @param <T> Special Review
 */
public abstract class SpecialReviewHelperBase<T extends SpecialReview<? extends SpecialReviewExemption>> implements Serializable {

    /**
     * Namespace code for Protocol linking parameters.
     */
    protected static final String NAMESPACE_CODE = "KC-PROTOCOL";
    
    /**
     * Parameter code for Protocol linking parameters.
     */
    protected static final String PARAMETER_CODE = "Document";
    
    private static final String AMENDMENT_KEY = "A";
    private static final String RENEWAL_KEY = "R";

    private static final long serialVersionUID = 4726816248612555502L;

    private T newSpecialReview;
    
    private List<String> linkedProtocolNumbers;

    private boolean canModifySpecialReview;
    private boolean isProtocolLinkingEnabled;
    
    private transient ParameterService parameterService;
    private transient ProtocolFinderDao protocolFinderDao;
    private transient SpecialReviewService specialReviewService;
    
    public T getNewSpecialReview() {
        return newSpecialReview;
    }
    
    public void setNewSpecialReview(T newSpecialReview) {
        this.newSpecialReview = newSpecialReview;
    }
    
    public List<String> getLinkedProtocolNumbers() {
        return linkedProtocolNumbers;
    }

    public void setLinkedProtocolNumbers(List<String> linkedProtocolNumbers) {
        this.linkedProtocolNumbers = linkedProtocolNumbers;
    }

    public boolean getCanModifySpecialReview() {
        return canModifySpecialReview;
    }
    
    public boolean getIsProtocolLinkingEnabled() {
        return isProtocolLinkingEnabled;
    }
    
    /**
     * Prepares the user view by initializing the permissions.
     */
    public void prepareView() {
        initializePermissions();
        initializeLinkedProtocolNumbers();
        initializeProtocolLinkView();
    }
    
    /**
     * Prepares the linked fields on the Special Review that are pulled directly from the Protocol and not from the local object.
     * @param specialReview the Special Review to update
     */
    public void prepareProtocolLinkViewFields(T specialReview) {
        if (getIsProtocolLinkingEnabled()) {
            if (specialReview != null && SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode())) {
                Protocol protocol = getLastApprovedProtocol(specialReview.getProtocolNumber());
                
                if (protocol != null) {
                    specialReview.setApprovalTypeCode(SpecialReviewApprovalType.LINK_TO_IRB);
                    specialReview.setProtocolStatus(protocol.getProtocolStatus().getDescription());
                    specialReview.setProtocolNumber(protocol.getProtocolNumber());
                    specialReview.setApplicationDate(protocol.getProtocolSubmission().getSubmissionDate());
                    specialReview.setApprovalDate(protocol.getLastApprovalDate() == null ? protocol.getApprovalDate() : protocol.getLastApprovalDate());
                    specialReview.setExpirationDate(protocol.getExpirationDate());
                    List<String> exemptionTypeCodes = new ArrayList<String>();
                    for (ProtocolExemptStudiesCheckListItem checkListItem : protocol.getProtocolSubmission().getExemptStudiesCheckList()) {
                        exemptionTypeCodes.add(checkListItem.getExemptStudiesCheckListCode());
                    }
                    specialReview.setExemptionTypeCodes(exemptionTypeCodes);
                }
            }
        }
    }
    
    /**
     * Get the existing saved Special Reviews from the form.
     * @return the list of saved Special Reviews
     */
    protected abstract List<T> getSpecialReviews();
    
    /**
     * Can the current user modify Special Review?
     * @param principalId the Id of the user for which to check permissions
     * @return true if the current user can modify Special Review, false otherwise
     */
    protected abstract boolean hasModifySpecialReviewPermission(String principalId);
    
    /**
     * Is the Protocol linking parameter enabled for this module?
     * @return true if Protocol liking is enabled for this module, false otherwise
     */
    protected abstract boolean isProtocolLinkingEnabledForModule();
    
    /**
     * Gets the last approved Protocol, ignoring any amendments or renewals.
     * @param protocolNumber the number of the Protocol
     * @return the last approved Protocol
     */
    protected Protocol getLastApprovedProtocol(String protocolNumber) {
        String lastApprovedProtocolNumber = protocolNumber;
        
        if (StringUtils.contains(protocolNumber, AMENDMENT_KEY)) {
            lastApprovedProtocolNumber = StringUtils.substringBefore(protocolNumber, AMENDMENT_KEY);
        } else if (StringUtils.contains(protocolNumber, RENEWAL_KEY)) {
            lastApprovedProtocolNumber = StringUtils.substringBefore(protocolNumber, RENEWAL_KEY);
        }
        
        return getProtocolFinderDao().findCurrentProtocolByNumber(lastApprovedProtocolNumber);
    }
    
    /**
     * Synchronizes the information between the Special Reviews and the corresponding Protocol Funding Sources.
     * 
     * @param fundingSourceNumber The human-readable number of the Institutional Proposal or Award in which the Special Review is added/saved
     * @param fundingSourceTypeCode The type code (for either Institutional Proposal or Award) of the entity in which the Special Review is added/saved
     * @param fundingSourceName The name of the Institutional Proposal or Award in which the Special Review is added/saved
     * @param fundingSourceTitle The title of the Institutional Proposal or Award in which the Special Review is added/saved
     */
    protected void syncProtocolFundingSourcesWithSpecialReviews(String fundingSourceNumber, String fundingSourceTypeCode, String fundingSourceName, 
        String fundingSourceTitle) {
        
        for (T specialReview : getSpecialReviews()) {
            if (SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode())) {
                prepareProtocolLinkViewFields(specialReview);
                
                String protocolNumber = specialReview.getProtocolNumber();
                if (!getSpecialReviewService().isLinkedToProtocolFundingSource(protocolNumber, fundingSourceNumber, fundingSourceTypeCode)) {
                    getSpecialReviewService().addProtocolFundingSourceForSpecialReview(
                        protocolNumber, fundingSourceNumber, fundingSourceTypeCode, fundingSourceName, fundingSourceTitle);
                    linkedProtocolNumbers.add(protocolNumber);
                }
            }
        }
        
        List<String> deletedLinkedProtocolNumbers = new ArrayList<String>();
        
        for (String linkedProtocolNumber : linkedProtocolNumbers) {
            boolean isLinkedToSpecialReview = false;
            for (T specialReview : getSpecialReviews()) {
                if (SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode()) 
                    && StringUtils.equals(specialReview.getProtocolNumber(), linkedProtocolNumber)) {
                    isLinkedToSpecialReview = true;
                    break;
                }
            }
            
            if (!isLinkedToSpecialReview) {
                getSpecialReviewService().deleteProtocolFundingSourceForSpecialReview(linkedProtocolNumber, fundingSourceNumber, fundingSourceTypeCode);
                deletedLinkedProtocolNumbers.add(linkedProtocolNumber);

            }
        }
        
        linkedProtocolNumbers.removeAll(deletedLinkedProtocolNumbers);
    }
    
    /**
     * Initialize the permissions for viewing/editing the Special Review web page.
     */
    private void initializePermissions() {
        canModifySpecialReview = hasModifySpecialReviewPermission(getUserIdentifier());
        isProtocolLinkingEnabled = isProtocolLinkingEnabledForModule();
    }
    
    private String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }
    
    private void initializeLinkedProtocolNumbers() {
        if (getIsProtocolLinkingEnabled()) {
            for (T specialReview : getSpecialReviews()) {
                if (SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode())) {
                    linkedProtocolNumbers.add(specialReview.getProtocolNumber());
                }
            }
        }
    }
    
    private void initializeProtocolLinkView() {
        prepareProtocolLinkViewFields(getNewSpecialReview());
        for (T specialReview : getSpecialReviews()) {
            prepareProtocolLinkViewFields(specialReview);
        }
    }
    
    public ParameterService getParameterService() {
        if (parameterService == null) {
            parameterService = KraServiceLocator.getService(ParameterService.class);
        }
        return parameterService;
    }
    
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    public ProtocolFinderDao getProtocolFinderDao() {
        if (protocolFinderDao == null) {
            protocolFinderDao = KraServiceLocator.getService(ProtocolFinderDao.class);
        }
        return protocolFinderDao;
    }

    public void setProtocolFinderDao(ProtocolFinderDao protocolFinderDao) {
        this.protocolFinderDao = protocolFinderDao;
    }
    
    public SpecialReviewService getSpecialReviewService() {
        if (specialReviewService == null) {
            specialReviewService = KraServiceLocator.getService(SpecialReviewService.class);
        }
        return specialReviewService;
    }

    public void setSpecialReviewService(SpecialReviewService specialReviewService) {
        this.specialReviewService = specialReviewService;
    }
    
}