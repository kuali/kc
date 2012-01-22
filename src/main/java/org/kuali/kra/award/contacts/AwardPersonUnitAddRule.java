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

import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This defines the rules for AwardProjectPerson
 */
public interface AwardPersonUnitAddRule extends BusinessRule {
    public static final String AWARD_PROJECT_PERSON_LIST_ERROR_KEY = "projectPersonnelBean.newAwardPersonUnit";
    public static final String ERROR_AWARD_PROJECT_PERSON_PI_EXISTS = "error.awardProjectPerson.pi.exists";
    public static final String ERROR_AWARD_PROJECT_PERSON_EXISTS = "error.awardProjectPerson.person.exists";
    public static final String ERROR_AWARD_PROJECT_PERSON_DUPLICATE_UNITS = "error.awardProjectPerson.duplicate.units";
    public static final String ERROR_AWARD_PROJECT_PERSON_INVAILD_UNIT = "error.awardProjectPerson.invalid.unit";
    
    /**
     * This method should be called before adding a new Project Person
     * @param event
     * @return
     */
    boolean processAddAwardPersonUnitBusinessRules(AwardPersonUnitRuleAddEvent event);

}
