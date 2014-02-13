package org.kuali.kra.test.infrastructure.test;

import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.ScienceKeyword;
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
        newKeyword.setScienceKeywordCode(newMessage);
        newKeyword.setDescription(newMessage);
        boService.save(newKeyword);
        ScienceKeyword keyword = boService.findBySinglePrimaryKey(ScienceKeyword.class, newMessage);
        assertNotNull("BO did not save", keyword);
        assertEquals("BO did not save properly", keyword.getDescription(), newMessage);
        keyword = boService.findBySinglePrimaryKey(ScienceKeyword.class, oldMessage);
        assertNull("Previous test rollback failed", keyword);
    }
}