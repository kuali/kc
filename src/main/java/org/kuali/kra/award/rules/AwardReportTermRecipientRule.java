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
import org.kuali.kra.award.bo.AwardReportTermRecipient;
import org.kuali.kra.award.rule.AddAwardReportTermRecipientRule;
import org.kuali.kra.award.rule.event.AddAwardReportTermRecipientEvent;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * 
 * This class represents the AwardReportTermRecipientRule
 */
public class AwardReportTermRecipientRule extends ResearchDocumentRuleBase 
                        implements AddAwardReportTermRecipientRule {
    
    private static final String NEW_AWARD_REPORT_TERM_RECIPIENT = "newAwardReportTermRecipient";
    private static final String DOT_CONTACT_TYPE_CODE = ".contactTypeCode";
    private static final String DOT_ROLODEX_ID = ".rolodexId";
    
    /**
     * 
     * @see org.kuali.kra.award.rule.AddAwardReportTermRecipientRule#processAddAwardReportTermRecipientEvent(
     * org.kuali.kra.award.rule.event.AddAwardReportTermRecipientEvent)
     */
    public boolean processAddAwardReportTermRecipientEvent(
            AddAwardReportTermRecipientEvent addAwardReportTermRecipientEvent) {        
        AwardForm awardForm = (AwardForm) GlobalVariables.getKualiForm();
        AwardReportTermRecipient awardReportTermRecipient = 
            addAwardReportTermRecipientEvent.getAwardReportTermRecipient();        
               
        boolean rulePassed = evaluateRuleForContactType(
                awardReportTermRecipient,awardForm.getAwardReportTermPanelNumber());
        
        rulePassed &= evaluateRuleForRolodex(
                awardReportTermRecipient,awardForm.getAwardReportTermPanelNumber());

        return rulePassed;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for distribution field.
     * @param awardReportTerm
     * @return
     */
    protected boolean evaluateRuleForContactType(
            AwardReportTermRecipient awardReportTermRecipient, int index){
        boolean rulePassed = !(StringUtils.isEmpty(awardReportTermRecipient.getContactTypeCode()));
        
        if(!rulePassed){
            reportError(index, DOT_CONTACT_TYPE_CODE, KeyConstants.ERROR_REQUIRED_CONTACT_TYPE);
        }
        
        return rulePassed;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for distribution field.
     * @param awardReportTerm
     * @return
     */
    protected boolean evaluateRuleForRolodex(AwardReportTermRecipient awardReportTermRecipient, int index){
        boolean rulePassed = !(awardReportTermRecipient.getRolodexId() == null);
        
        if(!rulePassed){
            reportError(index, DOT_ROLODEX_ID, KeyConstants.ERROR_REQUIRED_ROLODEX_ID);
            
        }
        return rulePassed;
    }
    
    private void reportError(int index, String context, String errorKey) {
        String errorPath = new StringBuilder(NEW_AWARD_REPORT_TERM_RECIPIENT).append(Constants.LEFT_SQUARE_BRACKET)
                                           .append(index).append(Constants.RIGHT_SQUARE_BRACKET)
                                           .append(context).toString();
        reportError(errorPath, errorKey);
    }
}
