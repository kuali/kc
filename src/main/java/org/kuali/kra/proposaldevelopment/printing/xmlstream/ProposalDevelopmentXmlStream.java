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
package org.kuali.kra.proposaldevelopment.printing.xmlstream;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalYnq;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;

import edu.mit.coeus.utils.xml.v2.budget.BUDGETDocument.BUDGET;
import edu.mit.coeus.utils.xml.v2.budget.BUDGETDocument.BUDGET.BudgetMaster;
import edu.mit.coeus.utils.xml.v2.lookuptypes.APPLICABLEREVIEWTYPEDocument.APPLICABLEREVIEWTYPE;
import edu.mit.coeus.utils.xml.v2.lookuptypes.PROPOSALSTATUSDocument.PROPOSALSTATUS;
import edu.mit.coeus.utils.xml.v2.lookuptypes.PROPOSALTYPEDocument.PROPOSALTYPE;
import edu.mit.coeus.utils.xml.v2.lookuptypes.SPECIALREVIEWDocument.SPECIALREVIEW;
import edu.mit.coeus.utils.xml.v2.organization.ORGANIZATIONDocument.ORGANIZATION;
import edu.mit.coeus.utils.xml.v2.propdev.PROPOSALDocument;
import edu.mit.coeus.utils.xml.v2.propdev.PROPABSTRACTDocument.PROPABSTRACT;
import edu.mit.coeus.utils.xml.v2.propdev.PROPAPPROVALMAPSDocument.PROPAPPROVALMAPS;
import edu.mit.coeus.utils.xml.v2.propdev.PROPCHANGEDDATA31Document.PROPCHANGEDDATA31;
import edu.mit.coeus.utils.xml.v2.propdev.PROPCUSTOMDATADocument.PROPCUSTOMDATA;
import edu.mit.coeus.utils.xml.v2.propdev.PROPINVESTIGATORSDocument.PROPINVESTIGATORS;
import edu.mit.coeus.utils.xml.v2.propdev.PROPKEYPERSONSDocument.PROPKEYPERSONS;
import edu.mit.coeus.utils.xml.v2.propdev.PROPLOCATIONDocument.PROPLOCATION;
import edu.mit.coeus.utils.xml.v2.propdev.PROPNOTEPADDocument.PROPNOTEPAD;
import edu.mit.coeus.utils.xml.v2.propdev.PROPOSALDocument.PROPOSAL;
import edu.mit.coeus.utils.xml.v2.propdev.PROPOSALMASTERDocument.PROPOSALMASTER;
import edu.mit.coeus.utils.xml.v2.propdev.PROPPERCREDITSPLITDocument.PROPPERCREDITSPLIT;
import edu.mit.coeus.utils.xml.v2.propdev.PROPPERSONDocument.PROPPERSON;
import edu.mit.coeus.utils.xml.v2.propdev.PROPSCIENCECODEDocument.PROPSCIENCECODE;
import edu.mit.coeus.utils.xml.v2.propdev.PROPSPECIALREVIEWDocument.PROPSPECIALREVIEW;
import edu.mit.coeus.utils.xml.v2.propdev.PROPUNITCREDITSPLITDocument.PROPUNITCREDITSPLIT;
import edu.mit.coeus.utils.xml.v2.propdev.PROPUNITSDocument.PROPUNITS;
import edu.mit.coeus.utils.xml.v2.propdev.PROPUSERROLESDocument.PROPUSERROLES;
import edu.mit.coeus.utils.xml.v2.propdev.PROPYNQDocument.PROPYNQ;
import edu.mit.coeus.utils.xml.v2.rolodex.ADDRESSDocument.ADDRESS;
import edu.mit.coeus.utils.xml.v2.rolodex.NAMEDocument.NAME;
import edu.mit.coeus.utils.xml.v2.rolodex.ROLODEXDocument.ROLODEX;
import edu.mit.coeus.utils.xml.v2.userUnit.UNITDocument.UNIT;

/**
 * This class generates XML that confirms with the RaR XSD related to Proposal
 * Submission Report or Sponsor Report. The data for XML is derived from
 * {@link ResearchDocumentBase} and {@link Map} of details passed to the class.
 * 
 * 
 */
public class ProposalDevelopmentXmlStream extends ProposalBaseStream {

