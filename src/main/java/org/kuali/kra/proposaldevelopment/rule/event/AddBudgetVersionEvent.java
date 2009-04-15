/*
 * Copyright 2006-2009 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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

import org.kuali.core.document.Document;
import org.kuali.core.rule.BusinessRule;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AddBudgetVersionRule;
import org.kuali.kra.rule.event.KraDocumentEventBase;

import static org.apache.commons.lang.StringUtils.substringAfterLast;
import static org.kuali.kra.infrastructure.Constants.EMPTY_STRING;
import static org.kuali.kra.logging.BufferedLogger.debug;
import static org.kuali.kra.logging.BufferedLogger.logger;

public class AddBudgetVersionEvent extends KraDocumentEventBase {
    
    private String versionName;

    /**
     * Convenience constructor for {@link #AddBudgetVersionEvent(String, Document, String)}
     * 
     * @param errorPathPrefix
     * @param document {@link ProposalDevelopmentDocument} instance the version is to be added to
     * @param versionName or name of the {@link BudgetVersionsOverview}
     */
    public AddBudgetVersionEvent(Document document, String versionName) {
        this(EMPTY_STRING, document, versionName);
    }

    /**
     * Instantiate the event describing that a Budget Version is to be added
     * 
     * @param errorPathPrefix
     * @param document {@link ProposalDevelopmentDocument} instance the version is to be added to
     * @param versionName or name of the {@link BudgetVersionsOverview}
     */
    public AddBudgetVersionEvent(String errorPathPrefix, Document document, String versionName) {
        super("adding budget version to document " + getDocumentId(document), errorPathPrefix, document);
        setVersionName(versionName);
        logEvent();
    }


    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class<AddBudgetVersionRule> getRuleInterfaceClass() {
        return AddBudgetVersionRule.class;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String name) {
        this.versionName = name;
    }

    /**
     * Logs the event type and some information about the associated budget period. Only logs if 
     * DEBUG is enabled in Log4j
     */
    protected void logEvent() {
        if (!logger().isDebugEnabled()) {
            return;
        }

        StringBuffer logMessage = new StringBuffer(substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ").append(versionName).append(" versionName");


        debug(logMessage);
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        try {
            return getRuleInterfaceClass().cast(rule).processAddBudgetVersion(this);
        }
        catch (Exception cce) {
            return false;
        }
    }
}

