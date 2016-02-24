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

import edu.mit.coeus.utils.xml.v2.budget.BUDGETDocument.BUDGET;
import edu.mit.coeus.utils.xml.v2.budget.BUDGETDocument.BUDGET.BudgetMaster;
import edu.mit.coeus.utils.xml.v2.budget.BUDGETPERIODDocument.BUDGETPERIOD;
import edu.mit.coeus.utils.xml.v2.lookuptypes.ACTIVITYTYPEDocument.ACTIVITYTYPE;
import edu.mit.coeus.utils.xml.v2.lookuptypes.ANTICIPATEDAWARDTYPEDocument.ANTICIPATEDAWARDTYPE;
import edu.mit.coeus.utils.xml.v2.lookuptypes.APPLICABLEREVIEWTYPEDocument.APPLICABLEREVIEWTYPE;
import edu.mit.coeus.utils.xml.v2.lookuptypes.NOTICEOFOPPORTUNITYDocument.NOTICEOFOPPORTUNITY;
import edu.mit.coeus.utils.xml.v2.lookuptypes.PROPOSALSTATUSDocument.PROPOSALSTATUS;
import edu.mit.coeus.utils.xml.v2.lookuptypes.PROPOSALTYPEDocument.PROPOSALTYPE;
import edu.mit.coeus.utils.xml.v2.lookuptypes.SPECIALREVIEWDocument.SPECIALREVIEW;
import edu.mit.coeus.utils.xml.v2.organization.ORGANIZATIONDocument.ORGANIZATION;
import edu.mit.coeus.utils.xml.v2.propdev.CREDITSPLITCOLUMNSDocument.CREDITSPLITCOLUMNS;
import edu.mit.coeus.utils.xml.v2.propdev.PROPABSTRACTDocument.PROPABSTRACT;
import edu.mit.coeus.utils.xml.v2.propdev.PROPAPPROVALMAPSDocument.PROPAPPROVALMAPS;
import edu.mit.coeus.utils.xml.v2.propdev.PROPCHANGEDDATA31Document.PROPCHANGEDDATA31;
import edu.mit.coeus.utils.xml.v2.propdev.PROPCUSTOMDATADocument.PROPCUSTOMDATA;
import edu.mit.coeus.utils.xml.v2.propdev.PROPINVESTIGATORTYPE;
import edu.mit.coeus.utils.xml.v2.propdev.PROPKEYPERSONSDocument.PROPKEYPERSONS;
import edu.mit.coeus.utils.xml.v2.propdev.PROPLOCATIONDocument.PROPLOCATION;
import edu.mit.coeus.utils.xml.v2.propdev.PROPNOTEPADDocument.PROPNOTEPAD;
import edu.mit.coeus.utils.xml.v2.propdev.PROPOSALDocument;
import edu.mit.coeus.utils.xml.v2.propdev.PROPOSALDocument.PROPOSAL;
import edu.mit.coeus.utils.xml.v2.propdev.PROPOSALMASTERDocument.PROPOSALMASTER;
import edu.mit.coeus.utils.xml.v2.propdev.PROPOSALMASTERDocument.PROPOSALMASTER.PRIMESPONSOR;
import edu.mit.coeus.utils.xml.v2.propdev.PROPOSALMASTERDocument.PROPOSALMASTER.PRINCIPALINVESTIGATORNAME;
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
import edu.mit.coeus.utils.xml.v2.sponsor.SPONSORDocument.SPONSOR;
import edu.mit.coeus.utils.xml.v2.userUnit.UNITDocument.UNIT;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.propdev.impl.ynq.ProposalYnq;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class generates XML that confirms with the RaR XSD related to Proposal Submission Report or Sponsor Report. The data for XML
 * is derived from {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} and {@link Map} of details passed to the class.
 * 
 * 
 */
