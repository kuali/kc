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
package org.kuali.kra.protocol.actions.correction;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;

/**
 * The event that occurs when the ProtocolBase Administrator assigns reviewers to a protocol.
 */
public abstract class ProtocolAdminCorrectionEventBase extends KraDocumentEventBaseExtension {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProtocolAdminCorrectionEventBase.class);
    
    private AdminCorrectionBean actionBean;
    private String propertyName;
    
    public ProtocolAdminCorrectionEventBase(ProtocolDocumentBase document, String propertyName, AdminCorrectionBean actionBean) {
        super("Administrative Correction for document " + getDocumentId(document), "", document);
        this.actionBean = actionBean;
        this.propertyName = propertyName;
        logEvent();
    }

    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ");

        if (this.actionBean == null) {
            logMessage.append("null actionBean");
        }
        else {
            logMessage.append(actionBean.toString());
        }

        LOG.debug(logMessage);
    }

    public ProtocolDocumentBase getProtocolDocument() {
        return (ProtocolDocumentBase) getDocument();
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public AdminCorrectionBean getAdminCorrectionBean() {
        return actionBean;
    }

    public abstract BusinessRuleInterface getRule();

}
