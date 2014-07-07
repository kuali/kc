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
package org.kuali.coeus.s2sgen.impl.generate.support;

import gov.grants.apply.forms.rrsf424SF424BV11.AssuranceType;
import gov.grants.apply.forms.rrsf424SF424BV11.AssurancesDocument;
import gov.grants.apply.forms.rrsf424SF424BV11.AuthorizedRepresentativeDocument.AuthorizedRepresentative;

import org.kuali.coeus.propdev.api.core.DevelopmentProposalContract;
import org.kuali.coeus.propdev.api.location.ProposalSiteContract;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonDto;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonService;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Calendar;

@FormGenerator("RRSF424BV1_1Generator")
public class RRSF424BV1_1Generator extends S2SBaseFormGenerator {

    private static final String NON_CONSTRUCTION = "Non-Construction";

    @Autowired
    @Qualifier("departmentalPersonService")
    private DepartmentalPersonService departmentalPersonService;

    @Override
    public AssurancesDocument getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) throws S2SException {
        AssurancesDocument assurcesDocument = AssurancesDocument.Factory.newInstance();
        DevelopmentProposalContract propDevFormBean = proposalDevelopmentDocument.getDevelopmentProposal();
        AssuranceType rrSF424B = assurcesDocument.addNewAssurances();
        rrSF424B.setFormVersionIdentifier(FormVersion.v1_1.getVersion());
        rrSF424B.setProgramType(NON_CONSTRUCTION);
        rrSF424B.setFormVersion(FormVersion.v1_1.getVersion());
        ProposalSiteContract applicantOrganization = propDevFormBean.getApplicantOrganization();
        rrSF424B.setApplicantOrganizationName(applicantOrganization.getOrganization().getOrganizationName());
        rrSF424B.setAuthorizedRepresentative(getAuthorizedRepresentative(proposalDevelopmentDocument));
        rrSF424B.setSubmittedDate(Calendar.getInstance());
        return assurcesDocument;
    }
    /**
     * 
     * This method gets AuthorizedRepresentative details RepresentativeTitle based on ProposalDevelopmentDocumentContract.
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocumentContract)
     * @return AuthorizedRepresentative authorized representative title.
     */
    private AuthorizedRepresentative getAuthorizedRepresentative(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {

        AuthorizedRepresentative authorizedRepresentative = AuthorizedRepresentative.Factory.newInstance();
        DepartmentalPersonDto aorInfo = departmentalPersonService.getDepartmentalPerson(proposalDevelopmentDocument);
        if (aorInfo.getPrimaryTitle() != null) {
            authorizedRepresentative.setRepresentativeTitle(aorInfo.getPrimaryTitle());
        }
        return authorizedRepresentative;
    }

    public DepartmentalPersonService getDepartmentalPersonService() {
        return departmentalPersonService;
    }

    public void setDepartmentalPersonService(DepartmentalPersonService departmentalPersonService) {
        this.departmentalPersonService = departmentalPersonService;
    }
}
