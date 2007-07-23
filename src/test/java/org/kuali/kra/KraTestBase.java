/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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

package org.kuali.kra;

import org.junit.After;
import org.junit.Before;
import org.kuali.rice.testharness.KNSTestCase;
import org.kuali.rice.testharness.TransactionalLifecycle;

public class KraTestBase extends KNSTestCase {

	private TransactionalLifecycle transactionalLifecycle;

	@Before
	public void setUp() throws Exception {
        setContextName("/kra-dev");
		setRelativeWebappRoot("/src/main/webapp");
		setSqlFilename("classpath:DefaultTestData.sql");
		setSqlDelimiter(";");
		setXmlFilename("classpath:DefaultTestData.xml");
		setTestConfigFilename("classpath:META-INF/kra-test-config.xml");
		super.setUp();
		transactionalLifecycle = new TransactionalLifecycle();
		transactionalLifecycle.start();
	}

	@After
	public void tearDown() throws Exception {
		transactionalLifecycle.stop();
		super.tearDown();
	}

	@Override
	protected String getModuleName() {
		return "";
	}

    /**
     * 7/23/2007 bh79 - Hack so that Bamboo test doesn't fail.
     * We can remove this once we can edit the kra-test-config.xml
     * file on the Bamboo server.
     * @see org.kuali.rice.testharness.KNSTestCase#getPort()
     */
    @Override
    protected int getPort() {
        return 9916;
    }

}
