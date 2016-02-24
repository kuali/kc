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
package org.kuali.kra.award.rule;

import org.kuali.coeus.common.framework.sponsor.term.SponsorTermType;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.kra.award.home.AwardTemplateTerm;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SponsorTemplateTermsExistenceRule extends KcMaintenanceDocumentRuleBase {


    public SponsorTemplateTermsExistenceRule() {
        super();
    }
    
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkTermsExistence(document);
    }
    /**
     * 
     * This method is to check the existence of terms of the Sponsor Template.
     * @param maintenanceDocument
     * @return
     */
    private boolean checkTermsExistence(MaintenanceDocument maintenanceDocument) {
        boolean valid= true;
        AwardTemplate awardTemplate = (AwardTemplate) maintenanceDocument.getNewMaintainableObject().getDataObject();
        List<AwardTemplateTerm> aList = awardTemplate.getAwardSponsorTerms();

        BusinessObjectService businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        Collection<SponsorTermType> types = businessObjectService.findAll(SponsorTermType.class);
        ArrayList currentTypeCodes = new ArrayList();
        for(AwardTemplateTerm aTerm : aList) {
            currentTypeCodes.add(aTerm.getSponsorTermTypeCode());
        }
        
        for(SponsorTermType aType: types) {
            if(!currentTypeCodes.contains(aType.getSponsorTermTypeCode())) {
                ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);
                errorReporter.reportError("document.newMaintainableObject.templateTerms", 
                      KeyConstants.ERROR_TERM_REQUIRED, aType.getDescription());
                valid = false;
            }
        }
        return valid;
    }
    
}
