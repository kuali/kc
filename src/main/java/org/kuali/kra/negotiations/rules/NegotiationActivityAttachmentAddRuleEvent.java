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
package org.kuali.kra.negotiations.rules;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.negotiations.bo.NegotiationActivity;
import org.kuali.kra.negotiations.bo.NegotiationActivityAttachment;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * 
 * Event class to use when validating new negotiation activity attachments.
 */
public class NegotiationActivityAttachmentAddRuleEvent extends KraDocumentEventBase {

    private static final Log LOG = LogFactory.getLog(NegotiationActivityAttachmentAddRuleEvent.class);
    
    private NegotiationActivity activity;
    private NegotiationActivityAttachment newAttachment;
    
    /**
     * 
     * Constructs a NegotiationActivityAttachmentAddRuleEvent.java.
     * @param description
     * @param errorPathPrefix
     * @param document
     * @param activity
     * @param newAttachment
     */
    public NegotiationActivityAttachmentAddRuleEvent(String description, String errorPathPrefix, Document document, NegotiationActivity activity, NegotiationActivityAttachment newAttachment) {
        super(description, errorPathPrefix, document);
        this.activity = activity;
        this.newAttachment = newAttachment;
    }
    
    public Class<NegotiationActivityAddRule> getRuleInterfaceClass() {
        return NegotiationActivityAddRule.class;
    }

    /**
     * @see org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.rice.krad.rules.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((NegotiationActivityAttachmentAddRule) rule).processAddAttachmentRule(this);
    }

    @Override
    protected void logEvent() {
        LOG.info("Logging NegotiationActivityAttachmentAddRuleEvent");
    }

    public NegotiationActivity getActivity() {
        return activity;
    }

    public void setActivity(NegotiationActivity activity) {
        this.activity = activity;
    }

    public NegotiationActivityAttachment getNewAttachment() {
        return newAttachment;
    }

    public void setNewAttachment(NegotiationActivityAttachment newAttachment) {
        this.newAttachment = newAttachment;
    }
}
