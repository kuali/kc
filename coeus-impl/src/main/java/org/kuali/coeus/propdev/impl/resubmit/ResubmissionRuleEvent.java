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
package org.kuali.coeus.propdev.impl.resubmit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * Defines the event of validating the options for Proposal Development resubmission.
 */
public class ResubmissionRuleEvent extends KcDocumentEventBase {
    
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

    @Override
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
