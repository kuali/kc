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
package org.kuali.kra.rules;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.RiceKeyConstants;
import org.kuali.core.UserSession;
import org.kuali.core.service.DocumentService;
import org.kuali.core.util.AuditCluster;
import org.kuali.core.util.AuditError;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.KNSServiceLocator;

import edu.iu.uis.eden.exception.WorkflowException;

/**
 * This class tests the ResearchDocumentBaseAuditRule class
 */
public class ResearchDocumentBaseAuditRuleTest extends KraTestBase {

    private static final String DEFAULT_PROPOSAL_SPONSOR_CODE = "123456";
    private static final String DEFAULT_PROPOSAL_TITLE = "Project title";
    private static final String DEFAULT_PROPOSAL_ACTIVITY_TYPE = "1";
    private static final String DEFAULT_PROPOSAL_OWNED_BY_UNIT = "000002";
    private static final String PROPOSAL_TYPE_NEW = "1";

    private DocumentService documentService = null;
    private ResearchDocumentBaseAuditRule auditRule = null;

    private Date defaultProposalRequestedStartDate = null;
    private Date defaultProposalRequestedEndDate = null;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        GlobalVariables.setAuditErrorMap(new HashMap());
        documentService = KNSServiceLocator.getDocumentService();
        auditRule = new ResearchDocumentBaseAuditRule();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        defaultProposalRequestedStartDate = new Date(dateFormat.parse("08/14/2007").getTime());
        defaultProposalRequestedEndDate = new Date(dateFormat.parse("08/21/2007").getTime());
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        GlobalVariables.setAuditErrorMap(null);
        documentService = null;
        auditRule = null;
        defaultProposalRequestedStartDate = null;
        defaultProposalRequestedEndDate = null;
        super.tearDown();
    }

    @Test public void testRequiredCustomAttributeFieldsMissing() throws Exception {
        ProposalDevelopmentDocument document = getNewDocument();

        Map<String, CustomAttribute> requiredFields = new HashMap<String, CustomAttribute>();

        Map<String, CustomAttributeDocument> customAttributeDocuments = ((ResearchDocumentBase)document).getCustomAttributeDocuments();
        for (Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry: customAttributeDocuments.entrySet()) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
            CustomAttribute customAttribute = customAttributeDocument.getCustomAttribute();
            if (customAttributeDocument.isRequired()) {
                requiredFields.put("customData." + customAttribute.getGroupName() + customAttribute.getName(), customAttribute);
            }
        }

        assertFalse("Audit Rule should produce an audit error", auditRule.processRunAuditBusinessRules(document));
        assertEquals(requiredFields.size(), GlobalVariables.getAuditErrorMap().size());

        for (String key: requiredFields.keySet()) {
            CustomAttribute customAttribute = requiredFields.get(key);
            AuditCluster auditCluster = (AuditCluster)GlobalVariables.getAuditErrorMap().get("customAttributes." + customAttribute.getGroupName() + "Errors");

            assertEquals(1, auditCluster.getSize());
            assertEquals("Custom Data: " + customAttribute.getGroupName(), auditCluster.getLabel());
            assertEquals("Errors", auditCluster.getCategory());
            AuditError auditError = (AuditError) auditCluster.getAuditErrorList().get(0);
            assertEquals("customData." + customAttribute.getName(), auditError.getErrorKey());
            assertEquals("customData." + customAttribute.getGroupName(), auditError.getLink());
            assertEquals(customAttribute.getLabel(), auditError.getParams()[0]);
            assertEquals(RiceKeyConstants.ERROR_REQUIRED, auditError.getMessageKey());
        }

    }

    @Test public void testRequiredCustomAttributeFieldsPopulated() throws Exception {
        ProposalDevelopmentDocument document = getNewDocument();

        Map<String, CustomAttributeDocument> customAttributeDocuments = ((ResearchDocumentBase)document).getCustomAttributeDocuments();
        for (Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry: customAttributeDocuments.entrySet()) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
            CustomAttribute customAttribute = customAttributeDocument.getCustomAttribute();
            if (customAttributeDocument.isRequired()) {
                customAttribute.setValue("value");
            }
        }

        assertTrue("Audit Rule shouldn't produce any audit errors", auditRule.processRunAuditBusinessRules(document));
        assertEquals(0, GlobalVariables.getAuditErrorMap().size());
    }

    private ProposalDevelopmentDocument getNewDocument() throws WorkflowException {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");
        document.initialize();

        setRequiredDocumentFields(document,
                DEFAULT_PROPOSAL_SPONSOR_CODE,
                DEFAULT_PROPOSAL_TITLE,
                defaultProposalRequestedStartDate,
                defaultProposalRequestedEndDate,
                DEFAULT_PROPOSAL_ACTIVITY_TYPE,
                PROPOSAL_TYPE_NEW,
                DEFAULT_PROPOSAL_OWNED_BY_UNIT);

        return document;
    }

    /**
     * This method sets required document fields
     * @param document ProposalDevelopmentDocument to set fields for
     * @param sponsorCode String Sponsor code for the document
     * @param title String title of document
     * @param requestedStartDateInitial String start date
     * @param requestedEndDateInitila String end date
     * @param activityTypeCode String activity type code
     * @param proposalTypeCode String proposal type code
     * @param ownedByUnit String owned by unit
     */
    private void setRequiredDocumentFields(ProposalDevelopmentDocument document, String sponsorCode, String title, Date requestedStartDateInitial, Date requestedEndDateInitial, String activityTypeCode, String proposalTypeCode, String ownedByUnit) {
        document.setSponsorCode(sponsorCode);
        document.setTitle(title);
        document.setRequestedStartDateInitial(requestedStartDateInitial);
        document.setRequestedEndDateInitial(requestedEndDateInitial);
        document.setActivityTypeCode(activityTypeCode);
        document.setProposalTypeCode(proposalTypeCode);
        document.setOwnedByUnitNumber(ownedByUnit);
    }

}