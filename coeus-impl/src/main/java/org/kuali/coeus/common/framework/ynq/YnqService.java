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
package org.kuali.coeus.common.framework.ynq;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.ynq.ProposalYnq;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;

import java.util.List;

public interface YnqService {
    /**
     * This method returns list of YNQ Explanation Types.
     * @return List of explanation types.
     */
    public List<YnqExplanationType> getYnqExplanationTypes();

    /**
     * This method returns list of YNQ - filter by question type.
     * @return List of questions.
     */
    public List<Ynq> getYnq(String questionType);

    /**
     * This method is used to populate questions.
     * 
     */
    public void populateProposalQuestions(List<ProposalYnq> proposalYnqs, List<YnqGroupName> ynqGroupNames, ProposalDevelopmentDocument document);
    
    public ProposalPerson getPersonYNQ(ProposalPerson proposalPerson, ProposalDevelopmentDocument document);

}
