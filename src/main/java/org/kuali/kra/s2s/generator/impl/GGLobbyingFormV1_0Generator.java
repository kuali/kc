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

import gov.grants.apply.forms.ggLobbyingFormV10.LobbyingFormDocument;
import gov.grants.apply.forms.ggLobbyingFormV10.LobbyingFormDocument.LobbyingForm;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * Class for generating the XML object for grants.gov GGLobbyingFormV1_0. Form is generated using XMLBean classes and is based on
 * GGLobbyingForm schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class GGLobbyingFormV1_0Generator extends GGLobbyingFormBaseGenerator {

    /**
     * 
     * This method is used to get Lobbying form information such as Applicant Name, Authorized Representative
     * Title,Name,Signature,SubmittedDate.
     * 
     * @return lobbyingFormDocument(LobbyingFormDocument) {@link XmlObject} of type LobbyingFormDocument.
     */
    private LobbyingFormDocument getLobbyingForm() {

        LobbyingFormDocument lobbyingFormDocument = LobbyingFormDocument.Factory.newInstance();
        LobbyingForm lobbyingForm = LobbyingForm.Factory.newInstance();
        lobbyingForm.setFormVersion(S2SConstants.FORMVERSION_1_0);
        Organization organization = null;
        String applicantName = EMPTY_STRING;
        String authorizedRepresentativeTitle = EMPTY_STRING;
        String authorizedRepresentativeSignature = EMPTY_STRING;

        if (pdDoc.getOrganization() != null) {
            organization = pdDoc.getOrganization();
        }
        if (organization != null && organization.getOrganizationName() != null) {
            if (organization.getOrganizationName().length() > ORGANIZATON_NAME_MAX_LENGTH) {
                applicantName = organization.getOrganizationName().substring(0, 120);
            }
            else {
                applicantName = organization.getOrganizationName();
            }
        }

        DepartmentalPerson departmentalPerson = s2sUtilService.getDepartmentalPerson(pdDoc);
        if (departmentalPerson != null) {
            if (departmentalPerson.getPrimaryTitle() != null) {
                if (departmentalPerson.getPrimaryTitle().length() > PRIMARY_TITLE_MAX_LENGTH) {
                    authorizedRepresentativeTitle = departmentalPerson.getPrimaryTitle().substring(0, PRIMARY_TITLE_MAX_LENGTH);
                }
                else {
                    authorizedRepresentativeTitle = departmentalPerson.getPrimaryTitle();
                }
            }
            if (departmentalPerson.getFullName() != null) {
                authorizedRepresentativeSignature = departmentalPerson.getFullName();
            }
        }

        lobbyingForm.setApplicantName(applicantName);
        lobbyingForm.setAuthorizedRepresentativeTitle(authorizedRepresentativeTitle);
        lobbyingForm.setAuthorizedRepresentativeSignature(authorizedRepresentativeSignature);
        lobbyingForm.setAuthorizedRepresentativeName(globLibV10Generator.getHumanNameDataType(departmentalPerson));
        lobbyingForm.setSubmittedDate(s2sUtilService.getCurrentCalendar());
        lobbyingFormDocument.setLobbyingForm(lobbyingForm);
        return lobbyingFormDocument;
    }

    /**
     * This method creates {@link XmlObject} of type {@link LobbyingFormDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getLobbyingForm();
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

        LobbyingForm lobbyingForm = (LobbyingForm) xmlObject;
        LobbyingFormDocument lobbyingFormDocument = LobbyingFormDocument.Factory.newInstance();
        lobbyingFormDocument.setLobbyingForm(lobbyingForm);
        return lobbyingFormDocument;
    }
}
