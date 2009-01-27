/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.rules;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.document.Document;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.AwardApprovedSubaward;
import org.kuali.kra.award.bo.AwardCostShare;
import org.kuali.kra.award.bo.AwardFandaRate;
import org.kuali.kra.award.bo.AwardReportTerm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.lookup.keyvalue.FrequencyBaseCodeValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.FrequencyCodeValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.ReportCodeValuesFinder;
import org.kuali.kra.award.rule.AddAwardReportTermRecipientRule;
import org.kuali.kra.award.rule.AddAwardReportTermRule;
import org.kuali.kra.award.rule.AddFandaRateRule;
import org.kuali.kra.award.rule.AwardApprovedEquipmentRule;
import org.kuali.kra.award.rule.event.AddAwardFandaRateEvent;
import org.kuali.kra.award.rule.event.AddAwardReportTermEvent;
import org.kuali.kra.award.rule.event.AddAwardReportTermRecipientEvent;
import org.kuali.kra.award.rule.event.AwardApprovedEquipmentRuleEvent;
import org.kuali.kra.bo.AbstractSpecialReview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rule.SpecialReviewRule;
import org.kuali.kra.rule.event.AddSpecialReviewEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * Main Business Rule class for <code>{@link AwardDocument}</code>. 
 * Responsible for delegating rules to independent rule classes.
 *
 */
public class AwardDocumentRule extends ResearchDocumentRuleBase implements AwardApprovedEquipmentRule, AddFandaRateRule,SpecialReviewRule,AddAwardReportTermRule, AddAwardReportTermRecipientRule {
    
    public static final String DOCUMENT_ERROR_PATH = "document";
    public static final String AWARD_ERROR_PATH = "awardList[0]";
    public static final boolean VALIDATION_REQUIRED = true;
    public static final boolean CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME = false;
    private static final String NEW_SPECIAL_REVIEW = "newSpecialReview";
    private static final int ZERO = 0;
    
    /**
     * 
     * @see org.kuali.core.rules.DocumentRuleBase#processCustomRouteDocumentBusinessRules(
     * org.kuali.core.document.Document)
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        return super.processCustomRouteDocumentBusinessRules(document);
    }

    /**
     * 
     * @see org.kuali.core.rules.DocumentRuleBase#processCustomSaveDocumentBusinessRules(
     * org.kuali.core.document.Document)
     */
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        boolean retval = true;
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        if (!(document instanceof AwardDocument)) {
            return false;
        }
        
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        getDictionaryValidationService().validateDocumentAndUpdatableReferencesRecursively(
                document, getMaxDictionaryValidationDepth(),
                VALIDATION_REQUIRED, CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        
        AwardDocument awardDocument = (AwardDocument) document;
        
        retval &= processAwardFandaRateBusinessRules(document);
        retval &= processCostShareBusinessRules(document);
        retval &= processApprovedSubawardBusinessRules(document);
        retval &= processApprovedEquipmentBusinessRules(errorMap, awardDocument);
        retval &= processAwardReportTermBusinessRules(document);

        return retval;
    }
    
