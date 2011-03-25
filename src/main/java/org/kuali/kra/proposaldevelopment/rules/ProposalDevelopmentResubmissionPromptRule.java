/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.rule.ResubmissionPromptRule;
import org.kuali.kra.proposaldevelopment.rule.event.ResubmissionRuleEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * Implements {@code ResubmissionPromptRule}.
 */
public class ProposalDevelopmentResubmissionPromptRule extends ResearchDocumentRuleBase implements ResubmissionPromptRule {

    private static final String REBUSMISSION_OPTION = "resubmissionOption";
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.proposaldevelopment.rule.ResubmissionPromptRule#processResubmissionPromptBusinessRules(
     *      org.kuali.kra.proposaldevelopment.rule.event.ResubmissionRuleEvent)
     */
    public boolean processResubmissionPromptBusinessRules(ResubmissionRuleEvent resubmissionRuleEvent) {
        boolean rulePassed = true;
        
        if (StringUtils.isBlank(resubmissionRuleEvent.getResubmissionOption())) {
            rulePassed = false;
            reportError(REBUSMISSION_OPTION, KeyConstants.ERROR_PROPOSAL_DEVELOPMENT_RESUBMISSION_PROMPT_OPTION_REQUIRED);
        }
        
        return rulePassed;
    }

}