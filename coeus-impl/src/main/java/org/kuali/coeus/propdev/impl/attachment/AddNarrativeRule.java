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
package org.kuali.coeus.propdev.impl.attachment;

import org.kuali.coeus.propdev.impl.attachment.AddNarrativeEvent;
import org.kuali.rice.krad.rules.rule.BusinessRule;


/**
 * Interface for adding proposal narratives
 *
 * @author kualidev@oncourse.iu.edu
 * @version 1.0
 */
public interface AddNarrativeRule extends BusinessRule {
    
    /**
     * Rule invoked upon adding a key person to a 
     * <code>{@link org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument}</code>
     *
     * @return boolean
     */
    public boolean processAddNarrativeBusinessRules(AddNarrativeEvent addNarrativeEvent);
}
