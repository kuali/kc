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
