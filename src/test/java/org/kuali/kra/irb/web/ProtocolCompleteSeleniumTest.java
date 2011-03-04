package org.kuali.kra.irb.web;

import org.junit.Test;

public class ProtocolCompleteSeleniumTest extends ProtocolSeleniumTestBase {

	@Test
	public void testProtocolComplete() throws Exception {
	    createProtocol();

        submitProtocol();
	}

}