    private boolean processApprovedEquipmentBusinessRules(ErrorMap errorMap, AwardDocument awardDocument) {
        boolean success = true;
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(AWARD_ERROR_PATH);
        Award award = awardDocument.getAward();
        EquipmentCapitalizationMinimumLoader helper = new EquipmentCapitalizationMinimumLoader();
        int count = award.getApprovedEquipmentItems().size();
        for (int i = 0; i < count; i++) {
            String errorPath = String.format("approvedEquipmentItems[%d]", i);
            errorMap.addToErrorPath(errorPath);
            String errorKey = "document.awardList[0]." + errorPath;
            AwardApprovedEquipmentRuleEvent event = new AwardApprovedEquipmentRuleEvent(errorKey, awardDocument, 
                                                                                        award.getApprovedEquipmentItems().get(i),
                                                                                        helper.getMinimumCapitalization());
            success &= new AwardApprovedEquipmentRuleImpl().processAwardApprovedEquipmentBusinessRules(event);
            errorMap.removeFromErrorPath(errorPath);
        }
        errorMap.removeFromErrorPath(AWARD_ERROR_PATH);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        
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
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        AwardDocument awardDocument = (AwardDocument) document;
        int i = 0;
        List<AwardCostShare> awardCostShares = awardDocument.getAward().getAwardCostShares();
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(AWARD_ERROR_PATH);
        for (AwardCostShare awardCostShare : awardCostShares) {
            String errorPath = "awardCostShares[" + i + "]";
            errorMap.addToErrorPath(errorPath);
            valid = testCostShareSourceAndDestinationForEquality(awardCostShare, errorMap);
            valid = testCostShareFiscalYearRange(awardCostShare, errorMap);
            errorMap.removeFromErrorPath(errorPath);
            i++;
        }
        errorMap.removeFromErrorPath(AWARD_ERROR_PATH);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        return valid;
    }
    
    /**
    *
    * Test source and destination for equality in AwardCostShare.
    * @param AwardCostShare, ErrorMap
    * @return Boolean
    */
    public boolean testCostShareSourceAndDestinationForEquality(AwardCostShare awardCostShare, ErrorMap errorMap){
        boolean valid = true;
        if(awardCostShare.getSource().equals(awardCostShare.getDestination())) {
            valid = false;
            errorMap.putError("source", KeyConstants.ERROR_SOURCE_DESTINATION);
        }
        return valid;
    }
    
    /**
    *
    * Test fiscal year for valid range.
    * @param AwardCostShare, ErrorMap
    * @return Boolean
    */
    public boolean testCostShareFiscalYearRange(AwardCostShare awardCostShare, ErrorMap errorMap){
        boolean valid = true;
        int fiscalYear = Integer.parseInt(awardCostShare.getFiscalYear());
        if(fiscalYear < Constants.MIN_FISCAL_YEAR || fiscalYear > Constants.MAX_FISCAL_YEAR) {
            valid = false;
            errorMap.putError("fiscalYear", KeyConstants.ERROR_FISCAL_YEAR_RANGE);
        }
        return valid;
    }
    
    /**
    *
    * process ApprovedSubaward business rules.
    * @param awardDocument
    * @return
    */
    private boolean processApprovedSubawardBusinessRules(Document document) {
        boolean valid = true;
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        AwardDocument awardDocument = (AwardDocument) document;
        int i = 0;
        List<AwardApprovedSubaward> awardApprovedSubawards = awardDocument.getAward().getAwardApprovedSubawards();
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(AWARD_ERROR_PATH);
        for (AwardApprovedSubaward awardApprovedSubaward : awardApprovedSubawards) {
             String errorPath = "awardApprovedSubawards[" + i + "]";
             errorMap.addToErrorPath(errorPath);
             valid = testApprovedSubawardAmount(awardApprovedSubaward, errorMap);
             valid = testApprovedSubawardDuplicateOrganization(awardApprovedSubawards, awardApprovedSubaward, errorMap, i, errorPath);
             errorMap.removeFromErrorPath(errorPath);
             i++;
            }
        errorMap.removeFromErrorPath(AWARD_ERROR_PATH);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        return valid;
    }
    
    /**
    *
    * Test Approved Subawards for duplicate organizations
    * @param AwardApprovedSubaward, ErrorMap, List<AwardApprovedSubaward>, currentIndex
    * @return Boolean
    */
    public boolean testApprovedSubawardDuplicateOrganization(List<AwardApprovedSubaward> awardApprovedSubawards, AwardApprovedSubaward awardApprovedSubaward, ErrorMap errorMap, int currentIndex, String errorPath){
        boolean valid = true;
        int j = 0;
        for (AwardApprovedSubaward loopAwardApprovedSubaward : awardApprovedSubawards) {
            if (currentIndex != j){
                if(awardApprovedSubaward.getOrganizationName().equals(loopAwardApprovedSubaward.getOrganizationName())){
                    valid = false;
                    errorMap.putError("organizationName", KeyConstants.ERROR_DUPLICATE_ORGANIZATION_NAME);
                    errorMap.removeFromErrorPath(errorPath);
                    break;
                }
            }
            j++;
          }
        return valid;
       }
    
