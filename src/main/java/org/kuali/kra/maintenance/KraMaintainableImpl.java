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
package org.kuali.kra.maintenance;

import org.kuali.core.maintenance.KualiMaintainableImpl;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * This class...
 */
public class KraMaintainableImpl extends KualiMaintainableImpl {

    /**
     *
     * @see org.kuali.core.maintenance.Maintainable#prepareForSave()
     */
    @Override
    public void prepareForSave() {
        super.prepareForSave();
        if ((businessObject != null) && (businessObject instanceof KraPersistableBusinessObjectBase)) {

            String updateUser = GlobalVariables.getUserSession().getLoggedInUserNetworkId();

            // Since the UPDATE_USER column is only VACHAR(8), we need to truncate this string if it's longer than 8 characters
            if (updateUser.length() > 8) {
                updateUser = updateUser.substring(0, 8);
            }
            
            ((KraPersistableBusinessObjectBase)businessObject).setUpdateUser(updateUser);
            ((KraPersistableBusinessObjectBase)businessObject).setUpdateUserSet(true);
        }
    }

}
