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
package org.kuali.coeus.propdev.impl.person;

import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.rice.krad.bo.BusinessObject;

/**
 * Interface for rule implementations to go from where an action changes a <code>{@link ProposalPerson}</code>
 */
public interface ChangeKeyPersonRule extends org.kuali.rice.krad.rules.rule.BusinessRule {

    /**
     * To process Change event business rules for <code>{@link ProposalPerson}</code> instances. Any <code>{@link BusinessObject}</code>
     * can change the state of a <code>{@link ProposalPerson}</code> instance. This <code>{@link BusinessObject}</code> instance is considered
     *  to be the <code>source</code> of the event.
     * 
     * @param proposalPerson
     * @param source the event source acting on the rule
     * @return boolean pass or fail
     */
    public boolean processChangeKeyPersonBusinessRules(ProposalPerson proposalPerson, BusinessObject source,int index);

}
