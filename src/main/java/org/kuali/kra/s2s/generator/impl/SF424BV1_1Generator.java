/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.s2s.generator.impl;


import gov.grants.apply.forms.sf424BV11.AssuranceType;
import gov.grants.apply.forms.sf424BV11.AssurancesDocument;
import gov.grants.apply.forms.sf424BV11.AuthorizedRepresentativeDocument.AuthorizedRepresentative;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * This Class is used to generate XML object for grants.gov SF424BV1.1. This form is generated using XMLBean classes and is based on
 * SF424BV1.1 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SF424BV1_1Generator extends SF424BaseGenerator {


    /**
     * 
     * This method returns AssurancesDocument object based on proposal development document which contains all the information for a
     * particular proposal
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return assuranceDocument {@link XmlObject} of type AssurancesDocument.
     */
    private AssurancesDocument getAssurance() {

        AssurancesDocument assuranceDocument = AssurancesDocument.Factory.newInstance();
        assuranceDocument.setAssurances(getAssuranceType());
        return assuranceDocument;
    }

    /**
     * 
     * This method gets AssuranceType details based on proposal development document.AssuranceType type includes details like
     * ProgramType,AuthorizedRepresentative,ApplicantOrganizationName.
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return AssuranceType object containing information about ApplicantOrganizationName,ProgramType,AuthorizedRepresentative
     */
    private AssuranceType getAssuranceType() {

        AssuranceType assuranceType = AssuranceType.Factory.newInstance();
        assuranceType.setFormVersionIdentifier(S2SConstants.FORMVERSION_1_1);
        assuranceType.setProgramType(PROGRAM_TYPE);
        assuranceType.setCoreSchemaVersion(S2SConstants.CORE_SCHEMA_VERSION_1_1);
        assuranceType.setAuthorizedRepresentative(getAuthorizedRepresentative());
        Organization organization = pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization();
        assuranceType.setApplicantOrganizationName(organization.getOrganizationName());

        return assuranceType;
    }

    /**
     * 
     * This method gets AuthorizedRepresentative details RepresentativeTitle based on ProposalDevelopmentDocument.
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return AuthorizedRepresentative authorized representative title.
     */
    private AuthorizedRepresentative getAuthorizedRepresentative() {

        AuthorizedRepresentative authorizedRepresentative = AuthorizedRepresentative.Factory.newInstance();
        DepartmentalPerson aorInfo = s2sUtilService.getDepartmentalPerson(pdDoc);
        if (aorInfo.getPrimaryTitle() != null) {
            authorizedRepresentative.setRepresentativeTitle(aorInfo.getPrimaryTitle());
        }
        return authorizedRepresentative;
    }

    /**
     * This method creates {@link XmlObject} of type {@link AssurancesDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getAssurance();
    }

    /**
     * This method typecasts the given {@link XmlObject} to the required generator type and returns back the document of that
     * generator type.
     * 
     * @param xmlObject which needs to be converted to the document type of the required generator
     * @return {@link XmlObject} document of the required generator type
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(XmlObject)
     */
    public XmlObject getFormObject(XmlObject xmlObject) {
        AssuranceType assuranceType = (AssuranceType) xmlObject;
        AssurancesDocument assurancesDocument = AssurancesDocument.Factory.newInstance();
        assurancesDocument.setAssurances(assuranceType);
        return assurancesDocument;
    }
}