    /**
    *
    * Test Approved Subaward amount for zero and negative value.
    * @param AwardApprovedSubaward, ErrorMap
    * @return Boolean
    */
    public boolean testApprovedSubawardAmount(AwardApprovedSubaward awardApprovedSubaward, ErrorMap errorMap){
        boolean valid = true;
        int amount = awardApprovedSubaward.getAmount().intValue();
        if(amount <= ZERO) {
            valid = false;
            errorMap.putError("amount", KeyConstants.ERROR_AMOUNT_IS_ZERO);
        }
        return valid;
    }


    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(
     * org.kuali.core.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document){
        return super.processRunAuditBusinessRules(document);
    }
    
    /**
     * 
     * @see org.kuali.kra.award.rule.AddFandaRateRule#
     * processAddFandaRateBusinessRules(
     * org.kuali.kra.award.rule.event.AddAwardFandaRateEvent)
     */
    public boolean processAddFandaRateBusinessRules(AddAwardFandaRateEvent 
            addAwardFandaRateEvent) {        
        return new AwardFandaRateRule().processAddFandaRateBusinessRules(
                addAwardFandaRateEvent);            
    }    
    
    /**
     * 
     * This method evaluates the business rules for <code>AwardFandaRate</code>
     * business object.
     * @param document
     * @return
     */
    protected boolean processAwardFandaRateBusinessRules(Document document) {
        boolean retval = true;
        AwardDocument awardDocument = (AwardDocument) document;
        if(StringUtils.equalsIgnoreCase(
                getKualiConfigurationService().getParameter(Constants.PARAMETER_MODULE_AWARD, 
                        Constants.PARAMETER_COMPONENT_DOCUMENT,
                        KeyConstants.MIT_IDC_VALIDATION_ENABLED).getParameterValue(),
                        KeyConstants.MIT_IDC_VALIDATION_ENABLED_VALUE_FOR_COMPARISON)){
            retval = isFandaRateInputInPairs(awardDocument.getAward().getAwardFandaRate());
        }        
        return retval;
    }
    
    protected boolean processAwardReportTermBusinessRules(Document document) {
        boolean retval = true;
        
        int i=0;
        
        GlobalVariables.getErrorMap().addToErrorPath(DOCUMENT_ERROR_PATH);
        GlobalVariables.getErrorMap().addToErrorPath(AWARD_ERROR_PATH);        
        
        AwardDocument awardDocument = (AwardDocument) document;
        for(AwardReportTerm awardReportTerm: awardDocument.getAward().getAwardReportTerms()){
            
            retval = evaluateBusinessRuleForReportCodeField(awardReportTerm, i);
            retval = evaluateBusinessRuleForFrequencyCodeField(awardReportTerm, i);
            retval = evaluateBusinessRuleForFrequencyBaseCodeField(awardReportTerm, i);
            i++;
        }
        
        GlobalVariables.getErrorMap().removeFromErrorPath(AWARD_ERROR_PATH);
        GlobalVariables.getErrorMap().removeFromErrorPath(DOCUMENT_ERROR_PATH);
        
        return retval;
    }
    
