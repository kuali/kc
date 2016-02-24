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
package org.kuali.coeus.common.framework.compliance.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.compliance.exemption.SpecialReviewExemption;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.specialreview.IacucProtocolSpecialReviewService;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.specialreview.ProtocolSpecialReviewService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
     * Namespace code for IACUC Protocol linking parameters.
     */
    protected static final String IACUC_NAMESPACE_CODE = "KC-IACUC";
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
    private boolean isIrbProtocolLinkingEnabled;
    private boolean isIacucProtocolLinkingEnabled;
    
    private transient ParameterService parameterService;
    private transient ProtocolFinderDao protocolFinderDao;
    private transient SpecialReviewService specialReviewService;
    private transient ProtocolSpecialReviewService protocolSpecialReviewService;   
    private transient IacucProtocolSpecialReviewService iacucProtocolSpecialReviewService;
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
    
    public boolean getIsIrbProtocolLinkingEnabled() {
        return isIrbProtocolLinkingEnabled;
    }
    public boolean getIsIacucProtocolLinkingEnabled() {
        return isIacucProtocolLinkingEnabled;
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
        if (getIsIrbProtocolLinkingEnabled() || getIsIacucProtocolLinkingEnabled()) {
            if (specialReview != null && SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode())) {
                ProtocolSpecialReviewService protocolSpecialReviewService = getProtocolSpecialReviewService();
                protocolSpecialReviewService.populateSpecialReview(specialReview);
            }
            else if (specialReview != null && SpecialReviewType.ANIMAL_USAGE.equals(specialReview.getSpecialReviewTypeCode())) {
                IacucProtocolSpecialReviewService iacucProtocolSpecialReviewService = getIacucProtocolSpecialReviewService();
                iacucProtocolSpecialReviewService.populateSpecialReview(specialReview);
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

    protected abstract boolean isIrbProtocolLinkingEnabledForModule();

    /**
     * Is the Iacuc Protocol linking parameter enabled for this module?
     * @return true if Protocol liking is enabled for this module, false otherwise
     */

    protected abstract boolean isIacucProtocolLinkingEnabledForModule();
    
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
        isIrbProtocolLinkingEnabled = isIrbProtocolLinkingEnabledForModule();
        isIacucProtocolLinkingEnabled = isIacucProtocolLinkingEnabledForModule();
    }
    
    private String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }
    
    private void initializeLinkedProtocolNumbers() {
        if (getIsIrbProtocolLinkingEnabled() || getIsIacucProtocolLinkingEnabled()) {
            for (T specialReview : getSpecialReviews()) {
                if (( SpecialReviewType.HUMAN_SUBJECTS.equals(specialReview.getSpecialReviewTypeCode()) && getIsIrbProtocolLinkingEnabled() )
                        || ( SpecialReviewType.ANIMAL_USAGE.equals(specialReview.getSpecialReviewTypeCode()) && getIsIacucProtocolLinkingEnabled() ) ) {
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
            parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return parameterService;
    }
    
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    public ProtocolFinderDao getProtocolFinderDao() {
        if (protocolFinderDao == null) {
            protocolFinderDao = KcServiceLocator.getService(ProtocolFinderDao.class);
        }
        return protocolFinderDao;
    }

    public void setProtocolFinderDao(ProtocolFinderDao protocolFinderDao) {
        this.protocolFinderDao = protocolFinderDao;
    }
    
    public SpecialReviewService getSpecialReviewService() {
        if (specialReviewService == null) {
            specialReviewService = KcServiceLocator.getService(SpecialReviewService.class);
        }
        return specialReviewService;
    }

    public void setSpecialReviewService(SpecialReviewService specialReviewService) {
        this.specialReviewService = specialReviewService;
    }
    
    public ProtocolSpecialReviewService getProtocolSpecialReviewService() {
        if (protocolSpecialReviewService == null) {
            protocolSpecialReviewService = KcServiceLocator.getService(ProtocolSpecialReviewService.class);
        }
        return protocolSpecialReviewService;
    }
   
    public void setProtocolSpecialReviewService(ProtocolSpecialReviewService protocolSpecialReviewService) {
        this.protocolSpecialReviewService = protocolSpecialReviewService;
    }

    public IacucProtocolSpecialReviewService getIacucProtocolSpecialReviewService() {
        if (iacucProtocolSpecialReviewService == null) {
            iacucProtocolSpecialReviewService = KcServiceLocator.getService(IacucProtocolSpecialReviewService.class);
        }
        return iacucProtocolSpecialReviewService;
    }
   
    public void setIacucProtocolSpecialReviewService(IacucProtocolSpecialReviewService iacucProtocolSpecialReviewService) {
        this.iacucProtocolSpecialReviewService = iacucProtocolSpecialReviewService;
    }
   // method to check if IRB protocol can be created from Special review page for non-Protocol modules such as proposal, award and so on
    protected abstract boolean isCanCreateIrbProtocol();
    // method to check if IACUC protocol can be created from Special review page for non-Protocol modules such as proposal, award and so on
    protected abstract boolean isCanCreateIacucProtocol();
}
