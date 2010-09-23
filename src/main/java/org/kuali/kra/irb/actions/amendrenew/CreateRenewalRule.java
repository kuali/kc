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
package org.kuali.kra.irb.actions.amendrenew;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * 
 * This class checks the summary for 'create renewal without amendment' is not empty
 */
public class CreateRenewalRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<CreateRenewalEvent> {

    public boolean processRules(CreateRenewalEvent event) {
        
        boolean valid = true;
        
        if (StringUtils.isBlank(event.getRenewalSummary())) {
            valid = false;
            reportError(event.getPropertyName(), KeyConstants.ERROR_PROTOCOL_SUMMARY_IS_REQUIRED);
        }
                
        return valid;
    }
}
