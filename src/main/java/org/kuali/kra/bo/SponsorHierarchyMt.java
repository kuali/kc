/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.util.GlobalVariables;

public class SponsorHierarchyMt extends SponsorHierarchy {

    // override the following methods for dwr-ajax.  sometimes dwr-ajax has usersession as null ??
    @Override
    protected void prePersist() {
        if (StringUtils.isEmpty(this.getObjectId())) {
            this.setObjectId(UUID.randomUUID().toString());
        }
        //super.prePersist();
        setUpdateFields();
    }

    @Override
    protected void preUpdate() {
        if (StringUtils.isEmpty(this.getObjectId())) {
            this.setObjectId(UUID.randomUUID().toString());
        }
        //super.preUpdate();
        setUpdateFields();
    }
    
    private void setUpdateFields() {
        if (!isUpdateUserSet()) {
            String updateUser;
            if (GlobalVariables.getUserSession() == null) {
                updateUser = "quickstart";
            } else {
                updateUser = GlobalVariables.getUserSession().getPrincipalName();
            }

            setUpdateUser(updateUser);
        }
        setUpdateTimestamp(((DateTimeService)KraServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME)).getCurrentTimestamp());
    }

}
