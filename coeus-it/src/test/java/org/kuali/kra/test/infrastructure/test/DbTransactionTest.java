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
package org.kuali.kra.test.infrastructure.test;

import org.junit.Test;
import org.kuali.coeus.common.framework.keyword.ScienceKeyword;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import static org.junit.Assert.*;
public class DbTransactionTest extends KcIntegrationTestBase {
    private static final String KEYWORD_1 = "TK1";
    private static final String KEYWORD_2 = "TK2";
 
    @Test
    public void testRollback1() throws Throwable {
        rollbackTest(KEYWORD_1, KEYWORD_2);
    }
    
    @Test
    public void testRollback2() throws Throwable {
        rollbackTest(KEYWORD_2, KEYWORD_1);
    }
    
    public void rollbackTest(String newMessage, String oldMessage) {
        BusinessObjectService boService = KcServiceLocator.getService(BusinessObjectService.class);
        ScienceKeyword newKeyword = new ScienceKeyword();
        newKeyword.setCode(newMessage);
        newKeyword.setDescription(newMessage);
        boService.save(newKeyword);
        ScienceKeyword keyword = boService.findBySinglePrimaryKey(ScienceKeyword.class, newMessage);
        assertNotNull("BO did not save", keyword);
        assertEquals("BO did not save properly", keyword.getDescription(), newMessage);
        keyword = boService.findBySinglePrimaryKey(ScienceKeyword.class, oldMessage);
        assertNull("Previous test rollback failed", keyword);
    }
}
