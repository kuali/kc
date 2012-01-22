/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.printing.service;

import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.SponsorFormTemplate;
import org.kuali.kra.bo.SponsorFormTemplateList;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;

/**
 * This class provides the means for printing reports related to Proposal Development. It has
 * the capability to provide a PDF document of various reports related to Proposal Development
 * like Print Certification, Proposal etc.
 * 
 * @author
 * 
 */
public interface ProposalDevelopmentPrintingService {

	public static final String PRINT_CERTIFICATION_REPORT = "Print Certification";
    public static final String PRINT_PROPOSAL_SPONSOR_FORMS = "Print Proposal Sponsor Forms";
    public static final String SELECTED_TEMPLATES = "Selected Templates";
    public static final String PRINT_CERTIFICATION_PERSON = "Print Certification Person";

    /**
	 * This method generates the required report and returns the PDF stream as
	 * {@link AttachmentDataSource}.
	 * 
	 * @param printableBusinessObject
	 *            Document data using which report is generated
	 * @param reportName
	 *            report to be generated
	 * @param reportParameters
	 *            {@link Map} of parameters required for report generation
	 * @return {@link AttachmentDataSource} which contains the byte array of the
	 *         generated PDF
	 * @throws PrintingException
	 *             if any errors occur during report generation
	 */
	AttachmentDataSource printProposalDevelopmentReport(
			KraPersistableBusinessObjectBase printableBusinessObject, String reportName,
			Map<String, Object> reportParameters) throws PrintingException;

    /**
     * 
     * This method is to get templates for generic sponsor code.
     * 
     * @param sponsorFormTemplates
     *            list of SponsorFormTemplateList.
     * @param sponsorCode
     *            code for the sponsor.
     */
    void populateSponsorForms(
            List<SponsorFormTemplateList> sponsorFormTemplates,
            String sponsorCode);

    /**
     * 
     * This method is used to get the sponsor form template form template list.
     * 
     * @param sponsorFormTemplateLists
     *            list of SponsorFormTemplateList.
     * @return List<SponsorFormTemplate> list of SponsorFormTemplate
     *         corresponding to the SponsorFormTemplateList object.
     */
    List<SponsorFormTemplate> getSponsorFormTemplates(
            List<SponsorFormTemplateList> sponsorFormTemplateLists);
	
	
}
