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
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kim.bo.impl.PersonImpl;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.bo.DocumentHeader;
import org.kuali.rice.kns.document.authorization.PessimisticLock;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;

public class PrintingTestUtils {
	private static final String OBLIGATED_DIRECT_INDIRECT_COST_CONSTANT = "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST";
	public static String FILE_DIR = System.getProperty("java.io.tmpdir");
	private static final String REPORTING = "reporting";
	private static final String TECHNICAL_REPORTING = "technicalReporting";
	private static final String TERMS = "terms";
	private static final String SPECIAL_REVIEW = "specialReview";
	private static final String SUBCONTRACT = "subcontract";
	private static final String SCIENCE_CODE = "scienceCode";
	private static final String PROPOSAL_DUE = "proposalDue";
	private static final String PAYMENT = "payment";
	private static final String INDIRECT_COST = "indirectCost";
	private static final String HIERARCHY_INFO = "hierarchyInfo";
	private static final String FOREIGN_TRAVEL = "foreignTravel";
	private static final String FLOW_THRU = "flowThru";
	private static final String EQUIPMENT = "equipment";
	private static final String COST_SHARING = "costSharing";
	private static final String COMMENTS = "comments";
	private static final String CLOSEOUT = "closeout";
	private static final String ADDRESS_LIST = "addressList";
	private static final String SIGNATURE_REQUIRED = "signatureRequired";
	private static final String SEQUENCE_NUMBER = "sequenceNumber";
	private static final String AMOUNT_SEQUENCE_NUMBER = "amountSequenceNumber";
	private static final String TRANSACTION_ID = "transactionId";
	private static final String PERSON_ID = "personId";
	private static DocumentService documentService;
	private static BusinessObjectService businessObjectService;

	private static DocumentService getDocumentService() {
		if (documentService == null) {
			documentService = KraServiceLocator
					.getService(DocumentService.class);
		}
		return documentService;
	}

	private static BusinessObjectService getBusinessObjectService() {
		if (businessObjectService == null) {
			businessObjectService = KraServiceLocator
					.getService(BusinessObjectService.class);
		}
		return businessObjectService;
	}

	public static AwardDocument getAwardDocument() {
		GlobalVariables.setUserSession(new UserSession("quickstart"));
		AwardDocument doc = null;
		try {
			doc = (AwardDocument) getDocumentService().getNewDocument(
					AwardDocument.class);
		} catch (WorkflowException e) {
			throw new RuntimeException(e);
		}
		doc.getDocumentHeader().setDocumentDescription("Test document");
		Award award = doc.getAward();
		doc.setAward(getAwardOne(award));

		// doc.setAward(getAwardTwo());
		doc.setDocumentNumber("1001");
		doc.setVersionNumber(new Long(1));
		doc.setUpdateTimestamp(new Timestamp(Calendar.getInstance()
				.getTimeInMillis()));
		doc.setUpdateUser("TEST");
		getBusinessObjectService().save(doc);
		return doc;
	}

