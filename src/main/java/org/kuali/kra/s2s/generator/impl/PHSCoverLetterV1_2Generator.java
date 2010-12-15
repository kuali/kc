/*
 * Copyright 2005-2010 The Kuali Foundation.
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

import gov.grants.apply.forms.phsCoverLetter12V12.PHSCoverLetter12Document;
import gov.grants.apply.forms.phsCoverLetter12V12.PHSCoverLetter12Document.PHSCoverLetter12;
import gov.grants.apply.forms.phsCoverLetter12V12.PHSCoverLetter12Document.PHSCoverLetter12.CoverLetterFile;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.util.S2SConstants;

public class PHSCoverLetterV1_2Generator extends PHSCoverLetterBaseGenerator {

	/**
	 * This method is used to get PHSCoverLetter12Document attachment from the
	 * narrative attachments.
	 * 
	 * @return phsCoverLetter12Document {@link XmlObject} of type
	 *         PHS398CoverLetterDocument.
	 */
	private PHSCoverLetter12Document getPHSCoverLetter() {

		PHSCoverLetter12Document phsCoverLetterDocument = PHSCoverLetter12Document.Factory
				.newInstance();
		PHSCoverLetter12 phsCoverLetter = PHSCoverLetter12.Factory
				.newInstance();
        CoverLetterFile coverLetterFile = CoverLetterFile.Factory.newInstance();
		phsCoverLetter.setFormVersion(S2SConstants.FORMVERSION_1_2);
		AttachedFileDataType attachedFileDataType = null;
		for (Narrative narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeTypeCode() != null
					&& Integer.parseInt(narrative.getNarrativeTypeCode()) == NARRATIVE_PHS_COVER_LETTER) {
				attachedFileDataType = getAttachedFileType(narrative);
				if(attachedFileDataType != null){
					coverLetterFile.setCoverLetterFilename(attachedFileDataType);
					break;
				}
			}
		}
        phsCoverLetter.setCoverLetterFile(coverLetterFile);
		phsCoverLetterDocument.setPHSCoverLetter12(phsCoverLetter);
		return phsCoverLetterDocument;
	}

	/**
	 * This method creates {@link XmlObject} of type
	 * {@link PHSCoverLetter12Document} by populating data from the given
	 * {@link ProposalDevelopmentDocument}
	 * 
	 * @param proposalDevelopmentDocument
	 *            for which the {@link XmlObject} needs to be created
	 * @return {@link XmlObject} which is generated using the given
	 *         {@link ProposalDevelopmentDocument}
	 * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
	 */
	public XmlObject getFormObject(
			ProposalDevelopmentDocument proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
		return getPHSCoverLetter();
	}

	/**
	 * This method typecasts the given {@link XmlObject} to the required
	 * generator type and returns back the document of that generator type.
	 * 
	 * @param xmlObject
	 *            which needs to be converted to the document type of the
	 *            required generator
	 * @return {@link XmlObject} document of the required generator type
	 * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(XmlObject)
	 */
	public XmlObject getFormObject(XmlObject xmlObject) {
		PHSCoverLetter12 phsCoverLetter12 = (PHSCoverLetter12) xmlObject;
		PHSCoverLetter12Document phsCoverLetter12Document = PHSCoverLetter12Document.Factory
				.newInstance();
		phsCoverLetter12Document.setPHSCoverLetter12(phsCoverLetter12);
		return phsCoverLetter12Document;
	}

}
