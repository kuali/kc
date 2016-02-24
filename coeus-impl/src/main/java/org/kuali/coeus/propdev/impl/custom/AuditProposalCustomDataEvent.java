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
package org.kuali.coeus.propdev.impl.custom;

import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.AUDIT_ERRORS;
import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.SUPPLEMENTAL_PAGE_ID;
import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.SUPPLEMENTAL_PAGE_NAME;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.AuditCustomDataEvent;
import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;

public class AuditProposalCustomDataEvent extends AuditCustomDataEvent {
	
	public AuditProposalCustomDataEvent(KcTransactionalDocumentBase document) {
		super(document);
	}

	@Override
	public void reportError(CustomAttribute customAttribute, String propertyName, String errorKey, String... errorParams) {
        String key = SUPPLEMENTAL_PAGE_NAME + "." +customAttribute.getGroupName();
        AuditCluster auditCluster = GlobalVariables.getAuditErrorMap().get(key);
        if (auditCluster == null) {
            List<AuditError> auditErrors = new ArrayList<AuditError>();
            auditCluster = new AuditCluster(key, auditErrors, AUDIT_ERRORS);
            GlobalVariables.getAuditErrorMap().put(key, auditCluster);
        }
        List<AuditError> auditErrors = auditCluster.getAuditErrorList();
        auditErrors.add(new AuditError(StringUtils.removePattern(customAttribute.getGroupName() + "_" + customAttribute.getLabel(), "([^0-9a-zA-Z\\-_])"),
                errorKey, SUPPLEMENTAL_PAGE_ID + "." + customAttribute.getGroupName().replace(" ","_"), errorParams));

    }
}
