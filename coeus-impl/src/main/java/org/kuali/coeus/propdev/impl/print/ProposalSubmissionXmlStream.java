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

import edu.mit.coeus.utils.xml.v2.lookuptypes.ACTIVITYTYPEDocument.ACTIVITYTYPE;
import edu.mit.coeus.utils.xml.v2.lookuptypes.APPLICABLEREVIEWTYPEDocument.APPLICABLEREVIEWTYPE;
import edu.mit.coeus.utils.xml.v2.lookuptypes.NOTICEOFOPPORTUNITYDocument.NOTICEOFOPPORTUNITY;
import edu.mit.coeus.utils.xml.v2.lookuptypes.PROPOSALTYPEDocument.PROPOSALTYPE;
import edu.mit.coeus.utils.xml.v2.lookuptypes.SPECIALREVIEWDocument.SPECIALREVIEW;
import edu.mit.coeus.utils.xml.v2.organization.ORGANIZATIONDocument.ORGANIZATION;
import edu.mit.coeus.utils.xml.v2.propdev.PROPINVESTIGATORTYPE;
import edu.mit.coeus.utils.xml.v2.propdev.PROPOSALDocument;
import edu.mit.coeus.utils.xml.v2.propdev.PROPOSALDocument.PROPOSAL;
import edu.mit.coeus.utils.xml.v2.propdev.PROPOSALMASTERDocument.PROPOSALMASTER;
import edu.mit.coeus.utils.xml.v2.propdev.PROPPERCREDITSPLITDocument.PROPPERCREDITSPLIT;
import edu.mit.coeus.utils.xml.v2.propdev.PROPSPECIALREVIEWDocument.PROPSPECIALREVIEW;
import edu.mit.coeus.utils.xml.v2.propdev.PROPUNITCREDITSPLITDocument.PROPUNITCREDITSPLIT;
import edu.mit.coeus.utils.xml.v2.propdev.PROPUNITSDocument.PROPUNITS;
import edu.mit.coeus.utils.xml.v2.propdev.PROPYNQDocument.PROPYNQ;
import edu.mit.coeus.utils.xml.v2.rolodex.ADDRESSDocument.ADDRESS;
import edu.mit.coeus.utils.xml.v2.rolodex.NAMEDocument.NAME;
import edu.mit.coeus.utils.xml.v2.rolodex.ROLODEXDocument.ROLODEX;
import edu.mit.coeus.utils.xml.v2.sponsor.SPONSORDocument.SPONSOR;
import edu.mit.coeus.utils.xml.v2.userUnit.UNITDocument.UNIT;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.noo.NoticeOfOpportunity;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.editable.ProposalChangedData;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalPersonCreditSplit;
import org.kuali.coeus.propdev.impl.ynq.ProposalYnq;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.*;

