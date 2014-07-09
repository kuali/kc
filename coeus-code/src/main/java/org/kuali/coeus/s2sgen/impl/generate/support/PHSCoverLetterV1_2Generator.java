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
package org.kuali.coeus.s2sgen.impl.generate.support;

import gov.grants.apply.forms.phsCoverLetter12V12.PHSCoverLetter12Document;
import gov.grants.apply.forms.phsCoverLetter12V12.PHSCoverLetter12Document.PHSCoverLetter12;
import gov.grants.apply.forms.phsCoverLetter12V12.PHSCoverLetter12Document.PHSCoverLetter12.CoverLetterFile;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.attachment.NarrativeContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;


@FormGenerator("PHSCoverLetterV1_2Generator")
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
		phsCoverLetter.setFormVersion(FormVersion.v1_2.getVersion());
		AttachedFileDataType attachedFileDataType = null;
		for (NarrativeContract narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeType().getCode() != null
					&& Integer.parseInt(narrative.getNarrativeType().getCode()) == NARRATIVE_PHS_COVER_LETTER) {
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
	 * {@link ProposalDevelopmentDocumentContract}
	 * 
	 * @param proposalDevelopmentDocument
	 *            for which the {@link XmlObject} needs to be created
	 * @return {@link XmlObject} which is generated using the given
	 *         {@link ProposalDevelopmentDocumentContract}
	 */
	public XmlObject getFormObject(
			ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
		return getPHSCoverLetter();
	}
}
