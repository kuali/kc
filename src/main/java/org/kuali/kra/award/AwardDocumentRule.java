/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award;

import static org.kuali.kra.infrastructure.KeyConstants.AWARD_ATTACHMENT_FILE_REQUIRED;
import static org.kuali.kra.infrastructure.KeyConstants.AWARD_ATTACHMENT_TYPE_CODE_REQUIRED;

import java.sql.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.commitments.AddAwardFandaRateEvent;
import org.kuali.kra.award.commitments.AddFandaRateRule;
import org.kuali.kra.award.commitments.AwardBenefitsRatesRuleEvent;
import org.kuali.kra.award.commitments.AwardBenefitsRatesRuleImpl;
import org.kuali.kra.award.commitments.AwardCostShare;
import org.kuali.kra.award.commitments.AwardCostShareAuditRule;
import org.kuali.kra.award.commitments.AwardCostShareRuleEvent;
import org.kuali.kra.award.commitments.AwardCostShareRuleImpl;
import org.kuali.kra.award.commitments.AwardFandARateAuditRule;
import org.kuali.kra.award.commitments.AwardFandaRateRule;
import org.kuali.kra.award.commitments.AwardFandaRateSaveEvent;
import org.kuali.kra.award.commitments.AwardFandaRateSaveRule;
import org.kuali.kra.award.contacts.AwardPersonCreditSplitAuditRule;
import org.kuali.kra.award.contacts.AwardProjectPersonsAuditRule;
import org.kuali.kra.award.contacts.AwardProjectPersonsSaveRule;
import org.kuali.kra.award.contacts.AwardProjectPersonsSaveRuleImpl;
import org.kuali.kra.award.contacts.AwardSponsorContactAuditRule;
import org.kuali.kra.award.contacts.SaveAwardProjectPersonsRuleEvent;
import org.kuali.kra.award.customdata.AwardCustomDataRuleImpl;
import org.kuali.kra.award.customdata.AwardSaveCustomDataRuleEvent;
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
import org.kuali.kra.award.lookup.keyvalue.FrequencyCodeValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.ReportCodeValuesFinder;
import org.kuali.kra.award.paymentreports.awardreports.AddAwardReportTermRecipientRuleEvent;
import org.kuali.kra.award.paymentreports.awardreports.AddAwardReportTermRuleEvent;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipientRule;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipientRuleEvent;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipientRuleImpl;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRule;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRuleEvent;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRuleImpl;
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
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.common.permissions.bo.PermissionsUser;
import org.kuali.kra.common.permissions.bo.PermissionsUserEditRoles;
import org.kuali.kra.common.permissions.rule.PermissionsRule;
import org.kuali.kra.common.permissions.web.bean.User;
import org.kuali.kra.common.specialreview.rule.event.SaveSpecialReviewEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.kra.rule.event.SaveCustomAttributeEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;



/**
 * Main Business Rule class for <code>{@link AwardDocument}</code>. 
 * Responsible for delegating rules to independent rule classes.
 *
 */
