/*
 * Copyright 2005-2014 The Kuali Foundation.
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

import gov.grants.apply.forms.phs398CoverLetterV11.PHS398CoverLetterDocument;
import gov.grants.apply.forms.phs398CoverLetterV11.PHS398CoverLetterDocument.PHS398CoverLetter;
import gov.grants.apply.forms.phs398CoverLetterV11.PHS398CoverLetterDocument.PHS398CoverLetter.CoverLetterFile;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * 
 * Class for generating the XML object for grants.gov PHS398CoverLetterV1_1. Form is generated using XMLBean classes and is based on
 * PHS398CoverLetterV1_1 schema
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class PHS398CoverLetterV1_1Generator extends PHS398CoverLetterBaseGenerator {

    /**
     * 
     * This method is used to get PHS398CoverLetter attachment from the narrative attachments.
     * 
     * @return phsCoverLetterDocument {@link XmlObject} of type PHS398CoverLetterDocument.
     */
    private PHS398CoverLetterDocument getPHSCoverLetter() {

        PHS398CoverLetterDocument phsCoverLetterDocument = PHS398CoverLetterDocument.Factory.newInstance();
        PHS398CoverLetter phsCoverLetter = PHS398CoverLetter.Factory.newInstance();
        CoverLetterFile coverLetterFile = CoverLetterFile.Factory.newInstance();
        phsCoverLetter.setFormVersion(S2SConstants.FORMVERSION_1_1);
        AttachedFileDataType attachedFileDataType = null;
        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null && Integer.parseInt(narrative.getNarrativeTypeCode()) == PHS_COVER_LETTER) {
                attachedFileDataType = getAttachedFileType(narrative);
                if(attachedFileDataType != null){
                	coverLetterFile.setCoverLetterFilename(attachedFileDataType);
                	break;
                }
            }
        }
        phsCoverLetter.setCoverLetterFile(coverLetterFile);
        phsCoverLetterDocument.setPHS398CoverLetter(phsCoverLetter);
        return phsCoverLetterDocument;
    }

    /**
     * This method creates {@link XmlObject} of type {@link PHS398CoverLetterDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getPHSCoverLetter();
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
        PHS398CoverLetter phsCoverLetter = (PHS398CoverLetter) xmlObject;
        PHS398CoverLetterDocument phsCoverLetterDocument = PHS398CoverLetterDocument.Factory.newInstance();
        phsCoverLetterDocument.setPHS398CoverLetter(phsCoverLetter);
        return phsCoverLetterDocument;
    }
}
