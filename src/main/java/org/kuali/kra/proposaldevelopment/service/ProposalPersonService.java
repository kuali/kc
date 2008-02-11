/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.service;

import org.kuali.kra.bo.Person;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

public interface ProposalPersonService {
    public String getPersonName(ProposalDevelopmentDocument doc, String userId);

    public Person getPerson(String loggedInUser);

    /**
     * Retrieve a persisted <code>{@link ProposalPerson}</code> instance using the <code>proposalNumber</code>
     * and <code>proposalPersonNumber</code> 
     * 
     * @param proposalNumber
     * @param proposalPersonNumber
     */
    public ProposalPerson getProposalPersonById(String proposalNumber, Integer proposalPersonNumber);
}
