/*
 * Copyright 2005-2013 The Kuali Foundation
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import noNamespace.InstituteProposalDocument;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.award.commitments.AwardCostShare;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardUnitContact;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.AwardBasisOfPayment;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.home.AwardMethodOfPayment;
import org.kuali.kra.award.home.AwardStatus;
import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.kra.award.home.AwardTemplateComment;
import org.kuali.kra.award.home.AwardTemplateContact;
import org.kuali.kra.award.home.AwardTemplateReportTerm;
import org.kuali.kra.award.home.AwardTemplateReportTermRecipient;
import org.kuali.kra.award.home.AwardTransferringSponsor;
import org.kuali.kra.award.home.AwardType;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.award.home.ContactType;
import org.kuali.kra.award.home.Distribution;
import org.kuali.kra.award.paymentreports.Frequency;
import org.kuali.kra.award.paymentreports.FrequencyBase;
import org.kuali.kra.award.paymentreports.Report;
import org.kuali.kra.award.paymentreports.ReportClass;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.closeout.AwardCloseout;
import org.kuali.kra.award.paymentreports.closeout.CloseoutReportType;
import org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentSchedule;
import org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.AwardApprovedEquipment;
import org.kuali.kra.award.paymentreports.specialapproval.foreigntravel.AwardApprovedForeignTravel;
import org.kuali.kra.award.printing.AwardPrintParameters;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.bo.CommentType;
import org.kuali.kra.bo.CostShareType;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.ScienceKeyword;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.SponsorType;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.bo.UnitContactType;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.calculator.RateClassType;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetCategory;
import org.kuali.kra.budget.core.CostElement;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.nonpersonnel.BudgetRateAndBase;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.personnel.BudgetPerson;
import org.kuali.kra.budget.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.personnel.BudgetPersonnelDetails;
import org.kuali.kra.budget.personnel.BudgetPersonnelRateAndBase;
import org.kuali.kra.budget.rates.BudgetLaRate;
import org.kuali.kra.budget.rates.RateClass;
import org.kuali.kra.budget.rates.RateType;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalComment;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalCostShare;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalScienceKeyword;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationActivity;
import org.kuali.kra.negotiations.bo.NegotiationActivityType;
import org.kuali.kra.negotiations.bo.NegotiationAssociationType;
import org.kuali.kra.negotiations.bo.NegotiationStatus;
import org.kuali.kra.negotiations.bo.NegotiationUnassociatedDetail;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalBudgetStatus;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.impl.identity.PersonImpl;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;

public class PrintingTestUtils {

	public static String FILE_DIR = System.getProperty("java.io.tmpdir");

	public static void writePdftoDisk(AttachmentDataSource pdfBytes,
			String reportType) {
		if (pdfBytes == null || pdfBytes.getContent() == null) {
			return;
		}
		try {
			FileOutputStream fos = new FileOutputStream(new File(
					PrintingTestUtils.FILE_DIR
					        + File.separator
							+ reportType
							+ "_"
							+ new SimpleDateFormat("ddMMyyyy_HHmmss")
									.format(new java.util.Date()) + ".pdf"));
			fos.write(pdfBytes.getContent());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
