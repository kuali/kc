package org.kuali.kra.irb.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the basic submission of a Protocol.
 */
public class ProtocolCompleteSeleniumTest extends KcSeleniumTestBase {
    
    private ProtocolSeleniumHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = ProtocolSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }

    /**
     * Test the basic submission of a protocol.
     */
	@Test
	public void testProtocolComplete() throws Exception {
	    helper.createProtocol();

        helper.submit();
	}

}