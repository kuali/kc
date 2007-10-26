/*
 * Copyright 2005-2007 The Kuali Foundation.
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

package org.kuali.kra.datadictionary;

import org.junit.Test;
import org.kuali.core.datadictionary.DataDictionaryBuilder;
import org.kuali.kra.KraTestBase;
import org.kuali.rice.KNSServiceLocator;

/**
 * This class is used to validate the KRA Data Dictionary files
 */
public class DataDictionaryTest extends KraTestBase {

	private DataDictionaryBuilder builder = null;

    private static final String PACKAGE = "org/kuali/kra/datadictionary/";

    @Test
	public void testDataDictionaryFiles() throws Exception {
		builder = new DataDictionaryBuilder(KNSServiceLocator.getValidationCompletionUtils());
		builder.addUniqueEntries(PACKAGE, true);
        builder.getDataDictionary().forceCompleteDataDictionaryLoad();
	}

}
