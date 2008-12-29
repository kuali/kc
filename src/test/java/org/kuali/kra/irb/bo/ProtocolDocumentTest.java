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
package org.kuali.kra.irb.bo;

import org.apache.log4j.Logger;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.rice.KNSServiceLocator;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import edu.iu.uis.eden.exception.WorkflowException;

@PerSuiteUnitTestData(
        @UnitTestData(
            sqlFiles = {
                    @UnitTestFile(filename = "classpath:sql/dml/load_system_params.sql", delimiter = ";")
                    ,@UnitTestFile(filename = "classpath:sql/dml/load_unit.sql", delimiter = ";")
                    ,@UnitTestFile(filename = "classpath:sql/dml/load_kim.sql", delimiter = ";")
                    ,@UnitTestFile(filename = "classpath:sql/dml/load_country_code.sql", delimiter = ";")
                    ,@UnitTestFile(filename = "classpath:sql/dml/load_rolodex.sql", delimiter = ";")
                    ,@UnitTestFile(filename = "classpath:sql/dml/load_person.sql", delimiter = ";")
                    ,@UnitTestFile(filename = "classpath:sql/dml/load_protocol_status.sql", delimiter = ";")
                    ,@UnitTestFile(filename = "classpath:sql/dml/load_protocol_type.sql", delimiter = ";")

            }
        )
    )
public class ProtocolDocumentTest extends KraTestBase {
    
    private Mockery context = new JUnit4Mockery();
    protected final Logger LOG = Logger.getLogger(getClass());
    
    private final String LU_ID = "000001";
    private final String LU_NAME = "University";
    private final String PI_ID = "000000001";
    private final String PI_NAME = "Terry Durkin";
    private final String PI_NE_ID = "1727";
    private final String PI_NE_NAME = "Pauline Ho";
    private final String BOGUS_PI_ID = "-5";


    public ProtocolDocumentTest() {
    }
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        GlobalVariables.setUserSession(null);
    }
    
    
    @Test
    public void testProtocolDocumentCreate() {       
        ProtocolDocument doc = null;
        try {
            doc = (ProtocolDocument) KNSServiceLocator.getDocumentService().getNewDocument("ProtocolDocument");
        }
        catch (WorkflowException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
/*        doc.setPrincipalInvestigatorId(PI_ID);        
        // note, we have to do this here because DWR does this on the page, not in the bo
        doc.setLeadUnitNumber(LU_ID);   
        
        doc.setApplicationDate(new Date(System.currentTimeMillis()));
        ProtocolType pt = new ProtocolType();
        pt.setProtocolTypeCode("1");
        pt.setDescription("Standard");
        doc.setProtocolType(pt);
        doc.setProtocolTypeCode(pt.getProtocolTypeCode());
        doc.setTitle("Test protocol title");
        doc.setDescription("Test protocol description");
        doc.getDocumentHeader().setDocumentDescription(doc.getDescription());
                
        //  called from helper
        doc.resolvePrincipalInvestigator();
        
        assertTrue(doc != null);          
        assertNotNull(doc.getPrincipalInvestigator());
        assertNotNull(doc.getPrincipalInvestigator().getProtocolUnits());          
        assertEquals(PI_NAME, doc.getPrincipalInvestigator().getPersonName());
        assertEquals(LU_NAME, doc.getLeadUnitName());
        assertTrue(doc.getProtocolInvestigators().size()==1);
        assertTrue(doc.getPrincipalInvestigator().getProtocolUnits().size()==1);*/
        
 /*       DocumentService documentService = KNSServiceLocator.getDocumentService();
        try {
            documentService.saveDocument(doc);
        }
        catch (WorkflowException e) {
            e.printStackTrace();
        }*/
    }
    
    @Test
    public void testProtocolDocumentCreateNonEmp() {       
        ProtocolDocument doc = null;
        try {
            doc = (ProtocolDocument) KNSServiceLocator.getDocumentService().getNewDocument("ProtocolDocument");
        }
        catch (WorkflowException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
/*        doc.setPrincipalInvestigatorId(PI_NE_ID);        
        // note, we have to do this here because DWR does this on the page, not in the bo
        doc.setLeadUnitNumber(LU_ID);       
        
        doc.setNonEmployeeFlag(true);        

        //  called from helper
        doc.resolvePrincipalInvestigator();
        assertTrue(doc != null);          
        assertNotNull(doc.getPrincipalInvestigator());
        assertNotNull(doc.getPrincipalInvestigator().getProtocolUnits());          
        assertEquals(PI_NE_NAME, doc.getPrincipalInvestigator().getPersonName());
        assertEquals(LU_NAME, doc.getLeadUnitName());
        assertTrue(doc.getProtocolInvestigators().size()==1);
        assertTrue(doc.getPrincipalInvestigator().getProtocolUnits().size()==1);
    }
    
    @Test
    public void testProtocolDocumentCreateNegative() {       
        ProtocolDocument doc = new ProtocolDocument();
        doc.setPrincipalInvestigatorId(BOGUS_PI_ID);        
        // note, we have to do this here because DWR does this on the page, not in the bo
        doc.setLeadUnitNumber(LU_ID);       
        
        doc.setNonEmployeeFlag(false);        

        //  called from helper
        doc.resolvePrincipalInvestigator();
        assertTrue(doc != null);          
        assertNotNull(doc.getPrincipalInvestigator());
        assertNotNull(doc.getPrincipalInvestigator().getProtocolUnits());          
        assertNull(doc.getPrincipalInvestigator().getPersonName());
        assertEquals(LU_NAME, doc.getLeadUnitName());
        assertTrue(doc.getProtocolInvestigators().size()==1);
        assertTrue(doc.getPrincipalInvestigator().getProtocolUnits().size()==1);*/
    }

}
