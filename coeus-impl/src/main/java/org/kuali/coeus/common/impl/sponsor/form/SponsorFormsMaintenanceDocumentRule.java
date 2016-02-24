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
package org.kuali.coeus.common.impl.sponsor.form;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.sponsor.form.SponsorForms;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;

/**
 * This class overrides the custom route and custom approve methods of the MaintenanceDocument processing to check the length of the
 * sponsor code and return a more informative error message than the Rice message if the length constraint is violated.
 */
public class SponsorFormsMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {
    private GlobalVariableService globalVariableService;

    public SponsorFormsMaintenanceDocumentRule() {
        super();
    }
    
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        return checkSponsorCodeOrHierarchyName(document);
    }

    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return checkSponsorCodeOrHierarchyName(document);
    }

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkSponsorCodeOrHierarchyName(document);
    }

    /**
     * This method verifies that the sponsorCode adheres to the required rules.
     * 
     * @param document
     * @return
     */
    private boolean checkSponsorCodeOrHierarchyName(MaintenanceDocument document) {
        boolean valid = true;
        SponsorForms sponsorForm = (SponsorForms) document.getNewMaintainableObject().getDataObject();
        if (StringUtils.isBlank(sponsorForm.getSponsorCode()) && StringUtils.isBlank(sponsorForm.getSponsorHierarchyName())
                || (StringUtils.isNotBlank(sponsorForm.getSponsorCode()) && StringUtils.isNotBlank(sponsorForm.getSponsorHierarchyName()))) {
            getGlobalVariableService().getMessageMap().putError("document.newMaintainableObject.sponsorCode", "error.sponsorForms.selector");
            valid = false;
        }
        return valid;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public GlobalVariableService getGlobalVariableService() {
        if (this.globalVariableService == null) {
            this.globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }
        return this.globalVariableService;
    }

}
