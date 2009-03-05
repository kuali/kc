/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.rules;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.document.Document;
import org.kuali.core.rule.DocumentAuditRule;
import org.kuali.core.util.AuditCluster;
import org.kuali.core.util.AuditError;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;

/**
 * This class processes audit rules (warnings) for the Report Information related
 * data of the AwardDocument.
 */
public class AwardReportAuditRule implements DocumentAuditRule {

    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    @SuppressWarnings("unchecked")
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;

        AwardDocument awardDocument = (AwardDocument)document;
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        
        if (awardDocument.getAward().getAwardReportTerms().size() == 0) {
            valid = false;
            auditErrors.add(new AuditError(Constants.REPORT_TERMS_AUDIT_RULES_ERROR_KEY, 
                                            KeyConstants.ERROR_EMPTY_REPORT_TERMS, 
                                            Constants.MAPPING_AWARD_PAYMENT_REPORTS_AND_TERMS_PAGE + "." + Constants.REPORTS_PANEL_ANCHOR));
        } 
        
        if (auditErrors.size() > 0) {
            GlobalVariables.getAuditErrorMap().put("reportsAuditErrors", new AuditCluster(Constants.REPORTS_PANEL_NAME,
                                                                                            auditErrors, Constants.AUDIT_ERRORS));
        }
        
        return valid;
    }

}
