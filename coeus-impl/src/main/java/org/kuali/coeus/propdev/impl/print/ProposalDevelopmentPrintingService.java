/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.print;

import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.sponsor.form.SponsorFormTemplate;
import org.kuali.coeus.common.framework.sponsor.form.SponsorFormTemplateList;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;

import java.util.List;
import java.util.Map;

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
			KcPersistableBusinessObjectBase printableBusinessObject, String reportName,
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
     * @return List&lt;SponsorFormTemplate&gt; list of SponsorFormTemplate
     *         corresponding to the SponsorFormTemplateList object.
     */
    List<SponsorFormTemplate> getSponsorFormTemplates(
            List<SponsorFormTemplateList> sponsorFormTemplateLists);
	
    AttachmentDataSource printPersonCertificationQuestionnaire(List<ProposalPerson> person) throws PrintingException;
	
}
