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
package org.kuali.kra.rules;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.custom.KcDocumentBaseAuditRule;
import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocValue;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;


import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * This class tests the KcDocumentBaseAuditRule class
 */
public class KcDocumentBaseAuditRuleTest extends KcIntegrationTestBase {

    private static final String DEFAULT_PROPOSAL_SPONSOR_CODE = "123456";
    private static final String DEFAULT_PROPOSAL_TITLE = "Project title";
    private static final String DEFAULT_PROPOSAL_ACTIVITY_TYPE = "1";
    private static final String DEFAULT_PROPOSAL_OWNED_BY_UNIT = "000002";
    private static final String PROPOSAL_TYPE_NEW = "1";

    private DocumentService documentService = null;
    private KcDocumentBaseAuditRule auditRule = null;

    private Date defaultProposalRequestedStartDate = null;
    private Date defaultProposalRequestedEndDate = null;


    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        GlobalVariables.setAuditErrorMap(new HashMap());
        documentService = KRADServiceLocatorWeb.getDocumentService();
        auditRule = new KcDocumentBaseAuditRule();
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
    }

    @Test public void testRequiredCustomAttributeFieldsMissing() throws Exception {
        ProposalDevelopmentDocument document = getNewDocument();

        Map<String, CustomAttribute> requiredFields = new HashMap<String, CustomAttribute>();

        Map<String, CustomAttributeDocument> customAttributeDocuments = ((KcTransactionalDocumentBase)document).getCustomAttributeDocuments();
        for (Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry: customAttributeDocuments.entrySet()) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
            CustomAttribute customAttribute = customAttributeDocument.getCustomAttribute();
            if (customAttributeDocument.isRequired()) {
                requiredFields.put("customData." + customAttribute.getGroupName() + customAttribute.getName(), customAttribute);
            }
            CustomAttributeDocValue newValue = new CustomAttributeDocValue();
            newValue.setCustomAttribute(customAttributeDocument.getCustomAttribute());
            newValue.setId(customAttributeDocument.getId().longValue());
            newValue.setValue(null);
            document.getCustomDataList().add(newValue);          
        }

        assertFalse("Audit Rule should produce an audit error", auditRule.processRunAuditBusinessRules(document));
        assertEquals(requiredFields.size(), GlobalVariables.getAuditErrorMap().size());

        for (String key: requiredFields.keySet()) {
            CustomAttribute customAttribute = requiredFields.get(key);

            Map<String, AuditCluster> map = GlobalVariables.getAuditErrorMap();
            AuditCluster auditCluster = map.get("CustomData" + StringUtils.deleteWhitespace(customAttribute.getGroupName()) + "Errors");

            assertEquals(1, auditCluster.getSize());
            assertEquals(customAttribute.getGroupName(), auditCluster.getLabel());
            assertEquals("Error", auditCluster.getCategory());
            AuditError auditError = (AuditError) auditCluster.getAuditErrorList().get(0);
            int index = 0;
            for (CustomAttributeDocValue value : document.getCustomDataList()) {
                if (value.getId().longValue() == customAttribute.getId().longValue()) {
                    break;
                }
                index++;
            }
            assertTrue(auditError.getErrorKey().matches("customDataHelper.customDataList.*value"));
            assertEquals(StringUtils.deleteWhitespace(Constants.CUSTOM_ATTRIBUTES_PAGE + "." + customAttribute.getGroupName()), auditError.getLink());
            assertEquals(customAttribute.getLabel(), auditError.getParams()[0]);
            assertEquals(RiceKeyConstants.ERROR_REQUIRED, auditError.getMessageKey());
        }

    }

    @Test public void testRequiredCustomAttributeFieldsPopulated() throws Exception {
        ProposalDevelopmentDocument document = getNewDocument();

        Map<String, CustomAttributeDocument> customAttributeDocuments = ((KcTransactionalDocumentBase)document).getCustomAttributeDocuments();
        for (Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry: customAttributeDocuments.entrySet()) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocumentEntry.getValue();
            CustomAttribute customAttribute = customAttributeDocument.getCustomAttribute();
            if (customAttributeDocument.isRequired()) {
                //setting the value to a number since one of the required fields is an numeric field
                //and the audit rule now checks the fields type as well.
                customAttribute.setValue("5");
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
     * @param requestedEndDateInitial String end date
     * @param activityTypeCode String activity type code
     * @param proposalTypeCode String proposal type code
     * @param ownedByUnit String owned by unit
     */
    private void setRequiredDocumentFields(ProposalDevelopmentDocument document, String sponsorCode, String title, Date requestedStartDateInitial, Date requestedEndDateInitial, String activityTypeCode, String proposalTypeCode, String ownedByUnit) {
        document.getDevelopmentProposal().setSponsorCode(sponsorCode);
        document.getDevelopmentProposal().setTitle(title);
        document.getDevelopmentProposal().setRequestedStartDateInitial(requestedStartDateInitial);
        document.getDevelopmentProposal().setRequestedEndDateInitial(requestedEndDateInitial);
        document.getDevelopmentProposal().setActivityTypeCode(activityTypeCode);
        document.getDevelopmentProposal().setProposalTypeCode(proposalTypeCode);
        document.getDevelopmentProposal().setOwnedByUnitNumber(ownedByUnit);
    }

}
