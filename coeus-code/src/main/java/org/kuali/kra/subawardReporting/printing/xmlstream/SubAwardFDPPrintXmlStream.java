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
package org.kuali.kra.subawardReporting.printing.xmlstream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.print.stream.xml.XmlStream;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.ContactType;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardAmountInfo;
import org.kuali.kra.subaward.bo.SubAwardTemplateInfo;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.reporting.printing.SubAwardPrintType;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

import org.kuali.kra.infrastructure.Constants;

import org.kuali.kra.award.printing.schema.AwardHeaderType;
import org.kuali.kra.award.printing.schema.AwardType;
import org.kuali.kra.award.printing.schema.AwardType.AwardDetails;
import org.kuali.kra.award.printing.schema.AwardType.AwardDetails.OtherHeaderDetails;
import org.kuali.kra.subaward.printing.schema.OrganizationType;
import org.kuali.kra.subaward.printing.schema.RolodexDetailsType;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.FundingSource;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.PrimeRecipientContacts;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.PrintRequirement;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.SubcontractAmountInfo;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.SubcontractContacts.RolodexDetails;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.SubcontractDetail;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.SubcontractTemplateInfo;

public class SubAwardFDPPrintXmlStream implements XmlStream  {
    private static final String SUB_AWARD_FDP_TEMPLATE  = "fdpAgreement";
    private BusinessObjectService businessObjectService;
    protected SubAward subaward = null;
    protected SubAwardDocument subawarddoc = null;
    private String awardNumber;
    private String sponsorName;
    private String cfdaNumber;
    private String unitName;
    private ParameterService parameterService;
    
    
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    /**
     * Gets the unitName attribute. 
     * @return Returns the unitName.
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * Sets the unitName attribute value.
     * @param unitName The unitName to set.
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    
    /**
     * Gets the cfdaNumber attribute. 
     * @return Returns the cfdaNumber.
     */
    public String getCfdaNumber() {
        return cfdaNumber;
    }

