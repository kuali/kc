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
package org.kuali.coeus.sys.framework.workflow;

import org.kuali.rice.kew.api.action.ActionTaken;

public interface LastActionService {

    /**
     * This method finds the last user action taken.  It will ignore actions take by the system user 'kr'.
     * If no actions are found then null is returned.
     *
     * @param routeHeaderId the document id.  if blank is passed in then null is returned.
     * @return the last user action taken or null.
     */
    ActionTaken findLastUserActionTaken(String routeHeaderId);

    /**
     * This method finds the last user action taken's principal id.  It will ignore actions take by the system user 'kr'.
     * If no actions are found then null is returned.
     *
     * @param routeHeaderId the document id.  if blank is passed in then null is returned.
     * @return the last user action taken's principal id or null.
     */
    String findLastUserActionTakenPrincipalId(String routeHeaderId);
}
