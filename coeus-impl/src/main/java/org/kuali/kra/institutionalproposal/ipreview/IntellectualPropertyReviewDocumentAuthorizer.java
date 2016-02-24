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

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentAuthorizer;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentAuthorizerBase;

import java.util.Map;

/**
 * Custom document authorizer for IntellectualPropertyReview maintenance document.
 * We use a custom authorizer to add Unit Number qualification for KIM role-based authorizations.
 */
public class IntellectualPropertyReviewDocumentAuthorizer 
    extends MaintenanceDocumentAuthorizerBase implements MaintenanceDocumentAuthorizer {
    
    @Override
    protected void addRoleQualification(
            Object primaryBusinessObjectOrDocument,
            Map<String, String> attributes) {
        super.addRoleQualification(primaryBusinessObjectOrDocument, attributes);
        MaintenanceDocument maintenanceDocument = (MaintenanceDocument) primaryBusinessObjectOrDocument;
        IntellectualPropertyReview ipReview = 
            (IntellectualPropertyReview) maintenanceDocument.getOldMaintainableObject().getDataObject();
        if (!StringUtils.isBlank(ipReview.getLeadUnitNumber())) {
            attributes.put(KcKimAttributes.UNIT_NUMBER, ipReview.getLeadUnitNumber());
        } else {
            attributes.put(KcKimAttributes.UNIT_NUMBER, "*");
        }
    }

}
