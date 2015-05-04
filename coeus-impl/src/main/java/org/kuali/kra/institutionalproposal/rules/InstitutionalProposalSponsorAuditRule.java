/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 *
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.institutionalproposal.rules;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.sponsor.SponsorService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;

import java.util.ArrayList;
import java.util.List;

public class InstitutionalProposalSponsorAuditRule implements DocumentAuditRule {

    private static final String SPONSOR_WARNINGS = "sponsorWarnings";
    private SponsorService sponsorService;
    private GlobalVariableService globalVariableService;

    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        final List<AuditError> sponsorAuditWarnings = new ArrayList<>();

        final InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument) document;

        if (!getSponsorService().isValidSponsor(institutionalProposalDocument.getInstitutionalProposal().getSponsor())) {
            sponsorAuditWarnings.add(new AuditError("document.institutionalProposalList[0].sponsorCode", KeyConstants.WARNING_INSTITUTIONALPROPOSAL_INACTIVE_SPONSOR, Constants.MAPPING_INSTITUTIONAL_PROPOSAL_HOME_PAGE + "." + Constants.INSTITUTIONAL_PROPOSAL_IP_PANEL_ANCHOR ));
            valid = false;
        }

        if (!StringUtils.isEmpty(institutionalProposalDocument.getInstitutionalProposal().getPrimeSponsorCode()) &&
                !getSponsorService().isValidSponsor(institutionalProposalDocument.getInstitutionalProposal().getPrimeSponsor())) {
            sponsorAuditWarnings.add(new AuditError("document.institutionalProposalList[0].primeSponsorCode", KeyConstants.WARNING_INSTITUTIONALPROPOSAL_INACTIVE_PRIMESPONSOR, Constants.MAPPING_INSTITUTIONAL_PROPOSAL_HOME_PAGE + "." + Constants.INSTITUTIONAL_PROPOSAL_IP_PANEL_ANCHOR ));
            valid = false;
        }

        if (!sponsorAuditWarnings.isEmpty()) {
            getGlobalVariableService().getAuditErrorMap().put(SPONSOR_WARNINGS, new AuditCluster("Sponsors", sponsorAuditWarnings, Constants.AUDIT_WARNINGS));
        }
        return valid;
    }

    public GlobalVariableService getGlobalVariableService() {
        if (globalVariableService == null) {
            globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }

        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    private SponsorService getSponsorService() {
        if (sponsorService == null) {
            sponsorService = KcServiceLocator.getService(SponsorService.class);
        }
        return sponsorService;
    }

    public void setSponsorService(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }
}
