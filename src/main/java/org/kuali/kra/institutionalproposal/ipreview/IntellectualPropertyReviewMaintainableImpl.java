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
package org.kuali.kra.institutionalproposal.ipreview;

import java.io.Serializable;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalVersioningService;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.kra.service.VersionException;
import org.kuali.rice.kns.maintenance.Maintainable;

public class IntellectualPropertyReviewMaintainableImpl extends KraMaintainableImpl implements Maintainable, Serializable {

    private static final long serialVersionUID = 1L;

    /**
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
            }
            catch (VersionException ve) {
                throw new RuntimeException("Caught exception versioning intellectual property review: " + ve);
            }
        }
    }

    private InstitutionalProposalVersioningService getInstitutionalProposalVersioningService() {
        return KraServiceLocator.getService(InstitutionalProposalVersioningService.class);
    }

}
