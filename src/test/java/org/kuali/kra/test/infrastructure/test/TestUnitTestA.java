package org.kuali.kra.test.infrastructure.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.kra.test.infrastructure.KcUnitTestReqs;
import org.kuali.kra.test.infrastructure.KcUnitTestReqs.Req;
import org.kuali.rice.core.config.ConfigContext;
import org.kuali.rice.core.resourceloader.GlobalResourceLoader;
import org.springframework.transaction.PlatformTransactionManager;

public class TestUnitTestA extends KcUnitTestBase {
	@Test
	public void testA() {
		System.out.println("testA");
		assertNull("Config loaded prematurely", ConfigContext.getCurrentContextConfig());
	}

	@Test
	@KcUnitTestReqs(Req.CONFIG)
	public void testB() {
		System.out.println("testB");
        assertNotNull("Config not loaded appropriately", ConfigContext.getCurrentContextConfig());
        assertFalse("Context loaded prematurely", GlobalResourceLoader.isInitialized());
	}
    
    @Test
    @KcUnitTestReqs(Req.CONTEXT)
    public void testC() {
        assertTrue("Context not loaded appropriately", GlobalResourceLoader.isInitialized());
//        PlatformTransactionManager txMgr = KraServiceLocator.getService("transactionManager");
//        txMgr.
    }
    
    @Test
    @KcUnitTestReqs(Req.SERVER)
    public void testD() {
        System.out.println("testD");
        assertTrue(true);
    }
    
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        System.out.println("**UnitTestTest.setUp()");
    }
    
    @Override
    @After
    public void tearDown() throws Exception {
        System.out.println("**UnitTestTest.tearDown()");
        super.tearDown();
    }
    
}
