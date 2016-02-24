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
package org.kuali.kra.committee.document.authorizer;

import org.kuali.coeus.common.committee.impl.document.authorizer.CommitteeActionAuthorizerBase;
import org.kuali.kra.infrastructure.PermissionConstants;

/**
 * The Committee Action Authorizer checks to see if the user has 
 * permission to perform committee actions. 
 */
public class CommitteeActionAuthorizer extends CommitteeActionAuthorizerBase {

    @Override
    protected String getPermissionNameForPerformCommitteeActionsCodeHook() {
        return PermissionConstants.PERFORM_COMMITTEE_ACTIONS;
    }
}