	private static Award getAwardTwo() {
		Award award = new Award();
		award.setAwardId(21L);
		award.setAwardNumber("000044-00001");
		award.setSequenceNumber(2);
		award.setSponsorCode("sponsorCode");
		award.setStatusCode(2);
		AwardStatus awardStatus = new AwardStatus();
		awardStatus.setStatusCode("2");
		awardStatus.setDescription("Test Description");
		award.setAwardStatus(awardStatus);
		award.setAccountNumber("2342342");
		award.setApprovedEquipmentIndicator("Y");
		award.setApprovedForeignTripIndicator("Y");
		award.setSubContractIndicator("Y");
		Calendar calendar = Calendar.getInstance();
		calendar.set(2009, 07, 28);
		award.setAwardEffectiveDate(new Date(calendar.getTimeInMillis()));
		calendar.set(2009, 07, 29);
		award.setAwardExecutionDate(new Date(calendar.getTimeInMillis()));
		calendar.set(2009, 07, 27);
		award.setBeginDate(new Date(calendar.getTimeInMillis()));
		award.setCostSharingIndicator("1");
		award.setIndirectCostIndicator("1");
		award.setModificationNumber("2134");
		award.setNsfCode("J.02");
		award.setPaymentScheduleIndicator("1");
		award.setScienceCodeIndicator("1");
		award.setSpecialReviewIndicator("1");
		award.setSponsorAwardNumber("213");
		award.setTransferSponsorIndicator("1");
		award.setAccountTypeCode(2);
		award.setActivityTypeCode("1");
		award.setAwardTypeCode(6);
		award.setCfdaNumber("334");
		award.setDocumentFundingId("23");
		award.setPreAwardAuthorizedAmount(new KualiDecimal(34));
		calendar.set(2009, 07, 30);
		award.setPreAwardEffectiveDate(new Date(calendar.getTimeInMillis()));
		award.setPreAwardInstitutionalAuthorizedAmount(new KualiDecimal(34));
		calendar.set(2009, 07, 30);
		award.setPreAwardInstitutionalEffectiveDate(new Date(calendar
				.getTimeInMillis()));
		award.setProcurementPriorityCode("1");
		award.setProposalNumber("10");
		award.setSpecialEbRateOffCampus(new KualiDecimal(34));
		award.setSpecialEbRateOnCampus(new KualiDecimal(34));
		award.setSubPlanFlag("true");
		award.setTitle("Award Title");
		award.setArchiveLocation("c:/docs");
		calendar.set(2009, 07, 30);
		award.setCloseoutDate(new Date(calendar.getTimeInMillis()));
		award.setIdcIndicator("Y");
		award.setPaymentScheduleIndicator("1");
		award.setCostSharingIndicator("Y");
		award.setTemplateCode(2);
		Frequency frequency = new Frequency();
		frequency.setDescription("Frequency 123");
		award.setBasisOfPaymentCode("basis of  payment code 123");
		award.setMethodOfPaymentCode("method Of Payment Code 123");
		AwardTemplate awardTemplate = new AwardTemplate();
		awardTemplate.setDescription("Award Template Description 123");
		award.setAwardTemplate(awardTemplate);
		AwardBasisOfPayment awardBasisOfPayment = new AwardBasisOfPayment();
		awardBasisOfPayment.setDescription("basis of payment desc 123");
		award.setAwardBasisOfPayment(awardBasisOfPayment);
		AwardMethodOfPayment awardMethodOfPayment = new AwardMethodOfPayment();
		awardMethodOfPayment.setDescription("award method of payment desc 123");
		Sponsor sponsor = new Sponsor();
		sponsor.setSponsorCode("005948");
		sponsor.setSponsorName("sponsor123");
		award.setSponsor(sponsor);
		Sponsor primeSponsor = new Sponsor();
		primeSponsor.setSponsorCode("005948");
		primeSponsor.setSponsorName("sponsor123");
		award.setSponsor(primeSponsor);
		award.setNsfCode("C1");
		AwardComment awardComment = new AwardComment();
		awardComment.setComments("Award cost Comment 123");
		CommentType commentType = new CommentType();
		commentType.setDescription("comment type desc 123");
		awardComment.setCommentType(commentType);
		awardComment.setCommentTypeCode("9");
		awardComment.setAwardNumber(award.getAwardNumber());
		awardComment.setSequenceNumber(award.getSequenceNumber());
		AwardComment indirectAwardComment = new AwardComment();
		indirectAwardComment.setComments("Indirect cost Comment 123");
		CommentType indirectCommentType = new CommentType();
		indirectCommentType.setDescription("comment type desc 123");
		indirectAwardComment.setCommentType(indirectCommentType);
		indirectAwardComment.setCommentTypeCode("8");
		indirectAwardComment.setAwardNumber(award.getAwardNumber());
		indirectAwardComment.setSequenceNumber(award.getSequenceNumber());
		List<AwardComment> awardComments = new ArrayList<AwardComment>();
		awardComments.add(awardComment);
		awardComments.add(indirectAwardComment);
		award.setAwardComments(awardComments);
		List<AwardReportTerm> awardReportTermItems = new ArrayList<AwardReportTerm>();
		AwardReportTerm awardReportTerm = new AwardReportTerm();
		awardReportTerm.setSequenceNumber(1);
		awardReportTerm.setReportClassCode("1");
		ReportClass reportClass = new ReportClass();
		reportClass.setDescription("description");
		awardReportTerm.setReportClass(reportClass);
		awardReportTerm.setReportCode("1");
		Report report = new Report();
		report.setReportCode("1");
		report.setDescription("Report Desc");
		awardReportTerm.setReport(report);
		awardReportTerm.setFrequencyCode("1");
		awardReportTerm.setFrequencyBaseCode("1");
		Frequency frequency2 = new Frequency();
		frequency2.setFrequencyCode("1");
		frequency2.setDescription("Frequency desc");
		awardReportTerm.setFrequency(frequency2);
		FrequencyBase frequencyBase = new FrequencyBase();
		frequencyBase.setFrequencyBaseCode("1");
		frequencyBase.setDescription("Frequency base desc");
		awardReportTerm.setFrequencyBase(frequencyBase);
		awardReportTerm.setOspDistributionCode("1");
		Distribution distribution = new Distribution();
		distribution.setOspDistributionCode("1");
		distribution.setDescription("OSP DESC");
		awardReportTerm.setDueDate(new Date(System.currentTimeMillis()));
		awardReportTermItems.add(awardReportTerm);
		award.setAwardReportTermItems(awardReportTermItems);
		Unit unit = new Unit();
		Organization organization = new Organization();
		organization.setOrganizationName("Kuali org");
		organization.setOrganizationId("1");
		unit.setOrganization(organization);
		unit.setOrganizationId("1");
		// FIXME: Kim migration
		KcPerson person = new KcPerson();
		// person.setUnit(unit);
		// person.setFullName("first last");
		person.setPersonId("1");
		// person.setOfficeLocation("abc");
		NonOrganizationalRolodex rolodex = new NonOrganizationalRolodex();
		rolodex.setAddressLine1("address line 1");
		rolodex.setAddressLine2("address Line 2");
		rolodex.setAddressLine3("address Line 3");
		rolodex.setCity("BLR");
		rolodex.setComments("Comments one ");
		rolodex.setCountryCode("USA");
		rolodex.setCounty("USA");
		rolodex.setCreateUser("Quick start");
		rolodex.setEmailAddress("abs@abs.com");
		rolodex.setFaxNumber("12431234");
		rolodex.setFirstName("First ");
		rolodex.setLastName("Last Name");
		rolodex.setMiddleName("Middle");
		rolodex.setOrganization("Kuali");
		rolodex.setPhoneNumber("2354234634754");
		rolodex.setPostalCode("423412");
		rolodex.setRolodexId(1);
		rolodex.setSponsor(sponsor);
		rolodex.setSponsorCode("2");
		rolodex.setSponsorAddressFlag(true);
		rolodex.setState("AK");
		rolodex.setTitle("Rolodex Title");
		rolodex.setSuffix("suffix");
		rolodex.setPrefix("preffix");
		rolodex.setUnit(unit);
		rolodex.setUpdateUser("quickstart");
		rolodex.setVersionNumber(1L);
		List<AwardUnitContact> awardUnitContacts = new ArrayList<AwardUnitContact>();
		AwardUnitContact administrator = new AwardUnitContact();
		administrator.setUnitContactType(UnitContactType.ADMINISTRATOR);
		administrator.setRoleCode("2");
		administrator.setPerson(person);
		awardUnitContacts.add(administrator);
		AwardUnitContact piAwardUnitContact = new AwardUnitContact();
		piAwardUnitContact.setPerson(person);
		ContactType contactType = new ContactType();
		contactType.setContactTypeCode(ContactRole.PI_CODE);
		piAwardUnitContact.setContactRole(contactType);
		piAwardUnitContact.setContactRoleCode(ContactRole.PI_CODE);
		piAwardUnitContact.setRoleCode("2");
		piAwardUnitContact.setRolodex(rolodex);
		awardUnitContacts.add(piAwardUnitContact);
		AwardUnitContact awardUnitContact = new AwardUnitContact();
		awardUnitContact.setPerson(person);
		ContactType coiContact = new ContactType();
		coiContact.setContactTypeCode(ContactRole.COI_CODE);
		awardUnitContact.setContactRole(coiContact);
		awardUnitContact.setContactRoleCode(ContactRole.COI_CODE);
		awardUnitContact.setRoleCode("2");
		awardUnitContact.setRolodex(rolodex);
		award.setAwardUnitContacts(awardUnitContacts);
		AwardUnitContact keyPersonAwardUnitContact = new AwardUnitContact();
		keyPersonAwardUnitContact.setPerson(person);
		ContactType keyContact = new ContactType();
		keyContact.setContactTypeCode(ContactRole.KEY_PERSON_CODE);
		keyPersonAwardUnitContact.setContactRole(keyContact);
		keyPersonAwardUnitContact
				.setContactRoleCode(ContactRole.KEY_PERSON_CODE);
		keyPersonAwardUnitContact.setRoleCode("2");
		keyPersonAwardUnitContact.setRolodex(rolodex);
		awardUnitContacts.add(keyPersonAwardUnitContact);
		AwardAmountInfo awardAmountInfo = new AwardAmountInfo();
		awardAmountInfo.setSequenceNumber(1);
		awardAmountInfo.setTransactionId(2L);
		awardAmountInfo.setAmountObligatedToDate(new KualiDecimal(2000L));
		awardAmountInfo.setAntDistributableAmount(new KualiDecimal(3000L));
		awardAmountInfo.setAnticipatedChange(new KualiDecimal(23L));
		awardAmountInfo.setAnticipatedChangeDirect(new KualiDecimal(2L));
		awardAmountInfo.setAnticipatedChangeIndirect(new KualiDecimal(3L));
		awardAmountInfo.setAnticipatedTotalAmount(new KualiDecimal(3000L));
		awardAmountInfo.setAnticipatedTotalDirect(new KualiDecimal(2));
		awardAmountInfo.setAnticipatedTotalIndirect(new KualiDecimal(3));
		awardAmountInfo.setAwardAmountInfoId(2312L);
		awardAmountInfo.setAwardNumber(award.getAwardNumber());
		awardAmountInfo.setSequenceNumber(1);
		awardAmountInfo.setCurrentFundEffectiveDate(new Date(System
				.currentTimeMillis()));
		awardAmountInfo.setEntryType(true);
		awardAmountInfo.setEomProcessFlag(true);
		awardAmountInfo.setFinalExpirationDate(new Date(System
				.currentTimeMillis()));
		awardAmountInfo.setObliDistributableAmount(new KualiDecimal(3000L));
		awardAmountInfo.setObligatedChange(new KualiDecimal(324L));
		awardAmountInfo.setObligatedChangeDirect(new KualiDecimal(234L));
		awardAmountInfo.setObligatedChangeIndirect(new KualiDecimal(3434L));
		awardAmountInfo.setObligatedTotalDirect(new KualiDecimal(3));
		awardAmountInfo.setObligatedTotalIndirect(new KualiDecimal(34));
		awardAmountInfo.setObligationExpirationDate(new Date(System
				.currentTimeMillis()));
		awardAmountInfo.setTransactionId(2L);
		awardAmountInfo.setUpdateUser("quick start");
		awardAmountInfo.setUpdateTimestamp(new Timestamp(System
				.currentTimeMillis()));
		awardAmountInfo.setVersionNumber(1L);
		AwardAmountInfo awardAmountInfoForNextSeq = new AwardAmountInfo();
		awardAmountInfoForNextSeq.setSequenceNumber(1);
		awardAmountInfoForNextSeq.setTransactionId(2L);
		awardAmountInfoForNextSeq.setAmountObligatedToDate(new KualiDecimal(
				2000L));
		awardAmountInfoForNextSeq.setAntDistributableAmount(new KualiDecimal(
				3000L));
		awardAmountInfoForNextSeq.setAnticipatedChange(new KualiDecimal(23L));
		awardAmountInfoForNextSeq.setAnticipatedChangeDirect(new KualiDecimal(2L));
		awardAmountInfoForNextSeq.setAnticipatedChangeIndirect(new KualiDecimal(3L));
		awardAmountInfoForNextSeq.setAnticipatedTotalAmount(new KualiDecimal(
				3000L));
		awardAmountInfoForNextSeq
				.setAnticipatedTotalDirect(new KualiDecimal(2));
		awardAmountInfoForNextSeq.setAnticipatedTotalIndirect(new KualiDecimal(
				3));
		awardAmountInfoForNextSeq.setAwardAmountInfoId(2312L);
		awardAmountInfoForNextSeq.setAwardNumber(award.getAwardNumber());
		awardAmountInfoForNextSeq.setSequenceNumber(1);
		awardAmountInfoForNextSeq.setCurrentFundEffectiveDate(new Date(System
				.currentTimeMillis()));
		awardAmountInfoForNextSeq.setEntryType(true);
		awardAmountInfoForNextSeq.setEomProcessFlag(true);
		awardAmountInfoForNextSeq.setFinalExpirationDate(new Date(System
				.currentTimeMillis()));
		awardAmountInfoForNextSeq.setObliDistributableAmount(new KualiDecimal(
				3000L));
		awardAmountInfoForNextSeq.setObligatedChange(new KualiDecimal(324L));
		awardAmountInfoForNextSeq.setObligatedChangeDirect(new KualiDecimal(234L));
		awardAmountInfoForNextSeq.setObligatedChangeIndirect(new KualiDecimal(3434L));
		awardAmountInfoForNextSeq.setObligatedTotalDirect(new KualiDecimal(3));
		awardAmountInfoForNextSeq
				.setObligatedTotalIndirect(new KualiDecimal(34));
		awardAmountInfoForNextSeq.setObligationExpirationDate(new Date(System
				.currentTimeMillis()));
		awardAmountInfoForNextSeq.setTransactionId(2L);
		awardAmountInfoForNextSeq.setUpdateUser("quick start");
		awardAmountInfoForNextSeq.setUpdateTimestamp(new Timestamp(System
				.currentTimeMillis()));
		awardAmountInfoForNextSeq.setVersionNumber(1L);
		List<AwardAmountInfo> awardAmountInfos = new ArrayList<AwardAmountInfo>();
		awardAmountInfos.add(awardAmountInfoForNextSeq);
		awardAmountInfos.add(awardAmountInfo);
		award.setAwardAmountInfos(awardAmountInfos);
		AwardCloseout awardCloseout = new AwardCloseout();
		awardCloseout.setAwardNumber(award.getAwardNumber());
		awardCloseout.setSequenceNumber(award.getSequenceNumber());
		awardCloseout.setAwardCloseoutId(1L);
		awardCloseout.setCloseoutReportCode("1");
		awardCloseout.setFinalSubmissionDate(new Date(System
				.currentTimeMillis()));
		awardCloseout.setCloseoutReportName("Test closeout report");
		awardCloseout.setUpdateUser("quick start");
		awardCloseout.setVersionNumber(1L);
		awardCloseout.setDueDate(new Date(System.currentTimeMillis()));
		awardCloseout.setUpdateTimestamp(new Timestamp(System
				.currentTimeMillis()));
		CloseoutReportType closeoutReportType = new CloseoutReportType();
		closeoutReportType.setCloseoutReportCode("1");
		closeoutReportType.setDescription("Close out desc");
		awardCloseout.setCloseoutReportType(closeoutReportType);
		List<AwardCloseout> awardCloseoutItems = new ArrayList<AwardCloseout>();
		awardCloseoutItems.add(awardCloseout);
		award.setAwardCloseoutItems(awardCloseoutItems);
		AwardPerson awardPerson = new AwardPerson();
		awardPerson.setVersionNumber(1L);
		awardPerson.setUpdateUser("quick start");
		awardPerson
				.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
		awardPerson.setFaculty(true);
		awardPerson.setRoleCode("1");
		ContactType keyPersonContactType = new ContactType();
		keyPersonContactType.setContactTypeCode(ContactRole.KEY_PERSON_CODE);
		awardPerson.setContactRole(keyPersonContactType);
		awardPerson.setPerson(person);
		awardPerson.setTotalEffort(new KualiDecimal(23234));
		AwardPerson piAwardPerson = new AwardPerson();
		piAwardPerson.setVersionNumber(1L);
		piAwardPerson.setUpdateUser("quick start");
		piAwardPerson.setUpdateTimestamp(new Timestamp(System
				.currentTimeMillis()));
		piAwardPerson.setFaculty(true);
		piAwardPerson.setRoleCode("1");
		ContactType piCodeContactType = new ContactType();
		piCodeContactType.setContactTypeCode(ContactRole.PI_CODE);
		piAwardPerson.setContactRole(piCodeContactType);
		piAwardPerson.setPerson(person);
		piAwardPerson.setTotalEffort(new KualiDecimal(23234));
		award.add(piAwardPerson);
		AwardTransferringSponsor awardTransferringSponsor = new AwardTransferringSponsor();
		awardTransferringSponsor.setAwardNumber(award.getAwardNumber());
		awardTransferringSponsor.setSequenceNumber(award.getSequenceNumber());
		awardTransferringSponsor.setAwardTransferringSponsorId(23);
		awardTransferringSponsor.setSponsor(sponsor);
		awardTransferringSponsor.setSponsorCode("2");
		List<AwardTransferringSponsor> awardTransferringSponsors = new ArrayList<AwardTransferringSponsor>();
		awardTransferringSponsors.add(awardTransferringSponsor);
		award.setAwardTransferringSponsors(awardTransferringSponsors);
		AwardPaymentSchedule awardPaymentSchedule = new AwardPaymentSchedule();
		awardPaymentSchedule.setAwardNumber(award.getAwardNumber());
		awardPaymentSchedule.setSequenceNumber(award.getSequenceNumber());
		awardPaymentSchedule.setDueDate(new Date(System.currentTimeMillis()));
		awardPaymentSchedule.setAmount(new KualiDecimal(234));
		awardPaymentSchedule.setStatusDescription("payment schedule 123");
		awardPaymentSchedule.setInvoiceNumber("3");
		awardPaymentSchedule.setStatus("active");
		awardPaymentSchedule
				.setSubmitDate(new Date(System.currentTimeMillis()));
		awardPaymentSchedule.setSubmittedBy("Gving");
		awardPaymentSchedule.setUpdateUser("quick start");
		List<AwardPaymentSchedule> paymentScheduleItems = new ArrayList<AwardPaymentSchedule>();
		paymentScheduleItems.add(awardPaymentSchedule);
		award.setPaymentScheduleItems(paymentScheduleItems);
		AwardApprovedForeignTravel approvedForeignTravel = new AwardApprovedForeignTravel();
		approvedForeignTravel.setAmount(23423.32);
		approvedForeignTravel.setAwardNumber(award.getAwardNumber());
		approvedForeignTravel.setSequenceNumber(award.getSequenceNumber());
		approvedForeignTravel.setPersonId("234");
		approvedForeignTravel.setTravelerName("XYZ");
		approvedForeignTravel.setDestination("BLR");
		approvedForeignTravel
				.setStartDate(new Date(System.currentTimeMillis()));
		approvedForeignTravel.setEndDate(new Date(System.currentTimeMillis()));
		List<AwardApprovedForeignTravel> approvedForeignTravelTrips = new ArrayList<AwardApprovedForeignTravel>();
		approvedForeignTravelTrips.add(approvedForeignTravel);
		award.setApprovedForeignTravelTrips(approvedForeignTravelTrips);
		AwardApprovedEquipment approvedEquipment = new AwardApprovedEquipment();
		approvedEquipment.setAwardNumber(award.getAwardNumber());
		approvedEquipment.setSequenceNumber(award.getSequenceNumber());
		approvedEquipment.setItem("Item 1");
		approvedEquipment.setVendor("vendor 1 ");
		approvedEquipment.setModel("Model 1");
		approvedEquipment.setAmount(23423.23);
		List<AwardApprovedEquipment> approvedEquipmentItems = new ArrayList<AwardApprovedEquipment>();
		approvedEquipmentItems.add(approvedEquipment);
		award.setApprovedEquipmentItems(approvedEquipmentItems);
		AwardSpecialReview awardSpecialReview = new AwardSpecialReview();
		awardSpecialReview.setAwardSpecialReviewId(2L);
		awardSpecialReview.setSequenceOwner(award);
		awardSpecialReview.setUpdateUser("quick start");
		awardSpecialReview.setSpecialReviewTypeCode("2");
		awardSpecialReview.setApprovalTypeCode("2");
		List<AwardSpecialReview> specialReviews = new ArrayList<AwardSpecialReview>();
		specialReviews.add(awardSpecialReview);
		award.setSpecialReviews(specialReviews);
		AwardCostShare awardCostShare = new AwardCostShare();
		awardCostShare.setAwardNumber(award.getAwardNumber());
		awardCostShare.setSequenceNumber(award.getSequenceNumber());
		awardCostShare.setProjectPeriod("2009");
		awardCostShare.setCostSharePercentage(new KualiDecimal(23));
		CostShareType costShareType = new CostShareType();
		costShareType.setCostShareTypeCode(2);
		costShareType.setDescription("Cost share ");
		awardCostShare.setCostShareType(costShareType);
		awardCostShare.setDestination("BLR");
		awardCostShare.setCommitmentAmount(new KualiDecimal(234));
		List<AwardCostShare> awardCostShares = new ArrayList<AwardCostShare>();
		awardCostShares.add(awardCostShare);
		award.setAwardCostShares(awardCostShares);
		AwardCustomData awardCustomData = new AwardCustomData();
		awardCustomData.setAwardNumber(award.getAwardNumber());
		awardCustomData.setSequenceNumber(award.getSequenceNumber());
		awardCustomData.setCustomAttributeId(1L);
		CustomAttribute customAttribute = new CustomAttribute();
		customAttribute.setLookupClass("W_ARG_CODE_TBL");
		customAttribute.setLookupReturn("STATE");
		awardCustomData.setCustomAttribute(customAttribute);
		awardCustomData.setValue("AK");
		List<AwardCustomData> awardCustomDataList = new ArrayList<AwardCustomData>();
		awardCustomDataList.add(awardCustomData);
		award.setAwardCustomDataList(awardCustomDataList);
		award.setPrincipalInvestigatorName("principle Investigator 123");
		award.setPrimeSponsorCode("005944");
		return award;
	}

