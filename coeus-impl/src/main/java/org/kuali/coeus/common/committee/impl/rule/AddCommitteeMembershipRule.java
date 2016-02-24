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
package org.kuali.coeus.common.committee.impl.rule;

import org.kuali.coeus.common.committee.impl.rule.event.AddCommitteeMembershipEvent;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * 
 * This interface addresses the adds rule for adding a new <code>CommitteeMembershipBase</code>
 * 
 * @author Kuali Research Administration Team (kc.dev@kuali.org)
 */
public interface AddCommitteeMembershipRule extends BusinessRule {
    
    /**
     * 
     * ProcessDefinitionDefinitiones the validation rules for an <code>{@link AddCommitteeMembershipEvent}</code>
     * 
     * @param addCommitteeMembershipEvent
     * @return <code>true</code> if valid, <code>false</code> otherwise
     */
    public boolean processAddCommitteeMembershipBusinessRules(AddCommitteeMembershipEvent addCommitteeMembershipEvent);
}
