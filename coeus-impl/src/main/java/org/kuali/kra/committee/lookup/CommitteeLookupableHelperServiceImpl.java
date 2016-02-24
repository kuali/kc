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
package org.kuali.kra.committee.lookup;

import org.kuali.rice.krad.util.KRADConstants;

import org.kuali.coeus.common.committee.impl.bo.CommitteeType;
import org.kuali.coeus.common.committee.impl.lookup.CommitteeLookupableHelperServiceImplBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.infrastructure.PermissionConstants;

/**
 * 
 * This class is for committee lookup.
 */
public class CommitteeLookupableHelperServiceImpl extends CommitteeLookupableHelperServiceImplBase<Committee> {

    private static final String DOCHANDLER_LINK = "%s/DocHandler.do?command=displayDocSearchView&docId=%s";

    private static final long serialVersionUID = -3249634640550089590L;

    @Override
    protected String getCommitteeTypeCodeHook() {
        return CommitteeType.IRB_TYPE_CODE;
    }

    @Override
    protected String getCommitteeMembershipFullyQualifiedClassNameHook() {
        return CommitteeMembership.class.getName();
    }

    @Override
    protected String getHtmlAction() {
        return "committeeCommittee.do";
    }

    @Override
    protected String getCustomResumeEditUrl(final String editCommitteeDocId) {
        final String workflowUrl = getKualiConfigurationService().getPropertyValueAsString(KRADConstants.WORKFLOW_URL_KEY);
        return String.format(DOCHANDLER_LINK, workflowUrl, editCommitteeDocId);
    }


    @Override
    protected String getDocumentTypeName() {
        return "CommitteeDocument";
    }

    @Override
    protected String getViewCommitteePermissionNameHook() {
        return PermissionConstants.VIEW_COMMITTEE;
    }

    @Override
    protected String getModifyCommitteePermissionNameHook() {
        return PermissionConstants.MODIFY_COMMITTEE;
    }

    @Override
    protected Class<Committee> getCommitteeBOClassHook() {
        return Committee.class;
    }
}
