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
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * 
 * Negotiation Activity Add Rule Event - Event to use when validating new activities.
 */
public class NegotiationActivityAddRuleEvent extends KcDocumentEventBase {

    private static final Log LOG = LogFactory.getLog(NegotiationActivityAddRuleEvent.class);
    
    private NegotiationActivity newActivity;
    
    /**
     * 
     * Constructs a NegotiationActivityAddRuleEvent.java.
     * @param description
     * @param errorPathPrefix
     * @param document
     * @param newActivity
     */
    public NegotiationActivityAddRuleEvent(String description, String errorPathPrefix, Document document, NegotiationActivity newActivity) {
        super(description, errorPathPrefix, document);
        this.newActivity = newActivity;
    }
    
    public Class<NegotiationActivityAddRule> getRuleInterfaceClass() {
        return NegotiationActivityAddRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((NegotiationActivityAddRule) rule).processAddNegotiationActivityRule(this);
    }

    @Override
    protected void logEvent() {
        LOG.info("Logging NegotiationActivityAddRuleEvent");
    }

    public NegotiationActivity getNewActivity() {
        return newActivity;
    }
}
