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
package org.kuali.kra.award.contacts;

import java.util.List;

import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.UnitAdministratorType;
import org.kuali.rice.core.api.util.KeyValue;

/**
 * This class finds Award Unit Contact Project Roles
 */
public class AwardCentralAdminProjectRolesValuesFinder extends AwardContactsProjectRoleValuesFinder {

    @Override
    protected Class<? extends ContactRole> getRoleType() {
        return UnitAdministratorType.class;
    }    
    
    /**
     * Override to not inlude empty selection.
     * 
     * This is a hack to make the silly Rule API behave properly. Because CentralAdmin contacts and unit Contacts are stored 
     * in the same collection, the Rule API will use the UnitContact BO type to build the error path when an error occurs.
     * This causes the CentralAdmin errors to show up in the UnitContact tab panel.
     * 
     * The only user error that can occur not selecting a project role. By removing the blank from the selection list
     * we can force a contact role to be applied to the CentralAdmin, thus preventing the user error. 
     * 
     * The Rule API is so moronic, there are untold numbers of hacks in the system to satisfy this API. Yes, this is just another one.
     * 
     * @see org.kuali.kra.award.contacts.AwardContactsProjectRoleValuesFinder#addEmptyKeyValuePair(java.util.List)
     */
    @Override
    protected void addEmptyKeyValuePair(List<KeyValue> keyValues) {
        // intentionally do nothing
    }
    
}
