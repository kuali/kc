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
package org.kuali.coeus.propdev.impl.abstrct;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * Defines the Business Rule for processing Proposal Abstracts.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface AbstractsRule extends BusinessRule {
    
    /**
     * Determines the legality of adding an abstract to the
     * given proposal development document.
     * 
     * @param document the proposal development document.
     * @param proposalAbstract the abstract to be added to the document.
     * @return true if the addition is valid; otherwise false.
     */
    public boolean processAddAbstractBusinessRules(ProposalDevelopmentDocument document, 
                                                   ProposalAbstract proposalAbstract);
}
