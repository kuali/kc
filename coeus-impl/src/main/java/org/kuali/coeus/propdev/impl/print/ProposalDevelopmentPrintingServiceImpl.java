/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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

import org.apache.commons.beanutils.PropertyUtils;
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
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.question.ProposalPersonQuestionnaireHelper;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireConstants;
import org.kuali.coeus.common.questionnaire.framework.print.QuestionnairePrint;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
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
@Lazy
public class ProposalDevelopmentPrintingServiceImpl implements
		ProposalDevelopmentPrintingService {

    private static final String SPONSOR_CODE_DB_KEY = "sponsorCode";
    public static final String TEMPLATE = "template";
    public static final String SPONSOR_HIERARCHY_NAME = "sponsorHierarchyName";

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
		source.setName(getReportName(printableBusinessObject, reportName));
		source.setType(Constants.PDF_REPORT_CONTENT_TYPE);
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
     * @return Collection&lt;SponsorFormTemplateList&gt; collection of
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
            sponsorCodeMap.put(SPONSOR_HIERARCHY_NAME, hierarchyEntry.getLevel1());
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
            helper.populateAnswers();
            AnswerHeader header = helper.getAnswerHeaders().get(0);            
            reportParameters.put(QuestionnaireConstants.QUESTIONNAIRE_SEQUENCE_ID_PARAMETER_NAME, header.getQuestionnaire().getQuestionnaireSeqIdAsInteger());
            reportParameters.put(TEMPLATE, header.getQuestionnaire().getTemplate());
            AbstractPrint printable = new QuestionnairePrint();
            try {
                PropertyUtils.copyProperties(printable,getQuestionnairePrint());
            } catch (Exception e) {
                throw new RuntimeException("error copying questionnaire print",e);
            }

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
	 * Sets the proposalSponsorFormsPrint attribute value.
	 * 
	 * @param proposalSponsorFormsPrint
	 *            The proposalSponsorFormsPrint to set.
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
