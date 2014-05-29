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
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.ContactType;
import org.kuali.kra.award.home.ContactUsage;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.printing.xmlstream.XmlStream;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.UnitService;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardAmountInfo;
import org.kuali.kra.subaward.bo.SubAwardContact;
import org.kuali.kra.subaward.bo.SubAwardForms;
import org.kuali.kra.subaward.bo.SubAwardFundingSource;
import org.kuali.kra.subaward.bo.SubAwardReportType;
import org.kuali.kra.subaward.bo.SubAwardReports;
import org.kuali.kra.subaward.bo.SubAwardTemplateInfo;
import org.kuali.kra.subaward.config.SubAwardConfigurer;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.subawardrule.SubAwardDocumentRule;
import org.kuali.kra.subawardReporting.printing.SubAwardPrintType;
import org.kuali.kra.subawardReporting.printing.service.SubAwardPrintingService;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.uif.UifConstants.ConfigProperties;

import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.award.AwardHeaderType;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.award.AwardType;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.award.AwardType.AwardDetails;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.award.AwardType.AwardDetails.OtherHeaderDetails;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.OrganizationType;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.PersonDetailsType;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.RolodexDetailsType;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.SubContractDataDocument;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.SubContractDataDocument.SubContractData;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.SubContractDataDocument.SubContractData.AdministrativeContact;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.SubContractDataDocument.SubContractData.AuthorizedOfficial;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.SubContractDataDocument.SubContractData.FinancialContact;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.SubContractDataDocument.SubContractData.FundingSource;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.SubContractDataDocument.SubContractData.PrimeAdministrativeContact;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.SubContractDataDocument.SubContractData.PrimeAuthorizedOfficial;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.SubContractDataDocument.SubContractData.PrimeFinancialContact;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.SubContractDataDocument.SubContractData.PrimeRecipientContacts;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.SubContractDataDocument.SubContractData.PrintRequirement;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.SubContractDataDocument.SubContractData.SubcontractAmountInfo;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.SubContractDataDocument.SubContractData.SubcontractAttachments;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.SubContractDataDocument.SubContractData.SubcontractContacts.RolodexDetails;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.SubContractDataDocument.SubContractData.SubcontractDetail;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.SubContractDataDocument.SubContractData.SubcontractReports;
import edu.mit.coeus.utils.xml.bean.subcontractFdpReports.subcontract.SubContractDataDocument.SubContractData.SubcontractTemplateInfo;

public class SubAwardFDPPrintXmlStream implements XmlStream  {
    private static final String SUB_AWARD_FDP_TEMPLATE  = "fdpAgreement";
    private BusinessObjectService businessObjectService;
    protected SubAward subaward = null;
    protected SubAwardDocument subawarddoc = null;
    private String awardNumber;
    private String sponsorName;
    private String cfdaNumber;
    private String unitName;
    private List<SubAwardForms> sponsorTemplates;
    private ParameterService parameterService;
    
    
   

    /**
     * Gets the sponsorTemplates attribute. 
     * @return Returns the sponsorTemplates.
     */
    public List<SubAwardForms> getSponsorTemplates() {
        return sponsorTemplates;
    }

