/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.printing.util;

import java.util.ArrayList;
import java.util.Map;

import org.apache.xmlbeans.XmlObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.printing.xmlstream.XmlStream;
import org.kuali.kra.s2s.service.S2SValidatorService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class forms base class for all test classes belonging to Printing. It
 * creates an {@link XmlObject} of a particular report and validates it against
 * its schema.
 * 
 * @param <T>
 *            Report test class
 */
public abstract class XmlStreamTestBase<T> extends KcUnitTestBase {
	protected XmlStream xmlStream;
	protected XmlObject xmlObject;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		GlobalVariables.setUserSession(new UserSession("quickstart"));
		xmlStream = (XmlStream) getXmlStream().newInstance();
		xmlStream.setBusinessObjectService(KraServiceLocator
				.getService(BusinessObjectService.class));
		xmlStream.setDateTimeService(KraServiceLocator
				.getService(DateTimeService.class));
		createXmlObject();
	}

	@After
	public void tearDown() throws Exception {
		xmlStream = null;
		xmlObject = null;
		GlobalVariables.setUserSession(null);
		super.tearDown();
	}

	protected void createXmlObject() {
		xmlObject = xmlStream.generateXmlStream(prepareData(),
				getReportParameters()).values().iterator().next();
		// FIXME XML saved for testing purpose only
		PrintingTestUtils.saveXml(xmlObject, this.getClass().getSimpleName());
	}

	@Test
	public void testValidateXmlStream() throws Exception {
		ArrayList<AuditError> errors = new ArrayList<AuditError>();
		getService(S2SValidatorService.class).validate(xmlObject, errors);
		assertTrue(errors.isEmpty());
	}

	protected abstract Map<String, Object> getReportParameters();

	protected abstract KraPersistableBusinessObjectBase prepareData();

	protected abstract Class<T> getXmlStream();
}
