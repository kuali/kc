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
package org.kuali.kra.iacuc.procedures;

import org.kuali.coeus.sys.framework.keyvalue.PrefixValuesFinder;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class IacucProcedureCategoryValuesFinder extends UifKeyValuesFinderBase {

    private static final long serialVersionUID = 3597528521094545089L;

    /**
     * Constructs the list of Iacuc Protocol Procedure Category Types. Each entry in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * procedure category code and the "value" is the textual description that is viewed by a user. The list is obtained from the IACUC_PROCEDURE_CATEGORY
     * database table via the "KeyValuesService".
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types. The first entry is always &lt;"", "select:"&gt;.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        Collection<IacucProcedureCategory> iacucProcedureCategories = getKeyValuesService().findAllOrderBy(IacucProcedureCategory.class,
                "procedureCategoryCode", true);
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(0, new ConcreteKeyValue(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));
        for (Iterator<IacucProcedureCategory> iter = iacucProcedureCategories.iterator(); iter.hasNext();) {
            IacucProcedureCategory iacucProcedureCategory = (IacucProcedureCategory) iter.next();
            if(iacucProcedureCategory.isActive()) {
                keyValues.add(new ConcreteKeyValue(iacucProcedureCategory.getProcedureCategoryCode().toString(),
                        iacucProcedureCategory.getProcedureCategory()));
            }
        }
        return keyValues;
    }
    
    protected KeyValuesService getKeyValuesService() {
        return (KeyValuesService) KcServiceLocator.getService("keyValuesService");
    }


}