	private static Award getAwardOne(Award award) {
		award.setAwardId(21L);
		award.setAwardNumber("000044-00001");
		award.setSequenceNumber(1);
		award.setSponsorCode("sponsorCode");
		award.setStatusCode(2);
		AwardStatus awardStatus = new AwardStatus();
		awardStatus.setStatusCode("2");
		awardStatus.setDescription("Test Description");
		award.setAwardStatus(awardStatus);
		award.setAccountNumber("2342342");
		award.setApprovedEquipmentIndicator("Y");
		award.setApprovedForeignTripIndicator("Y");
		award.setSubContractIndicator("Y");
		Calendar calendar = Calendar.getInstance();
		calendar.set(2009, 07, 28);
		award.setAwardEffectiveDate(new Date(calendar.getTimeInMillis()));
		calendar.set(2009, 07, 29);
		award.setAwardExecutionDate(new Date(calendar.getTimeInMillis()));
		calendar.set(2009, 07, 27);
		award.setBeginDate(new Date(calendar.getTimeInMillis()));
		award.setCostSharingIndicator("1");
		award.setIndirectCostIndicator("1");
		award.setModificationNumber("2134");
		award.setNsfCode("J.02");
		award.setPaymentScheduleIndicator("1");
		award.setScienceCodeIndicator("1");
		award.setSpecialReviewIndicator("1");
		award.setSponsorAwardNumber("213");
		award.setTransferSponsorIndicator("1");
		award.setAccountTypeCode(2);
		award.setActivityTypeCode("1");
		award.setAwardTypeCode(6);
		award.setCfdaNumber("334");
		award.setDocumentFundingId("23");
		award.setPreAwardAuthorizedAmount(new KualiDecimal(34));
		calendar.set(2009, 07, 30);
		award.setPreAwardEffectiveDate(new Date(calendar.getTimeInMillis()));
		award.setPreAwardInstitutionalAuthorizedAmount(new KualiDecimal(34));
		calendar.set(2009, 07, 30);
		award.setPreAwardInstitutionalEffectiveDate(new Date(calendar
				.getTimeInMillis()));
		award.setProcurementPriorityCode("1");
		award.setProposalNumber("10");
		award.setSpecialEbRateOffCampus(new KualiDecimal(34));
		award.setSpecialEbRateOnCampus(new KualiDecimal(34));
		award.setSubPlanFlag("true");
		award.setTitle("Award Title");
		award.setArchiveLocation("c:/docs");
		calendar.set(2009, 07, 30);
		award.setCloseoutDate(new Date(calendar.getTimeInMillis()));
		award.setIdcIndicator("Y");
		award.setPaymentScheduleIndicator("1");
		award.setCostSharingIndicator("Y");
		award.setTemplateCode(2);
		Frequency frequency = new Frequency();
		frequency.setDescription("Frequency 123");
		award.setBasisOfPaymentCode("basis of  payment code 123");
		award.setMethodOfPaymentCode("method Of Payment Code 123");
		AwardTemplate awardTemplate = new AwardTemplate();
		awardTemplate.setDescription("Award Template Description 123");
		award.setAwardTemplate(awardTemplate);
		AwardBasisOfPayment awardBasisOfPayment = new AwardBasisOfPayment();
		awardBasisOfPayment.setDescription("basis of payment desc 123");
		award.setAwardBasisOfPayment(awardBasisOfPayment);
		AwardMethodOfPayment awardMethodOfPayment = new AwardMethodOfPayment();
		awardMethodOfPayment.setDescription("award method of payment desc 123");
		Sponsor sponsor = new Sponsor();
		sponsor.setSponsorCode("005948");
		sponsor.setSponsorName("sponsor123");
		award.setSponsor(sponsor);
		Sponsor primeSponsor = new Sponsor();
		primeSponsor.setSponsorCode("005948");
		primeSponsor.setSponsorName("sponsor123");
		award.setSponsor(primeSponsor);
		award.setNsfCode("C1");
		AwardComment awardComment = new AwardComment();
		awardComment.setComments("Award cost Comment 123");
		CommentType commentType = new CommentType();
		commentType.setDescription("comment type desc 123");
		awardComment.setCommentType(commentType);
		awardComment.setCommentTypeCode("9");
		awardComment.setAwardNumber(award.getAwardNumber());
		awardComment.setSequenceNumber(award.getSequenceNumber());
		AwardComment indirectAwardComment = new AwardComment();
		indirectAwardComment.setComments("Indirect cost Comment 123");
		CommentType indirectCommentType = new CommentType();
		indirectCommentType.setDescription("comment type desc 123");
		indirectAwardComment.setCommentType(indirectCommentType);
		indirectAwardComment.setCommentTypeCode("8");
		indirectAwardComment.setAwardNumber(award.getAwardNumber());
		indirectAwardComment.setSequenceNumber(award.getSequenceNumber());
		List<AwardComment> awardComments = new ArrayList<AwardComment>();
		awardComments.add(awardComment);
		awardComments.add(indirectAwardComment);
		award.setAwardComments(awardComments);
		List<AwardReportTerm> awardReportTermItems = new ArrayList<AwardReportTerm>();
		AwardReportTerm awardReportTerm = new AwardReportTerm();
		awardReportTerm.setSequenceNumber(1);
		awardReportTerm.setReportClassCode("1");
		ReportClass reportClass = new ReportClass();
		reportClass.setDescription("description");
		awardReportTerm.setReportClass(reportClass);
		awardReportTerm.setReportCode("1");
		Report report = new Report();
		report.setReportCode("1");
		report.setDescription("Report Desc");
		awardReportTerm.setReport(report);
		awardReportTerm.setFrequencyCode("1");
		awardReportTerm.setFrequencyBaseCode("1");
		Frequency frequency2 = new Frequency();
		frequency2.setFrequencyCode("1");
		frequency2.setDescription("Frequency desc");
		awardReportTerm.setFrequency(frequency2);
		FrequencyBase frequencyBase = new FrequencyBase();
		frequencyBase.setFrequencyBaseCode("1");
		frequencyBase.setDescription("Frequency base desc");
		awardReportTerm.setFrequencyBase(frequencyBase);
		awardReportTerm.setOspDistributionCode("1");
		Distribution distribution = new Distribution();
		distribution.setOspDistributionCode("1");
		distribution.setDescription("OSP DESC");
		awardReportTerm.setDueDate(new Date(System.currentTimeMillis()));
		awardReportTermItems.add(awardReportTerm);
		award.setAwardReportTermItems(awardReportTermItems);
		Unit unit = new Unit();
		Organization organization = new Organization();
		organization.setOrganizationName("Kuali org");
		organization.setOrganizationId("1");
		unit.setOrganization(organization);
		unit.setOrganizationId("1");
		// FIXME: Kim Migration
		KcPerson person = new KcPerson();
		// person.setUnit(unit);
		// person.setFullName("first last");
		person.setPersonId("1");
		// person.setOfficeLocation("abc");
		NonOrganizationalRolodex rolodex = new NonOrganizationalRolodex();
		rolodex.setAddressLine1("address line 1");
		rolodex.setAddressLine2("address Line 2");
		rolodex.setAddressLine3("address Line 3");
		rolodex.setCity("BLR");
		rolodex.setComments("Comments one ");
		rolodex.setCountryCode("USA");
		rolodex.setCounty("USA");
		rolodex.setCreateUser("Quick start");
		rolodex.setEmailAddress("abs@abs.com");
		rolodex.setFaxNumber("12431234");
		rolodex.setFirstName("First ");
		rolodex.setLastName("Last Name");
		rolodex.setMiddleName("Middle");
		rolodex.setOrganization("Kuali");
		rolodex.setPhoneNumber("2354234634754");
		rolodex.setPostalCode("423412");
		rolodex.setRolodexId(1);
		rolodex.setSponsor(sponsor);
		rolodex.setSponsorCode("2");
		rolodex.setSponsorAddressFlag(true);
		rolodex.setState("AK");
		rolodex.setTitle("Rolodex Title");
		rolodex.setSuffix("suffix");
		rolodex.setPrefix("preffix");
		rolodex.setUnit(unit);
		rolodex.setUpdateUser("quickstart");
		rolodex.setVersionNumber(1L);
		List<AwardUnitContact> awardUnitContacts = new ArrayList<AwardUnitContact>();
		AwardUnitContact administrator = new AwardUnitContact();
		administrator.setUnitContactType(UnitContactType.ADMINISTRATOR);
		administrator.setRoleCode("2");
		administrator.setPerson(person);
		awardUnitContacts.add(administrator);
		AwardUnitContact piAwardUnitContact = new AwardUnitContact();
		piAwardUnitContact.setPerson(person);
		ContactType contactType = new ContactType();
		contactType.setContactTypeCode(ContactRole.PI_CODE);
		piAwardUnitContact.setContactRole(contactType);
		piAwardUnitContact.setContactRoleCode(ContactRole.PI_CODE);
		piAwardUnitContact.setRoleCode("2");
		piAwardUnitContact.setRolodex(rolodex);
		awardUnitContacts.add(piAwardUnitContact);
		AwardUnitContact awardUnitContact = new AwardUnitContact();
		awardUnitContact.setPerson(person);
		ContactType coiContact = new ContactType();
		coiContact.setContactTypeCode(ContactRole.COI_CODE);
		awardUnitContact.setContactRole(coiContact);
		awardUnitContact.setContactRoleCode(ContactRole.COI_CODE);
		awardUnitContact.setRoleCode("2");
		awardUnitContact.setRolodex(rolodex);
		award.setAwardUnitContacts(awardUnitContacts);
		AwardUnitContact keyPersonAwardUnitContact = new AwardUnitContact();
		keyPersonAwardUnitContact.setPerson(person);
		ContactType keyContact = new ContactType();
		keyContact.setContactTypeCode(ContactRole.KEY_PERSON_CODE);
		keyPersonAwardUnitContact.setContactRole(keyContact);
		keyPersonAwardUnitContact
				.setContactRoleCode(ContactRole.KEY_PERSON_CODE);
		keyPersonAwardUnitContact.setRoleCode("2");
		keyPersonAwardUnitContact.setRolodex(rolodex);
		awardUnitContacts.add(keyPersonAwardUnitContact);
		AwardAmountInfo awardAmountInfo = new AwardAmountInfo();
		awardAmountInfo.setSequenceNumber(1);
		awardAmountInfo.setTransactionId(1L);
		awardAmountInfo.setAmountObligatedToDate(new KualiDecimal(2000L));
		awardAmountInfo.setAntDistributableAmount(new KualiDecimal(3000L));
		awardAmountInfo.setAnticipatedChange(new KualiDecimal(23L));
		awardAmountInfo.setAnticipatedChangeDirect(new KualiDecimal(2L));
		awardAmountInfo.setAnticipatedChangeIndirect(new KualiDecimal(3L));
		awardAmountInfo.setAnticipatedTotalAmount(new KualiDecimal(3000L));
		awardAmountInfo.setAnticipatedTotalDirect(new KualiDecimal(2));
		awardAmountInfo.setAnticipatedTotalIndirect(new KualiDecimal(3));
		awardAmountInfo.setAwardAmountInfoId(2312L);
		awardAmountInfo.setAwardNumber(award.getAwardNumber());
		awardAmountInfo.setSequenceNumber(1);
		awardAmountInfo.setCurrentFundEffectiveDate(new Date(System
				.currentTimeMillis()));
		awardAmountInfo.setEntryType(true);
		awardAmountInfo.setEomProcessFlag(true);
		awardAmountInfo.setFinalExpirationDate(new Date(System
				.currentTimeMillis()));
		awardAmountInfo.setObliDistributableAmount(new KualiDecimal(3000L));
		awardAmountInfo.setObligatedChange(new KualiDecimal(324L));
		awardAmountInfo.setObligatedChangeDirect(new KualiDecimal(234L));
		awardAmountInfo.setObligatedChangeIndirect(new KualiDecimal(3434L));
		awardAmountInfo.setObligatedTotalDirect(new KualiDecimal(3));
		awardAmountInfo.setObligatedTotalIndirect(new KualiDecimal(34));
		awardAmountInfo.setObligationExpirationDate(new Date(System
				.currentTimeMillis()));
		awardAmountInfo.setTransactionId(2L);
		awardAmountInfo.setUpdateUser("quick start");
		awardAmountInfo.setUpdateTimestamp(new Timestamp(System
				.currentTimeMillis()));
		awardAmountInfo.setVersionNumber(1L);
		AwardAmountInfo awardAmountInfoForNextSeq = new AwardAmountInfo();
		awardAmountInfoForNextSeq.setSequenceNumber(1);
		awardAmountInfoForNextSeq.setTransactionId(2L);
		awardAmountInfoForNextSeq.setAmountObligatedToDate(new KualiDecimal(
				2000L));
		awardAmountInfoForNextSeq.setAntDistributableAmount(new KualiDecimal(
				3000L));
		awardAmountInfoForNextSeq.setAnticipatedChange(new KualiDecimal(23L));
		awardAmountInfoForNextSeq.setAnticipatedChangeDirect(new KualiDecimal(2L));
		awardAmountInfoForNextSeq.setAnticipatedChangeIndirect(new KualiDecimal(3L));
		awardAmountInfoForNextSeq.setAnticipatedTotalAmount(new KualiDecimal(
				3000L));
		awardAmountInfoForNextSeq
				.setAnticipatedTotalDirect(new KualiDecimal(2));
		awardAmountInfoForNextSeq.setAnticipatedTotalIndirect(new KualiDecimal(
				3));
		awardAmountInfoForNextSeq.setAwardAmountInfoId(2312L);
		awardAmountInfoForNextSeq.setAwardNumber(award.getAwardNumber());
		awardAmountInfoForNextSeq.setSequenceNumber(1);
		awardAmountInfoForNextSeq.setCurrentFundEffectiveDate(new Date(System
				.currentTimeMillis()));
		awardAmountInfoForNextSeq.setEntryType(true);
		awardAmountInfoForNextSeq.setEomProcessFlag(true);
		awardAmountInfoForNextSeq.setFinalExpirationDate(new Date(System
				.currentTimeMillis()));
		awardAmountInfoForNextSeq.setObliDistributableAmount(new KualiDecimal(
				3000L));
		awardAmountInfoForNextSeq.setObligatedChange(new KualiDecimal(324L));
		awardAmountInfoForNextSeq.setObligatedChangeDirect(new KualiDecimal(234L));
		awardAmountInfoForNextSeq.setObligatedChangeIndirect(new KualiDecimal(3434L));
		awardAmountInfoForNextSeq.setObligatedTotalDirect(new KualiDecimal(3));
		awardAmountInfoForNextSeq
				.setObligatedTotalIndirect(new KualiDecimal(34));
		awardAmountInfoForNextSeq.setObligationExpirationDate(new Date(System
				.currentTimeMillis()));
		awardAmountInfoForNextSeq.setTransactionId(2L);
		awardAmountInfoForNextSeq.setUpdateUser("quick start");
		awardAmountInfoForNextSeq.setUpdateTimestamp(new Timestamp(System
				.currentTimeMillis()));
		awardAmountInfoForNextSeq.setVersionNumber(1L);
		List<AwardAmountInfo> awardAmountInfos = new ArrayList<AwardAmountInfo>();
		awardAmountInfos.add(awardAmountInfoForNextSeq);
		awardAmountInfos.add(awardAmountInfo);
		award.setAwardAmountInfos(awardAmountInfos);
		AwardCloseout awardCloseout = new AwardCloseout();
		awardCloseout.setAwardNumber(award.getAwardNumber());
		awardCloseout.setSequenceNumber(award.getSequenceNumber());
		awardCloseout.setAwardCloseoutId(1L);
		awardCloseout.setCloseoutReportCode("1");
		awardCloseout.setFinalSubmissionDate(new Date(System
				.currentTimeMillis()));
		awardCloseout.setCloseoutReportName("Test closeout report");
		awardCloseout.setUpdateUser("quick start");
		awardCloseout.setVersionNumber(1L);
		awardCloseout.setDueDate(new Date(System.currentTimeMillis()));
		awardCloseout.setUpdateTimestamp(new Timestamp(System
				.currentTimeMillis()));
		CloseoutReportType closeoutReportType = new CloseoutReportType();
		closeoutReportType.setCloseoutReportCode("1");
		closeoutReportType.setDescription("Close out desc");
		awardCloseout.setCloseoutReportType(closeoutReportType);
		List<AwardCloseout> awardCloseoutItems = new ArrayList<AwardCloseout>();
		awardCloseoutItems.add(awardCloseout);
		award.setAwardCloseoutItems(awardCloseoutItems);
		AwardPerson awardPerson = new AwardPerson();
		awardPerson.setVersionNumber(1L);
		awardPerson.setUpdateUser("quick start");
		awardPerson
				.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
		awardPerson.setFaculty(true);
		awardPerson.setRoleCode("1");
		ContactType keyPersonContactType = new ContactType();
		keyPersonContactType.setContactTypeCode(ContactRole.KEY_PERSON_CODE);
		awardPerson.setContactRole(keyPersonContactType);
		awardPerson.setPerson(person);
		awardPerson.setTotalEffort(new KualiDecimal(23234));
		AwardPerson piAwardPerson = new AwardPerson();
		piAwardPerson.setVersionNumber(1L);
		piAwardPerson.setUpdateUser("quick start");
		piAwardPerson.setUpdateTimestamp(new Timestamp(System
				.currentTimeMillis()));
		piAwardPerson.setFaculty(true);
		piAwardPerson.setRoleCode("1");
		ContactType piCodeContactType = new ContactType();
		piCodeContactType.setContactTypeCode(ContactRole.PI_CODE);
		piAwardPerson.setContactRole(piCodeContactType);
		piAwardPerson.setPerson(person);
		piAwardPerson.setTotalEffort(new KualiDecimal(23234));
		award.add(piAwardPerson);
		AwardTransferringSponsor awardTransferringSponsor = new AwardTransferringSponsor();
		awardTransferringSponsor.setAwardNumber(award.getAwardNumber());
		awardTransferringSponsor.setSequenceNumber(award.getSequenceNumber());
		awardTransferringSponsor.setAwardTransferringSponsorId(23);
		awardTransferringSponsor.setSponsor(sponsor);
		awardTransferringSponsor.setSponsorCode("2");
		List<AwardTransferringSponsor> awardTransferringSponsors = new ArrayList<AwardTransferringSponsor>();
		awardTransferringSponsors.add(awardTransferringSponsor);
		award.setAwardTransferringSponsors(awardTransferringSponsors);
		AwardPaymentSchedule awardPaymentSchedule = new AwardPaymentSchedule();
		awardPaymentSchedule.setAwardNumber(award.getAwardNumber());
		awardPaymentSchedule.setSequenceNumber(award.getSequenceNumber());
		awardPaymentSchedule.setDueDate(new Date(System.currentTimeMillis()));
		awardPaymentSchedule.setAmount(new KualiDecimal(234));
		awardPaymentSchedule.setStatusDescription("payment schedule 123");
		awardPaymentSchedule.setInvoiceNumber("3");
		awardPaymentSchedule.setStatus("active");
		awardPaymentSchedule
				.setSubmitDate(new Date(System.currentTimeMillis()));
		awardPaymentSchedule.setSubmittedBy("Gving");
		awardPaymentSchedule.setUpdateUser("quick start");
		List<AwardPaymentSchedule> paymentScheduleItems = new ArrayList<AwardPaymentSchedule>();
		paymentScheduleItems.add(awardPaymentSchedule);
		award.setPaymentScheduleItems(paymentScheduleItems);
		AwardApprovedForeignTravel approvedForeignTravel = new AwardApprovedForeignTravel();
		approvedForeignTravel.setAmount(23423.32);
		approvedForeignTravel.setAwardNumber(award.getAwardNumber());
		approvedForeignTravel.setSequenceNumber(award.getSequenceNumber());
		approvedForeignTravel.setPersonId("234");
		approvedForeignTravel.setTravelerName("XYZ");
		approvedForeignTravel.setDestination("BLR");
		approvedForeignTravel
				.setStartDate(new Date(System.currentTimeMillis()));
		approvedForeignTravel.setEndDate(new Date(System.currentTimeMillis()));
		List<AwardApprovedForeignTravel> approvedForeignTravelTrips = new ArrayList<AwardApprovedForeignTravel>();
		approvedForeignTravelTrips.add(approvedForeignTravel);
		award.setApprovedForeignTravelTrips(approvedForeignTravelTrips);
		AwardApprovedEquipment approvedEquipment = new AwardApprovedEquipment();
		approvedEquipment.setAwardNumber(award.getAwardNumber());
		approvedEquipment.setSequenceNumber(award.getSequenceNumber());
		approvedEquipment.setItem("Item 1");
		approvedEquipment.setVendor("vendor 1 ");
		approvedEquipment.setModel("Model 1");
		approvedEquipment.setAmount(23423.23);
		List<AwardApprovedEquipment> approvedEquipmentItems = new ArrayList<AwardApprovedEquipment>();
		approvedEquipmentItems.add(approvedEquipment);
		award.setApprovedEquipmentItems(approvedEquipmentItems);
		AwardSpecialReview awardSpecialReview = new AwardSpecialReview();
		awardSpecialReview.setAwardSpecialReviewId(2L);
		awardSpecialReview.setSequenceOwner(award);
		awardSpecialReview.setUpdateUser("quick start");
		awardSpecialReview.setSpecialReviewTypeCode("2");
		awardSpecialReview.setApprovalTypeCode("2");
		List<AwardSpecialReview> specialReviews = new ArrayList<AwardSpecialReview>();
		specialReviews.add(awardSpecialReview);
		award.setSpecialReviews(specialReviews);
		AwardCostShare awardCostShare = new AwardCostShare();
		awardCostShare.setAwardNumber(award.getAwardNumber());
		awardCostShare.setSequenceNumber(award.getSequenceNumber());
		awardCostShare.setProjectPeriod("2009");
		awardCostShare.setCostSharePercentage(new KualiDecimal(23));
		CostShareType costShareType = new CostShareType();
		costShareType.setCostShareTypeCode(2);
		costShareType.setDescription("Cost share ");
		awardCostShare.setCostShareType(costShareType);
		awardCostShare.setDestination("BLR");
		awardCostShare.setCommitmentAmount(new KualiDecimal(234));
		List<AwardCostShare> awardCostShares = new ArrayList<AwardCostShare>();
		awardCostShares.add(awardCostShare);
		award.setAwardCostShares(awardCostShares);
		AwardCustomData awardCustomData = new AwardCustomData();
		awardCustomData.setAwardNumber(award.getAwardNumber());
		awardCustomData.setSequenceNumber(award.getSequenceNumber());
		awardCustomData.setCustomAttributeId(1L);
		CustomAttribute customAttribute = new CustomAttribute();
		customAttribute.setLookupClass("W_ARG_CODE_TBL");
		customAttribute.setLookupReturn("STATE");
		awardCustomData.setCustomAttribute(customAttribute);
		awardCustomData.setValue("AK");
		List<AwardCustomData> awardCustomDataList = new ArrayList<AwardCustomData>();
		awardCustomDataList.add(awardCustomData);
		award.setAwardCustomDataList(awardCustomDataList);
		award.setPrincipalInvestigatorName("principle Investigator 123");
		award.setPrimeSponsorCode("005944");

		// Hard coded values for Award Template Report
		List<AwardTemplateComment> templateComments = new ArrayList<AwardTemplateComment>();
		AwardTemplateComment awardTemplateComment = new AwardTemplateComment();
		awardTemplateComment.setComments("Award Template Comments1");
		awardTemplateComment.setCommentTypeCode("1234");
		awardTemplateComment.setTemplate(awardTemplate);
		templateComments.add(awardTemplateComment);
		AwardTemplateComment awardTemplateComment2 = new AwardTemplateComment();
		awardTemplateComment2.setComments("Award Template Comments2");
		awardTemplateComment2.setCommentTypeCode("124");
		awardTemplateComment2.setTemplate(awardTemplate);
		templateComments.add(awardTemplateComment2);
		awardTemplate.setTemplateComments(templateComments);
		List<AwardTemplateContact> templateContacts = new ArrayList<AwardTemplateContact>();
		AwardTemplateContact awardTemplateContact = new AwardTemplateContact();
		awardTemplateContact.setContactType(contactType);
		awardTemplateContact.setRolodex(rolodex);
		awardTemplateContact.setRoleCode("2");
		templateContacts.add(awardTemplateContact);
		AwardTemplateContact awardTemplateContact1 = new AwardTemplateContact();
		awardTemplateContact1.setContactType(contactType);
		awardTemplateContact1.setRolodex(rolodex);
		awardTemplateContact1.setRoleCode("2");
		templateContacts.add(awardTemplateContact1);
		awardTemplate.setTemplateContacts(templateContacts);
		List<AwardTemplateReportTerm> templateReportTerms = new ArrayList<AwardTemplateReportTerm>();
		AwardTemplateReportTerm awardTemplateReportTerm = new AwardTemplateReportTerm();
		awardTemplateReportTerm.setReportClass(reportClass);
		awardTemplateReportTerm
				.setDueDate(new Date(System.currentTimeMillis()));
		awardTemplateReportTerm.setFrequencyBaseCode("1");
		awardTemplateReportTerm.setFrequencyBase(frequencyBase);
		awardTemplateReportTerm.setFrequencyCode("1");
		awardTemplateReportTerm.setFrequency(frequency);
		awardTemplateReportTerm.setOspDistributionCode("1");
		awardTemplateReportTerm.setDistribution(distribution);
		awardTemplateReportTerm.setReportClassCode("1");
		awardTemplateReportTerm.setReportClass(reportClass);
		awardTemplateReportTerm.setReportCode("1");
		awardTemplateReportTerm.setReport(report);
		List<AwardTemplateReportTermRecipient> awardTemplateReportTermRecipients = new ArrayList<AwardTemplateReportTermRecipient>();
		AwardTemplateReportTermRecipient awardTemplateReportTermRecipient = new AwardTemplateReportTermRecipient();
		awardTemplateReportTermRecipient.setContactType(contactType);
		awardTemplateReportTermRecipient.setContactTypeCode("1");
		awardTemplateReportTermRecipient.setNumberOfCopies(123);
		awardTemplateReportTermRecipient.setRolodexId(1);
		awardTemplateReportTermRecipient.setRolodex(rolodex);
		awardTemplateReportTermRecipients.add(awardTemplateReportTermRecipient);
		awardTemplateReportTerm
				.setAwardTemplateReportTermRecipients(awardTemplateReportTermRecipients);
		templateReportTerms.add(awardTemplateReportTerm);
		awardTemplate.setTemplateReportTerms(templateReportTerms);
		return award;
	}

