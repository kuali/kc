/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.document;

import java.util.ArrayList;

import org.junit.Test;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.OrganizationAudit;
import org.kuali.kra.bo.OrganizationIndirectcost;
import org.kuali.kra.bo.OrganizationType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.test.data.PerTestUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestSql;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

// need to delete organization last because fk issue
@PerTestUnitTestData(
        @UnitTestData(
                sqlStatements = {
                        @UnitTestSql("delete from ORGANIZATION_YNQ where ORGANIZATION_ID = 00999")
                        ,@UnitTestSql("delete from ORGANIZATION_AUDIT where ORGANIZATION_ID = 00999")
                        ,@UnitTestSql("delete from ORGANIZATION_TYPE where ORGANIZATION_ID = 00999")
                        ,@UnitTestSql("delete from ORGANIZATION_IDC where ORGANIZATION_ID = 00999")
                        ,@UnitTestSql("delete from ORGANIZATION where ORGANIZATION_ID = 00999")
                        ,@UnitTestSql("delete from ORGANIZATION_YNQ where ORGANIZATION_ID = 000425")
                        ,@UnitTestSql("delete from ORGANIZATION_AUDIT where ORGANIZATION_ID = 000425")
                        ,@UnitTestSql("delete from ORGANIZATION_TYPE where ORGANIZATION_ID = 000425")
                        ,@UnitTestSql("delete from ORGANIZATION_IDC where ORGANIZATION_ID = 000425")
                        ,@UnitTestSql("commit")

                }
        )
    )
public class OrganizationMaintenanceDocumentTest extends MaintenanceDocumentTestBase {

    private static final String DOCTYPE = "OrganizationMaintenanceDocument";


    @Test
    public void testDocumentCreation() throws Exception {
        testDocumentCreation(DOCTYPE);
    }

    @Test
    public void testCopyOrganization() throws Exception {
        HtmlPage organizationMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Organization");
        setFieldValue(organizationMaintenanceLookupPage,"organizationId","000425");
        HtmlPage searchPage = clickOn(organizationMaintenanceLookupPage, "search");
        assertContains(searchPage, "251 Town and Country Village Palo Alto");
        
        HtmlAnchor copyLink = searchPage.getAnchorByHref("maintenance.do?organizationId=000425&businessObjectClassName=org.kuali.kra.bo.Organization&methodToCall=copy");
        HtmlPage organizationMaintenancePage = clickOn(copyLink, "Kuali :: Organization Maintenance Document");
        String documentNumber = getFieldValue(organizationMaintenancePage, "document.documentHeader.documentNumber");

        setFieldValue(organizationMaintenancePage, "document.documentHeader.documentDescription", "Organization Maint Doc - copy test");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationId", "00999");

        organizationMaintenancePage = setupOrganizationCollections(organizationMaintenancePage);
                
        HtmlPage routedOrganizationPage = clickOn(organizationMaintenancePage, "methodToCall.route", "Kuali :: Organization Maintenance Document");
        
        assertContains(routedOrganizationPage, "Document was successfully submitted.");
        //assertContains(routedOrganizationPage,"New Id: 999 Data Length: 8 Data Type Code: String Default Value: Group Name: test group Label: Test 99 Lookup Class: User Roles Lookup Return: Role Id Name: test99");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        Organization organization = (Organization)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(organization.getOrganizationId(),"00999");
        assertEquals(organization.getOrganizationName(),"Desktop Aeronautics, Incorporated");
        assertEquals(organization.getContactAddressId(),new Integer(13469));
        assertEquals(((OrganizationType)((ArrayList)organization.getOrganizationTypes()).get(0)).getOrganizationTypeCode(),new Integer(1));
        assertEquals(((OrganizationIndirectcost)((ArrayList)organization.getOrganizationIdcs()).get(0)).getIdcNumber(),new Integer(1));
        assertEquals(((OrganizationAudit)((ArrayList)organization.getOrganizationAudits()).get(0)).getFiscalYear(),"2008");

                

    }

    
    @Test
    public void testEditOrganization() throws Exception {
        HtmlPage organizationMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Organization");
        System.out.println(organizationMaintenanceLookupPage.asXml());
        setFieldValue(organizationMaintenanceLookupPage,"organizationId","000425");
        HtmlPage searchPage = clickOn(organizationMaintenanceLookupPage, "search");
        assertContains(searchPage, "251 Town and Country Village Palo Alto");
        
        HtmlAnchor editLink = searchPage.getAnchorByHref("maintenance.do?organizationId=000425&businessObjectClassName=org.kuali.kra.bo.Organization&methodToCall=edit");
        HtmlPage organizationMaintenancePage = clickOn(editLink, "Kuali :: Organization Maintenance Document");
        String documentNumber = getFieldValue(organizationMaintenancePage, "document.documentHeader.documentNumber");

        setFieldValue(organizationMaintenancePage, "document.documentHeader.documentDescription", "Organization Maint Doc - edit test");

        organizationMaintenancePage = setupOrganizationCollections(organizationMaintenancePage);
                
        HtmlPage routedOrganizationPage = clickOn(organizationMaintenancePage, "methodToCall.route", "Kuali :: Organization Maintenance Document");
        
        assertContains(routedOrganizationPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        Organization organization = (Organization)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(organization.getOrganizationId(),"000425");
        assertEquals(organization.getOrganizationName(),"Desktop Aeronautics, Incorporated");
        assertEquals(organization.getContactAddressId(),new Integer(13469));
        assertEquals(((OrganizationType)((ArrayList)organization.getOrganizationTypes()).get(0)).getOrganizationTypeCode(),new Integer(1));
        assertEquals(((OrganizationIndirectcost)((ArrayList)organization.getOrganizationIdcs()).get(0)).getIdcNumber(),new Integer(1));
        assertEquals(((OrganizationAudit)((ArrayList)organization.getOrganizationAudits()).get(0)).getFiscalYear(),"2008");


    }
    
    
    /**
     * 
     * This method is to test creation or organization with collections, audit/idc/ynq/type, set up properly.
     * @throws Exception
     */
    @Test
    public void testCreateNewOrganization() throws Exception {
        HtmlPage organizationMaintenancePage = getMaintenanceDocumentPage("Organization",Organization.class.getName(),"Kuali :: Organization Maintenance Document");
        String documentNumber = getFieldValue(organizationMaintenancePage, "document.documentHeader.documentNumber");
        assertContains(organizationMaintenancePage,"Edit Organization New * Organization Id: Address: Agency Symbol: Animal Welfare Assurance: ");
        
        // set up required fields for organization
        setFieldValue(organizationMaintenancePage, "document.documentHeader.documentDescription", "Organization Maint Doc - test");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationId", "00999");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationName", "test organization");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.contactAddressId", "1741");
        
        organizationMaintenancePage = setupOrganizationCollections(organizationMaintenancePage);
                
        HtmlPage routedOrganizationPage = clickOn(organizationMaintenancePage, "methodToCall.route", "Kuali :: Organization Maintenance Document");
        
        assertContains(routedOrganizationPage, "Document was successfully submitted.");
        //assertContains(routedOrganizationPage,"New Id: 999 Data Length: 8 Data Type Code: String Default Value: Group Name: test group Label: Test 99 Lookup Class: User Roles Lookup Return: Role Id Name: test99");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        Organization organization = (Organization)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(organization.getOrganizationId(),"00999");
        assertEquals(organization.getOrganizationName(),"test organization");
        assertEquals(organization.getContactAddressId(),new Integer(1741));
        assertEquals(((OrganizationType)((ArrayList)organization.getOrganizationTypes()).get(0)).getOrganizationTypeCode(),new Integer(1));
        assertEquals(((OrganizationIndirectcost)((ArrayList)organization.getOrganizationIdcs()).get(0)).getIdcNumber(),new Integer(1));
        assertEquals(((OrganizationAudit)((ArrayList)organization.getOrganizationAudits()).get(0)).getFiscalYear(),"2008");
   
    }

