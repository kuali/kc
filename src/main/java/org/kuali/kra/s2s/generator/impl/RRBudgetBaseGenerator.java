/*
 * Copyright 2005-2010 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.generator.impl;

import gov.grants.apply.coeus.additionalEquipment.AdditionalEquipmentListDocument;
import gov.grants.apply.coeus.additionalEquipment.AdditionalEquipmentListDocument.AdditionalEquipmentList;
import gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument;
import gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument.ExtraKeyPersonList;
import gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument.ExtraKeyPersonList.KeyPersons.Compensation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.GenericPrintable;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.generator.bo.BudgetPeriodInfo;
import org.kuali.kra.s2s.generator.bo.CostInfo;
import org.kuali.kra.s2s.generator.bo.EquipmentInfo;
import org.kuali.kra.s2s.generator.bo.KeyPersonInfo;
import org.kuali.kra.s2s.service.S2SBudgetCalculatorService;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * This abstract class has methods that are common to all the versions of
 * RRBudget form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class RRBudgetBaseGenerator extends S2SBaseFormGenerator {
	private static final String ADDITIONAL_EQUIPMENT = "ADDITIONAL_EQUIPMENT";
    protected S2SBudgetCalculatorService s2sBudgetCalculatorService;
	protected S2SUtilService s2sUtilService;
	protected BusinessObjectService businessObjectService;
	public static final String OTHERCOST_DESCRIPTION = "Other";
	public static final String OTHERPERSONNEL_POSTDOC = "PostDoc";
	public static final String OTHERPERSONNEL_GRADUATE = "Grad";
	public static final String OTHERPERSONNEL_UNDERGRADUATE = "UnderGrad";
	public static final String OTHERPERSONNEL_SECRETARIAL = "Sec";
	public static final String NARRATIVE_ATTACHMENT_LIST = "narrativeAttachmentList";
	public static final int BUDGET_JUSTIFICATION_ATTACHMENT = 7;
	public static final int ADDITIONAL_KEYPERSONS_ATTACHMENT = 11;
	public static final int ADDITIONAL_EQUIPMENT_ATTACHMENT = 12;

	protected static final int OTHERPERSONNEL_MAX_ALLOWED = 6;
	protected static final int ARRAY_LIMIT_IN_SCHEMA = 4;
	protected static final String SPONSOR_GROUPS = "SPONSOR GROUPS";
	protected static final String NID_PD_PI = "PD/PI";
	protected static final String NID_CO_PD_PI = "CO-INVESTIGATOR";		
	protected static final String KEYPERSON_CO_PD_PI = "CO-PD/PI";
	
	private static final String EXTRA_KEYPERSONS = "EXTRA_KEYPERSONS";
	private static final String EQUIPMENT_NARRATIVE_TYPE_CODE = "12";

	private static final String EXTRA_KEYPERSON_ATTACHMENT_XSL = "/org/kuali/kra/s2s/stylesheet/ExtraKeyPersonAttachment.xsl";
	private static final String EXTRA_KEYPERSONS_COMMENT = "EXTRA_KEYPERSONS";
	private static final String EXTRA_KEYPERSONS_TYPE = "11";


	/**
	 * 
	 * Constructs a RRBudgetBaseGenerator.java.
	 */
	public RRBudgetBaseGenerator() {
		s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
		s2sBudgetCalculatorService = KraServiceLocator
				.getService(S2SBudgetCalculatorService.class);
		businessObjectService = KraServiceLocator
				.getService(BusinessObjectService.class);
	}
	protected void deleteAutoGenNarratives() {
		BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
		for (Narrative narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeTypeCode() != null
					&& (Integer.parseInt(narrative.getNarrativeTypeCode()) == ADDITIONAL_KEYPERSONS_ATTACHMENT || 
							Integer.parseInt(narrative.getNarrativeTypeCode()) == ADDITIONAL_EQUIPMENT_ATTACHMENT)) {
				businessObjectService.delete(narrative);
			}
		}
		pdDoc.getDevelopmentProposal();
	}
	
	protected Narrative saveAdditionalEquipments(BudgetPeriodInfo periodInfo,List<CostInfo> extraEquipmentArrayList) {
//		List<EquipmentInfo> equipmentInfoList = periodInfo.getEquipment();
	    Narrative narrative = null;
		if (extraEquipmentArrayList.size() > 0) {
			AdditionalEquipmentList additionalEquipmentList = AdditionalEquipmentList.Factory
					.newInstance();
			additionalEquipmentList.setProposalNumber(pdDoc
					.getDevelopmentProposal().getProposalNumber());
			additionalEquipmentList.setBudgetPeriod(new BigInteger(Integer
					.toString(periodInfo.getBudgetPeriod())));
			additionalEquipmentList
					.setEquipmentListArray(getEquipmentListArray(extraEquipmentArrayList));

			AdditionalEquipmentListDocument additionalEquipmentDoc = AdditionalEquipmentListDocument.Factory.newInstance();
			additionalEquipmentDoc.setAdditionalEquipmentList(additionalEquipmentList);
			Source xsltSource = new StreamSource(
					getClass()
							.getResourceAsStream(
									"/org/kuali/kra/s2s/stylesheet/AdditionalEquipmentAttachment.xsl"));
			Map<String, Source> xSLTemplateWithBookmarks = new HashMap<String, Source>();
			xSLTemplateWithBookmarks.put("", xsltSource);

			String xmlData = additionalEquipmentDoc.xmlText();
			Map<String, byte[]> streamMap = new HashMap<String, byte[]>();
			streamMap.put("", xmlData.getBytes());
			GenericPrintable printable = new GenericPrintable();
			printable.setXSLTemplateWithBookmarks(xSLTemplateWithBookmarks);
			printable.setStreamMap(streamMap);
			PrintingService printingService = KraServiceLocator
					.getService(PrintingService.class);
			try {
				AttachmentDataSource printData = printingService
						.print(printable);
				String fileName=pdDoc.getDevelopmentProposal().getProposalNumber()+periodInfo.getBudgetPeriod()+ADDITIONAL_EQUIPMENT+".pdf";
				narrative = saveNarrative(printData.getContent(),
						EQUIPMENT_NARRATIVE_TYPE_CODE,fileName,ADDITIONAL_EQUIPMENT);
			} catch (PrintingException e) {
				e.printStackTrace();
			}
		}
		return narrative;
	}

	private gov.grants.apply.coeus.additionalEquipment.AdditionalEquipmentListDocument.AdditionalEquipmentList.EquipmentList[] getEquipmentListArray(
			List<CostInfo> extraEquipmentArrayList) {
		List<AdditionalEquipmentList.EquipmentList> additionalEquipmentListList = new ArrayList<AdditionalEquipmentList.EquipmentList>();
		AdditionalEquipmentList.EquipmentList equipmentList = null;
		for (CostInfo costInfo : extraEquipmentArrayList) {
			equipmentList = AdditionalEquipmentList.EquipmentList.Factory
					.newInstance();
			equipmentList.setFundsRequested(costInfo.getCost()
					.bigDecimalValue());
			equipmentList
					.setEquipmentItem(costInfo.getDescription() != null ? costInfo
							.getDescription()
							: costInfo.getCategory());
			additionalEquipmentListList.add(equipmentList);
		}
		return additionalEquipmentListList
				.toArray(new gov.grants.apply.coeus.additionalEquipment.AdditionalEquipmentListDocument.AdditionalEquipmentList.EquipmentList[0]);
	}
	protected Narrative saveExtraKeyPersons(BudgetPeriodInfo periodInfo) {
	    Narrative extraKPNarrative = null;
		if (periodInfo.getExtraKeyPersons() != null && !periodInfo.getExtraKeyPersons().isEmpty()) {
			ExtraKeyPersonListDocument  extraKeyPersonListDocument = ExtraKeyPersonListDocument.Factory.newInstance();
			ExtraKeyPersonList extraKeyPersonList = ExtraKeyPersonList.Factory.newInstance(); 
			extraKeyPersonList.setProposalNumber(pdDoc.getDevelopmentProposal().getProposalNumber());
			extraKeyPersonList.setBudgetPeriod(new BigInteger(""+periodInfo.getBudgetPeriod()));
			extraKeyPersonList.setKeyPersonsArray(getExtraKeyPersons(periodInfo.getExtraKeyPersons()));
			extraKeyPersonListDocument.setExtraKeyPersonList(extraKeyPersonList);
			String xmlData = extraKeyPersonListDocument.xmlText();
			Map<String, byte[]> streamMap = new HashMap<String, byte[]>();
			streamMap.put("", xmlData.getBytes());
			Source xsltSource = new StreamSource(getClass()
					.getResourceAsStream(EXTRA_KEYPERSON_ATTACHMENT_XSL));
			Map<String, Source> xSLTemplateWithBookmarks = new HashMap<String, Source>();
			xSLTemplateWithBookmarks.put("", xsltSource);
			
			GenericPrintable printable = new GenericPrintable();
			printable.setXSLTemplateWithBookmarks(xSLTemplateWithBookmarks);
			printable.setStreamMap(streamMap);
			PrintingService printingService= KraServiceLocator.getService(PrintingService.class);
			try {
				AttachmentDataSource printData = printingService.print(printable);
				String fileName = pdDoc.getDevelopmentProposal().getProposalNumber()+periodInfo.getBudgetPeriod()+"_"+EXTRA_KEYPERSONS+".pdf";
				extraKPNarrative = saveNarrative(printData.getContent(), EXTRA_KEYPERSONS_TYPE, fileName, EXTRA_KEYPERSONS_COMMENT);
			} catch (PrintingException e) {
				e.printStackTrace();
			}
		}
		return extraKPNarrative;
	}
	private gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument.ExtraKeyPersonList.KeyPersons[] getExtraKeyPersons(List<KeyPersonInfo> keyPersonList) {
		List<gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument.ExtraKeyPersonList.KeyPersons> keypersonslist = new ArrayList<gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument.ExtraKeyPersonList.KeyPersons>();
		for(KeyPersonInfo keyPersonInfo : keyPersonList){
			gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument.ExtraKeyPersonList.KeyPersons keyPerson = gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument.ExtraKeyPersonList.KeyPersons.Factory.newInstance();
			keyPerson.setFirstName(keyPersonInfo.getFirstName());
			keyPerson.setMiddleName(keyPersonInfo.getMiddleName());
			keyPerson.setLastName(keyPersonInfo.getLastName());
			keyPerson.setProjectRole(keyPersonInfo.getRole());
			keyPerson.setCompensation(getExtraKeyPersonCompensation(keyPersonInfo));
			keypersonslist.add(keyPerson);
		}
		return keypersonslist.toArray(new gov.grants.apply.coeus.extraKeyPerson.ExtraKeyPersonListDocument.ExtraKeyPersonList.KeyPersons[0]);
	}
	private Compensation getExtraKeyPersonCompensation(
			KeyPersonInfo keyPersonInfo) {
		Compensation compensation = Compensation.Factory.newInstance();
		if(keyPersonInfo.getAcademicMonths() != null){
			compensation.setAcademicMonths(keyPersonInfo.getAcademicMonths().bigDecimalValue());
		}
		if(keyPersonInfo.getBaseSalary() != null){
			compensation.setBaseSalary(keyPersonInfo.getBaseSalary().bigDecimalValue());
		}
		if(keyPersonInfo.getCalendarMonths() != null){
			compensation.setCalendarMonths(keyPersonInfo.getCalendarMonths().bigDecimalValue());
		}
		if(keyPersonInfo.getFringe() != null){
			compensation.setFringeBenefits(keyPersonInfo.getFringe().bigDecimalValue());
		}
		if(keyPersonInfo.getFundsRequested() != null){
			compensation.setFundsRequested(keyPersonInfo.getFundsRequested().bigDecimalValue());
		}
		if(keyPersonInfo.getRequestedSalary() != null){
			compensation.setRequestedSalary(keyPersonInfo.getRequestedSalary().bigDecimalValue());
		}
		if(keyPersonInfo.getSummerMonths() != null){
			compensation.setSummerMonths(keyPersonInfo.getSummerMonths().bigDecimalValue());
		}
		return compensation;
	}
   protected boolean isProposalPersonEqualsKeyPerson(ProposalPerson proposalPerson, KeyPersonInfo keyPerson) {
        if(keyPerson.getPersonId()!=null){
            return keyPerson.getPersonId().equals(proposalPerson.getPersonId());
        }else if(keyPerson.getRolodexId()!=null){
            return keyPerson.getRolodexId().equals(proposalPerson.getRolodexId());
        }
        return false;
    }

}