	public static BudgetDocument getBudgetDocument() {

		documentService = KraServiceLocator.getService(DocumentService.class);
		GlobalVariables.setUserSession(new UserSession("quickstart"));
		ProposalDevelopmentDocument pdDoc = (ProposalDevelopmentDocument) getProposalDevelopmentDocument();
		BudgetDocument bd = null;
		try {
			bd = (BudgetDocument) getDocumentService().getNewDocument(
					"BudgetDocument");
		} catch (WorkflowException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		bd.getDocumentHeader()
				.setDocumentDescription("Test budget calculation");
		bd.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
		bd.setUpdateUser("KRADEV");
		bd.setParentDocumentTypeCode("P");
		// Budget
		List<Budget> budgets = new ArrayList<Budget>();
		Budget budget = bd.getBudget();

		// budget.setProposalNumber("1001");//TODO
		budget.setBudgetId(new Long(1));
		budget.setBudgetVersionNumber(1);
		budget.setStartDate(java.sql.Date.valueOf("2002-01-01"));
		budget.setEndDate(java.sql.Date.valueOf("2008-12-31"));
		budget.setOhRateClassCode("1");
		budget.setUrRateClassCode("1");
		budget.setModularBudgetFlag(false);
		budget.setActivityTypeCode("1");
		budget.setBudgetDocument(bd);
		budget.setTotalDirectCost(new BudgetDecimal(100000));

		// RateClass
		RateClass rateClass = new RateClass();
		rateClass.setRateClassType(RateClassType.OVERHEAD.getRateClassType());
		rateClass.setDescription("MTDC");
		RateClass rateClass1 = new RateClass();
		rateClass1.setRateClassType(RateClassType.EMPLOYEE_BENEFITS
				.getRateClassType());
		rateClass1.setDescription("Employee Benefits");
		RateClass rateClass2 = new RateClass();
		rateClass2.setRateClassType(RateClassType.LA_SALARIES
				.getRateClassType());
		rateClass2.setDescription("Lab Allocation - Salaries");
		RateClass rateClass3 = new RateClass();
		rateClass3.setRateClassType(RateClassType.LAB_ALLOCATION
				.getRateClassType());
		rateClass3.setDescription("Lab Allocation - M&S");
		RateClass rateClass4 = new RateClass();
		rateClass4.setRateClassType(RateClassType.VACATION.getRateClassType());
		rateClass4.setDescription("Vacation");
		RateClass rateClass5 = new RateClass();
		rateClass5.setRateClassType(RateClassType.OTHER
				.getRateClassType());
		rateClass5.setDescription("Other");
		// RateType
		RateType rateType = new RateType();
		rateType.setRateClass(rateClass);
		rateType.setRateClassCode("1");
		rateType.setRateTypeCode("1");
		rateType.setDescription(rateClass.getDescription());
		RateType rateType1 = new RateType();
		rateType1.setRateClass(rateClass);
		rateType1.setRateClassCode("5");
		rateType1.setRateTypeCode("1");
		rateType1.setDescription(rateClass.getDescription());
		RateType rateType2 = new RateType();
		rateType2.setRateClass(rateClass);
		rateType2.setRateClassCode("10");
		rateType2.setRateTypeCode("1");
		rateType2.setDescription(rateClass.getDescription());
		RateType rateType3 = new RateType();
		rateType3.setRateClass(rateClass);
		rateType3.setRateClassCode("11");
		rateType3.setRateTypeCode("1");
		rateType3.setDescription(rateClass.getDescription());
		RateType rateType4 = new RateType();
		rateType4.setRateClass(rateClass);
		rateType4.setRateClassCode("8");
		rateType4.setRateTypeCode("1");
		rateType4.setDescription(rateClass.getDescription());
		RateType rateType5 = new RateType();
		rateType5.setRateClass(rateClass);
		rateType5.setRateClassCode("8");
		rateType5.setRateTypeCode("2");
		rateType5.setDescription(rateClass.getDescription());
		// Person
		// FIXME: Kim Migration
		KcPerson person = new KcPerson();
		person.setPersonId("993764481");
		// person.setFullName("Irvine, Darrell J");

		// BudgetPeriods
		BudgetPeriod firstPeriod = new BudgetPeriod();
		firstPeriod.setBudgetId(new Long(1));
		firstPeriod.setBudgetPeriod(1);
		firstPeriod.setVersionNumber(Long.valueOf(1));
		firstPeriod.setStartDate(new Date(Calendar.getInstance()
				.getTimeInMillis()));
		firstPeriod.setEndDate(new Date(Calendar.getInstance()
				.getTimeInMillis()));
		firstPeriod.setTotalCost(new BudgetDecimal(152614.9));
		firstPeriod.setTotalDirectCost(new BudgetDecimal(100000));
		firstPeriod.setTotalIndirectCost(new BudgetDecimal(52614.9));
		firstPeriod.setBudget(budget);
		// BudgetLineItems
		List<BudgetLineItem> budgetLineItemList = new ArrayList<BudgetLineItem>();
		BudgetLineItem budgetLineItem = budget.getNewBudgetLineItem();
		budgetLineItem.setBudgetId(new Long(1));
		budgetLineItem.setBudgetPeriod(firstPeriod.getBudgetPeriod());
		budgetLineItem.setLineItemNumber(1);
		budgetLineItem.setApplyInRateFlag(true);
		budgetLineItem.setVersionNumber(Long.valueOf(1));
		budgetLineItem.setCostElement("400005");
		budgetLineItem.setEndDate(java.sql.Date.valueOf("2007-12-31"));
		budgetLineItem.setStartDate(java.sql.Date.valueOf("2007-01-01"));
		budgetLineItem.setOnOffCampusFlag(false);
		budgetLineItem.setLineItemCost(new BudgetDecimal(4596.94));
		budgetLineItem.setBudgetCategoryCode("1");
		budgetLineItem.setQuantity(1);
		BudgetCategory budgetCategory = new BudgetCategory();
		budgetCategory.setDescription("Senior Personnel");
		budgetCategory.setBudgetCategoryTypeCode("T");
		budgetLineItem.setBudgetCategory(budgetCategory);
		// CostElement
		CostElement costElementBO = new CostElement();
		costElementBO.setDescription("Summer Faculty - On");
		costElementBO.setBudgetCategoryCode(budgetLineItem
				.getBudgetCategoryCode());
		costElementBO.setOnOffCampusFlag(false);
		// BudgetLineItemCalculatedAmount
		List<BudgetLineItemCalculatedAmount> budgetLineItemCalculatedAmounts = new ArrayList<BudgetLineItemCalculatedAmount>();
		BudgetLineItemCalculatedAmount budgetLineItemCalcAmount = (BudgetLineItemCalculatedAmount) budgetLineItem
				.getNewBudgetLineItemCalculatedAmount();
		budgetLineItemCalcAmount.setBudgetId(budgetLineItem.getBudgetId());
		budgetLineItemCalcAmount.setBudgetPeriod(budgetLineItem
				.getBudgetPeriod());
		budgetLineItemCalcAmount.setLineItemNumber(budgetLineItem
				.getLineItemNumber());
		budgetLineItemCalcAmount.setRateClassCode("1");
		budgetLineItemCalcAmount.setRateTypeCode("1");
		budgetLineItemCalcAmount.setApplyRateFlag(true);
		budgetLineItemCalcAmount.setCalculatedCost(new BudgetDecimal(3794.77));
		budgetLineItemCalcAmount.setRateClass(rateClass); 
//		budgetLineItemCalcAmount.setRateType(rateType);
		budgetLineItemCalculatedAmounts.add(budgetLineItemCalcAmount);

		BudgetLineItemCalculatedAmount budgetLineItemCalcAmount1 = (BudgetLineItemCalculatedAmount) budgetLineItem
				.getNewBudgetLineItemCalculatedAmount();
		budgetLineItemCalcAmount1.setBudgetId(budgetLineItem.getBudgetId());
		budgetLineItemCalcAmount1.setBudgetPeriod(budgetLineItem
				.getBudgetPeriod());
		budgetLineItemCalcAmount1.setLineItemNumber(budgetLineItem
				.getLineItemNumber());
		budgetLineItemCalcAmount1.setRateClassCode("5");
		budgetLineItemCalcAmount1.setRateTypeCode("1");
		budgetLineItemCalcAmount1.setApplyRateFlag(true);
		budgetLineItemCalcAmount1.setCalculatedCost(new BudgetDecimal(3794.77));
		budgetLineItemCalcAmount1.setRateClass(rateClass1);
//		budgetLineItemCalcAmount1.setRateType(rateType1);
		budgetLineItemCalculatedAmounts.add(budgetLineItemCalcAmount1);
		BudgetLineItemCalculatedAmount budgetLineItemCalcAmount2 = (BudgetLineItemCalculatedAmount) budgetLineItem
				.getNewBudgetLineItemCalculatedAmount();
		budgetLineItemCalcAmount2.setBudgetId(budgetLineItem.getBudgetId());
		budgetLineItemCalcAmount2.setBudgetPeriod(budgetLineItem
				.getBudgetPeriod());
		budgetLineItemCalcAmount2.setLineItemNumber(budgetLineItem
				.getLineItemNumber());
		budgetLineItemCalcAmount2.setRateClassCode("10");
		budgetLineItemCalcAmount2.setRateTypeCode("1");
		budgetLineItemCalcAmount2.setApplyRateFlag(true);
		budgetLineItemCalcAmount2.setCalculatedCost(new BudgetDecimal(3794.77));
		budgetLineItemCalcAmount2.setRateClass(rateClass2);
//		budgetLineItemCalcAmount2.setRateType(rateType2);
		budgetLineItemCalculatedAmounts.add(budgetLineItemCalcAmount2);
		BudgetLineItemCalculatedAmount budgetLineItemCalcAmount3 = (BudgetLineItemCalculatedAmount) budgetLineItem
				.getNewBudgetLineItemCalculatedAmount();
		budgetLineItemCalcAmount3.setBudgetId(budgetLineItem.getBudgetId());
		budgetLineItemCalcAmount3.setBudgetPeriod(budgetLineItem
				.getBudgetPeriod());
		budgetLineItemCalcAmount3.setLineItemNumber(budgetLineItem
				.getLineItemNumber());
		budgetLineItemCalcAmount3.setRateClassCode("11");
		budgetLineItemCalcAmount3.setRateTypeCode("1");
		budgetLineItemCalcAmount3.setApplyRateFlag(true);
		budgetLineItemCalcAmount3.setCalculatedCost(new BudgetDecimal(3794.77));
		budgetLineItemCalcAmount3.setRateClass(rateClass3);
//		budgetLineItemCalcAmount3.setRateType(rateType3);
		budgetLineItemCalculatedAmounts.add(budgetLineItemCalcAmount3);
		BudgetLineItemCalculatedAmount budgetLineItemCalcAmount4 = (BudgetLineItemCalculatedAmount) budgetLineItem
				.getNewBudgetLineItemCalculatedAmount();
		budgetLineItemCalcAmount4.setBudgetId(budgetLineItem.getBudgetId());
		budgetLineItemCalcAmount4.setBudgetPeriod(budgetLineItem
				.getBudgetPeriod());
		budgetLineItemCalcAmount4.setLineItemNumber(budgetLineItem
				.getLineItemNumber());
		budgetLineItemCalcAmount4.setRateClassCode("8");
		budgetLineItemCalcAmount4.setRateTypeCode("1");
		budgetLineItemCalcAmount4.setApplyRateFlag(true);
		budgetLineItemCalcAmount4.setCalculatedCost(new BudgetDecimal(3794.77));
		budgetLineItemCalcAmount4.setRateClass(rateClass4);
//		budgetLineItemCalcAmount4.setRateType(rateType4);
		budgetLineItemCalculatedAmounts.add(budgetLineItemCalcAmount4);
		BudgetLineItemCalculatedAmount budgetLineItemCalcAmount5 = (BudgetLineItemCalculatedAmount) budgetLineItem
				.getNewBudgetLineItemCalculatedAmount();
		budgetLineItemCalcAmount5.setBudgetId(budgetLineItem.getBudgetId());
		budgetLineItemCalcAmount5.setBudgetPeriod(budgetLineItem
				.getBudgetPeriod());
		budgetLineItemCalcAmount5.setLineItemNumber(budgetLineItem
				.getLineItemNumber());
		budgetLineItemCalcAmount5.setRateClassCode("8");
		budgetLineItemCalcAmount5.setRateTypeCode("2");
		budgetLineItemCalcAmount5.setApplyRateFlag(true);
		budgetLineItemCalcAmount5.setCalculatedCost(new BudgetDecimal(3794.77));
		budgetLineItemCalcAmount5.setRateClass(rateClass5);
//		budgetLineItemCalcAmount5.setRateType(rateType5);
		budgetLineItemCalculatedAmounts.add(budgetLineItemCalcAmount5);

		// BudgetLaRate
		List<BudgetLaRate> budgetLaRates = new ArrayList<BudgetLaRate>();
		BudgetLaRate budgetPropLaRate = new BudgetLaRate();
		budgetPropLaRate.setActive(true);
		budgetPropLaRate.setBudget(budget);
		budgetPropLaRate.setRateTypeCode("1");
		budgetPropLaRate.setRateClassCode("1");
		budgetPropLaRate.setFiscalYear("2009");
		budgetPropLaRate.setOnOffCampusFlag(true);
		budgetPropLaRate.setStartDate(new java.sql.Date(Calendar.getInstance()
				.getTimeInMillis()));
		budgetPropLaRate.setApplicableRate(new BudgetDecimal(30));
		budgetPropLaRate.setInstituteRate(new BudgetDecimal(30));
		budgetLaRates.add(budgetPropLaRate);
		budget.setBudgetLaRates(budgetLaRates);

		budgetLineItem
				.setBudgetLineItemCalculatedAmounts(budgetLineItemCalculatedAmounts);

		// BudgetPersonnelDetails
		List<BudgetPersonnelDetails> budgetPersonnelDetailsList = new ArrayList<BudgetPersonnelDetails>();
		BudgetPersonnelDetails budgetPersDetails = budgetLineItem
				.getNewBudgetPersonnelLineItem();
		budgetPersDetails.setBudgetId(new Long(1));
		budgetPersDetails.setStartDate(java.sql.Date.valueOf("2007-06-01"));
		budgetPersDetails.setEndDate(java.sql.Date.valueOf("2007-06-30"));
		budgetPersDetails.setOnOffCampusFlag(false);
		budgetPersDetails.setApplyInRateFlag(true);
		budgetPersDetails.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
		budgetPersDetails.setLineItemNumber(budgetLineItem.getLineItemNumber());
		budgetPersDetails.setPersonNumber(1);
		budgetPersDetails.setJobCode("BB009");
		budgetPersDetails.setPercentCharged(new BudgetDecimal(50));
		budgetPersDetails.setPercentEffort(new BudgetDecimal(50));
		budgetPersDetails.setVersionNumber(Long.valueOf(1));
		budgetPersDetails.setPersonId("993764481");
		budgetPersDetails.setPersonNumber(1);
		budgetPersDetails.setSalaryRequested(new BudgetDecimal(4596.94));
		budgetPersDetails.setBudgetCategoryCode(budgetLineItem
				.getBudgetCategoryCode());
		budgetPersDetails.setBudgetCategory(budgetLineItem.getBudgetCategory());
		budgetPersDetails.setCostElementBO(costElementBO);

		// BudgetPerson
		BudgetPerson budgetPerson = new BudgetPerson();
		budgetPerson.setJobCode(budgetPersDetails.getJobCode());
		budgetPerson.setPersonId(budgetPersDetails.getPersonId());
		budgetPerson.setPersonName("Irvine, Darrell J");

		budgetPersDetails.setBudgetPerson(budgetPerson);

		// BudgetPersonnelCalculatedAmount
		List<BudgetPersonnelCalculatedAmount> budgetPersCalcAmountList = new ArrayList<BudgetPersonnelCalculatedAmount>();
		BudgetPersonnelCalculatedAmount budgetPersCalcAmount = (BudgetPersonnelCalculatedAmount) budgetPersDetails
				.getNewBudgetPersonnelCalculatedAmount();
		budgetPersCalcAmount.setBudgetId(budgetPersDetails.getBudgetId());
		budgetPersCalcAmount.setBudgetPeriod(budgetPersDetails
				.getBudgetPeriod());
		budgetPersCalcAmount.setLineItemNumber(budgetPersDetails
				.getLineItemNumber());
		budgetPersCalcAmount.setRateClassCode("1");
		budgetPersCalcAmount.setRateTypeCode("1");
		budgetPersCalcAmount.setRateClass(rateClass);
//		budgetPersCalcAmount.setRateType(rateType);
		budgetPersCalcAmount.setPersonNumber(budgetPersDetails
				.getPersonNumber());
		budgetPersCalcAmount.setVersionNumber(budgetPersDetails
				.getVersionNumber());
		budgetPersCalcAmount.setApplyRateFlag(true);
		budgetPersCalcAmount.setCalculatedCost(new BudgetDecimal(3794.77));
		budgetPersCalcAmount
				.setCalculatedCostSharing(new BudgetDecimal(345.45));
		budgetPersCalcAmountList.add(budgetPersCalcAmount);

		BudgetPersonnelCalculatedAmount budgetPersCalcAmount1 = (BudgetPersonnelCalculatedAmount) budgetPersDetails
				.getNewBudgetPersonnelCalculatedAmount();
		budgetPersCalcAmount1.setBudgetId(budgetPersDetails.getBudgetId());
		budgetPersCalcAmount1.setBudgetPeriod(budgetPersDetails
				.getBudgetPeriod());
		budgetPersCalcAmount1.setLineItemNumber(budgetPersDetails
				.getLineItemNumber());
		budgetPersCalcAmount1.setRateClassCode("5");
		budgetPersCalcAmount1.setRateTypeCode("1");
		budgetPersCalcAmount1.setRateClass(rateClass1);
//		budgetPersCalcAmount1.setRateType(rateType1);
		budgetPersCalcAmount1.setPersonNumber(budgetPersDetails
				.getPersonNumber());
		budgetPersCalcAmount1.setVersionNumber(budgetPersDetails
				.getVersionNumber());
		budgetPersCalcAmount1.setApplyRateFlag(true);
		budgetPersCalcAmount1.setCalculatedCost(new BudgetDecimal(3794.77));
		budgetPersCalcAmount1
				.setCalculatedCostSharing(new BudgetDecimal(345.45));
		budgetPersCalcAmountList.add(budgetPersCalcAmount1);

		BudgetPersonnelCalculatedAmount budgetPersCalcAmount2 = (BudgetPersonnelCalculatedAmount) budgetPersDetails
				.getNewBudgetPersonnelCalculatedAmount();
		budgetPersCalcAmount2.setBudgetId(budgetPersDetails.getBudgetId());
		budgetPersCalcAmount2.setBudgetPeriod(budgetPersDetails
				.getBudgetPeriod());
		budgetPersCalcAmount2.setLineItemNumber(budgetPersDetails
				.getLineItemNumber());
		budgetPersCalcAmount2.setRateClassCode("10");
		budgetPersCalcAmount2.setRateTypeCode("1");
		budgetPersCalcAmount2.setRateClass(rateClass2);
//		budgetPersCalcAmount2.setRateType(rateType2);
		budgetPersCalcAmount2.setPersonNumber(budgetPersDetails
				.getPersonNumber());
		budgetPersCalcAmount2.setVersionNumber(budgetPersDetails
				.getVersionNumber());
		budgetPersCalcAmount2.setApplyRateFlag(true);
		budgetPersCalcAmount2.setCalculatedCost(new BudgetDecimal(3794.77));
		budgetPersCalcAmount2
				.setCalculatedCostSharing(new BudgetDecimal(345.45));
		budgetPersCalcAmountList.add(budgetPersCalcAmount2);

		BudgetPersonnelCalculatedAmount budgetPersCalcAmount3 = (BudgetPersonnelCalculatedAmount) budgetPersDetails
				.getNewBudgetPersonnelCalculatedAmount();
		budgetPersCalcAmount3.setBudgetId(budgetPersDetails.getBudgetId());
		budgetPersCalcAmount3.setBudgetPeriod(budgetPersDetails
				.getBudgetPeriod());
		budgetPersCalcAmount3.setLineItemNumber(budgetPersDetails
				.getLineItemNumber());
		budgetPersCalcAmount3.setRateClassCode("11");
		budgetPersCalcAmount3.setRateTypeCode("1");
		budgetPersCalcAmount3.setRateClass(rateClass3);
//		budgetPersCalcAmount3.setRateType(rateType3);
		budgetPersCalcAmount3.setPersonNumber(budgetPersDetails
				.getPersonNumber());
		budgetPersCalcAmount3.setVersionNumber(budgetPersDetails
				.getVersionNumber());
		budgetPersCalcAmount3.setApplyRateFlag(true);
		budgetPersCalcAmount3.setCalculatedCost(new BudgetDecimal(3794.77));
		budgetPersCalcAmount3
				.setCalculatedCostSharing(new BudgetDecimal(345.45));
		budgetPersCalcAmountList.add(budgetPersCalcAmount3);

		BudgetPersonnelCalculatedAmount budgetPersCalcAmount4 = (BudgetPersonnelCalculatedAmount) budgetPersDetails
				.getNewBudgetPersonnelCalculatedAmount();
		budgetPersCalcAmount4.setBudgetId(budgetPersDetails.getBudgetId());
		budgetPersCalcAmount4.setBudgetPeriod(budgetPersDetails
				.getBudgetPeriod());
		budgetPersCalcAmount4.setLineItemNumber(budgetPersDetails
				.getLineItemNumber());
		budgetPersCalcAmount4.setRateClassCode("8");
		budgetPersCalcAmount4.setRateTypeCode("1");
		budgetPersCalcAmount4.setRateClass(rateClass4);
//		budgetPersCalcAmount4.setRateType(rateType4);
		budgetPersCalcAmount4.setPersonNumber(budgetPersDetails
				.getPersonNumber());
		budgetPersCalcAmount4.setVersionNumber(budgetPersDetails
				.getVersionNumber());
		budgetPersCalcAmount4.setApplyRateFlag(true);
		budgetPersCalcAmount4.setCalculatedCost(new BudgetDecimal(3794.77));
		budgetPersCalcAmount4
				.setCalculatedCostSharing(new BudgetDecimal(345.45));
		budgetPersCalcAmountList.add(budgetPersCalcAmount4);

		BudgetPersonnelCalculatedAmount budgetPersCalcAmount5 = (BudgetPersonnelCalculatedAmount) budgetPersDetails
				.getNewBudgetPersonnelCalculatedAmount();
		budgetPersCalcAmount5.setBudgetId(budgetPersDetails.getBudgetId());
		budgetPersCalcAmount5.setBudgetPeriod(budgetPersDetails
				.getBudgetPeriod());
		budgetPersCalcAmount5.setLineItemNumber(budgetPersDetails
				.getLineItemNumber());
		budgetPersCalcAmount5.setRateClassCode("8");
		budgetPersCalcAmount5.setRateTypeCode("2");
		budgetPersCalcAmount5.setRateClass(rateClass5);
//		budgetPersCalcAmount5.setRateType(rateType5);
		budgetPersCalcAmount5.setPersonNumber(budgetPersDetails
				.getPersonNumber());
		budgetPersCalcAmount5.setVersionNumber(budgetPersDetails
				.getVersionNumber());
		budgetPersCalcAmount5.setApplyRateFlag(true);
		budgetPersCalcAmount5.setCalculatedCost(new BudgetDecimal(3794.77));
		budgetPersCalcAmount5
				.setCalculatedCostSharing(new BudgetDecimal(345.45));
		budgetPersCalcAmountList.add(budgetPersCalcAmount5);
		budgetPersDetails
				.setBudgetPersonnelCalculatedAmounts(budgetPersCalcAmountList);
		// BudgetPersonnelRateAndBase
		List<BudgetPersonnelRateAndBase> budgetPersRateAndBaseList = new ArrayList<BudgetPersonnelRateAndBase>();
		BudgetPersonnelRateAndBase budgetPersRateAndBase = new BudgetPersonnelRateAndBase();
		budgetPersRateAndBase.setBudgetId(budgetPersCalcAmount1.getBudgetId());
		budgetPersRateAndBase.setPersonId(budgetPersDetails.getPersonId());
		budgetPersRateAndBase.setPersonNumber(budgetPersDetails
				.getPersonNumber());
		budgetPersRateAndBase.setSalaryRequested(new BudgetDecimal(200));
		budgetPersRateAndBase.setCalculatedCost(new BudgetDecimal(100));
		budgetPersRateAndBase.setCalculatedCostSharing(new BudgetDecimal(300));
		budgetPersRateAndBase.setBudgetPeriod(budgetPersDetails
				.getBudgetPeriod());
		budgetPersRateAndBase.setLineItemNumber(budgetPersDetails
				.getLineItemNumber());
		budgetPersRateAndBase.setRateClassCode("1");
		budgetPersRateAndBase.setRateTypeCode("1");
		budgetPersRateAndBase.setRateClass(rateClass);
		budgetPersRateAndBase.setOnOffCampusFlag(false);
		budgetPersRateAndBase.setAppliedRate(new BudgetDecimal(50));
		budgetPersRateAndBase.setRateNumber(10);
		budgetPersRateAndBase.setStartDate(java.sql.Date.valueOf("2007-06-01"));
		budgetPersRateAndBase.setEndDate(java.sql.Date.valueOf("2007-06-30"));
		budgetPersRateAndBase.setBudgetId(budget.getBudgetId());
		budgetPersRateAndBaseList.add(budgetPersRateAndBase);

		// BudgetRateAndBase
		List<BudgetRateAndBase> budgetRateAndBaseList = new ArrayList<BudgetRateAndBase>();
		BudgetRateAndBase budgetRateAndBase = new BudgetRateAndBase();
		budgetRateAndBase.setBudgetId(new Long(1));
		budgetRateAndBase.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
		budgetRateAndBase.setLineItemNumber(budgetLineItem.getLineItemNumber());
		budgetRateAndBase.setRateClassCode("1");
		budgetRateAndBase.setRateTypeCode("1");
		budgetRateAndBase.setRateNumber(10);
		budgetRateAndBase.setRateClass(rateClass);
		budgetRateAndBase.setStartDate(java.sql.Date.valueOf("2007-06-01"));
		budgetRateAndBase.setEndDate(java.sql.Date.valueOf("2007-06-30"));
		budgetRateAndBase.setOnOffCampusFlag(false);
		budgetRateAndBase.setBaseCost(new BudgetDecimal(234.54));
		budgetRateAndBase.setAppliedRate(new BudgetDecimal(50));
		budgetRateAndBase.setCalculatedCost(new BudgetDecimal(200));
		budgetRateAndBase.setCalculatedCostSharing(new BudgetDecimal(2345.76));
		budgetRateAndBase.setVersionNumber(budgetLineItem.getVersionNumber());
		budgetRateAndBaseList.add(budgetRateAndBase);

		budgetLineItem.setBudgetRateAndBaseList(budgetRateAndBaseList);
		budgetLineItem.setCostElementBO(costElementBO);
		budgetLineItemList.add(budgetLineItem);
		budgetLineItemList.add(budgetLineItem);
		firstPeriod.setBudgetLineItems(budgetLineItemList);
		List<BudgetPeriod> budgetPeriods = new ArrayList<BudgetPeriod>();

		budgetPeriods.add(firstPeriod);
		budget.setBudgetPeriods(budgetPeriods);
		budgets.add(budget);
		bd.setBudgets(budgets);
		bd.setParentDocument(pdDoc);

		// Data for BudgetOHExclusions
		BudgetRateAndBase budgetRateAndBase1 = new BudgetRateAndBase();
		budgetRateAndBase1.setBudgetId(new Long(1));
		budgetRateAndBase1.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
		budgetRateAndBase1
				.setLineItemNumber(budgetLineItem.getLineItemNumber());
		budgetRateAndBase1.setRateClassCode("5");
		budgetRateAndBase1.setRateTypeCode("1");
		budgetRateAndBase1.setRateNumber(1);
		budgetRateAndBase1.setRateClass(rateClass1);
		budgetRateAndBase1.setStartDate(java.sql.Date.valueOf("2009-06-01"));
		budgetRateAndBase1.setEndDate(java.sql.Date.valueOf("2009-06-30"));
		budgetRateAndBase1.setOnOffCampusFlag(false);
		budgetRateAndBase1.setBaseCost(new BudgetDecimal(234.54));
		budgetRateAndBase1.setAppliedRate(new BudgetDecimal(50));
		budgetRateAndBase1.setCalculatedCost(new BudgetDecimal(200));
		budgetRateAndBase1.setCalculatedCostSharing(new BudgetDecimal(2345.76));
		budgetRateAndBase1.setVersionNumber(budgetLineItem.getVersionNumber());
		budgetRateAndBaseList.add(budgetRateAndBase1);

		BudgetRateAndBase budgetRateAndBase2 = new BudgetRateAndBase();
		budgetRateAndBase2.setBudgetId(new Long(1));
		budgetRateAndBase2.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
		budgetRateAndBase2
				.setLineItemNumber(budgetLineItem.getLineItemNumber());
		budgetRateAndBase2.setRateClassCode("10");
		budgetRateAndBase2.setRateTypeCode("1");
		budgetRateAndBase2.setRateNumber(2);
		budgetRateAndBase2.setRateClass(rateClass2);
		budgetRateAndBase2.setStartDate(java.sql.Date.valueOf("2009-06-01"));
		budgetRateAndBase2.setEndDate(java.sql.Date.valueOf("2009-06-30"));
		budgetRateAndBase2.setOnOffCampusFlag(false);
		budgetRateAndBase2.setBaseCost(new BudgetDecimal(234.54));
		budgetRateAndBase2.setAppliedRate(new BudgetDecimal(50));
		budgetRateAndBase2.setCalculatedCost(new BudgetDecimal(200));
		budgetRateAndBase2.setCalculatedCostSharing(new BudgetDecimal(2345.76));
		budgetRateAndBase2.setVersionNumber(budgetLineItem.getVersionNumber());

		budgetRateAndBaseList.add(budgetRateAndBase2);
		BudgetRateAndBase budgetRateAndBase3 = new BudgetRateAndBase();
		budgetRateAndBase3.setBudgetId(new Long(1));
		budgetRateAndBase3.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
		budgetRateAndBase3
				.setLineItemNumber(budgetLineItem.getLineItemNumber());
		budgetRateAndBase3.setRateClassCode("11");
		budgetRateAndBase3.setRateTypeCode("1");
		budgetRateAndBase3.setRateNumber(3);
		budgetRateAndBase3.setRateClass(rateClass3);
		budgetRateAndBase3.setStartDate(java.sql.Date.valueOf("2009-06-01"));
		budgetRateAndBase3.setEndDate(java.sql.Date.valueOf("2009-06-30"));
		budgetRateAndBase3.setOnOffCampusFlag(false);
		budgetRateAndBase3.setBaseCost(new BudgetDecimal(234.54));
		budgetRateAndBase3.setAppliedRate(new BudgetDecimal(50));
		budgetRateAndBase3.setCalculatedCost(new BudgetDecimal(200));
		budgetRateAndBase3.setCalculatedCostSharing(new BudgetDecimal(2345.76));
		budgetRateAndBase3.setVersionNumber(budgetLineItem.getVersionNumber());
		budgetRateAndBaseList.add(budgetRateAndBase3);

		BudgetRateAndBase budgetRateAndBase4 = new BudgetRateAndBase();
		budgetRateAndBase4.setBudgetId(new Long(1));
		budgetRateAndBase4.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
		budgetRateAndBase4
				.setLineItemNumber(budgetLineItem.getLineItemNumber());
		budgetRateAndBase4.setRateClassCode("8");
		budgetRateAndBase4.setRateTypeCode("1");
		budgetRateAndBase4.setRateNumber(4);
		budgetRateAndBase4.setRateClass(rateClass4);
		budgetRateAndBase4.setStartDate(java.sql.Date.valueOf("2009-06-01"));
		budgetRateAndBase4.setEndDate(java.sql.Date.valueOf("2009-06-30"));
		budgetRateAndBase4.setOnOffCampusFlag(false);
		budgetRateAndBase4.setBaseCost(new BudgetDecimal(234.54));
		budgetRateAndBase4.setAppliedRate(new BudgetDecimal(50));
		budgetRateAndBase4.setCalculatedCost(new BudgetDecimal(200));
		budgetRateAndBase4.setCalculatedCostSharing(new BudgetDecimal(2345.76));
		budgetRateAndBase4.setVersionNumber(budgetLineItem.getVersionNumber());
		budgetRateAndBaseList.add(budgetRateAndBase4);

		BudgetRateAndBase budgetRateAndBase5 = new BudgetRateAndBase();
		budgetRateAndBase5.setBudgetId(new Long(1));
		budgetRateAndBase5.setBudgetPeriod(budgetLineItem.getBudgetPeriod());
		budgetRateAndBase5
				.setLineItemNumber(budgetLineItem.getLineItemNumber());
		budgetRateAndBase5.setRateClassCode("8");
		budgetRateAndBase5.setRateTypeCode("2");
		budgetRateAndBase5.setRateNumber(5);
		budgetRateAndBase5.setRateClass(rateClass5);
		budgetRateAndBase5.setStartDate(java.sql.Date.valueOf("2009-06-01"));
		budgetRateAndBase5.setEndDate(java.sql.Date.valueOf("2009-06-30"));
		budgetRateAndBase5.setOnOffCampusFlag(false);
		budgetRateAndBase5.setBaseCost(new BudgetDecimal(234.54));
		budgetRateAndBase5.setAppliedRate(new BudgetDecimal(50));
		budgetRateAndBase5.setCalculatedCost(new BudgetDecimal(200));
		budgetRateAndBase5.setCalculatedCostSharing(new BudgetDecimal(2345.76));
		budgetRateAndBase5.setVersionNumber(budgetLineItem.getVersionNumber());
		budgetRateAndBaseList.add(budgetRateAndBase5);
		// BudgetPersonnelRateAndBase
		BudgetPersonnelRateAndBase budgetPersRateAndBase1 = new BudgetPersonnelRateAndBase();
		budgetPersRateAndBase1.setBudgetId(budgetPersCalcAmount1.getBudgetId());
		budgetPersRateAndBase1.setPersonId(budgetPersDetails.getPersonId());
		budgetPersRateAndBase1.setPersonNumber(budgetPersDetails
				.getPersonNumber());
		budgetPersRateAndBase1.setSalaryRequested(new BudgetDecimal(200));
		budgetPersRateAndBase1.setCalculatedCost(new BudgetDecimal(100));
		budgetPersRateAndBase1.setCalculatedCostSharing(new BudgetDecimal(300));
		budgetPersRateAndBase1.setBudgetPeriod(budgetPersDetails
				.getBudgetPeriod());
		budgetPersRateAndBase1.setLineItemNumber(budgetPersDetails
				.getLineItemNumber());
		budgetPersRateAndBase1.setRateClassCode("5");
		budgetPersRateAndBase1.setRateTypeCode("1");
		budgetPersRateAndBase1.setRateClass(rateClass1);
		budgetPersRateAndBase1.setOnOffCampusFlag(false);
		budgetPersRateAndBase1.setAppliedRate(new BudgetDecimal(50));
		budgetPersRateAndBase1.setRateNumber(1);
		budgetPersRateAndBase1
				.setStartDate(java.sql.Date.valueOf("2009-06-01"));
		budgetPersRateAndBase1.setEndDate(java.sql.Date.valueOf("2009-06-30"));
		budgetPersRateAndBase1.setBudgetId(budget.getBudgetId());
		budgetPersRateAndBaseList.add(budgetPersRateAndBase1);

		BudgetPersonnelRateAndBase budgetPersRateAndBase2 = new BudgetPersonnelRateAndBase();
		budgetPersRateAndBase2.setBudgetId(budgetPersCalcAmount1.getBudgetId());
		budgetPersRateAndBase2.setPersonId(budgetPersDetails.getPersonId());
		budgetPersRateAndBase2.setPersonNumber(budgetPersDetails
				.getPersonNumber());
		budgetPersRateAndBase2.setSalaryRequested(new BudgetDecimal(200));
		budgetPersRateAndBase2.setCalculatedCost(new BudgetDecimal(100));
		budgetPersRateAndBase2.setCalculatedCostSharing(new BudgetDecimal(300));
		budgetPersRateAndBase2.setBudgetPeriod(budgetPersDetails
				.getBudgetPeriod());
		budgetPersRateAndBase2.setLineItemNumber(budgetPersDetails
				.getLineItemNumber());
		budgetPersRateAndBase2.setRateClassCode("10");
		budgetPersRateAndBase2.setRateTypeCode("1");
		budgetPersRateAndBase2.setRateClass(rateClass2);
		budgetPersRateAndBase2.setOnOffCampusFlag(false);
		budgetPersRateAndBase2.setAppliedRate(new BudgetDecimal(50));
		budgetPersRateAndBase2.setRateNumber(2);
		budgetPersRateAndBase2
				.setStartDate(java.sql.Date.valueOf("2009-06-01"));
		budgetPersRateAndBase2.setEndDate(java.sql.Date.valueOf("2009-06-30"));
		budgetPersRateAndBase2.setBudgetId(budget.getBudgetId());
		budgetPersRateAndBaseList.add(budgetPersRateAndBase2);

		BudgetPersonnelRateAndBase budgetPersRateAndBase3 = new BudgetPersonnelRateAndBase();
		budgetPersRateAndBase3.setBudgetId(budgetPersCalcAmount1.getBudgetId());
		budgetPersRateAndBase3.setPersonId(budgetPersDetails.getPersonId());
		budgetPersRateAndBase3.setPersonNumber(budgetPersDetails
				.getPersonNumber());
		budgetPersRateAndBase3.setSalaryRequested(new BudgetDecimal(200));
		budgetPersRateAndBase3.setCalculatedCost(new BudgetDecimal(100));
		budgetPersRateAndBase3.setCalculatedCostSharing(new BudgetDecimal(300));
		budgetPersRateAndBase3.setBudgetPeriod(budgetPersDetails
				.getBudgetPeriod());
		budgetPersRateAndBase3.setLineItemNumber(budgetPersDetails
				.getLineItemNumber());
		budgetPersRateAndBase3.setRateClassCode("11");
		budgetPersRateAndBase3.setRateTypeCode("1");
		budgetPersRateAndBase3.setRateClass(rateClass3);
		budgetPersRateAndBase3.setOnOffCampusFlag(false);
		budgetPersRateAndBase3.setAppliedRate(new BudgetDecimal(50));
		budgetPersRateAndBase3.setRateNumber(3);
		budgetPersRateAndBase3
				.setStartDate(java.sql.Date.valueOf("2009-06-01"));
		budgetPersRateAndBase3.setEndDate(java.sql.Date.valueOf("2009-06-30"));
		budgetPersRateAndBase3.setBudgetId(budget.getBudgetId());
		budgetPersRateAndBaseList.add(budgetPersRateAndBase3);

		BudgetPersonnelRateAndBase budgetPersRateAndBase4 = new BudgetPersonnelRateAndBase();
		budgetPersRateAndBase4.setBudgetId(budgetPersCalcAmount1.getBudgetId());
		budgetPersRateAndBase4.setPersonId(budgetPersDetails.getPersonId());
		budgetPersRateAndBase4.setPersonNumber(budgetPersDetails
				.getPersonNumber());
		budgetPersRateAndBase4.setSalaryRequested(new BudgetDecimal(200));
		budgetPersRateAndBase4.setCalculatedCost(new BudgetDecimal(100));
		budgetPersRateAndBase4.setCalculatedCostSharing(new BudgetDecimal(300));
		budgetPersRateAndBase4.setBudgetPeriod(budgetPersDetails
				.getBudgetPeriod());
		budgetPersRateAndBase4.setLineItemNumber(budgetPersDetails
				.getLineItemNumber());
		budgetPersRateAndBase4.setRateClassCode("8");
		budgetPersRateAndBase4.setRateTypeCode("1");
		budgetPersRateAndBase4.setRateClass(rateClass4);
		budgetPersRateAndBase4.setOnOffCampusFlag(false);
		budgetPersRateAndBase4.setAppliedRate(new BudgetDecimal(50));
		budgetPersRateAndBase4.setRateNumber(4);
		budgetPersRateAndBase4
				.setStartDate(java.sql.Date.valueOf("2009-06-01"));
		budgetPersRateAndBase4.setEndDate(java.sql.Date.valueOf("2009-06-30"));
		budgetPersRateAndBase4.setBudgetId(budget.getBudgetId());
		budgetPersRateAndBaseList.add(budgetPersRateAndBase4);

		BudgetPersonnelRateAndBase budgetPersRateAndBase5 = new BudgetPersonnelRateAndBase();
		budgetPersRateAndBase5.setBudgetId(budgetPersCalcAmount1.getBudgetId());
		budgetPersRateAndBase5.setPersonId(budgetPersDetails.getPersonId());
		budgetPersRateAndBase5.setPersonNumber(budgetPersDetails
				.getPersonNumber());
		budgetPersRateAndBase5.setSalaryRequested(new BudgetDecimal(200));
		budgetPersRateAndBase5.setCalculatedCost(new BudgetDecimal(100));
		budgetPersRateAndBase5.setCalculatedCostSharing(new BudgetDecimal(300));
		budgetPersRateAndBase5.setBudgetPeriod(budgetPersDetails
				.getBudgetPeriod());
		budgetPersRateAndBase5.setLineItemNumber(budgetPersDetails
				.getLineItemNumber());
		budgetPersRateAndBase5.setRateClassCode("8");
		budgetPersRateAndBase5.setRateTypeCode("2");
		budgetPersRateAndBase5.setRateClass(rateClass5);
		budgetPersRateAndBase5.setOnOffCampusFlag(false);
		budgetPersRateAndBase5.setAppliedRate(new BudgetDecimal(50));
		budgetPersRateAndBase5.setRateNumber(5);
		budgetPersRateAndBase5
				.setStartDate(java.sql.Date.valueOf("2009-06-01"));
		budgetPersRateAndBase5.setEndDate(java.sql.Date.valueOf("2009-06-30"));
		budgetPersRateAndBase5.setBudgetId(budget.getBudgetId());
		budgetPersRateAndBaseList.add(budgetPersRateAndBase5);

		budgetPersDetails
				.setBudgetPersonnelRateAndBaseList(budgetPersRateAndBaseList);
		budgetPersonnelDetailsList.add(budgetPersDetails);
		budgetLineItem
				.setBudgetPersonnelDetailsList(budgetPersonnelDetailsList);

		ProposalBudgetStatus proposalStatus = new ProposalBudgetStatus();
		proposalStatus.setProposalNumber(pdDoc.getDevelopmentProposal()
				.getProposalNumber());
		proposalStatus.setBudgetStatusCode(pdDoc.getDevelopmentProposal()
				.getBudgetStatus());
		getBusinessObjectService().save(proposalStatus);
		try {
			// KNSServiceLocator.getDocumentService().saveDocument(bd);
			getBusinessObjectService().save(bd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bd;
	}

	public static ProposalDevelopmentDocument getProposalDevelopmentDocument() {
		ProposalDevelopmentDocument pd = null;
		try {
			pd = (ProposalDevelopmentDocument) getDocumentService()
					.getNewDocument("ProposalDevelopmentDocument");
		} catch (WorkflowException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		DevelopmentProposal proposal = new DevelopmentProposal();
		proposal.refreshReferenceObject("activityType");
		proposal.setActivityTypeCode("1");
		proposal.setSponsorCode("000162");
		proposal.setOwnedByUnitNumber("000001");
		proposal.refreshReferenceObject("ownedByUnit");
		proposal.setProposalTypeCode("1");
		proposal.setCreationStatusCode("1");
		proposal.setPerformingOrganizationId("000001");
		proposal.setNoticeOfOpportunityCode("1");
		proposal.setRequestedStartDateInitial(new java.sql.Date(Calendar
				.getInstance().getTimeInMillis()));
		proposal.setRequestedEndDateInitial(new java.sql.Date(Calendar
				.getInstance().getTimeInMillis()));
		proposal.setTitle("Test service title");
		proposal.setDeadlineType("P");
		proposal.setDeadlineDate(new java.sql.Date(Calendar.getInstance()
				.getTimeInMillis()));
		proposal.setNsfCode("J.05");
		proposal.setProposalNumber("1001");
		List<ProposalSite> proposalSites = new ArrayList<ProposalSite>();
		ProposalSite pSite = new ProposalSite();
		pSite.setProposalNumber("1001");
		pSite.setLocationName("Performance site");
		pSite.setLocationTypeCode(4);
		pSite.setUpdateTimestamp(new java.sql.Timestamp(Calendar.getInstance()
				.getTimeInMillis()));
		pSite.setUpdateUser("quickst");
		proposalSites.add(pSite);
		proposal.setProposalSites(proposalSites);
		pd.setDevelopmentProposal(proposal);
		pd.setUpdateUser("quickstart");
		pd.setUpdateTimestamp(new java.sql.Timestamp(Calendar.getInstance()
				.getTimeInMillis()));
		List<PessimisticLock> pLockList = new ArrayList<PessimisticLock>();
		PessimisticLock pLock = new PessimisticLock();// TODO
		org.kuali.rice.kim.bo.Person p = new PersonImpl();
		pLock.setOwnedByUser(p);
		pLock.setLockDescriptor("PROPOSAL");
		pd.setPessimisticLocks(pLockList);

		DocumentHeader docHeader = pd.getDocumentHeader();
		docHeader.setDocumentDescription("Test description");
		pd.getDevelopmentProposal().setBudgetStatus("1");
		getBusinessObjectService().save(pd);
		return pd;
	}
	
	public static NegotiationDocument getNegotiationDocument() {
        NegotiationDocument negotiationDocument = null;
        try {
            negotiationDocument = (NegotiationDocument) getDocumentService()
                    .getNewDocument("NegotiationDocument");
        } catch (WorkflowException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Negotiation negotiation = new Negotiation();        
        negotiation.setNegotiatorPersonId("1");        
        negotiation.setNegotiationStartDate(new Date(System.currentTimeMillis()));
        negotiation.setDocumentFolder("DocumentFolder");           
        
        NegotiationUnassociatedDetail negotiationUnassociatedDetail = new NegotiationUnassociatedDetail();
        negotiationUnassociatedDetail.setTitle("Title");
        negotiationUnassociatedDetail.setPiName("principle Investigator");
        
        Unit leadUnit = new Unit();
        leadUnit.setUnitName("University");
        leadUnit.setUnitNumber("000001");       
        negotiationUnassociatedDetail.setLeadUnit(leadUnit);
        
        Sponsor sponsor = new Sponsor();
        SponsorType sponsorType = new SponsorType();        
        sponsor.setSponsorName("Sponsor Name");
        sponsorType.setSponsorTypeCode("1");
        sponsor.setSponsorType(sponsorType);        
        negotiationUnassociatedDetail.setSponsor(sponsor);  
        negotiationUnassociatedDetail.setContactAdminPersonId("1");       
        
        NegotiationStatus negotiationStatus = new NegotiationStatus();
        negotiationStatus.setDescription("Description");
        
        NegotiationAssociationType negotiationAssociationType = new NegotiationAssociationType();
        negotiationAssociationType.setCode("PL");         
        
        List<NegotiationActivity> negotiationActivities =new ArrayList<NegotiationActivity>();        
        NegotiationActivity  negotiationActivity = new NegotiationActivity();
        NegotiationActivityType negotiationActivityType = new NegotiationActivityType();
        negotiationActivity.setDescription("Activity 1");
        negotiationActivity.setCreateDate(new Date(System.currentTimeMillis()));
        negotiationActivity.setFollowupDate(new Date(System.currentTimeMillis()));       
        negotiationActivity.setStartDate(new Date(System.currentTimeMillis())); 
        negotiationActivityType.setDescription("Activity 1");
        negotiationActivity.setActivityType(negotiationActivityType);        
        negotiationActivities.add(negotiationActivity);        
        negotiationActivity.setUpdateUser("quickstart");
        
        negotiation.setNegotiationStatus(negotiationStatus);
        List<Negotiation> negotiationList = new ArrayList<Negotiation>();
        negotiationList.add(negotiation);
        negotiationDocument.setNegotiationList(negotiationList);
        return negotiationDocument;
    }
 

	public static Map<String, Object> getInstituteProposalReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		return reportParamsMap;
	}

	public static Map<String, Object> getPendingProposalReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		reportParamsMap.put(PERSON_ID, 1);
		return reportParamsMap;
	}

	public static Map<String, Object> getCurrentProposalReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		reportParamsMap.put(PERSON_ID, 1);
		return reportParamsMap;
	}

	public static InstitutionalProposalDocument getInstituteProposalDocument() {
		InstitutionalProposalDocument ipd = null;
		try {
			ipd = (InstitutionalProposalDocument) getDocumentService()
					.getNewDocument("InstitutionalProposalDocument");
		} catch (WorkflowException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		InstitutionalProposal proposal = new InstitutionalProposal();
		proposal.setProposalNumber("1001");
		proposal.setCurrentAccountNumber("1002");
		proposal.setCurrentAwardNumber("1003");
		proposal.setSponsorProposalNumber("1004");
		proposal.setProposalTypeCode(1);
		proposal.setGradStudPersonMonths(new KualiDecimal(12));
		proposal.setTypeOfAccount(true);
		proposal.setSequenceNumber(1);
		proposal.setSubcontractFlag(true);

		AwardType awardType = new AwardType();
		awardType.setAwardTypeCode(1);
		awardType.setDescription("description");
		proposal.setAwardType(awardType);

		InstitutionalProposalScienceKeyword ipscienceKeyword = new InstitutionalProposalScienceKeyword();
		ScienceKeyword scienceKeyword = new ScienceKeyword();
		scienceKeyword.setScienceKeywordCode("scienceCode");
		ipscienceKeyword.setScienceKeyword(scienceKeyword);
		ipscienceKeyword.setScienceKeywordCode("scienceCode");
		ipscienceKeyword
				.setScienceKeywordDescription("scienceKeywordDescription");
		proposal.getInstitutionalProposalScienceKeywords()
				.add(ipscienceKeyword);

		InstitutionalProposalCostShare ipCostShare = new InstitutionalProposalCostShare();
		ipCostShare.setAmount(new KualiDecimal(1000));
		ipCostShare.setCostSharePercentage(new KualiDecimal(10));
		ipCostShare.setCostShareTypeCode(1);
		ipCostShare.setProjectPeriod("2009");
		ipCostShare.setProposalCostShareId(1L);
		ipCostShare.setSourceAccount("1");
		proposal.getInstitutionalProposalCostShares().add(ipCostShare);

		proposal.getSummaryComment().setComments("Proposal Summary comments");

		ipd.setInstitutionalProposal(proposal);
		return ipd;
	}

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

	public static void saveXml(XmlObject xmlObject, String reportName) {
		try {
			xmlObject.save(new File(PrintingTestUtils.FILE_DIR + File.separator + reportName
					+ ".xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Map<String, Object> getAwardDeltaReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		reportParamsMap.put(SIGNATURE_REQUIRED, true);
		reportParamsMap.put(SEQUENCE_NUMBER, 2);
		reportParamsMap.put(AMOUNT_SEQUENCE_NUMBER, 2);
		return reportParamsMap;
	}

	public static Map<String, Object> getAwardNoticeReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		reportParamsMap.put(ADDRESS_LIST, true);
		reportParamsMap.put(CLOSEOUT, true);
		reportParamsMap.put(COMMENTS, true);
		reportParamsMap.put(COST_SHARING, true);
		reportParamsMap.put(EQUIPMENT, true);
		reportParamsMap.put(FLOW_THRU, true);
		reportParamsMap.put(FOREIGN_TRAVEL, true);
		reportParamsMap.put(HIERARCHY_INFO, true);
		reportParamsMap.put(INDIRECT_COST, true);
		reportParamsMap.put(PAYMENT, true);
		reportParamsMap.put(PROPOSAL_DUE, true);
		reportParamsMap.put(SCIENCE_CODE, true);
		reportParamsMap.put(SUBCONTRACT, true);
		reportParamsMap.put(SPECIAL_REVIEW, true);
		reportParamsMap.put(TERMS, true);
		reportParamsMap.put(TECHNICAL_REPORTING, true);
		reportParamsMap.put(SIGNATURE_REQUIRED, true);
		reportParamsMap.put(REPORTING, true);
		return reportParamsMap;
	}

	public static Map<String, Object> getBudgetSummaryReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		return reportParamsMap;
	}

	public static Map<String, Object> getBudgetSalaryReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		return reportParamsMap;
	}

	public static Map<String, Object> getBudgetTotalReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		return reportParamsMap;
	}

	public static Map<String, Object> getBudgetSummaryTotalReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		return reportParamsMap;
	}

	public static Map<String, Object> getIndustrialCumulativeBudgetReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		return reportParamsMap;
	}

