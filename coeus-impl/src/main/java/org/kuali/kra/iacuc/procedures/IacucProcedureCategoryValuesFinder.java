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
