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
package org.kuali.kra.award.paymentreports.closeout;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This the AwardCloseoutRuleEvent
 */
public class AwardCloseoutRuleEvent extends KcDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(AwardCloseoutRuleEvent.class);
    
    private Award award;
    private AwardCloseout closeoutItem;
    
    /**
     * 
     * Constructs a AwardCloseoutRuleEvent.java.
     * @param errorPathPrefix
     * @param awardDocument
     * @param award
     * @param closeoutItem
     */
    public AwardCloseoutRuleEvent(String errorPathPrefix, 
                                            AwardDocument awardDocument,
                                            Award award,
                                            AwardCloseout closeoutItem) {
        super("Closeout item", errorPathPrefix, awardDocument);
        this.award = award;
        this.closeoutItem = closeoutItem;
    }

    /**
     * Convenience method to return an Award
     * @return
     */
    public Award getAward() {
        return award;
    }
    
    /**
     * Convenience method to return an AwardDocument
     * @return
     */
    public AwardDocument getAwardDocument() {
        return (AwardDocument) getDocument();
    }
    
    /**
     * This method returns the closeout item for validation
     * @return
     */
    public AwardCloseout getCloseoutItemForValidation() {
        return closeoutItem;
    }   

    @Override
    protected void logEvent() {
        LOG.info("Logging AwardCloseoutRuleEvent");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return AwardCloseoutRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardCloseoutRule)rule).processAwardCloseoutBusinessRules(this);
    }   
}
