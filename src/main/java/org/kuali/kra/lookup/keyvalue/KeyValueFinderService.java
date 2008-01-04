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
package org.kuali.kra.lookup.keyvalue;

import java.util.List;
import java.util.Map;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.web.ui.KeyLabelPair;

/**
 * This class...
 */
public interface KeyValueFinderService{
    public List<KeyLabelPair> getKeyValues(Class keyValClass,String codePropName,String valPropName);

    /**
     * 
     * This method is to get key values pair by providing additional query map as retrieving criteria.
     * @param keyValClass
     * @param codePropName
     * @param valPropName
     * @param queryMap
     * @return
     */
    public List<KeyLabelPair> getKeyValues(Class keyValClass, String codePropName, String valPropName, Map queryMap);
}
