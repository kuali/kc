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
package org.kuali.kra.protocol.actions.decision;

import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class defines the methods needed for committee decision rules.
 */
public interface ExecuteCommitteeDecisionRule<CD extends CommitteeDecision<?> > extends BusinessRule {
   
    /**
     * 
     * This method will check for valid user input and attach error message to fields as needed.
     * @param document the ProtocolDocumentBase
     * @param actionBean a CommitteeDecision bean
     * @return a boolean as to whether the user input is valid
     */
    boolean proccessCommitteeDecisionRule(ProtocolDocumentBase document, CD actionBean);

}
