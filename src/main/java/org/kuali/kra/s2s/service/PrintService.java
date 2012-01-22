/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.s2s.service;

import java.util.List;

import org.kuali.kra.bo.SponsorFormTemplate;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;

/**
 * This class contains the PDF form generation related services for a proposal.
 */
public interface PrintService {
	/**
	 * This method is used for the printing of forms in PDF format
	 * 
	 * @param pdDoc(ProposalDevelopmentDocument)
	 * @throws S2SException
	 * 
	 */
	public AttachmentDataSource printForm(ProposalDevelopmentDocument pdDoc)
			throws S2SException, PrintingException;

	/**
	 * 
	 * Prints the proposal sponsor forms by passing the given proposal
	 * information to {@link ProposalPrintReader}
	 * 
	 * @param proposalNumber
	 *            proposal number.
	 * @param sponsorFormTemplates
	 *            list of SponsorFormTemplate.
	 * @return byte array of forms corresponding to the proposal number and
	 *         SponsorFormTemplate objects.
	 * @throws S2SException
	 */
	public byte[] printProposalSponsorForms(String proposalNumber,
			List<SponsorFormTemplate> sponsorFormTemplates) throws S2SException;
}
