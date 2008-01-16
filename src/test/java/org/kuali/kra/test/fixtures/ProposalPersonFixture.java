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
package org.kuali.kra.test.fixtures;

import static org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.KEY_PERSON_ROLE;
import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE;

import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;

/**
 * Fixtures used as <code>{@link ProposalPerson}</code> instances for tests. This is NOT test data. Test data is loaded by scripts. These are 
 * test fixtures.
 */
public enum ProposalPersonFixture {
    INVESTIGATOR_SPLIT_ADDS_TO_ONE_HUNDRED("000000003", PRINCIPAL_INVESTIGATOR_ROLE),
    PHILIP_CO_INVESTIGATOR("000000002", CO_INVESTIGATOR_ROLE),
    BRYAN_CO_INVESTIGATOR("000000006", CO_INVESTIGATOR_ROLE),
    ANDY_KEY_PERSON("000000006", KEY_PERSON_ROLE);

    private ProposalPerson person;
    
    private ProposalPersonFixture() {
    }
 
    private ProposalPersonFixture(String personId, String roleId) {
        person = new ProposalPerson();
        person.setPersonId(personId);
        person.setProposalPersonRoleId(roleId);
        person.setPercentageEffort(new KualiDecimal(100.0));
    }

    public ProposalPerson getPerson() {
        return person;
    }

    public void setPerson(ProposalPerson person) {
        this.person = person;
    }
}
