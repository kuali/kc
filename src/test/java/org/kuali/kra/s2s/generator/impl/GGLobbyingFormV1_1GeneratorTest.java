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
 * 
 * This class is used to test GGLobbyingFormV1_1 form
 */
public class GGLobbyingFormV1_1GeneratorTest extends S2STestBase<GGLobbyingFormV1_1Generator> {

    @Override
    protected Class<GGLobbyingFormV1_1Generator> getFormGeneratorClass() {
        return GGLobbyingFormV1_1Generator.class;
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {
        Organization organization = new Organization();
        organization.setOrganizationName("MIT");
        Rolodex rolodex = new Rolodex();
        rolodex.setFirstName("John");
        rolodex.setMiddleName("A");
        rolodex.setLastName("Doe");
        rolodex.setTitle("Project Title");
        rolodex.setRolodexId(1234);
        organization.setRolodex(rolodex);
        document.setOrganization(organization);
    }
}
