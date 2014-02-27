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
package org.kuali.kra.committee.bo;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeType;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;

/**
 * Represents a single committee within an institution.
 */
@SuppressWarnings("serial")
public class Committee extends CommitteeBase<Committee, CommitteeDocument, CommitteeSchedule> {

    @Override
    protected String getProtocolCommitteeTypeCodehook() {
        return CommitteeType.IRB_TYPE_CODE;
    }

    @Override
    protected Committee getThisHook() {
        return this;
    }

    @Override
    protected String getProtocolReviewerRoleHook() {
        return RoleConstants.IRB_REVIEWER;
    }

    @Override
    protected String getAdminRoleHook() {
        return RoleConstants.IRB_ADMINISTRATOR;
    }

    @Override
    protected String getModuleNamespaceCodeHook() {
        return Constants.MODULE_NAMESPACE_PROTOCOL;
    }

}
