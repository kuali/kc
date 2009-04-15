/*
 * Copyright 2006-2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kuali.kra.test;

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

    /**
     * Create a <code>{@link DataDictionaryBuilder}</code> instance to traverse the <code>PACKAGE</code> path
     * and load every DataDictionary file there. Forced complete loading to turn of lazy loading of the 
     * DataDictionary. This ensures that every DataDictionary file will be loaded and checked completely.
     * 
     * @throws Exception
     */
    @Test
    public void validateDataDictionary() throws Exception {
        builder = new DataDictionaryBuilder(KNSServiceLocator.getValidationCompletionUtils());
        builder.addUniqueEntries(PACKAGE, true);
        builder.getDataDictionary().forceCompleteDataDictionaryLoad();
    }

}
