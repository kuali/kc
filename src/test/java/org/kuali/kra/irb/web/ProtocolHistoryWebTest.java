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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolVersionService;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
@org.junit.Ignore("This test is not meant to be run against the 2.0 release")
public class ProtocolHistoryWebTest extends ProtocolWebTestBase {
    
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private static final long ONE_DAY = 1000L * 60L * 60L * 24L;
    
    private ProtocolVersionService versionService;
    static private ProtocolDocument protocolDocument1;
    static private ProtocolDocument protocolDocument2;
    static private ProtocolDocument protocolDocument3;
    static private boolean initialized = false;
    
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
                    protocolDocument1 = ProtocolFactory.createProtocolDocument("0906001001");
                    ProtocolAction protocolAction = new ProtocolAction(protocolDocument1.getProtocol(), null, ProtocolActionType.PROTOCOL_CREATED);
                    protocolDocument1.getProtocol().getProtocolActions().add(protocolAction);
                    
                    ProtocolSubmission ps = new ProtocolSubmission();
                    ps.setSubmissionNumber(1);
                    protocolAction = new ProtocolAction(protocolDocument1.getProtocol(), ps, ProtocolActionType.NOTIFY_IRB);
                    protocolAction.setActionDate(new Timestamp(System.currentTimeMillis() + ONE_DAY));
                    protocolAction.setActualActionDate(new Timestamp(System.currentTimeMillis() + ONE_DAY));
                    protocolAction.setSequenceNumber(protocolDocument1.getProtocol().getSequenceNumber());
                    protocolDocument1.getProtocol().getProtocolActions().add(protocolAction);
                    
                    getDocumentService().saveDocument(protocolDocument1);
                        
                    protocolDocument2 = versionService.versionProtocolDocument(protocolDocument1);
                    getDocumentService().saveDocument(protocolDocument2);
                       
