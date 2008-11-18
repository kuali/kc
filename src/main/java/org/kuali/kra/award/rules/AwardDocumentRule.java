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

import org.kuali.core.document.Document;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.rule.AddIndirectCostRateRule;
import org.kuali.kra.award.rule.event.AddAwardIndirectCostRateEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * Main Business Rule class for <code>{@link AwardDocument}</code>. Responsible for delegating rules to independent rule classes.
 *
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class AwardDocumentRule extends ResearchDocumentRuleBase implements AddIndirectCostRateRule {
    
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        return super.processCustomRouteDocumentBusinessRules(document);
    }

    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        if (!(document instanceof AwardDocument)) {
            return false;
        }

        return true;
    }


    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document){
        return super.processRunAuditBusinessRules(document);
    }
    
    /**
     * 
     * @see org.kuali.kra.award.rule.AddIndirectCostRateRule#processAddIndirectCostRatesBusinessRules(org.kuali.kra.award.rule.event.AddAwardIndirectCostRateEvent)
     */
    public boolean processAddIndirectCostRatesBusinessRules(AddAwardIndirectCostRateEvent 
            addAwardIndirectCostRateEvent) {        
        return new AwardIndirectCostRateRule().processAddIndirectCostRatesBusinessRules(
                addAwardIndirectCostRateEvent);            
    }

}
