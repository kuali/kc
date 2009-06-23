/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.rules;

import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.rule.AwardTemplateSyncRule;
import org.kuali.kra.award.rule.event.AwardTemplateSyncEvent;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ErrorReporter;

/**
 * This class is to validate all rules pertaining to Award Template sync functionality
 */
public class AwardTemplateSyncRuleImpl implements AwardTemplateSyncRule {

    private ErrorReporter errorReporter;
    /**
     * @see org.kuali.kra.award.rule.AwardTemplateSyncRule#processAwardTemplateSyncRules(org.kuali.kra.award.rule.event.AwardTemplateSyncEvent)
     */
    public boolean processAwardTemplateSyncRules(AwardTemplateSyncEvent awardTemplateSyncEvent) {
        Award award = awardTemplateSyncEvent.getAward();
        errorReporter = new ErrorReporter();
        if(award.getTemplateCode()==null){
            errorReporter.reportError(awardTemplateSyncEvent.getErrorPathPrefix(), KeyConstants.ERROR_NO_TEMPLATE_CODE);
            return false;
        }
                
        return true;
    }

}
