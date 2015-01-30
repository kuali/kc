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
package org.kuali.coeus.common.committee.impl.rule.event;

import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipRole;
import org.kuali.rice.krad.rules.rule.event.DocumentEvent;

public interface CommitteeMembershipRoleEvent extends DocumentEvent {

    /**
     * Get the <code>{@link CommitteeMembershipRole}</code> of this event.
     * 
     * @return <code>CommitteeMembershipRole</code>
     */
     CommitteeMembershipRole getCommitteeMembershipRole();
}
