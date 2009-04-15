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
package org.kuali.kra.rules;

import java.util.Map;

import org.kuali.RiceConstants;
import org.kuali.RiceKeyConstants;
import org.kuali.core.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.core.util.GlobalVariables;

public class KraMaintenanceDocumentRuleBase extends MaintenanceDocumentRuleBase {
    /**
     * 
     * This method to check pk does exist in table.  Maybe this should be in service instead in this rule base ?
     * @param clazz
     * @param fieldValues
     * @param errorField
     * @param errorParam
     * @return
     */
    protected boolean checkExistenceFromTable(Class clazz, Map fieldValues, String errorField, String errorParam) {
        boolean success = true;
        success = getBoService().countMatching(clazz, fieldValues) != 0;
        if (!success) {
            GlobalVariables.getErrorMap().putErrorWithoutFullErrorPath(RiceConstants.MAINTENANCE_NEW_MAINTAINABLE + errorField, RiceKeyConstants.ERROR_EXISTENCE, errorParam);
        }
        return success;
    }

}