    /**
     * Sets the sponsorTemplates attribute value.
     * @param sponsorTemplates The sponsorTemplates to set.
     */
    public void setSponsorTemplates(List<SubAwardForms> sponsorTemplates) {
        this.sponsorTemplates = sponsorTemplates;
    }

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
    public Map<String, XmlObject> generateXmlStream(KraPersistableBusinessObjectBase printableBusinessObject,
            Map<String, Object> reportParameters) {
        
        Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
        SubContractDataDocument subContractDataDoc = SubContractDataDocument.Factory.newInstance();
        SubContractData subContractData = SubContractData.Factory.newInstance();
        SubAward subaward=(SubAward) printableBusinessObject;
        this.awardNumber=(String) reportParameters.get("awardNumber");
        this.sponsorName=(String) reportParameters.get("sponsorName");
        this.cfdaNumber=(String) reportParameters.get("cfdaNumber");
        this.sponsorTemplates = (List<SubAwardForms>) reportParameters.get(SubAwardPrintingService.SELECTED_TEMPLATES);
        setSubcontractTemplateInfo(subContractData,subaward);
        setFundingSource(subContractData,subaward);
        setSubcontractDetail(subContractData,subaward);
        setSubcontractAmountInfo(subContractData,subaward);
        setAwardHeader(subContractData,subaward);
        setPrimeRecipientContacts(subContractData,subaward);
        setPrintRequirement(subContractData,subaward);
        setPrimeAdministrativeContact(subContractData,subaward);
        setPrimePrincipalInvestigator(subContractData,subaward);
        setPrimeFinancialContact(subContractData,subaward);
        setPrimeAuthorizedOfficial(subContractData,subaward);
        setAdministrativeContact(subContractData,subaward);
        setFinancialContact(subContractData,subaward);
        setAuthorizedOfficial(subContractData,subaward);
        setSubcontractReports(subContractData,subaward);
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
      
       Collection<ContactType> contact = (Collection<ContactType>) KraServiceLocator.getService(BusinessObjectService.class).findAll(ContactType.class);
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
                   subContractTemplateInfo.setExemptFromRprtgExecComp(subawardTemplate.getExemptFromRprtgExecComp());
                   subContractTemplateInfo.setSubRegisteredInCcr(subawardTemplate.getSubRegisteredInCcr());
               if(subawardTemplate.getPerfSiteDiffFromOrgAddr() != null){
                   subContractTemplateInfo.setPerfSiteDiffFromOrgAddr(subawardTemplate.getPerfSiteDiffFromOrgAddr());
               }
               if(subawardTemplate.getPerfSiteSameAsSubPiAddr() != null){
                   subContractTemplateInfo.setPerfSiteSameAsSubPiAddr(subawardTemplate.getPerfSiteSameAsSubPiAddr());
               }
               if(subawardTemplate.getSubProposalDate() != null){
                   subContractTemplateInfo.setSubProposalDate(getDateTimeService().getCalendar(subawardTemplate.getSubProposalDate()));
               }
                   
               if(subawardTemplate.getParentDunsNumber() != null){
                   subContractTemplateInfo.setParentDunsNumber(subawardTemplate.getParentDunsNumber());
               }
               if(subawardTemplate.getParentCongressionalDistrict() != null){
                   subContractTemplateInfo.setParentCongressionalDistrict(subawardTemplate.getParentCongressionalDistrict());
               }
               if(subawardTemplate.getCopyRightType() != null) {
                   subContractTemplateInfo.setCopyRights(subawardTemplate.getCopyRightType().toString());
               }
               if(subawardTemplate.getAutomaticCarryForward() != null) {
                   subContractTemplateInfo.setAutomaticCarryForward(subawardTemplate.getAutomaticCarryForward());
               }
               if(subawardTemplate.getTreatmentPrgmIncomeAdditive() != null) {
                   subContractTemplateInfo.setTreatmentPrgmIncomeAdditive(subawardTemplate.getTreatmentPrgmIncomeAdditive());
               }
               if(subawardTemplate.getApplicableProgramRegulations() != null) {
                   subContractTemplateInfo.setApplicableProgramRegulations(subawardTemplate.getApplicableProgramRegulations());
               }
               if(subawardTemplate.getApplicableProgramRegsDate() != null) {
                   subContractTemplateInfo.setApplicableProgramRegsDate(getDateTimeService().getCalendar(subawardTemplate.getApplicableProgramRegsDate()));
               }
               if(subawardTemplate.getCarryForwardRequestsSentTo() != null) {
                   ContactType contactTypeCode = (ContactType) KraServiceLocator.getService(BusinessObjectService.class).findBySinglePrimaryKey(ContactType.class,subawardTemplate.getCarryForwardRequestsSentTo());
                   if(contactTypeCode.getDescription() != null) {
                       subContractTemplateInfo.setCarryForwardRequestsSentToDescription(contactTypeCode.getDescription());
                   }
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
            RolodexDetailsType rolodexDetailsType = RolodexDetailsType.Factory.newInstance();
            OrganizationType organisation =OrganizationType.Factory.newInstance();
            if(subaward.getRolodex() != null ){
                subcontractDetail.setSiteInvestigator(subaward.getRolodex().getFullName());
                rolodexDetails.setRolodexName(subaward.getRolodex().getFullName());
                rolodexDetails.setAddress1(subaward.getRolodex().getAddressLine1());
                rolodexDetails.setAddress2(subaward.getRolodex().getAddressLine2());
                rolodexDetails.setCity(subaward.getRolodex().getCity());
                rolodexDetails.setStateDescription(subaward.getRolodex().getState());
                rolodexDetails.setPincode(subaward.getRolodex().getPostalCode());
                rolodexDetails.setPhoneNumber(subaward.getRolodex().getPhoneNumber());
                rolodexDetails.setFax(subaward.getRolodex().getFaxNumber());
                rolodexDetails.setEmail(subaward.getRolodex().getEmailAddress());
            }
                subcontractDetail.setPONumber(subaward.getPurchaseOrderNum());
            if(subaward.getOrganization() != null){
                subcontractDetail.setSubcontractorName(subaward.getOrganization().getOrganizationName());
                rolodexDetailsType.setAddress1(subaward.getOrganization().getAddress());
                rolodexDetailsType.setAddress2(subaward.getOrganization().getRolodex().getAddressLine2());
                rolodexDetailsType.setCity(subaward.getOrganization().getRolodex().getCity());
                rolodexDetailsType.setStateDescription(subaward.getOrganization().getRolodex().getState());
                rolodexDetailsType.setPincode(subaward.getOrganization().getRolodex().getPostalCode());
                organisation.setFedralEmployerId(subaward.getOrganization().getFederalEmployerId());
                organisation.setDunsNumber(subaward.getOrganization().getDunsNumber());
                organisation.setCongressionalDistrict(subaward.getOrganization().getCongressionalDistrict());
            }
            if(subaward.getStartDate() != null){
                subcontractDetail.setStartDate(getDateTimeService().getCalendar(subaward.getStartDate()));
            }
            if(subaward.getEndDate() != null){
                subcontractDetail.setEndDate(getDateTimeService().getCalendar(subaward.getEndDate()));
            }
            
            subcontractDetail.setSubcontractorOrgRolodexDetails(rolodexDetailsType);
            subcontractDetail.setSiteInvestigatorDetails(rolodexDetails);
            subcontractDetail.setSubcontractorDetails(organisation);
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
            UnitService unitService = KraServiceLocator.
                    getService(UnitService.class);
            if(subaward.getRequisitionerUnit() != null)
                {
                this.unitName = unitService.getUnitName(subaward.getRequisitionerUnit());
                organisation.setOrganizationName(unitName);
                }
            if(subaward.getOrganization().getRolodex() != null && unitName!=null ){
                rolodexDetails.setAddress1(subaward.getOrganization().getRolodex().getAddressLine1());
                rolodexDetails.setAddress2(subaward.getOrganization().getRolodex().getAddressLine2());
                rolodexDetails.setCity(subaward.getOrganization().getRolodex().getCity());
                rolodexDetails.setStateDescription(subaward.getOrganization().getRolodex().getState());
                rolodexDetails.setPincode(subaward.getOrganization().getRolodex().getPostalCode());
            }
            primeReceipient.setOrgRolodexDetails(rolodexDetails);
            primeReceipient.setRequisitionerOrgDetails(organisation);
            subContractData.setPrimeRecipientContacts(primeReceipient);
        }
        public void setPrintRequirement(SubContractData subContractData, SubAward subaward) {
            PrintRequirement printrequirement =PrintRequirement.Factory.newInstance();
            ConfigurationService configurationService = CoreApiServiceLocator.getKualiConfigurationService();
            String externalImageURL = Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY;
            String imagePath=configurationService.getPropertyValueAsString(externalImageURL);
            parameterService = KraServiceLocator.getService(ParameterService.class);
            
            printrequirement.setAttachment5Required("N");
            printrequirement.setAttachment3BRequired("N");
            printrequirement.setAttachment4Required("N");
            String subawardAttachment4 = parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_SUBAWARD, ParameterConstants.DOCUMENT_COMPONENT, Constants.PARAMETER_FDP_SUBAWARD_ATTACHMENT_4);
            String subawardAttachment3B = parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_SUBAWARD, ParameterConstants.DOCUMENT_COMPONENT, Constants.PARAMETER_FDP_SUBAWARD_ATTACHMENT_3B);
            
