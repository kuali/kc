/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.abstrct;

import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import java.util.List;

@Component("proposalAbstractsService")
public class ProposalAbstractsServiceImpl implements ProposalAbstractsService {

    @Autowired
    @Qualifier("personService")
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
