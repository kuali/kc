/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.rules;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.OrganizationYnq;
import org.kuali.kra.bo.Ynq;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.kra.rules.OrganizationMaintenanceDocumentRule;
import org.kuali.kra.service.YnqService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class OrganizationMaintenanceDocumentRuleTest extends MaintenanceRuleTestBase {
    private OrganizationMaintenanceDocumentRule rule = null;

    @Before
    public void setUp() throws Exception {
        rule = new OrganizationMaintenanceDocumentRule();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
    }

    @Test
    public void testOK() throws Exception {

        Organization organization = new Organization();

        organization.setOrganizationId("00999");
        organization.setOrganizationName("test");
        organization.setContactAddressId(new Integer(1741));
        organization.setOrganizationYnqs(setupOrganizationYnq(organization, "test", KcServiceLocator.getService(DateTimeService.class).getCurrentSqlDate()));
        MaintenanceDocument organizationDocument = newMaintDoc(organization);
        assertTrue(rule.processCustomRouteDocumentBusinessRules(organizationDocument));
        assertTrue(rule.processCustomApproveDocumentBusinessRules(organizationDocument));
    }

    @Test
    public void testMissingReviewDate() throws Exception {

        Organization organization = new Organization();

        organization.setOrganizationId("00999");
        organization.setOrganizationName("test");
        organization.setContactAddressId(new Integer(1741));
        organization.setOrganizationYnqs(setupOrganizationYnq(organization, "test", null));
        MaintenanceDocument organizationDocument = newMaintDoc(organization);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(organizationDocument));
        assertFalse(rule.processCustomApproveDocumentBusinessRules(organizationDocument));
    }
    
    @Test
    public void testMissingExplanation() throws Exception {

        Organization organization = new Organization();

        organization.setOrganizationId("00999");
        organization.setOrganizationName("test");
        organization.setContactAddressId(new Integer(1741));
        organization.setOrganizationYnqs(setupOrganizationYnq(organization, "", KcServiceLocator.getService(DateTimeService.class).getCurrentSqlDate()));
        MaintenanceDocument organizationDocument = newMaintDoc(organization);
        assertFalse(rule.processCustomRouteDocumentBusinessRules(organizationDocument));
        assertFalse(rule.processCustomApproveDocumentBusinessRules(organizationDocument));
    }

    private List<OrganizationYnq> setupOrganizationYnq(Organization organization, String explanation, Date reviewDate) {
        List<Ynq> ynqs = KcServiceLocator.getService(YnqService.class).getYnq("O");
        List<OrganizationYnq> organizationYnqs = organization.getOrganizationYnqs();
        for (Ynq ynq : ynqs) {
            OrganizationYnq organizationYnq = new OrganizationYnq();
            organizationYnq.setYnq(ynq);
            organizationYnq.setQuestionId(ynq.getQuestionId());
            organizationYnq.setAnswer("Y");
            organizationYnq.setExplanation(explanation);
            organizationYnq.setReviewDate(reviewDate);
            if (StringUtils.isNotBlank(organization.getOrganizationId())) {
                organizationYnq.setOrganizationId(organization.getOrganizationId()); 
            }
            organizationYnqs.add(organizationYnq);
        }
        return organizationYnqs;
    }
}
