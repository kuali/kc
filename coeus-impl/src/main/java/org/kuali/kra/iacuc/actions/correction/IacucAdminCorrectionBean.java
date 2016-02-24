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
