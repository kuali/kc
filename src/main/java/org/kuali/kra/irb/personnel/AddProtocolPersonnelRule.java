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
package org.kuali.kra.irb.personnel;

import org.kuali.rice.kns.rule.BusinessRule;

/**
 * This class adds rule for adding new <code>ProtocolPerson</code> object
 */
public interface AddProtocolPersonnelRule extends BusinessRule {

    /**
     * This method evaluates to true if ProtocolPerson objects satisfy required fields and business rules.
     * Protocol person role is mandatory.
     * Don't allow more than one Principal Investigator for each protocol.
     * Don't allow duplicate person. Same person can be listed in multiple roles. Validate with person and role
     * @param addProtocolPersonnelEvent
     * @return boolean true for valid object and false for invalid entry
     */
    public boolean processAddProtocolPersonnelBusinessRules(AddProtocolPersonnelEvent addProtocolPersonnelEvent);

}
