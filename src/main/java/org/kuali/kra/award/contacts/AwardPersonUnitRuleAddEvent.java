/*
 * Copyright 2005-2010 The Kuali Foundation
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
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class is the event passed when a new projectPerson is being added
 */
public class AwardPersonUnitRuleAddEvent extends KraDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(AwardPersonUnitRuleAddEvent.class);
    
    private AwardPersonUnit newPersonUnit;
    private AwardPerson projectPerson;
    private int addUnitPersonIndex;
    
    protected AwardPersonUnitRuleAddEvent(String description, String errorPathPrefix, Document document, 
                                                    AwardPerson projectPerson, AwardPersonUnit newPersonUnit,
                                                    int addUnitPersonIndex) {
        super(description, errorPathPrefix, document);
        this.newPersonUnit = newPersonUnit;
        this.projectPerson = projectPerson;
        this.addUnitPersonIndex = addUnitPersonIndex;
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

    public void setAddUnitPersonIndex(int addUnitPersonIndex) {
        this.addUnitPersonIndex = addUnitPersonIndex;
    }

    public int getAddUnitPersonIndex() {
        return addUnitPersonIndex;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardPersonUnitAddRule) rule).processAddAwardPersonUnitBusinessRules(this);
    }

    @Override
    protected void logEvent() {
        LOG.info("Logging AddAwardProjectPersonRuleEvent");
    }
}