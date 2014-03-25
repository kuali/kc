/*
\ * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.framework.print.util;

import org.kuali.coeus.common.committee.impl.print.CommitteeReportType;
import org.kuali.coeus.common.framework.print.CurrentAndPendingReportService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingType;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.budget.printing.BudgetPrintType;
import org.kuali.kra.coi.print.CoiDisclosureType;
import org.kuali.kra.institutionalproposal.printing.InstitutionalProposalPrintType;
import org.kuali.kra.institutionalproposal.proposallog.service.ProposalLogPrintingService;
import org.kuali.kra.irb.actions.print.ProtocolPrintType;
import org.kuali.kra.negotiations.printing.NegotiationActivityPrintType;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.printing.service.ProposalDevelopmentPrintingService;
import org.kuali.kra.subaward.reporting.printing.SubAwardPrintType;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.location.api.country.Country;
import org.kuali.rice.location.api.country.CountryService;
import org.kuali.rice.location.api.state.State;
import org.kuali.rice.location.api.state.StateService;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrintingUtils {

	private static String XSL_CONTEXT_DIR = "/org/kuali/kra/printing/stylesheet/";
	private static final String XSL_BUDGET_SUMMARY = "BudgetSummaryReport.xsl";
	private static final String XSL_BUDGET_SALARY = "BudgetSalary.xsl";
	private static final String XSL_BUDGET_TOTAL = "BudgetSummaryTotalPage.xsl";
	private static final String XSL_BUDGET_CUMULATIVE = "CumulativeSummary.xsl";
	private static final String XSL_INDUSTRIAL_BUDGET = "IndstlBudgetSummary.xsl";
	private static final String XSL_BUDGET_COSTSHARING_SUMMARY = "CostSharingBudgetSummary.xsl";

	private static final String XSL_AWARD_NOTICE = "AwardNotice.xsl";
	private static final String XSL_AWARD_DELTA = "AwardModification.xsl";
	
    private static final String XSL_AWARD_BUDGET_HIERARCHY = "awardBudgetHierarchy.xsl";
    private static final String XSL_AWARD_BUDGET_HISTORY_TRANSACTION = "awardBudgetModification.xsl";
    private static final String XSL_AWARD_TEMPLATE = "awardTemplate.xsl";
    private static final String XSL_MONEY_AND_END_DATES_HISTORY = "awardMoneyAndEndDatesHistory.xsl";
	
    private static final String XSL_PRINT_CERTIFICATION = "printCertification.xsl";
	private static final String XSL_CURRENT_REPORT = "CurrentSupport.xsl";
	private static final String XSL_PENDING_REPORT = "PendingSupport.xsl";
	private static final String XSL_INSTITUTIONAL_PROPOSAL_REPORT = "instituteProposal.xsl";
    private static final String XSL_COMMITTEE_ROSTER = "CommitteeRoster.xsl";
    private static final String XSL_FUTURE_SCHEDULED_MEETINGS = "CommitteeFutureScheduledMeetings.xsl";
    	private static final String XSL_PROPOSAL_LOG_REPORT = "proposalLog.xsl";
	private static final String PRINCIPAL_INVESTIGATOR = "PI";
	private static final String  XSL_PRINT_NEGOTIATION_ACTIVITY_REPORT ="NegotiationActivityReport.xsl";
	private static final String XSL_PRINT_SUB_AWARD_SF_294_REPORT ="294.xsl";
	private static final String XSL_PRINT_SUB_AWARD_SF_295_REPORT ="295.xsl";
	private static final String XSL_COI_APPROVED_DISCLOSURE = "ApprovedDisclosure.xsl";
    private static final String XSL_AWARD_REPORT_TRACKING = "AwardReportingRequirements.xsl";
	/**
	 * This method fetches system constant parameters
	 * 
	 * @param parameter
	 *            String for which value must be fetched
	 * @return String System constant parameters.
	 * @see org.kuali.kra.s2s.service.S2SUtilService#getParameterValue(java.lang.String)
	 */
	public static String getParameterValue(String parameter) {
		ParameterService parameterService = KcServiceLocator
				.getService(ParameterService.class);
		return parameterService.getParameterValueAsString(
				ProposalDevelopmentDocument.class, parameter);
	}

	/**
	 * 
	 * This method is used if class is not PD. right now is used by AWARD.
	 * @param clazz
	 * @param parameter
	 * @return
	 */
    public static String getParameterValue(Class clazz, String parameter) {
        ParameterService parameterService = KcServiceLocator.getService(ParameterService.class);
        return parameterService.getParameterValueAsString(clazz, parameter);
    }

	/**
	 * This method is to get a State object from the state name
	 * 
	 * @param stateName
	 *            Name of the state
	 * @return State object matching the name.
	 * @see org.kuali.kra.s2s.service.S2SUtilService#getStateFromName(java.lang.String)
	 */
    public static State getStateFromName(String countryAlternateCode, String stateName) {
        Country country = getCountryService().getCountryByAlternateCode(countryAlternateCode);
        
        State state = getStateService().getState(country.getCode(), stateName);
        return state;
    }

    private static CountryService getCountryService() {
        return KcServiceLocator.getService(CountryService.class);
    }
    
	private static StateService getStateService() {
        return KcServiceLocator.getService(StateService.class);
    }

	/**
	 * This method fetches the stylesheet for a given report and returns it as a
	 * {@link Source} in an {@link ArrayList}
	 * 
	 * @param reportType
	 *            report for which stylesheet is to be fetched
	 * @return {@link ArrayList} of stylesheet {@link Source}
	 */

	public static ArrayList<Source> getXSLTforReport(String reportType) {
		String xsl = null;
		if (reportType.equals(AwardPrintType.AWARD_NOTICE_REPORT
				.getAwardPrintType())) {
			xsl = XSL_AWARD_NOTICE;
		} else if (reportType.equals(AwardPrintType.AWARD_DELTA_REPORT
				.getAwardPrintType())) {
			xsl = XSL_AWARD_DELTA;
		} else if (reportType.equals(AwardPrintType.AWARD_BUDGET_HIERARCHY
                .getAwardPrintType())) {
            xsl = XSL_AWARD_BUDGET_HIERARCHY;
        } else if (reportType.equals(AwardPrintType.AWARD_BUDGET_HISTORY_TRANSACTION
                .getAwardPrintType())) {
            xsl = XSL_AWARD_BUDGET_HISTORY_TRANSACTION;
        } else if (reportType.equals(AwardPrintType.AWARD_TEMPLATE
                .getAwardPrintType())) {
            xsl = XSL_AWARD_TEMPLATE;
        } else if (reportType.equals(AwardPrintType.MONEY_AND_END_DATES_HISTORY
                .getAwardPrintType())) {
            xsl = XSL_MONEY_AND_END_DATES_HISTORY;
        } else if (reportType.equals(BudgetPrintType.BUDGET_SUMMARY_REPORT
				.getBudgetPrintType())) {
			xsl = XSL_BUDGET_SUMMARY;
		} else if (reportType.equals(BudgetPrintType.BUDGET_SALARY_REPORT
				.getBudgetPrintType())) {
			xsl = XSL_BUDGET_SALARY;
		} else if (reportType.equals(BudgetPrintType.BUDGET_TOTAL_REPORT
				.getBudgetPrintType())) {
			xsl = XSL_BUDGET_TOTAL;
		} else if (reportType
				.equals(BudgetPrintType.BUDGET_SUMMARY_TOTAL_REPORT
						.getBudgetPrintType())) {
			xsl = XSL_BUDGET_TOTAL;
		} else if (reportType
				.equals(BudgetPrintType.INDUSTRIAL_CUMULATIVE_BUDGET_REPORT
						.getBudgetPrintType())) {
			xsl = XSL_BUDGET_TOTAL;
		} else if (reportType.equals(BudgetPrintType.BUDGET_CUMULATIVE_REPORT
				.getBudgetPrintType())) {
			xsl = XSL_BUDGET_CUMULATIVE;
		} else if (reportType.equals(BudgetPrintType.INDUSTRIAL_BUDGET_REPORT
				.getBudgetPrintType())) {
			xsl = XSL_INDUSTRIAL_BUDGET;
		} else if (reportType
				.equals(BudgetPrintType.BUDGET_COST_SHARE_SUMMARY_REPORT
						.getBudgetPrintType())) {
			xsl = XSL_BUDGET_COSTSHARING_SUMMARY;
		} else if (reportType
				.equals(CurrentAndPendingReportService.CURRENT_REPORT_TYPE)) {
			xsl = XSL_CURRENT_REPORT;
		} else if (reportType
				.equals(CurrentAndPendingReportService.PENDING_REPORT_TYPE)) {
			xsl = XSL_PENDING_REPORT;
		} else if (reportType
				.equals(InstitutionalProposalPrintType.INSTITUTIONAL_PROPOSAL_REPORT
						.getInstitutionalProposalPrintType())) {
			xsl = XSL_INSTITUTIONAL_PROPOSAL_REPORT;
		} else if (reportType.equals(ProposalLogPrintingService.PROPOSAL_LOG_REPORT_TYPE)) {
		    xsl = XSL_PROPOSAL_LOG_REPORT;
		} else if (reportType
				.equals(ProposalDevelopmentPrintingService.PRINT_CERTIFICATION_REPORT)) {
			xsl = XSL_PRINT_CERTIFICATION;
		}  else if (reportType.equals(CoiDisclosureType.APPROVED_DISCLOSURE_TYPE.getCoiDisclosureType())) {
            xsl = XSL_COI_APPROVED_DISCLOSURE;
        } 
		else if (reportType.equals(CommitteeReportType.ROSTER.getCommitteeReportType())) {
            xsl = XSL_COMMITTEE_ROSTER;
        } else if (reportType.equals(CommitteeReportType.FUTURE_SCHEDULED_MEETINGS.getCommitteeReportType())) {
            xsl = XSL_FUTURE_SCHEDULED_MEETINGS;
        } 
        else if (reportType.equals(ReportTrackingType.
                AWARD_REPORT_TRACKING.getReportTrackingType())) {
            xsl = XSL_AWARD_REPORT_TRACKING;
        } 
        else if (reportType
                .equals(NegotiationActivityPrintType.NEGOTIATION_ACTIVITY_REPORT
                        .getNegotiationActivityPrintType())) {
            xsl = XSL_PRINT_NEGOTIATION_ACTIVITY_REPORT;
        }
        else if (reportType
                .equals(SubAwardPrintType.SUB_AWARD_SF_294_PRINT_TYPE
                        .getSubAwardPrintType())) {
            xsl = XSL_PRINT_SUB_AWARD_SF_294_REPORT;
        }
        else if (reportType
                .equals(SubAwardPrintType.SUB_AWARD_SF_295_PRINT_TYPE
                        .getSubAwardPrintType())) {
            xsl = XSL_PRINT_SUB_AWARD_SF_295_REPORT;
        }
        else if (ProtocolPrintType.getReportTypes().contains(reportType)) {
            for (ProtocolPrintType protocolPrintType : ProtocolPrintType.values()) {
                if (reportType.equals(protocolPrintType.getProtocolPrintType())) {
                    xsl = protocolPrintType.getTemplate();
                    break;
                }
            }
        }

		Source src = new StreamSource(new PrintingUtils().getClass()
				.getResourceAsStream(XSL_CONTEXT_DIR + "/" + xsl));

		ArrayList<Source> sourceList = new ArrayList<Source>();
		sourceList.add(src);
		return sourceList;
	}

    public static ArrayList<Source> getXSLTforReportTemplate(String reportTemplate) {
        Source src = new StreamSource(new PrintingUtils().getClass()
                .getResourceAsStream(XSL_CONTEXT_DIR + "/" + reportTemplate));

        ArrayList<Source> sourceList = new ArrayList<Source>();
        sourceList.add(src);
        return sourceList;
    }
	
	/**
	 * This method is to get a Country object from the country code
	 * 
	 * @param countryCode
	 *            country code for the country.
	 * @return Country object matching the code
	 * @see org.kuali.kra.s2s.service.S2SUtilService#getCountryFromCode(java.lang.String)
	 */
	public static Country getCountryFromCode(String countryCode,
			BusinessObjectService businessObjectService) {
	    CountryService countryService = KcServiceLocator.getService(CountryService.class);
	    Country country = countryService.getCountryByAlternateCode(countryCode);
		return country;
	}
	
	/**
     * This method is to get PrincipalInvestigator for a given Proposal Development Document
     * 
     * @param proposalPersons Proposal development document.
     * @return ProposalPerson PrincipalInvestigator for the proposal.
     * @see org.kuali.kra.s2s.service.S2SUtilService#getPrincipalInvestigator(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public static ProposalPerson getPrincipalInvestigator(List<ProposalPerson> proposalPersons) {
        ProposalPerson proposalPerson = null;
        if (proposalPersons != null) {
            for (ProposalPerson person : proposalPersons) {
                if (person.getProposalPersonRoleId().equals(PRINCIPAL_INVESTIGATOR)) {
                    proposalPerson = person;
                }
            }
        }
        return proposalPerson;
    }
	
    /*
     * This method is copied from KraTransactionalDocumentBase.   It is referenced by meeting.
     * TODO : refactor other references from KraTransactionalDocumentBase to this method ?
     */
    public static void streamToResponse(AttachmentDataSource attachmentDataSource,
            HttpServletResponse response) throws Exception {
        byte[] xbts = attachmentDataSource.getContent();
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream(xbts.length);
            baos.write(xbts);

            WebUtils.saveMimeOutputStreamAsFile(response, attachmentDataSource.getContentType(), baos, attachmentDataSource.getFileName());

        } finally {
            try {
                if (baos != null) {
                    baos.close();
                    baos = null;
                }
            } catch (IOException ioEx) {
                // LOG.warn(ioEx.getMessage(), ioEx);
            }
        }
    }

    public static void streamToResponse(byte[] fileContents, String fileName, String fileContentType, HttpServletResponse response) throws Exception {
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream(fileContents.length);
            baos.write(fileContents);
            WebUtils.saveMimeOutputStreamAsFile(response, fileContentType, baos, fileName);
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                    baos = null;
                }
            } catch (IOException ioEx) {
                throw new RuntimeException("IOException occurred while downloading attachment", ioEx);
            }
        }
    }

}
