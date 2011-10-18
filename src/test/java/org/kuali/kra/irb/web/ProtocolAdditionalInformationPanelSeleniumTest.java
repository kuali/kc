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
package org.kuali.kra.irb.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Additional Information tab in the Protocol page of a Protocol.
 */
public class ProtocolAdditionalInformationPanelSeleniumTest extends KcSeleniumTestBase {  
    
    private static final String TAB_ID = "Additional Information";
    private static final String RESEARCH_AREA_TABLE_ID = "researchAreaTableId";
    private static final String OTHER_IDENTIFIERS_TABLE_ID = "other-idenfifiers-table";
    
    private static final String RESEARCH_AREA_TAG_ID = "protocolResearchAreas";
    
    private static final String PROTOCOL_REFERENCE_BEAN_PREFIX = "newProtocolReferenceBean.";
    private static final String LIST_PREFIX = "document.protocolList[0].";
    
    private static final String RESEARCH_AREA_CODE_ID = "researchAreaCode";
    private static final String FDA_APPLICATION_NUMBER_ID = "fdaApplicationNumber";
    private static final String REFERENCE_NUMBER_1_ID = "referenceNumber1";
    private static final String REFERENCE_NUMBER_2_ID = "referenceNumber2";
    private static final String DESCRIPTION_ID = "description";
    private static final String PROTOCOL_REFERENCE_TYPE_CODE_ID = "protocolReferenceTypeCode";
    private static final String REFERENCE_KEY_ID = "referenceKey";
    private static final String COMMENTS_ID = "comments";
    private static final String LIST_FDA_APPLICATION_NUMBER_ID = LIST_PREFIX + FDA_APPLICATION_NUMBER_ID;
    private static final String LIST_REFERENCE_NUMBER_1_ID = LIST_PREFIX + REFERENCE_NUMBER_1_ID;
    private static final String LIST_REFERENCE_NUMBER_2_ID = LIST_PREFIX + REFERENCE_NUMBER_2_ID;
    private static final String LIST_DESCRIPTION_ID = LIST_PREFIX + DESCRIPTION_ID;
    private static final String PROTOCOL_REFERENCE_BEAN_PROTOCOL_REFERENCE_TYPE_CODE_ID = PROTOCOL_REFERENCE_BEAN_PREFIX + PROTOCOL_REFERENCE_TYPE_CODE_ID;
    private static final String PROTOCOL_REFERENCE_BEAN_REFERENCE_KEY_ID = PROTOCOL_REFERENCE_BEAN_PREFIX + REFERENCE_KEY_ID;
    private static final String PROTOCOL_REFERENCE_BEAN_COMMENTS_ID =  PROTOCOL_REFERENCE_BEAN_PREFIX + COMMENTS_ID;

    private static final String RESEARCH_AREA_CODE = "01.0508";
    private static final String RESEARCH_AREA_CODE_NAME = "01.0508:TaxidermyTaxidermist";
    private static final String FDA_APPLICATION_NUMBER = "A11111";
    private static final String REFERENCE_NUMBER_1 = "0000";
    private static final String REFERENCE_NUMBER_2 = "0010";
    private static final String DESCRIPTION_1 =  "keyword_to_test1";
    private static final String DESCRIPTION_2 = "test should be done based on feature";
    private static final String PROTOCOL_REFERENCE_TYPE_CODE = "COAG";
    private static final String REFERENCE_KEY = "My Test";
    
    private static final String DELETE_PROTOCOL_RESEARCH_AREA_BUTTON = "methodToCall.deleteProtocolResearchArea.line%d";
    private static final String ADD_PROTOCOL_REFERENCE_BUTTON ="methodToCall.addProtocolReferenceBean";
    private static final String DELETE_PROTOCOL_REFERENCE_BUTTON = "methodToCall.deleteProtocolReference.line0";
    
