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
package org.kuali.kra.coi.actions;

import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiUserRole;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;

/**
 * 
 * This class represents the event fired when a coi reviewer is added to 
 * a disclosure.
 */
public class AddCoiReviewerEvent extends KraDocumentEventBaseExtension {

    private String propertyName;
    private CoiDisclosure coiDisclosure;
    private CoiUserRole coiUserRole;
    
    
    public AddCoiReviewerEvent(String propertyName, CoiDisclosure coiDisclosure, CoiUserRole coiUserRole) {
        super("Add Coi Reviewer", "", null);       
        this.propertyName = propertyName;
        this.coiDisclosure = coiDisclosure;
        this.coiUserRole = coiUserRole;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public BusinessRuleInterface getRule() {
        return new AddCoiReviewerRule();
    }

    public CoiDisclosure getCoiDisclosure() {
        return coiDisclosure;
    }

    public void setCoiDisclosure(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public CoiUserRole getCoiUserRole() {
        return coiUserRole;
    }

    public void setCoiUserRole(CoiUserRole coiUserRole) {
        this.coiUserRole = coiUserRole;
    }
}
