/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.budget.editable;

import org.kuali.coeus.sys.framework.keyvalue.KeyValueFinderService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.List;

public class BudgetEditableColumnsValuesFinder extends UifKeyValuesFinderBase {
    private KeyValueFinderService keyValueFinderService ;
    protected  KeyValueFinderService getKeyValueFinderService (){
        if (keyValueFinderService==null)
            keyValueFinderService = KcServiceLocator.getService(KeyValueFinderService.class);
        return keyValueFinderService;
    }
    @Override
    public List<KeyValue> getKeyValues() {
        return getKeyValueFinderService().getKeyValues(BudgetColumnsToAlter.class, "columnName", "columnLabel");
    }
}
