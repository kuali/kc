/*
 * Copyright 2006-2007 The Kuali Foundation.
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

import java.sql.Timestamp;

import org.kuali.core.maintenance.KualiMaintainableImpl;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class KraMaintainableImpl extends KualiMaintainableImpl {
    private static final long serialVersionUID = 4814145799502207182L;

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(KraMaintainableImpl.class);

    /**
     * @see org.kuali.core.maintenance.Maintainable#prepareForSave()
     */
    @Override
    public void prepareForSave() {
        if ((businessObject != null) && (businessObject instanceof KraPersistableBusinessObjectBase)) {
        	Timestamp updateTimestamp = new Timestamp(System.currentTimeMillis());

        	((KraPersistableBusinessObjectBase)businessObject).setUpdateTimestamp(updateTimestamp);
        	((KraPersistableBusinessObjectBase)businessObject).setUpdateUser("kradev");
        }
    }

}
