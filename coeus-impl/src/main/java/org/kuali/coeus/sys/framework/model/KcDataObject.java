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
package org.kuali.coeus.sys.framework.model;


import org.kuali.coeus.sys.api.model.GloballyUnique;
import org.kuali.coeus.sys.api.model.RecordedUpdate;
import org.kuali.coeus.sys.api.model.Versioned;

import java.sql.Timestamp;

/**
 * Kuali Coeus Data Objects have certain fields in common which are contained within this interface.
 */
public interface KcDataObject extends GloballyUnique, Versioned, RecordedUpdate {

    int UPDATE_USER_LENGTH = 60;

    void setObjectId(String objectId);

    void setVersionNumber(Long versionNumber);

    @Override
    Timestamp getUpdateTimestamp();

    void setUpdateTimestamp(Timestamp updateTimestamp);

    void setUpdateUser(String updateUser);

    boolean isUpdateUserSet();

    void setUpdateUserSet(boolean updateUserSet);
}