    protected boolean evaluateBusinessRuleForReportCodeField(AwardReportTerm awardReportTerm, int index){
        boolean found = false;
        boolean retval = true;
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        ReportCodeValuesFinder reportCodeValuesFinder = new ReportCodeValuesFinder();
        reportCodeValuesFinder.setReportClassCode(awardReportTerm.getReportClassCode());
        for(KeyLabelPair keyLabelPair:reportCodeValuesFinder.getKeyValues()){
            if(StringUtils.equalsIgnoreCase(keyLabelPair.getKey().toString(), 
                    awardReportTerm.getReportCode())) {
                found = true;                    
            }
        }
        if(!found){
            retval = false;
            errorMap.putError("awardReportTerms[" + index + "].reportCode"
                    , KeyConstants.INVALID_REPORT_CODE_FOR_REPORT_CLASS);
        }
        return retval;
    }
    
    protected boolean evaluateBusinessRuleForFrequencyCodeField(AwardReportTerm awardReportTerm, int index){
        boolean found = false;
        boolean retval = true;
        ErrorMap errorMap = GlobalVariables.getErrorMap();        
        FrequencyCodeValuesFinder frequencyCodeValuesFinder = new FrequencyCodeValuesFinder();
        
        frequencyCodeValuesFinder.setReportClassCode(awardReportTerm.getReportClassCode());
        frequencyCodeValuesFinder.setReportCode(awardReportTerm.getReportCode());
        
        for(KeyLabelPair keyLabelPair:frequencyCodeValuesFinder.getKeyValues()){
            if(StringUtils.equalsIgnoreCase(keyLabelPair.getKey().toString(), 
                    awardReportTerm.getFrequencyCode())) {
                found = true;                    
            }
        }
        
        if(!found){
            retval = false;
            errorMap.putError("awardReportTerms[" + index + "].frequencyCode"
                    , KeyConstants.INVALID_FREQUENCY_FOR_REPORT_CLASS_AND_TYPE);
        }
        
        return retval;
    }
    
    
    protected boolean evaluateBusinessRuleForFrequencyBaseCodeField(
            AwardReportTerm awardReportTerm, int index){
        boolean found = false;
        boolean retval = true;
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        FrequencyBaseCodeValuesFinder frequencyBaseCodeValuesFinder = new FrequencyBaseCodeValuesFinder();
        frequencyBaseCodeValuesFinder.setFrequencyCode(awardReportTerm.getFrequencyCode());
                
        for(KeyLabelPair keyLabelPair:frequencyBaseCodeValuesFinder.getKeyValues()){
            if(StringUtils.equalsIgnoreCase(keyLabelPair.getKey().toString(), 
                    awardReportTerm.getFrequencyBaseCode())) {
                found = true;                    
            }
        }
        if(!found){
            retval = false;
            errorMap.putError("awardReportTerms[" + index + "].frequencyBaseCode"
                    , KeyConstants.INVALID_FREQUENCY_BASE_FOR_FREQUENCY);
        }
        
        return retval;
    }
    /**
     * 
     * @see org.kuali.kra.award.rule.AddAwardReportTermRule#processAddAwardReportTermEvent(
     * org.kuali.kra.award.rule.event.AddAwardReportTermEvent)
     */
    public boolean processAddAwardReportTermEvent(AddAwardReportTermEvent 
            addAwardReportTermEvent) {
        return new AwardReportTermRule().processAddAwardReportTermEvent(addAwardReportTermEvent);        
    }
    
    /**
     * 
     * @see org.kuali.kra.award.rule.AddAwardReportTermRecipientRule#processAddAwardReportTermRecipientEvent(
     * org.kuali.kra.award.rule.event.AddAwardReportTermRecipientEvent)
     */
    public boolean processAddAwardReportTermRecipientEvent(AddAwardReportTermRecipientEvent 
            addAwardReportTermRecipientEvent) {
        return new AwardReportTermRecipientRule().processAddAwardReportTermRecipientEvent(
                addAwardReportTermRecipientEvent);        
    }
    
    
    /**
     * 
     * @see org.kuali.core.rules.DocumentRuleBase#getKualiConfigurationService()
     */
    protected KualiConfigurationService getKualiConfigurationService(){
        return KraServiceLocator.getService(KualiConfigurationService.class);
    }
    
