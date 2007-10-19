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
package org.kuali.kra.proposaldevelopment.rules;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.kuali.core.document.Document;
import org.kuali.core.rule.DocumentAuditRule;
import org.kuali.core.util.AuditCluster;
import org.kuali.core.util.AuditError;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * This class processes audit rules (warnings) for the Sponsor & Program Information related
 * data of the ProposalDevelopmenDocument.
 */
public class ProposalDevelopmentSponsorProgramInformationAuditRule implements DocumentAuditRule {

    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;

        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)document;
        List<AuditError> auditErrors = new ArrayList<AuditError>();

        //The Proposal Deadline Date should return a warning during validation for the
        //following conditions: a) if the date entered is older than the current date,
        //or b) if there is no data entered.
        if (proposalDevelopmentDocument.getDeadlineDate() == null) {
            valid = false;
            auditErrors.add(new AuditError("document.deadlineDate", KeyConstants.WARNING_EMPTY_DEADLINE_DATE, "proposal.SponsorProgramInformation"));
        } else if (proposalDevelopmentDocument.getDeadlineDate().before(new Date(System.currentTimeMillis()))) {
            valid = false;
            auditErrors.add(new AuditError("document.deadlineDate", KeyConstants.WARNING_PAST_DEADLINE_DATE, "proposal.SponsorProgramInformation"));
        }

        if (auditErrors.size() > 0) {
            GlobalVariables.getAuditErrorMap().put("sponsorProgramInformationAuditWarnings", new AuditCluster("Sponsor & Program Information", auditErrors, "Warnings"));
        }

        return valid;
    }

}
