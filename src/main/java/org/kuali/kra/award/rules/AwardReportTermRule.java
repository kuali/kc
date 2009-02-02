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
import org.kuali.kra.award.bo.AwardReportTerm;
import org.kuali.kra.award.rule.AddAwardReportTermRule;
import org.kuali.kra.award.rule.event.AddAwardReportTermEvent;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * 
 * This class represents the AwardFandaRateRule
 */
public class AwardReportTermRule extends ResearchDocumentRuleBase implements AddAwardReportTermRule {
    
    private static final String NEW_AWARD_REPORT_TERM = "newAwardReportTerm";
    
    /**
     * 
     * @see org.kuali.kra.award.rule.AddAwardReportTermRule#processAddAwardReportTermEvent(
     * org.kuali.kra.award.rule.event.AddAwardReportTermEvent)
     */
    public boolean processAddAwardReportTermEvent(AddAwardReportTermEvent 
            addAwardReportTermEvent) {        
        AwardReportTerm awardReportTerm = 
            addAwardReportTermEvent.getAwardReportTerm();
        AwardForm awardForm = (AwardForm) GlobalVariables.getKualiForm();
        boolean rulePassed = true;
        
        rulePassed &= evaluateRuleForReportCode(awardReportTerm,awardForm.getAwardReportTermPanelNumber());
        rulePassed &= evaluateRuleForFrequency(awardReportTerm,awardForm.getAwardReportTermPanelNumber());
        rulePassed &= evaluateRuleForFrequencyBase(awardReportTerm,awardForm.getAwardReportTermPanelNumber());
        rulePassed &= evaluateRuleForDistribution(awardReportTerm,awardForm.getAwardReportTermPanelNumber());
        rulePassed &= evaluateRuleForDueDate(awardReportTerm,awardForm.getAwardReportTermPanelNumber());

        return rulePassed;            
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for reportCode field.
     * @param awardReportTerm
     * @return
     */
    protected boolean evaluateRuleForReportCode(AwardReportTerm awardReportTerm, String index){
        boolean rulePassed = true;
        
        if(awardReportTerm.getReportCode() == null 
                || StringUtils.isBlank(awardReportTerm.getReportCode().toString())){
            rulePassed = false;
            reportError(NEW_AWARD_REPORT_TERM + Constants.LEFT_SQUARE_BRACKET + index 
                    + Constants.RIGHT_SQUARE_BRACKET + ".reportCode"
                    , KeyConstants.ERROR_REQUIRED_REPORT_CODE);
        }
        
        return rulePassed;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for frequency field.
     * @param awardReportTerm
     * @return
     */
    protected boolean evaluateRuleForFrequency(AwardReportTerm awardReportTerm, String index){
        boolean rulePassed = true;
        
        if(awardReportTerm.getFrequencyCode() == null 
                || StringUtils.isBlank(awardReportTerm.getFrequencyCode().toString())){
            rulePassed = false;
            reportError(NEW_AWARD_REPORT_TERM + Constants.LEFT_SQUARE_BRACKET + index 
                    + Constants.RIGHT_SQUARE_BRACKET + ".frequencyCode"
                    , KeyConstants.ERROR_REQUIRED_FREQUENCY_CODE);
        }
        
        return rulePassed;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for frequency base field.
     * @param awardReportTerm
     * @return
     */
    protected boolean evaluateRuleForFrequencyBase(AwardReportTerm awardReportTerm, String index){
        boolean rulePassed = !(awardReportTerm.getFrequencyBaseCode() == null 
                || StringUtils.isBlank(awardReportTerm.getFrequencyBaseCode().toString()));
        
        if(!rulePassed){            
            reportError(NEW_AWARD_REPORT_TERM + Constants.LEFT_SQUARE_BRACKET + index 
                    + Constants.RIGHT_SQUARE_BRACKET + ".frequencyBaseCode"
                    , KeyConstants.ERROR_REQUIRED_FREQUENCY_BASE_CODE);
        }
        
        return rulePassed;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for distribution field.
     * @param awardReportTerm
     * @return
     */
    protected boolean evaluateRuleForDistribution(AwardReportTerm awardReportTerm, String index){
        boolean rulePassed = !(awardReportTerm.getOspDistributionCode() == null 
                || StringUtils.isBlank(awardReportTerm.getOspDistributionCode().toString()));
        
        if(!rulePassed){            
            reportError(NEW_AWARD_REPORT_TERM + Constants.LEFT_SQUARE_BRACKET + index 
                    + Constants.RIGHT_SQUARE_BRACKET + ".ospDistributionCode"
                    , KeyConstants.ERROR_REQUIRED_DISTRIBUTION_CODE);
        }
        
        return rulePassed;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for Due Date field.
     * @param awardReportTerm
     * @return
     */
    protected boolean evaluateRuleForDueDate(AwardReportTerm awardReportTerm, String index){
        boolean rulePassed = !(awardReportTerm.getDueDate() == null 
                || StringUtils.isBlank(awardReportTerm.getDueDate().toString()));
        
        if(!rulePassed){            
            reportError(NEW_AWARD_REPORT_TERM + Constants.LEFT_SQUARE_BRACKET + index 
                    + Constants.RIGHT_SQUARE_BRACKET + ".dueDate"
                    , KeyConstants.ERROR_REQUIRED_DUE_DATE);
        }
        
        return rulePassed;
    }
}
