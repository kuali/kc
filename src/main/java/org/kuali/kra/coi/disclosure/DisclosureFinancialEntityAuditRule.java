/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.coi.disclosure;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.coi.CoiDiscDetail;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.DocumentAuditRule;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;

public class DisclosureFinancialEntityAuditRule extends ResearchDocumentRuleBase implements DocumentAuditRule {

    private static final String FINANCIAL_ENTITY_AUDIT_ERRORS = "financialEntityDiscAuditErrors";
    private List<AuditError> auditErrors;
    
    public boolean processRunAuditBusinessRules(Document document) {
        boolean isValid = true;
        CoiDisclosureDocument coiDisclosureDocument = (CoiDisclosureDocument) document;
        auditErrors = new ArrayList<AuditError>();
        
        isValid = isConflictValueSelected(coiDisclosureDocument.getCoiDisclosure());

        reportAndCreateAuditCluster();
        
        return isValid;
    }

    private void addErrorToAuditErrors(int index) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.COI_DISCLOSURE_DISCLOSURE_PAGE);
        stringBuilder.append(".");
        stringBuilder.append(Constants.DISCLOSURE_FINANCIAL_ENTITY_PANEL_ANCHOR);
        auditErrors.add(new AuditError(String.format(Constants.DISCLOSURE_FINANCIAL_ENTITY_KEY, index),
                                        KeyConstants.ERROR_COI_FINANCIAL_ENTITY_STATUS_REQUIRED,
                                        stringBuilder.toString()));   
    }

    private boolean isConflictValueSelected(CoiDisclosure coiDisclosure) {
        boolean isSelected = true;
        int i = 0;
        for (CoiDiscDetail coiDiscDetail : coiDisclosure.getCoiDiscDetails()) {
            if (coiDiscDetail.getEntityStatusCode() == null) {
                addErrorToAuditErrors(i);
                isSelected = false;
            }
            i++;
        }
        return isSelected;
    }
    
    protected void reportAndCreateAuditCluster() {
        if (auditErrors.size() > 0) {
            GlobalVariables.getAuditErrorMap().put(FINANCIAL_ENTITY_AUDIT_ERRORS, 
                    new AuditCluster(Constants.COI_DISCLOSURE_DISCLOSURE_PANEL_NAME, auditErrors, Constants.AUDIT_ERRORS));
        }
    }

}
