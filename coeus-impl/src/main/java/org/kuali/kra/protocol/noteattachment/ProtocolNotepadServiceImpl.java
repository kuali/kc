/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.noteattachment;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;

import java.util.List;

public class ProtocolNotepadServiceImpl implements ProtocolNotepadService {

    private PersonService personService;
    
    protected final Log LOG = LogFactory.getLog(getClass()); 
    private static final String PERSON_NOT_FOUND_FORMAT_STRING = "%s (not found)";
   
    @Override
    public void setProtocolNotepadUpdateUsersName(List<ProtocolNotepadBase> protocolNotepads) {
        for (ProtocolNotepadBase pnp : protocolNotepads) {
            if (LOG.isDebugEnabled()) { 
                LOG.debug(String.format("Looking up person for update user %s.", pnp.getUpdateUser()));
            }
            Person person = personService.getPersonByPrincipalName(pnp.getUpdateUser());
            pnp.setUpdateUserFullName(person==null?String.format(PERSON_NOT_FOUND_FORMAT_STRING, pnp.getUpdateUser()):person.getName());
            
            if (StringUtils.isNotBlank(pnp.getCreateUser())) {
                Person creator = personService.getPersonByPrincipalName(pnp.getCreateUser());
                pnp.setCreateUserFullName(creator==null?String.format(PERSON_NOT_FOUND_FORMAT_STRING, pnp.getCreateUser()):creator.getName());
            } else {
                pnp.setCreateUserFullName("");
            }
        }
    }

    /**
     * Gets the personService attribute. 
     * @return Returns the personService.
     */
    public PersonService getPersonService() {
        return personService;
    }

    /**
     * Sets the personService attribute value.
     * @param personService The personService to set.
     */
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

}