    /**
     * Sets the cfdaNumber attribute value.
     * @param cfdaNumber The cfdaNumber to set.
     */
    public void setCfdaNumber(String cfdaNumber) {
        this.cfdaNumber = cfdaNumber;
    }
    private DateTimeService dateTimeService;
    /**
     * Gets the dateTimeService attribute. 
     * @return Returns the dateTimeService.
     */
    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    /**
     * Sets the dateTimeService attribute value.
     * @param dateTimeService The dateTimeService to set.
     */
    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }
   
    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * Gets the awardNumber attribute. 
     * @return Returns the awardNumber.
     */
    public String getAwardNumber() {
        return awardNumber;
    }

    /**
     * Sets the awardNumber attribute value.
     * @param awardNumber The awardNumber to set.
     */
    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    /**
     * Gets the sponsorName attribute. 
     * @return Returns the sponsorName.
     */
    public String getSponsorName() {
        return sponsorName;
    }

    /**
     * Sets the sponsorName attribute value.
     * @param sponsorName The sponsorName to set.
     */
    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    /**
     * This method get's the businessObjectService
     */
    
    @Override
    public Map<String, XmlObject> generateXmlStream(KcPersistableBusinessObjectBase printableBusinessObject,
            Map<String, Object> reportParameters) {
        Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
        SubContractDataDocument subContractDataDoc = SubContractDataDocument.Factory.newInstance();
        SubContractData subContractData = SubContractData.Factory.newInstance();
        SubAward subaward=(SubAward) printableBusinessObject;
        this.awardNumber=(String) reportParameters.get("awardNumber");
        this.sponsorName=(String) reportParameters.get("sponsorName");
        this.cfdaNumber=(String) reportParameters.get("cfdaNumber");
        setSubcontractTemplateInfo(subContractData,subaward);
        setFundingSource(subContractData,subaward);
        setSubcontractDetail(subContractData,subaward);
        setSubcontractAmountInfo(subContractData,subaward);
        setAwardHeader(subContractData,subaward);
        setPrimeRecipientContacts(subContractData,subaward);
        setPrintRequirement(subContractData,subaward);
        subContractDataDoc.setSubContractData(subContractData);
        xmlObjectList.put(SubAwardPrintType.SUB_AWARD_FDP_TEMPLATE.getSubAwardPrintType(), subContractDataDoc);
        return xmlObjectList;
    }

   public void setSubcontractTemplateInfo(SubContractData subContractData, SubAward subaward) {
       SubcontractTemplateInfo subContractTemplateInfo = SubcontractTemplateInfo.Factory.newInstance();
       List<SubcontractTemplateInfo> templateDataList = new ArrayList<SubcontractTemplateInfo>();
      
       SubAwardTemplateInfo subawardTemplate=null;
       if(subaward.getSubAwardTemplateInfo() != null && !subaward.getSubAwardTemplateInfo().isEmpty() ){
           subawardTemplate =subaward.getSubAwardTemplateInfo().get(0);
      
       Collection<ContactType> contact = (Collection<ContactType>) KcServiceLocator.getService(BusinessObjectService.class).findAll(ContactType.class);
       for(ContactType contactType : contact){
           if(subawardTemplate.getInvoiceOrPaymentContact()!= null && contactType.getContactTypeCode().equals(subawardTemplate.getInvoiceOrPaymentContact().toString())){
               subContractTemplateInfo.setInvoiceOrPaymentContactDescription(contactType.getDescription());
           }
           if(subawardTemplate.getFinalStmtOfCostscontact()!=null && contactType.getContactTypeCode().equals(subawardTemplate.getFinalStmtOfCostscontact().toString())){
               subContractTemplateInfo.setFinalStmtOfCostsContactDescription(contactType.getDescription());
           }
           if(subawardTemplate.getChangeRequestsContact()!= null && contactType.getContactTypeCode().equals(subawardTemplate.getChangeRequestsContact().toString())){
               subContractTemplateInfo.setChangeRequestsContactDescription(contactType.getDescription());
           }
           if(subawardTemplate.getTerminationContact()!= null && contactType.getContactTypeCode().equals(subawardTemplate.getTerminationContact().toString())){
               subContractTemplateInfo.setTerminationContactDescription(contactType.getDescription());
           }
           if(subawardTemplate.getNoCostExtensionContact() != null && contactType.getContactTypeCode().equals(subawardTemplate.getNoCostExtensionContact().toString())){
               subContractTemplateInfo.setNoCostExtensionContactDescription(contactType.getDescription());
           }
        }
           subContractTemplateInfo.setSowOrSubProposalBudget(subawardTemplate.getSowOrSubProposalBudget());
           if(subawardTemplate.getSubProposalDate() != null){
            subContractTemplateInfo.setSubProposalDate(getDateTimeService().getCalendar(subawardTemplate.getSubProposalDate()));
           }
           templateDataList.add(subContractTemplateInfo);
       }
           subContractData.setSubcontractTemplateInfoArray((SubcontractTemplateInfo[])templateDataList.toArray(new SubcontractTemplateInfo[0]));
    }
        public void setFundingSource(SubContractData subContractData, SubAward subaward) {
            FundingSource fundingSource = FundingSource.Factory.newInstance();
            List<FundingSource> fundingSourceList = new ArrayList<FundingSource>();
            fundingSource.setAwardNumber(awardNumber);
            fundingSource.setSponsor(sponsorName);
            fundingSourceList.add(fundingSource);
            subContractData.setFundingSourceArray((FundingSource[])fundingSourceList.toArray(new FundingSource[0]));
        }
        
        public void setSubcontractDetail(SubContractData subContractData, SubAward subaward) {
            SubcontractDetail subcontractDetail = SubcontractDetail.Factory.newInstance();
            RolodexDetailsType rolodexDetails = RolodexDetailsType.Factory.newInstance();
            if(subaward.getRolodex() != null ){
                subcontractDetail.setSiteInvestigator(subaward.getRolodex().getFullName());
            }
                subcontractDetail.setPONumber(subaward.getPurchaseOrderNum());
            if(subaward.getOrganization() != null){
                subcontractDetail.setSubcontractorName(subaward.getOrganization().getOrganizationName());
                rolodexDetails.setAddress1(subaward.getOrganization().getAddress());
            }
            if(subaward.getStartDate() != null){
                subcontractDetail.setStartDate(getDateTimeService().getCalendar(subaward.getStartDate()));
            }
            if(subaward.getEndDate() != null){
                subcontractDetail.setEndDate(getDateTimeService().getCalendar(subaward.getEndDate()));
            }
            subcontractDetail.setSubcontractorOrgRolodexDetails(rolodexDetails);
            subContractData.setSubcontractDetail(subcontractDetail);
        }
        public void setSubcontractAmountInfo(SubContractData subContractData, SubAward subaward) {
           
            SubcontractAmountInfo subContractAmountInfo = SubcontractAmountInfo.Factory.newInstance();
            List<SubcontractAmountInfo> amountinfoList = new ArrayList<SubcontractAmountInfo>();
            SubAwardAmountInfo subawardAmount=null;
            if(subaward.getSubAwardAmountInfoList() != null && !subaward.getSubAwardAmountInfoList().isEmpty()){
                subawardAmount = subaward.getSubAwardAmountInfoList().get(0);
                subContractAmountInfo.setObligatedAmount(subawardAmount.getObligatedAmount().bigDecimalValue());
                subContractAmountInfo.setAnticipatedAmount(subawardAmount.getAnticipatedAmount().bigDecimalValue());
            }
            if(subaward.getPerformanceStartDate() != null){
                subContractAmountInfo.setPerformanceStartDate(getDateTimeService().getCalendar(subaward.getPerformanceStartDate()));
            }
            if(subaward.getPerformanceEnddate() != null){
                subContractAmountInfo.setPerformanceEndDate(getDateTimeService().getCalendar(subaward.getPerformanceEnddate()));
            }
            if(subaward.getModificationEffectiveDate() != null){
                subContractAmountInfo.setModificationEffectiveDate(getDateTimeService().getCalendar(subaward.getModificationEffectiveDate()));
            }
            if(subaward.getModificationId() != null){
                subContractAmountInfo.setModificationNumber(subaward.getModificationId());
            }
            amountinfoList.add(subContractAmountInfo);
            subContractData.setSubcontractAmountInfoArray((SubcontractAmountInfo[])amountinfoList.toArray(new SubcontractAmountInfo[0]));
        }
        public void setAwardHeader(SubContractData subContractData, SubAward subaward) {
            AwardType awardType= AwardType.Factory.newInstance();
            AwardHeaderType awardHeaderType =AwardHeaderType.Factory.newInstance();
            AwardDetails awardDetails= AwardDetails.Factory.newInstance();
            OtherHeaderDetails otherDetails = OtherHeaderDetails.Factory.newInstance();
            List<AwardType> awardTypeList = new ArrayList<AwardType>();
            awardHeaderType.setSponsorAwardNumber(awardNumber);
            awardHeaderType.setSponsorDescription(sponsorName);
            if(subaward.getTitle() != null){
                awardHeaderType.setTitle(subaward.getTitle());
            }
            if(cfdaNumber != null){
                otherDetails.setCFDANumber(cfdaNumber);
            }
            awardDetails.setAwardHeader(awardHeaderType);
            awardDetails.setOtherHeaderDetails(otherDetails);
            awardType.setAwardDetails(awardDetails);
            awardTypeList.add(awardType);
            subContractData.setAwardArray((AwardType[])awardTypeList.toArray(new AwardType[0]));
        }
        public void setPrimeRecipientContacts(SubContractData subContractData, SubAward subaward) {
            PrimeRecipientContacts primeReceipient = PrimeRecipientContacts.Factory.newInstance();
            OrganizationType organisation= OrganizationType.Factory.newInstance();
            RolodexDetailsType rolodexDetails =RolodexDetailsType.Factory.newInstance();
            UnitService unitService = KcServiceLocator.
                    getService(UnitService.class);
            if(subaward.getRequisitionerUnit() != null)
                {
                this.unitName = unitService.getUnitName(subaward.getRequisitionerUnit());
                organisation.setOrganizationName(unitName);
                }
            if(subaward.getOrganization().getRolodex() != null ){
                rolodexDetails.setAddress1(subaward.getOrganization().getRolodex().getAddressLine1());
                rolodexDetails.setAddress2(subaward.getOrganization().getRolodex().getAddressLine2());
            }
            primeReceipient.setOrgRolodexDetails(rolodexDetails);
            primeReceipient.setRequisitionerOrgDetails(organisation);
            subContractData.setPrimeRecipientContacts(primeReceipient);
        }
        public void setPrintRequirement(SubContractData subContractData, SubAward subaward) {
            PrintRequirement printrequirement =PrintRequirement.Factory.newInstance();
            printrequirement.setAttachment5Required("N");
            parameterService = KcServiceLocator.getService(ParameterService.class);
            String subawardattachment5 = parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_SUBAWARD, ParameterConstants.DOCUMENT_COMPONENT, "Subaward_FDP_Attachment_5_Form_ID");
            if(subawardattachment5.equals("1")){
              printrequirement.setAttachment5Required("Y");
            }
           subContractData.setPrintRequirement(printrequirement);
        }
}
