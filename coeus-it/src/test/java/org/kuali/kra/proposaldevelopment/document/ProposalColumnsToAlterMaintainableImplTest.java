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
