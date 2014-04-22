/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.document;


import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.editable.ProposalColumnsToAlterMaintainableImpl;
import org.kuali.coeus.sys.framework.persistence.KcPersistenceStructureService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.editable.ProposalColumnsToAlter;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.Map;

import static org.junit.Assert.assertTrue;
public class ProposalColumnsToAlterMaintainableImplTest extends KcIntegrationTestBase {

    private ProposalColumnsToAlterMaintainableImpl columnsToAlterMaintainable;
    private Map<String, String> columnToAttrMap;
    
    @Before
    public void setUp() throws Exception {
        KcPersistenceStructureService persistenceStructureService =
            KcServiceLocator.getService(KcPersistenceStructureService.class);
        columnToAttrMap = persistenceStructureService.getDBColumnToObjectAttributeMap(DevelopmentProposal.class);
        
        columnsToAlterMaintainable = new ProposalColumnsToAlterMaintainableImpl();
        
        GlobalVariables.setUserSession(new UserSession("admin"));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAllProposalDevColumns() throws Exception {
        for (String cols : columnToAttrMap.keySet()) {
            ProposalColumnsToAlter testBo = new ProposalColumnsToAlter();
            testBo.setColumnName(cols);
            columnsToAlterMaintainable.setBusinessObject(testBo);
            columnsToAlterMaintainable.prepareForSave();
            //no exception was thrown which means it worked well enough
        }
    }
    
    @Test
    public void testSpecificProposalColumns() throws Exception {
        ProposalColumnsToAlter testBo = new ProposalColumnsToAlter();
        testBo.setColumnName("MAIL_BY");
        columnsToAlterMaintainable.setBusinessObject(testBo);
        columnsToAlterMaintainable.prepareForSave();
        assertTrue("Label was not set as expected", StringUtils.isNotBlank(testBo.getColumnLabel()));
        assertTrue("Data length not set properly", testBo.getDataLength() > 0);
        testBo.setColumnName("TITLE");
        columnsToAlterMaintainable.setBusinessObject(testBo);
        columnsToAlterMaintainable.prepareForSave();
        assertTrue("Label was not set as expected", StringUtils.isNotBlank(testBo.getColumnLabel()));
        assertTrue("Data length not set properly", testBo.getDataLength() > 0);
        testBo.setColumnName("ACTIVITY_TYPE_CODE");
        columnsToAlterMaintainable.setBusinessObject(testBo);
        columnsToAlterMaintainable.prepareForSave();
        assertTrue("Label was not set as expected", StringUtils.isNotBlank(testBo.getColumnLabel()));
        assertTrue("Data length not set properly", testBo.getDataLength() > 0);        
    }
}
