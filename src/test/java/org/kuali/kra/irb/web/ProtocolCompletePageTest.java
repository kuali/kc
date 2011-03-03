package org.kuali.kra.irb.web;

import org.junit.Test;

public class ProtocolCompletePageTest extends ProtocolSeleniumTestBase {

	@Test
	public void testProtocolComplete() throws Exception {
	    createProtocol();

        submitProtocol();
	}

}