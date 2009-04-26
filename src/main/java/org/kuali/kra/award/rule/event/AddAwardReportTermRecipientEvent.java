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
package org.kuali.kra.award.rule.event;

import org.kuali.kra.award.bo.AwardReportTermRecipient;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.rule.AddAwardReportTermRecipientRule;
import org.kuali.rice.kns.rule.BusinessRule;

/**
 * 
 * This is the base event class for <code>AwardReportTerm</code> business object.
 */
public class AddAwardReportTermRecipientEvent extends AwardReportTermRecipientEvent {
    private static final org.apache.commons.logging.Log LOG = 
        org.apache.commons.logging.LogFactory.getLog(AddAwardReportTermRecipientEvent.class);    

    /**
     * 
     * Constructs a AddAwardReportTermRecipientEvent.java.
     * @param errorPathPrefix
     * @param document
     * @param awardReportTermRecipient
     */
    public AddAwardReportTermRecipientEvent(String errorPathPrefix, 
            AwardDocument document, AwardReportTermRecipient awardReportTermRecipient) {
        super("adding Award Report Terms Recipient ", errorPathPrefix, document, awardReportTermRecipient);
        logEvent();
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddAwardReportTermRecipientRule) rule).processAddAwardReportTermRecipientEvent(this);
    }
    
    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class<AddAwardReportTermRecipientRule> getRuleInterfaceClass() {
        return AddAwardReportTermRecipientRule.class;
    }
}
