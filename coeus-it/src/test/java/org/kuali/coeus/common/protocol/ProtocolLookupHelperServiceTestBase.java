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
package org.kuali.coeus.common.protocol;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

public class ProtocolLookupHelperServiceTestBase extends KcIntegrationTestBase {

	protected static final String PERSON_INQ_URL = "inquiry.do?businessObjectClassName=" + KcPerson.class.getName() + "&personId=10000000001&methodToCall=start";
	protected static final String ROLODEX_INQ_URL = "inquiry.do?businessObjectClassName=" + Rolodex.class.getName() + "&rolodexId=1727&methodToCall=start";
	
	protected Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};

	public ProtocolLookupHelperServiceTestBase() {
		super();
	}

	public void setUp() throws Exception {
	        GlobalVariables.setUserSession(new UserSession("quickstart"));
	   }

	public void tearDown() throws Exception {
	    GlobalVariables.setUserSession(null);
	}

}
