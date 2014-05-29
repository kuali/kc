/*
 * Copyright 2005-2014 The Kuali Foundation.
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
package org.kuali.coeus.common.budget.framework.version;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

import static org.apache.commons.lang3.StringUtils.substringAfterLast;
import static org.kuali.kra.infrastructure.Constants.EMPTY_STRING;

public class AddBudgetVersionEvent extends KcDocumentEventBase {

    private static final Log LOG = LogFactory.getLog(AddBudgetVersionEvent.class);

    private String versionName;
    private Budget budget;

    /**
     * Convenience constructor for {@link #AddBudgetVersionEvent(String, Document, String)}
     *
     * @param document {@link ProposalDevelopmentDocument} instance the version is to be added to
     * @param versionName
     */
    public AddBudgetVersionEvent(Document document, String versionName) {
        this(EMPTY_STRING, document, versionName);
    }

    /**
     * Instantiate the event describing that a Budget Version is to be added
     * 
     * @param errorPathPrefix
     * @param document {@link ProposalDevelopmentDocument} instance the version is to be added to
     * @param versionName
     */
    public AddBudgetVersionEvent(String errorPathPrefix, Document document, String versionName) {
        super("adding budget version to document " + getDocumentId(document), errorPathPrefix, document);
        setVersionName(versionName);
        logEvent();
    }

    /**
     * Instantiate the event describing that a Budget Version is to be added
     * 
     * @param errorPathPrefix
     * @param document {@link ProposalDevelopmentDocument} instance the version is to be added to
     * @param budgetVersionOverview
     */
    public AddBudgetVersionEvent(String errorPathPrefix, Document document, Budget budgetVersionOverview) {
        super("adding budget version to document " + getDocumentId(document), errorPathPrefix, document);
        setBudget(budgetVersionOverview);
        logEvent();
    }

    @Override
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
        if (!LOG.isDebugEnabled()) {
            return;
        }

        StringBuffer logMessage = new StringBuffer(substringAfterLast(this.getClass().getName(), "."));
        logMessage.append(" with ").append(versionName).append(" versionName");


        LOG.debug(logMessage);
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        try {
            return getRuleInterfaceClass().cast(rule).processAddBudgetVersionName(this);
        }
        catch (Exception cce) {
            return false;
        }
    }
    
    /**
     * Gets the budget attribute. 
     * @return Returns the budget.
     */
    public Budget getBudget() {
        return budget;
    }

    /**
     * Sets the budget attribute value.
     * @param budget The budget to set.
     */
    public void setBudget(Budget budget) {
        this.budget = budget;
    }
}