    /**
     * 
     * This method takes as the input a list of <code>AwardFandaRate</code>,
     * iterates through it twice to find out whether both on campus and off campus entries
     * are present for every indirectRateTypeCode. 
     * Returns true if they both are present.
     * @param awardFandaRateList
     * @return
     */
    protected boolean isFandaRateInputInPairs(List<AwardFandaRate> awardFandaRateList){
        boolean valid = true;
        HashMap<Integer,String> a1 = new HashMap<Integer,String>();
        HashMap<Integer,String> b1 = new HashMap<Integer,String>();        
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        int i=0;
        
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(AWARD_ERROR_PATH);
        
        for(AwardFandaRate awardFandaRate : awardFandaRateList){
            if(StringUtils.equalsIgnoreCase(awardFandaRate.getOnCampusFlag(),"N")){
                a1.put(awardFandaRate.getFandaRateTypeCode(), awardFandaRate.getOnCampusFlag());
            }else if(StringUtils.equalsIgnoreCase(awardFandaRate.getOnCampusFlag(),"F")){
                b1.put(awardFandaRate.getFandaRateTypeCode(), awardFandaRate.getOnCampusFlag());
            }
        }
        for(AwardFandaRate awardFandaRate : awardFandaRateList){            
            if((a1.containsKey(awardFandaRate.getFandaRateTypeCode()) 
                    && !b1.containsKey(awardFandaRate.getFandaRateTypeCode()))
                    ||(b1.containsKey(awardFandaRate.getFandaRateTypeCode()) 
                            && !a1.containsKey(awardFandaRate.getFandaRateTypeCode()))){                
                errorMap.putError("awardFandaRate[" + i + "].fandaRateTypeCode"
                        , KeyConstants.INDIRECT_COST_RATE_NOT_IN_PAIR);
                valid = false;
            }
            i++;
        }
        errorMap.removeFromErrorPath(AWARD_ERROR_PATH);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        return valid;        
    }

    /**
     * Error upon add - 
     * 1.  Select a special review type
     * 2.  Select an approval status
     * 3.  Approval Date should be later than Application Date
     */
    public boolean processAddSpecialReviewEvent(AddSpecialReviewEvent addSpecialReviewEvent) {
        AbstractSpecialReview specialReview = addSpecialReviewEvent.getSpecialReview();
        Document document = addSpecialReviewEvent.getDocument();
//        ProposalSpecialReview proposalSpecialReview = addProposalSpecialReviewEvent.getProposalSpecialReview();
        boolean rulePassed = true;
        String errorPath = NEW_SPECIAL_REVIEW;
        String[] dateParams = {"Approval Date","Application Date"};

        if(StringUtils.isBlank(specialReview.getApprovalTypeCode())){
            rulePassed = false;
            reportError(errorPath+".approvalTypeCode", KeyConstants.ERROR_REQUIRED_SELECT_APPROVAL_STATUS);
        }
        if(StringUtils.isBlank(specialReview.getSpecialReviewCode())){
            rulePassed = false;
            reportError(errorPath+".specialReviewCode", KeyConstants.ERROR_REQUIRED_SELECT_SPECIAL_REVIEW_CODE);
        }
        if (specialReview.getApplicationDate() !=null && specialReview.getApprovalDate() != null && 
                specialReview.getApprovalDate().before(specialReview.getApplicationDate())) {
            rulePassed = false;
            reportError(errorPath+".approvalDate", KeyConstants.ERROR_APPROVAL_DATE_BEFORE_APPLICATION_DATE_SPECIALREVIEW,dateParams);
        }

        return rulePassed;
    }

    public boolean processAwardApprovedEquipmentBusinessRules(AwardApprovedEquipmentRuleEvent awardApprovedEquipmentRuleEvent) {
        return new AwardApprovedEquipmentRuleImpl().processAwardApprovedEquipmentBusinessRules(awardApprovedEquipmentRuleEvent);
    }
}
