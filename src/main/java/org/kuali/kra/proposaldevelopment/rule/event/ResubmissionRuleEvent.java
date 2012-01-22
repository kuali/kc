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
package org.kuali.kra.proposaldevelopment.rule.event;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.proposaldevelopment.rule.ResubmissionPromptRule;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * Defines the event of validating the options for Proposal Development resubmission.
 */
public class ResubmissionRuleEvent extends KraDocumentEventBase {
    
    private static final Log LOG = LogFactory.getLog(ResubmissionRuleEvent.class);
    
    private String resubmissionOption;
    
    /**
     * Constructs a ResubmissionRuleEvent.
     * 
     * @param document the document being validated
     * @param resubmissionOption the option for resubmission entered by the user
     */
    public ResubmissionRuleEvent(Document document, String resubmissionOption) {
        super("resubmission prompt for proposal document " + getDocumentId(document), "", document);
        
        this.resubmissionOption = resubmissionOption;
    }
    
    public String getResubmissionOption() {
        return resubmissionOption;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.rice.krad.rules.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((ResubmissionPromptRule) rule).processResubmissionPromptBusinessRules(this);
    }

    public Class<ResubmissionPromptRule> getRuleInterfaceClass() {
        return ResubmissionPromptRule.class;
    }

    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        LOG.debug(logMessage);
    }

}