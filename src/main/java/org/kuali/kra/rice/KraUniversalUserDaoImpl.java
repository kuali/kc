/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.rice;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.bo.user.UserId;
import org.kuali.core.dao.UniversalUserDao;
import org.kuali.core.exceptions.UserNotFoundException;
import org.kuali.kra.bo.Person;
import org.kuali.kra.service.PersonService;

import edu.iu.uis.eden.exception.EdenUserNotFoundException;
import edu.iu.uis.eden.user.AuthenticationUserId;
import edu.iu.uis.eden.user.BaseWorkflowUser;
import edu.iu.uis.eden.user.EmplId;
import edu.iu.uis.eden.user.UuId;
import edu.iu.uis.eden.user.WorkflowUser;
import edu.iu.uis.eden.user.WorkflowUserId;

/**
 * The Universal User DAO is used by the Universal User Service to obtain the users
 * and workflow users from the database.  Only the DAO knows where the users are stored
 * in the database.  With respect to KRA, all users are currently stored in the PERSON
 * table.  Therefore, this DAO will use KRA's Person Service to retrieve users from the
 * PERSON table and construct the necessary UniversalUser and WorkflowUser objects.
 * 
 * A little background is in order to fully understand everything that is going on.  First,
 * there are two user services: the Universal User Service and the WorkFlow User Service (known
 * as enUserService).  By default, Rice has both user services configured to obtain user
 * information from the EN_USR_T table.  
 * 
 * It should be mentioned that KFA stores its users in the FS_UNIVERSAL_USR_T table.  Like
 * KRA, it uses a default DAO in order to access the FS_UNIVERSAL_USR_T table.  Now, since
 * both user services must access the same database table, KFS plays a little game.  Instead
 * of instantiating both services, it only instantiates the Universal User Service with its
 * DAO and sets up an alias for "enUserService" in the SpringBeans.xml.  The Universal User
 * Service is actually a superset of the Workflow User Service.  
 * 
 * Lastly, KRA also plays the same game as KFS by using an alias in the SpringBeans.xml for
 * the Workflow User Service.  
 */
public class KraUniversalUserDaoImpl implements UniversalUserDao {

    private static final Logger LOG = Logger.getLogger(KraUniversalUserDaoImpl.class);
    
    private PersonService personService;
    
    /**
     * Set the Person Service.  Injected by the Spring Framework.
     * @param personService [in] the Person Service
     */
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Get a Universal User based upon its user id, i.e. username.  
     * @see org.kuali.core.dao.UniversalUserDao#getUser(org.kuali.core.bo.user.UserId)
     */
    public UniversalUser getUser(UserId userId) throws UserNotFoundException {
        
        UniversalUser user = null;
        
        String username = userId.toString();
        if (StringUtils.equals(username, "KULUSER")) {
            user = new UniversalUser();
            user.setPersonUserIdentifier(username);
            user.setPersonPayrollIdentifier(username);
            user.setPersonUniversalIdentifier(username);
            user.setPersonEmailAddress("KULUSER@indiana.edu");
            user.setPersonName(username);
            user.setPersonFirstName(username);
            user.setPersonLastName(username);
            user.setFinancialSystemsEncryptedPasswordText("");
        } else {
            Person person = personService.getPersonByName(username);
            if (person == null) {
                throw new UserNotFoundException();
            }
            user = new UniversalUser();
            user.setPersonUserIdentifier(username);
            user.setPersonPayrollIdentifier(username);
            user.setPersonUniversalIdentifier(username);
            user.setPersonEmailAddress(person.getEmailAddress());
            user.setPersonName(person.getFullName());
            user.setPersonFirstName(person.getFirstName());
            user.setPersonLastName(person.getLastName());
            user.setFinancialSystemsEncryptedPasswordText(person.getPassword());
        }
        return user;
    }

    /**
     * Get a Workflow User.
     * @see org.kuali.core.dao.UniversalUserDao#getWorkflowUser(edu.iu.uis.eden.user.UserId)
     */
    public WorkflowUser getWorkflowUser(edu.iu.uis.eden.user.UserId userId) throws EdenUserNotFoundException {
        
        WorkflowUser workflowUser = null;
        String username = userId.getId();
        if (StringUtils.equals(username, "KULUSER")) {
            workflowUser = new BaseWorkflowUser();
            workflowUser.setAuthenticationUserId(new AuthenticationUserId(username));
            workflowUser.setWorkflowUserId(new WorkflowUserId(username));
            workflowUser.setEmplId(new EmplId(username));
            workflowUser.setUuId(new UuId(username));
            workflowUser.setDisplayName("KULUSER");
            workflowUser.setGivenName("KULUSER");
            workflowUser.setLastName("KULUSER");
            workflowUser.setEmailAddress("KULUSER@indiana.edu");
        } else {
            Person person = personService.getPersonByName(username);
            if (person == null) {
                throw new EdenUserNotFoundException();
            }
            workflowUser = new BaseWorkflowUser();
            workflowUser.setAuthenticationUserId(new AuthenticationUserId(username));
            workflowUser.setWorkflowUserId(new WorkflowUserId(username));
            workflowUser.setEmplId(new EmplId(username));
            workflowUser.setUuId(new UuId(username));
            workflowUser.setDisplayName(person.getFullName());
            workflowUser.setGivenName(person.getFirstName());
            workflowUser.setLastName(person.getLastName());
            workflowUser.setEmailAddress(person.getEmailAddress());
        }
        return workflowUser;
    }

    /**
     * @see org.kuali.core.dao.UniversalUserDao#save(edu.iu.uis.eden.user.WorkflowUser)
     */
    public void save(WorkflowUser workflowUser) {
        throw new UnsupportedOperationException("KraUniversalUserDaoImpl doesn't support saving users");
    }

    /**
     * @see org.kuali.core.dao.UniversalUserDao#search(edu.iu.uis.eden.user.WorkflowUser, boolean)
     */
    public List search(WorkflowUser user, boolean useWildCards) {
        throw new UnsupportedOperationException("KraUniversalUserDaoImpl doesn't support searching for users");
    }
}

