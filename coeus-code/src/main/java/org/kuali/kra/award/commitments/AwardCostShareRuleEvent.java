/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award.commitments;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.krad.rules.rule.BusinessRule;


public class AwardCostShareRuleEvent extends KcDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(AwardCostShareRuleEvent.class);
    
    private AwardCostShare awardCostShare;

    public AwardCostShareRuleEvent(String errorPathPrefix, 
                                           AwardDocument awardDocument,
                                           AwardCostShare awardCostShare) {
        super("Cost Share", errorPathPrefix, awardDocument);
        this.awardCostShare = awardCostShare;
    }
    
    /**
     * Convenience method to return an AwardDocument
     * @return
     */
    public AwardDocument getAwardDocument() {
        return (AwardDocument) getDocument();
    }
    
    /**
     * This method returns the equipment item for validation
     * @return
     */
    public AwardCostShare getCostShareForValidation() {
        return awardCostShare;
    }
    
    
    @Override
    protected void logEvent() {
        LOG.info("Logging AwardCostShareRuleEvent");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return AwardCostShareRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardCostShareRule)rule).processCostShareBusinessRules(this, 0);
    }

}