public class AwardDocumentRule extends ResearchDocumentRuleBase implements AwardPaymentScheduleRule, 
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
                                                                            BusinessRuleInterface,
                                                                            AddAwardAttachmentRule {
    
    public static final String DOCUMENT_ERROR_PATH = "document";
    public static final String AWARD_ERROR_PATH = "awardList[0]";
    public static final boolean VALIDATION_REQUIRED = true;
    public static final boolean CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME = false;
    private static final String AWARD_REPORT_TERMS = "awardReportTerms";
    private static final String AWARD_REPORT_TERM_ITEMS = "awardReportTermItems";
    private static final String AWARD_ERROR_PATH_PREFIX = "document.awardList[0].";
    private static final String SAVE_SPECIAL_REVIEW_FIELD = "document.awardList[0].specialReviews";
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(AwardDocumentRule.class);

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
     * @see org.kuali.kra.common.permissions.rule.PermissionsRule#processAddPermissionsUserBusinessRules(
     * org.kuali.core.document.Document, java.util.List, org.kuali.kra.common.permissions.bo.PermissionsUser)
     */
    public boolean processAddPermissionsUserBusinessRules(Document document, List<User> users, PermissionsUser newUser) {
        return new AwardPermissionsRule().processAddPermissionsUserBusinessRules(document, users, newUser);
    }
    
    /**
     * 
     * @see org.kuali.kra.common.permissions.rule.PermissionsRule#processDeletePermissionsUserBusinessRules(
     * org.kuali.core.document.Document, java.util.List, int)
     */
    public boolean processDeletePermissionsUserBusinessRules(Document document, List<User> users, int index) {
        return new AwardPermissionsRule().processDeletePermissionsUserBusinessRules(document, users, index);     
    }
    
    /**
     * 
     * @see org.kuali.kra.common.permissions.rule.PermissionsRule#processEditPermissionsUserRolesBusinessRules(
     * org.kuali.core.document.Document, java.util.List, org.kuali.kra.common.permissions.bo.PermissionsUserEditRoles)
     */
    public boolean processEditPermissionsUserRolesBusinessRules(Document document, List<User> users,
            PermissionsUserEditRoles editRoles) {
        return new AwardPermissionsRule().processEditPermissionsUserRolesBusinessRules(document, users, editRoles);
    }
    
    /**
     * @see org.kuali.kra.award.detailsdates.AwardDetailsAndDatesRule#processSaveAwardDetailsAndDates(org.kuali.kra.award.detailsdates.AwardDetailsAndDatesSaveEvent)
     */
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
        
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        getKnsDictionaryValidationService().validateDocumentAndUpdatableReferencesRecursively(
                document, getMaxDictionaryValidationDepth(),
                VALIDATION_REQUIRED, CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        
        AwardDocument awardDocument = (AwardDocument) document;

        retval &= processUnitNumberBusinessRule(errorMap, awardDocument);
        retval &= processCostShareBusinessRules(document);
        retval &= processBenefitsRatesBusinessRules(document);
        retval &= processApprovedSubawardBusinessRules(document);
        retval &= processApprovedEquipmentBusinessRules(errorMap, awardDocument);
        retval &= processApprovedForeignTravelBusinessRules(errorMap, awardDocument);
//        retval &= processAwardReportTermBusinessRules(document);
        retval &= processSaveAwardProjectPersonsBusinessRules(errorMap, awardDocument);
        retval &= processSaveAwardCustomDataBusinessRules(awardDocument);
        retval &= processAwardCommentsBusinessRules(awardDocument);
        retval &= processSpecialReviewBusinessRule(document);
        retval &= processAwardDetailsAndDatesSaveRules(document);
        retval &= processDateBusinessRule(errorMap, awardDocument);
        retval &=processKeywordBusinessRule(awardDocument);
        
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
    *
    * process save Custom Data Business Rules.
    * @param awardDocument
    * @return
    */
    private boolean processSaveAwardCustomDataBusinessRules(Document document) {
        boolean valid = true;
        
        AwardDocument awardDocument = (AwardDocument) document;
        
        valid &= processRules(new SaveCustomAttributeEvent(Constants.EMPTY_STRING, awardDocument));
        
        MessageMap errorMap = GlobalVariables.getMessageMap();
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(AWARD_ERROR_PATH);
        String errorPath = "awardCustomData";
        errorMap.addToErrorPath(errorPath);
        AwardSaveCustomDataRuleEvent event = new AwardSaveCustomDataRuleEvent(errorPath, 
                                                               awardDocument);
        valid &= new AwardCustomDataRuleImpl().processSaveAwardCustomDataBusinessRules(event);
        errorMap.removeFromErrorPath(errorPath);
        errorMap.removeFromErrorPath(AWARD_ERROR_PATH);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        return valid;
    }
    
    /**
     * This method validates 'Proposal Special review'. It checks
     * validSpecialReviewApproval table, and if there is a match, then checks
     * protocalnumberflag, applicationdateflag, and approvaldataflag.
     *
     * @paramDocument : The awardDocument that is being validated
     * @return valid Does the validation pass
     */
    private boolean processSpecialReviewBusinessRule(Document document) {
        AwardDocument awardDocument = (AwardDocument) document;
        List<AwardSpecialReview> specialReviews = awardDocument.getAward().getSpecialReviews();
        boolean isProtocolLinkingEnabled = getParameterService().getParameterValueAsBoolean("KC-PROTOCOL", "Document", "irb.protocol.award.linking.enabled");
        return processRules(new SaveSpecialReviewEvent<AwardSpecialReview>(
            SAVE_SPECIAL_REVIEW_FIELD, document, specialReviews, isProtocolLinkingEnabled));
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
        
        retval &= new AwardReportAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardTermsAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardCustomDataAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardPaymentAndInvoicesAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardCostShareAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardFandARateAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardProjectPersonsAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardPersonCreditSplitAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardSubawardAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardSyncAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardSponsorContactAuditRule().processRunAuditBusinessRules(document);
        retval &= new AwardBudgetLimitsAuditRule().processRunAuditBusinessRules(document);
         
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
    
    public boolean processSaveFandaRateBusinessRules(AwardFandaRateSaveEvent awardFandaRateSaveEvent) {
        return new AwardFandaRateRule().processSaveFandaRateBusinessRules(awardFandaRateSaveEvent);
    }
    
    /**
     * 
     * This method...
     * @param document
     * @return
     */
    public boolean processAwardReportTermBusinessRules(Document document) {
        AwardDocument awardDocument = (AwardDocument) document;
        AwardReportTerm awardReportTermItem = awardDocument.getAward().getAwardReportTermItems().isEmpty() ? null : awardDocument.getAward().getAwardReportTermItems().get(0);
        AwardReportTermRuleEvent event = new AwardReportTermRuleEvent(AWARD_ERROR_PATH_PREFIX, awardDocument, awardDocument.getAward(), awardReportTermItem);
        return processAwardReportTermBusinessRules(event);
    }
    
    /**
     * 
     * @see org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRule#processAwardReportTermBusinessRules(
     *          org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRuleEvent)
     */
    public boolean processAwardReportTermBusinessRules(AwardReportTermRuleEvent event){
        return new AwardReportTermRuleImpl().processAwardReportTermBusinessRules(event);
    }
    
    
    protected boolean evaluateBusinessRuleForReportCodeField(AwardReportTerm awardReportTerm, int index){
        boolean retval = isValidReportCode(awardReportTerm, getReportCodes(awardReportTerm.getReportClassCode()));
        if(!retval){            
            GlobalVariables.getMessageMap().putError(AWARD_REPORT_TERM_ITEMS + Constants.LEFT_SQUARE_BRACKET + index
                    + Constants.RIGHT_SQUARE_BRACKET + ".reportCode"
                    , KeyConstants.INVALID_REPORT_CODE_FOR_REPORT_CLASS);            
        }
        return retval;        
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
    
    protected boolean evaluateBusinessRuleForFrequencyCodeField(AwardReportTerm awardReportTerm, int index){
        boolean retval = isValidFrequency(awardReportTerm,getFrequencyCodes(
                awardReportTerm.getReportClassCode(),awardReportTerm.getReportCode()));
        if(!retval){
            GlobalVariables.getMessageMap().putError(AWARD_REPORT_TERM_ITEMS + Constants.LEFT_SQUARE_BRACKET + index
                    + Constants.RIGHT_SQUARE_BRACKET + ".frequencyCode"
                    , KeyConstants.INVALID_FREQUENCY_FOR_REPORT_CLASS_AND_TYPE);            
        }
        return retval;
    }
    
    protected boolean isValidFrequency(
            AwardReportTerm awardReportTerm, List<KeyValue> frequencyCodes){
        boolean isValid = false;
        for(KeyValue KeyValue:frequencyCodes){
            if(StringUtils.equalsIgnoreCase(KeyValue.getKey().toString(), 
                    awardReportTerm.getFrequencyCode())) {
                isValid = true;                    
            }
        }
        return isValid;
    }
    
    protected boolean evaluateBusinessRuleForFrequencyBaseCodeField(
            AwardReportTerm awardReportTerm, int index){
        boolean retval = isValidFrequencyBase(awardReportTerm, getFrequencyBaseCodes(
                awardReportTerm.getFrequencyCode()));
        if(!retval){
            GlobalVariables.getMessageMap().putError(AWARD_REPORT_TERM_ITEMS + Constants.LEFT_SQUARE_BRACKET + index
                    + Constants.RIGHT_SQUARE_BRACKET + ".frequencyBaseCode"
                    , KeyConstants.INVALID_FREQUENCY_BASE_FOR_FREQUENCY);                        
        }
        return retval;
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

    /**
     * This method checks that each of the report term's recipients has a name/organization
     * @param awardReportTerm
     * @param index
     * @return
     */
    protected boolean evaluateBusinessRuleForRecipients(AwardReportTerm awardReportTerm, int index) {
        boolean isValid = true;

        List<AwardReportTermRecipient> recipients = awardReportTerm.getAwardReportTermRecipients();
        for (int i=0; i<recipients.size(); i++) {
            AwardReportTermRecipient recipient = recipients.get(i);
            if (recipient.getRolodexId() == null) {
                GlobalVariables.getMessageMap().putError(AWARD_REPORT_TERM_ITEMS + Constants.LEFT_SQUARE_BRACKET + index
                        + Constants.RIGHT_SQUARE_BRACKET
                        + ".awardReportTermRecipients" + Constants.LEFT_SQUARE_BRACKET + i + Constants.RIGHT_SQUARE_BRACKET + ".rolodexId" 
                        , KeyConstants.ERROR_REQUIRED_ORGANIZATION, new Integer(i+1).toString());
                isValid = false;
            }
        }
        
        return isValid;
    }
    
    protected List<KeyValue> getReportCodes(String reportClassCode){
        ReportCodeValuesFinder reportCodeValuesFinder = new ReportCodeValuesFinder();
        reportCodeValuesFinder.setReportClassCode(reportClassCode);
        return reportCodeValuesFinder.getKeyValues();
    }
    
    protected List<KeyValue> getFrequencyCodes(String reportClassCode, String reportCode){
        FrequencyCodeValuesFinder frequencyCodeValuesFinder 
        = new FrequencyCodeValuesFinder(reportClassCode, reportCode);
        return frequencyCodeValuesFinder.getKeyValues();
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
    
    /**
     * 
     * This method...
     * @param addAwardReportTermRecipientRuleEvent
     * @return
     */
    public boolean processAddAwardReportTermRecipientEvent(AddAwardReportTermRecipientRuleEvent 
            addAwardReportTermRecipientRuleEvent) {
        return new AwardReportTermRecipientRuleImpl().processAddAwardReportTermRecipientBusinessRules(addAwardReportTermRecipientRuleEvent);        
    }
    
    /**
     * 
     * This method...
     * @param event
     * @return
     
    public boolean processAwardReportTermEvent(AwardReportTermRuleEvent event){
        return new AwardReportTermRuleImpl().processAwardReportTermBusinessRules(event);
    }*/
    
    /**
     * 
     * This method...
     * @param event
     * @return
     */
    public boolean processAwardReportTermRecipientEvent(AwardReportTermRecipientRuleEvent event){
        return new AwardReportTermRecipientRuleImpl().processAwardReportTermRecipientBusinessRules(event);
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
        Award award = awardDocument.getAward();
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(AWARD_ERROR_PATH);

        boolean success = true;
        int lastIndex = award.getIndexOfLastAwardAmountInfo();
        // make sure start dates are before end dates
        Date effStartDate = award.getAwardEffectiveDate(); 
        Date effEndDate = award.getAwardAmountInfos().get(lastIndex).getFinalExpirationDate();
        
        // make sure Project Start Date <= Obligation Start Date <= Obligation End Date <= Project Start Date
        if (effStartDate != null && effEndDate != null && effStartDate.after(effEndDate))  {
            success = false;
            errorMap.putError("awardAmountInfos["+lastIndex+"].finalExpirationDate", KeyConstants.ERROR_START_DATE_ON_OR_BEFORE,
                    new String[] {"Project Start Date", "Project End Date"});
        }
        Date oblStartDate = award.getAwardAmountInfos().get(lastIndex).getCurrentFundEffectiveDate(); 
        Date oblEndDate = award.getAwardAmountInfos().get(lastIndex).getObligationExpirationDate();
        if (oblStartDate != null && oblEndDate != null && oblStartDate.after(oblEndDate)) {
            success = false;
            errorMap.putError("awardAmountInfos["+lastIndex+"].obligationExpirationDate", KeyConstants.ERROR_START_DATE_ON_OR_BEFORE,
                    new String[] {"Obligation Start Date", "Obligation End Date"});
        }
        // make sure obligation dates are within effective dates
        if (oblStartDate != null && effStartDate != null && oblStartDate.before(effStartDate)) {
            success = false;
            errorMap.putError("awardAmountInfos["+lastIndex+"].currentFundEffectiveDate", KeyConstants.ERROR_START_DATE_ON_OR_BEFORE,
                    new String[] {"Project Start Date","Obligation Start Date"});
        }
        if (oblEndDate != null && effStartDate != null && oblEndDate.before(effStartDate)) {
            success = false;
            errorMap.putError("awardAmountInfos["+lastIndex+"].obligationExpirationDate", KeyConstants.ERROR_START_DATE_ON_OR_BEFORE,
                    new String[] {"Project Start Date","Obligation End Date"});
        }
        if (oblStartDate != null && effEndDate != null && oblStartDate.after(effEndDate)) {
            success = false;
            errorMap.putError("awardAmountInfos["+lastIndex+"].currentFundEffectiveDate", KeyConstants.ERROR_START_DATE_ON_OR_BEFORE,
                    new String[] {"Obligation Start Date", "Project End Date"});
        }
        if (oblEndDate != null && effEndDate != null && oblEndDate.after(effEndDate)) {
            success = false;
            errorMap.putError("awardAmountInfos["+lastIndex+"].obligationExpirationDate", KeyConstants.ERROR_START_DATE_ON_OR_BEFORE,
                    new String[] {"Obligation End Date", "Project End Date"});
        }

        errorMap.removeFromErrorPath(AWARD_ERROR_PATH);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        return success;
    }

    /**
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(KraDocumentEventBaseExtension event) {
        boolean retVal = false;
        retVal = event.getRule().processRules(event);
        return retVal;
    }

    /**
     * @see org.kuali.kra.award.rule.AddAwardAttachmentRule#processsAddAttachmentRule(org.kuali.kra.award.rule.event.AddAwardAttachmentEvent)
     */
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
}
