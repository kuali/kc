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
package org.kuali.kra.subaward.subawardrule;


import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.KcDocumentBaseAuditRule;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.subaward.bo.*;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import org.kuali.kra.subaward.subawardrule.events.AddSubAwardAttachmentEvent;

import java.util.Collections;

import static org.kuali.kra.infrastructure.KeyConstants.*;

/**
 * This class is for rule validation while
 * subAwardDocumentRule is used...
 */
public class SubAwardDocumentRule extends
        KcTransactionalDocumentRuleBase implements SubAwardRule,
SubAwardAmountInfoRule,
SubAwardContactRule,
SubAwardCloseoutRule,
SubAwardFundingSourceRule,
DocumentAuditRule,
AddSubAwardAttachmentRule,
SubAwardTemplateInfoRule,
SubAwardFfataReportingRule {

    private static final String STATUS_CODE = ".statusCode";
    private static final String SUBAWARD_TYPE_CODE = ".subAwardTypeCode";
    private static final String REQUISITIONER = ".requisitionerUserName";
    private static final String REQUISITIONER_UNIT = ".requisitionerUnit";
    private static final String PURCHASE_ORDER_NUM= ".purchaseOrderNum";
    private static final String SUBCONTRACTOR_ID = ".organizationId";
    private static final String NEW_SUBAWARD = "document.subAwardList[0]";
    private static final String SUBAWARD_START_DATE =".startDate";
    private static final String SITEINVESTIGATOR =".siteInvestigatorId";
    private static final String AMOUNT_INFO_OBLIGATED_AMOUNT = "newSubAwardAmountInfo.obligatedChange";
    private static final String AMOUNT_INFO_ANTICIPATED_AMOUNT = "newSubAwardAmountInfo.anticipatedChange";

    private static final String ROLODEX_ID="newSubAwardContact.rolodex.fullName";
    private static final String CONTACT_TYPE_CODE="newSubAwardContact.contactTypeCode";
    
    private static final String CLOSEOUT_TYPE_CODE="newSubAwardCloseout.closeoutTypeCode";
    private static final String DATE_REQUESTED = "newSubAwardCloseout.dateRequested";
    private static final String DATE_FLLOWUP = "newSubAwardCloseout.dateFollowup";
    private static final String REPORT_TYPE = "subAwardAttachmentFormBean.newReport.subAwardReportTypeCode";
    
    private static final String AWARD_NUMBER="newSubAwardFundingSource.award.awardNumber";
    private static final String AMOUNT_PERIOD_OF_PERFORMANCE_START_DATE = "newSubAwardAmountInfo.periodofPerformanceStartDate";
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(SubAwardDocumentRule.class);
    public static final String DESCRIPTION = ".description";
    public static final String SUB_AWARD_ATTACHMENT_TYPE_CODE = ".subAwardAttachmentTypeCode";

    /**.
     * This method is for AddSubAwardBusinessRules
     * @param subAward
     * @return rulePassed boolean...
     */
    public boolean processAddSubAwardBusinessRules(SubAward subAward) {

        boolean rulePassed = true;
        rulePassed &= processSaveSubAwardBusinessRules(subAward, NEW_SUBAWARD);
        return rulePassed;
}
    /**.
     * This method is for SaveSubAwardBusinessRules
     * @param subAward
     * @param propertyPrefix
     * @return  boolean...
     */
    protected boolean  processSaveSubAwardBusinessRules(
    SubAward subAward,String propertyPrefix){
     
        boolean rulePassed = true;

        if (subAward.getOrganizationId() == null ){ 
            rulePassed = false;
            reportError(propertyPrefix+SUBCONTRACTOR_ID
                    , KeyConstants.ERROR_REQUIRED_SUBRECIPIENT_ID);
        }   
        
        if(subAward.getStatusCode()==null ){ 
            rulePassed = false;            
            reportError(propertyPrefix+STATUS_CODE
                    , KeyConstants.ERROR_REQUIRED_STATUS);          
        }  
        if(subAward.getSubAwardTypeCode()==null ){ 
            rulePassed = false;            
            reportError(propertyPrefix+SUBAWARD_TYPE_CODE
                    , KeyConstants.ERROR_REQUIRED_SUBAWARD_TYPE);
        }  
        if(subAward.getRequisitionerId()==null ){ 
            rulePassed = false;            
            if (subAward.getRequisitionerUserName() == null) {
                reportError(propertyPrefix + REQUISITIONER
                        , KeyConstants.ERROR_REQUIRED_REQUISITIONER);
            }
            else {
                reportError(propertyPrefix + REQUISITIONER,
                        KeyConstants.ERROR_INVALID_REQUISITIONER, new String[] {subAward.getRequisitionerUserName()});
            }
        }  
        if(subAward.getRequisitionerUnit() != null){
            Unit leadUnit = (Unit) getBusinessObjectService().findByPrimaryKey(Unit.class, Collections.singletonMap("unitNumber", subAward.getRequisitionerUnit()));
            if(leadUnit == null)
                reportError(propertyPrefix+REQUISITIONER_UNIT
                        , KeyConstants.ERROR_REQUIRED_REQUISITIONER_UNIT); 
        }
        if(subAward.getPurchaseOrderNum()==null ){
            rulePassed = false;
            
            reportError(propertyPrefix+PURCHASE_ORDER_NUM
                    , KeyConstants.ERROR_REQUIRED_PURCHASE_ORDER_NUM); 
        }      
        if(subAward.getStartDate()!=null && subAward.getEndDate()!=null){
            if(subAward.getStartDate().after(subAward.getEndDate())){
                rulePassed = false;
                reportError(propertyPrefix+SUBAWARD_START_DATE
                        , KeyConstants.SUBAWARD_ERROR_END_DATE_GREATER_THAN_START); 
            }
        }
        if (subAward.getSiteInvestigator() == null && subAward.getSiteInvestigatorId() != null) {
            rulePassed = false;               
            reportError(propertyPrefix + SITEINVESTIGATOR, 
                    KeyConstants.ERROR_INVALID_SITEINVESTIGATOR_ID, new String[] {subAward.getSiteInvestigatorId().toString()});          
        }
        if (subAward.getOrganizationId() != null) { 
            if (subAward.getOrganization() == null) {
                rulePassed = false;               
                reportError(propertyPrefix + SUBCONTRACTOR_ID, KeyConstants.ERROR_INVALID_SUBRECIPIENT_ID, new String[] {subAward.getOrganizationId()});
            }           
        }
        return rulePassed;
    }

    public boolean processAddSubAwardAmountInfoBusinessRules(SubAwardAmountInfo amountInfo,SubAward subAward) {
        boolean rulePassed = true; 
        
        GlobalVariables.getMessageMap().addToErrorPath("newSubAwardAmountInfo");
        rulePassed &= getDictionaryValidationService().isBusinessObjectValid(amountInfo); 
        GlobalVariables.getMessageMap().removeFromErrorPath("newSubAwardAmountInfo");
        
        ScaleTwoDecimal obligatedAmount = subAward.getTotalObligatedAmount();
        if (amountInfo.getObligatedChange() != null) {
            obligatedAmount = obligatedAmount.add(amountInfo.getObligatedChange());
            if (obligatedAmount.isNegative()) {
                rulePassed = false; 
                reportError(AMOUNT_INFO_OBLIGATED_AMOUNT, KeyConstants.ERROR_AMOUNT_INFO_OBLIGATED_AMOUNT_NEGATIVE); 
            }
        }
        ScaleTwoDecimal anticipatedAmount = subAward.getTotalAnticipatedAmount();
        if (amountInfo.getAnticipatedChange() != null) {
            anticipatedAmount = anticipatedAmount.add(amountInfo.getAnticipatedChange());
            if (anticipatedAmount.isNegative()) {
                rulePassed = false; 
                reportError(AMOUNT_INFO_ANTICIPATED_AMOUNT, KeyConstants.ERROR_AMOUNT_INFO_ANTICIPATED_AMOUNT_NEGATIVE); 
            }
        }
        
        if (obligatedAmount.isGreaterThan(anticipatedAmount)) {
            rulePassed = false;
            reportError(AMOUNT_INFO_ANTICIPATED_AMOUNT, KeyConstants.ERROR_AMOUNT_INFO_OBLIGATED_AMOUNT_GREATER_THAN_ANTICIPATED_AMOUNT); 
        }
        
        if (obligatedAmount.isLessThan(subAward.getTotalAmountReleased())) {
            rulePassed = false;
            reportError(AMOUNT_INFO_OBLIGATED_AMOUNT, KeyConstants.ERROR_SUBAWARD_OBLIGATED_AMOUNT_SHOULD_BE_GREATER_AMOUNT_RELEASED); 
        }
        
        if (amountInfo.getPeriodofPerformanceStartDate() != null && amountInfo.getPeriodofPerformanceEndDate()!= null) {
            if(amountInfo.getPeriodofPerformanceStartDate().after(amountInfo.getPeriodofPerformanceEndDate())){
                rulePassed = false;
                reportError(AMOUNT_PERIOD_OF_PERFORMANCE_START_DATE,
                        KeyConstants.ERROR_PERIOD_OF_PERFORMANCE_START_DATE_SHOULD_BE_GREATER_THAN_ERROR_PERIOD_OF_PERFORMANCE_END_DATE);         
            }
        }
        return rulePassed;
    }

    public boolean processAddSubAwardContactBusinessRules(SubAwardContact subAwardContact,SubAward subAward) {
        boolean rulePassed = true;          
        
        if(subAwardContact==null 
                || subAwardContact.getRolodexId()==null){
            rulePassed = false;            
            reportError(ROLODEX_ID
                    , KeyConstants.ERROR_REQUIRED_SUBAWARD_CONTACT_ROLODEX_ID);
        }  
        if(subAwardContact==null 
                || subAwardContact.getContactTypeCode()==null){
            rulePassed = false;            
            reportError(CONTACT_TYPE_CODE
                    , KeyConstants.ERROR_REQUIRED_SUBAWARD_CONTACT_TYPE_CODE);
        }  
        for(SubAwardContact contact : subAward.getSubAwardContactsList()){
            if(ObjectUtils.equals(contact.getRolodexId(), subAwardContact.getRolodexId()) 
                    && ObjectUtils.equals(contact.getContactTypeCode(), subAwardContact.getContactTypeCode())) {
                rulePassed = false;              
                String contactName = contact.getRolodex().getFullName();

                if(contactName == null){
                    contactName = contact.getRolodex().getOrganization();
                }               
                reportError(ROLODEX_ID, KeyConstants.ERROR_REQUIRED_SUBAWARD_CONTACT_PERSON_EXIST, new String[] {contactName});  
            }
        }
        return rulePassed;
    }

    public boolean processAddSubAwardCloseoutBusinessRules(SubAwardCloseout subAwardCloseout) {
        boolean rulePassed = true;
        rulePassed &= processSaveSubAwardCloseoutBusinessRules(subAwardCloseout);
        
        return rulePassed;
    }
    protected boolean  processSaveSubAwardCloseoutBusinessRules(SubAwardCloseout subAwardCloseout){
        boolean rulePassed = true;   
        
        if(subAwardCloseout==null 
                || subAwardCloseout.getCloseoutTypeCode()==null){
            rulePassed = false;            
            reportError(CLOSEOUT_TYPE_CODE
                    , KeyConstants.ERROR_REQUIRED_SUBAWARD_CLOSEOUT_TYPE_CODE);
        }  
        if (subAwardCloseout == null || subAwardCloseout.getDateRequested() == null) {
            rulePassed = false;
            reportError(DATE_REQUESTED, KeyConstants.ERROR_REQUIRED_SUBAWARD_DATE_REQUESTED);
        }
        
        if (subAwardCloseout == null || subAwardCloseout.getDateFollowup()== null) {
            rulePassed = false;
            reportError(DATE_FLLOWUP, KeyConstants.ERROR_REQUIRED_SUBAWARD_DATE_FOLLOWUP);
        }
        
        return rulePassed;
    }
    
    
    

    public boolean processAddSubAwardFundingSourceBusinessRules(SubAwardFundingSource subAwardFundingSource,SubAward subAward) {
        boolean rulePassed = true;
        rulePassed &= processSaveSubAwardFundingSourceBusinessRules(subAwardFundingSource,subAward);
        
        return rulePassed;
    }
    protected boolean processSaveSubAwardFundingSourceBusinessRules(SubAwardFundingSource subAwardFundingSource,SubAward subAward){
        boolean rulePassed = true;   
        
        if(subAwardFundingSource==null 
                || subAwardFundingSource.getAwardId()==null){
            rulePassed = false;            
            reportError(AWARD_NUMBER
                    , KeyConstants.ERROR_REQUIRED_SUBAWARD_FUNDING_SOURCE_AWARD_NUMBER);
        }  
        else{
            for(SubAwardFundingSource fundingSource : subAward.getSubAwardFundingSourceList()){
                if(fundingSource.getAwardId().equals(subAwardFundingSource.getAwardId())){
                    rulePassed = false;
                    AwardService awardService = KcServiceLocator.getService(AwardService.class);
                    Award award = awardService.getAward(fundingSource.getAwardId());
                    
                    reportError(AWARD_NUMBER, KeyConstants.ERROR_REQUIRED_SUBAWARD_FUNDING_SOURCE_AWARD_NUMBER_DUPLICATE, new String[] {award.getAwardNumber()});
                }
            }
        }
        return rulePassed;
    }

    @Override
    public boolean processRunAuditBusinessRules(Document document){
        boolean retval = new KcDocumentBaseAuditRule().processRunAuditBusinessRules(document);
        retval &= new SubAwardAuditRule().processRunAuditBusinessRules(document);
        retval &= new SubAwardFinancialAuditRule().processRunAuditBusinessRules(document);
        return retval;
    }
    
    
    
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        if (!(document instanceof SubAwardDocument)) {
            return false;
        }

        MessageMap errorMap = GlobalVariables.getMessageMap();

        return true;
    }
    
    public boolean processRules(KcDocumentEventBaseExtension event) {
        boolean retVal = false;
        retVal = event.getRule().processRules(event);
        return retVal;
    }
    /**
     * @see org.kuali.kra.subaward.subawardrule.AddSubAwardAttachmentRule#processsAddSubawardAttachmentRule(org.kuali.kra.subaward.subawardrule.events.AddSubAwardAttachmentEvent)
     */
    public boolean processsAddSubawardAttachmentRule(AddSubAwardAttachmentEvent event) {
        boolean valid = true;
        
        if( StringUtils.isBlank(event.getSubAwardAttachments().getSubAwardAttachmentTypeCode())) {
            valid = false;
            LOG.debug(SUBAWARD_ATTACHMENT_TYPE_CODE_REQUIRED);
            reportError("subAwardAttachmentFormBean.newAttachment.subAwardAttachmentTypeCode", SUBAWARD_ATTACHMENT_TYPE_CODE_REQUIRED);
        }
        
        if (StringUtils.isBlank(event.getSubAwardAttachments().getNewFile().getFileName()) ) {
            valid = false;
            LOG.debug(SUBAWARD_ATTACHMENT_FILE_REQUIRED);
            reportError("subAwardAttachmentFormBean.newAttachment.newFile", SUBAWARD_ATTACHMENT_FILE_REQUIRED);
    
            }
        if (StringUtils.isBlank(event.getSubAwardAttachments().getDescription()) ) {
            valid = false;
            LOG.debug(SUBAWARD_ATTACHMENT_DESCRIPTION_REQUIRED);
            reportError("subAwardAttachmentFormBean.newAttachment.description", SUBAWARD_ATTACHMENT_DESCRIPTION_REQUIRED);
    
            }
       return valid;
    }

    public boolean processApplySubawardAttachmentModificationRule(AddSubAwardAttachmentEvent event) {
        boolean valid = true;
        if( StringUtils.isBlank(event.getSubAwardAttachments().getSubAwardAttachmentTypeCode())) {
            valid = false;
            LOG.debug(SUBAWARD_ATTACHMENT_TYPE_CODE_REQUIRED);
            reportError(event.getErrorPathPrefix() + SUB_AWARD_ATTACHMENT_TYPE_CODE, SUBAWARD_ATTACHMENT_TYPE_CODE_REQUIRED);
        }
        if (StringUtils.isBlank(event.getSubAwardAttachments().getDescription()) ) {
            valid = false;
            LOG.debug(SUBAWARD_ATTACHMENT_DESCRIPTION_REQUIRED);
            reportError(event.getErrorPathPrefix() + DESCRIPTION, SUBAWARD_ATTACHMENT_DESCRIPTION_REQUIRED);
        }
        return valid;
    }

    public boolean processsAddSubawardReportRule(SubAwardReports subAwardReports) {
        boolean valid = true;
        
        if(subAwardReports==null 
                || subAwardReports.getSubAwardReportTypeCode()==null){
            valid = false;            
            reportError(REPORT_TYPE
                    , KeyConstants.ERROR_REQUIRED_SUBAWARD_REPORT_TYPE_CODE);
        }
        return valid;
        
    }
    public boolean processAddSubAwardTemplateInfoBusinessRules(SubAward subAward) {
        boolean rulePassed = true;
        rulePassed &= processSaveSubAwardTemplateInfoBusinessRules(subAward);
        
        return rulePassed;
    }
    protected boolean processSaveSubAwardTemplateInfoBusinessRules(SubAward subAward){
        boolean rulePassed = true;   
        for (SubAwardTemplateInfo subAwardTemplateInfo : subAward.getSubAwardTemplateInfo()) {
          if (subAward!=null 
                && ((subAwardTemplateInfo.getAutomaticCarryForward()!=null) && (subAwardTemplateInfo.getAutomaticCarryForward().equals("Y")))) {
             if (subAwardTemplateInfo.getCarryForwardRequestsSentTo()==null) {
                rulePassed = false;            
                LOG.debug(ERROR_REQUIRED_SUBAWARD_TEMPLATE_INFO_CARRY_FORWARD_REQUESTS_SENT_TO);
                reportError("document.subAwardList[0].subAwardTemplateInfo[0].carryForwardRequestsSentTo", ERROR_REQUIRED_SUBAWARD_TEMPLATE_INFO_CARRY_FORWARD_REQUESTS_SENT_TO);  
             }
          }  
        }
        return rulePassed;
    }

    @Override
    public boolean processAddSubAwardFfataReportingBusinessRules(SubAwardFfataReporting subAwardFfataReporting, SubAward subAward) {

        boolean valid = getDictionaryValidationService().isBusinessObjectValid(subAwardFfataReporting, "newSubAwardFfataReporting");

        if (subAwardFfataReporting.getSubAwardAmountInfoId() != null == StringUtils.isNotBlank(subAwardFfataReporting.getOtherTransactionDescription())) {
            reportError("newSubAwardFfataReporting.otherTransactionDescription", SUBAWARD_FFATA_REPORTING_TRANS_OTHER_DESC);
            valid = false;
        }

        if (subAwardFfataReporting.getSubAwardAmountInfoId() != null && (subAwardFfataReporting.getDateSubmitted() == null ||
                (subAwardFfataReporting.getSubAwardAmountInfo() != null
                        && subAwardFfataReporting.getSubAwardAmountInfo().getEffectiveDate().after(subAwardFfataReporting.getDateSubmitted())))) {
            reportError("newSubAwardFfataReporting.dateSubmitted", SUBAWARD_FFATA_REPORTING_SUNMITTED_DATE);
            valid = false;
        }
        return valid;
    }
}
