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

import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.*;


public class AuditCustomDataEvent extends SaveCustomDataEvent {

    public AuditCustomDataEvent(KcTransactionalDocumentBase document) {
        super(document, true);
    }
    
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
