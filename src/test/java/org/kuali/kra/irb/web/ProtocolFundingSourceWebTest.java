/*
 * Copyright 2006-2008 The Kuali Foundation
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

import org.junit.Test;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Abstract Protocol Web Test base class provides common functionalities required by extended class.
 */
@PerSuiteUnitTestData(@UnitTestData(sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/dml/load_FUNDING_SOURCE_TYPE.sql", delimiter = ";"),}))
public class ProtocolFundingSourceWebTest extends ProtocolWebTestBase {
    
    private static final String PROTOCOL_FUNDINGSOURCE_TYPE_FIELD = "protocolHelper.newFundingSource.fundingSourceTypeCode";
    private static final String PROTOCOL_FUNDINGSOURCE_ID_FIELD = "protocolHelper.newFundingSource.fundingSource";
    private static final String PROTOCOL_FUNDINGSOURCE_NAME_FIELD = "protocolHelper.newFundingSource.fundingSourceName";
    

    private static final String ADD_LOCATION = "methodToCall.addProtocolFundingSource.anchorFundingSources";
    private static final String DELETELINE_1_LOCATION = "methodToCall.deleteProtocolFundingSource.line1.anchor5";
    
    private static final String VIEWLINE_0_LOCATION = "methodToCall.viewProtocolFundingSource.line0.anchor5";

    
    private static final String FUNDINGSOURCE_NAME_1 = "Arkansas Enterprises for the Blind";
    private static final String FUNDINGSOURCE_ID_1 = "005174";
    private static final String FUNDINGSOURCE_NAME_2 = "Department of Homeland Security";
    private static final String FUNDINGSOURCE_ID_2 = "000162";
    private static final String SPONSOR_FUNDINGSOURCE_VAL = "1";

    @Test
    public void testFundingSourcePage() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        String documentNumber = getDocNbr(protocolPage);
  
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_ID_FIELD, FUNDINGSOURCE_ID_1);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_NAME_FIELD, FUNDINGSOURCE_NAME_1);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_TYPE_FIELD, SPONSOR_FUNDINGSOURCE_VAL);
        protocolPage = clickOn(protocolPage, ADD_LOCATION);
        
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_ID_FIELD, FUNDINGSOURCE_ID_2);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_NAME_FIELD, FUNDINGSOURCE_NAME_2);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_TYPE_FIELD, SPONSOR_FUNDINGSOURCE_VAL);
        protocolPage = clickOn(protocolPage, ADD_LOCATION);

        protocolPage = saveDoc(protocolPage);
        
        assertContains(protocolPage, "Document was successfully saved.");
        assertContains(protocolPage, FUNDINGSOURCE_ID_1);
        assertContains(protocolPage, FUNDINGSOURCE_NAME_1);
        assertContains(protocolPage, FUNDINGSOURCE_ID_2);
        assertContains(protocolPage, FUNDINGSOURCE_NAME_2);
       
        
        protocolPage = clickOn(protocolPage, DELETELINE_1_LOCATION);

        protocolPage = saveDoc(protocolPage);

        assertContains(protocolPage, "Document was successfully saved.");
        assertContains(protocolPage, FUNDINGSOURCE_ID_1);
        assertContains(protocolPage, FUNDINGSOURCE_NAME_1);
        assertDoesNotContain(protocolPage, FUNDINGSOURCE_ID_2);
        assertDoesNotContain(protocolPage, FUNDINGSOURCE_NAME_2);
       
        ProtocolDocument doc = (ProtocolDocument) getDocument(documentNumber);
        assertNotNull(doc);
        assertEquals(FUNDINGSOURCE_NAME_1, doc.getProtocol().getProtocolFundingSources().get(0).getFundingSourceName() );
     }

}
