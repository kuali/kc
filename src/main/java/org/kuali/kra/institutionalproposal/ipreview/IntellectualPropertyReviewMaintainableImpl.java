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
package org.kuali.kra.institutionalproposal.ipreview;

import java.io.Serializable;
import java.util.Map;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalVersioningService;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.kra.service.VersionException;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.Maintainable;

/**
 * This class leverages hooks into the Intellectual Property Review document lifecycle for custom processing.
 */
public class IntellectualPropertyReviewMaintainableImpl extends KraMaintainableImpl implements Maintainable, Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final String KIM_PERSON_LOOKUPABLE_REFRESH_CALLER = "kimPersonLookupable";
    
    /**
     * If returning from a person lookup, default the lead unit to the selected person's home unit.
     * 
     * @param refreshCaller String
     * @param fieldValues Map
     * @param document MaintenanceDocument
     * @see org.kuali.rice.kns.maintenance.Maintainable#refresh(String refreshCaller, Map fieldValues, MaintenanceDocument document)
     */
    @Override
    @SuppressWarnings("unchecked")
    public void refresh(String refreshCaller, Map fieldValues, MaintenanceDocument document) {
        super.refresh(refreshCaller, fieldValues, document);
        
        if (KIM_PERSON_LOOKUPABLE_REFRESH_CALLER.equals(refreshCaller)) {
            IntellectualPropertyReview ipReview = (IntellectualPropertyReview) this.getBusinessObject();
            String principalId = (String) fieldValues.get(KimConstants.PrimaryKeyConstants.PRINCIPAL_ID);
            ipReview.setIpReviewer(principalId);
        }
    }

    /**
    * IP Reviews are versioned, so we need to create a new version and archive the old version here.
    *
    * @see org.kuali.core.maintenance.Maintainable#prepareForSave()
    */
    @Override
    public void prepareForSave() {
        super.prepareForSave();
        if ((businessObject != null) && (businessObject instanceof IntellectualPropertyReview)) {
            try {
                IntellectualPropertyReview newVersion = getInstitutionalProposalVersioningService()
                        .createNewIntellectualPropertyReviewVersion((IntellectualPropertyReview) businessObject);
                this.setBusinessObject(newVersion);
            } catch (VersionException ve) {
                throw new RuntimeException("Caught exception versioning intellectual property review: " + ve);
            }
        }
    }
   
   /**
     * Set the new collection records back to true so they can be deleted (copy should act like new).
     * 
     * @param document MaintenanceDocument
     * @param parameters Map<String, String[]>
     * @see org.kuali.rice.kns.maintenance.KualiMaintainableImpl#processAfterCopy()
     */
    public void processAfterCopy(MaintenanceDocument document, Map<String, String[]> parameters) {
        super.processAfterCopy(document, parameters);
        String proposalIdToLink = parameters.get("proposalId")[0];
        ((IntellectualPropertyReview) this.getBusinessObject()).setProposalIdToLink(Long.parseLong(proposalIdToLink));
    }

    private InstitutionalProposalVersioningService getInstitutionalProposalVersioningService() {
        return KraServiceLocator.getService(InstitutionalProposalVersioningService.class);
    }

}
