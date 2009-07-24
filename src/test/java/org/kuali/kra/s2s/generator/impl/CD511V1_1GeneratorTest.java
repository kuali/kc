/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.s2s.generator.impl;

import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2STestBase;

/**
 * This class tests the CD511V1_1 Generator
 */
public class CD511V1_1GeneratorTest extends S2STestBase<CD511V1_1Generator> {

    @Override
    protected Class<CD511V1_1Generator> getFormGeneratorClass() {
        return CD511V1_1Generator.class;
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {

        Organization organization = new Organization();
        organization.setOrganizationName("MIT");
        document.getDevelopmentProposal().setCurrentAwardNumber("00007");
        document.getDevelopmentProposal().setTitle("CD511V1_1 Test");
        Rolodex rolodex = new Rolodex();
        rolodex.setTitle("Primary title");
        rolodex.setFirstName("George");
        rolodex.setLastName("Marshall");
        rolodex.setMiddleName("C.");
        rolodex.setRolodexId(1234);
        organization.setRolodex(rolodex);
        document.getDevelopmentProposal().setOrganization(organization);
    }
}
