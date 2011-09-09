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
package org.kuali.kra.coi.personfinancialentity;

import java.util.List;

import net.sf.cglib.core.CollectionUtils;

import org.apache.commons.lang.StringUtils;
import org.displaytag.util.CollectionUtil;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;

public class SaveFinancialEntityReporterUnitRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<SaveFinancialEntityReporterUnitEvent> {
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(SaveFinancialEntityReporterUnitEvent event) {
        boolean isValid = true;
        
        String errorPathKey = event.getPropertyName() + ".financialEntityReporter";
        GlobalVariables.getMessageMap().addToErrorPath(errorPathKey);
        if (org.apache.commons.collections.CollectionUtils.isEmpty(event.getFinancialEntityReporterUnits())) {
            GlobalVariables.getMessageMap().putError("unitNumber",
            KeyConstants.ERROR_ONE_UNIT, "Financial Entity");
            
        } else {
            boolean leadUnitFound = false;
            for (FinancialEntityReporterUnit unit : event.getFinancialEntityReporterUnits()) {
                if (unit.isLeadUnitFlag()) {
                    leadUnitFound = true;
                    break;
                }
            }
            if (!leadUnitFound) {
                GlobalVariables.getMessageMap().putError("unitNumber",
                        KeyConstants.ERROR_LEAD_UNIT_REQUIRED);
                
            }
            
        }
        isValid &= GlobalVariables.getMessageMap().hasNoErrors();
        GlobalVariables.getMessageMap().removeFromErrorPath(errorPathKey);
        
        return isValid;
    }


}
