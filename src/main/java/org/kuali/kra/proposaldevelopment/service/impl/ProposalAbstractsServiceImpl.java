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
package org.kuali.kra.proposaldevelopment.service.impl;

import java.util.List;

import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.service.ProposalAbstractsService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;

public class ProposalAbstractsServiceImpl implements ProposalAbstractsService {
    
    private PersonService personService;

    public void loadAbstractsUploadUserFullName(List<ProposalAbstract> abstracts) {
        for (ProposalAbstract curAbstract : abstracts) {
            Person person = getPersonService().getPersonByPrincipalName(curAbstract.getUploadUserDisplay());
            if (person != null) {
                curAbstract.setUploadUserFullName(person.getName());
            } else {
                curAbstract.setUploadUserFullName(curAbstract.getUploadUserDisplay() + "(not found)");
            }
        }
    }

    protected PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
    

}
