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
package org.kuali.kra.proposaldevelopment.printing.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.SponsorFormTemplate;
import org.kuali.kra.bo.SponsorFormTemplateList;
import org.kuali.kra.bo.SponsorForms;
import org.kuali.kra.bo.SponsorHierarchy;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.printing.print.PrintCertificationPrint;
import org.kuali.kra.proposaldevelopment.printing.print.ProposalSponsorFormsPrint;
import org.kuali.kra.proposaldevelopment.printing.service.ProposalDevelopmentPrintingService;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.ParameterService;

/**
 * This class is the implementation of
 * {@link ProposalDevelopmentPrintingService}. It has capability to print any
 * reports related to Proposal Development like Print Certification, Proposal
 * etc.
 * 
 */

public class ProposalDevelopmentPrintingServiceImpl implements
		ProposalDevelopmentPrintingService {
	private PrintCertificationPrint printCertificationPrint;
	private ProposalSponsorFormsPrint proposalSponsorFormsPrint;
	private PrintingService printingService;
	private S2SUtilService s2SUtilService;
	private BusinessObjectService businessObjectService;
	private ParameterService parameterService;
	private static final String SPONSOR_CODE_DB_KEY = "sponsorCode";
	private static final String PAGE_NUMBER_DB_KEY = "pageNumber";

	/**
	 * This method generates the required report and returns the PDF stream as
	 * {@link AttachmentDataSource}. It first identifies the report type to be
	 * printed, then fetches the required report generator. The report generator
	 * generates XML which is then passed to {@link PrintingService} for
	 * transforming into PDF.
	 * 
	 * @param document
	 *            Document data using which report is generated
	 * @param reportName
	 *            report to be generated
	 * @param reportParameters
	 *            {@link Map} of parameters required for report generation
	 * @return {@link AttachmentDataSource} which contains the byte array of the
	 *         generated PDF
	 * @throws PrintingException
	 *             if any errors occur during report generation
	 * 
	 */
	public AttachmentDataSource printProposalDevelopmentReport(
	        KraPersistableBusinessObjectBase printableBusinessObject, String reportName,
			Map<String, Object> reportParameters) throws PrintingException {
		AttachmentDataSource source = null;
		AbstractPrint printable = null;
		if (reportName.equals(PRINT_CERTIFICATION_REPORT)) {
			printable = getPrintCertificationPrint();
		} else if (reportName.equals(PRINT_PROPOSAL_SPONSOR_FORMS)) {
			printable = getProposalSponsorFormsPrint();
		}
		// TODO provide the XMLStream Object before printing.

		printable.setPrintableBusinessObject(printableBusinessObject);
		printable.setReportParameters(reportParameters);
		source = getPrintingService().print(printable);
		source.setFileName(getReportName(printableBusinessObject, reportName));
		source.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
		return source;
	}

	protected String getReportName(KraPersistableBusinessObjectBase printableBusinessObject,
			String reportName) {
		DevelopmentProposal developmentProposal = (DevelopmentProposal) printableBusinessObject;
		String proposalNumber = developmentProposal.getProposalNumber();

		StringBuilder reportFullName = new StringBuilder(proposalNumber)
				.append("_").append(reportName.replace(' ', '_')).append(
						Constants.PDF_FILE_EXTENSION);
		return reportFullName.toString();
	}
	
    /**
     * 
     * This method is to get templates for generic sponsor code.
     * 
     * @param sponsorFormTemplates
     *            list of SponsorFormTemplateList.
     * @param sponsorCode
     *            code for the sponsor.
     */
    public void populateSponsorForms(
            List<SponsorFormTemplateList> sponsorFormTemplates,
            String sponsorCode) {
        // check if sponsor forms isEmpty
        if (!sponsorFormTemplates.isEmpty()) {
            // if exists - check if sponsor code has changed
            if (StringUtils.equalsIgnoreCase(sponsorCode, 
                    sponsorFormTemplates.get(0).getSponsorForms().getSponsorCode())) {
                sponsorFormTemplates.clear();
            }
        }

        if (sponsorFormTemplates.isEmpty()) {
            // fetch all templates for proposal sponsor code
            Collection<SponsorFormTemplateList> clsponsorFormTemplates = getSponsorTemplatesList(sponsorCode);
            sponsorFormTemplates.addAll(clsponsorFormTemplates);
            // fetch all templates for generic sponsor code
            String genericSponsorCode = s2SUtilService
                    .getParameterValue(Constants.GENERIC_SPONSOR_CODE);
            clsponsorFormTemplates = getSponsorTemplatesList(genericSponsorCode);
            sponsorFormTemplates.addAll(clsponsorFormTemplates);
        } else {
            resetSelectedFormList(sponsorFormTemplates);
        }
        Set set = new HashSet();
        set.addAll(sponsorFormTemplates);
        sponsorFormTemplates.clear();
        sponsorFormTemplates.addAll(set);
        Collections.sort(sponsorFormTemplates);
    }	
	
    /**
     * 
     * This method is used to get the Sponsor template list for the given
     * sponsor code.
     * 
     * @param sponsorCode
     *            code number of the sponsor.
     * @return Collection<SponsorFormTemplateList> collection of
     *         SponsorFormTemplateList for the given sponsor code.
     */
    protected Collection<SponsorFormTemplateList> getSponsorTemplatesList(
            String sponsorCode) {
        Map<String, String> sponsorCodeMap = new HashMap<String, String>();
        sponsorCodeMap.put(SPONSOR_CODE_DB_KEY, sponsorCode);
        Collection<SponsorForms> sponsorForms = getBusinessObjectService()
                .findMatching(SponsorForms.class, sponsorCodeMap);
        List<SponsorFormTemplateList> retval = new ArrayList<SponsorFormTemplateList>();
        for (SponsorForms sponsorForm : sponsorForms) {
            retval.addAll(sponsorForm.getSponsorFormTemplates());
        }
        
        /*String hierarchyName = getParameterService().getParameterValue(
                Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
                Constants.SPONSOR_HIERARCHY_PRINTING_NAME_PARAM);*/
        String hierarchyName = "";
        sponsorCodeMap.put(Constants.HIERARCHY_NAME, hierarchyName);
        SponsorHierarchy hierarchyEntry = (SponsorHierarchy) getBusinessObjectService().findByPrimaryKey(SponsorHierarchy.class, sponsorCodeMap);
        if (hierarchyEntry != null) {
            sponsorCodeMap.clear();
            sponsorCodeMap.put("sponsorHierarchyName", hierarchyEntry.getLevel1());
            sponsorForms = getBusinessObjectService()
                    .findMatching(SponsorForms.class, sponsorCodeMap);
            for (SponsorForms sponsorForm : sponsorForms) {
                retval.addAll(sponsorForm.getSponsorFormTemplates());
            }
        }
        return retval;
    }	

    /**
     * 
     * This method is to reset the selected form list.
     * 
     * @param sponsorFormTemplates
     *            list of SponsorFormTemplateList.
     */
    protected void resetSelectedFormList(
            List<SponsorFormTemplateList> sponsorFormTemplates) {
        for (SponsorFormTemplateList sponsorFormTemplateList : sponsorFormTemplates) {
            sponsorFormTemplateList.setSelectToPrint(false);
        }
    }

    /**
     * This method gets the sponsor form template from the given sponsor form
     * template list
     * 
     * @param sponsorFormTemplateLists -
     *            list of sponsor form template list
     * @return list of sponsor form template
     */
    public List<SponsorFormTemplate> getSponsorFormTemplates(
            List<SponsorFormTemplateList> sponsorFormTemplateLists) {
        List<SponsorFormTemplate> printFormTemplates = new ArrayList<SponsorFormTemplate>();
        for (SponsorFormTemplateList sponsorFormTemplateList : sponsorFormTemplateLists) {
            if (sponsorFormTemplateList.getSelectToPrint()) {
                printFormTemplates.add(getBusinessObjectService().findBySinglePrimaryKey(SponsorFormTemplate.class, sponsorFormTemplateList.getSponsorFormTemplateId()));
            }
        }

        Collections.sort(printFormTemplates);
        resetSelectedFormList(sponsorFormTemplateLists);
        return printFormTemplates;
    }

	/**
	 * @return the printingService
	 */
	public PrintingService getPrintingService() {
		return printingService;
	}

	/**
	 * @param printingService
	 *            the printingService to set
	 */
	public void setPrintingService(PrintingService printingService) {
		this.printingService = printingService;
	}

	public PrintCertificationPrint getPrintCertificationPrint() {
		return printCertificationPrint;
	}

	public void setPrintCertificationPrint(
			PrintCertificationPrint printCertificationPrint) {
		this.printCertificationPrint = printCertificationPrint;
	}

	/**
	 * Gets the s2SUtilService attribute.
	 * 
	 * @return Returns the s2SUtilService.
	 */
	public S2SUtilService getS2SUtilService() {
		return s2SUtilService;
	}

	/**
	 * Sets the s2SUtilService attribute value.
	 * 
	 * @param utilService
	 *            The s2SUtilService to set.
	 */
	public void setS2SUtilService(S2SUtilService utilService) {
		s2SUtilService = utilService;
	}

	/**
	 * Gets the businessObjectService attribute.
	 * 
	 * @return Returns the businessObjectService.
	 */
	public BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	/**
	 * Sets the businessObjectService attribute value.
	 * 
	 * @param businessObjectService
	 *            The businessObjectService to set.
	 */
	public void setBusinessObjectService(
			BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	/**
	 * Gets the printProposalSponsorForms attribute.
	 * 
	 * @return Returns the printProposalSponsorForms.
	 */
	public ProposalSponsorFormsPrint getProposalSponsorFormsPrint() {
		return proposalSponsorFormsPrint;
	}

	/**
	 * Sets the printProposalSponsorForms attribute value.
	 * 
	 * @param printProposalSponsorForms
	 *            The printProposalSponsorForms to set.
	 */
	public void setProposalSponsorFormsPrint(
			ProposalSponsorFormsPrint proposalSponsorFormsPrint) {
		this.proposalSponsorFormsPrint = proposalSponsorFormsPrint;
	}

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
