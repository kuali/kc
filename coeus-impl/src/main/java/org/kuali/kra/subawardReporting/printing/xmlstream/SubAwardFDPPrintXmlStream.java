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
package org.kuali.kra.subawardReporting.printing.xmlstream;

import java.math.BigDecimal;
import java.util.*;

import java.util.Optional;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.print.stream.xml.XmlStream;
import org.kuali.coeus.common.framework.print.util.PrintingUtils;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.ContactType;
import org.kuali.kra.subaward.bo.*;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.home.ContactUsage;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.subaward.reporting.printing.SubAwardPrintType;
import org.kuali.kra.subaward.reporting.printing.service.SubAwardPrintingService;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.location.api.state.State;

import org.kuali.kra.award.printing.schema.AwardHeaderType;
import org.kuali.kra.award.printing.schema.AwardType;
import org.kuali.kra.award.printing.schema.AwardType.AwardDetails;
import org.kuali.kra.award.printing.schema.AwardType.AwardDetails.OtherHeaderDetails;
import org.kuali.kra.subaward.printing.schema.*;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.AdministrativeContact;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.AuthorizedOfficial;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.FinancialContact;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.FundingSource;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.PrimeAdministrativeContact;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.PrimeAuthorizedOfficial;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.PrimeFinancialContact;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.PrimeRecipientContacts;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.PrintRequirement;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.SubcontractAmountInfo;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.SubcontractDetail;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.SubcontractReports;
import org.kuali.kra.subaward.printing.schema.SubContractDataDocument.SubContractData.SubcontractTemplateInfo;

public class SubAwardFDPPrintXmlStream implements XmlStream  {
    private static final String ORGANIZATION_ID = "organizationId";
    private static final String FDP_ORG_FROM_REQUISITIONER_UNIT = "FDP_ORG_FROM_REQUISITIONER_UNIT";

    private BusinessObjectService businessObjectService;

    private String awardNumber;
    private String awardTitle;
    private String sponsorAwardNumber;
    private String sponsorName;
    private String cfdaNumber;
    private String fain;
    private String modificationType;
    private Boolean fcoi;
    private Boolean ffata;
    private Boolean randd;
    private Boolean costshare;
    private String primeSponsorName;
    private Calendar noticeDate;
    private BigDecimal obligatedTotal;
    private BigDecimal anticipatedTotal;
    private List<SubAwardForms> sponsorTemplates;
    private ParameterService parameterService;

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, XmlObject> generateXmlStream(KcPersistableBusinessObjectBase printableBusinessObject,
            Map<String, Object> reportParameters) {

        this.awardNumber=(String) reportParameters.get("awardNumber");
        this.awardTitle=(String) reportParameters.get("awardTitle");
        this.sponsorAwardNumber=(String) reportParameters.get("sponsorAwardNumber");
        this.sponsorName=(String) reportParameters.get("sponsorName");
        this.cfdaNumber=(String) reportParameters.get("cfdaNumber");
        this.sponsorTemplates = (List<SubAwardForms>) reportParameters.get(SubAwardPrintingService.SELECTED_TEMPLATES);
        this.modificationType = (String) reportParameters.get("modificationType");
        this.fcoi = (Boolean) reportParameters.get("fcoi");
        this.fain = (String) reportParameters.get("fain");
        this.ffata = (Boolean) reportParameters.get("ffata");
        this.randd = (Boolean) reportParameters.get("randd");
        this.costshare = (Boolean) reportParameters.get("costshare");
        this.primeSponsorName = (String) reportParameters.get("primeSponsorName");
        this.noticeDate = (Calendar) reportParameters.get("noticeDate");
        this.obligatedTotal = (BigDecimal) reportParameters.get("obligatedTotal");
        this.anticipatedTotal = (BigDecimal) reportParameters.get("anticipatedTotal");
        final SubAward subaward=(SubAward) printableBusinessObject;
        final SubContractData subContractData = SubContractData.Factory.newInstance();


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

        final SubContractDataDocument subContractDataDoc = SubContractDataDocument.Factory.newInstance();
        subContractDataDoc.setSubContractData(subContractData);


        final Map<String, XmlObject> xmlObjectList = new LinkedHashMap<>();
        xmlObjectList.put(SubAwardPrintType.SUB_AWARD_FDP_TEMPLATE.getSubAwardPrintType(), subContractDataDoc);
        return xmlObjectList;
    }

