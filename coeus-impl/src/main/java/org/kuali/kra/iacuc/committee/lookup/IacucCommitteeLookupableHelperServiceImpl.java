/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.iacuc.committee.lookup;

import org.kuali.coeus.common.committee.impl.bo.CommitteeType;
import org.kuali.coeus.common.committee.impl.lookup.CommitteeLookupableHelperServiceImplBase;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeMembership;
import org.kuali.kra.iacuc.committee.document.CommonCommitteeDocument;
import org.kuali.kra.infrastructure.PermissionConstants;

public class IacucCommitteeLookupableHelperServiceImpl extends CommitteeLookupableHelperServiceImplBase<IacucCommittee, CommonCommitteeDocument> {


    private static final long serialVersionUID = -1230794939567685359L;

    @Override
    protected String getCommitteeTypeCodeHook() {
        return CommitteeType.IACUC_TYPE_CODE;
    }

    @Override
    protected String getCommitteeMembershipFullyQualifiedClassNameHook() {
        return IacucCommitteeMembership.class.getName();
    }

    @Override
    protected String getHtmlAction() {
        return "iacucCommitteeCommittee.do";
    }

    @Override
    protected String getDocumentTypeName() {
        return "CommonCommitteeDocument";
    }

    @Override
    protected String getViewCommitteePermissionNameHook() {
        return PermissionConstants.VIEW_IACUC_COMMITTEE;
    }

    @Override
    protected String getModifyCommitteePermissionNameHook() {
        return PermissionConstants.MODIFY_IACUC_COMMITTEE;
    }

    @Override
    protected Class<CommonCommitteeDocument> getCommitteeDocumentBOClassHook() {
        return CommonCommitteeDocument.class;
    }

    @Override
    protected Class<IacucCommittee> getCommitteeBOClassHook() {
        return IacucCommittee.class;
    }


}
