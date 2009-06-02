/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.rice;

import org.apache.log4j.Logger;
import org.kuali.kra.bo.Person;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.rice.shim.UniversalUserDao;
import org.kuali.kra.service.PersonService;
import org.kuali.rice.kew.user.UserId;
import org.kuali.rice.kns.dao.impl.PlatformAwareDaoBaseOjb;

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
public class KraUniversalUserDaoImpl extends PlatformAwareDaoBaseOjb implements UniversalUserDao {

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
    public UniversalUser getUser(UserId userId) throws Exception {
        
        UniversalUser user = null;
        String username = userId.getId();
        
        Person person = personService.getPersonByName(username);
        if (person == null) {
            throw new Exception();
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
        return user;
    }

    /**
     * Get a Workflow User.
     * @see org.kuali.core.dao.UniversalUserDao#getWorkflowUser(edu.iu.uis.eden.user.UserId)
     */
//    public WorkflowUser getWorkflowUser(edu.iu.uis.eden.user.UserId userId) throws EdenUserNotFoundException {
//        
//        WorkflowUser workflowUser = null;
//        String username = userId.getId();
//       
//        Person person = personService.getPersonByName(username);
//        if (person == null) {
//            throw new EdenUserNotFoundException();
//        }
//        workflowUser = createWorkflowUser(person);
//        return workflowUser;
//    }

    /**
     * @see org.kuali.core.dao.UniversalUserDao#save(edu.iu.uis.eden.user.WorkflowUser)
     */
//    public void save(WorkflowUser workflowUser) {
//        throw new UnsupportedOperationException("KraUniversalUserDaoImpl doesn't support saving users");
//    }

    /**
     * @see org.kuali.core.dao.UniversalUserDao#search(edu.iu.uis.eden.user.WorkflowUser, boolean)
     */
//    public List search(WorkflowUser user, boolean useWildCards) {
//        Criteria criteria = new Criteria();
//        if (user != null) {
//            if ((user.getAuthenticationUserId() != null) && StringUtils.isNotBlank(user.getAuthenticationUserId().getAuthenticationId())) {
//                criteria.addLike("userName", user.getAuthenticationUserId().getAuthenticationId().trim() + "%");
//            }
//            if ((user.getEmplId() != null) && StringUtils.isNotBlank(user.getEmplId().getEmplId())) {
//                criteria.addLike("userName", user.getEmplId().getEmplId().trim() + "%");
//            }
//            if ((user.getUuId() != null) && StringUtils.isNotBlank(user.getUuId().getUuId())) {
//                criteria.addLike("userName", user.getUuId().getUuId().trim() + "%");
//            }
//            if ((user.getWorkflowUserId() != null) && StringUtils.isNotBlank(user.getWorkflowUserId().getWorkflowId())) {
//                criteria.addLike("userName", user.getWorkflowUserId().getWorkflowId().trim() + "%");
//            }
//            if (StringUtils.isNotBlank(user.getGivenName())) {
//                criteria.addLike("firstName", user.getGivenName().trim() + "%");
//            }
//            if (StringUtils.isNotBlank(user.getLastName())) {
//                criteria.addLike("lastName", user.getLastName().trim() + "%");
//            }
//            if (StringUtils.isNotBlank(user.getDisplayName())) {
//                criteria.addLike("fullName", user.getDisplayName().trim().toUpperCase().trim() + "%");
//            }
//            if (StringUtils.isNotBlank(user.getEmailAddress())) {
//                criteria.addLike("emailAddress", user.getEmailAddress().trim() + "%");
//            }
//        }
//        
//        Integer searchResultsLimit = getSearchResultsLimit(Person.class);
//        if (searchResultsLimit != null) {
//            applySearchResultsLimit(Person.class, criteria, getDbPlatform());
//        }
//        
//        List<WorkflowUser> workflowUsers = new ArrayList<WorkflowUser>();
//        Collection<Person> persons = getPersistenceBrokerTemplate().getCollectionByQuery(QueryFactory.newQuery(Person.class, criteria));
//        for (Person person : persons) {
//            workflowUsers.add(createWorkflowUser(person));
//        }
//        return workflowUsers;
//    }
    
    /**
     * Convert a person into a workflow user.
     * @param person [in] the person
     * @return the workflow user
     */
//    private WorkflowUser createWorkflowUser(Person person) {
//        WorkflowUser workflowUser = new BaseWorkflowUser();
//        workflowUser.setAuthenticationUserId(new AuthenticationUserId(person.getUserName()));
//        workflowUser.setWorkflowUserId(new WorkflowUserId(person.getUserName()));
//        workflowUser.setEmplId(new EmplId(person.getUserName()));
//        workflowUser.setUuId(new UuId(person.getUserName()));
//        workflowUser.setDisplayName(person.getFullName());
//        workflowUser.setGivenName(person.getFirstName());
//        workflowUser.setLastName(person.getLastName());
//        workflowUser.setEmailAddress(person.getEmailAddress());
//        return workflowUser;
//    }
}

