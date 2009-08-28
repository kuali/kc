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
package org.kuali.kra.questionnaire.question;

import org.kuali.kra.bo.Person;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.PersonService;
import org.kuali.kra.service.UnitAuthorizationService;
import org.kuali.rice.kns.util.GlobalVariables;


public class QuestionAuthorizationServiceImpl implements QuestionAuthorizationService {

    private UnitAuthorizationService unitAuthorizationService;
    
    private PersonService personService;

    public boolean hasPermission(String permissionName) {
        Person person = personService.getPersonByName(getUserName());       
        return unitAuthorizationService.hasPermission(getUserName(), person.getUnit().getUnitNumber(), permissionName);
    }

    private String getUserName() {
        UniversalUser user = new UniversalUser(GlobalVariables.getUserSession().getPerson());
        return user.getPersonUserIdentifier();
    }

    public void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
        this.unitAuthorizationService = unitAuthorizationService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

}
