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

import org.apache.log4j.Logger;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.rice.shim.UniversalUserDao;
import org.kuali.kra.rice.shim.UniversalUserService;
import org.kuali.rice.kew.user.AuthenticationUserId;
import org.kuali.rice.kew.user.UserId;

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
    
    public UniversalUser getUniversalUser(UserId userId) throws Exception {
        UniversalUser user = universalUserDao.getUser(userId);
        if (user == null) {
            throw new Exception("unable to find universaluser for userId '" + userId + "'");
        }
        return user;
    }
    
    public UniversalUser getUniversalUserByAuthenticationUserId(String authenticationUserId ) throws Exception {
        return getUniversalUser(new AuthenticationUserId(authenticationUserId));
    }

    public UniversalUserDao getUniversalUserDao() {
        return universalUserDao;
    }

    public void setUniversalUserDao(UniversalUserDao universalUserDao) {
        this.universalUserDao = universalUserDao;
    }
    
}
