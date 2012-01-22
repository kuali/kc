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
package org.kuali.kra.s2s.generator.impl;

import gov.grants.apply.forms.rrsf424SF424BV11.AssuranceType;
import gov.grants.apply.forms.rrsf424SF424BV11.AssurancesDocument;
import gov.grants.apply.forms.rrsf424SF424BV11.AuthorizedRepresentativeDocument.AuthorizedRepresentative;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.rice.core.api.datetime.DateTimeService;

/**
 * This class...
 */
public class RRSF424BV1_1Generator extends S2SBaseFormGenerator {

    private static final String NON_CONSTRUCTION = "Non-Construction";
    /**
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public AssurancesDocument getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) throws S2SException {
        AssurancesDocument assurcesDocument = AssurancesDocument.Factory.newInstance();
        DevelopmentProposal propDevFormBean = proposalDevelopmentDocument.getDevelopmentProposal();
        AssuranceType rrSF424B = assurcesDocument.addNewAssurances();
        rrSF424B.setFormVersionIdentifier(S2SConstants.FORMVERSION_1_1);
        rrSF424B.setProgramType(NON_CONSTRUCTION);
        rrSF424B.setFormVersion(S2SConstants.FORMVERSION_1_1);
        ProposalSite applicantOrganization = propDevFormBean.getApplicantOrganization();
        rrSF424B.setApplicantOrganizationName(applicantOrganization.getOrganization().getOrganizationName());
        rrSF424B.setAuthorizedRepresentative(getAuthorizedRepresentative(proposalDevelopmentDocument));
        rrSF424B.setSubmittedDate(getDateTimeService().getCurrentCalendar());
        return assurcesDocument;
    }
    private DateTimeService getDateTimeService() {
        return KraServiceLocator.getService(DateTimeService.class);
    }
    /**
     * 
     * This method gets AuthorizedRepresentative details RepresentativeTitle based on ProposalDevelopmentDocument.
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return AuthorizedRepresentative authorized representative title.
     */
    private AuthorizedRepresentative getAuthorizedRepresentative(ProposalDevelopmentDocument proposalDevelopmentDocument) {

        AuthorizedRepresentative authorizedRepresentative = AuthorizedRepresentative.Factory.newInstance();
        DepartmentalPerson aorInfo = getS2sUtilService().getDepartmentalPerson(proposalDevelopmentDocument);
        if (aorInfo.getPrimaryTitle() != null) {
            authorizedRepresentative.setRepresentativeTitle(aorInfo.getPrimaryTitle());
        }
        return authorizedRepresentative;
    }
    private S2SUtilService getS2sUtilService() {
        return KraServiceLocator.getService(S2SUtilService.class);
    }

}
