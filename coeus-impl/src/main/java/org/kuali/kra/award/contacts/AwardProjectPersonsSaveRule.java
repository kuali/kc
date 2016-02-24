/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.contacts;

import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This defines the rules for AwardProjectPerson
 */
public interface AwardProjectPersonsSaveRule extends BusinessRule {
    public static final String AWARD_PROJECT_PERSON_LIST_ERROR_KEY = "projectPersons";
    public static final String ERROR_AWARD_PROJECT_PERSON_NO_PI = "error.awardProjectPerson.no.pi.exists";
    public static final String ERROR_AWARD_PROJECT_PERSON_MULTIPLE_PI_EXISTS = "error.awardProjectPerson.pi.exists";
    public static final String ERROR_AWARD_PROJECT_KEY_PERSON_ROLE_REQUIRED = "error.awardProjectPerson.keyperson.role.required";
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
