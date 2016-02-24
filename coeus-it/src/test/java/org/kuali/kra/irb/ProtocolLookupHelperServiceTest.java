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
package org.kuali.kra.irb;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.protocol.ProtocolLookupHelperServiceTestBase;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
public class ProtocolLookupHelperServiceTest extends ProtocolLookupHelperServiceTestBase {

	private static final String EDIT_URL ="../protocolProtocol.do?viewDocument=false&docId=101&docTypeName=ProtocolDocument&methodToCall=docHandler&command=displayDocSearchView";
    private static final String VIEW_URL ="../protocolProtocol.do?viewDocument=true&docId=101&docTypeName=ProtocolDocument&methodToCall=docHandler&command=displayDocSearchView";
    private static final String COPY_URL = "../DocCopyHandler.do?docId=101&command=displayDocSearchView&documentTypeName=ProtocolDocument";
    private static final String UNIT_INQ_URL ="inquiry.do?businessObjectClassName=" + Unit.class.getName() + "&unitNumber=000001&methodToCall=start";
    /**
     * Count of all lookup rows, including one row for all hidden non-lookup fields
     */
    private static final int NUMBER_LOOKUP_CRITERIA_FIELDS = 23;

    ProtocolLookupableHelperServiceImpl protocolLookupableHelperServiceImpl;
    
    @Override
	@Before
	public void setUp() throws Exception {
        protocolLookupableHelperServiceImpl = new ProtocolLookupableHelperServiceImpl();
        protocolLookupableHelperServiceImpl.setBusinessObjectClass(Protocol.class);
        super.setUp();
	}

	@Override
	@After
	public void tearDown() throws Exception {
	    protocolLookupableHelperServiceImpl = null;
		super.tearDown();
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
                    assertDropDownField(field, "researchAreaCode", ResearchArea.class.getName());
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
        ProtocolPerson protocolPerson = (ProtocolPerson) protocol.getProtocolPersons().get(0);
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
        final KcAuthorizationService kraAuthorizationService = context.mock(KcAuthorizationService.class);
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
        KcServiceLocator.getService(BusinessObjectService.class).save(prot);
        
        final ProtocolDao protocolDao = KcServiceLocator.getService(ProtocolDao.class);
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
