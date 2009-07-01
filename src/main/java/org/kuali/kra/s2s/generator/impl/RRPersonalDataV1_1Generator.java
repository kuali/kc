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

import gov.grants.apply.forms.rrPersonalDataV11.DirectorType;
import gov.grants.apply.forms.rrPersonalDataV11.RRPersonalDataDocument;
import gov.grants.apply.forms.rrPersonalDataV11.RRPersonalDataDocument.RRPersonalData;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * Class for generating the XML object for grants.gov RRPersonalDataV1.1. Form is generated using XMLBean classes and is based on
 * RRPersonalData schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RRPersonalDataV1_1Generator extends RRPersonalDataBaseGenerator {

    private static final Logger LOG = Logger.getLogger(RRPersonalDataV1_1Generator.class);

    /**
     * 
     * This method gives the personal information of ProjectDirector and CoProjectDirector
     * 
     * @return rrPersonalDataDocument {@link XmlObject} of type RRPersonalDataDocument.
     */
    private RRPersonalDataDocument getRRPersonalData() {

        RRPersonalDataDocument rrPersonalDataDocument = RRPersonalDataDocument.Factory.newInstance();
        RRPersonalData rrPersonalData = RRPersonalData.Factory.newInstance();
        rrPersonalData.setFormVersion(S2SConstants.FORMVERSION_1_1);
        rrPersonalData.setProjectDirector(getProjectDirectorType());
        rrPersonalData.setCoProjectDirectorArray(getCoProjectDirectoryType());
        rrPersonalDataDocument.setRRPersonalData(rrPersonalData);
        return rrPersonalDataDocument;
    }

    /**
     * 
     * This method is used to get Personal details of Principal Investigator
     * 
     * @return DirectorType principal investigator details.
     */
    private DirectorType getProjectDirectorType() {

        DirectorType directorType = DirectorType.Factory.newInstance();
        ProposalPerson PI = s2sUtilService.getPrincipalInvestigator(pdDoc);
        if (PI != null) {
            directorType.setName(globLibV20Generator.getHumanNameDataType(PI));
        }
        return directorType;
    }

    /**
     * 
     * This method is used to get List of Personal details of Co-Investigator
     * 
     * @return DirectorType[] Array of director types.
     */
    private DirectorType[] getCoProjectDirectoryType() {
        DirectorType[] directorTypes = new DirectorType[0];
        List<DirectorType> directorTypeList = new ArrayList<DirectorType>();
        if (pdDoc.getProposalPersons() != null) {
            ProposalPerson CoPI = null;
            for (ProposalPerson proposalPerson : pdDoc.getProposalPersons()) {
                DirectorType coDirectorType = DirectorType.Factory.newInstance();
                if (proposalPerson.getProposalPersonRoleId() != null) {
                    if (KEYPERSON_TYPE_C0_INVESTIGATOR.equals(proposalPerson.getProposalPersonRoleId())) {
                        CoPI = proposalPerson;
                        coDirectorType.setName(globLibV20Generator.getHumanNameDataType(CoPI));
                        directorTypeList.add(coDirectorType);
                    }
                }
            }
        }
        directorTypes = directorTypeList.toArray(directorTypes);
        return directorTypes;
    }

    /**
     * This method creates {@link XmlObject} of type {@link RRPersonalDataDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     * 
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getRRPersonalData();
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
        RRPersonalData rrPersonalData = (RRPersonalData) xmlObject;
        RRPersonalDataDocument rrPersonalDataDocument = RRPersonalDataDocument.Factory.newInstance();
        rrPersonalDataDocument.setRRPersonalData(rrPersonalData);
        return rrPersonalDataDocument;
    }
}
