/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.rules;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.document.Document;
import org.kuali.core.rule.DocumentAuditRule;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.AuditCluster;
import org.kuali.core.util.AuditError;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * This class processes audit rules (warnings) for the Sponsor & Program Information related
 * data of the ProposalDevelopmenDocument.
 */
public class ProposalDevelopmentProposalRequiredFieldsAuditRule implements DocumentAuditRule {    
    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;

        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)document;
        List<AuditError> auditErrors = new ArrayList<AuditError>();

        if (StringUtils.equalsIgnoreCase(proposalDevelopmentDocument.getSponsorCode(),Constants.NIH_SPONSOR_CODE) && proposalDevelopmentDocument.getTitle().length() > 81){
            valid = false;
            auditErrors.add(new AuditError(Constants.PROJECT_TITLE_KEY, KeyConstants.ERROR_NIH_SPONSOR_PROJECT_TITLE_LENGTH, Constants.PROPOSAL_PAGE + "." + Constants.REQUIRED_FIELDS_PANEL_ANCHOR));
        }
        
        if (auditErrors.size() > 0) {
            GlobalVariables.getAuditErrorMap().put("requiredFieldsAuditErrors", new AuditCluster(Constants.REQUIRED_FIELDS_PANEL_NAME, auditErrors, Constants.AUDIT_ERRORS));
        }

        return valid;
    }

}
