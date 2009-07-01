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

import java.util.Calendar;

import org.kuali.kra.bo.Organization;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.bo.S2sSubmissionType;
import org.kuali.kra.s2s.generator.S2STestBase;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;

public class SF424AV1_0GeneratorTest extends S2STestBase<SF424AV1_0Generator> {


    @Override
    protected Class<SF424AV1_0Generator> getFormGeneratorClass() {
        return SF424AV1_0Generator.class;
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {

        GlobalVariables.setUserSession(new UserSession("quickstart"));
        DocumentService documentService = KNSServiceLocator.getDocumentService();
        ProposalDevelopmentDocument pdDoc = (ProposalDevelopmentDocument) documentService
                .getNewDocument("ProposalDevelopmentDocument");

        Organization organization = new Organization();
        organization.setDunsNumber("122");
        organization.setOrganizationName("MIT");
        pdDoc.setOrganization(organization);

        S2sSubmissionType submissionType = new S2sSubmissionType();
        submissionType.setS2sSubmissionTypeCode("1");
        S2sOpportunity opportunity = new S2sOpportunity();
        opportunity.setS2sSubmissionType(submissionType);
        opportunity.setS2sSubmissionTypeCode("1");
        opportunity.setRevisionCode("AC");
        opportunity.setRevisionOtherDescription("Revision description");
        opportunity.setOpportunityTitle("opportunityTitle");
        opportunity.setCompetetionId("3650");
        opportunity.setCfdaNumber("654654");

        pdDoc.setUpdateUser("quickstart");
        pdDoc.setUpdateTimestamp(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
        KraServiceLocator.getService(BusinessObjectService.class).save(pdDoc);
        opportunity.setProposalNumber(pdDoc.getProposalNumber());
        pdDoc.setS2sOpportunity(opportunity);
    }
}
