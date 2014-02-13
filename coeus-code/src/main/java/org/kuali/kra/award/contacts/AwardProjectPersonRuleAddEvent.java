/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.award.contacts;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class is the event passed when a new projectPerson is being added
 */
public class AwardProjectPersonRuleAddEvent extends KcDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(AwardProjectPersonRuleAddEvent.class);
    
    private AwardPerson newProjectPerson;
    
    protected AwardProjectPersonRuleAddEvent(String description, String errorPathPrefix, Document document, AwardPerson newProjectPerson) {
        super(description, errorPathPrefix, document);
        this.newProjectPerson = newProjectPerson;
    }

    /**
     * Gets the newProjectPerson attribute. 
     * @return Returns the newProjectPerson.
     */
    public AwardPerson getNewProjectPerson() {
        return newProjectPerson;
    }

    public Class<AwardProjectPersonAddRule> getRuleInterfaceClass() {
        return AwardProjectPersonAddRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardProjectPersonAddRule) rule).processAddAwardProjectPersonBusinessRules(this);
    }

    @Override
    protected void logEvent() {
        LOG.info("Logging AddAwardProjectPersonRuleEvent");
    }

}
