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
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

import java.util.List;

/**
 * This class is the event passed when a new projectPerson is being added
 */
public class SaveAwardProjectPersonsRuleEvent extends KcDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(SaveAwardProjectPersonsRuleEvent.class);
    
    private List<AwardPerson> projectPersons;
    
    public SaveAwardProjectPersonsRuleEvent(String description, String errorPathPrefix, Document document) {
        super(description, errorPathPrefix, document);
        this.projectPersons = ((AwardDocument) document).getAward().getProjectPersons();
    }

    /**
     * Gets the Award project persons. 
     * @return Returns the Award project persons
     */
    public List<AwardPerson> getProjectPersons() {
        return projectPersons;
    }

    public Class<AwardProjectPersonAddRule> getRuleInterfaceClass() {
        return AwardProjectPersonAddRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardProjectPersonsSaveRule) rule).processSaveAwardProjectPersonsBusinessRules(this);
    }

    @Override
    protected void logEvent() {
        LOG.info("Logging SaveAwardProjectPersonsRuleEvent");
    }

}
