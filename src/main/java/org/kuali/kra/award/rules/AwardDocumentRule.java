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
import org.kuali.kra.award.bo.AwardCostShare;
import org.kuali.kra.award.bo.AwardFandaRate;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.rule.AddAwardReportTermRecipientRule;
import org.kuali.kra.award.rule.AddAwardReportTermRule;
import org.kuali.kra.award.rule.AddFandaRateRule;
import org.kuali.kra.award.rule.event.AddAwardFandaRateEvent;
import org.kuali.kra.award.rule.event.AddAwardReportTermEvent;
import org.kuali.kra.award.rule.event.AddAwardReportTermRecipientEvent;
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
public class AwardDocumentRule extends ResearchDocumentRuleBase implements AddFandaRateRule,SpecialReviewRule,AddAwardReportTermRule, AddAwardReportTermRecipientRule {
    
    public static final String DOCUMENT_ERROR_PATH = "document";
    public static final String AWARD_ERROR_PATH = "awardList[0]";
    public static final boolean VALIDATION_REQUIRED = true;
    public static final boolean CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME = false;
    private static final String NEW_SPECIAL_REVIEW = "newSpecialReview";
    private static final String NEW_AWARD_FANDA_RATE = "newAwardFandaRate";
    
    
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
        
        retval &= processAwardFandaRateBusinessRules(document);
        retval &= processCostShareBusinessRules(document);

        return retval;
    }
    
    /**
    *
    * process Cost Share business rules.
    * @param awardDocument
    * @return
    */
    private boolean processCostShareBusinessRules(Document document) {
        boolean valid = true;

        //checkErrors();
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        AwardDocument awardDocument = (AwardDocument) document;
        int i = 0;
        List<AwardCostShare> awardCostShares = awardDocument.getAward().getAwardCostShares();
        GlobalVariables.getErrorMap().addToErrorPath("award");
        for (AwardCostShare awardCostShare : awardCostShares) {
            String errorPath = "awardCostShares[" + i + "]";
            errorMap.addToErrorPath(errorPath);
            int fiscalYear = Integer.parseInt(awardCostShare.getFiscalYear());//get the integer value of Fiscal Year.
            //test for equality of source and destination
            if(awardCostShare.getSource().equals(awardCostShare.getDestination())) {
                 valid = false;
                 errorMap.putError("source", KeyConstants.ERROR_SOURCE_DESTINATION);
             }
            //test valid fiscal year range.
            if(fiscalYear < 1900 || fiscalYear > 2499) {
                valid = false;
                errorMap.putError("fiscalYear", KeyConstants.ERROR_FISCAL_YEAR_RANGE);
            }
            errorMap.removeFromErrorPath(errorPath);
            i++;
        }
        errorMap.removeFromErrorPath("award");
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
        return new AwardReportTermRule().processAddAwardReportTermRecipientEvent(addAwardReportTermRecipientEvent);        
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
                return false;
            }
            i++;
        }
        return true;        
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
}
