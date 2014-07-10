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
package org.kuali.kra.committee.lookup;

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
public class CommitteeLookupableHelperServiceImpl extends CommitteeLookupableHelperServiceImplBase<Committee, CommitteeDocument> {


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
    protected Class<CommitteeDocument> getCommitteeDocumentBOClassHook() {
        return CommitteeDocument.class;
    }

    @Override
    protected Class<Committee> getCommitteeBOClassHook() {
        return Committee.class;
    }
}
