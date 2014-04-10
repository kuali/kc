/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.print;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.PrintingService;
import org.kuali.coeus.common.framework.sponsor.form.SponsorFormTemplate;
import org.kuali.coeus.common.framework.sponsor.form.SponsorFormTemplateList;
import org.kuali.coeus.common.framework.sponsor.form.SponsorForms;
import org.kuali.coeus.common.framework.sponsor.hierarchy.SponsorHierarchy;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.kra.proposaldevelopment.printing.print.ProposalSponsorFormsPrint;
import org.kuali.kra.proposaldevelopment.questionnaire.ProposalPersonQuestionnaireHelper;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.print.QuestionnairePrint;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import java.util.*;

/**
 * This class is the implementation of
 * {@link ProposalDevelopmentPrintingService}. It has capability to print any
 * reports related to Proposal Development like Print Certification, Proposal
 * etc.
 * 
 */

@Component("proposalDevelopmentPrintingService")
public class ProposalDevelopmentPrintingServiceImpl implements
		ProposalDevelopmentPrintingService {
	
	private static final String SPONSOR_CODE_DB_KEY = "sponsorCode";
	
	@Autowired
	@Qualifier("printCertificationPrint")
	private PrintCertificationPrint printCertificationPrint;
	
	@Autowired
	@Qualifier("proposalSponsorFormsPrint")
	private ProposalSponsorFormsPrint proposalSponsorFormsPrint;
	
	@Autowired
	@Qualifier("printingService")
	private PrintingService printingService;
	
	@Autowired
	@Qualifier("businessObjectService")
	private BusinessObjectService businessObjectService;
	
	@Autowired
	@Qualifier("parameterService")
	private ParameterService parameterService;

	@Autowired
	@Qualifier("questionnairePrint")
	private QuestionnairePrint questionnairePrint;

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
	        KcPersistableBusinessObjectBase printableBusinessObject, String reportName,
			Map<String, Object> reportParameters) throws PrintingException {
		AttachmentDataSource source = null;
		AbstractPrint printable = null;
		if (reportName.equals(PRINT_CERTIFICATION_REPORT)) {
			printable = getPrintCertificationPrint();
		} else if (reportName.equals(PRINT_PROPOSAL_SPONSOR_FORMS)) {
			printable = getProposalSponsorFormsPrint();
		}

		printable.setPrintableBusinessObject(printableBusinessObject);
		printable.setReportParameters(reportParameters);
		source = getPrintingService().print(printable);
		source.setFileName(getReportName(printableBusinessObject, reportName));
		source.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
		return source;
	}

	protected String getReportName(KcPersistableBusinessObjectBase printableBusinessObject,
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
        if (!sponsorFormTemplates.isEmpty()) {
            if (StringUtils.equalsIgnoreCase(sponsorCode, 
                    sponsorFormTemplates.get(0).getSponsorForms().getSponsorCode())) {
                sponsorFormTemplates.clear();
            }
            sponsorFormTemplates.clear();
            
        }

        if (sponsorFormTemplates.isEmpty()) {
            Collection<SponsorFormTemplateList> clsponsorFormTemplates = getSponsorTemplatesList(sponsorCode);
            sponsorFormTemplates.addAll(clsponsorFormTemplates);
            if(!getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.LOCAL_PRINT_FORM_SPONSOR_CODE).equals(sponsorCode)){
                String genericSponsorCode = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.GENERIC_SPONSOR_CODE);
                clsponsorFormTemplates = getSponsorTemplatesList(genericSponsorCode);
            }
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
        
        String hierarchyName = getParameterService().getParameterValueAsString(
                Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
                Constants.SPONSOR_HIERARCHY_PRINTING_NAME_PARAM);
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
    
    public AttachmentDataSource printPersonCertificationQuestionnaire(List<ProposalPerson> persons) throws PrintingException {
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        List<Printable> printables = new ArrayList<Printable> ();
        
        for (ProposalPerson person : persons) {
            ProposalPersonQuestionnaireHelper helper = new ProposalPersonQuestionnaireHelper(person);
            AnswerHeader header = helper.getAnswerHeaders().get(0);            
            reportParameters.put("questionnaireId", header.getQuestionnaire().getQuestionnaireIdAsInteger());
            reportParameters.put("template", header.getQuestionnaire().getTemplate());  
            AbstractPrint printable = getQuestionnairePrint();
            if (printable != null) {
            	printable.setPrintableBusinessObject(person);
            	printable.setReportParameters(reportParameters);
            }
            printables.add(printable);
        }
        return getPrintingService().print(printables);

    }

	/**
	 * @return the printingService
	 */
	protected PrintingService getPrintingService() {
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
	 * Gets the businessObjectService attribute.
	 * 
	 * @return Returns the businessObjectService.
	 */
	protected BusinessObjectService getBusinessObjectService() {
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

    protected ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    protected QuestionnairePrint getQuestionnairePrint() {
		return questionnairePrint;
	}
    
    public void setQuestionnairePrint(QuestionnairePrint questionnairePrint) {
		this.questionnairePrint = questionnairePrint;
	}

	
}
