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

import org.kuali.kra.award.bo.AwardReportTerm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.rule.AddAwardReportTermRule;
import org.kuali.rice.kns.rule.BusinessRule;

/**
 * 
 * This is the base event class for <code>AwardReportTerm</code> business object.
 */
public class AddAwardReportTermEvent extends AwardReportTermEvent {
    private static final org.apache.commons.logging.Log LOG = 
        org.apache.commons.logging.LogFactory.getLog(AddAwardReportTermEvent.class);    

    /**
     * 
     * Constructs a AddAwardReportTermEvent.java.
     * @param errorPathPrefix
     * @param document
     * @param awardReportTerm
     */
    public AddAwardReportTermEvent(String errorPathPrefix, 
            AwardDocument document, AwardReportTerm awardReportTerm) {
        super("adding an Award Report Term", errorPathPrefix, document, awardReportTerm);
        logEvent();
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddAwardReportTermRule) rule).processAddAwardReportTermEvent(this);
    }
    
    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class<AddAwardReportTermRule> getRuleInterfaceClass() {
        return AddAwardReportTermRule.class;
    }
    
}