	public static Map<String, Object> getBudgetCumulativeReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		return reportParamsMap;
	}

	public static Map<String, Object> getIndustrialBudgetReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		return reportParamsMap;
	}

	public static Map<String, Object> getCostSharingSummaryByPeriodReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		return reportParamsMap;
	}

	public static Map<String, Object> getPrintCertificationParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		return reportParamsMap;
	}

	public static void main(String[] args) {
		getAwardDocument();
	}

	public static Map<String, Object> getAwardTemplateReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		return reportParamsMap;
	}

	public static Map<String, Object> getProposalDevelopmentReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		return reportParamsMap;
	}

	public static Map<String, Object> getMoneyAndEndDatesHistoryReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		//TODO Set the proper Value.
		reportParamsMap.put(OBLIGATED_DIRECT_INDIRECT_COST_CONSTANT, null);
		return reportParamsMap;
	}

	public static Map<String, Object> getAwardBudgetHierarchyXmlStream() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		return reportParamsMap;
	}

	public static Map<String, Object> getAwardBudgetHistoryTransactionReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		reportParamsMap.put(TRANSACTION_ID, null);
		//TODO need to put all the parameters associated with highestTransactionIdIndex.( AwardPrintParameters.HIGHEST_TRANSACTION_ID_INDEX
//		.getAwardPrintParameter() )
		return reportParamsMap;
	}
	public static Map<String, Object> getResearchAndRelatedReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		return reportParamsMap;
	}
	public static Map<String, Object> getProposalSubmissionReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		return reportParamsMap;
	}
	public static Map<String, Object> getInstitutionalProposalReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		return reportParamsMap;
	}
	public static Map<String, Object> getProposalDevelopmentXmlStreamReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		return reportParamsMap;
	}
	
	public static Map<String, Object> getBudgetBaseSalaryStreamReportParameters() {
		Map<String, Object> reportParamsMap = new HashMap<String, Object>();
		return reportParamsMap;
	}
	
	public static Map<String, Object> getNegotiationActivityXmlStreamReportParameters() {
        Map<String, Object> reportParamsMap = new HashMap<String, Object>();
        return reportParamsMap;
    }
}