                    protocolDocument3 = versionService.versionProtocolDocument(protocolDocument2);
                    getDocumentService().saveDocument(protocolDocument3);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                    
                return null;
            }
        });
    }
    
    /**
     * Verify that the Protocol Create action is added when a protocol is created.
     * @throws Exception
     */
    @Test
    public void testCreateAction() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
        HtmlPage actionsPage = clickOnTab(protocolPage, PROTOCOL_ACTIONS_LINK_NAME);
        HtmlTable table = this.getTable(actionsPage, "historyTable");
        assertNotNull(table);
        assertEquals(3, table.getRowCount());
        assertEquals("Protocol Created", table.getRow(1).getCell(1).asText());
    }
    
    /**
     * Verify that we see the two actions in the history table
     * in the order expected.
     * @throws Exception
     */
    @Test
    public void testHistoryTable() throws Exception {
       
        String docNbr = protocolDocument3.getDocumentNumber();
        
        HtmlPage protocolPage = docSearch(docNbr);
        assertNotNull(protocolPage);
        
        HtmlPage actionsPage = clickOnTab(protocolPage, PROTOCOL_ACTIONS_LINK_NAME);
        
        HtmlTable table = this.getTable(actionsPage, "historyTable");
        assertNotNull(table);
        assertEquals(4, table.getRowCount());
        assertEquals("Notify IRB", table.getRow(1).getCell(1).asText());
        assertEquals("Protocol Created", table.getRow(2).getCell(1).asText());   
    }
    
    /**
     * Test the Date Filter.  Try different combinations (after, before, between)
     * and verify that the expected action(s) are there.
     * @throws Exception
     */
    @Test
    public void testHistoryDateFilter() throws Exception {
        String docNbr = protocolDocument3.getDocumentNumber();
        
        HtmlPage protocolPage = docSearch(docNbr);
        assertNotNull(protocolPage);
        
        HtmlPage actionsPage = clickOnTab(protocolPage, PROTOCOL_ACTIONS_LINK_NAME);
        
        /*
         * Test after today.  Today's actions will be shown as well as any future ones.
         */
        setFieldValue(actionsPage, "actionHelper.historyDateRangeFilter.beginningOn", dateFormat.format(new Date()));
        HtmlElement filterBtn = getElementByName(actionsPage, "methodToCall.filterHistory", true);
        actionsPage = clickOn(filterBtn);
        
        HtmlTable table = this.getTable(actionsPage, "historyTable");
        assertNotNull(table);
        assertEquals(4, table.getRowCount());
        
        /*
         * Test after tomorrow.
         */
        setFieldValue(actionsPage, "actionHelper.historyDateRangeFilter.beginningOn", dateFormat.format(new Date(System.currentTimeMillis() + ONE_DAY)));
        filterBtn = getElementByName(actionsPage, "methodToCall.filterHistory", true);
        actionsPage = clickOn(filterBtn);
        
        table = this.getTable(actionsPage, "historyTable");
        assertNotNull(table);
        assertEquals(3, table.getRowCount());
        assertEquals("Notify IRB", table.getRow(1).getCell(1).asText());
        
        /*
         * Test before tomorrow.
         */
        setFieldValue(actionsPage, "actionHelper.historyDateRangeFilter.beginningOn", "");
        setFieldValue(actionsPage, "actionHelper.historyDateRangeFilter.endingOn", dateFormat.format(new Date(System.currentTimeMillis() + ONE_DAY)));
        filterBtn = getElementByName(actionsPage, "methodToCall.filterHistory", true);
        actionsPage = clickOn(filterBtn);
        
        table = this.getTable(actionsPage, "historyTable");
        assertNotNull(table);
        assertEquals(4, table.getRowCount());
        
        /*
         * Test before today.
         */
        setFieldValue(actionsPage, "actionHelper.historyDateRangeFilter.beginningOn", "");
        setFieldValue(actionsPage, "actionHelper.historyDateRangeFilter.endingOn", dateFormat.format(new Date(System.currentTimeMillis())));
        filterBtn = getElementByName(actionsPage, "methodToCall.filterHistory", true);
        actionsPage = clickOn(filterBtn);
        
        table = this.getTable(actionsPage, "historyTable");
        assertNotNull(table);
        assertEquals(3, table.getRowCount());
        assertEquals("Protocol Created", table.getRow(1).getCell(1).asText());
        
        /*
         * Test between today and tomorrow.
         */
        setFieldValue(actionsPage, "actionHelper.historyDateRangeFilter.beginningOn", dateFormat.format(new Date()));
        setFieldValue(actionsPage, "actionHelper.historyDateRangeFilter.endingOn", dateFormat.format(new Date(System.currentTimeMillis() + ONE_DAY)));
        filterBtn = getElementByName(actionsPage, "methodToCall.filterHistory", true);
        actionsPage = clickOn(filterBtn);
        
        table = this.getTable(actionsPage, "historyTable");
        assertNotNull(table);
        assertEquals(4, table.getRowCount());
    }
    
    /**
     * Test the Load Summary button.  Select the Notify IRB entry and
     * then click on the load summary button.  Then check the summary
     * to verify that we are looking at the summary for the first protocol.
     * @throws Exception
     */
    @Test
    public void testHistoryLoadSummary() throws Exception {
        String docNbr = protocolDocument3.getDocumentNumber();
        
        HtmlPage protocolPage = docSearch(docNbr);
        assertNotNull(protocolPage);
        
        HtmlPage actionsPage = clickOnTab(protocolPage, PROTOCOL_ACTIONS_LINK_NAME);
        
        HtmlRadioButtonInput btn = (HtmlRadioButtonInput) getElementByName(actionsPage, "actionHelper.selectedHistoryItem");
        btn.setChecked(true);
        
        HtmlElement loadBtn = getElementByName(actionsPage, "methodToCall.loadProtocolSummary", true);
        actionsPage = clickOn(loadBtn);
        
        HtmlElement element = getElementById(actionsPage, "summarySequence");
        assertNotNull(element);
        String text = element.asText().trim();
        assertTrue("expected Sequence 1/3:  Notify IRB, was " + text, text.startsWith("Sequence 1/3:  Notify IRB"));
    }
}
