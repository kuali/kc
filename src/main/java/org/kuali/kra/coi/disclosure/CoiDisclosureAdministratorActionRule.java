/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.coi.disclosure;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;


public class CoiDisclosureAdministratorActionRule {

    private static final String ADMIN_ERRORS = "coiAdminActionErrors";
    
    public boolean isValidStatus(String disclosureStatus, String dispositionStatus) {
        boolean isValid = true;
        if (StringUtils.isBlank(disclosureStatus)) {
            GlobalVariables.getMessageMap().putError(ADMIN_ERRORS, KeyConstants.ERROR_COI_DISCLOSURE_STATUS_REQUIRED);    
            isValid = false;
        }
        if (StringUtils.isBlank(dispositionStatus)) {
            GlobalVariables.getMessageMap().putError(ADMIN_ERRORS, KeyConstants.ERROR_COI_DISPOSITON_STATUS_REQUIRED); 
            isValid = false;
        }
        return isValid;
    }

}
