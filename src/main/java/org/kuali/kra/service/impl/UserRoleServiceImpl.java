/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.service.impl;

import java.util.List;

import org.kuali.kra.bo.UserRole;
import org.kuali.kra.dao.UserRoleDao;
import org.kuali.kra.service.UserRoleService;

public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleDao userRoleDao;
    
    public List<UserRole> getUserRoles(String userId) {
        return userRoleDao.getUserRoles(userId);
    }

    public List<UserRole> getUserRoles(String userId, String rightId) {
        return userRoleDao.getUserRoles(userId, rightId);
    }

    /**
     * Gets the userRoleDao attribute. 
     * @return Returns the userRoleDao.
     */
    public UserRoleDao getUserRoleDao() {
        return userRoleDao;
    }

    /**
     * Sets the userRoleDao attribute value.
     * @param userRoleDao The userRoleDao to set.
     */
    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }



}
