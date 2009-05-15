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

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ProtocolFundingSourceWebTest extends ProtocolWebTestBase {
    
    private static final String PROTOCOL_FUNDINGSOURCE_TYPE_FIELD = "protocolHelper.newFundingSource.fundingSourceTypeCode";
    private static final String PROTOCOL_FUNDINGSOURCE_ID_FIELD = "protocolHelper.newFundingSource.fundingSource";
    private static final String PROTOCOL_FUNDINGSOURCE_NAME_FIELD = "protocolHelper.newFundingSource.fundingSourceName";
    

    private static final String ADD_LOCATION = "methodToCall.addProtocolFundingSource.anchorFundingSources";
    private static final String DELETELINE0_LOCATION = "methodToCall.deleteProtocolFundingSource.line0.anchor5";
    private static final String VIEWLINE0_LOCATION = "methodToCall.viewProtocolFundingSource.line0.anchor5";

    
    private static final String USAF_FUNDINGSOURCE_NAME = "Air Force";
    private static final String USAF_FUNDINGSOURCE_ID = "000100";
    private static final String SPONSOR_FUNDINGSOURCE_VAL = "1";

    @Test
    public void testFundingSourcePage() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        String documentNumber = getDocNbr(protocolPage);
  
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_ID_FIELD, USAF_FUNDINGSOURCE_ID);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_NAME_FIELD, USAF_FUNDINGSOURCE_NAME);
        setFieldValue(protocolPage, PROTOCOL_FUNDINGSOURCE_TYPE_FIELD, SPONSOR_FUNDINGSOURCE_VAL);
        protocolPage = clickOn(protocolPage, ADD_LOCATION);

        protocolPage = saveDoc(protocolPage);
        
        assertContains(protocolPage, "Document was successfully saved.");
        assertContains(protocolPage, USAF_FUNDINGSOURCE_ID);
        assertContains(protocolPage, USAF_FUNDINGSOURCE_NAME);
       
        // Verify that the values are stored in the database
        ProtocolDocument doc = (ProtocolDocument) getDocument(documentNumber);
        assertNotNull(doc);
        assertEquals(USAF_FUNDINGSOURCE_NAME, doc.getProtocol().getProtocolFundingSources().get(0).getFundingSourceName() );
     }

}