   public void setSubcontractTemplateInfo(SubContractData subContractData, SubAward subaward) {
       SubcontractTemplateInfo subContractTemplateInfo = SubcontractTemplateInfo.Factory.newInstance();
       List<SubcontractTemplateInfo> templateDataList = new ArrayList<>();

       SubAwardTemplateInfo subawardTemplate;
       if(subaward.getSubAwardTemplateInfo() != null && !subaward.getSubAwardTemplateInfo().isEmpty() ){
           subawardTemplate =subaward.getSubAwardTemplateInfo().get(0);
      
       Collection<ContactType> contact = KcServiceLocator.getService(BusinessObjectService.class).findAll(ContactType.class);
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
                   ContactType contactTypeCode = KcServiceLocator.getService(BusinessObjectService.class).findBySinglePrimaryKey(ContactType.class,subawardTemplate.getCarryForwardRequestsSentTo());
                   if(contactTypeCode.getDescription() != null) {
                       subContractTemplateInfo.setCarryForwardRequestsSentToDescription(contactTypeCode.getDescription());
                   }
                   
               }
                   
           templateDataList.add(subContractTemplateInfo);
       }
           subContractData.setSubcontractTemplateInfoArray(templateDataList.toArray(new SubcontractTemplateInfo[0]));
    }
        public void setFundingSource(SubContractData subContractData, SubAward subaward) {
            FundingSource fundingSource = FundingSource.Factory.newInstance();
            List<FundingSource> fundingSourceList = new ArrayList<>();
            fundingSource.setAwardNumber(awardNumber);
            fundingSource.setSponsor(sponsorName);
            fundingSourceList.add(fundingSource);
            subContractData.setFundingSourceArray(fundingSourceList.toArray(new FundingSource[0]));
        }
        
        public void setSubcontractDetail(SubContractData subContractData, SubAward subaward) {
            SubcontractDetail subcontractDetail = SubcontractDetail.Factory.newInstance();
            RolodexDetailsType rolodexDetails = RolodexDetailsType.Factory.newInstance();
            RolodexDetailsType rolodexDetailsType = RolodexDetailsType.Factory.newInstance();
            OrganizationType organisation =OrganizationType.Factory.newInstance();
            if(subaward.getRolodex() != null ){
                subcontractDetail.setSiteInvestigator(subaward.getRolodex().getFullName());
                if(subaward.getRolodex().getFullName() != null && subaward.getRolodex().getFullName().length() > 0){
                    rolodexDetails.setRolodexName(subaward.getRolodex().getFullName());
                } else{
                    rolodexDetails.setRolodexName(subaward.getRolodex().getOrganization());
                }
                rolodexDetails.setAddress1(subaward.getRolodex().getAddressLine1());
                rolodexDetails.setAddress2(subaward.getRolodex().getAddressLine2());
                rolodexDetails.setAddress3(subaward.getRolodex().getAddressLine3());
                rolodexDetails.setCity(subaward.getRolodex().getCity());
                String countryCode = subaward.getRolodex().getCountryCode();
                String stateName = subaward.getRolodex().getState();
                if(countryCode != null && countryCode.length() > 0 && stateName != null && stateName.length() > 0){
                    State state = KcServiceLocator.getService(PrintingUtils.class).getStateFromName(countryCode, stateName);
                    if(state != null){
                        rolodexDetails.setStateDescription(state.getName());
                    }
                }
                rolodexDetails.setPincode(subaward.getRolodex().getPostalCode());
                rolodexDetails.setPhoneNumber(subaward.getRolodex().getPhoneNumber());
                rolodexDetails.setFax(subaward.getRolodex().getFaxNumber());
                rolodexDetails.setEmail(subaward.getRolodex().getEmailAddress());
            }
                subcontractDetail.setPONumber(subaward.getPurchaseOrderNum());
                subcontractDetail.setSubContractCode(subaward.getSubAwardCode());
            if(subaward.getOrganization() != null){
                subcontractDetail.setSubcontractorName(subaward.getOrganization().getOrganizationName());
                rolodexDetailsType.setAddress1(subaward.getOrganization().getRolodex().getAddressLine1());
                rolodexDetailsType.setAddress2(subaward.getOrganization().getRolodex().getAddressLine2());
                rolodexDetailsType.setAddress3(subaward.getOrganization().getRolodex().getAddressLine3());
                rolodexDetailsType.setCity(subaward.getOrganization().getRolodex().getCity());
                String countryCode = subaward.getOrganization().getRolodex().getCountryCode();
                String stateName = subaward.getOrganization().getRolodex().getState();
                if(countryCode != null && countryCode.length() > 0 && stateName != null && stateName.length() > 0){
                    State state = KcServiceLocator.getService(PrintingUtils.class).getStateFromName(countryCode, stateName);
                    if(state != null){
                        rolodexDetailsType.setStateDescription(state.getName());
                    }
                }
                rolodexDetailsType.setPincode(subaward.getOrganization().getRolodex().getPostalCode());
                organisation.setFedralEmployerId(subaward.getOrganization().getFederalEmployerId());
                organisation.setDunsNumber(subaward.getOrganization().getDunsNumber());
                organisation.setCongressionalDistrict(subaward.getOrganization().getCongressionalDistrict());
            }
            if(subaward.getStartDate() != null){
                subcontractDetail.setStartDate(getDateTimeService().getCalendar(subaward.getStartDate()));
            }
            Optional<Date>optionalPerformanceDate=   getOptionalPerformanceDate(subaward);
            if(subaward.getAllSubAwardAmountInfos() != null && !subaward.getAllSubAwardAmountInfos().isEmpty()){
			if (optionalPerformanceDate.isPresent()) {
				subcontractDetail.setEndDate(getDateTimeService().getCalendar(optionalPerformanceDate.get()));
			}
            }
            
            subcontractDetail.setSubcontractorOrgRolodexDetails(rolodexDetailsType);
            subcontractDetail.setSiteInvestigatorDetails(rolodexDetails);
            subcontractDetail.setSubcontractorDetails(organisation);

            if (!subaward.getAllSubAwardAmountInfos().isEmpty()) {
                subcontractDetail.setComments(subaward.getAllSubAwardAmountInfos()
                        .get(subaward.getAllSubAwardAmountInfos().size() - 1)
                        .getComments());
            }
            if(subaward.getAllSubAwardAmountInfos() != null && !subaward.getAllSubAwardAmountInfos().isEmpty()){
                subcontractDetail.setModificationType(subaward.getAllSubAwardAmountInfos()
                        .get(subaward.getAllSubAwardAmountInfos().size() - 1).getModificationTypeCode());

            }

            if (fcoi != null) {
                subcontractDetail.setPHSFCOI(toFlag(fcoi));
            }

            if (ffata != null) {
                subcontractDetail.setFFATA(toFlag(ffata));
            }

            if (randd != null) {
                subcontractDetail.setRANDD(toFlag(randd));
            }

            if (costshare != null) {
                subcontractDetail.setCOSTSHARE(toFlag(costshare));
            }

            subContractData.setSubcontractDetail(subcontractDetail);
        }
        private Optional<Date> getOptionalPerformanceDate(SubAward subaward) {
            Optional<Date>optionalPerformanceDate=   Optional.ofNullable(subaward.getAllSubAwardAmountInfos()
                    .get(subaward.getAllSubAwardAmountInfos().size() - 1).getPeriodofPerformanceEndDate());
            return optionalPerformanceDate;
        }

        private String toFlag(Boolean b) {
            return Boolean.TRUE.equals(b) ? "Y" : "N";
        }
        public void setSubcontractAmountInfo(SubContractData subContractData, SubAward subaward) {
           
            SubcontractAmountInfo subContractAmountInfo = SubcontractAmountInfo.Factory.newInstance();
            List<SubcontractAmountInfo> amountinfoList = new ArrayList<>();
            if(subaward.getAllSubAwardAmountInfos() != null && !subaward.getAllSubAwardAmountInfos().isEmpty()){
                subContractAmountInfo.setObligatedAmount(subaward.getTotalObligatedAmount().bigDecimalValue());
                subContractAmountInfo.setAnticipatedAmount(subaward.getTotalAnticipatedAmount().bigDecimalValue());
            }
            if(subaward.getAllSubAwardAmountInfos() != null && !subaward.getAllSubAwardAmountInfos().isEmpty()){
                SubAwardAmountInfo amountInfo = subaward.getAllSubAwardAmountInfos().get(subaward.getAllSubAwardAmountInfos().size() - 1);
                subContractAmountInfo.setObligatedChange(amountInfo.getObligatedChange().bigDecimalValue());
                subContractAmountInfo.setAnticipatedChange(amountInfo.getAnticipatedChange().bigDecimalValue());

                if(amountInfo.getPeriodofPerformanceStartDate() != null){
                    subContractAmountInfo.setPerformanceStartDate(getDateTimeService().getCalendar(subaward.getStartDate()));
                }
                Optional<Date>optionalPerformanceDate=   getOptionalPerformanceDate(subaward);

                if(subaward.getAllSubAwardAmountInfos() != null && !subaward.getAllSubAwardAmountInfos().isEmpty()){
                if(optionalPerformanceDate.isPresent()){
                    subContractAmountInfo.setPerformanceEndDate(getDateTimeService().getCalendar(optionalPerformanceDate.get()));
                }
                }
                if(amountInfo.getModificationEffectiveDate() != null){
                    subContractAmountInfo.setModificationEffectiveDate(getDateTimeService().getCalendar(amountInfo.getModificationEffectiveDate()));
                }
                if (amountInfo.getModificationID() != null) {
                    subContractAmountInfo.setModificationNumber(amountInfo.getModificationID());
                }
            }

            amountinfoList.add(subContractAmountInfo);
            subContractData.setSubcontractAmountInfoArray(amountinfoList.toArray(new SubcontractAmountInfo[0]));
        }
        public void setAwardHeader(SubContractData subContractData, SubAward subaward) {
            AwardType awardType= AwardType.Factory.newInstance();
            AwardHeaderType awardHeaderType =AwardHeaderType.Factory.newInstance();
            AwardDetails awardDetails= AwardDetails.Factory.newInstance();
            OtherHeaderDetails otherDetails = OtherHeaderDetails.Factory.newInstance();
            List<AwardType> awardTypeList = new ArrayList<>();

            if (StringUtils.isNotBlank(sponsorAwardNumber)) {
                awardHeaderType.setSponsorAwardNumber(sponsorAwardNumber);
            }

            if (StringUtils.isNotBlank(sponsorName)) {
                awardHeaderType.setSponsorDescription(sponsorName);
            }
            if(StringUtils.isNotBlank(awardTitle)) {
                awardHeaderType.setTitle(awardTitle);
            }

            if(StringUtils.isNotBlank(cfdaNumber)) {
                otherDetails.setCFDANumber(cfdaNumber);
            }

            if(StringUtils.isNotBlank(primeSponsorName)) {
                otherDetails.setPrimeSponsorDescription(primeSponsorName);
            }

            if(noticeDate != null) {
                otherDetails.setLastUpdate(noticeDate);
            }

            if(obligatedTotal != null) {
                otherDetails.setObligatedAmt(obligatedTotal);
            }

            if(anticipatedTotal != null) {
                otherDetails.setAnticipatedAmt(anticipatedTotal);
            }

            if (StringUtils.isNotEmpty(fain)) {
                otherDetails.setFAIN(fain);
            }

            awardDetails.setAwardHeader(awardHeaderType);
            awardDetails.setOtherHeaderDetails(otherDetails);
            awardType.setAwardDetails(awardDetails);
            awardTypeList.add(awardType);
            subContractData.setAwardArray(awardTypeList.toArray(new AwardType[0]));
        }
        public void setPrimeRecipientContacts(SubContractData subContractData, SubAward subaward) {
            PrimeRecipientContacts primeReceipient = PrimeRecipientContacts.Factory.newInstance();
            OrganizationType organisation = OrganizationType.Factory.newInstance();
            RolodexDetailsType rolodexDetails = RolodexDetailsType.Factory.newInstance();

            final Organization univOrganisation = businessObjectService.findByPrimaryKey(Organization.class, Collections.singletonMap(ORGANIZATION_ID, "000001"));
            if (univOrganisation != null) {
                organisation.setOrganizationName(univOrganisation.getOrganizationName());
            }


            boolean requisitionerOrg = parameterService.getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_SUBAWARD, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, FDP_ORG_FROM_REQUISITIONER_UNIT);
            final Organization reqOrganisation = requisitionerOrg && subaward.getUnit() != null ? subaward.getUnit().getOrganization() : null;
            final Rolodex rolodex = requisitionerOrg && reqOrganisation != null && reqOrganisation.getRolodex() != null ? reqOrganisation.getRolodex() : univOrganisation != null ? univOrganisation.getRolodex() : null;

            if (rolodex != null) {
                rolodexDetails.setAddress1(rolodex.getAddressLine1());
                rolodexDetails.setAddress2(rolodex.getAddressLine2());
                rolodexDetails.setAddress3(rolodex.getAddressLine3());
                rolodexDetails.setCity(rolodex.getCity());
                String countryCode = rolodex.getCountryCode();
                String stateName = rolodex.getState();
                if (countryCode != null && countryCode.length() > 0 && stateName != null && stateName.length() > 0) {
                    State state = KcServiceLocator.getService(PrintingUtils.class).getStateFromName(countryCode, stateName);
                    if (state != null) {
                        rolodexDetails.setStateDescription(state.getName());
                    }
                }
                rolodexDetails.setPincode(rolodex.getPostalCode());
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
            parameterService = KcServiceLocator.getService(ParameterService.class);
            
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
            List<PrimeAdministrativeContact> rolodexDetailsList = new ArrayList<>();
            Map<String, String> administrativeMap = new HashMap<>();
            Map<String, Integer> rolodexId = new HashMap<>();
            parameterService = KcServiceLocator.getService(ParameterService.class);
            String administrativeContactCode = parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_SUBAWARD, ParameterConstants.DOCUMENT_COMPONENT, Constants.PARAMETER_FDP_PRIME_ADMINISTRATIVE_CONTACT_CODE);
            administrativeMap.put("contactTypeCode", administrativeContactCode);
            ContactUsage contactUsage =   businessObjectService.findByPrimaryKey(ContactUsage.class, administrativeMap);
            if(contactUsage.getModuleCode().equals(CoeusModule.SUBCONTRACTS_MODULE_CODE)){
              if(subaward.getSubAwardContactsList() != null && !subaward.getSubAwardContactsList().isEmpty()){
                  for(SubAwardContact subAwardContact : subaward.getSubAwardContactsList()){
                    if(subAwardContact.getContactTypeCode().equals(administrativeContactCode)) {
                        rolodexId.put("rolodexId", subAwardContact.getRolodex().getRolodexId());
                        Rolodex rolodex = businessObjectService.findByPrimaryKey(Rolodex.class, rolodexId); 
                        if( rolodex.getFullName() == null ) {
                            rolodexdetails.setRolodexName(rolodex.getOrganization());
                        }
                        else {
                            rolodexdetails.setRolodexName(rolodex.getFullName());
                        }
                        rolodexdetails.setAddress1(rolodex.getAddressLine1());
                        rolodexdetails.setAddress2(rolodex.getAddressLine2());
                        rolodexdetails.setAddress3(rolodex.getAddressLine3());
                        rolodexdetails.setCity(rolodex.getCity());
                        String countryCode = rolodex.getCountryCode();
                        String stateName = rolodex.getState();
                        if(countryCode != null && countryCode.length() > 0 && stateName != null && stateName.length() > 0){
                            State state = KcServiceLocator.getService(PrintingUtils.class).getStateFromName(countryCode, stateName);
                            if(state != null){
                                rolodexdetails.setStateDescription(state.getName());
                            }
                        }
                        rolodexdetails.setPincode(rolodex.getPostalCode());
                        rolodexdetails.setPhoneNumber(rolodex.getPhoneNumber());
                        rolodexdetails.setFax(rolodex.getFaxNumber());
                        rolodexdetails.setEmail(rolodex.getEmailAddress());
                 }
               }  
              }    
            }
            primeAdministrativeContact.setRolodexDetails(rolodexdetails);
            rolodexDetailsList.add(primeAdministrativeContact);
            subContractData.setPrimeAdministrativeContactArray(rolodexDetailsList.toArray(new PrimeAdministrativeContact[0]));
        }
        public void setPrimePrincipalInvestigator(SubContractData subContractData, SubAward subaward) {
            PersonDetailsType personDetails = PersonDetailsType.Factory.newInstance();
            List<PersonDetailsType> personDetailsList = new ArrayList<>();
            Map<String, String> awardNum = new HashMap<>();
            if(awardNumber != null){
                awardNum.put("awardNumber",awardNumber);
                awardNum.put("roleCode", "PI");
                List<AwardPerson> awardNumList = (List<AwardPerson>) businessObjectService.findMatchingOrderBy(AwardPerson.class, awardNum, "sequenceNumber", true);
                if (CollectionUtils.isNotEmpty(awardNumList)) {
                    AwardPerson awardPerson = awardNumList.get(awardNumList.size() - 1);
                    KcPerson awardPersons = KcServiceLocator.getService(KcPersonService.class).getKcPersonByPersonId(awardPerson.getPersonId());

                    personDetails.setFullName(awardPersons.getFullName());
                    personDetails.setAddressLine1(awardPersons.getAddressLine1());
                    personDetails.setAddressLine2(awardPersons.getAddressLine2());
                    personDetails.setAddressLine3(awardPersons.getAddressLine3());
                    personDetails.setCity(awardPersons.getCity());
                    String countryCode = awardPersons.getCountryCode();
                    String stateName = awardPersons.getState();
                    if (countryCode != null && countryCode.length() > 0 && stateName != null && stateName.length() > 0) {
                        State state = KcServiceLocator.getService(PrintingUtils.class).getStateFromName(countryCode, stateName);
                        if (state != null) {
                            personDetails.setState(state.getName());
                        }
                    }
                    personDetails.setPostalCode(awardPersons.getPostalCode());
                    personDetails.setMobilePhoneNumber(awardPersons.getOfficePhone());
                    personDetails.setFaxNumber(awardPersons.getFaxNumber());
                    personDetails.setEmailAddress(awardPersons.getEmailAddress());
                }
            }
            personDetailsList.add(personDetails);
            subContractData.setPrimePrincipalInvestigatorArray(personDetailsList.toArray(new PersonDetailsType [0]));
        }
       
        public void setPrimeFinancialContact(SubContractData subContractData, SubAward subaward) {
            PrimeFinancialContact primeFinancialContact = PrimeFinancialContact.Factory.newInstance();
            RolodexDetailsType rolodexDetails = RolodexDetailsType.Factory.newInstance();
            List<PrimeFinancialContact> personDetailsList = new ArrayList<>();
            Map<String, String> administrativeMap = new HashMap<>();
            Map<String, Integer> rolodexId = new HashMap<>();
            parameterService = KcServiceLocator.getService(ParameterService.class);
            String administrativeFinancialCode = parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_SUBAWARD, ParameterConstants.DOCUMENT_COMPONENT, Constants.PARAMETER_FDP_PRIME_FINANCIAL_CONTACT_CODE);
            administrativeMap.put("contactTypeCode", administrativeFinancialCode);
            ContactUsage contactUsage =   businessObjectService.findByPrimaryKey(ContactUsage.class, administrativeMap);
            if(contactUsage.getModuleCode().equals(CoeusModule.SUBCONTRACTS_MODULE_CODE)){
              if(subaward.getSubAwardContactsList() != null && !subaward.getSubAwardContactsList().isEmpty()){
                 for(SubAwardContact subAwardContact : subaward.getSubAwardContactsList()){
                  if(subAwardContact.getContactTypeCode().equals(administrativeFinancialCode)) {
                      rolodexId.put("rolodexId", subAwardContact.getRolodex().getRolodexId());
                      Rolodex rolodex = businessObjectService.findByPrimaryKey(Rolodex.class, rolodexId);
                        if( rolodex.getFullName() == null) {
                          rolodexDetails.setRolodexName(rolodex.getOrganization());
                        }
                        else {
                          rolodexDetails.setRolodexName(rolodex.getFullName());
                        }
                        rolodexDetails.setAddress1(rolodex.getAddressLine1());
                        rolodexDetails.setAddress2(rolodex.getAddressLine2());
                        rolodexDetails.setAddress3(rolodex.getAddressLine3());
                        rolodexDetails.setCity(rolodex.getCity());
                        String countryCode = rolodex.getCountryCode();
                        String stateName = rolodex.getState();
                        if(countryCode != null && countryCode.length() > 0 && stateName != null && stateName.length() > 0){
                            State state = KcServiceLocator.getService(PrintingUtils.class).getStateFromName(countryCode, stateName);
                            if(state != null){
                                rolodexDetails.setStateDescription(state.getName());
                            }
                        }
                        rolodexDetails.setPincode(rolodex.getPostalCode());
                        rolodexDetails.setPhoneNumber(rolodex.getPhoneNumber());
                        rolodexDetails.setFax(rolodex.getFaxNumber());
                        rolodexDetails.setEmail(rolodex.getEmailAddress());
                   }
                 }
              }
            }
            primeFinancialContact.setRolodexDetails(rolodexDetails);
            personDetailsList.add(primeFinancialContact);
            subContractData.setPrimeFinancialContactArray(personDetailsList.toArray(new PrimeFinancialContact [0]));
        }
        public void setPrimeAuthorizedOfficial(SubContractData subContractData, SubAward subaward) {
            PrimeAuthorizedOfficial primeAuthorisedOfficial = PrimeAuthorizedOfficial.Factory.newInstance();
            RolodexDetailsType rolodexDetails = RolodexDetailsType.Factory.newInstance();
            List<PrimeAuthorizedOfficial> primeAuthorisedList = new ArrayList<>();
            Map<String, String> administrativeMap = new HashMap<>();
            Map<String, Integer> rolodexId = new HashMap<>();
            parameterService = KcServiceLocator.getService(ParameterService.class);
            String auhtorisedOfficialCode = parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_SUBAWARD, ParameterConstants.DOCUMENT_COMPONENT, Constants.PARAMETER_FDP_PRIME_AUTHORIZED_OFFICIAL_CODE);
            administrativeMap.put("contactTypeCode", auhtorisedOfficialCode);
            ContactUsage contactUsage =   businessObjectService.findByPrimaryKey(ContactUsage.class, administrativeMap);
            if(contactUsage.getModuleCode().equals(CoeusModule.SUBCONTRACTS_MODULE_CODE)) {
              if(subaward.getSubAwardContactsList() != null && !subaward.getSubAwardContactsList().isEmpty()){
                  for(SubAwardContact subAwardContact : subaward.getSubAwardContactsList()){
                      if(subAwardContact.getContactTypeCode().equals(auhtorisedOfficialCode)) {
                          rolodexId.put("rolodexId", subAwardContact.getRolodex().getRolodexId());
                          Rolodex rolodex = businessObjectService.findByPrimaryKey(Rolodex.class, rolodexId);
                           if( rolodex.getFullName()==null) {
                           rolodexDetails.setRolodexName(rolodex.getOrganization());
                           }
                           else {
                           rolodexDetails.setRolodexName(rolodex.getFullName());
                           }
                           rolodexDetails.setAddress1(rolodex.getAddressLine1());
                           rolodexDetails.setAddress2(rolodex.getAddressLine2());
                           rolodexDetails.setAddress3(rolodex.getAddressLine3());
                           rolodexDetails.setCity(rolodex.getCity());
                           String countryCode = rolodex.getCountryCode();
                           String stateName = rolodex.getState();
                           if(countryCode != null && countryCode.length() > 0 && stateName != null && stateName.length() > 0){
                               State state = KcServiceLocator.getService(PrintingUtils.class).getStateFromName(countryCode, stateName);
                               if(state != null){
                                   rolodexDetails.setStateDescription(state.getName());
                               }
                           }
                           rolodexDetails.setPincode(rolodex.getPostalCode());
                           rolodexDetails.setPhoneNumber(rolodex.getPhoneNumber());
                           rolodexDetails.setFax(rolodex.getFaxNumber());
                           rolodexDetails.setEmail(rolodex.getEmailAddress());
                      }
              }
             }
            }
            primeAuthorisedOfficial.setRolodexDetails(rolodexDetails);
            primeAuthorisedList.add(primeAuthorisedOfficial);
            subContractData.setPrimeAuthorizedOfficialArray(primeAuthorisedList.toArray(new PrimeAuthorizedOfficial [0]));
        }
            public void setAdministrativeContact(SubContractData subContractData, SubAward subaward) {
                AdministrativeContact administrativeContact = AdministrativeContact.Factory.newInstance();
                RolodexDetailsType rolodexDetails = RolodexDetailsType.Factory.newInstance();
                List<AdministrativeContact> administrativeContactList = new ArrayList<>();
                Map<String, String> administrativeMap = new HashMap<>();
                Map<String, Integer> rolodexId = new HashMap<>();
                parameterService = KcServiceLocator.getService(ParameterService.class);
                String administrativeCode = parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_SUBAWARD, ParameterConstants.DOCUMENT_COMPONENT, Constants.PARAMETER_FDP_SUB_ADMINISTRATIVE_CONTACT_CODE);
                administrativeMap.put("contactTypeCode", administrativeCode);
                ContactUsage contactUsage =   businessObjectService.findByPrimaryKey(ContactUsage.class, administrativeMap);
                if(contactUsage.getModuleCode().equals(CoeusModule.SUBCONTRACTS_MODULE_CODE)){
                  if(subaward.getSubAwardContactsList() != null && !subaward.getSubAwardContactsList().isEmpty()){
                      for(SubAwardContact subAwardContact : subaward.getSubAwardContactsList()){
                      if(subAwardContact.getContactTypeCode().equals(administrativeCode))
                          {
                          rolodexId.put("rolodexId", subAwardContact.getRolodex().getRolodexId());
                          Rolodex rolodex = businessObjectService.findByPrimaryKey(Rolodex.class, rolodexId);
                          String organization = rolodex.getOrganization();
                          String fullName = rolodex.getFullName();
                          if (fullName != null && fullName.length() > 0){
                              rolodexDetails.setRolodexName(fullName);
                          } else {
                              rolodexDetails.setRolodexName(organization);
                          }
                          rolodexDetails.setAddress1(rolodex.getAddressLine1());
                          rolodexDetails.setAddress2(rolodex.getAddressLine2());
                          rolodexDetails.setAddress3(rolodex.getAddressLine3());
                          rolodexDetails.setCity(rolodex.getCity());
                          String countryCode = rolodex.getCountryCode();
                          String stateName = rolodex.getState();
                          if(countryCode != null && countryCode.length() > 0 && stateName != null && stateName.length() > 0){
                              State state = KcServiceLocator.getService(PrintingUtils.class).getStateFromName(countryCode, stateName);
                              if(state != null){
                                  rolodexDetails.setStateDescription(state.getName());
                              }
                          }
                          rolodexDetails.setPincode(rolodex.getPostalCode());
                          rolodexDetails.setPhoneNumber(rolodex.getPhoneNumber());
                          rolodexDetails.setFax(rolodex.getFaxNumber());
                          rolodexDetails.setEmail(rolodex.getEmailAddress());
                          }
                  }}
                }
                administrativeContact.setRolodexDetails(rolodexDetails);
                administrativeContactList.add(administrativeContact);
                subContractData.setAdministrativeContactArray(administrativeContactList.toArray(new AdministrativeContact [0]));
            }
            public void setFinancialContact(SubContractData subContractData, SubAward subaward) {
                FinancialContact financialContact =FinancialContact.Factory.newInstance();
                RolodexDetailsType rolodexDetails = RolodexDetailsType.Factory.newInstance();
                List<FinancialContact> financialContactList = new ArrayList<>();
                Map<String, String> administrativeMap = new HashMap<>();
                Map<String, Integer> rolodexId = new HashMap<>();
                parameterService = KcServiceLocator.getService(ParameterService.class);
                String financialContactCode = parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_SUBAWARD, ParameterConstants.DOCUMENT_COMPONENT, Constants.PARAMETER_FDP_SUB_FINANCIAL_CONTACT_CODE);
                administrativeMap.put("contactTypeCode", financialContactCode);
                ContactUsage contactUsage =   businessObjectService.findByPrimaryKey(ContactUsage.class, administrativeMap);
                if(contactUsage.getModuleCode().equals(CoeusModule.SUBCONTRACTS_MODULE_CODE)){
                  if(subaward.getSubAwardContactsList() != null && !subaward.getSubAwardContactsList().isEmpty()){
                      for(SubAwardContact subAwardContact : subaward.getSubAwardContactsList()){
                      if(subAwardContact.getContactTypeCode().equals(financialContactCode))
                          {
                          rolodexId.put("rolodexId", subAwardContact.getRolodex().getRolodexId());
                          Rolodex rolodex = businessObjectService.findByPrimaryKey(Rolodex.class, rolodexId);
                          String organization = rolodex.getOrganization();
                          String fullName = rolodex.getFullName();
                          if (fullName != null && fullName.length() > 0){
                              rolodexDetails.setRolodexName(fullName);
                          } else {
                              rolodexDetails.setRolodexName(organization);
                          }
                          rolodexDetails.setAddress1(rolodex.getAddressLine1());
                          rolodexDetails.setAddress2(rolodex.getAddressLine2());
                          rolodexDetails.setAddress3(rolodex.getAddressLine3());
                          rolodexDetails.setCity(rolodex.getCity());
                          String countryCode = rolodex.getCountryCode();
                          String stateName = rolodex.getState();
                          if(countryCode != null && countryCode.length() > 0 && stateName != null && stateName.length() > 0){
                              State state = KcServiceLocator.getService(PrintingUtils.class).getStateFromName(countryCode, stateName);
                              if(state != null){
                                  rolodexDetails.setStateDescription(state.getName());
                              }
                          }
                          rolodexDetails.setPincode(rolodex.getPostalCode());
                          rolodexDetails.setPhoneNumber(rolodex.getPhoneNumber());
                          rolodexDetails.setFax(rolodex.getFaxNumber());
                          rolodexDetails.setEmail(rolodex.getEmailAddress());
                          }
                  }}
                }
                financialContact.setRolodexDetails(rolodexDetails);
                financialContactList.add(financialContact);
                subContractData.setFinancialContactArray(financialContactList.toArray(new FinancialContact [0]));
            }
            public void setAuthorizedOfficial(SubContractData subContractData, SubAward subaward) {
                AuthorizedOfficial authorizedOfficial =AuthorizedOfficial.Factory.newInstance();
                RolodexDetailsType rolodexDetails = RolodexDetailsType.Factory.newInstance();
                List<AuthorizedOfficial> authorizedOfficialList = new ArrayList<>();
                Map<String, String> administrativeMap = new HashMap<>();
                Map<String, Integer> rolodexId = new HashMap<>();
                parameterService = KcServiceLocator.getService(ParameterService.class);
                String financialContactCode = parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_SUBAWARD, ParameterConstants.DOCUMENT_COMPONENT, Constants.PARAMETER_FDP_SUB_AUTHORIZED_CONTACT_CODE);
                administrativeMap.put("contactTypeCode", financialContactCode);
                ContactUsage contactUsage =   businessObjectService.findByPrimaryKey(ContactUsage.class, administrativeMap);
                if(contactUsage.getModuleCode().equals(CoeusModule.SUBCONTRACTS_MODULE_CODE)){
                  if(subaward.getSubAwardContactsList() != null && !subaward.getSubAwardContactsList().isEmpty()){
                      for(SubAwardContact subAwardContact : subaward.getSubAwardContactsList()){
                      if(subAwardContact.getContactTypeCode().equals(financialContactCode))
                          {
                          rolodexId.put("rolodexId", subAwardContact.getRolodex().getRolodexId());
                          Rolodex rolodex = businessObjectService.findByPrimaryKey(Rolodex.class, rolodexId);
                          String organization = rolodex.getOrganization();
                          String fullName = rolodex.getFullName();
                          if (fullName != null && fullName.length() > 0){
                              rolodexDetails.setRolodexName(fullName);
                          } else {
                              rolodexDetails.setRolodexName(organization);
                          }
                          rolodexDetails.setAddress1(rolodex.getAddressLine1());
                          rolodexDetails.setAddress2(rolodex.getAddressLine2());
                          rolodexDetails.setAddress3(rolodex.getAddressLine3());
                          rolodexDetails.setCity(rolodex.getCity());
                          String countryCode = rolodex.getCountryCode();
                          String stateName = rolodex.getState();
                          if(countryCode != null && countryCode.length() > 0 && stateName != null && stateName.length() > 0){
                              State state = KcServiceLocator.getService(PrintingUtils.class).getStateFromName(countryCode, stateName);
                              if(state != null){
                                  rolodexDetails.setStateDescription(state.getName());
                              }
                          }
                          rolodexDetails.setPincode(rolodex.getPostalCode());
                          rolodexDetails.setPhoneNumber(rolodex.getPhoneNumber());
                          rolodexDetails.setFax(rolodex.getFaxNumber());
                          rolodexDetails.setEmail(rolodex.getEmailAddress());
                          }
                  }}
                }
                authorizedOfficial.setRolodexDetails(rolodexDetails);
                authorizedOfficialList.add(authorizedOfficial);
                subContractData.setAuthorizedOfficialArray(authorizedOfficialList.toArray(new AuthorizedOfficial [0]));
            }
            public void setSubcontractReports(SubContractData subContractData, SubAward subaward) {
                List<SubcontractReports> subContractReportsList = new ArrayList<>();
                Map<String, String> reportTypeCode = new HashMap<>();
                SubAwardReportType subAwardReportsType;
                if(subaward.getSubAwardReportList() != null && !subaward.getSubAwardReportList().isEmpty()){
                for(SubAwardReports subAwardReports : subaward.getSubAwardReportList()) {
                    SubcontractReports subContractReports = SubcontractReports.Factory.newInstance();
                    reportTypeCode.put("subAwardReportTypeCode", subAwardReports.getSubAwardReportTypeCode());
                    subAwardReportsType = businessObjectService.findByPrimaryKey(SubAwardReportType.class, reportTypeCode);
                subContractReports.setReportTypeDescription(subAwardReportsType.getDescription());
                subContractReportsList.add(subContractReports);
                }
               }
                subContractData.setSubcontractReportsArray(subContractReportsList.toArray(new SubcontractReports [0]));
            }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    private DateTimeService dateTimeService;

    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }


    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }


    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }


    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}