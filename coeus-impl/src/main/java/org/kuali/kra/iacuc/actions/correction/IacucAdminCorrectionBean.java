/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc.actions.correction;

import org.kuali.kra.iacuc.actions.IacucActionHelper;
import org.kuali.kra.iacuc.actions.IacucProtocolEditableBean;
import org.kuali.kra.protocol.actions.correction.AdminCorrectionBean;

public class IacucAdminCorrectionBean extends IacucProtocolEditableBean implements AdminCorrectionBean {


    private static final long serialVersionUID = 3855105581488726963L;
    
    private String comments;
    private boolean applyCorrection;
    
    /**
     * Constructs a AdminCorrectionBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public IacucAdminCorrectionBean(IacucActionHelper actionHelper) {
        super(actionHelper);
    }
    
    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public boolean isApplyCorrection() {
        return applyCorrection;
    }
    
    public void setApplyCorrection(boolean applyCorrection) {
        this.applyCorrection = applyCorrection;
    }
    
    public boolean isAmendmentRenewalOutstanding() {
        return !(getGeneralInfoEnabled() &&  
            getFundingSourceEnabled() && 
            getProtocolReferencesEnabled() && 
            getProtocolOrganizationsEnabled() && 
            getSubjectsEnabled() && 
            getAddModifyAttachmentsEnabled() && 
            getAreasOfResearchEnabled() && 
            getSpecialReviewEnabled() && 
            getProtocolPersonnelEnabled() && 
            getOthersEnabled() &&
            getProtocolPermissionsEnabled() &&
            getQuestionnaireEnabled());
    }


}
