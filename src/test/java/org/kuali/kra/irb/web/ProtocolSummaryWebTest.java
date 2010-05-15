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

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolVersionService;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@org.junit.Ignore("This test is not meant to be run against the 2.0 release")
public class ProtocolSummaryWebTest extends ProtocolWebTestBase {
    
    private ProtocolVersionService versionService;
    static private ProtocolDocument protocolDocument1;
    static private ProtocolDocument protocolDocument2;
    static private ProtocolDocument protocolDocument3;
    static private boolean initialized;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();    
        versionService = KraServiceLocator.getService(ProtocolVersionService.class);
        if (!initialized) {
            initialized = true;
            loadProtocolVersions();
        }
    }
    
    private void loadProtocolVersions() throws Exception {
        TransactionTemplate template = new TransactionTemplate(KNSServiceLocator.getTransactionManager());
        template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
      
        template.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus status) {
                try {
                    protocolDocument1 = ProtocolFactory.createProtocolDocument();
                    getDocumentService().saveDocument(protocolDocument1);
                        
                    protocolDocument2 = versionService.versionProtocolDocument(protocolDocument1);
                    getDocumentService().saveDocument(protocolDocument2);
                       
                    protocolDocument3 = versionService.versionProtocolDocument(protocolDocument2);
                    getDocumentService().saveDocument(protocolDocument3);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                    
                return null;
            }
        });
    }
    
    /**
     * Open up the third (latest) protocol version and go to the 
     * Action tab.  From there click on the "previous" button to traverse
     * back to the previous versions.  Then click on the "next" button
     * to get back to the third version again.  Also, if a version has
     * a previous version, then it must have that previous summary for
     * comparison purposes.  Just verify that the Comparison title is there (or not there).
     * @throws Exception
     */
    @Test
    public void testSummaryTraversal() throws Exception {
       
        String docNbr = protocolDocument3.getDocumentNumber();
        
        HtmlPage protocolPage = docSearch(docNbr);
        assertNotNull(protocolPage);
        
        HtmlPage actionsPage = clickOnTab(protocolPage, PROTOCOL_ACTIONS_LINK_NAME);
        
        HtmlElement element = getElementById(actionsPage, "summarySequence");
        assertNotNull(element);
        String text = element.asText().trim();
        assertEquals("Sequence 3/3:", text);
        assertContains(actionsPage, "Compare to Previous Sequence");
        
        HtmlElement viewPrev = getElementByName(actionsPage, "methodToCall.viewPreviousProtocolSummary", true);
        actionsPage = clickOn(viewPrev);
        element = getElement(actionsPage, "summarySequence");
        text = element.asText().trim();
        assertEquals("Sequence 2/3:", text);
        assertContains(actionsPage, "Compare to Previous Sequence");
        
        viewPrev = getElementByName(actionsPage, "methodToCall.viewPreviousProtocolSummary", true);
        actionsPage = clickOn(viewPrev);
        element = getElement(actionsPage, "summarySequence");
        text = element.asText().trim();
        assertEquals("Sequence 1/3:", text);
        assertDoesNotContain(actionsPage, "Compare to Previous Sequence");
        
        HtmlElement viewNext = getElementByName(actionsPage, "methodToCall.viewNextProtocolSummary", true);
        actionsPage = clickOn(viewNext);
        element = getElement(actionsPage, "summarySequence");
        text = element.asText().trim();
        assertEquals("Sequence 2/3:", text);
        assertContains(actionsPage, "Compare to Previous Sequence");
        
        viewNext = getElementByName(actionsPage, "methodToCall.viewNextProtocolSummary", true);
        actionsPage = clickOn(viewNext);
        element = getElement(actionsPage, "summarySequence");
        text = element.asText().trim();
        assertEquals("Sequence 3/3:", text);
        assertContains(actionsPage, "Compare to Previous Sequence");
    }
}