	private static final Log LOG = LogFactory
			.getLog(ProposalDevelopmentXmlStream.class);
	private static final String HIPHEN = " - ";
	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"MM/dd/yyyy");
	private DevelopmentProposal developmentProposal;
	private static final String YES = "y";
	private static final String NO = "n";
	Calendar calendar;

	/**
	 * This method generates XML for Proposal Submission Report or Sponsor
	 * Report. It uses data passed in {@link ResearchDocumentBase} for
	 * populating the XML nodes. The XMl once generated is returned as
	 * {@link XmlObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XmlObject} representing the XML
	 */
	public Map<String, XmlObject> generateXmlStream(
			KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		this.developmentProposal = (DevelopmentProposal) printableBusinessObject;
		PROPOSALDocument proposalDocument = PROPOSALDocument.Factory
				.newInstance();

		PROPOSAL proposal = PROPOSAL.Factory.newInstance();
		proposal.setPROPOSALMASTER(getPROPOSALMASTER());
		proposal.setPROPABSTRACTArray(getPROPOSALABSTRACTArray());
		proposal.setPROPAPPROVALMAPSArray(getPROPAPPROVALMAPSArray());
		proposal.setPROPCHANGEDDATA31Array(getPROPCHANGEDDATA31Array());
		proposal.setPROPCUSTOMDATAArray(getPROPCUSTOMDATAArray());
		proposal.setPROPINVESTIGATORSArray(getPROPINVESTIGATORSArray());
		proposal.setPROPKEYPERSONSArray(getPROPKEYPERSONSArray());
		proposal.setPROPLOCATIONArray(getPROPLOCATIONArray());
		proposal.setPROPNOTEPADArray(getPROPNOTEPADArray());
		proposal.setPROPPERSONArray(getPROPPERSONArray());
		proposal.setPROPSCIENCECODEArray(getPROPSCIENCECODEArray());
		proposal.setPROPSPECIALREVIEWArray(getPROPSPECIALREVIEWArray());
		proposal.setPROPPERCREDITSPLITArray(getPROPPERCREDITSPLITArray());
		proposal.setPROPUNITCREDITSPLITArray(getPROPUNITCREDITSPLITArray());
		proposal.setPROPUSERROLESArray(getPROPUSERROLESArray());
		proposal.setPROPYNQArray(getPROPYNQArray());
		proposal.setBUDGET(getBUDGET());
		proposal.setPROPUNITSArray(getPROPUNITSArray());
		proposal.setCURDATE(getCURDATE());
		proposal.setLOGOPATH(getLOGOPATH());
		proposalDocument.setPROPOSAL(proposal);
		Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
		// xmlObjectList.put(REPORT_NAME, researchAndRelatedProjectDocument);
		return xmlObjectList;
	}

	private String getLOGOPATH() {
		return null;
	}

	private String getCURDATE() {
		return null;
	}

	private PROPUNITS[] getPROPUNITSArray() {
		List<PROPUNITS> propUnitList = new ArrayList<PROPUNITS>();
		Unit proposalUnit = developmentProposal.getUnit();
		PROPUNITS propUnits = PROPUNITS.Factory.newInstance();
		propUnits.setPROPOSALNUMBER(developmentProposal.getProposalNumber());
		PROPPERSON propPerson = PROPPERSON.Factory.newInstance();
		// propPerson.setLASTNAME()
		propUnits.setPROPPERSON(propPerson);
		// propUnits.setLEADUNITFLAG(proposalUnit.getU)
		UNIT unit = UNIT.Factory.newInstance();
		// unit.set
		propUnits.setUNIT(unit);
		propUnitList.add(propUnits);
		return propUnitList.toArray(new PROPUNITS[0]);
	}

	private BUDGET getBUDGET() {
		BUDGET proposalBudget = BUDGET.Factory.newInstance();
		proposalBudget.setBudgetMaster(getBudgetMaster());
		return proposalBudget;
	}

	private BudgetMaster getBudgetMaster() {
		BudgetMaster budgetMaster = BudgetMaster.Factory.newInstance();
		Budget budget = getBudget(developmentProposal.getProposalDocument());
		budgetMaster.setCOMMENTS(budget.getComments());
		budgetMaster.setCOSTSHARINGAMOUNT(budget.getCostSharingAmount()
				.bigDecimalValue());
		calendar = Calendar.getInstance();
		calendar.setTime(budget.getEndDate());
		budgetMaster.setENDDATE(calendar);
		budgetMaster.setFINALVERSIONFLAG(getFlag(budget.getFinalVersionFlag()));
		budgetMaster.setMODULARBUDGETFLAG(getFlag(budget.getModularBudgetFlag()));
		budgetMaster.setOHRATECLASSCODE(getCode(budget.getOhRateClassCode()));
		budgetMaster.setOHRATETYPECODE(getCode(budget.getOhRateTypeCode()));
		budgetMaster.setPROPOSALNUMBER(developmentProposal.getProposalNumber());
		budgetMaster.setRESIDUALFUNDS(budget.getResidualFunds()
				.bigDecimalValue());
		calendar = Calendar.getInstance();
		calendar.setTime(budget.getStartDate());
		budgetMaster.setSTARTDATE(calendar);
		budgetMaster.setTOTALCOST(budget.getTotalCost().bigDecimalValue());
		budgetMaster.setTOTALCOSTLIMIT(budget.getTotalCostLimit()
				.bigDecimalValue());
		budgetMaster.setTOTALDIRECTCOST(budget.getTotalDirectCost()
				.bigDecimalValue());
		budgetMaster.setTOTALINDIRECTCOST(budget.getTotalIndirectCost()
				.bigDecimalValue());
		budgetMaster.setUNDERRECOVERYAMOUNT(budget.getUnderrecoveryAmount()
				.bigDecimalValue());
		calendar = Calendar.getInstance();
		calendar.setTime(budget.getUpdateTimestamp());
		budgetMaster.setUPDATETIMESTAMP(calendar);
		budgetMaster.setUPDATEUSER(budget.getUpdateUser());
		budgetMaster.setURRATECLASSCODE(getCode(budget.getUrRateClassCode()));
		budgetMaster.setVERSIONNUMBER(budget.getVersionNumber().intValue());
		return budgetMaster;
	}

	private PROPYNQ[] getPROPYNQArray() {
		List<PROPYNQ> propYnqList = new ArrayList<PROPYNQ>();
		PROPYNQ propynq = null;
		for (ProposalYnq proposalYnq : developmentProposal.getProposalYnqs()) {
			propynq = PROPYNQ.Factory.newInstance();
			propynq.setANSWER(proposalYnq.getAnswer());
			propynq.setEXPLANATION(proposalYnq.getExplanation());
			propynq.setPROPOSALNUMBER(proposalYnq.getProposalNumber());
			propynq.setQUESTIONID(proposalYnq.getQuestionId());
			calendar = Calendar.getInstance();
			calendar.setTime(proposalYnq.getReviewDate());
			propynq.setREVIEWDATE(calendar);
			calendar = Calendar.getInstance();
			calendar.setTime(proposalYnq.getUpdateTimestamp());
			propynq.setUPDATETIMESTAMP(calendar);
			propynq.setUPDATEUSER(proposalYnq.getUpdateUser());
			propYnqList.add(propynq);
		}
		return propYnqList.toArray(new PROPYNQ[0]);
	}

	private PROPUSERROLES[] getPROPUSERROLESArray() {
		List<PROPUSERROLES> propUserRolesList = new ArrayList<PROPUSERROLES>();
		return propUserRolesList.toArray(new PROPUSERROLES[0]);
	}

	private PROPUNITCREDITSPLIT[] getPROPUNITCREDITSPLITArray() {
		List<PROPUNITCREDITSPLIT> propUnitCreditList = new ArrayList<PROPUNITCREDITSPLIT>();
		return propUnitCreditList.toArray(new PROPUNITCREDITSPLIT[0]);
	}

	private PROPPERCREDITSPLIT[] getPROPPERCREDITSPLITArray() {
		return null;
	}

	private PROPSPECIALREVIEW[] getPROPSPECIALREVIEWArray() {
		List<PROPSPECIALREVIEW> propSpecialReviewList = new ArrayList<PROPSPECIALREVIEW>();
		List<ProposalSpecialReview> propSpecialReviews = developmentProposal.getPropSpecialReviews();
		PROPSPECIALREVIEW proposalReview = null;
		for (ProposalSpecialReview specialReview : propSpecialReviews) {
			proposalReview = PROPSPECIALREVIEW.Factory.newInstance();
			proposalReview.setPROPOSALNUMBER(developmentProposal.getProposalNumber());
			proposalReview.setSPECIALREVIEWNUMBER(specialReview
					.getSpecialReviewNumber());
			SPECIALREVIEW proposalSpecialReview = SPECIALREVIEW.Factory
					.newInstance();
			proposalSpecialReview.setDESCRIPTION(specialReview
					.getSpecialReviewType().getDescription());
			 proposalSpecialReview.setSPECIALREVIEWCODE(getCode(specialReview.getSpecialReviewType().getSpecialReviewTypeCode()));
			proposalReview.setSPECIALREVIEW(proposalSpecialReview);
			APPLICABLEREVIEWTYPE applicablereviewtype = APPLICABLEREVIEWTYPE.Factory
					.newInstance();
			applicablereviewtype.setAPPLICABLEREVIEWTYPECODE(getCode(specialReview.getApprovalTypeCode()));
			proposalReview.setAPPLICABLEREVIEWTYPE(applicablereviewtype);
			proposalReview.setPROTOCOLNUMBER(specialReview.getProtocolNumber());
			if (specialReview.getApplicationDate() != null) {
				calendar = Calendar.getInstance();
				calendar.setTime(specialReview.getApplicationDate());
				proposalReview.setAPPLICATIONDATE(calendar);
			}
			if (specialReview.getApprovalDate() != null) {
				calendar = Calendar.getInstance();
				calendar.setTime(specialReview.getApprovalDate());
				proposalReview.setAPPROVALDATE(calendar);
			}
			proposalReview.setCOMMENTS(specialReview.getComments());
			proposalReview.setUPDATEUSER(specialReview.getUpdateUser());
			if (specialReview.getUpdateTimestamp() != null) {
				calendar = Calendar.getInstance();
				calendar.setTime(toDate(specialReview.getUpdateTimestamp()));
				proposalReview.setUPDATETIMESTAMP(calendar);
			}
			propSpecialReviewList.add(proposalReview);
		}
		return propSpecialReviewList.toArray(new PROPSPECIALREVIEW[0]);
	}

	private PROPSCIENCECODE[] getPROPSCIENCECODEArray() {
		return null;
	}

	private PROPPERSON[] getPROPPERSONArray() {
		return null;
	}

	private PROPNOTEPAD[] getPROPNOTEPADArray() {
		return null;
	}

	private PROPOSALMASTER getPROPOSALMASTER() {
		PROPOSALMASTER proposalmaster = PROPOSALMASTER.Factory.newInstance();
		proposalmaster.setPROPOSALNUMBER(developmentProposal
				.getProposalNumber());
		PROPOSALTYPE proposaltype = PROPOSALTYPE.Factory.newInstance();
		proposaltype.setPROPOSALTYPECODE(getCode(developmentProposal.getProposalType().getProposalTypeCode()));
		proposaltype.setDESCRIPTION(developmentProposal.getProposalType()
				.getDescription());
		proposalmaster.setPROPOSALTYPE(proposaltype);
		PROPOSALSTATUS proposalstatus = PROPOSALSTATUS.Factory.newInstance();
		// NOT Implemented in Coeus.
		proposalstatus.setPROPOSALSTATUSCODE(getCode(developmentProposal.getProposalStateTypeCode()));
		proposalmaster.setPROPOSALSTATUS(proposalstatus);
		// Not Implemented in Coeus.
//		proposalmaster.setBASEPROPOSALNUMBER(developmentProposal.get);
//		proposalmaster.setCONTINUEDFROM(developmentProposal.get);
//		proposalmaster.setTEMPLATEFLAG(getFlag(arg0));
		proposalmaster.setORGANIZATION(getORGANIZATION());
		// proposalmaster.setCURRENTACCOUNTNUMBER(developmentProposal.getc)

		// prop_units.unit_number || ' ' || unit.unit_name as lead_unit,
		// eps_prop_units
		// proposalmaster.setLEADUNIT(arg0)

		proposalmaster.setTITLE(developmentProposal.getTitle());
		proposalmaster.setPERIOD(getPERIOD());
		Budget budget = getBudget(developmentProposal.getProposalDocument());
		proposalmaster.setSPONSORCOST(getCurrencyFormat(budget.getTotalCost().bigDecimalValue()));
		proposalmaster.setCOSTSHARING(getCurrencyFormat(budget.getCostSharingAmount().bigDecimalValue()));
//		proposalmaster.setOTHERCOMMENTS(otherComments);
		return proposalmaster;
	}

	private String getPERIOD() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(simpleDateFormat.format(developmentProposal.getRequestedStartDateInitial()));
		stringBuffer.append(HIPHEN);
		stringBuffer.append(simpleDateFormat.format(developmentProposal.getRequestedEndDateInitial()));
		return stringBuffer.toString();
	}

	/**
	 * Method returns the Organization
	 * 
	 * @return
	 */
	private ORGANIZATION getORGANIZATION() {
		ORGANIZATION organization = ORGANIZATION.Factory.newInstance();
		ROLODEX rolodex = ROLODEX.Factory.newInstance();
		NAME name = NAME.Factory.newInstance();
		ADDRESS address = ADDRESS.Factory.newInstance();
		// address.setStringValue(arg0)
		rolodex.setADDRESS(address);
		// name.setFIRSTNAME(developmentProposal.get)
		rolodex.setNAME(name);
		organization.setROLODEX(rolodex);
		return organization;
	}

	private PROPABSTRACT[] getPROPOSALABSTRACTArray() {
		return null;
	}

	private PROPAPPROVALMAPS[] getPROPAPPROVALMAPSArray() {
		return null;
	}

	private PROPCHANGEDDATA31[] getPROPCHANGEDDATA31Array() {
		return null;
	}

	private PROPCUSTOMDATA[] getPROPCUSTOMDATAArray() {
		return null;
	}

	private PROPINVESTIGATORS[] getPROPINVESTIGATORSArray() {
		List<PROPINVESTIGATORS> propInvestigatorList = new ArrayList<PROPINVESTIGATORS>();

		List<ProposalPerson> investigatorList = developmentProposal.getInvestigators();
		PROPINVESTIGATORS propInvestigator = null;
		for (ProposalPerson proposalPerson : investigatorList) {
			propInvestigator = PROPINVESTIGATORS.Factory.newInstance();
			propInvestigator.setPROPOSALNUMBER(developmentProposal.getProposalNumber());
			propInvestigator.setPERSONID(proposalPerson.getPersonId());
			propInvestigator.setPERSONNAME(proposalPerson.getFullName());
			propInvestigator.setPRINCIPALINVESTIGATORFLAG(getFlag(proposalPerson.isInvestigator()));
			propInvestigator.setFACULTYFLAG(getFlag(proposalPerson.getFedrDebrFlag()));
//			propInvestigator.setNONMITPERSONFLAG(getFlag(proposalPerson.getN)
			propInvestigator.setCONFLICTOFINTERESTFLAG(getFlag(proposalPerson.getConflictOfInterestFlag()));
			propInvestigator.setPERCENTAGEEFFORT(proposalPerson
					.getPercentageEffort().bigDecimalValue());
			propInvestigator.setFEDRDEBRFLAG(getFlag(proposalPerson.getFedrDebrFlag()));
			propInvestigator.setFEDRDELQFLAG(getFlag(proposalPerson.getFedrDelqFlag()));
			if (proposalPerson.getUpdateTimestamp() != null) {
				calendar = Calendar.getInstance();
				calendar.setTime(toDate(proposalPerson.getUpdateTimestamp()));
				propInvestigator.setUPDATETIMESTAMP(calendar);
			}
			propInvestigator.setUPDATEUSER(proposalPerson.getUpdateUser());
			// propInvestigator.setPROPPERCREDITSPLIT(arg0);
			propInvestigatorList.add(propInvestigator);
		}
		return propInvestigatorList.toArray(new PROPINVESTIGATORS[0]);
	}

	private PROPKEYPERSONS[] getPROPKEYPERSONSArray() {
		// ProposalPerson of type Key Person.
		return null;
	}

	private PROPLOCATION[] getPROPLOCATIONArray() {
		// proposalDevelopmentDocument.getDevelopmentProposal().getProposal
		return null;
	}

	public static java.util.Date toDate(java.sql.Timestamp timestamp) {
		long milliseconds = timestamp.getTime()
				+ (timestamp.getNanos() / 1000000);
		return new java.util.Date(milliseconds);
	}

	private String getFlag(boolean flag) {
		if (flag) {
			return YES;
		} else {
			return NO;
		}
	}
	private int getCode(String code){
		try{
			return Integer.parseInt(code);
		}catch(Exception e){
			return 0;
		}
	}
	private String getCurrencyFormat(BigDecimal amount){
		NumberFormat currencyFormater=new DecimalFormat("###,###,##0");
		currencyFormater.setCurrency(Currency.getInstance(Locale.US));
		return currencyFormater.format(amount.doubleValue());
	}
}
