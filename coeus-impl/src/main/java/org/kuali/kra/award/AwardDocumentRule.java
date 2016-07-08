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
package org.kuali.kra.award;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.KcDocumentBaseAuditRule;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.permissions.impl.bo.PermissionsUser;
import org.kuali.coeus.common.permissions.impl.bo.PermissionsUserEditRoles;
import org.kuali.coeus.common.permissions.impl.rule.PermissionsRule;
import org.kuali.coeus.common.permissions.impl.web.bean.User;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.commitments.*;
import org.kuali.kra.award.contacts.*;
import org.kuali.kra.award.detailsdates.AddAwardTransferringSponsorEvent;
import org.kuali.kra.award.detailsdates.AwardDetailsAndDatesRule;
import org.kuali.kra.award.detailsdates.AwardDetailsAndDatesRuleImpl;
import org.kuali.kra.award.detailsdates.AwardDetailsAndDatesSaveEvent;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubaward;
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubawardRuleEvent;
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubawardRuleImpl;
import org.kuali.kra.award.home.keywords.AwardScienceKeyword;
import org.kuali.kra.award.lookup.keyvalue.FrequencyBaseCodeValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.ReportCodeValuesFinder;
import org.kuali.kra.award.notesandattachments.attachments.AwardAttachment;
import org.kuali.kra.award.paymentreports.awardreports.*;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTrackingBean;
import org.kuali.kra.award.paymentreports.closeout.AddAwardCloseoutRuleEvent;
import org.kuali.kra.award.paymentreports.closeout.AwardCloseoutRule;
import org.kuali.kra.award.paymentreports.closeout.AwardCloseoutRuleEvent;
import org.kuali.kra.award.paymentreports.closeout.AwardCloseoutRuleImpl;
import org.kuali.kra.award.paymentreports.paymentschedule.AddAwardPaymentScheduleRuleEvent;
import org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentScheduleRule;
import org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentScheduleRuleEvent;
import org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentScheduleRuleImpl;
import org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.AwardApprovedEquipmentRule;
import org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.AwardApprovedEquipmentRuleEvent;
import org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.AwardApprovedEquipmentRuleImpl;
import org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.EquipmentCapitalizationMinimumLoader;
import org.kuali.kra.award.paymentreports.specialapproval.foreigntravel.AwardApprovedForeignTravelRule;
import org.kuali.kra.award.paymentreports.specialapproval.foreigntravel.AwardApprovedForeignTravelRuleEvent;
import org.kuali.kra.award.paymentreports.specialapproval.foreigntravel.AwardApprovedForeignTravelRuleImpl;
import org.kuali.kra.award.permissions.AwardPermissionsRule;
import org.kuali.kra.award.rule.AddAwardAttachmentRule;
import org.kuali.kra.award.rule.AwardCommentsRule;
import org.kuali.kra.award.rule.AwardCommentsRuleImpl;
import org.kuali.kra.award.rule.event.AddAwardAttachmentEvent;
import org.kuali.kra.award.rule.event.AwardCommentsRuleEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.timeandmoney.TimeAndMoneyForm;
import org.kuali.kra.timeandmoney.rule.event.TimeAndMoneyAwardDateSaveEvent;
import org.kuali.kra.timeandmoney.rules.TimeAndMoneyAwardDateSaveRuleImpl;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.util.MessageMap;

import java.sql.Date;
import java.util.List;

import static org.kuali.kra.infrastructure.KeyConstants.AWARD_ATTACHMENT_FILE_REQUIRED;
import static org.kuali.kra.infrastructure.KeyConstants.AWARD_ATTACHMENT_TYPE_CODE_REQUIRED;



/**
 * Main Business Rule class for <code>{@link AwardDocument}</code>. 
 * Responsible for delegating rules to independent rule classes.
 *
 */
