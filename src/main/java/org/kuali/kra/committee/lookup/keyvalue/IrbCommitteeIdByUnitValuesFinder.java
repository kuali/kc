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
package org.kuali.kra.committee.lookup.keyvalue;

import org.kuali.kra.committee.bo.CommitteeType;
import org.kuali.kra.infrastructure.RoleConstants;

public class IrbCommitteeIdByUnitValuesFinder extends CommitteeIdByUnitValuesFinder {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4302453726017564740L;

    @Override
    protected String getCommitteeTypeCodeHook() {
        return CommitteeType.IRB_TYPE_CODE;
    }

    @Override
    protected String getRoleNameHook() {
        return RoleConstants.IRB_ADMINISTRATOR;
    }

}