@Component("proposalDevelopmentXmlStream")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProposalDevelopmentXmlStream extends ProposalBaseStream {

    private static final String HIPHEN = " - ";
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private DevelopmentProposal developmentProposal;
    private static final String YES = "y";
    private static final String NO = "n";
    Calendar calendar;

    /**
     * This method generates XML for Proposal Submission Report or Sponsor Report. It uses data passed in
     * {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} for populating the XML nodes. The XMl once generated is returned as {@link XmlObject}
     * 
     * @param printableBusinessObject using which XML is generated
     * @param reportParameters parameters related to XML generation
     * @return {@link XmlObject} representing the XML
     */
    public Map<String, XmlObject> generateXmlStream(KcPersistableBusinessObjectBase printableBusinessObject,
            Map<String, Object> reportParameters) {
        this.developmentProposal = (DevelopmentProposal) printableBusinessObject;
        PROPOSALDocument proposalDocument = PROPOSALDocument.Factory.newInstance();

        PROPOSAL proposal = PROPOSAL.Factory.newInstance();
        proposal.setPROPOSALMASTER(getPROPOSALMASTER());
        proposal.setPROPABSTRACTArray(getPROPOSALABSTRACTArray());
        proposal.setPROPAPPROVALMAPSArray(getPROPAPPROVALMAPSArray());
        proposal.setPROPCHANGEDDATA31Array(getPROPCHANGEDDATA31Array());
        proposal.setPROPCUSTOMDATAArray(getPROPCUSTOMDATAArray());
        proposal.setPROPINVESTIGATORSArray(getPROPINVESTIGATORSArray());
        proposal.setPROPINVESTIGATORSBASICDETAILSArray(getPROPINVESTIGATORSArray());
        proposal.setPROPKEYPERSONSArray(getPROPKEYPERSONSArray());
        proposal.setPROPLOCATIONArray(getPROPLOCATIONArray());
        proposal.setPROPNOTEPADArray(getPROPNOTEPADArray());
        proposal.setPROPPERSONArray(getPROPPERSONArray());
        proposal.setPROPSCIENCECODEArray(getPROPSCIENCECODEArray());
        proposal.setPROPSPECIALREVIEWArray(getPROPSPECIALREVIEWArray());
        proposal.setPROPPERCREDITSPLITArray(getPROPPERCREDITSPLITArray());
        proposal.setCREDITSPLITCOLUMNS(getCreditSplitColumns());
        proposal.setPROPUNITCREDITSPLITArray(getPROPUNITCREDITSPLITArray());
        proposal.setPROPUSERROLESArray(getPROPUSERROLESArray());
        proposal.setPROPYNQArray(getPROPYNQArray());
        if(getBudget(developmentProposal.getProposalDocument()) != null){
            proposal.setBUDGET(getBUDGET());
        }
        proposal.setPROPUNITSArray(getPROPUNITSArray());
        proposal.setCURDATE(getCURDATE());
        proposal.setLOGOPATH(getLOGOPATH());
        proposalDocument.setPROPOSAL(proposal); 
        Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
        xmlObjectList.put("summary", proposalDocument);
        return xmlObjectList;

    }

    private String getLOGOPATH() {
        return null;
    }

    private String getCURDATE() {
        Date todayDate =new Date();   
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");  
        String currentDate = dateFormat.format(todayDate);  
        return currentDate;
    }

    private PROPUNITS[] getPROPUNITSArray() {
        List<PROPUNITS> propUnitList = new ArrayList<PROPUNITS>();
        Unit proposalUnit = developmentProposal.getUnit();
        PROPUNITS propUnits = PROPUNITS.Factory.newInstance();
        propUnits.setPROPOSALNUMBER(developmentProposal.getProposalNumber());
        PROPPERSON propPerson = PROPPERSON.Factory.newInstance();
        propUnits.setPROPPERSON(propPerson);
        UNIT unit = UNIT.Factory.newInstance();
        unit.setUNITNUMBER(proposalUnit.getUnitNumber());
        unit.setUNITNAME(proposalUnit.getUnitName());
        propUnits.setUNIT(unit);
        propUnitList.add(propUnits);
        return propUnitList.toArray(new PROPUNITS[0]);
    }
    
    private PROPUNITS[] getPROPUNITSArray(ProposalPerson person) {
        List<PROPUNITS> propUnitList = new ArrayList<PROPUNITS>();
        developmentProposal.refresh();
        Unit proposalUnit;
        PROPPERSON propPerson = PROPPERSON.Factory.newInstance();
        for (ProposalPersonUnit proposalPUnit : person.getUnits()) {
            proposalUnit = proposalPUnit.getUnit();
            PROPUNITS propUnits = PROPUNITS.Factory.newInstance();
            propUnits.setPROPOSALNUMBER(developmentProposal.getProposalNumber());            
            propUnits.setPROPPERSON(propPerson);
            propUnits.setLEADUNITFLAG(getFlag(proposalPUnit.isLeadUnit()));
            UNIT unit = UNIT.Factory.newInstance();
            unit.setUNITNUMBER(proposalUnit.getUnitNumber());
            unit.setUNITNAME(proposalUnit.getUnitName());
            
            propUnits.setUNIT(unit);
            propUnitList.add(propUnits);
        }
        return propUnitList.toArray(new PROPUNITS[0]);
    }

    private BUDGET getBUDGET() {
        BUDGET proposalBudget = BUDGET.Factory.newInstance();
        proposalBudget.setBudgetMaster(getBudgetMaster());
        return proposalBudget;
    }

    /**
     * 
     * This method generates Proposal Budget details.
     * @return BudgetMaster
     */
    private BudgetMaster getBudgetMaster() {
        BudgetMaster budgetMaster = BudgetMaster.Factory.newInstance();
        Budget budget = getBudget(developmentProposal.getProposalDocument());
        budgetMaster.setCOMMENTS(budget.getComments());
        budgetMaster.setCOSTSHARINGAMOUNT(budget.getCostSharingAmount().bigDecimalValue());
        calendar = Calendar.getInstance();
        calendar.setTime(budget.getEndDate());
        budgetMaster.setENDDATE(calendar);
        budgetMaster.setFINALVERSIONFLAG(getFlag(true));
        budgetMaster.setMODULARBUDGETFLAG(getFlag(budget.getModularBudgetFlag()));
        budgetMaster.setOHRATECLASSCODE(getCode(budget.getOhRateClassCode()));
        budgetMaster.setOHRATETYPECODE(getCode(budget.getOhRateTypeCode()));
        budgetMaster.setPROPOSALNUMBER(developmentProposal.getProposalNumber());

        calendar = Calendar.getInstance();
        calendar.setTime(budget.getStartDate());
        budgetMaster.setSTARTDATE(calendar);
        budgetMaster.setTOTALCOST(budget.getTotalCost().bigDecimalValue());
        budgetMaster.setTOTALCOSTLIMIT(budget.getTotalCostLimit().bigDecimalValue());
        budgetMaster.setTOTALDIRECTCOST(budget.getTotalDirectCost().bigDecimalValue());
        budgetMaster.setTOTALINDIRECTCOST(budget.getTotalIndirectCost().bigDecimalValue());
        budgetMaster.setUNDERRECOVERYAMOUNT(budget.getUnderrecoveryAmount().bigDecimalValue());
        budgetMaster.setTOTALDIRECTCOSTLIMIT(budget.getTotalDirectCostLimit().bigDecimalValue());
        if (budget.getUrRateClass() != null) {
        	budgetMaster.setURRATETYPEDESCRIPTION(budget.getUrRateClass().getDescription());
        }
        if (budget.getRateClass() != null) {
        	budgetMaster.setOHRATETYPEDESCRIPTION(budget.getRateClass().getDescription());
        }
        budgetMaster.setSUBMITCOSTSHARINGFLAG(getFlag(budget.getSubmitCostSharingFlag()));
        budgetMaster.setONOFFCAMPUSFLAG(budget.getOnOffCampusFlagDescription());
        budgetMaster.setBUDGETPERIODArray(getBUDGETPERIODArray());
        if(budget.getResidualFunds()!=null){
            budgetMaster.setRESIDUALFUNDS(budget.getResidualFunds().bigDecimalValue());
        }
        return budgetMaster;
    }

    /**
     * 
     * This method generates Proposal Budget period details.
     * @return BUDGETPERIOD
     */
    private BUDGETPERIOD[] getBUDGETPERIODArray() {
        List<BUDGETPERIOD> budgetPeriodList = new ArrayList<BUDGETPERIOD>();
        BUDGETPERIOD budgetPeriod = null;
        Budget budget = getBudget(developmentProposal.getProposalDocument());
        for (BudgetPeriod budgetPeriodData : budget.getBudgetPeriods()) {
            budgetPeriod = BUDGETPERIOD.Factory.newInstance();
            budgetPeriod.setPROPOSALNUMBER(developmentProposal.getProposalNumber());
            BigDecimal numberOfMonths ;
            numberOfMonths = (getNumberOfMonths(budgetPeriodData.getStartDate(), budgetPeriodData.getEndDate())).bigDecimalValue();
            budgetPeriod.setNOOFMONTHS(numberOfMonths);
            budgetPeriod.setBUDGETPERIODNUMBER(budgetPeriodData.getBudgetPeriod());
            budgetPeriod.setTOTALDIRECTCOST(budgetPeriodData.getTotalDirectCost().bigDecimalValue());
            budgetPeriod.setTOTALINDIRECTCOST(budgetPeriodData.getTotalIndirectCost().bigDecimalValue());
            calendar = Calendar.getInstance();
            calendar.setTime(budgetPeriodData.getEndDate());
            budgetPeriod.setENDDATE(calendar);
            calendar = Calendar.getInstance();
            calendar.setTime(budgetPeriodData.getStartDate());
            budgetPeriod.setSTARTDATE(calendar);
            budgetPeriod.setUNDERRECOVERYAMOUNT(budgetPeriodData.getUnderrecoveryAmount().bigDecimalValue());
            budgetPeriod.setCOSTSHARINGAMOUNT(budgetPeriodData.getCostSharingAmount().bigDecimalValue());
            budgetPeriod.setTOTALCOST(budgetPeriodData.getTotalCost().bigDecimalValue());
            budgetPeriodList.add(budgetPeriod);
        }
        return budgetPeriodList.toArray(new BUDGETPERIOD[0]);
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
            proposalReview.setSPECIALREVIEWNUMBER(specialReview.getSpecialReviewNumber());
            SPECIALREVIEW proposalSpecialReview = SPECIALREVIEW.Factory.newInstance();
            proposalSpecialReview.setDESCRIPTION(specialReview.getSpecialReviewType().getDescription());
            proposalSpecialReview.setSPECIALREVIEWCODE(getCode(specialReview.getSpecialReviewType().getSpecialReviewTypeCode()));
            proposalReview.setSPECIALREVIEW(proposalSpecialReview);
            APPLICABLEREVIEWTYPE applicablereviewtype = APPLICABLEREVIEWTYPE.Factory.newInstance();
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
        developmentProposal.refresh();
        proposalmaster.setPROPOSALNUMBER(developmentProposal.getProposalNumber());
        PROPOSALTYPE proposaltype = PROPOSALTYPE.Factory.newInstance();
        if (developmentProposal.getProposalType() != null) {
            proposaltype.setPROPOSALTYPECODE(getCode(developmentProposal.getProposalType().getCode()));
            proposaltype.setDESCRIPTION(developmentProposal.getProposalType().getDescription());
        }        
        ACTIVITYTYPE activityType = ACTIVITYTYPE.Factory.newInstance();
        if (developmentProposal.getActivityType() != null) {
            activityType.setACTIVITYTYPECODE(getCode(developmentProposal.getActivityType().getCode()));
            activityType.setDESCRIPTION(developmentProposal.getActivityType().getDescription());
        }
        proposalmaster.setPROPOSALTYPE(proposaltype);
        proposalmaster.setACTIVITYTYPE(activityType);
        PROPOSALSTATUS proposalstatus = PROPOSALSTATUS.Factory.newInstance();
        proposalstatus.setPROPOSALSTATUSCODE(getCode(developmentProposal.getProposalStateTypeCode()));
        proposalmaster.setPROPOSALSTATUS(proposalstatus);
        ANTICIPATEDAWARDTYPE anticipatedAwaraType = ANTICIPATEDAWARDTYPE.Factory.newInstance();
        if (developmentProposal.getAnticipatedAwardType() != null) {
            anticipatedAwaraType.setDESCRIPTION(developmentProposal.getAnticipatedAwardType().getDescription());
        }
        proposalmaster.setANTICIPATEDAWARDTYPE(anticipatedAwaraType);
        calendar = Calendar.getInstance();
        calendar.setTime(developmentProposal.getRequestedStartDateInitial());
        proposalmaster.setREQUESTEDSTARTDATEINITIAL(calendar);

        calendar = Calendar.getInstance();
        calendar.setTime(developmentProposal.getRequestedEndDateInitial());
        proposalmaster.setREQUESTEDENDDATEINITIAL(calendar);

        calendar = Calendar.getInstance();
        if (developmentProposal.getDeadlineDate() != null) {
            calendar.setTime(developmentProposal.getDeadlineDate());
        }
        proposalmaster.setDEADLINEDATE(calendar);
        PRIMESPONSOR primeSponsor = PRIMESPONSOR.Factory.newInstance();
        SPONSOR sponsor = SPONSOR.Factory.newInstance();
        sponsor.setSPONSORCODE(developmentProposal.getSponsor().getSponsorCode());
        sponsor.setSPONSORNAME(developmentProposal.getSponsor().getSponsorName());
        proposalmaster.setSPONSOR(sponsor);
        
        if (developmentProposal.getPrimeSponsor() != null) {
            SPONSOR primesponsor = SPONSOR.Factory.newInstance();
            primesponsor.setSPONSORCODE(developmentProposal.getPrimeSponsor().getSponsorCode());
            primesponsor.setSPONSORNAME(developmentProposal.getPrimeSponsor().getSponsorName());
            primeSponsor.setSPONSOR(primesponsor);
            proposalmaster.setPRIMESPONSOR(primeSponsor);
        }


        Budget budget = getBudget(developmentProposal.getProposalDocument());
        
        if(developmentProposal.getNoticeOfOpportunity()!=null){
            NOTICEOFOPPORTUNITY noticeOfOpportunity = NOTICEOFOPPORTUNITY.Factory.newInstance();
            noticeOfOpportunity.setNOTICEOFOPPORTUNITYCODE(getCode(developmentProposal.getNoticeOfOpportunity().getCode()));
            noticeOfOpportunity.setDESCRIPTION(developmentProposal.getNoticeOfOpportunity().getDescription());
            proposalmaster.setNOTICEOFOPPORTUNITY(noticeOfOpportunity);
        }
        proposalmaster.setBUDGETSTATUS(developmentProposal.getBudgetStatus());
      
        proposalmaster.setSPONSORPROPOSALNUMBER(developmentProposal.getSponsorProposalNumber());
        proposalmaster.setPROGRAMANNOUNCEMENTTITLE(developmentProposal.getProgramAnnouncementTitle());
        proposalmaster.setPROGRAMANNOUNCEMENTNUMBER(developmentProposal.getProgramAnnouncementNumber());
        proposalmaster.setCFDANUMBER(developmentProposal.getCfdaNumber());
        proposalmaster.setCONTINUEDFROM(developmentProposal.getContinuedFrom());
        proposalmaster.setCURRENTAWARDNUMBER(developmentProposal.getCurrentAwardNumber());
       
        proposalmaster.setNSFCODE(developmentProposal.getNsfCode());
        proposalmaster.setORGANIZATION(getORGANIZATION());
        PRINCIPALINVESTIGATORNAME PincipalInvestigatorName = PRINCIPALINVESTIGATORNAME.Factory.newInstance();
        proposalmaster.setPRINCIPALINVESTIGATORNAME(developmentProposal.getPrincipalInvestigatorName());
        proposalmaster.setTITLE(developmentProposal.getTitle());
        proposalmaster.setPERIOD(getPERIOD());
        if (budget != null){
            proposalmaster.setSPONSORCOST(getCurrencyFormat(budget.getTotalCost().bigDecimalValue()));
            proposalmaster.setCOSTSHARING(getCurrencyFormat(budget.getCostSharingAmount().bigDecimalValue()));
        }

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
        rolodex.setADDRESS(address);
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

    private CREDITSPLITCOLUMNS getCreditSplitColumns() {

        CREDITSPLITCOLUMNS proposalCreditSplitColumns = CREDITSPLITCOLUMNS.Factory.newInstance();
        Collection<InvestigatorCreditType> investigatorCreditTypes;
        developmentProposal.refresh();
        if (developmentProposal.getInvestigatorCreditTypes() != null) {
            investigatorCreditTypes = developmentProposal.getInvestigatorCreditTypes();
            for (InvestigatorCreditType investigatorCreditType : investigatorCreditTypes) {
                String invCreditTypeCode = investigatorCreditType.getCode();
                String invCreditDescription = investigatorCreditType.getDescription();
                if (invCreditTypeCode.equals("0")) {
                    proposalCreditSplitColumns.setColumnName1(invCreditDescription);
                } else if (invCreditTypeCode.equals("1")) {
                    proposalCreditSplitColumns.setColumnName2(invCreditDescription);
                } else if (invCreditTypeCode.equals("2")) {
                    proposalCreditSplitColumns.setColumnName3(invCreditDescription);
                } else if (invCreditTypeCode.equals("3")) {
                    proposalCreditSplitColumns.setColumnName4(invCreditDescription);
                } else if (invCreditTypeCode.equals("4")) {
                    proposalCreditSplitColumns.setColumnName5(invCreditDescription);
                } else if (invCreditTypeCode.equals("5")) {
                    proposalCreditSplitColumns.setColumnName6(invCreditDescription);
                }
            }
        }
        return proposalCreditSplitColumns;
    }

    /**
     * 
     * This method generates Proposal Investigators details.
     * @return PROPINVESTIGATORTYPE List
     */
    private PROPINVESTIGATORTYPE[] getPROPINVESTIGATORSArray() {
        List<PROPINVESTIGATORTYPE> propInvestigatorList = new ArrayList<PROPINVESTIGATORTYPE>();

        List<ProposalPerson> investigatorList = developmentProposal.getInvestigators();
        PROPINVESTIGATORTYPE propInvestigator = null;
        for (ProposalPerson proposalPerson : investigatorList) {
            propInvestigator = PROPINVESTIGATORTYPE.Factory.newInstance();
            propInvestigator.setPROPOSALNUMBER(developmentProposal.getProposalNumber());
            propInvestigator.setPERSONID(proposalPerson.getPersonId());
            propInvestigator.setPERSONNAME(proposalPerson.getPerson().getFullName());
            propInvestigator.setPERSONNAME(proposalPerson.getFullName());
            if (Constants.PRINCIPAL_INVESTIGATOR_ROLE.equals(proposalPerson.getProposalPersonRoleId())) {
                propInvestigator.setPRINCIPALINVESTIGATORFLAG(getFlag(proposalPerson.isInvestigator()));
            }
            
            propInvestigator.setPROPUNITSArray(getPROPUNITSArray(proposalPerson));
            propInvestigator.setFACULTYFLAG(getFlag(proposalPerson.getFacultyFlag()));
            if(proposalPerson.getPercentageEffort()!=null){
                propInvestigator.setPERCENTAGEEFFORT(proposalPerson.getPercentageEffort().bigDecimalValue());
            }
            
            propInvestigator.setCONFLICTOFINTERESTFLAG(getFlag(proposalPerson.getConflictOfInterestFlag()));
            if (proposalPerson.getUpdateTimestamp() != null) {
                calendar = Calendar.getInstance();
                calendar.setTime(toDate(proposalPerson.getUpdateTimestamp()));
                propInvestigator.setUPDATETIMESTAMP(calendar);
            }
            propInvestigator.setUPDATEUSER(proposalPerson.getUpdateUser());
            propInvestigatorList.add(propInvestigator);
        }
        return propInvestigatorList.toArray(new PROPINVESTIGATORTYPE[0]);
    }

    private PROPKEYPERSONS[] getPROPKEYPERSONSArray() {
        // ProposalPerson of type Key Person.
        return null;
    }

    private PROPLOCATION[] getPROPLOCATIONArray() {
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
