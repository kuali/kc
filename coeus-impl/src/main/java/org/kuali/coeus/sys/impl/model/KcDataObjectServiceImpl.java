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
package org.kuali.coeus.sys.impl.model;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.model.KcDataObject;
import org.kuali.coeus.sys.framework.model.KcDataObjectService;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.UUID;

@Component("kcDataObjectService")
public class KcDataObjectServiceImpl implements KcDataObjectService {

    @Autowired
    @Qualifier("identityService")
    private IdentityService identityService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Override
    public void initVersionNumberForPersist(KcDataObject kcDataObject) {
        kcDataObject.setVersionNumber(0L);
    }

    @Override
    public void initUpdateFieldsForPersist(KcDataObject kcDataObject) {
        setUpdateFields(kcDataObject);
    }

    @Override
    public void initObjectIdForPersist(KcDataObject kcDataObject) {
        if (StringUtils.isBlank(kcDataObject.getObjectId())) {
            kcDataObject.setObjectId(UUID.randomUUID().toString());
        }
    }

    @Override
    public void initVersionNumberForUpdate(KcDataObject kcDataObject) {
        // Optimistic Locking has been disabled so adding null check and setting version number to 0
        // If we ever turn Optimistic Locking back on, we need to remove this code
        if (kcDataObject.getVersionNumber() == null) {
            kcDataObject.setVersionNumber(0L);
        }
    }

    @Override
    public void initUpdateFieldsForUpdate(KcDataObject kcDataObject) {
        setUpdateFields(kcDataObject);
    }

    @Override
    public void initObjectIdForUpdate(KcDataObject kcDataObject) {
        if (StringUtils.isBlank(kcDataObject.getObjectId())) {
            kcDataObject.setObjectId(UUID.randomUUID().toString());
        }
    }

    private void setUpdateFields(KcDataObject kcDataObject) {
        if (!kcDataObject.isUpdateUserSet() && globalVariableService.getUserSession() != null) {
            kcDataObject.setUpdateUser(globalVariableService.getUserSession().getPrincipalName());
        }

        kcDataObject.setUpdateTimestamp(new Timestamp(new java.util.Date().getTime()));

        if (kcDataObject.getUpdateUser() != null) {
            kcDataObject.setUpdateUser(StringUtils.substring(kcDataObject.getUpdateUser(), 0, KcDataObject.UPDATE_USER_LENGTH));
        }
    }

    public IdentityService getIdentityService() {
        return identityService;
    }

    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}
