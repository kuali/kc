/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.rule.event;

import static org.kuali.kra.logging.BufferedLogger.info;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.rule.SaveProtocolRequiredFieldsRule;
import org.kuali.kra.irb.rules.ProtocolRequiredFieldsRule;
import org.kuali.kra.rule.event.KraDocumentEventBase;

public class SaveProtocolRequiredFieldsEvent extends KraDocumentEventBase {

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
    .getLog(SaveProtocolRequiredFieldsEvent.class);
    
    protected SaveProtocolRequiredFieldsEvent(String description, String errorPathPrefix, ProtocolDocument document) {
        super(description, errorPathPrefix, document);
        // TODO Auto-generated constructor stub
    }
    
    public SaveProtocolRequiredFieldsEvent(ProtocolDocument document) {
        super(StringUtils.EMPTY, StringUtils.EMPTY, document);
        logEvent();
    }

    @Override
    protected void logEvent() {
        // TODO Auto-generated method stub
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        // vary logging detail as needed
        if (document == null) {
            logMessage.append("null protocolDocument");
        }
        else {
            logMessage.append(document.toString());
        }

        LOG.debug(logMessage);        
    }

    public Class getRuleInterfaceClass() {
        return SaveProtocolRequiredFieldsRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        info("Calling processSaveProtocolRequiredFieldsRules on ", rule.getClass().getSimpleName());
        return ((SaveProtocolRequiredFieldsRule) rule).processSaveProtocolRequiredFieldsRules(this);
        
    }

}