            for(SubAwardForms subAwardForms : sponsorTemplates){
                if(subAwardForms.getFormId().equals(subawardAttachment4)){
                    printrequirement.setAttachment4Required("Y");
                }
                if(subAwardForms.getFormId().equals(subawardAttachment3B)){
                    printrequirement.setAttachment3BRequired("Y");
                }
            }
            printrequirement.setImageCheckedPath(imagePath);
            printrequirement.setImageUncheckedPath(imagePath);
           subContractData.setPrintRequirement(printrequirement);
        }
        public void setPrimeAdministrativeContact(SubContractData subContractData, SubAward subaward) {
            PrimeAdministrativeContact primeAdministrativeContact= PrimeAdministrativeContact.Factory.newInstance();
            RolodexDetailsType rolodexdetails = RolodexDetailsType.Factory.newInstance();
            List<PrimeAdministrativeContact> rolodexDetailsList = new ArrayList<PrimeAdministrativeContact>();
            Map<String, String> administrativeMap = new HashMap<String, String>();
            parameterService = KraServiceLocator.getService(ParameterService.class);
            String administrativeContactCode = parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_SUBAWARD, ParameterConstants.DOCUMENT_COMPONENT, Constants.PARAMETER_FDP_PRIME_ADMINISTRATIVE_CONTACT_CODE);
            administrativeMap.put("contactTypeCode", administrativeContactCode);
            ContactUsage contactUsage =   businessObjectService.findByPrimaryKey(ContactUsage.class, administrativeMap);
            if(contactUsage.getModuleCode().equals(CoeusModule.SUBCONTRACTS_MODULE_CODE)){
              if(subaward.getSubAwardContactsList() != null && !subaward.getSubAwardContactsList().isEmpty()){
                  for(SubAwardContact subAwardContact : subaward.getSubAwardContactsList()){
                    if(subAwardContact.getContactTypeCode().equals(administrativeContactCode))
                    {
                        rolodexdetails.setRolodexName(subAwardContact.getRolodex().getOrganization());
                        rolodexdetails.setAddress1(subAwardContact.getRolodex().getAddressLine1());
                        rolodexdetails.setAddress2(subAwardContact.getRolodex().getAddressLine2());
                        rolodexdetails.setCity(subAwardContact.getRolodex().getCity());
                        rolodexdetails.setStateDescription(subAwardContact.getRolodex().getState());
                        rolodexdetails.setPincode(subAwardContact.getRolodex().getPostalCode());
                        rolodexdetails.setPhoneNumber(subAwardContact.getRolodex().getPhoneNumber());
                        rolodexdetails.setFax(subAwardContact.getRolodex().getFaxNumber());
                        rolodexdetails.setEmail(subAwardContact.getRolodex().getEmailAddress());
                    }
               }  
              }    
            }
            primeAdministrativeContact.setRolodexDetails(rolodexdetails);
            rolodexDetailsList.add(primeAdministrativeContact);
            subContractData.setPrimeAdministrativeContactArray((PrimeAdministrativeContact[])rolodexDetailsList.toArray(new PrimeAdministrativeContact[0]));
        }
        public void setPrimePrincipalInvestigator(SubContractData subContractData, SubAward subaward) {
            PersonDetailsType personDetails = PersonDetailsType.Factory.newInstance();
            List<PersonDetailsType> personDetailsList = new ArrayList<PersonDetailsType>();
            Map<String, String> awardMap = new HashMap<String, String>();
            awardMap.put("awardNumber", awardNumber);
            List<AwardPerson> awardList = (List<AwardPerson>) businessObjectService.findMatching(AwardPerson.class, awardMap);
            KcPerson awardPersons = KraServiceLocator.getService(KcPersonService.class).getKcPersonByPersonId(awardList.get(0).getPersonId());
            personDetails.setFullName(awardPersons.getFullName());
            personDetails.setAddressLine1(awardPersons.getAddressLine1());
            personDetails.setAddressLine2(awardPersons.getAddressLine2());
            personDetails.setCity(awardPersons.getCity());
            personDetails.setState(awardPersons.getState());
            personDetails.setPostalCode(awardPersons.getPostalCode());
            personDetails.setMobilePhoneNumber(awardPersons.getMobilePhoneNumber());
            personDetails.setFaxNumber(awardPersons.getFaxNumber());
            personDetails.setEmailAddress(awardPersons.getEmailAddress());
            personDetailsList.add(personDetails);
            subContractData.setPrimePrincipalInvestigatorArray((PersonDetailsType[])personDetailsList.toArray(new PersonDetailsType [0]));
        }
       
        public void setPrimeFinancialContact(SubContractData subContractData, SubAward subaward) {
            PrimeFinancialContact primeFinancialContact = PrimeFinancialContact.Factory.newInstance();
            RolodexDetailsType rolodexDetails = RolodexDetailsType.Factory.newInstance();
            List<PrimeFinancialContact> personDetailsList = new ArrayList<PrimeFinancialContact>();
            Map<String, String> administrativeMap = new HashMap<String, String>();
            parameterService = KraServiceLocator.getService(ParameterService.class);
            String administrativeFinancialCode = parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_SUBAWARD, ParameterConstants.DOCUMENT_COMPONENT, Constants.PARAMETER_FDP_PRIME_FINANCIAL_CONTACT_CODE);
            administrativeMap.put("contactTypeCode", administrativeFinancialCode);
            ContactUsage contactUsage =   businessObjectService.findByPrimaryKey(ContactUsage.class, administrativeMap);
            if(contactUsage.getModuleCode().equals(CoeusModule.SUBCONTRACTS_MODULE_CODE)){
              if(subaward.getSubAwardContactsList() != null && !subaward.getSubAwardContactsList().isEmpty()){
                 for(SubAwardContact subAwardContact : subaward.getSubAwardContactsList()){
                  if(subAwardContact.getContactTypeCode().equals(administrativeFinancialCode))
                      {
                      rolodexDetails.setRolodexName(subAwardContact.getRolodex().getOrganization());
                      rolodexDetails.setAddress1(subAwardContact.getRolodex().getAddressLine1());
                      rolodexDetails.setAddress2(subAwardContact.getRolodex().getAddressLine2());
                      rolodexDetails.setCity(subAwardContact.getRolodex().getCity());
                      rolodexDetails.setStateDescription(subAwardContact.getRolodex().getState());
                      rolodexDetails.setPincode(subAwardContact.getRolodex().getPostalCode());
                      rolodexDetails.setPhoneNumber(subAwardContact.getRolodex().getPhoneNumber());
                      rolodexDetails.setFax(subAwardContact.getRolodex().getFaxNumber());
                      rolodexDetails.setEmail(subAwardContact.getRolodex().getEmailAddress());
                      }
                 }
              }
            }
            primeFinancialContact.setRolodexDetails(rolodexDetails);
            personDetailsList.add(primeFinancialContact);
            subContractData.setPrimeFinancialContactArray((PrimeFinancialContact[])personDetailsList.toArray(new PrimeFinancialContact [0]));
        }
        public void setPrimeAuthorizedOfficial(SubContractData subContractData, SubAward subaward) {
            PrimeAuthorizedOfficial primeAuthorisedOfficial = PrimeAuthorizedOfficial.Factory.newInstance();
            RolodexDetailsType rolodexDetails = RolodexDetailsType.Factory.newInstance();
            List<PrimeAuthorizedOfficial> primeAuthorisedList = new ArrayList<PrimeAuthorizedOfficial>();
            Map<String, String> administrativeMap = new HashMap<String, String>();
            parameterService = KraServiceLocator.getService(ParameterService.class);
            String auhtorisedOfficialCode = parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_SUBAWARD, ParameterConstants.DOCUMENT_COMPONENT, Constants.PARAMETER_FDP_PRIME_AUTHORIZED_OFFICIAL_CODE);
            administrativeMap.put("contactTypeCode", auhtorisedOfficialCode);
            ContactUsage contactUsage =   businessObjectService.findByPrimaryKey(ContactUsage.class, administrativeMap);
            if(contactUsage.getModuleCode().equals(CoeusModule.SUBCONTRACTS_MODULE_CODE)){
              if(subaward.getSubAwardContactsList() != null && !subaward.getSubAwardContactsList().isEmpty()){
                  for(SubAwardContact subAwardContact : subaward.getSubAwardContactsList()){
                  if(subAwardContact.getContactTypeCode().equals(auhtorisedOfficialCode))
                      {
                      rolodexDetails.setRolodexName(subAwardContact.getRolodex().getOrganization());
                      rolodexDetails.setAddress1(subAwardContact.getRolodex().getAddressLine1());
                      rolodexDetails.setAddress2(subAwardContact.getRolodex().getAddressLine2());
                      rolodexDetails.setCity(subAwardContact.getRolodex().getCity());
                      rolodexDetails.setStateDescription(subAwardContact.getRolodex().getState());
                      rolodexDetails.setPincode(subAwardContact.getRolodex().getPostalCode());
                      rolodexDetails.setPhoneNumber(subAwardContact.getRolodex().getPhoneNumber());
                      rolodexDetails.setFax(subAwardContact.getRolodex().getFaxNumber());
                      rolodexDetails.setEmail(subAwardContact.getRolodex().getEmailAddress());
                      }
              }}
            }
            primeAuthorisedOfficial.setRolodexDetails(rolodexDetails);
            primeAuthorisedList.add(primeAuthorisedOfficial);
            subContractData.setPrimeAuthorizedOfficialArray((PrimeAuthorizedOfficial[])primeAuthorisedList.toArray(new PrimeAuthorizedOfficial [0]));
        }
            public void setAdministrativeContact(SubContractData subContractData, SubAward subaward) {
                AdministrativeContact administrativeContact = AdministrativeContact.Factory.newInstance();
                RolodexDetailsType rolodexDetails = RolodexDetailsType.Factory.newInstance();
                List<AdministrativeContact> administrativeContactList = new ArrayList<AdministrativeContact>();
                Map<String, String> administrativeMap = new HashMap<String, String>();
                parameterService = KraServiceLocator.getService(ParameterService.class);
                String administrativeCode = parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_SUBAWARD, ParameterConstants.DOCUMENT_COMPONENT, Constants.PARAMETER_FDP_SUB_ADMINISTRATIVE_CONTACT_CODE);
                administrativeMap.put("contactTypeCode", administrativeCode);
                ContactUsage contactUsage =   businessObjectService.findByPrimaryKey(ContactUsage.class, administrativeMap);
                if(contactUsage.getModuleCode().equals(CoeusModule.SUBCONTRACTS_MODULE_CODE)){
                  if(subaward.getSubAwardContactsList() != null && !subaward.getSubAwardContactsList().isEmpty()){
                      for(SubAwardContact subAwardContact : subaward.getSubAwardContactsList()){
                      if(subAwardContact.getContactTypeCode().equals(administrativeCode))
                          {
                          rolodexDetails.setRolodexName(subAwardContact.getRolodex().getOrganization());
                          rolodexDetails.setAddress1(subAwardContact.getRolodex().getAddressLine1());
                          rolodexDetails.setAddress2(subAwardContact.getRolodex().getAddressLine2());
                          rolodexDetails.setCity(subAwardContact.getRolodex().getCity());
                          rolodexDetails.setStateDescription(subAwardContact.getRolodex().getState());
                          rolodexDetails.setPincode(subAwardContact.getRolodex().getPostalCode());
                          rolodexDetails.setPhoneNumber(subAwardContact.getRolodex().getPhoneNumber());
                          rolodexDetails.setFax(subAwardContact.getRolodex().getFaxNumber());
                          rolodexDetails.setEmail(subAwardContact.getRolodex().getEmailAddress());
                          }
                  }}
                }
                administrativeContact.setRolodexDetails(rolodexDetails);
                administrativeContactList.add(administrativeContact);
                subContractData.setAdministrativeContactArray((AdministrativeContact[])administrativeContactList.toArray(new AdministrativeContact [0]));
            }
            public void setFinancialContact(SubContractData subContractData, SubAward subaward) {
                FinancialContact financialContact =FinancialContact.Factory.newInstance();
                RolodexDetailsType rolodexDetails = RolodexDetailsType.Factory.newInstance();
                List<FinancialContact> financialContactList = new ArrayList<FinancialContact>();
                Map<String, String> administrativeMap = new HashMap<String, String>();
                parameterService = KraServiceLocator.getService(ParameterService.class);
                String financialContactCode = parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_SUBAWARD, ParameterConstants.DOCUMENT_COMPONENT, Constants.PARAMETER_FDP_SUB_FINANCIAL_CONTACT_CODE);
                administrativeMap.put("contactTypeCode", financialContactCode);
                ContactUsage contactUsage =   businessObjectService.findByPrimaryKey(ContactUsage.class, administrativeMap);
                if(contactUsage.getModuleCode().equals(CoeusModule.SUBCONTRACTS_MODULE_CODE)){
                  if(subaward.getSubAwardContactsList() != null && !subaward.getSubAwardContactsList().isEmpty()){
                      for(SubAwardContact subAwardContact : subaward.getSubAwardContactsList()){
                      if(subAwardContact.getContactTypeCode().equals(financialContactCode))
                          {
                          rolodexDetails.setRolodexName(subAwardContact.getRolodex().getOrganization());
                          rolodexDetails.setAddress1(subAwardContact.getRolodex().getAddressLine1());
                          rolodexDetails.setAddress2(subAwardContact.getRolodex().getAddressLine2());
                          rolodexDetails.setCity(subAwardContact.getRolodex().getCity());
                          rolodexDetails.setStateDescription(subAwardContact.getRolodex().getState());
                          rolodexDetails.setPincode(subAwardContact.getRolodex().getPostalCode());
                          rolodexDetails.setPhoneNumber(subAwardContact.getRolodex().getPhoneNumber());
                          rolodexDetails.setFax(subAwardContact.getRolodex().getFaxNumber());
                          rolodexDetails.setEmail(subAwardContact.getRolodex().getEmailAddress());
                          }
                  }}
                }
                financialContact.setRolodexDetails(rolodexDetails);
                financialContactList.add(financialContact);
                subContractData.setFinancialContactArray((FinancialContact[])financialContactList.toArray(new FinancialContact [0]));
            }
            public void setAuthorizedOfficial(SubContractData subContractData, SubAward subaward) {
                AuthorizedOfficial authorizedOfficial =AuthorizedOfficial.Factory.newInstance();
                RolodexDetailsType rolodexDetails = RolodexDetailsType.Factory.newInstance();
                List<AuthorizedOfficial> authorizedOfficialList = new ArrayList<AuthorizedOfficial>();
                Map<String, String> administrativeMap = new HashMap<String, String>();
                parameterService = KraServiceLocator.getService(ParameterService.class);
                String financialContactCode = parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_SUBAWARD, ParameterConstants.DOCUMENT_COMPONENT, Constants.PARAMETER_FDP_SUB_AUTHORIZED_CONTACT_CODE);
                administrativeMap.put("contactTypeCode", financialContactCode);
                ContactUsage contactUsage =   businessObjectService.findByPrimaryKey(ContactUsage.class, administrativeMap);
                if(contactUsage.getModuleCode().equals(CoeusModule.SUBCONTRACTS_MODULE_CODE)){
                  if(subaward.getSubAwardContactsList() != null && !subaward.getSubAwardContactsList().isEmpty()){
                      for(SubAwardContact subAwardContact : subaward.getSubAwardContactsList()){
                      if(subAwardContact.getContactTypeCode().equals(financialContactCode))
                          {
                          rolodexDetails.setRolodexName(subAwardContact.getRolodex().getOrganization());
                          rolodexDetails.setAddress1(subAwardContact.getRolodex().getAddressLine1());
                          rolodexDetails.setAddress2(subAwardContact.getRolodex().getAddressLine2());
                          rolodexDetails.setCity(subAwardContact.getRolodex().getCity());
                          rolodexDetails.setStateDescription(subAwardContact.getRolodex().getState());
                          rolodexDetails.setPincode(subAwardContact.getRolodex().getPostalCode());
                          rolodexDetails.setPhoneNumber(subAwardContact.getRolodex().getPhoneNumber());
                          rolodexDetails.setFax(subAwardContact.getRolodex().getFaxNumber());
                          rolodexDetails.setEmail(subAwardContact.getRolodex().getEmailAddress());
                          }
                  }}
                }
                authorizedOfficial.setRolodexDetails(rolodexDetails);
                authorizedOfficialList.add(authorizedOfficial);
                subContractData.setAuthorizedOfficialArray((AuthorizedOfficial[])authorizedOfficialList.toArray(new AuthorizedOfficial [0]));
            }
            public void setSubcontractReports(SubContractData subContractData, SubAward subaward) {
                List<SubcontractReports> subContractReportsList = new ArrayList<SubcontractReports>();
                Map<String, String> reportTypeCode = new HashMap<String, String>();
                SubAwardReportType subAwardReportsType = null;
                if(subaward.getSubAwardReportList() != null && !subaward.getSubAwardReportList().isEmpty()){
                for(SubAwardReports subAwardReports : subaward.getSubAwardReportList()){
                    SubcontractReports subContractReports = SubcontractReports.Factory.newInstance();
                    reportTypeCode.put("subAwardReportTypeCode", subAwardReports.getSubAwardReportTypeCode());
                    subAwardReportsType = businessObjectService.findByPrimaryKey(SubAwardReportType.class, reportTypeCode);
                subContractReports.setReportTypeDescription(subAwardReportsType.getDescription());
                subContractReportsList.add(subContractReports);
                }
               }
                subContractData.setSubcontractReportsArray((SubcontractReports[])subContractReportsList.toArray(new SubcontractReports [0]));
            }
            
            
}
