/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.negotiations.rules;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.negotiations.bo.NegotiationActivity;
import org.kuali.kra.negotiations.bo.NegotiationActivityAttachment;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * 
 * Event class to use when validating new negotiation activity attachments.
 */
public class NegotiationActivityAttachmentAddRuleEvent extends KcDocumentEventBase {

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

    @Override
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
