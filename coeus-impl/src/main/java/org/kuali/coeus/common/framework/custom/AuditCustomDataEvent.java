/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.framework.custom;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.List;

public class AuditCustomDataEvent extends SaveCustomDataEvent {

    public AuditCustomDataEvent(KcTransactionalDocumentBase document) {
        super(document, true);
    }
    
    public void reportError(CustomAttribute customAttribute, String propertyName, String errorKey, String... errorParams) {
        reportErrorOrWarning(customAttribute, propertyName, errorKey, Constants.AUDIT_ERRORS, errorParams);
    }

    public void reportWarning(CustomAttribute customAttribute, String propertyName, String errorKey, String... errorParams) {
        reportErrorOrWarning(customAttribute, propertyName, errorKey, Constants.AUDIT_WARNINGS, errorParams);
    }

    public void reportErrorOrWarning(CustomAttribute customAttribute, String propertyName, String errorKey, String errorCategory, String... errorParams) {
        String key = "CustomData" + StringUtils.deleteWhitespace(customAttribute.getGroupName());
        if (Constants.AUDIT_ERRORS.equals(errorCategory)) {
            key += "Errors";
        }
        else {
            key += "Warnings";
        }
        AuditCluster auditCluster = (AuditCluster) GlobalVariables.getAuditErrorMap().get(key);
        if (auditCluster == null) {
            List<AuditError> auditErrors = new ArrayList<AuditError>();
            auditCluster = new AuditCluster(customAttribute.getGroupName(), auditErrors, errorCategory);
            GlobalVariables.getAuditErrorMap().put(key, auditCluster);
        }
        List<AuditError> auditErrors = auditCluster.getAuditErrorList();
        auditErrors.add(new AuditError(propertyName, errorKey, StringUtils.deleteWhitespace(Constants.CUSTOM_ATTRIBUTES_PAGE + "." + customAttribute.getGroupName()), errorParams));
    }

}
