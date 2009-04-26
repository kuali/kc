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

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.rice.shim.UniversalUserDao;
import org.kuali.kra.rice.shim.UniversalUserService;
import org.kuali.kra.rice.shim.UserNotFoundException;
import org.kuali.kra.rice.shim.UuId;
import org.kuali.kra.rice.shim.WorkflowUser;
import org.kuali.rice.kew.user.AuthenticationUserId;
import org.kuali.rice.kew.user.UserId;
import org.kuali.rice.kew.xml.UserXmlParser;

/**
 * The KRA Universal User Service overrides the Rice Universal User Service
 * in order to re-introduce the reading of a XML files containing workflow users.
 * These XML workflow users are stored in an in-memory cache.  They are never
 * written to the database.  This feature was available in the BaseUserService
 * but was removed by the Universal User Service for reasons not stated.  We
 * need this feature because we use it when loading workflow users for our
 * unit tests.
 */
public class KraUniversalUserServiceImpl implements UniversalUserService {
    
    private UniversalUserDao universalUserDao;
    
    private static final Logger LOG = Logger.getLogger(KraUniversalUserServiceImpl.class);
    
    public UniversalUser getUniversalUser(UserId userId) throws UserNotFoundException {
        UniversalUser user = universalUserDao.getUser(userId);
        if (user == null) {
            throw new UserNotFoundException("unable to find universaluser for userId '" + userId + "'");
        }
        return user;
    }
    
    public UniversalUser getUniversalUser(String personUniversalIdentifier) throws UserNotFoundException {
        return getUniversalUser(new UuId(personUniversalIdentifier));
    }
    
    public UniversalUser getUniversalUserByAuthenticationUserId(String authenticationUserId ) throws UserNotFoundException {
        return getUniversalUser(new AuthenticationUserId(authenticationUserId));
    }

    /**
     * Create a universal user from a workflow user.
     * @param user the workflow user
     * @return the universal user
     */
    private UniversalUser convertWorkflowUser(WorkflowUser user) {
        UniversalUser kUser = new UniversalUser();
        kUser.setPersonPayrollIdentifier(user.getEmplId().getEmplId());
        kUser.setPersonEmailAddress(user.getEmailAddress());
        kUser.setPersonName(user.getDisplayName());
        kUser.setPersonUserIdentifier(user.getAuthenticationUserId().getAuthenticationId());
        kUser.setPersonUniversalIdentifier(user.getWorkflowId());
        return kUser;
    }

    /**
     * Save the workflow user by storing it in the cache. 
     * We never use this service to write to the database.
     * @see org.kuali.core.service.impl.UniversalUserServiceImpl#save(edu.iu.uis.eden.user.WorkflowUser)
     */
//    public void save(WorkflowUser user) {
//        addToCache(user);
//    }
//    
//    /**
//     * Get a workflow user.
//     * @see org.kuali.core.service.impl.UniversalUserServiceImpl#getWorkflowUser(edu.iu.uis.eden.user.UserId)
//     */
//    public WorkflowUser getWorkflowUser(UserId userId) throws EdenUserNotFoundException {
//        WorkflowUser user = getFromCache(userId);
//        if (user == null) {
//            user = super.getWorkflowUser(userId);
//        }
//        return user;
//    }
//    
//    /**
//     * Get a workflow user.
//     * @see org.kuali.core.service.impl.UniversalUserServiceImpl#getWorkflowUser(edu.iu.uis.eden.clientapp.vo.UserIdDTO)
//     */
//    public WorkflowUser getWorkflowUser(UserIdDTO userId) throws EdenUserNotFoundException {
//        if (userId instanceof NetworkIdDTO) {
//            return getWorkflowUser(new AuthenticationUserId(userId.toString()));
//        } else if (userId instanceof EmplIdVO) {
//            return getWorkflowUser(new EmplId(userId.toString()));
//        } else if (userId instanceof UuIdVO) {
//            return getWorkflowUser(new UuId(userId.toString()));
//        } else if (userId instanceof WorkflowIdVO) {
//            return getWorkflowUser(new WorkflowUserId(userId.toString()));
//        }
//        throw new UnsupportedOperationException("Id type given to dao that is not supported. " + userId);
//    }
    
    /**
     * Load XML workflow users that are in the input stream.
     * @see org.kuali.core.service.impl.UniversalUserServiceImpl#loadXml(java.io.InputStream, edu.iu.uis.eden.user.WorkflowUser)
     */
    public void loadXml(InputStream stream, WorkflowUser user) {
        try {
            new UserXmlParser().parseUsers(stream);
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException)e;
            }
            throw new RuntimeException("Caught Exception parsing user xml.", e);
        }
    }

    public UniversalUserDao getUniversalUserDao() {
        return universalUserDao;
    }

    public void setUniversalUserDao(UniversalUserDao universalUserDao) {
        this.universalUserDao = universalUserDao;
    }
    
}
