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
