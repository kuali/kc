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
package org.kuali.kra.institutionalproposal.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.common.api.sponsor.SponsorService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.List;

/**
 * This class processes audit rules (warnings) for the Custom Data Information.
 */
public class InstitutionalProposalSponsorAuditRule implements DocumentAuditRule {

    private static final String SPONSOR_WARNINGS = "sponsorWarnings";

    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.kns.document.Document)
     */
    @SuppressWarnings("unchecked")
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        List<AuditError> sponsorAuditWarnings = new ArrayList<AuditError>();

        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument) document;

        SponsorService ss = this.getSponsorService();
        if (!ss.isValidSponsor(institutionalProposalDocument.getInstitutionalProposal().getSponsor())) {
            sponsorAuditWarnings.add(new AuditError("document.institutionalProposalList[0].sponsorCode", KeyConstants.WARNING_INSTITUTIONALPROPOSAL_INACTIVE_SPONSOR, Constants.MAPPING_INSTITUTIONAL_PROPOSAL_HOME_PAGE + "." + Constants.INSTITUTIONAL_PROPOSAL_IP_PANEL_ANCHOR ));
            valid = false;
        }

        if (!StringUtils.isEmpty(institutionalProposalDocument.getInstitutionalProposal().getPrimeSponsorCode()) &&
                !ss.isValidSponsor(institutionalProposalDocument.getInstitutionalProposal().getPrimeSponsor())) {
            sponsorAuditWarnings.add(new AuditError("document.institutionalProposalList[0].primeSponsorCode", KeyConstants.WARNING_INSTITUTIONALPROPOSAL_INACTIVE_PRIMESPONSOR, Constants.MAPPING_INSTITUTIONAL_PROPOSAL_HOME_PAGE + "." + Constants.INSTITUTIONAL_PROPOSAL_IP_PANEL_ANCHOR ));
            valid = false;
        }

        if (sponsorAuditWarnings.size() > 0) {
            GlobalVariables.getAuditErrorMap().put(SPONSOR_WARNINGS, new AuditCluster("Sponsors", sponsorAuditWarnings, Constants.AUDIT_WARNINGS));
        }
        return valid;
    }

    private SponsorService getSponsorService() {
        return KcServiceLocator.getService(SponsorService.class);
    }
}
