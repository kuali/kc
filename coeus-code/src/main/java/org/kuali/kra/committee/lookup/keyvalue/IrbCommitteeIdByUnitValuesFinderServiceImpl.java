/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.kuali.coeus.common.committee.impl.bo.CommitteeType;
import org.kuali.coeus.common.committee.impl.lookup.keyvalue.CommitteeIdByUnitValuesFinderServiceImplBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;

public class IrbCommitteeIdByUnitValuesFinderServiceImpl extends CommitteeIdByUnitValuesFinderServiceImplBase<Committee> implements IrbCommitteeIdByUnitValuesFinderService {

    @Override
    protected String getCommitteeTypeCodeHook() {
        return CommitteeType.IRB_TYPE_CODE;
    }

    @Override
    protected Class<Committee> getCommitteeBOClassHook() {
        return Committee.class;
    }

    @Override
    protected String getAssignCommitteePermissionNamespaceHook() {
        return Constants.MODULE_NAMESPACE_PROTOCOL;
    }

    @Override
    protected String getAssignCommitteePermissionNameHook() {
        return PermissionConstants.ASSIGN_IRB_COMMITTEE;
    }

}
