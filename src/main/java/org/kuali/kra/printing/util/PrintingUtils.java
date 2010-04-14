/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.printing.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.bo.CommentType;
import org.kuali.kra.bo.Country;
import org.kuali.kra.bo.State;
import org.kuali.kra.budget.printing.BudgetPrintType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.printing.InstitutionalProposalPrintType;
import org.kuali.kra.printing.service.CurrentAndPendingReportService;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.printing.service.ProposalDevelopmentPrintingService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.ParameterService;

public class PrintingUtils {

	private static final String KEY_COUNTRY_CODE = "countryCode";
	private static final String KEY_STATE_CODE = "stateCode";
	private static final String PARAMETER_MODULE_PROPOSAL_DEVELOPMENT = "KC-PD";
	private static final String PARAMETER_COMPONENT_DOCUMENT = "A";
	private static String XSL_CONTEXT_DIR = "/org/kuali/kra/printing/stylesheet/";
	private static final String XSL_BUDGET_SUMMARY = "BudgetSummaryReport.xsl";
	private static final String XSL_BUDGET_SALARY = "BudgetSalary.xsl";
	private static final String XSL_BUDGET_TOTAL = "BudgetSummaryTotalPage.xsl";
	private static final String XSL_BUDGET_CUMULATIVE = "CumulativeSummary.xsl";
	private static final String XSL_INDUSTRIAL_BUDGET = "IndstlBudgetSummary.xsl";
	private static final String XSL_BUDGET_COSTSHARING_SUMMARY = "CostSharingBudgetSummary.xsl";
	private static final String XSL_AWARD_NOTICE = "awardNotice.xsl";
	private static final String XSL_BAWARD_DELTA = "awardModification.xsl";
	private static final String XSL_PRINT_CERTIFICATION = "printCertification.xsl";
	private static final String XSL_CURRENT_REPORT = "CurrentSupport.xsl";
	private static final String XSL_PENDING_REPORT = "PendingSupport.xsl";
	private static final String XSL_INSTITUTIONAL_PROPOSAL_REPORT = "instituteProposal.xsl";
	private static final String PRINCIPAL_INVESTIGATOR = "PI";
	private static final String COMMENT_TYPE_CODE_PARAMETER = "commentTypeCode";

	/**
	 * This method fetches system constant parameters
	 * 
	 * @param parameter
	 *            String for which value must be fetched
	 * @return String System constant parameters.
	 * @see org.kuali.kra.s2s.service.S2SUtilService#getParameterValue(java.lang.String)
	 */
	public static String getParameterValue(String parameter) {
		ParameterService parameterService = KraServiceLocator
				.getService(ParameterService.class);
		return parameterService.getParameterValue(
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
        ParameterService parameterService = KraServiceLocator.getService(ParameterService.class);
        return parameterService.getParameterValue(clazz, parameter);
    }

	/**
	 * This method is to get a State object from the state name
	 * 
	 * @param stateName
	 *            Name of the state
	 * @return State object matching the name.
	 * @see org.kuali.kra.s2s.service.S2SUtilService#getStateFromName(java.lang.String)
	 */
	public static State getStateFromName(String stateName,
			BusinessObjectService businessObjectService) {
		State state = null;
		Map<String, String> stateMap = new HashMap<String, String>();
		stateMap.put(KEY_STATE_CODE, stateName);
		Iterator<State> stateList = businessObjectService.findMatching(
				State.class, stateMap).iterator();
		while (stateList.hasNext()) {
			state = stateList.next();
			if (state.getStateCode().equals(stateName)) {
				break;
			}
		}
		return state;
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
			xsl = XSL_BAWARD_DELTA;
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
		} else if (reportType
				.equals(ProposalDevelopmentPrintingService.PRINT_CERTIFICATION_REPORT)) {
			xsl = XSL_PRINT_CERTIFICATION;
		}

		Source src = new StreamSource(new PrintingUtils().getClass()
				.getResourceAsStream(XSL_CONTEXT_DIR + "/" + xsl));

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
		Map<String, String> countryMap = new HashMap<String, String>();
		countryMap.put(KEY_COUNTRY_CODE, countryCode);
		Country country = (Country) businessObjectService.findByPrimaryKey(
				Country.class, countryMap);
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

	/**
	 * 
	 * This method will get the comment type description for given comment type
	 * code
	 * 
	 * @param commentTypeCode
	 *            Code for which description will be fetched
	 * @param businessObjectService
	 *            {@link BusinessObjectService}
	 * @return String Comment Type Description
	 */
	public static String getCommentTypeDescription(String commentTypeCode,
			BusinessObjectService businessObjectService) {
		String description = null;
		Map<String, String> commentTypeCodeMap = new HashMap<String, String>();
		commentTypeCodeMap.put(COMMENT_TYPE_CODE_PARAMETER, commentTypeCode);
		CommentType commentType = (CommentType) businessObjectService
				.findByPrimaryKey(org.kuali.kra.bo.CommentType.class,
						commentTypeCodeMap);
		if (commentType != null) {
			description = commentType.getDescription();
		}
		return description;
	}
}
