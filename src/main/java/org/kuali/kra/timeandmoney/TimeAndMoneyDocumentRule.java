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
package org.kuali.kra.timeandmoney;

import java.util.List;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistribution;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistributionRule;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistributionRuleEvent;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistributionRuleImpl;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.transactions.AddTransactionRuleEvent;
import org.kuali.kra.timeandmoney.transactions.TransactionRule;
import org.kuali.kra.timeandmoney.transactions.TransactionRuleEvent;
import org.kuali.kra.timeandmoney.transactions.TransactionRuleImpl;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Main Business Rule class for <code>{@link TimeAndMoneyDocument}</code>. 
 * Responsible for delegating rules to independent rule classes.
 *
 */
public class TimeAndMoneyDocumentRule extends ResearchDocumentRuleBase implements TransactionRule, AwardDirectFandADistributionRule {
    
    public static final String DOCUMENT_ERROR_PATH = "document";
    public static final boolean VALIDATION_REQUIRED = true;
    public static final boolean CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME = false;
    public static final String AWARD_ERROR_PATH = "award";


    /**
     * 
     * @see org.kuali.core.rules.DocumentRuleBase#processCustomSaveDocumentBusinessRules(
     * org.kuali.rice.kns.document.Document)
     */
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        boolean retval = true;
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        if (!(document instanceof TimeAndMoneyDocument)) {
            return false;
        }
        
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        getDictionaryValidationService().validateDocumentAndUpdatableReferencesRecursively(
                document, getMaxDictionaryValidationDepth(),
                VALIDATION_REQUIRED, CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        retval &= processAwardDirectFandADistributionBusinessRules(document);
        return retval;
    }
    
    /**
    *
    * process Direct F and A Distribution business rules.
    * @param awardDocument
    * @return
    */
    private boolean processAwardDirectFandADistributionBusinessRules(Document document) {
        boolean valid = true;
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        TimeAndMoneyDocument timeAndMoneyDocument = (TimeAndMoneyDocument) document;
        int i = 0;
        List<AwardDirectFandADistribution> awardDirectFandADistributions = timeAndMoneyDocument.getAward().getAwardDirectFandADistributions();
        errorMap.addToErrorPath(DOCUMENT_ERROR_PATH);
        errorMap.addToErrorPath(AWARD_ERROR_PATH);
        String errorPath = "awardDirectFandADistribution[" + i + Constants.RIGHT_SQUARE_BRACKET;
        errorMap.addToErrorPath(errorPath);
        AwardDirectFandADistributionRuleEvent event = new AwardDirectFandADistributionRuleEvent(errorPath, 
                                                            timeAndMoneyDocument, 
                                                                   awardDirectFandADistributions);
        valid &= new AwardDirectFandADistributionRuleImpl().processAwardDirectFandADistributionBusinessRules(event);
        errorMap.removeFromErrorPath(errorPath);
        errorMap.removeFromErrorPath(AWARD_ERROR_PATH);
        errorMap.removeFromErrorPath(DOCUMENT_ERROR_PATH);
        return valid;
    }


    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(
     * org.kuali.rice.kns.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document){
        boolean retval = true;
        
        
        return retval;
        
        
    }


    public boolean processAddPendingTransactionBusinessRules(AddTransactionRuleEvent event) {
        return new TransactionRuleImpl().processAddPendingTransactionBusinessRules(event);
    }


    public boolean processPendingTransactionBusinessRules(TransactionRuleEvent event) {
        return new TransactionRuleImpl().processPendingTransactionBusinessRules(event);
    }

    /**
     * @see org.kuali.kra.award.detailsdates.AwardDetailsAndDatesRule#processAddAwardTransferringSponsorEvent
     * (org.kuali.kra.award.rule.event.AddAwardTransferringSponsorEvent)
     */
    public boolean processAddAwardDirectFandADistributionBusinessRules(AwardDirectFandADistributionRuleEvent 
                                                                                        awardDirectFandADistributionRuleEvent) {
        return new AwardDirectFandADistributionRuleImpl().processAddAwardDirectFandADistributionBusinessRules(awardDirectFandADistributionRuleEvent);
    }

    public boolean processAwardDirectFandADistributionBusinessRules(
            AwardDirectFandADistributionRuleEvent awardDirectFandADistributionRuleEvent) {
        return new AwardDirectFandADistributionRuleImpl().processAwardDirectFandADistributionBusinessRules(awardDirectFandADistributionRuleEvent);
    }
    
}
