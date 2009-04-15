/*
 * Copyright 2006-2009 The Kuali Foundation
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
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.exceptions.UserNotFoundException;
import org.kuali.core.service.impl.UniversalUserServiceImpl;

import edu.iu.uis.eden.clientapp.vo.EmplIdVO;
import edu.iu.uis.eden.clientapp.vo.NetworkIdVO;
import edu.iu.uis.eden.clientapp.vo.UserIdVO;
import edu.iu.uis.eden.clientapp.vo.UuIdVO;
import edu.iu.uis.eden.clientapp.vo.WorkflowIdVO;
import edu.iu.uis.eden.exception.EdenUserNotFoundException;
import edu.iu.uis.eden.user.AuthenticationUserId;
import edu.iu.uis.eden.user.BaseWorkflowUser;
import edu.iu.uis.eden.user.EmplId;
import edu.iu.uis.eden.user.UserId;
import edu.iu.uis.eden.user.UuId;
import edu.iu.uis.eden.user.WorkflowUser;
import edu.iu.uis.eden.user.WorkflowUserId;
import edu.iu.uis.eden.xml.UserXmlHandler;

/**
 * The KRA Universal User Service overrides the Rice Universal User Service
 * in order to re-introduce the reading of a XML files containing workflow users.
 * These XML workflow users are stored in an in-memory cache.  They are never
 * written to the database.  This feature was available in the BaseUserService
 * but was removed by the Universal User Service for reasons not stated.  We
 * need this feature because we use it when loading workflow users for our
 * unit tests.
 */
public class KraUniversalUserServiceImpl extends UniversalUserServiceImpl {
    
    private static final Logger LOG = Logger.getLogger(KraUniversalUserServiceImpl.class);

    /**
     * Must check to see if the user was placed into the workflow cache.  If
     * so, we will build a user based upon that entry; otherwise we will get the
     * user from the database.
     * @see org.kuali.core.service.impl.UniversalUserServiceImpl#getUniversalUser(org.kuali.core.bo.user.UserId)
     */
    public UniversalUser getUniversalUser(org.kuali.core.bo.user.UserId userId) throws UserNotFoundException {
        
        UserId workflowUserId;
        
        if (userId instanceof org.kuali.core.bo.user.AuthenticationUserId) {
            workflowUserId = new AuthenticationUserId(userId.toString());
        } else if (userId instanceof org.kuali.core.bo.user.PersonPayrollId) {
            workflowUserId = new EmplId(userId.toString());
        } else if (userId instanceof org.kuali.core.bo.user.UuId) {
            workflowUserId = new UuId(userId.toString());
        } else {
            throw new UnsupportedOperationException("Id type is not supported. " + userId.getClass());
        } 
        WorkflowUser user = getFromCache(workflowUserId); 
        if (user == null) {
            return super.getUniversalUser(userId);
        } else {
            return convertWorkflowUser(user);
        }
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
    public void save(WorkflowUser user) {
        addToCache(user);
    }
    
    /**
     * Get a workflow user.
     * @see org.kuali.core.service.impl.UniversalUserServiceImpl#getWorkflowUser(edu.iu.uis.eden.user.UserId)
     */
    public WorkflowUser getWorkflowUser(UserId userId) throws EdenUserNotFoundException {
        WorkflowUser user = getFromCache(userId);
        if (user == null) {
            user = super.getWorkflowUser(userId);
        }
        return user;
    }
    
    /**
     * Get a workflow user.
     * @see org.kuali.core.service.impl.UniversalUserServiceImpl#getWorkflowUser(edu.iu.uis.eden.clientapp.vo.UserIdVO)
     */
    public WorkflowUser getWorkflowUser(UserIdVO userId) throws EdenUserNotFoundException {
        if (userId instanceof NetworkIdVO) {
            return getWorkflowUser(new AuthenticationUserId(userId.toString()));
        } else if (userId instanceof EmplIdVO) {
            return getWorkflowUser(new EmplId(userId.toString()));
        } else if (userId instanceof UuIdVO) {
            return getWorkflowUser(new UuId(userId.toString()));
        } else if (userId instanceof WorkflowIdVO) {
            return getWorkflowUser(new WorkflowUserId(userId.toString()));
        }
        throw new UnsupportedOperationException("Id type given to dao that is not supported. " + userId);
    }
    
    /**
     * Load XML workflow users that are in the input stream.
     * @see org.kuali.core.service.impl.UniversalUserServiceImpl#loadXml(java.io.InputStream, edu.iu.uis.eden.user.WorkflowUser)
     */
    public void loadXml(InputStream stream, WorkflowUser user) {
        try {
            List parsedUsers = new UserXmlHandler().parseUserEntries(this, stream);
            for(Iterator iter = parsedUsers.iterator(); iter.hasNext();) {
                save((BaseWorkflowUser) iter.next());
            }
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException)e;
            }
            throw new RuntimeException("Caught Exception parsing user xml.", e);
        }
    }
}
