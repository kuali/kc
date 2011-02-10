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

import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.common.specialreview.bo.SpecialReview;
import org.kuali.kra.common.specialreview.bo.SpecialReviewExemption;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.actions.submit.ProtocolExemptStudiesCheckListItem;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.GlobalVariables;

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

    private static final long serialVersionUID = 4726816248612555502L;

    private T newSpecialReview;
    private List<T> deletedSpecialReviews;

    private boolean canModifySpecialReview;
    private boolean isProtocolLinkingEnabled;
    
    private transient ParameterService parameterService;
    private transient ProtocolFinderDao protocolFinderDao;
    
    public T getNewSpecialReview() {
        return newSpecialReview;
    }
    
    public void setNewSpecialReview(T newSpecialReview) {
        this.newSpecialReview = newSpecialReview;
    }

    public List<T> getDeletedSpecialReviews() {
        return deletedSpecialReviews;
    }

    public void setDeletedSpecialReviews(List<T> deletedSpecialReviews) {
        this.deletedSpecialReviews = deletedSpecialReviews;
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
        initializeProtocolLinkView();
    }
    
    /**
     * Prepares the linked fields on the Special Review that are pulled directly from the Protocol and not from the local object.
     * @param specialReview the Special Review to update
     */
    public void prepareProtocolLinkViewFields(T specialReview) {
        if (specialReview != null) {
            Protocol protocol = getProtocolFinderDao().findCurrentProtocolByNumber(specialReview.getProtocolNumber());
            
            if (protocol != null) {
                specialReview.setApprovalTypeCode(SpecialReviewApprovalType.LINK_TO_IRB);
                specialReview.setProtocolStatus(protocol.getProtocolStatus().getDescription());
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
    
    /**
     * Initialize the permissions for viewing/editing the Special Review web page.
     */
    private void initializePermissions() {
        canModifySpecialReview = hasModifySpecialReviewPermission(getUserIdentifier());
        isProtocolLinkingEnabled = isProtocolLinkingEnabledForModule();
    }
    
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
    
    private String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }
    
    private void initializeProtocolLinkView() {
        prepareProtocolLinkViewFields(getNewSpecialReview());
        for (T specialReview : getSpecialReviews()) {
            prepareProtocolLinkViewFields(specialReview);
        }
    }
    
    /**
     * Get the existing saved Special Reviews from the form.
     * @return the list of saved Special Reviews
     */
    protected abstract List<T> getSpecialReviews();
    
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
    
}