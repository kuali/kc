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

import gov.grants.apply.forms.cd511V11.CD511Document;
import gov.grants.apply.forms.cd511V11.CD511Document.CD511;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * 
 * This class is used to generate XML Document object for grants.gov CD511V1.1. This form is generated using XMLBean API's generated
 * by compiling CD511V1_1 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class CD511V1_1Generator extends S2SBaseFormGenerator {

    private S2SUtilService s2sUtilService;
    private DepartmentalPerson aorInfo;

    /**
     * 
     * Constructs a CD511V1_1Generator.java.
     */
    public CD511V1_1Generator() {
        s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
    }

    /**
     * 
     * This method returns CD511Document object based on proposal development document which contains the CD511Document informations
     * OrganizationName,AwardNumber,ProjectName,ContactName,Title,Signature,SubmittedDate for a particular proposal
     * 
     * @return cd511Document(CD511Document){@link XmlObject} of type CD511Document.
     */
    private CD511Document getcd511Document() {
        CD511Document cd511Document = CD511Document.Factory.newInstance();
        CD511 cd511 = CD511.Factory.newInstance();
        cd511.setFormVersion(S2SConstants.FORMVERSION_1_1);

        if (pdDoc.getDevelopmentProposal().getApplicantOrganization() != null) {
            cd511.setOrganizationName(pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization().getOrganizationName());
        }
        if (pdDoc.getDevelopmentProposal().getCurrentAwardNumber() != null && !pdDoc.getDevelopmentProposal().getCurrentAwardNumber().equals("")) {
            cd511.setAwardNumber(pdDoc.getDevelopmentProposal().getCurrentAwardNumber());
        }
        if (pdDoc.getDevelopmentProposal().getTitle() != null && !pdDoc.getDevelopmentProposal().getTitle().equals("")) {
            cd511.setProjectName(pdDoc.getDevelopmentProposal().getTitle());
        }

        String title = "";
        if (aorInfo != null) {
            cd511.setContactName(globLibV20Generator.getHumanNameDataType(aorInfo));
            if (aorInfo.getPrimaryTitle() != null && !aorInfo.getPrimaryTitle().equals("")) {
                title = aorInfo.getPrimaryTitle();
            }
        }
        cd511.setTitle(title);

        // As per Coeus,CD511-V1.1 data analysis file said:
        // if this application is submitted through Grants.gov leave signature to blank
        cd511.setSignature("  ");
        cd511.setSubmittedDate(s2sUtilService.getCurrentCalendar());
        cd511Document.setCD511(cd511);
        return cd511Document;
    }

    /**
     * This method creates {@link XmlObject} of type {@link CD511Document} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        aorInfo = s2sUtilService.getDepartmentalPerson(pdDoc);
        return getcd511Document();
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
        CD511Document cd511Document = CD511Document.Factory.newInstance();
        CD511 cd511 = (CD511) xmlObject;
        cd511Document.setCD511(cd511);
        return cd511Document;
    }

}
