/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.irb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

public class ProtocolLookupHelperServiceTest extends KcUnitTestBase {
    
    private static final String EDIT_URL ="../protocolProtocol.do?viewDocument=false&docId=101&docTypeName=ProtocolDocument&methodToCall=docHandler&command=displayDocSearchView";
    private static final String VIEW_URL ="../protocolProtocol.do?viewDocument=true&docId=101&docTypeName=ProtocolDocument&methodToCall=docHandler&command=displayDocSearchView";
    private static final String COPY_URL = "../DocCopyHandler.do?docId=101&command=displayDocSearchView&documentTypeName=ProtocolDocument";
    private static final String UNIT_INQ_URL ="inquiry.do?businessObjectClassName=org.kuali.kra.bo.Unit&unitNumber=000001&methodToCall=start";
    private static final String PERSON_INQ_URL ="inquiry.do?businessObjectClassName=org.kuali.kra.bo.KcPerson&personId=10000000001&methodToCall=start";
    private static final String ROLODEX_INQ_URL ="inquiry.do?businessObjectClassName=org.kuali.kra.bo.Rolodex&rolodexId=1727&methodToCall=start";
    
    /**
     * Count of all lookup rows, including one row for all hidden non-lookup fields
     */
    private static final int NUMBER_LOOKUP_CRITERIA_FIELDS = 23;

    private ProtocolLookupableHelperServiceImpl protocolLookupableHelperServiceImpl;
    
