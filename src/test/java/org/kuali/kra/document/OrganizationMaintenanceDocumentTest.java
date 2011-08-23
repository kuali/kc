/*
 * Copyright 2005-2010 The Kuali Foundation
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

import org.junit.Test;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.DocumentService;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class OrganizationMaintenanceDocumentTest extends MaintenanceDocumentTestBase {
    private static final String LOOKUP_PAGE_TITLE = "Organization";
    private static final String MAINTENANCE_PAGE_TITLE = "Kuali :: Organization Maintenance Document";

    private static final String DOCTYPE = "OrganizationMaintenanceDocument";
    private static final String ORG_ID_ORIG = "000425";
    private static final String ORG_ID_NEW_1 = Long.toString(System.currentTimeMillis()%1000000);
    private static final String ORG_ID_NEW_2 = Long.toString((System.currentTimeMillis()+1)%1000000);

    @Override
    public String getDocTypeName() {
        return DOCTYPE;
    }

    @Test
    public void testCopyOrganization() throws Exception {
        HtmlPage organizationMaintenanceLookupPage = getMaintenanceDocumentLookupPage(LOOKUP_PAGE_TITLE);
        setFieldValue(organizationMaintenanceLookupPage,"organizationId",ORG_ID_ORIG);
        HtmlPage searchPage = clickOn(organizationMaintenanceLookupPage, "search");
        assertContains(searchPage, "251 Town and Country Village Palo Alto");
        
        HtmlAnchor copyLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "copy"));
        HtmlPage organizationMaintenancePage = clickOn(copyLink, MAINTENANCE_PAGE_TITLE);
        String documentNumber = getFieldValue(organizationMaintenancePage, "document.documentHeader.documentNumber");

        setFieldValue(organizationMaintenancePage, "document.documentHeader.documentDescription", "Organization Maint Doc - copy test");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationId", ORG_ID_NEW_1);
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.contactAddressId", "1741");

        organizationMaintenancePage = setupOrganizationCollections(organizationMaintenancePage, 12);
                
        HtmlPage routedOrganizationPage = clickOn(organizationMaintenancePage, "methodToCall.route", MAINTENANCE_PAGE_TITLE);
        
        assertContains(routedOrganizationPage, "Document was successfully submitted.");
        //assertContains(routedOrganizationPage,"New Id: 999 Data Length: 8 Data Type Code: String Default Value: Group Name: test group Label: Test 99 Lookup Class: User Roles Lookup Return: Role Id Name: test99");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        Organization organization = (Organization)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(organization.getOrganizationId(),ORG_ID_NEW_1);
        assertEquals(organization.getOrganizationName(),"Desktop Aeronautics, Incorporated");
        assertEquals(organization.getContactAddressId(),new Integer(1741));
        assertEquals(organization.getOrganizationTypes().get(0).getOrganizationTypeCode(),new Integer(1));
        assertEquals(organization.getOrganizationIdcs().get(0).getIdcNumber(),new Integer(1));
        assertEquals(organization.getOrganizationAudits().get(0).getFiscalYear(),"2008");

                

    }

    
    @Test
    public void testEditOrganization() throws Exception {
        HtmlPage organizationMaintenanceLookupPage = getMaintenanceDocumentLookupPage(LOOKUP_PAGE_TITLE);

        setFieldValue(organizationMaintenanceLookupPage,"organizationId",ORG_ID_ORIG);
        HtmlPage searchPage = clickOn(organizationMaintenanceLookupPage, "search");
        assertContains(searchPage, "251 Town and Country Village Palo Alto");
        
        HtmlAnchor editLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "edit"));
        HtmlPage organizationMaintenancePage = clickOn(editLink, MAINTENANCE_PAGE_TITLE);
        String documentNumber = getFieldValue(organizationMaintenancePage, "document.documentHeader.documentNumber");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.contactAddressId", "1741");
        setFieldValue(organizationMaintenancePage, "document.documentHeader.documentDescription", "Organization Maint Doc - edit test");

        organizationMaintenancePage = setupOrganizationCollections(organizationMaintenancePage, 12);
                
        HtmlPage routedOrganizationPage = clickOn(organizationMaintenancePage, "methodToCall.route", MAINTENANCE_PAGE_TITLE);
        
        assertContains(routedOrganizationPage, "Document was successfully submitted.");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        Organization organization = (Organization)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(organization.getOrganizationId(),ORG_ID_ORIG);
        assertEquals(organization.getOrganizationName(),"Desktop Aeronautics, Incorporated");
        assertEquals(organization.getContactAddressId(),new Integer(1741));
        assertEquals(organization.getOrganizationTypes().get(0).getOrganizationTypeCode(),new Integer(1));
        assertEquals(organization.getOrganizationIdcs().get(0).getIdcNumber(),new Integer(1));
        assertEquals(organization.getOrganizationAudits().get(0).getFiscalYear(),"2008");


    }
    
    
    /**
     * 
     * This method is to test creation or organization with collections, audit/idc/ynq/type, set up properly.
     * @throws Exception
     */
    @Test
    public void testCreateNewOrganization() throws Exception {
        HtmlPage organizationMaintenancePage = getMaintenanceDocumentPage(LOOKUP_PAGE_TITLE,Organization.class.getName(),MAINTENANCE_PAGE_TITLE);
        String documentNumber = getFieldValue(organizationMaintenancePage, "document.documentHeader.documentNumber");
        assertContains(organizationMaintenancePage,"Edit Organization New * Organization Id: Address: Agency Symbol: Animal Welfare Assurance: ");
        
        // set up required fields for organization
        setFieldValue(organizationMaintenancePage, "document.documentHeader.documentDescription", "Organization Maint Doc - test");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationId", ORG_ID_NEW_2);
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationName", "test organization");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.contactAddressId", "1741");
        
        organizationMaintenancePage = setupOrganizationCollections(organizationMaintenancePage, 11);
                
        HtmlPage routedOrganizationPage = clickOn(organizationMaintenancePage, "methodToCall.route", MAINTENANCE_PAGE_TITLE);
        
        assertContains(routedOrganizationPage, "Document was successfully submitted.");
        //assertContains(routedOrganizationPage,"New Id: 999 Data Length: 8 Data Type Code: String Default Value: Group Name: test group Label: Test 99 Lookup Class: User Roles Lookup Return: Role Id Name: test99");
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
        Organization organization = (Organization)document.getNewMaintainableObject().getBusinessObject();
        assertEquals(organization.getOrganizationId(),ORG_ID_NEW_2);
        assertEquals(organization.getOrganizationName(),"test organization");
        assertEquals(organization.getContactAddressId(),new Integer(1741));
        assertEquals(organization.getOrganizationTypes().get(0).getOrganizationTypeCode(),new Integer(1));
        assertEquals(organization.getOrganizationIdcs().get(0).getIdcNumber(),new Integer(1));
        assertEquals(organization.getOrganizationAudits().get(0).getFiscalYear(),"2008");
   
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
        //int idx2 = page.asXml().indexOf(".((``)).((&lt;&gt;)).(([])).((**)).((^^)).((&amp;&amp;)).((//)).((~~)).anchor", idx1);
        int idx2 = page.asXml().indexOf("\"", idx1);
        return page.asXml().substring(idx1, idx2).replace("&amp;", "&").replace("((&lt;&gt;))", "((<>))");
    }

    private HtmlPage setupOrganizationCollections(HtmlPage organizationMaintenancePage, int size) throws Exception {
        // set up ynq answer       
        // for this org '000425', it has 13 questions, so edit/copy is <=12 and new is only <= 11
        for (int i = 0; i <= size; i++) {
            setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationYnqs["+i+"].answer", i%2==0?"Y":"N");
            setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationYnqs["+i+"].explanation", "test");
            setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.organizationYnqs["+i+"].reviewDate", "01/01/2008");

        }

        // add organization type
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.add.organizationTypes.organizationTypeCode", "1");
        // in 'edit', there is a hidden fiels with same tag name, so the tag name is longer than 'create new'
        // hidden field is "methodToCall.addLine.organizationTypes.(!!org.kuali.kra.bo.OrganizationType!!)"
        organizationMaintenancePage = clickOn(organizationMaintenancePage, getImageTagName(organizationMaintenancePage,"methodToCall.addLine.organizationTypes.(!!org.kuali.kra.bo.OrganizationType!!)."), MAINTENANCE_PAGE_TITLE);
  
        // set up audit
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.add.organizationAudits.fiscalYear", "2008");
        organizationMaintenancePage = clickOn(organizationMaintenancePage, getImageTagName(organizationMaintenancePage,"methodToCall.addLine.organizationAudits.(!!org.kuali.kra.bo.OrganizationAudit!!)."), MAINTENANCE_PAGE_TITLE);
        
        // set up idc
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.add.organizationIdcs.idcNumber", "1");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.add.organizationIdcs.applicableIndirectcostRate", "1.0");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.add.organizationIdcs.endDate", "01/01/2008");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.add.organizationIdcs.startDate", "01/01/2008");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.add.organizationIdcs.requestedDate", "01/01/2008");
        setFieldValue(organizationMaintenancePage, "document.newMaintainableObject.add.organizationIdcs.idcRateTypeCode", "1");
        organizationMaintenancePage = clickOn(organizationMaintenancePage, getImageTagName(organizationMaintenancePage,"methodToCall.addLine.organizationIdcs.(!!org.kuali.kra.bo.OrganizationIndirectcost!!)."), MAINTENANCE_PAGE_TITLE);

        return organizationMaintenancePage;
    }

}
