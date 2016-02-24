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
package org.kuali.kra.iacuc.species.exception.rule;

import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class adds rule for adding new <code>ProtocolException</code> object
 */
public interface AddProtocolExceptionRule extends BusinessRule {
    
    /**
     * This method evaluates to true if ProtocolException objects satisfy required fields and business rules.
     * @param addProtocolExceptionEvent
     * @return boolean true for valid object and false for invalid entry
     */
    public boolean processAddProtocolExceptionBusinessRules(AddProtocolExceptionEvent addProtocolExceptionEvent);
    
}
