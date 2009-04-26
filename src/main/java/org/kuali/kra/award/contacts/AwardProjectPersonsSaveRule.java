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

import org.kuali.rice.kns.rule.BusinessRule;

/**
 * This defines the rules for AwardProjectPerson
 */
public interface AwardProjectPersonsSaveRule extends BusinessRule {
    public static final String AWARD_PROJECT_PERSON_LIST_ERROR_KEY = "projectPersons";
    public static final String ERROR_AWARD_PROJECT_PERSON_NO_PI = "error.awardProjectPerson.no.pi.exists";
    public static final String ERROR_AWARD_PROJECT_PERSON_MULTIPLE_PI_EXISTS = "error.awardProjectPerson.pi.exists";
    public static final String ERROR_AWARD_PROJECT_PERSON_UNIT_DETAILS_REQUIRED = "error.awardProjectPerson.unit.details.required";
    public static final String ERROR_AWARD_PROJECT_PERSON_LEAD_UNIT_REQUIRED = "error.awardProjectPerson.lead.unit.required";
    public static final String ERROR_AWARD_PROJECT_PERSON_DUPLICATE_UNITS = "error.awardProjectPerson.duplicate.units";
    
    /**
     * This method should be called before adding a new Project Person
     * @param event
     * @return
     */
    boolean processSaveAwardProjectPersonsBusinessRules(SaveAwardProjectPersonsRuleEvent event);

}
