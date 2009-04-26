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
package org.kuali.kra.award.contacts;

import org.apache.log4j.Logger;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.BusinessRule;

/**
 * This class is the event passed when a new projectPerson is being added
 */
public class AwardPersonUnitRuleAddEvent extends KraDocumentEventBase {
    private static final Logger LOG = Logger.getLogger(AwardPersonUnitRuleAddEvent.class);
    
    private AwardPersonUnit newPersonUnit;
    private AwardPerson projectPerson;
    
    protected AwardPersonUnitRuleAddEvent(String description, String errorPathPrefix, Document document, 
                                                    AwardPerson projectPerson, AwardPersonUnit newPersonUnit) {
        super(description, errorPathPrefix, document);
        this.newPersonUnit = newPersonUnit;
        this.projectPerson = projectPerson;
    }

    /**
     * @return
     */
    public AwardPersonUnit getNewPersonUnit() {
        return newPersonUnit;
    }

    public AwardPerson getProjectPerson() {
        return projectPerson;
    }
    
    public Class<AwardPersonUnitAddRule> getRuleInterfaceClass() {
        return AwardPersonUnitAddRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardPersonUnitAddRule) rule).processAddAwardPersonUnitBusinessRules(this);
    }

    @Override
    protected void logEvent() {
        LOG.info("Logging AddAwardProjectPersonRuleEvent");
    }
}