    private ProtocolSeleniumHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = ProtocolSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }
    
    /**
     * Test addition and deletion of research areas.
     * 
     * @throws Exception
     */
    @Test
    public void testAreaOfResearchPanel() throws Exception {
        helper.createProtocol();
        helper.assertNoPageErrors();
        
        helper.openTab(TAB_ID);
        
        helper.multiLookup(RESEARCH_AREA_TAG_ID, RESEARCH_AREA_CODE_ID, RESEARCH_AREA_CODE);
        
        helper.assertTableRowCount(RESEARCH_AREA_TABLE_ID, 4);
        helper.assertTableCellValueContains(RESEARCH_AREA_TABLE_ID, RESEARCH_AREA_CODE_NAME);

        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.click(String.format(DELETE_PROTOCOL_RESEARCH_AREA_BUTTON,1));

        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertPageDoesNotContain(RESEARCH_AREA_CODE_NAME);
    }
    
    /**
     * Test modifying additional information fields.
     * 
     * @throws Exception
     */
    @Test
    public void testAdditionalInformationPanel() throws Exception {
        helper.createProtocol();
        helper.assertNoPageErrors();
        
        helper.openTab(TAB_ID);
        
        helper.set(LIST_FDA_APPLICATION_NUMBER_ID, FDA_APPLICATION_NUMBER);
        helper.set(LIST_REFERENCE_NUMBER_1_ID, REFERENCE_NUMBER_1);
        helper.set(LIST_REFERENCE_NUMBER_2_ID, REFERENCE_NUMBER_2);
        helper.set(LIST_DESCRIPTION_ID, DESCRIPTION_1);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertElementContains(LIST_FDA_APPLICATION_NUMBER_ID, FDA_APPLICATION_NUMBER);
        helper.assertElementContains(LIST_REFERENCE_NUMBER_1_ID, REFERENCE_NUMBER_1);
        helper.assertElementContains(LIST_REFERENCE_NUMBER_2_ID, REFERENCE_NUMBER_2);
        helper.assertElementContains(LIST_DESCRIPTION_ID, DESCRIPTION_1);
        
    }
   
    /**
     * Test the Summary/Keywords expanded text area.
     * 
     * @throws Exception
     */
    @Test
    public void testSummaryKeywordExpandedTextArea() throws Exception {
        helper.createProtocol();
        helper.assertNoPageErrors();
        
        helper.openTab(TAB_ID);
        
        helper.assertExpandedTextArea(LIST_DESCRIPTION_ID, DESCRIPTION_1, DESCRIPTION_2);
    }

    /**
     * Test addition and deletion of other identifiers.
     * 
     * @throws Exception
     */
    @Test
    public void testOtherIdentifiersPanel() throws Exception {
        helper.createProtocol();
        helper.assertNoPageErrors();
        
        helper.openTab(TAB_ID);
        
        helper.set(PROTOCOL_REFERENCE_BEAN_PROTOCOL_REFERENCE_TYPE_CODE_ID, PROTOCOL_REFERENCE_TYPE_CODE);
        helper.set(PROTOCOL_REFERENCE_BEAN_REFERENCE_KEY_ID, REFERENCE_KEY);
        
        helper.click(ADD_PROTOCOL_REFERENCE_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertTableRowCount(OTHER_IDENTIFIERS_TABLE_ID, 5);
        
        helper.assertTableCellValueContains(OTHER_IDENTIFIERS_TABLE_ID, 3, 0, PROTOCOL_REFERENCE_TYPE_CODE);
        helper.assertTableCellValueContains(OTHER_IDENTIFIERS_TABLE_ID, 3, 1, REFERENCE_KEY);
        
        helper.click(String.format(DELETE_PROTOCOL_REFERENCE_BUTTON, 0));
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertTableRowCount(OTHER_IDENTIFIERS_TABLE_ID, 3);
    }

    /**
     * Test the Comment expanded text area.
     * 
     * @throws Exception
     */
    @Test
    public void testCommentExpandedTextArea() throws Exception {
        helper.createProtocol();
        helper.assertNoPageErrors();
        
        helper.openTab(TAB_ID);
        
        helper.assertExpandedTextArea(PROTOCOL_REFERENCE_BEAN_COMMENTS_ID, DESCRIPTION_1, DESCRIPTION_2);
    }
    
}