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

import gov.grants.apply.forms.edCertificationDebarmentV11.CertificationDebarmentDocument;
import gov.grants.apply.forms.edCertificationDebarmentV11.CertificationDebarmentDocument.CertificationDebarment;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * Class for generating the XML object for grants.gov EDcertificationDebarmentV1_1. Form is generated using XMLBean classes and is
 * based on EDCertificationDebarment schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class EDCertificationDebarmentV1_1Generator extends S2SBaseFormGenerator {
    private static final int ED_CERTIFICATION_DEBARMENT = 58;
    S2SUtilService s2sUtilService;

    /**
     * 
     * Constructs a EDCertificationDebarmentV1_1Generator.java.
     */
    public EDCertificationDebarmentV1_1Generator() {
        s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
    }

    /**
     * 
     * This method is used to get CertificationDebarment information such as Organization Name,Authorized Representative
     * Title,Authorized Representative Name,Signature and Attachment for this form.
     * 
     * @return certificationDebarmentDocument(CertificationDebarmentDocument){@link XmlObject} of type
     *         CertificationDebarmentDocument.
     */
    private CertificationDebarmentDocument getCertificationDebarment() {

        CertificationDebarmentDocument certificationDebarmentDocument = CertificationDebarmentDocument.Factory.newInstance();
        CertificationDebarment certificationDebarment = CertificationDebarment.Factory.newInstance();
        certificationDebarment.setFormVersion(S2SConstants.FORMVERSION_1_1);
        Organization organization = null;
        if (pdDoc.getOrganization() != null) {
            organization = pdDoc.getOrganization();
        }
        if (organization != null && organization.getOrganizationName() != null) {
            if (organization.getOrganizationName().length() > 60) {
                certificationDebarment.setOrganizationName(organization.getOrganizationName().substring(0, 60));
            }
            else {
                certificationDebarment.setOrganizationName(organization.getOrganizationName());
            }
        }
        DepartmentalPerson departmentalPerson = s2sUtilService.getDepartmentalPerson(pdDoc);
        String authorizedRepresentativeTitle = "";
        String authorizedRepresentativeSignature = "";

        if (departmentalPerson != null) {
            if (departmentalPerson.getPrimaryTitle() != null && !departmentalPerson.getPrimaryTitle().equals("")) {
                if (departmentalPerson.getPrimaryTitle().length() > 45) {
                    authorizedRepresentativeTitle = departmentalPerson.getPrimaryTitle().substring(0, 45);
                }
                else {
                    authorizedRepresentativeTitle = departmentalPerson.getPrimaryTitle();
                }
            }

            if (departmentalPerson.getFullName() != null && !departmentalPerson.getFullName().equals("")) {
                authorizedRepresentativeSignature = departmentalPerson.getFullName();
            }
        }

        certificationDebarment.setAuthorizedRepresentativeTitle(authorizedRepresentativeTitle);
        certificationDebarment.setAuthorizedRepresentativeName(globLibV20Generator.getHumanNameDataType(departmentalPerson));
        certificationDebarment.setAuthorizedRepresentativeSignature(authorizedRepresentativeSignature);
        certificationDebarment.setSubmittedDate(s2sUtilService.getCurrentCalendar());

        for (Narrative narrative : pdDoc.getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null
                    && Integer.parseInt(narrative.getNarrativeTypeCode()) == ED_CERTIFICATION_DEBARMENT) {
                AttachedFileDataType attachedFileDataType = AttachedFileDataType.Factory.newInstance();
                attachedFileDataType = getAttachedFileType(narrative);
                certificationDebarment.setAttachment(attachedFileDataType);
            }
        }
        certificationDebarmentDocument.setCertificationDebarment(certificationDebarment);
        return certificationDebarmentDocument;
    }

    /**
     * This method creates {@link XmlObject} of type {@link CertificationDebarmentDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getCertificationDebarment();
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

        CertificationDebarment certificationDebarment = (CertificationDebarment) xmlObject;
        CertificationDebarmentDocument certificationDebarmentDocument = CertificationDebarmentDocument.Factory.newInstance();
        certificationDebarmentDocument.setCertificationDebarment(certificationDebarment);
        return certificationDebarmentDocument;
    }
}