    private Mockery context = new JUnit4Mockery();
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        protocolLookupableHelperServiceImpl = new ProtocolLookupableHelperServiceImpl();
        protocolLookupableHelperServiceImpl.setBusinessObjectClass(Protocol.class);
        GlobalVariables.setUserSession(new UserSession("quickstart"));
   }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        protocolLookupableHelperServiceImpl = null;
        GlobalVariables.setUserSession(null);
    }

    /**
     * 
     * This method is to test getrows.  The lookup fields will be updated and make sure 
     * a few of the drop down fields are set as dropdown_refresh 
     */
    @Test
    public void testGetRows() {

        List<Row> rows = protocolLookupableHelperServiceImpl.getRows();
        assertEquals(NUMBER_LOOKUP_CRITERIA_FIELDS, rows.size());
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals("researchAreaCode")) {
                    assertDropDownField(field, "researchAreaCode","org.kuali.kra.bo.ResearchArea");
               } 
            }
        }
    }
    
    /*
     * 
     * This method is to make sure the looks fields are set up properly.
     * @param field
     * @param keyName
     * @param className
     */
    private void assertDropDownField(Field field, String keyName,String className) {
        assertEquals(field.getFieldConversions(), keyName+":"+field.getPropertyName());
        assertEquals(field.getLookupParameters(), field.getPropertyName()+":"+keyName);
        assertEquals(field.getInquiryParameters(), field.getPropertyName()+":"+keyName);
        assertEquals(field.getQuickFinderClassNameImpl(), className);

    }

    /**
     * 
     * This method test a few specila inquirey fields are set up properly.
     */
    @Test
    public void testGetInquiryUrl() {
        Protocol protocol = initProtocol();
        
        final KcPersonService kcPersonService = context.mock(KcPersonService.class);
        final String principalId = GlobalVariables.getUserSession().getPrincipalId();
        context.checking(new Expectations() {{
            one(kcPersonService).getKcPersonByPersonId(principalId);
            will(returnValue(KcPerson.fromPersonId(principalId)));
        }});
        protocolLookupableHelperServiceImpl.setKcPersonService(kcPersonService);
        
        HtmlData inquiryUrl = protocolLookupableHelperServiceImpl.getInquiryUrl(protocol, "leadUnitNumber");
        assertEquals(((HtmlData.AnchorHtmlData) inquiryUrl).getHref(), UNIT_INQ_URL);
        inquiryUrl = protocolLookupableHelperServiceImpl.getInquiryUrl(protocol, "investigator");
        assertEquals(((HtmlData.AnchorHtmlData) inquiryUrl).getHref(), PERSON_INQ_URL);
        ProtocolPerson protocolPerson = protocol.getProtocolPersons().get(0);
        protocolPerson.setPersonId("");
        protocolPerson.setRolodexId(new Integer(1727));
        protocol.getProtocolPersons().clear();
        protocol.getProtocolPersons().add(protocolPerson);
        inquiryUrl = protocolLookupableHelperServiceImpl.getInquiryUrl(protocol, "investigator");
        assertEquals(((HtmlData.AnchorHtmlData) inquiryUrl).getHref(), ROLODEX_INQ_URL);
    }
    
    /**
     * 
     * This method to check the 'edit' link is correct
     */
    @Test
    public void testGetCustomActionUrls() {
        List pkNames = new ArrayList();
        pkNames.add("protocolId");
        final Protocol protocol = new Protocol();
        protocol.setProtocolNumber("100");
        ProtocolDocument document = new ProtocolDocument();
        document.setDocumentNumber("101");
        protocol.setProtocolDocument(document);
        final KraAuthorizationService kraAuthorizationService = context.mock(KraAuthorizationService.class);
        final String principalId = GlobalVariables.getUserSession().getPrincipalId();
        context.checking(new Expectations() {{
            Map<String, String> fieldValues = new HashMap<String, String>();

            one(kraAuthorizationService).hasPermission(principalId, protocol, PermissionConstants.MODIFY_PROTOCOL);
            will(returnValue(true));
            one(kraAuthorizationService).hasPermission(principalId, protocol, PermissionConstants.VIEW_PROTOCOL);
            will(returnValue(true));
        }});
        protocolLookupableHelperServiceImpl.setKraAuthorizationService(kraAuthorizationService);

        List<HtmlData> actionUrls = protocolLookupableHelperServiceImpl.getCustomActionUrls(protocol,pkNames);
        for (HtmlData htmlData : actionUrls) {
            HtmlData.AnchorHtmlData actionUrl = (HtmlData.AnchorHtmlData) htmlData;
            if (actionUrl.getMethodToCall().equals("copy")) {
                assertEquals(((HtmlData.AnchorHtmlData) actionUrl).getHref(), COPY_URL);                
            } else if (actionUrl.getDisplayText().equals("edit")){
                assertEquals(((HtmlData.AnchorHtmlData) actionUrl).getHref(), EDIT_URL);                
            } else if (actionUrl.getDisplayText().equals("view")){
                assertEquals(((HtmlData.AnchorHtmlData) actionUrl).getHref(), VIEW_URL);                
            }
        } 
    }
        
    
    /**
     * 
     * This method to check the number of return results is correct.
     * This is using mock for protocoldao.
     * @throws Exception
     */
    @Test 
    public void testGetSearchResults() throws Exception {
        String newProtocolNumber = "123456132";
        Protocol prot = ProtocolFactory.createProtocolDocument(newProtocolNumber).getProtocol();
        KraServiceLocator.getService(BusinessObjectService.class).save(prot);
        
        final ProtocolDao protocolDao = KraServiceLocator.getService(ProtocolDao.class);
        protocolLookupableHelperServiceImpl.setProtocolDao(protocolDao);        
        Map<String, String> searchStuff = new HashMap<String, String>();
        searchStuff.put("protocolNumber", newProtocolNumber);
        List<? extends BusinessObject> searchResults = protocolLookupableHelperServiceImpl.getSearchResults(searchStuff);
        assertEquals("searchResults.size(): '" +  searchResults.size() + "'",  1, searchResults.size());

    }

        
    /*
     * 
     * This method is to set up protocol for get inquiry url test
     * @return
     */
    private Protocol initProtocol() {
        Protocol protocol = new Protocol();
        protocol.setLeadUnitNumber("000001");
        
        protocol.setPrincipalInvestigatorId("10000000001");
        ProtocolPerson protocolPerson = new ProtocolPerson();
        protocolPerson.setPersonId("10000000001");
        protocol.getProtocolPersons().add(protocolPerson);
        protocolPerson.setProtocolPersonRoleId("PI");
        return protocol;
        
    }
}
