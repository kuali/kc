/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.RiceKeyConstants;
import org.kuali.core.document.Document;
import org.kuali.core.rule.DocumentAuditRule;
import org.kuali.core.util.AuditCluster;
import org.kuali.core.util.AuditError;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;

/**
 * This class processes audit rules (warnings) for the Sponsor & Program Information related
 * data of the ProposalDevelopmenDocument.
 */
public class ResearchDocumentBaseAuditRule implements DocumentAuditRule {

    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;

        if (document instanceof ResearchDocumentBase) {
            Map<String, CustomAttributeDocument> customAttributeDocuments = ((ResearchDocumentBase)document).getCustomAttributeDocuments();

            for (Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry: customAttributeDocuments.entrySet()) {
                CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
                CustomAttribute customAttribute = customAttributeDocument.getCustomAttribute();
                if (customAttributeDocument.isRequired() && StringUtils.isEmpty(customAttribute.getValue())) {
                    valid = false;
                    String key = "customAttributes." + customAttribute.getGroupName() + "Errors";
                    AuditCluster auditCluster = (AuditCluster) GlobalVariables.getAuditErrorMap().get(key);
                    if (auditCluster == null) {
                        List<AuditError> auditErrors = new ArrayList<AuditError>();
                        auditCluster = new AuditCluster("Custom Data: " + customAttribute.getGroupName(), auditErrors, Constants.AUDIT_ERRORS);
                        GlobalVariables.getAuditErrorMap().put(key, auditCluster);
                    }
                    List<AuditError> auditErrors = auditCluster.getAuditErrorList();
                    auditErrors.add(new AuditError("customData." + customAttribute.getName(), RiceKeyConstants.ERROR_REQUIRED, Constants.CUSTOM_ATTRIBUTES_PAGE + "." + customAttribute.getGroupName(), new String[]{customAttribute.getLabel()}));
                }
            }

        }

        return valid;
    }

}
