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
package org.kuali.kra.institutionalproposal;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.DocumentAuditRule;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class processes audit rules (warnings) for the Graduate Student Information related
 * data of the Institutional Proposal Document.
 */
public class InstitutionalProposalGraduateStudentAuditRule implements DocumentAuditRule {

    private static final int FIVE = 5;
    private static final int ZERO = 0;
    private static final String GRADUATE_STUDENT_AUDIT_ERRORS = "graduateStudentAuditErrors";
    private static final String DOT = ".";
    
    private List<AuditError> auditErrors;
    
    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */ 
    @SuppressWarnings("unchecked")
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument)document;
        auditErrors = new ArrayList<AuditError>();
        
        boolean nullGradStudentHeadCount = institutionalProposalDocument.getInstitutionalProposal().getGradStudHeadcount() == null;
        boolean nullGradStudentPersonMonths = institutionalProposalDocument.getInstitutionalProposal().getGradStudPersonMonths() == null;

        if (nullGradStudentHeadCount) {
            valid = false;
            addErrorToAuditErrors("Graduate Student Head Count");
        }
        if (nullGradStudentPersonMonths) {
            valid = false;
            addErrorToAuditErrors("Graduate Student Person Months");
        }
        reportAndCreateAuditCluster();
        
        return valid;
    }
    
    /**
     * This method creates and adds the Audit Error to the <code>{@link List<AuditError>}</code> auditError.
     * @param description
     */
    protected void addErrorToAuditErrors(String param) {
        String[] params = new String[FIVE];
        params[ZERO] = param;
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.MAPPING_INSTITUTIONAL_PROPOSAL_HOME_PAGE);
        sb.append(DOT);
        sb.append(Constants.GRADUATE_STUDENT_PANEL_ANCHOR);
        auditErrors.add(new AuditError(Constants.GRADUATE_STUDENT_AUDIT_RULES_ERROR_KEY, 
                                        KeyConstants.ERROR_EMPTY_GRADUATE_STUDENT_DATA, 
                                        sb.toString(),
                                        params));   
    }
    
    /**
     * This method creates and adds the AuditCluster to the Global AuditErrorMap.
     */
    @SuppressWarnings("unchecked")
    protected void reportAndCreateAuditCluster() {
        if (auditErrors.size() > ZERO) {
            GlobalVariables.getAuditErrorMap().put(GRADUATE_STUDENT_AUDIT_ERRORS, new AuditCluster(Constants.GRADUATE_STUDENT_PANEL_NAME,
                                                                                          auditErrors, Constants.AUDIT_ERRORS));
        }
    }
}