public class AwardDocumentRule extends KcTransactionalDocumentRuleBase implements AwardPaymentScheduleRule,
                                                                            AwardApprovedEquipmentRule, 
                                                                            AwardApprovedForeignTravelRule, 
                                                                            AddFandaRateRule,
                                                                            AwardFandaRateSaveRule,
                                                                            AwardDetailsAndDatesRule,
                                                                            AwardProjectPersonsSaveRule,
                                                                            PermissionsRule,
                                                                            AwardReportTermRule,
                                                                            AwardReportTermRecipientRule,
                                                                            AwardCloseoutRule,
                                                                            AwardTemplateSyncRule,
                                                                            AwardCommentsRule,
        KcBusinessRule,
                                                                            AddAwardAttachmentRule,
                                                                            DocumentAuditRule{
    
    public static final String DOCUMENT_ERROR_PATH = "document";
    public static final String AWARD_ERROR_PATH = "awardList[0]";
    private static final String AWARD_ERROR_PATH_PREFIX = "document.awardList[0].";
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(AwardDocumentRule.class);
    public static final String TYPE_CODE = ".typeCode";

    private ParameterService parameterService;
    private KcPersonService kcPersonService;

    public KcPersonService getKcPersonService() {
        if (kcPersonService == null) {
            kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return kcPersonService;
    }

    /**
     * @see org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.AwardApprovedEquipmentRule
     *  #processAwardApprovedEquipmentBusinessRules(org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.AwardApprovedEquipmentRuleEvent)
     */
    public boolean processAwardApprovedEquipmentBusinessRules(AwardApprovedEquipmentRuleEvent event) {
        return processApprovedEquipmentBusinessRules(GlobalVariables.getMessageMap(), event.getAwardDocument());
    }
    
    /**
     * @see org.kuali.kra.award.paymentreports.specialapproval.foreigntravel.AwardApprovedForeignTravelRule#processAwardApprovedForeignTravelBusinessRules
     * (org.kuali.kra.award.paymentreports.specialapproval.foreigntravel.AwardApprovedForeignTravelRuleEvent)
     * @see org.kuali.kra.award.paymentreports.specialapproval.foreigntravel.AwardApprovedForeignTravelRule
     *  #processAwardApprovedForeignTravelBusinessRules(org.kuali.kra.award.paymentreports.specialapproval.foreigntravel.AwardApprovedForeignTravelRuleEvent)
     */
        public boolean processAwardApprovedForeignTravelBusinessRules(AwardApprovedForeignTravelRuleEvent event) {
            return processApprovedForeignTravelBusinessRules(GlobalVariables.getMessageMap(), event.getAwardDocument());
    }
    
    /**
     * 
     * @see org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentScheduleRule#processAwardPaymentScheduleBusinessRules(
     * org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentScheduleRuleEvent)
     */
    public boolean processAwardPaymentScheduleBusinessRules(AwardPaymentScheduleRuleEvent event) {
        return processPaymentScheduleBusinessRules(GlobalVariables.getMessageMap(), event.getAwardDocument());
    }
    
    /**
     * 
     * @see org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentScheduleRule#processAddAwardPaymentScheduleBusinessRules(
     * org.kuali.kra.award.paymentreports.paymentschedule.AddAwardPaymentScheduleRuleEvent)
     */
    public boolean processAddAwardPaymentScheduleBusinessRules(AddAwardPaymentScheduleRuleEvent event) {
        return processAddPaymentScheduleBusinessRules(GlobalVariables.getMessageMap(), event);
    }
    
    /**
     * @see org.kuali.kra.award.detailsdates.AwardDetailsAndDatesRule#processAddAwardTransferringSponsorEvent
     * (org.kuali.kra.award.rule.event.AddAwardTransferringSponsorEvent)
     */
    public boolean processAddAwardTransferringSponsorEvent(AddAwardTransferringSponsorEvent addAwardTransferringSponsorEvent) {
        return new AwardDetailsAndDatesRuleImpl().processAddAwardTransferringSponsorEvent(addAwardTransferringSponsorEvent);
    }
    
//    /**
//     * @see org.kuali.kra.award.detailsdates.AwardDetailsAndDatesRule#processAddAwardTransferringSponsorEvent
//     * (org.kuali.kra.award.rule.event.AddAwardTransferringSponsorEvent)
//     */
//    public boolean processAddAwardDirectFandADistributionBusinessRules(AwardDirectFandADistributionRuleEvent 
//                                                                                        awardDirectFandADistributionRuleEvent) {
//        return new AwardDirectFandADistributionRuleImpl().processAddAwardDirectFandADistributionBusinessRules(awardDirectFandADistributionRuleEvent);
//    }
    
    /**
     * 
     * @see org.kuali.kra.award.paymentreports.closeout.AwardCloseoutRule#processAddAwardCloseoutBusinessRules(
     *              org.kuali.kra.award.paymentreports.closeout.AddAwardCloseoutRuleEvent)
     */
    public boolean processAddAwardCloseoutBusinessRules(AddAwardCloseoutRuleEvent addAwardCloseoutRuleEvent) {
        return new AwardCloseoutRuleImpl().processAddAwardCloseoutBusinessRules(addAwardCloseoutRuleEvent);
    }
    
    /**
     * 
     * @see org.kuali.kra.award.paymentreports.closeout.AwardCloseoutRule#processAwardCloseoutBusinessRules(
     *          org.kuali.kra.award.paymentreports.closeout.AwardCloseoutRuleEvent)
     */
    public boolean processAwardCloseoutBusinessRules(AwardCloseoutRuleEvent awardCloseoutRuleEvent) {
        return new AwardCloseoutRuleImpl().processAwardCloseoutBusinessRules(awardCloseoutRuleEvent);
    }
    
    /**
     * @see org.kuali.kra.award.contacts.AwardProjectPersonsSaveRule
     *  #processSaveAwardProjectPersonsBusinessRules(org.kuali.kra.award.contacts.SaveAwardProjectPersonsRuleEvent)
     */
    public boolean processSaveAwardProjectPersonsBusinessRules(SaveAwardProjectPersonsRuleEvent event) {
        return processSaveAwardProjectPersonsBusinessRules(GlobalVariables.getMessageMap(), (AwardDocument) event.getDocument());
    }
    
    /**
     * 
     * @see org.kuali.core.rules.DocumentRuleBase#processCustomRouteDocumentBusinessRules(
     * org.kuali.rice.krad.document.Document)
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        return super.processCustomRouteDocumentBusinessRules(document);
    }
    
    /**
     * 
     * @see org.kuali.coeus.common.permissions.impl.rule.PermissionsRule#processAddPermissionsUserBusinessRules(
     * org.kuali.core.document.Document, java.util.List, org.kuali.coeus.common.permissions.impl.bo.PermissionsUser)
     */
    public boolean processAddPermissionsUserBusinessRules(Document document, List<User> users, PermissionsUser newUser) {
        return new AwardPermissionsRule().processAddPermissionsUserBusinessRules(document, users, newUser);
    }
    
    /**
     * 
     * @see org.kuali.coeus.common.permissions.impl.rule.PermissionsRule#processDeletePermissionsUserBusinessRules(
     * org.kuali.core.document.Document, java.util.List, int)
     */
    public boolean processDeletePermissionsUserBusinessRules(Document document, List<User> users, int index) {
        return new AwardPermissionsRule().processDeletePermissionsUserBusinessRules(document, users, index);     
    }
    
    /**
     * 
     * @see org.kuali.coeus.common.permissions.impl.rule.PermissionsRule#processEditPermissionsUserRolesBusinessRules(
     * org.kuali.core.document.Document, java.util.List, org.kuali.coeus.common.permissions.impl.bo.PermissionsUserEditRoles)
     */
    public boolean processEditPermissionsUserRolesBusinessRules(Document document, List<User> users,
            PermissionsUserEditRoles editRoles) {
        return new AwardPermissionsRule().processEditPermissionsUserRolesBusinessRules(document, users, editRoles);
    }
    
    @Override
    public boolean processSaveAwardDetailsAndDates(AwardDetailsAndDatesSaveEvent awardDetailsAndDatesSaveEvent) {
        return new AwardDetailsAndDatesRuleImpl().processSaveAwardDetailsAndDates(awardDetailsAndDatesSaveEvent);
    }
    

    /**
     * 
     * @see org.kuali.rice.krad.rules.DocumentRuleBase#processCustomSaveDocumentBusinessRules(
     * org.kuali.rice.krad.document.Document)
     */
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        if(skipRuleProcessing(document)) {
            return true;
        }
        
        boolean retval = true;
        MessageMap errorMap = GlobalVariables.getMessageMap();
        if (!(document instanceof AwardDocument)) {
            return false;
        }
        
        AwardDocument awardDocument = (AwardDocument) document;

        retval &= processUnitNumberBusinessRule(errorMap, awardDocument);
        retval &= processCostShareBusinessRules(document);
        retval &= processBenefitsRatesBusinessRules(document);
        retval &= processApprovedSubawardBusinessRules(document);
        retval &= processApprovedEquipmentBusinessRules(errorMap, awardDocument);
        retval &= processApprovedForeignTravelBusinessRules(errorMap, awardDocument);
        retval &= processSaveAwardProjectPersonsBusinessRules(errorMap, awardDocument);
        retval &= processAwardCommentsBusinessRules(awardDocument);
        retval &= processAwardDetailsAndDatesSaveRules(document);
        retval &= processDateBusinessRule(errorMap, awardDocument);
        retval &=processKeywordBusinessRule(awardDocument);
        retval &=processAwardAttachmentBusinessRule(awardDocument);
        
        return retval;
    }

    private boolean skipRuleProcessing(Document document) {
        return AwardDocument.PLACEHOLDER_DOC_DESCRIPTION.equals(document.getDocumentHeader().getDocumentDescription());
    }    
    
    private boolean processApprovedEquipmentBusinessRules(MessageMap errorMap, AwardDocument awardDocument) {
        boolean success = true;
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(AWARD_ERROR_PATH);
        Award award = awardDocument.getAward();
        EquipmentCapitalizationMinimumLoader helper = new EquipmentCapitalizationMinimumLoader();
        AwardApprovedEquipmentRuleImpl rule = new AwardApprovedEquipmentRuleImpl();
        if (award.getApprovedEquipmentItems() != null) {
            int count = award.getApprovedEquipmentItems().size();
            for (int i = 0; i < count; i++) {
                String errorPath = String.format("approvedEquipmentItems[%d]", i);
                errorMap.addToErrorPath(errorPath);
                String errorKey = AWARD_ERROR_PATH_PREFIX + errorPath;
                AwardApprovedEquipmentRuleEvent event = new AwardApprovedEquipmentRuleEvent(errorKey, awardDocument, awardDocument.getAward(),
                        award.getApprovedEquipmentItems().get(i),
                        helper.getMinimumCapitalization());
                success &= rule.processAwardApprovedEquipmentBusinessRules(event);
                errorMap.removeFromErrorPath(errorPath);
            }
        }
        errorMap.removeFromErrorPath(AWARD_ERROR_PATH);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        
        return success;
    }
    
    private boolean processPaymentScheduleBusinessRules(MessageMap errorMap, AwardDocument awardDocument) {
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(AWARD_ERROR_PATH);
        
        boolean success = true;
        errorMap.removeFromErrorPath(AWARD_ERROR_PATH);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        return success;
    }
    
    private boolean processAwardAttachmentBusinessRule(AwardDocument awardDocument) {
       boolean valid=true;
       List<AwardAttachment> awardAttachments= awardDocument.getAwardList().get(0).getAwardAttachments();
       for ( AwardAttachment awardAttachment : awardAttachments ) {
           if (awardAttachment.getTypeCode() == null) {
                   valid = false;
           }
      }
       if(valid) {
           for (AwardAttachment awardattachment : awardAttachments) {
        	   awardattachment.setModifyAttachment(false); 
           }
       }
        return valid;
    }
    
    private boolean processKeywordBusinessRule(AwardDocument awardDocument) {
        
       List<AwardScienceKeyword> keywords= awardDocument.getAward().getKeywords();

       for ( AwardScienceKeyword keyword : keywords ) {
            for ( AwardScienceKeyword keyword2 : keywords ) {
                if ( keyword == keyword2 ) {
                    continue;
                } else if ( StringUtils.equalsIgnoreCase(keyword.getScienceKeywordCode(), keyword2.getScienceKeywordCode()) ) {
                    GlobalVariables.getMessageMap().putError("document.awardList[0].keywords", "error.proposalKeywords.duplicate");

                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean processAddPaymentScheduleBusinessRules(MessageMap errorMap, AddAwardPaymentScheduleRuleEvent event) {
        boolean success = new AwardPaymentScheduleRuleImpl().processAddAwardPaymentScheduleBusinessRules(event);
        return success;
    }

    /**
    *
    * process Cost Share business rules.
    * @param awardDocument
    * @return
    */
    private boolean processCostShareBusinessRules(Document document) {
        boolean valid = true;
        MessageMap errorMap = GlobalVariables.getMessageMap();
        AwardDocument awardDocument = (AwardDocument) document;
        int i = 0;
        List<AwardCostShare> awardCostShares = awardDocument.getAward().getAwardCostShares();
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(AWARD_ERROR_PATH);
        for (AwardCostShare awardCostShare : awardCostShares) {
                String errorPath = "awardCostShares[" + i + Constants.RIGHT_SQUARE_BRACKET;
                errorMap.addToErrorPath(errorPath);
                AwardCostShareRuleEvent event = new AwardCostShareRuleEvent(errorPath,
                        awardDocument,
                        awardCostShare);
                valid &= new AwardCostShareRuleImpl().processCostShareBusinessRules(event, i);
                errorMap.removeFromErrorPath(errorPath);
                i++;
        }
        errorMap.removeFromErrorPath(AWARD_ERROR_PATH);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        return valid;
    }
    
    /**
    *
    * process save details and dates Business Rules.
    * @param awardDocument
    * @return
    */
    @SuppressWarnings("deprecation")
    private boolean processAwardDetailsAndDatesSaveRules(Document document) {
        boolean valid = true;
        MessageMap errorMap = GlobalVariables.getMessageMap();
        AwardDocument awardDocument = (AwardDocument) document;
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(AWARD_ERROR_PATH);
        AwardDetailsAndDatesSaveEvent event = new AwardDetailsAndDatesSaveEvent(awardDocument, awardDocument.getAward());
        
        valid &= new AwardDetailsAndDatesRuleImpl().processSaveAwardDetailsAndDates(event);
        errorMap.removeFromErrorPath(AWARD_ERROR_PATH);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        return valid;
    }
    
    /**
     * This method checks the comments on an award
     * @param awardDocument
     * @return
     */
    private boolean processAwardCommentsBusinessRules(AwardDocument awardDocument) {
        AwardCommentsRuleEvent ruleEvent = new AwardCommentsRuleEvent(DOCUMENT_ERROR_PATH + "." + AWARD_ERROR_PATH, awardDocument);
        return processAwardCommentsBusinessRules(ruleEvent);
    }

    public boolean processAwardCommentsBusinessRules(AwardCommentsRuleEvent ruleEvent) {
        return new AwardCommentsRuleImpl().processAwardCommentsBusinessRules(ruleEvent);
    }
    
      
    private boolean processBenefitsRatesBusinessRules(Document document) {
        boolean valid = true;
        MessageMap errorMap = GlobalVariables.getMessageMap();
        AwardDocument awardDocument = (AwardDocument) document;
        Award award = awardDocument.getAward();
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(AWARD_ERROR_PATH);
        if(StringUtils.equalsIgnoreCase(
                getParameterService().getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, 
                        ParameterConstants.DOCUMENT_COMPONENT,
                        KeyConstants.ENABLE_AWARD_FNA_VALIDATION),
                        KeyConstants.ENABLED_PARAMETER_VALUE_ONE) || 
                        StringUtils.equalsIgnoreCase(
                                getParameterService().getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, 
                                        ParameterConstants.DOCUMENT_COMPONENT,
                                        KeyConstants.ENABLE_AWARD_FNA_VALIDATION),
                                        KeyConstants.ENABLED_PARAMETER_VALUE_TWO)){
            String errorPath = "benefitsRates.rates";
            errorMap.addToErrorPath(errorPath);
            AwardBenefitsRatesRuleEvent event = new AwardBenefitsRatesRuleEvent(errorPath, 
                                                                                    award, 
                                                                                    awardDocument); 
            valid &= new AwardBenefitsRatesRuleImpl().processBenefitsRatesBusinessRules(event);
            errorMap.removeFromErrorPath(errorPath);
        }        
        
        errorMap.removeFromErrorPath(AWARD_ERROR_PATH);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        return valid;
    }
    
    /**
    *
    * process ApprovedSubaward business rules.
    * @param awardDocument
    * @return
    */
    public boolean processApprovedSubawardBusinessRules(Document document) {
        boolean valid = true;
        MessageMap errorMap = GlobalVariables.getMessageMap();
        AwardDocument awardDocument = (AwardDocument) document;
        int i = 0;
        List<AwardApprovedSubaward> awardApprovedSubawards = 
                            awardDocument.getAward().getAwardApprovedSubawards();
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(AWARD_ERROR_PATH);
        for (AwardApprovedSubaward awardApprovedSubaward : awardApprovedSubawards) {
            String errorPath = "awardApprovedSubawards[" + i + Constants.RIGHT_SQUARE_BRACKET;
            errorMap.addToErrorPath(errorPath);
            AwardApprovedSubawardRuleEvent event = new AwardApprovedSubawardRuleEvent(errorPath, 
                                                                                      awardDocument, 
                                                                                      awardApprovedSubaward,
                                                                                      awardApprovedSubawards);
            valid &= new AwardApprovedSubawardRuleImpl().processApprovedSubawardBusinessRules(event);
            errorMap.removeFromErrorPath(errorPath);
            i++;
        }
        errorMap.removeFromErrorPath(AWARD_ERROR_PATH);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        return valid;
    }


    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(
     * org.kuali.rice.krad.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document){
        boolean retval = true;
        
        retval &= new KcDocumentBaseAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardReportAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardTermsAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardPaymentAndInvoicesAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardCostShareAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardFandARateAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardProjectPersonsAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardPersonCreditSplitAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardSubawardAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardSyncAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardSponsorContactAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardBudgetLimitsAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardDetailsAndDatesAuditRule().processRunAuditBusinessRules(document);
        return retval;
        
        
    }
    
    /**
     * 
     * @see org.kuali.kra.award.commitments.AddFandaRateRule#
     * processAddFandaRateBusinessRules(
     * org.kuali.kra.award.commitments.AddAwardFandaRateEvent)
     */
    public boolean processAddFandaRateBusinessRules(AddAwardFandaRateEvent 
            addAwardFandaRateEvent) {        
        return new AwardFandaRateRule().processAddFandaRateBusinessRules(
                addAwardFandaRateEvent);            
    }

    @Override
    public boolean processSaveFandaRateBusinessRules(AwardFandaRateSaveEvent awardFandaRateSaveEvent) {
        return new AwardFandaRateRule().processSaveFandaRateBusinessRules(awardFandaRateSaveEvent);
    }

    public boolean processAwardReportTermBusinessRules(Document document) {
        AwardDocument awardDocument = (AwardDocument) document;
        AwardReportTerm awardReportTermItem = awardDocument.getAward().getAwardReportTermItems().isEmpty() ? null : awardDocument.getAward().getAwardReportTermItems().get(0);
        AwardReportTermRuleEvent event = new AwardReportTermRuleEvent(AWARD_ERROR_PATH_PREFIX, awardDocument, awardDocument.getAward(), awardReportTermItem);
        return processAwardReportTermBusinessRules(event);
    }
    
    public boolean processAwardReportTermSaveRules(AwardForm form) {
        boolean isValid = true;
        AwardForm awardForm = (AwardForm) form;
        int reportTrackingBeanscount=0;
        if(awardForm.getReportTrackingBeans()!=null && !(awardForm.getReportTrackingBeans().isEmpty())) {
            List<ReportTrackingBean> reportTrackingBeanList=awardForm.getReportTrackingBeans();
            for(ReportTrackingBean reportTrackingBeans : reportTrackingBeanList ) {                 
                if(reportTrackingBeans.getPreparerName()!=null) {
                    KcPerson preparerPerson = getKcPersonService().getKcPersonByUserName(reportTrackingBeans.getPreparerName());
                    if (preparerPerson == null ) {
                        isValid = false; 
                        GlobalVariables.getMessageMap().putError("reportTrackingBeans["+reportTrackingBeanscount+"].preparerName", "error.preparername.duplicate");
                    }
                }
                reportTrackingBeanscount++;
            }
            if(awardForm.getAwardDocument().getAward().getAwardReportTermItems()!=null) {
                List<AwardReportTerm> awardReportTrackingItemList=awardForm.getAwardDocument().getAward().getAwardReportTermItems();
                int awardReportTermItemscount=0;
                for(AwardReportTerm awardReportTrackingTerm : awardReportTrackingItemList ) {
                    if(awardReportTrackingTerm.getReportTrackings()!=null) {
                        List<ReportTracking> reportTrackingsList = awardReportTrackingTerm.getReportTrackings();
                        for (ReportTracking reportTrackingsItem : reportTrackingsList) {
                            if(reportTrackingsItem.getPreparerName() != null) {
                                KcPerson preparerPerson = getKcPersonService().getKcPersonByUserName(reportTrackingsItem.getPreparerName());
                                if (preparerPerson == null ) {
                                    isValid = false; 
                                    GlobalVariables.getMessageMap().putError("document.award.awardReportTermItems["+awardReportTermItemscount+"].reportTrackings[0].preparerName", "error.preparername.duplicate");
                                }
                            }
                        }
                    }
                    awardReportTermItemscount++;
                } 
            }
        }
        return isValid;
    }
    
    /**
     * 
     * @see org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRule#processAwardReportTermBusinessRules(
     *          org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRuleEvent)
     */
    public boolean processAwardReportTermBusinessRules(AwardReportTermRuleEvent event){
        return new AwardReportTermRuleImpl().processAwardReportTermBusinessRules(event);
    }
    
    protected boolean isValidReportCode(AwardReportTerm awardReportTerm, List<KeyValue> reportCodes){
        boolean isValid = false;
        for(KeyValue KeyValue:reportCodes){
            if(StringUtils.equalsIgnoreCase(KeyValue.getKey().toString(), 
                    awardReportTerm.getReportCode())) {
                isValid = true;                    
            }
        }        
        return isValid;    
    }

    protected boolean isValidFrequencyBase(
            AwardReportTerm awardReportTerm, List<KeyValue> frequencyBaseCodes){
        boolean isValid = false;
        
        for(KeyValue KeyValue:frequencyBaseCodes){
            if(StringUtils.equalsIgnoreCase(KeyValue.getKey().toString(), 
                    awardReportTerm.getFrequencyBaseCode())) {
                isValid = true;                    
            }
        }
        return isValid;
    }
    
    protected List<KeyValue> getReportCodes(String reportClassCode){
        ReportCodeValuesFinder reportCodeValuesFinder = new ReportCodeValuesFinder();
        reportCodeValuesFinder.setReportClassCode(reportClassCode);
        return reportCodeValuesFinder.getKeyValues();
    }
    
    protected List<KeyValue> getFrequencyBaseCodes(String frequencyCode){
        FrequencyBaseCodeValuesFinder frequencyBaseCodeValuesFinder
            = new FrequencyBaseCodeValuesFinder();        
        frequencyBaseCodeValuesFinder.setFrequencyCode(frequencyCode);        
        return frequencyBaseCodeValuesFinder.getKeyValues();
    }
    
    
    
    /**
     * 
     * @see org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRule#processAddAwardReportTermBusinessRules(
     *          org.kuali.kra.award.paymentreports.awardreports.AddAwardReportTermRuleEvent)
     */
    public boolean processAddAwardReportTermBusinessRules(AddAwardReportTermRuleEvent event){
        return new AwardReportTermRuleImpl().processAddAwardReportTermBusinessRules(event);
    }
    
    /**
     * 
     * @see org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipientRule#processAwardReportTermRecipientBusinessRules(
     *          org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipientRuleEvent)
     */
    public boolean processAwardReportTermRecipientBusinessRules(AwardReportTermRecipientRuleEvent event){
        return new AwardReportTermRecipientRuleImpl().processAwardReportTermRecipientBusinessRules(event);
    }
    
    /**
     * 
     * @see org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipientRule#processAddAwardReportTermRecipientBusinessRules(
     *          org.kuali.kra.award.paymentreports.awardreports.AddAwardReportTermRecipientRuleEvent)
     */
    public boolean processAddAwardReportTermRecipientBusinessRules(AddAwardReportTermRecipientRuleEvent event){
        return new AwardReportTermRecipientRuleImpl().processAddAwardReportTermRecipientBusinessRules(event);
    }
    
    private boolean processApprovedForeignTravelBusinessRules(MessageMap errorMap, AwardDocument awardDocument) {
        boolean success = true;
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(AWARD_ERROR_PATH);
        Award award = awardDocument.getAward();
        AwardApprovedForeignTravelRule rule = new AwardApprovedForeignTravelRuleImpl();
        int count = award.getApprovedForeignTravelTrips().size();
        for (int i = 0; i < count; i++) {
            String errorPath = String.format("approvedForeignTravelTrips[%d]", i);
            errorMap.addToErrorPath(errorPath);
            String errorKey = AWARD_ERROR_PATH_PREFIX + errorPath;
            AwardApprovedForeignTravelRuleEvent event = new AwardApprovedForeignTravelRuleEvent(errorKey, awardDocument, awardDocument.getAward(),
                        award.getApprovedForeignTravelTrips().get(i));
            success &= rule.processAwardApprovedForeignTravelBusinessRules(event);
            errorMap.removeFromErrorPath(errorPath);
        }
        errorMap.removeFromErrorPath(AWARD_ERROR_PATH);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        
        return success;
    }
    
    private boolean processSaveAwardProjectPersonsBusinessRules(MessageMap errorMap, AwardDocument document) {
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(AWARD_ERROR_PATH);
        SaveAwardProjectPersonsRuleEvent event = new SaveAwardProjectPersonsRuleEvent("Project Persons", "projectPersons", document);
        boolean success = new AwardProjectPersonsSaveRuleImpl().processSaveAwardProjectPersonsBusinessRules(event);
        errorMap.removeFromErrorPath(AWARD_ERROR_PATH);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        
        return success;
    }

    public boolean processAwardTemplateSyncRules(AwardTemplateSyncEvent awardTemplateSyncEvent) {
        return new AwardTemplateSyncRuleImpl().processAwardTemplateSyncRules(awardTemplateSyncEvent);
    }

    private boolean processUnitNumberBusinessRule(MessageMap errorMap, AwardDocument awardDocument) {
        Award award = awardDocument.getAward();
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(AWARD_ERROR_PATH);

        boolean success = award.getUnitNumber() != null && award.getUnit() != null;
        if(!success) {
            errorMap.putError("unitNumber", "error.award.unitNumber", award.getUnitNumber());    
        }

        errorMap.removeFromErrorPath(AWARD_ERROR_PATH);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        return success;
    }

    /*
     * This method is to add bus rule check proj beg date <= proj end date
     * and obligation end date >= obligation start date.
     */
    private boolean processDateBusinessRule(MessageMap errorMap, AwardDocument awardDocument) {
        
        boolean isTimeAndMoneyDocument = KNSGlobalVariables.getKualiForm() instanceof TimeAndMoneyForm;
        
        if (isTimeAndMoneyDocument) {
            TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) KNSGlobalVariables.getKualiForm();
            TimeAndMoneyAwardDateSaveEvent event = new TimeAndMoneyAwardDateSaveEvent("", timeAndMoneyForm.getTimeAndMoneyDocument());
            TimeAndMoneyAwardDateSaveRuleImpl rule = new TimeAndMoneyAwardDateSaveRuleImpl();
            boolean result = rule.processSaveAwardDatesBusinessRules(event);
            return result;
            
        } else {
            Award award = awardDocument.getAward();
            errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
            errorMap.addToErrorPath(AWARD_ERROR_PATH);
    
            boolean success = true;
            int lastIndex = award.getIndexOfLastAwardAmountInfo();
            // make sure start dates are before end dates
            Date effStartDate = award.getAwardEffectiveDate(); 
            Date effEndDate = award.getAwardAmountInfos().get(lastIndex).getFinalExpirationDate();
            String awardId = award.getAwardNumber();
            
            
            Date oblStartDate = award.getAwardAmountInfos().get(lastIndex).getCurrentFundEffectiveDate(); 
            Date oblEndDate = award.getAwardAmountInfos().get(lastIndex).getObligationExpirationDate();
            
            String fieldStarter = "awardAmountInfos["+lastIndex;
            //String fieldStarter = "awardHierarchyNodeItems["+(lastIndex-1);
            success = AwardDateRulesHelper.validateProjectStartBeforeProjectEnd(errorMap, effStartDate, effEndDate, fieldStarter+"].finalExpirationDate", awardId) && success;
            success = AwardDateRulesHelper.validateObligationStartBeforeObligationEnd(errorMap, oblStartDate, oblEndDate, fieldStarter+"].currentFundEffectiveDate", awardId) && success;
            success = AwardDateRulesHelper.validateProjectStartBeforeObligationStart(errorMap, effStartDate, oblStartDate, fieldStarter+"].currentFundEffectiveDate", awardId) && success;
            success = AwardDateRulesHelper.validateProjectStartBeforeObligationEnd(errorMap, effStartDate, oblEndDate, fieldStarter+"].obligationExpirationDate", awardId) && success;
            success = AwardDateRulesHelper.validateObligationStartBeforeProjectEnd(errorMap, oblStartDate, effEndDate, fieldStarter+"].currentFundEffectiveDate", awardId) && success;
            success = AwardDateRulesHelper.validateObligationEndBeforeProjectEnd(errorMap, oblEndDate, effEndDate, fieldStarter+"].obligationExpirationDate", awardId) && success;

            errorMap.removeFromErrorPath(AWARD_ERROR_PATH);
            errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
            return success;
        }
    }



    @Override
    public boolean processRules(KcDocumentEventBaseExtension event) {
        boolean retVal = false;
        retVal = event.getRule().processRules(event);
        return retVal;
    }

    @Override
    public boolean processsAddAttachmentRule(AddAwardAttachmentEvent event) {
        boolean valid = true;

        if( StringUtils.isBlank(event.getAwardAttachment().getTypeCode() )) {
            valid = false;
            LOG.debug(AWARD_ATTACHMENT_TYPE_CODE_REQUIRED);
            reportError("awardAttachmentFormBean.newAttachment.typeCode", AWARD_ATTACHMENT_TYPE_CODE_REQUIRED);
            
        }
        
        
        if( event.getAwardAttachment().getNewFile() == null || StringUtils.isEmpty(event.getAwardAttachment().getNewFile().getFileName()) ) {
            valid = false;
            LOG.debug(AWARD_ATTACHMENT_FILE_REQUIRED);
            reportError("awardAttachmentFormBean.newAttachment.newFile", AWARD_ATTACHMENT_FILE_REQUIRED);
        }
        
        return valid;
    }

    @Override
    public boolean processApplyModifiedAttachmentRule( AddAwardAttachmentEvent event) {
        if( StringUtils.isBlank(event.getAwardAttachment().getTypeCode() )) {
            LOG.debug(AWARD_ATTACHMENT_TYPE_CODE_REQUIRED);
            reportError(event.getErrorPathPrefix() + TYPE_CODE, AWARD_ATTACHMENT_TYPE_CODE_REQUIRED);
            return false;

        }
        return true;
    }

    protected GlobalVariableService getGlobalVariableService() {
        return KcServiceLocator.getService(GlobalVariableService.class);
    }

    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }
}