@Component("proposalSubmissionXmlStream")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProposalSubmissionXmlStream extends ProposalBaseStream {

	private static final String DOES_REQUIRE = "does require";
	private static final String DOES_NOT_REQUIRE = "does not require";
	private static final String COI_DEFAULT_VALUE = "__________";
	private static final String INCOMPLETE_STRING_TERMINATOR = "...";
	private static final String CONFLICT_OF_INTEREST = "CONFLICT_OF_INTEREST";
	private static final String NOTICE_OF_OPPORTUNITY_CODE = "noticeOfOpportunityCode";
	private static final String MAIL_SUBMISSINON_METHOD_DHL = "DHL";
	private static final String MAIL_SUBMISSINON_METHOD_REGULAR = "Regular";
	private static final String MAIL_SUBMISSINON_METHOD_ELECTRONIC = "Electronic";
	private static final String MAIL_TYPE_DHL = "D";
	private static final String MAIL_TYPE_REGULAR = "R";
	private static final String MAIL_TYPE_ELECTRONIC = "E";
	private static final String ABSTRACT_TYPE_CODE_MAILING = "16";
	private static final String ABSTRACT_TYPE_CODE_OTHER = "17";
	private static final String STRING_DOUBLE_SEPARATOR = "--";
	private static final String INVESTIGATOR_CREDIT_TYPE_CODE_TWO = "2";
	private static final String INVESTIGATOR_CREDIT_TYPE_CODE_ONE = "1";
	private static final String IDENTIFIER_NO = "No";
	private static final String IDENTIFIER_YES = "Yes";
	private static final String NOT_AVAILABLE = "N/A";
	private static final String PROPOSAL_CHANGE_DATA_COLUMN_NAME_ITAP_FLAG_3 = "ITAP_FLAG_3";
	private static final String PROPOSAL_CHANGE_DATA_COLUMN_NAME_ITAP_FLAG_2 = "ITAP_FLAG_2";
	private static final String PROPOSAL_CHANGE_DATA_COLUMN_NAME_ITAP_FLAG_1 = "ITAP_FLAG_1";
	private static final String QUESTION_ID_E = "E";
	private static final String QUESTION_ID_D = MAIL_TYPE_DHL;
	private static final String QUESTION_ID_C = "C";
	private static final String QUESTION_ID_B = "B";
	private static final String QUESTION_ID_A = "A";
	private static final String QUESTION_ID_G8 = "G8";
	private static final String PROPOSAL_YNQ_ID_P_03 = "P-03";
	private static final String PROPOSAL_YNQ_ID_P_02 = "P-02";
	private static final String PROPOSAL_YNQ_ID_P_01 = "P-01";
	private static final String CENTER_INSTITUTE_CODE5 = "CENTER_INSTITUTE_CODE5";
	private static final String CENTER_INSTITUTE_CODE4 = "CENTER_INSTITUTE_CODE4";
	private static final String CENTER_INSTITUTE_CODE3 = "CENTER_INSTITUTE_CODE3";
	private static final String CENTER_INSTITUTE_CODE2 = "CENTER_INSTITUTE_CODE2";
	private static final String CENTER_INSTITUTE_CODE = "CENTER_INSTITUTE_CODE";
	private static final String NEW_LINE = "/n";
	private static final String SPONSOR_DEADLINE_RECEIPT_DESCRIPTION = "(Receipt)";
	private static final String SPONSOR_DEADLINE_TYPE_RECEIPT = "R";
	private static final String SPONSOR_DEADLINE_POSTMARK_DESCRIPTION = "(Postmark)";
	private static final String SPONSOR_DEADLINE_TYPE_POSTMARK = "P";
	private static final String STRING_SEPRATOR = "-";
	private static final String BLANK_STRING = " ";
	private static final String OPEN_BRACES = "(";
	private static final String CLOSE_BRACES = ")";
	private static final String IMAGES_PATH = "/images/";
	private static final String REPORT_NAME = "Proposal Submission";

	/**
	 * This method generates XML for Proposal Development Report. It uses data
	 * passed in {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} for populating the XML nodes. The
	 * XMl once generated is returned as {@link XmlObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XmlObject} representing the XML
	 * @throws PrintingException
	 *             in case of any errors occur during XML generation.
	 */
	public Map<String, XmlObject> generateXmlStream(
			KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		DevelopmentProposal developmentProposal = (DevelopmentProposal) printableBusinessObject;
		Budget budget = getBudget(developmentProposal.getProposalDocument());
		PROPOSALDocument proposalDocument = PROPOSALDocument.Factory
				.newInstance();
		PROPOSAL proposal = getProposal(developmentProposal, budget);
		proposalDocument.setPROPOSAL(proposal);

		Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
		xmlObjectList.put(REPORT_NAME, proposalDocument);
		return xmlObjectList;
	}

	/*
	 * This method gets PROPOSAL XMLObject and set data to it from
	 * developmentProposal
	 */
	private PROPOSAL getProposal(DevelopmentProposal developmentProposal,
			Budget budget) {
		PROPOSAL proposal = PROPOSAL.Factory.newInstance();
		proposal.setPROPOSALMASTER(getProposalMaster(developmentProposal,
				budget));
		proposal.setLOGOPATH(IMAGES_PATH);
		proposal.setPROPUNITSArray(getPropUnits(developmentProposal
				.getProposalPersons()));
		proposal
				.setPROPINVESTIGATORSArray(getProposalInvestigators(developmentProposal.getProposalPersons()));
		proposal
				.setPROPSPECIALREVIEWArray(getPropSpecialReviews(developmentProposal
						.getPropSpecialReviews()));
		proposal.setPROPYNQArray(getProposalYNQs(developmentProposal
				.getProposalYnqs(), developmentProposal
				.getProposalChangedDataList()));
		proposal.setCURDATE(getDateTimeService().getCurrentDate().toString());
		return proposal;
	}

	/*
	 * This method gets arrays of PROPYNQ XMLObject by setting data from list of
	 * proposalYnqs and list of proposalChangedData data by checking the
	 * Question id
	 */
	private PROPYNQ[] getProposalYNQs(List<ProposalYnq> proposalYnqs,
			List<ProposalChangedData> proposalChangedDataList) {
		List<PROPYNQ> propYnqList = new ArrayList<PROPYNQ>();
		PROPYNQ propYnqG8 = getProposalYnqByQuestionId(QUESTION_ID_G8,
				PROPOSAL_YNQ_ID_P_01, proposalYnqs);
		if (propYnqG8 != null) {
			propYnqList.add(propYnqG8);
		}
		PROPYNQ propYnqA = getProposalYnqByQuestionId(QUESTION_ID_A,
				PROPOSAL_YNQ_ID_P_02, proposalYnqs);
		if (propYnqA != null) {
			propYnqList.add(propYnqA);
		}
		PROPYNQ propYnqB = getProposalYnqByQuestionId(QUESTION_ID_B,
				PROPOSAL_YNQ_ID_P_03, proposalYnqs);
		if (propYnqB != null) {
			propYnqList.add(propYnqB);
		}
		setQuestionAnswerForProposalYNQS(proposalChangedDataList, propYnqList);
		return propYnqList.toArray(new PROPYNQ[0]);
	}

	/*
	 * This method sets Question and Answer to propYnq XMLObject from
	 * proposalChangeData List by checking the ITAP flag of proposalChangeData
	 */
	private void setQuestionAnswerForProposalYNQS(
			List<ProposalChangedData> proposalChangedDataList,
			List<PROPYNQ> propYnqList) {
		PROPYNQ propYnqC = PROPYNQ.Factory.newInstance();
		propYnqC.setQUESTIONID(QUESTION_ID_C);
		propYnqC.setANSWER(getProposalChangeDataAnswer(
				PROPOSAL_CHANGE_DATA_COLUMN_NAME_ITAP_FLAG_1,
				proposalChangedDataList));
		propYnqList.add(propYnqC);
		PROPYNQ propYnqD = PROPYNQ.Factory.newInstance();
		propYnqD.setQUESTIONID(QUESTION_ID_D);
		propYnqD.setANSWER(getProposalChangeDataAnswer(
				PROPOSAL_CHANGE_DATA_COLUMN_NAME_ITAP_FLAG_2,
				proposalChangedDataList));
		propYnqList.add(propYnqD);
		PROPYNQ propYnqE = PROPYNQ.Factory.newInstance();
		propYnqE.setQUESTIONID(QUESTION_ID_E);
		propYnqE.setANSWER(getProposalChangeDataAnswer(
				PROPOSAL_CHANGE_DATA_COLUMN_NAME_ITAP_FLAG_3,
				proposalChangedDataList));
		propYnqList.add(propYnqE);
	}

	/*
	 * This method gets ProposalChageData Answer by column name from
	 * ProposalChangeData
	 */
	private String getProposalChangeDataAnswer(String columenName,
			List<ProposalChangedData> proposalChangedDataList) {
		ProposalChangedData proposalChangeData = null;
		String columnValue = Constants.EMPTY_STRING;
		for (ProposalChangedData propChangeData : proposalChangedDataList) {
			if (propChangeData.getColumnName().equals(columenName)) {
				proposalChangeData = propChangeData;
				break;
			}
		}
		if (proposalChangeData != null) {
			columnValue = proposalChangeData.getChangedValue();
		}
		return columnValue;
	}

	/*
	 * This method gets PROPYNQ XMLObeject details by QuestionIds
	 */
	private PROPYNQ getProposalYnqByQuestionId(String questionId1,
			String questionId2, List<ProposalYnq> proposalYnqs) {
		PROPYNQ propYnq = null;
		ProposalYnq proposalYnq = getProposalYnqById(questionId2, proposalYnqs);
		if (proposalYnq != null) {
			propYnq = PROPYNQ.Factory.newInstance();
			propYnq.setQUESTIONID(questionId1);
			propYnq.setANSWER(getProposalYnqAnswerDetail(proposalYnq
					.getAnswer()));
			propYnq.setEXPLANATION(proposalYnq.getExplanation());
		}
		return propYnq;
	}

	/*
	 * This method gets answerDetails of Answer like Y-Yes, N-No from answer
	 */
	private String getProposalYnqAnswerDetail(String answer) {
		String detailAnswer = NOT_AVAILABLE;
		if (answer.equals(Constants.TRUE_FLAG)) {
			detailAnswer = IDENTIFIER_YES;
		}
		if (answer.equals(Constants.FALSE_FLAG)) {
			detailAnswer = IDENTIFIER_NO;
		}
		return detailAnswer;
	}

	/*
	 * This method gets ProposalYnq by questionId by checking in list of
	 * proposalYnq
	 */
	private ProposalYnq getProposalYnqById(String questionId,
			List<ProposalYnq> proposalYnqs) {
		ProposalYnq proposalYnq = null;
		for (ProposalYnq propYnq : proposalYnqs) {
			if (propYnq.getQuestionId().equals(questionId)) {
				proposalYnq = propYnq;
				break;
			}
		}
		return proposalYnq;
	}

	/*
	 * This method gets arrays of PROPSPECIALREVIEW XMLObject by setting the
	 * data to each PROPSPECIALREVIEW from list of ProposalSpecialReview object
	 * and add it to PROPSPECIALREVIEW List
	 */
	private PROPSPECIALREVIEW[] getPropSpecialReviews(
			List<ProposalSpecialReview> proposalSpecialReviewList) {
		List<PROPSPECIALREVIEW> propSplReviewList = new ArrayList<PROPSPECIALREVIEW>();
		for (ProposalSpecialReview specialReview : proposalSpecialReviewList) {
			PROPSPECIALREVIEW propSplReview = PROPSPECIALREVIEW.Factory
					.newInstance();
			APPLICABLEREVIEWTYPE appReviewType = APPLICABLEREVIEWTYPE.Factory
					.newInstance();
			appReviewType.setAPPLICABLEREVIEWTYPECODE(0);
			appReviewType.setDESCRIPTION(specialReview
					.getApprovalType().getDescription());
			propSplReview.setAPPLICABLEREVIEWTYPE(appReviewType);
			SPECIALREVIEW splReview = SPECIALREVIEW.Factory.newInstance();
			splReview.setSPECIALREVIEWCODE(0);
			splReview.setDESCRIPTION(specialReview.getSpecialReviewType()
					.getDescription());
			propSplReview.setSPECIALREVIEW(splReview);
			propSplReview.setPROTOCOLNUMBER(specialReview.getProtocolNumber());
			propSplReview.setPROPOSALNUMBER(specialReview.getDevelopmentProposal().getProposalNumber());
			propSplReviewList.add(propSplReview);
		}
		return propSplReviewList.toArray(new PROPSPECIALREVIEW[0]);
	}

	/*
	 * This method gets arrays of PROPINVESTIGATORS XMLObject and set data to it
	 * from list of proposalPerson
	 */
	private PROPINVESTIGATORTYPE[] getProposalInvestigators(
			List<ProposalPerson> proposalPersons) {
		List<PROPINVESTIGATORTYPE> propInvestigatorList = new ArrayList<PROPINVESTIGATORTYPE>();
		for (ProposalPerson propPerson : proposalPersons) {
		    PROPINVESTIGATORTYPE propInvestigator = PROPINVESTIGATORTYPE.Factory
					.newInstance();
			propInvestigator
					.setPERSONNAME(propPerson.getPerson().getFullName());
			propInvestigator.setPRINCIPALINVESTIGATORFLAG(propPerson
					.getProposalPersonRoleId());
			PROPPERCREDITSPLIT propPersonCredit = PROPPERCREDITSPLIT.Factory
					.newInstance();
			propPersonCredit
					.setCREDIT(getCreditForProposalInvestigator(propPerson));
			propPersonCredit
					.setPROPUNITCREDITSPLITArray(getProposalUnitCreditSplits(propPerson));
			propInvestigator.setPROPPERCREDITSPLIT(propPersonCredit);
			propInvestigatorList.add(propInvestigator);
		}
		return propInvestigatorList.toArray(new PROPINVESTIGATORTYPE[0]);
	}

	/*
	 * This method gets credit from List of ProposalPersonCreditSplit by
	 * checking the Investigator credit TypeCode is 1
	 */
	private String getCreditForProposalInvestigator(ProposalPerson propPerson) {
		String credit = Constants.EMPTY_STRING;
		for (ProposalPersonCreditSplit creditSplit : propPerson
				.getCreditSplits()) {
			if (creditSplit.getInvCreditTypeCode().equals(
					INVESTIGATOR_CREDIT_TYPE_CODE_ONE)) {
				credit = creditSplit.getCredit().toString();
				break;
			}
		}
		return credit;
	}

	/*
	 * This method gets Proposal Unit Splits XMLObject array by setting the data
	 * to Proposal Unit Splits and add it to List list of
	 * ProposalPersonCreditSplit
	 */
	private PROPUNITCREDITSPLIT[] getProposalUnitCreditSplits(
			ProposalPerson propPerson) {
		List<PROPUNITCREDITSPLIT> propUnitCreditSplitList = new ArrayList<PROPUNITCREDITSPLIT>();
		String unit = getUnitForCreditSplit(propPerson.getUnit());
		for (ProposalPersonCreditSplit creditSplit : propPerson
				.getCreditSplits()) {
			if (creditSplit.getInvCreditTypeCode().equals(
					INVESTIGATOR_CREDIT_TYPE_CODE_ONE)
					|| creditSplit.getInvCreditTypeCode().equals(
							INVESTIGATOR_CREDIT_TYPE_CODE_TWO)) {
				PROPUNITCREDITSPLIT propUnitCreditSplit = PROPUNITCREDITSPLIT.Factory
						.newInstance();
				propUnitCreditSplit.setUNIT(unit);
				propUnitCreditSplit
						.setPRIMARY(creditSplit.getCredit() == null ? STRING_DOUBLE_SEPARATOR
								: creditSplit.getCredit().toString());
				propUnitCreditSplit
						.setSECONDARY(creditSplit.getCredit() == null ? STRING_DOUBLE_SEPARATOR
								: creditSplit.getCredit().toString());
				propUnitCreditSplitList.add(propUnitCreditSplit);
			}
		}
		return propUnitCreditSplitList.toArray(new PROPUNITCREDITSPLIT[0]);
	}

	/*
	 * This method gets Unit for creditSplit by concatenating unitNumber and
	 * unitName
	 */
	private String getUnitForCreditSplit(Unit unit) {
		String unitCredit = null;
		if (unit != null) {
			String unitName = unit.getUnitName();
			unitCredit = new StringBuilder(unit.getUnitNumber()).append(
					STRING_SEPRATOR).append(unitName.substring(0, 29)).append(
					unitName.length() <= 30 ? Constants.EMPTY_STRING
							: INCOMPLETE_STRING_TERMINATOR).toString();
		}
		return unitCredit;
	}

	/*
	 * This methods gets arrays of PROPUNITS and set data to each PROPUNIT from
	 * ProposalPersonUnit. here List of ProposalPersonUnit get created by
	 * checking leadUnit
	 */
	private PROPUNITS[] getPropUnits(List<ProposalPerson> proposalPersonList) {
		List<PROPUNITS> propUnitsList = new ArrayList<PROPUNITS>();
		List<ProposalPersonUnit> propPersonUnitList = new ArrayList<ProposalPersonUnit>();
		for (ProposalPerson pPerson : proposalPersonList) {
			for (ProposalPersonUnit propPersonUnit : pPerson.getUnits()) {
				if (propPersonUnit.isLeadUnit()
						&& propPersonUnit.getUnit() != null) {
					propPersonUnitList.add(propPersonUnit);
				}
			}
		}
		for (ProposalPersonUnit propPersonUnit : propPersonUnitList) {
			PROPUNITS propUnits = PROPUNITS.Factory.newInstance();
			propUnits
					.setLEADUNITFLAG(propPersonUnit.isLeadUnit() ? Constants.TRUE_FLAG
							: Constants.FALSE_FLAG);
			propUnits.setPROPOSALNUMBER(propPersonUnit.getProposalPerson().getDevelopmentProposal().getProposalNumber());
			UNIT unit = UNIT.Factory.newInstance();
			unit.setUNITNUMBER(propPersonUnit.getUnitNumber());
			unit.setUNITNAME(propPersonUnit.getUnit().getUnitName());
			propUnits.setUNIT(unit);
			propUnitsList.add(propUnits);
		}
		return propUnitsList.toArray(new PROPUNITS[0]);
	}

	/*
	 * This method gets PROPOSALMASTER XMLObject and set data to it from Budget
	 * and ProposalDevelopment
	 */
	private PROPOSALMASTER getProposalMaster(
			DevelopmentProposal developmentProposal, Budget budget) {
		PROPOSALMASTER proposalMaster = PROPOSALMASTER.Factory.newInstance();
		proposalMaster.setPROPOSALNUMBER(developmentProposal
				.getProposalNumber());
		if (developmentProposal.getApplicantOrganization().getRolodex() != null) {
			proposalMaster
					.setORGANIZATION(getDevelopmentProposalOrganizationXMLObject(developmentProposal
							.getApplicantOrganization().getRolodex()));
		}
		proposalMaster.setLEADUNIT(getProposalLeadUnit(developmentProposal
				.getProposalPersons()));
		proposalMaster.setTITLE(developmentProposal.getTitle());
		proposalMaster.setPERIOD(getProjectPeriod(developmentProposal
				.getRequestedStartDateInitial(), developmentProposal
				.getRequestedEndDateInitial()));
		proposalMaster.setSPONSORCOST(getSponsorCost(budget));
		proposalMaster.setCOSTSHARING(budget.getCostSharingAmount().toString());
		proposalMaster.setOTHERCOMMENTS(getAbstractFromProposalAbstract(
				developmentProposal.getProposalAbstracts(),
				ABSTRACT_TYPE_CODE_OTHER));
		proposalMaster.setCOI(getConflictOfInterest(developmentProposal
				.getProposalChangedDataList()));
		proposalMaster.setDEADLINE(getSponsorDeadLine(developmentProposal
				.getDeadlineDate(), developmentProposal.getDeadlineType()));
		proposalMaster.setADDLMAILINGINSTR(getAbstractFromProposalAbstract(
				developmentProposal.getProposalAbstracts(),
				ABSTRACT_TYPE_CODE_MAILING));
		proposalMaster.setCENTERINST(getCenterInstitute(developmentProposal
				.getProposalChangedDataList(), developmentProposal
				.getProposalPersons()));
		proposalMaster.setSPONSOR(getSponsorXMLObject(developmentProposal
				.getSponsorName()));
		proposalMaster
				.setPRIMESPONSOR(getPrimeSponsorXMLObject(developmentProposal
						.getPrimeSponsorCode()));
		proposalMaster
				.setPROPINVESTIGATORS(getProposalInvestigatorXMLObject(developmentProposal
						.getProposalPersons()));
		proposalMaster
				.setPROPOSALTYPE(getProposalTypeXMLObject(developmentProposal
						.getProposalType().getDescription()));
		proposalMaster
				.setACTIVITYTYPE(getActivityTypeXMLObject(developmentProposal
						.getActivityType().getDescription()));
		proposalMaster
				.setNOTICEOFOPPORTUNITY(getNoticeOfOpportunityXMLObject(developmentProposal
						.getNoticeOfOpportunityCode()));
		proposalMaster.setPROGRAMANNOUNCEMENTNUMBER(developmentProposal
				.getProgramAnnouncementNumber());
		proposalMaster.setPROGRAMANNOUNCEMENTTITLE(developmentProposal
				.getProgramAnnouncementTitle());
		proposalMaster.setMAILTYPE(getMailType(developmentProposal
				.getMailType()));
		proposalMaster.setNUMBEROFCOPIES(developmentProposal
				.getNumberOfCopies());
		proposalMaster
				.setMAILADDRESS(getMailingAddressXMLObject(developmentProposal
						.getApplicantOrganization().getRolodex()));
		proposalMaster.setMAILDESCRIPTION(developmentProposal
				.getMailDescription());

		return proposalMaster;
	}

	/*
	 * This method gets mailAddress XMLObject and Rolodex, Rolodex address
	 * details are set to it.
	 */
	private PROPOSALMASTER.MAILADDRESS getMailingAddressXMLObject(
			Rolodex rolodexDetails) {
		ADDRESS address = ADDRESS.Factory.newInstance();
		address.setADDRESSLINE1(getSponsorAddress(rolodexDetails));
		ROLODEX rolodex = ROLODEX.Factory.newInstance();
		rolodex.setADDRESS(address);
		PROPOSALMASTER.MAILADDRESS mailAddress = PROPOSALMASTER.MAILADDRESS.Factory
				.newInstance();
		mailAddress.setROLODEX(rolodex);
		return mailAddress;
	}

	/*
	 * This method gets sponsorAddress by concatenating Rolodex details if
	 * rolodexPerson is there
	 */
	private String getSponsorAddress(Rolodex rolodex) {
		String address = Constants.EMPTY_STRING;
		if (rolodex != null && rolodex.getRolodexId() != null) {
			address = new StringBuilder(
					rolodex.getLastName() == null ? Constants.EMPTY_STRING
							: rolodex.getLastName())
					.append(
							rolodex.getFirstName() == null ? Constants.EMPTY_STRING
									: rolodex.getFirstName())
					.append(
							rolodex.getMiddleName() == null ? Constants.EMPTY_STRING
									: rolodex.getMiddleName())
					.append(
							rolodex.getEmailAddress() == null ? Constants.EMPTY_STRING
									: new StringBuilder(OPEN_BRACES).append(
											rolodex.getEmailAddress()).append(
											CLOSE_BRACES))
					.append(
							rolodex.getPhoneNumber() == null ? Constants.EMPTY_STRING
									: rolodex.getPhoneNumber())
					.append(NEW_LINE)
					.append(rolodex.getOrganization())
					.append(NEW_LINE)
					.append(
							rolodex.getAddressLine1() == null ? Constants.EMPTY_STRING
									: rolodex.getAddressLine1())
					.append(NEW_LINE)
					.append(
							rolodex.getAddressLine2() == null ? Constants.EMPTY_STRING
									: rolodex.getAddressLine2())
					.append(NEW_LINE)
					.append(
							rolodex.getAddressLine3() == null ? Constants.EMPTY_STRING
									: rolodex.getAddressLine3())
					.append(NEW_LINE)
					.append(
							rolodex.getCity() == null ? Constants.EMPTY_STRING
									: rolodex.getCity())
					.append(Constants.COMMA)
					.append(
							rolodex.getState() == null ? Constants.EMPTY_STRING
									: rolodex.getState())
					.append(BLANK_STRING)
					.append(
							rolodex.getPostalCode() == null ? Constants.EMPTY_STRING
									: rolodex.getPostalCode())
					.append(BLANK_STRING)
					.append(
							rolodex.getCountryCode() == null ? Constants.EMPTY_STRING
									: rolodex.getCountryCode()).toString();
		}
		return address;
	}

	/*
	 * This method get Submission method by checking the mailType
	 */
	private String getMailType(String mailType) {
		String submissionMethod = Constants.EMPTY_STRING;
		if (MAIL_TYPE_ELECTRONIC.equals(mailType)) {
			submissionMethod = MAIL_SUBMISSINON_METHOD_ELECTRONIC;
		} else if (MAIL_TYPE_REGULAR.equals(mailType)) {
			submissionMethod = MAIL_SUBMISSINON_METHOD_REGULAR;
		} else if (MAIL_TYPE_DHL.equals(mailType)) {
			submissionMethod = MAIL_SUBMISSINON_METHOD_DHL;
		}
		return submissionMethod;
	}

	/*
	 * This method gets NoticeOfOpportunityXMLObject by setting description and
	 * typeCode
	 * 
	 */
	private NOTICEOFOPPORTUNITY getNoticeOfOpportunityXMLObject(
			String noticeOfOpportunityCode) {
		NOTICEOFOPPORTUNITY noticeOfOpportunity = NOTICEOFOPPORTUNITY.Factory
				.newInstance();
		String description = getNoticeOfOpportunityDesc(noticeOfOpportunityCode);
		noticeOfOpportunity.setDESCRIPTION(description);
		noticeOfOpportunity.setNOTICEOFOPPORTUNITYCODE(0);
		return noticeOfOpportunity;
	}

	/*
	 * This method gets NoticeOfOpportunity description by calling database
	 * using noticeOfOpportunityCode
	 */
	private String getNoticeOfOpportunityDesc(String noticeOfOpportunityCode) {
		Map<String, String> noticeCodeMap = new HashMap<String, String>();
		noticeCodeMap.put(NOTICE_OF_OPPORTUNITY_CODE, noticeOfOpportunityCode);
		String description = Constants.EMPTY_STRING;
		NoticeOfOpportunity noticeOfOpportunity = (NoticeOfOpportunity) getBusinessObjectService()
				.findByPrimaryKey(NoticeOfOpportunity.class, noticeCodeMap);
		if (noticeOfOpportunity != null) {
			description = noticeOfOpportunity.getDescription();
		}
		return description;
	}

	/*
	 * This method gets activityType XMLObject and set description, typeCode
	 * data
	 */
	private ACTIVITYTYPE getActivityTypeXMLObject(String description) {
		ACTIVITYTYPE activityType = ACTIVITYTYPE.Factory.newInstance();
		activityType.setDESCRIPTION(description);
		activityType.setACTIVITYTYPECODE(0);
		return activityType;
	}

	/*
	 * This method gets proposalType XMLObject and set description, typeCode
	 * data
	 */
	private PROPOSALTYPE getProposalTypeXMLObject(String description) {
		PROPOSALTYPE propType = PROPOSALTYPE.Factory.newInstance();
		propType.setDESCRIPTION(description);
		propType.setPROPOSALTYPECODE(0);
		return propType;
	}

	/*
	 * This methods gets Proposal Investigator XMLObject by setting person name
	 * and info from principalInvestigator Person
	 * 
	 */
	private PROPINVESTIGATORTYPE getProposalInvestigatorXMLObject(
			List<ProposalPerson> proposalPersonList) {
	    PROPINVESTIGATORTYPE propInvestigatorType = PROPINVESTIGATORTYPE.Factory
				.newInstance();
		KcPerson person = getPrincipalInvetigatorPerson(proposalPersonList);
		if (person != null) {
		    propInvestigatorType.setPERSONNAME(getPersonNameAndInfo(person));
		}
		if (person.getFullName() != null) {
		    propInvestigatorType.setPERSONID(person.getFullName());
		}
		return propInvestigatorType;
	}

	/*
	 * This method gets Principal Investigator Person from list of Proposal
	 * Person
	 */
	private KcPerson getPrincipalInvetigatorPerson(
			List<ProposalPerson> proposalPersonList) {
		KcPerson person = null;
		for (ProposalPerson pPerson : proposalPersonList) {
			if (pPerson.getPersonId() != null
					&& pPerson.getProposalPersonRoleId().equals(
							Constants.PRINCIPAL_INVESTIGATOR_ROLE)) {
				person = pPerson.getPerson();
				break;
			}
		}
		return person;
	}

	/*
	 * This method gets PrimsSponsor XMLObject by get and set sponsor details
	 * using primeSponsorCode
	 */
	private PROPOSALMASTER.PRIMESPONSOR getPrimeSponsorXMLObject(
			String primeSponsorCode) {
		Sponsor primSponsor = getSponsorBySponsorCode(primeSponsorCode);
		SPONSOR sponsor = SPONSOR.Factory.newInstance();
		if (primSponsor != null) {
			sponsor.setSPONSORNAME(primSponsor.getSponsorName());
		}
		PROPOSALMASTER.PRIMESPONSOR primeSponsor = PROPOSALMASTER.PRIMESPONSOR.Factory
				.newInstance();
		primeSponsor.setSPONSOR(sponsor);
		return primeSponsor;
	}

	/*
	 * This method get sponsor by calling the database with sponsorCode
	 */
	private Sponsor getSponsorBySponsorCode(String sponsorCode) {
		Map<String, String> sponsorCodeMap = new HashMap<String, String>();
		sponsorCodeMap.put(Constants.SPONSOR_CODE, sponsorCode);
		Sponsor sponsor = (Sponsor) getBusinessObjectService().findByPrimaryKey(
				Sponsor.class, sponsorCodeMap);
		return sponsor;
	}

	/*
	 * This method get sponsor XMLObject by setting sponsor name from
	 * developmentProposal
	 */
	private SPONSOR getSponsorXMLObject(String sponsorName) {
		SPONSOR sponsor = SPONSOR.Factory.newInstance();
		sponsor.setSPONSORNAME(sponsorName);
		return sponsor;
	}

	/*
	 * This method gets Central Institute by concatenating five central
	 * Institute which gets from unit and column value
	 */
	private String getCenterInstitute(
			List<ProposalChangedData> proposalChangedDataList,
			List<ProposalPerson> proposalPersons) {
		String columnValue1 = getProposalChangeDataColumnValue(
				CENTER_INSTITUTE_CODE, proposalChangedDataList);
		String columnValue2 = getProposalChangeDataColumnValue(
				CENTER_INSTITUTE_CODE2, proposalChangedDataList);
		String columnValue3 = getProposalChangeDataColumnValue(
				CENTER_INSTITUTE_CODE3, proposalChangedDataList);
		String columnValue4 = getProposalChangeDataColumnValue(
				CENTER_INSTITUTE_CODE4, proposalChangedDataList);
		String columnValue5 = getProposalChangeDataColumnValue(
				CENTER_INSTITUTE_CODE5, proposalChangedDataList);
		Unit unit1 = getUnitFromProposalChangeData(columnValue1,
				proposalPersons);
		Unit unit2 = getUnitFromProposalChangeData(columnValue2,
				proposalPersons);
		Unit unit3 = getUnitFromProposalChangeData(columnValue3,
				proposalPersons);
		Unit unit4 = getUnitFromProposalChangeData(columnValue4,
				proposalPersons);
		Unit unit5 = getUnitFromProposalChangeData(columnValue5,
				proposalPersons);
		String centerInstitute1 = getCenterInstitute(columnValue1, unit1);
		String centerInstitute2 = getCenterInstitute(columnValue2, unit2);
		String centerInstitute3 = getCenterInstitute(columnValue3, unit3);
		String centerInstitute4 = getCenterInstitute(columnValue4, unit4);
		String centerInstitute5 = getCenterInstitute(columnValue5, unit5);
		return new StringBuilder(centerInstitute1).append(NEW_LINE).append(
				centerInstitute2).append(NEW_LINE).append(centerInstitute3)
				.append(NEW_LINE).append(centerInstitute4).append(NEW_LINE)
				.append(centerInstitute5).toString();
	}

	/*
	 * This method gets columnValue as central Institute if unitNumber is null
	 * else concatenating of columnValue and unitName
	 */
	private String getCenterInstitute(String columnValue, Unit unit) {
		String centerInstitue = columnValue;
		if (unit != null && unit.getUnitNumber() != null) {
			centerInstitue = new StringBuilder(columnValue).append(
					STRING_SEPRATOR).append(unit.getUnitName()).toString();
		}
		return centerInstitue;
	}

	/*
	 * This method gets columValue from List of ProposalChangeData by checking
	 * the CentralInstituteCode
	 */
	private String getProposalChangeDataColumnValue(String centerInstituteCode,
			List<ProposalChangedData> proposalChangedDataList) {
		String columnValue = Constants.EMPTY_STRING;
		for (ProposalChangedData propChangeData : proposalChangedDataList) {
			if (propChangeData.getColumnName().equals(centerInstituteCode)) {
				columnValue = propChangeData.getChangedValue();
			}
		}
		return columnValue;
	}

	/*
	 * This method gets Unit from List of ProposalPerson by comparing unitNumber
	 * with passes value
	 */
	private Unit getUnitFromProposalChangeData(String value,
			List<ProposalPerson> proposalPersons) {
		Unit unit = null;
		for (ProposalPerson pPerson : proposalPersons) {
			for (ProposalPersonUnit pUnit : pPerson.getUnits()) {
				if (pUnit.getUnitNumber().equals(value)) {
					unit = pUnit.getUnit();
				}
			}
		}
		return unit;
	}

	/*
	 * This method gets conflictOfInterest value based on value of
	 * proposalChangesData, if the value not match it return default value of
	 * conflictOfInterest
	 */
	private String getConflictOfInterest(
			List<ProposalChangedData> proposalChangedDataList) {
		String conflictOfInterest = COI_DEFAULT_VALUE;
		for (ProposalChangedData propChangesData : proposalChangedDataList) {
			if (propChangesData.getColumnName().equals(CONFLICT_OF_INTEREST)) {
				if (propChangesData.getChangedValue().equals(IDENTIFIER_NO)) {
					conflictOfInterest = DOES_NOT_REQUIRE;
				} else if (propChangesData.getChangedValue().equals(
						IDENTIFIER_YES)) {
					conflictOfInterest = DOES_REQUIRE;
				}
			}
		}
		return conflictOfInterest;
	}

	/*
	 * This method gets sponsorDeadLine by concatenating DevelopmentProposal
	 * deadLineDate and deadLineType
	 */
	private String getSponsorDeadLine(Date deadLineDate, String deadLineType) {
		String sponsorDeadLineType = Constants.EMPTY_STRING;
		String deadLineDateFormated = DateFormatUtils.format(deadLineDate,
				Constants.DEFAULT_DATE_FORMAT_PATTERN);
		if (SPONSOR_DEADLINE_TYPE_POSTMARK.equals(deadLineType)) {
			sponsorDeadLineType = SPONSOR_DEADLINE_POSTMARK_DESCRIPTION;
		} else if (SPONSOR_DEADLINE_TYPE_RECEIPT.equals(deadLineType)) {
			sponsorDeadLineType = SPONSOR_DEADLINE_RECEIPT_DESCRIPTION;
		}
		String sponsorDeadLine = new StringBuilder(deadLineDateFormated)
				.append(sponsorDeadLineType).toString();
		return sponsorDeadLine;
	}

	/*
	 * This method gets abstractDetails as OtherComment(17) or Address Mailing
	 * Instruction(16) from ProposalAbstract by checking abstractTypeCode as 17
	 * and 16
	 */
	private String getAbstractFromProposalAbstract(
			List<ProposalAbstract> proposalAbstracts, String abstractTypeCode) {
		String abstractDetail = Constants.EMPTY_STRING;
		for (ProposalAbstract proposalAbstract : proposalAbstracts) {
			if (proposalAbstract.getAbstractTypeCode().equals(abstractTypeCode)) {
				abstractDetail = proposalAbstract.getAbstractDetails();
				break;
			}
		}
		return abstractDetail;
	}

	/*
	 * This method gets totalCost as sponsorCost from budget if finalVersionFlag
	 * is true else it return sponsorCost as No Final Budget
	 */
	private String getSponsorCost(Budget budget) {
		String sponsorCost = budget.getTotalCost().toString();
		return sponsorCost;
	}

	/*
	 * This method gets the period by concatenating startData and endDate of
	 * DevelopmentProposal RequestStartDate and RequestEndDate with String
	 * separator
	 * 
	 */
	private String getProjectPeriod(Date requestedStartDate,
			Date requestedEndDate) {
		String startDate = DateFormatUtils.format(requestedStartDate,
				Constants.DEFAULT_DATE_FORMAT_PATTERN);
		String endDate = DateFormatUtils.format(requestedEndDate,
				Constants.DEFAULT_DATE_FORMAT_PATTERN);
		String projectPeriod = new StringBuilder(startDate).append(
				STRING_SEPRATOR).append(endDate).toString();
		return projectPeriod;
	}

	/*
	 * This method gets proposalLeadUnit by concatenating unitNumber of
	 * ProposalPersonUnit and UnitName with Blank separator from List of
	 * ProposalPerson
	 * 
	 */
	private String getProposalLeadUnit(List<ProposalPerson> proposalPersonList) {
		String leadUnit = Constants.EMPTY_STRING;
		for (ProposalPerson proposalPerson : proposalPersonList) {
			for (ProposalPersonUnit proposalPersonUnit : proposalPerson
					.getUnits()) {
				if (proposalPersonUnit.isLeadUnit()) {
					leadUnit = new StringBuilder(proposalPersonUnit
							.getUnitNumber()).append(BLANK_STRING).append(
							proposalPersonUnit.getUnit().getUnitName())
							.toString();
					break;
				}
			}
		}
		return leadUnit;
	}

	/*
	 * This method get Organization XMLObject by setting Rolodex details to
	 * organization XMLObject
	 */
	private ORGANIZATION getDevelopmentProposalOrganizationXMLObject(
			Rolodex rolodexDetails) {
		String nameAndInfo = getRolodexNameAndInfo(rolodexDetails);
		NAME name = NAME.Factory.newInstance();
		name.setFIRSTNAME(nameAndInfo);
		ADDRESS address = ADDRESS.Factory.newInstance();
		address.setADDRESSLINE1(nameAndInfo);
		ROLODEX rolodex = ROLODEX.Factory.newInstance();
		rolodex.setNAME(name);
		rolodex.setADDRESS(address);
		ORGANIZATION organization = ORGANIZATION.Factory.newInstance();
		organization.setROLODEX(rolodex);
		return organization;
	}

	/*
	 * This method gets PersonName and Information by concatenating Person
	 * FullName, Email and OfficePhone
	 */
	private String getPersonNameAndInfo(KcPerson person) {
		String personEmail = person.getEmailAddress();
		String officePhone = person.getOfficePhone();
		String personInfo = Constants.EMPTY_STRING;
		if (personEmail != null && officePhone != null) {
			personInfo = new StringBuilder(OPEN_BRACES).append(
					person.getEmailAddress()).append(person.getOfficePhone())
					.append(CLOSE_BRACES).toString();
		}
		if (personEmail == null && officePhone != null) {
			personInfo = new StringBuilder(OPEN_BRACES).append(
					person.getOfficePhone()).append(CLOSE_BRACES).toString();
		}
		String personNameAndInfo = new StringBuilder(person.getFullName())
				.append(personInfo).toString();
		return personNameAndInfo;
	}

	/*
	 * This method gets Rolodex and Information by concatenating Rolodex
	 * FullName, Email and OfficePhone
	 */
	private String getRolodexNameAndInfo(Rolodex rolodex) {
		String personEmail = rolodex.getEmailAddress();
		String officePhone = rolodex.getPhoneNumber();
		String rolodexinfo = Constants.EMPTY_STRING;
		if (personEmail != null && officePhone != null) {
			rolodexinfo = new StringBuilder(OPEN_BRACES).append(
					rolodex.getEmailAddress()).append(rolodex.getPhoneNumber())
					.append(CLOSE_BRACES).toString();
		}
		if (personEmail == null && officePhone != null) {
			rolodexinfo = new StringBuilder(OPEN_BRACES).append(
					rolodex.getPhoneNumber()).append(CLOSE_BRACES).toString();
		}
		String personNameAndInfo = new StringBuilder(rolodex.getFullName())
				.append(rolodexinfo).toString();
		return personNameAndInfo;
	}
}