    /**
     * 
     * This method is a util method to get the long name of the image field.
     * @param page
     * @param uniqueNamePrefix
     * @return
     */
    private String getImageTagName(HtmlPage page, String uniqueNamePrefix) {
        int idx1 = page.asXml().indexOf(uniqueNamePrefix);
        //int idx2 = page.asXml().indexOf(".((##)).((&lt;&gt;)).(([])).((**)).((^^)).((&amp;&amp;)).((//)).((~~)).anchor", idx1);
        int idx2 = page.asXml().indexOf("\"", idx1);
        return page.asXml().substring(idx1, idx2).replace("&amp;", "&").replace("((&lt;&gt;))", "((<>))");
    }

    private HtmlPage setupOrganizationCollections(HtmlPage organizationMaintenancePage) throws Exception {
        // set up ynq answer
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationYnqs[0].answer", "Y");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationYnqs[1].answer", "N");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationYnqs[2].answer", "Y");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationYnqs[3].answer", "N");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationYnqs[4].answer", "Y");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationYnqs[5].answer", "N");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationYnqs[6].answer", "Y");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationYnqs[7].answer", "N");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationYnqs[8].answer", "Y");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationYnqs[9].answer", "N");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationYnqs[10].answer", "Y");
        
        for (int i = 0; i < 11; i++) {
            setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationYnqs["+i+"].explanation", "test");
            setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationYnqs["+i+"].reviewDate", "01/01/2008");

        }

        // add organization type
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.add.organizationTypes.organizationTypeCode", "1");
        // in 'edit', there is a hidden fiels with same tag name, so the tag name is longer than 'create new'
        // hidden field is "methodToCall.addLine.organizationTypes.(!!org.kuali.kra.bo.OrganizationType!!)"
        organizationMaintenancePage = clickOn(organizationMaintenancePage, getImageTagName(organizationMaintenancePage,"methodToCall.addLine.organizationTypes.(!!org.kuali.kra.bo.OrganizationType!!)."), "Kuali :: Organization Maintenance Document");
  
        // set up audit
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.add.organizationAudits.fiscalYear", "2008");
        organizationMaintenancePage = clickOn(organizationMaintenancePage, getImageTagName(organizationMaintenancePage,"methodToCall.addLine.organizationAudits.(!!org.kuali.kra.bo.OrganizationAudit!!)."), "Kuali :: Organization Maintenance Document");
        
        // set up idc
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.add.organizationIdcs.idcNumber", "1");
        organizationMaintenancePage = clickOn(organizationMaintenancePage, getImageTagName(organizationMaintenancePage,"methodToCall.addLine.organizationIdcs.(!!org.kuali.kra.bo.OrganizationIndirectcost!!)."), "Kuali :: Organization Maintenance Document");

        return organizationMaintenancePage;
    }

}
