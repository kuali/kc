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
package org.kuali.coeus.propdev.impl.person.masschange;

import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;

import java.util.List;

/**
 * Defines the service interface for performing a Person Mass Change on Proposal Developments.
 */
public interface ProposalDevelopmentPersonMassChangeService {
    
    /**
     * Returns the Proposal Developments that would have a Person Mass Change performed on them.
     * 
     * @param personMassChange the Person Mass Change to be performed
     * @return the Proposal Developments that would have a Person Mass Change performed on them
     */
    List<DevelopmentProposal> getProposalDevelopmentChangeCandidates(PersonMassChange personMassChange);
    
    /**
     * Performs the Person Mass Change on the Proposal Developments.
     * 
     * @param personMassChange the Person Mass Change to be performed
     * @param proposalDevelopmentChangeCandidates the Proposal Developments to perform a Person Mass Change on
     */
    void performPersonMassChange(PersonMassChange personMassChange, List<DevelopmentProposal> proposalDevelopmentChangeCandidates);

}
