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
package org.kuali.kra.bo;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.Guid;

public class SponsorHierarchyMt extends SponsorHierarchy {

    // override the following methods for dwr-ajax.  sometimes dwr-ajax has usersession as null ??
    @Override
    public void beforeInsert(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        if (StringUtils.isEmpty(this.getObjectId())) {
            this.setObjectId(new Guid().toString());
        }
        //super.beforeInsert(persistenceBroker);
        setUpdateFields();
    }

    @Override
    public void beforeUpdate(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        if (StringUtils.isEmpty(this.getObjectId())) {
            this.setObjectId(new Guid().toString());
        }
        //super.beforeUpdate(persistenceBroker);
        setUpdateFields();
    }
    
    private void setUpdateFields() {
        if (!isUpdateUserSet()) {
            String updateUser;
            if (GlobalVariables.getUserSession() == null) {
                updateUser="quickstart";
            } else {
                updateUser = GlobalVariables.getUserSession().getPrincipalName();
            }

            // Since the UPDATE_USER column is only VACHAR(60), we need to truncate this string if it's longer than 60 characters
            if (updateUser.length() > 60) {
                updateUser = updateUser.substring(0, 60);
            }

            setUpdateUser(updateUser);
        }
        setUpdateTimestamp(((DateTimeService)KraServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME)).getCurrentTimestamp());
    }

}
