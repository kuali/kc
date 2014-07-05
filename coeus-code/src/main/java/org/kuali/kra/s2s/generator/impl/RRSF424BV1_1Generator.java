/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.location.ProposalSiteContract;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.generator.FormGenerator;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.kra.s2s.util.S2SConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Calendar;

@FormGenerator("RRSF424BV1_1Generator")
public class RRSF424BV1_1Generator extends S2SBaseFormGenerator {

    private static final String NON_CONSTRUCTION = "Non-Construction";

    @Autowired
    @Qualifier("s2SUtilService")
    private S2SUtilService s2SUtilService;

    @Override
    public AssurancesDocument getFormObject(ProposalDevelopmentDocumentContract ProposalDevelopmentDocumentContract) throws S2SException {
        AssurancesDocument assurcesDocument = AssurancesDocument.Factory.newInstance();
        DevelopmentProposalContract propDevFormBean = ProposalDevelopmentDocumentContract.getDevelopmentProposal();
        AssuranceType rrSF424B = assurcesDocument.addNewAssurances();
        rrSF424B.setFormVersionIdentifier(S2SConstants.FORMVERSION_1_1);
        rrSF424B.setProgramType(NON_CONSTRUCTION);
        rrSF424B.setFormVersion(S2SConstants.FORMVERSION_1_1);
        ProposalSiteContract applicantOrganization = propDevFormBean.getApplicantOrganization();
        rrSF424B.setApplicantOrganizationName(applicantOrganization.getOrganization().getOrganizationName());
        rrSF424B.setAuthorizedRepresentative(getAuthorizedRepresentative(ProposalDevelopmentDocumentContract));
        rrSF424B.setSubmittedDate(Calendar.getInstance());
        return assurcesDocument;
    }
    /**
     * 
     * This method gets AuthorizedRepresentative details RepresentativeTitle based on ProposalDevelopmentDocumentContract.
     * 
     * @param ProposalDevelopmentDocumentContract (ProposalDevelopmentDocumentContract)
     * @return AuthorizedRepresentative authorized representative title.
     */
    private AuthorizedRepresentative getAuthorizedRepresentative(ProposalDevelopmentDocumentContract ProposalDevelopmentDocumentContract) {

        AuthorizedRepresentative authorizedRepresentative = AuthorizedRepresentative.Factory.newInstance();
        DepartmentalPerson aorInfo = getS2SUtilService().getDepartmentalPerson(ProposalDevelopmentDocumentContract);
        if (aorInfo.getPrimaryTitle() != null) {
            authorizedRepresentative.setRepresentativeTitle(aorInfo.getPrimaryTitle());
        }
        return authorizedRepresentative;
    }

    public S2SUtilService getS2SUtilService() {
        return s2SUtilService;
    }

    public void setS2SUtilService(S2SUtilService s2SUtilService) {
        this.s2SUtilService = s2SUtilService;
    }
}
