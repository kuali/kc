/*
 * Copyright 2006-2008 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.DocumentAuditRule;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.RiceKeyConstants;

/**
 * This class processes audit rules (warnings) for the Sponsor & Program Information related
 * data of the ProposalDevelopmenDocument.
 */
public class ResearchDocumentBaseAuditRule implements DocumentAuditRule {

    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.kns.document.Document)
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
                    String key = "CustomData" + StringUtils.deleteWhitespace(customAttribute.getGroupName()) + "Errors";
                    AuditCluster auditCluster = (AuditCluster) GlobalVariables.getAuditErrorMap().get(key);
                    if (auditCluster == null) {
                        List<AuditError> auditErrors = new ArrayList<AuditError>();
                        auditCluster = new AuditCluster("Custom Data: " + customAttribute.getGroupName(), auditErrors, Constants.AUDIT_ERRORS);
                        GlobalVariables.getAuditErrorMap().put(key, auditCluster);
                    }
                    List<AuditError> auditErrors = auditCluster.getAuditErrorList();
                    auditErrors.add(new AuditError("customAttributeValues(id"+customAttributeDocument.getCustomAttributeId()+")", RiceKeyConstants.ERROR_REQUIRED, StringUtils.deleteWhitespace(Constants.CUSTOM_ATTRIBUTES_PAGE + "." + customAttribute.getGroupName()), new String[]{customAttribute.getLabel()}));
                }
            }

        }

        return valid;
    }

}
