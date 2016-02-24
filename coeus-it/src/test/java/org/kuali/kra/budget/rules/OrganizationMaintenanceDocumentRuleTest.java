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
package org.kuali.kra.budget.rules;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.org.OrganizationYnq;
import org.kuali.coeus.common.framework.ynq.Ynq;
import org.kuali.coeus.common.framework.ynq.YnqService;
import org.kuali.coeus.common.impl.org.OrganizationMaintenanceDocumentRule;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
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
