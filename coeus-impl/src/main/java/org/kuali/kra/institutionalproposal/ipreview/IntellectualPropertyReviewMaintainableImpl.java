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
package org.kuali.kra.institutionalproposal.ipreview;

import org.kuali.coeus.common.framework.version.VersionException;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalVersioningService;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.krad.data.MaterializeOption;
import org.kuali.rice.krad.util.KRADPropertyConstants;
import org.kuali.rice.krad.util.ObjectUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * This class leverages hooks into the Intellectual Property Review document lifecycle for custom processing.
 */
public class IntellectualPropertyReviewMaintainableImpl extends KraMaintainableImpl implements Maintainable, Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final String KIM_PERSON_LOOKUPABLE_REFRESH_CALLER = "kimPersonLookupable";
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(IntellectualPropertyReviewMaintainableImpl.class);
    
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
     * @param parameters Map&lt;String, String[]&gt;
     */
    public void processAfterCopy(MaintenanceDocument document, Map<String, String[]> parameters) {
    	/*
    	 * KualiMaintainableImpl.processAfterCopy causes problems here, So I copied the code in here, and fixed the code causing problems.
    	 * Note, this change is how Rice was coded for KC 5.2.
    	 */
    	try {
			getDataObjectService().wrap(businessObject).materializeReferencedObjectsToDepth(2, MaterializeOption.COLLECTIONS, MaterializeOption.UPDATE_UPDATABLE_REFS);
			/*
			 * KualiMaintainableImpl  originally called this line.  However, Rice was changed such that KNSLegacyDataAdapterImpl.setObjectPropertyDeep
			 * makes this same call, without the last parameter, which determines how deep the copy goes.  The original implementation works, but if you go deeper,
			 * We get erroneous errors for attachments on Award, and budgets on AwardDocument.  See KRACOEUS-7798 for details.
			 */
			ObjectUtils.setObjectPropertyDeep(businessObject, KRADPropertyConstants.NEW_COLLECTION_RECORD, boolean.class, true, 2);
		} catch (Exception e) {
			LOG.error("unable to set newCollectionRecord property: " + e.getMessage(), e);
			throw new RuntimeException("unable to set newCollectionRecord property: " + e.getMessage(), e);
		}
        
        
        String proposalIdToLink = parameters.get("proposalId")[0];
        ((IntellectualPropertyReview) this.getBusinessObject()).setProposalIdToLink(Long.parseLong(proposalIdToLink));
    }

    private InstitutionalProposalVersioningService getInstitutionalProposalVersioningService() {
        return KcServiceLocator.getService(InstitutionalProposalVersioningService.class);
    }

}
