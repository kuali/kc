package org.kuali.kra.test.infrastructure.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.kra.test.infrastructure.KcUnitTestReqs;
import org.kuali.kra.test.infrastructure.KcUnitTestReqs.Req;

public class TestUnitTestB extends KcUnitTestBase {
	@Test
	@KcUnitTestReqs(Req.CONFIG)
	public void testA() {
		System.out.println("testA");
	}

	@Test
	@KcUnitTestReqs(Req.SERVER)
	public void testB() {
		System.out.println("testB");
	}
    
    @Test
    @KcUnitTestReqs(Req.CONTEXT)
    public void testC() {
        System.out.println("testC");
        throw new RuntimeException();
    }
    
    @Test
    public void testD() {
        System.out.println("testD");
        assertTrue(false);
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
