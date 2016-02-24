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
package org.kuali.kra.iacuc.committee.bo;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeType;
import org.kuali.kra.iacuc.committee.document.CommonCommitteeDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;

public class IacucCommittee extends CommitteeBase<IacucCommittee, CommonCommitteeDocument, IacucCommitteeSchedule> {


    private static final long serialVersionUID = 2031629954610125464L;

    @Override
    protected String getProtocolCommitteeTypeCodehook() {
        return CommitteeType.IACUC_TYPE_CODE;
    }

    @Override
    protected String getProtocolReviewerRoleHook() {
        return RoleConstants.IACUC_PROTOCOL_REVIEWER;
    }

    @Override
    protected String getAdminRoleHook() {
        return RoleConstants.IACUC_ADMINISTRATOR;
    }

    @Override
    protected String getModuleNamespaceCodeHook() {
        return Constants.MODULE_NAMESPACE_IACUC;
    }

    @Override
    protected IacucCommittee getThisHook() {
        return this;
    }

}
