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

import org.apache.commons.lang.StringUtils;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.award.bo.AwardReportTerms;
import org.kuali.kra.award.rule.AddAwardReportTermRecipientRule;
import org.kuali.kra.award.rule.AddAwardReportTermRule;
import org.kuali.kra.award.rule.event.AddAwardReportTermEvent;
import org.kuali.kra.award.rule.event.AddAwardReportTermRecipientEvent;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * 
 * This class represents the AwardFandaRateRule
 */
public class AwardReportTermRule extends ResearchDocumentRuleBase implements AddAwardReportTermRule, AddAwardReportTermRecipientRule {
    
    private static final String NEW_AWARD_REPORT_TERMS = "newAwardReportTerms";
    private static final String NEW_AWARD_REPORT_TERMS_RECIPIENTS = "newAwardReportTermsRecipients";
    
    /**
     * 
     * @see org.kuali.kra.award.rule.AddAwardReportTermRule#processAddAwardReportTermEvent(
     * org.kuali.kra.award.rule.event.AddAwardReportTermEvent)
     */
    public boolean processAddAwardReportTermEvent(AddAwardReportTermEvent 
            addAwardReportTermEvent) {        
        AwardReportTerms awardReportTerms = 
            addAwardReportTermEvent.getAwardReportTerms();
        AwardForm awardForm = (AwardForm) GlobalVariables.getKualiForm();
        boolean rulePassed = true;
        
        rulePassed &= evaluateRuleForReportCode(awardReportTerms,awardForm.getAwardReportTermPanelNumber());
        rulePassed &= evaluateRuleForFrequency(awardReportTerms,awardForm.getAwardReportTermPanelNumber());
        rulePassed &= evaluateRuleForFrequencyBase(awardReportTerms,awardForm.getAwardReportTermPanelNumber());
        rulePassed &= evaluateRuleForDistribution(awardReportTerms,awardForm.getAwardReportTermPanelNumber());
        rulePassed &= evaluateRuleForDueDate(awardReportTerms,awardForm.getAwardReportTermPanelNumber());

        return rulePassed;            
    }    
    
    /**
     * 
     * @see org.kuali.kra.award.rule.AddAwardReportTermRecipientRule#processAddAwardReportTermRecipientEvent(
     * org.kuali.kra.award.rule.event.AddAwardReportTermRecipientEvent)
     */
    public boolean processAddAwardReportTermRecipientEvent(AddAwardReportTermRecipientEvent addAwardReportTermRecipientEvent) {
        
        AwardReportTerms awardReportTerms = 
            addAwardReportTermRecipientEvent.getAwardReportTerms();
        AwardForm awardForm = (AwardForm) GlobalVariables.getKualiForm();
        boolean rulePassed = true;
        
        rulePassed &= evaluateRuleForContactType(awardReportTerms,awardForm.getAwardReportTermPanelNumber());
        rulePassed &= evaluateRuleForRolodex(awardReportTerms,awardForm.getAwardReportTermPanelNumber());

        return rulePassed;        
        
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for reportCode field.
     * @param awardReportTerms
     * @return
     */
    protected boolean evaluateRuleForReportCode(AwardReportTerms awardReportTerms, String index){
        boolean rulePassed = true;
        if(awardReportTerms.getReportCode() == null 
                || StringUtils.isBlank(awardReportTerms.getReportCode().toString())){
            rulePassed = false;
            reportError(NEW_AWARD_REPORT_TERMS + "[" + index + "]" + ".reportCode"
                    , KeyConstants.ERROR_REQUIRED_REPORT_CODE);
        }
        return rulePassed;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for frequency field.
     * @param awardReportTerms
     * @return
     */
    protected boolean evaluateRuleForFrequency(AwardReportTerms awardReportTerms, String index){
        boolean rulePassed = true;
        if(awardReportTerms.getFrequencyCode() == null 
                || StringUtils.isBlank(awardReportTerms.getFrequencyCode().toString())){
            rulePassed = false;
            reportError(NEW_AWARD_REPORT_TERMS + "[" + index + "]" + ".frequencyCode"
                    , KeyConstants.ERROR_REQUIRED_FREQUENCY_CODE);
        }
        return rulePassed;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for frequency base field.
     * @param awardReportTerms
     * @return
     */
    protected boolean evaluateRuleForFrequencyBase(AwardReportTerms awardReportTerms, String index){
        boolean rulePassed = true;
        if(awardReportTerms.getFrequencyBaseCode() == null 
                || StringUtils.isBlank(awardReportTerms.getFrequencyBaseCode().toString())){
            rulePassed = false;
            reportError(NEW_AWARD_REPORT_TERMS + "[" + index + "]" + ".frequencyBaseCode"
                    , KeyConstants.ERROR_REQUIRED_FREQUENCY_BASE_CODE);
        }
        return rulePassed;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for distribution field.
     * @param awardReportTerms
     * @return
     */
    protected boolean evaluateRuleForDistribution(AwardReportTerms awardReportTerms, String index){
        boolean rulePassed = true;
        if(awardReportTerms.getOspDistributionCode() == null 
                || StringUtils.isBlank(awardReportTerms.getOspDistributionCode().toString())){
            rulePassed = false;
            reportError(NEW_AWARD_REPORT_TERMS + "[" + index + "]" + ".ospDistributionCode"
                    , KeyConstants.ERROR_REQUIRED_DISTRIBUTION_CODE);
        }
        return rulePassed;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for Due Date field.
     * @param awardReportTerms
     * @return
     */
    protected boolean evaluateRuleForDueDate(AwardReportTerms awardReportTerms, String index){
        boolean rulePassed = true;
        if(awardReportTerms.getDueDate() == null 
                || StringUtils.isBlank(awardReportTerms.getDueDate().toString())){
            rulePassed = false;
            reportError(NEW_AWARD_REPORT_TERMS + "[" + index + "]" + ".dueDate"
                    , KeyConstants.ERROR_REQUIRED_DUE_DATE);
        }
        return rulePassed;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for distribution field.
     * @param awardReportTerms
     * @return
     */
    protected boolean evaluateRuleForContactType(AwardReportTerms awardReportTerms, String index){
        boolean rulePassed = true;
        if(awardReportTerms.getContactTypeCode() == null 
                || StringUtils.isBlank(awardReportTerms.getContactTypeCode().toString())){
            rulePassed = false;
            reportError(NEW_AWARD_REPORT_TERMS_RECIPIENTS + "[" + index + "]" + ".contactTypeCode"
                    , KeyConstants.ERROR_REQUIRED_CONTACT_TYPE);
        }
        return rulePassed;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for distribution field.
     * @param awardReportTerms
     * @return
     */
    protected boolean evaluateRuleForRolodex(AwardReportTerms awardReportTerms, String index){
        boolean rulePassed = true;
        if(awardReportTerms.getRolodexId() == null 
                || StringUtils.isBlank(awardReportTerms.getRolodexId().toString())){
            rulePassed = false;
            reportError(NEW_AWARD_REPORT_TERMS_RECIPIENTS + "[" + index + "]" + ".rolodexId"
                    , KeyConstants.ERROR_REQUIRED_ROLODEX_ID);
        }
        return rulePassed;
    }
    
    
}
