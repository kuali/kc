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
package org.kuali.kra.lookup;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.lookup.KualiLookupableImpl;
import org.kuali.core.lookup.LookupUtils;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.ui.Field;
import org.kuali.core.web.ui.Row;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.lookup.keyvalue.LookupReturnValuesFinder;

public class CustomLookupableImpl extends KualiLookupableImpl {

    /**
     * This is to force to reload the lookupreturn dropdown list for the lookupform. It's not pretty. The
     * GlovalVaribles.getKualiForm() is not helping because the 'fields' on lookupForm is not set until all fields are set. So, the
     * lookupreturnvaluesfinder can't take advantage of that.
     * 
     * @see org.kuali.core.lookup.KualiLookupableImpl#checkForAdditionalFields(java.util.Map)
     */
    public boolean checkForAdditionalFields(Map fieldValues) {
        String lookupReturnFieldName = (String) fieldValues.get("lookupReturn");
        String lookupClassName = (String) fieldValues.get("lookupClass");
        if (StringUtils.isNotBlank(lookupClassName)) {
            for (Iterator iter = getRows().iterator(); iter.hasNext();) {
                Row row = (Row) iter.next();
                for (Iterator iterator = row.getFields().iterator(); iterator.hasNext();) {
                    Field field = (Field) iterator.next();
                    if (field.getPropertyName().equals("lookupReturn")) {
                        GlobalVariables.getUserSession().addObject(Constants.LOOKUP_CLASS_NAME, (Object) lookupClassName);
                        LookupReturnValuesFinder finder = new LookupReturnValuesFinder();
                        field.setFieldValidValues(finder.getKeyValues());
                        GlobalVariables.getUserSession().removeObject(Constants.LOOKUP_RETURN_FIELDS);
                        GlobalVariables.getUserSession().removeObject(Constants.LOOKUP_CLASS_NAME);
                        if (StringUtils.isNotBlank(lookupReturnFieldName)) {
                            field.setPropertyValue(lookupReturnFieldName);
                            field.setPropertyValue(LookupUtils.forceUppercase(this.getBusinessObjectClass(), lookupReturnFieldName, field.getPropertyValue()));
                            fieldValues.put(lookupReturnFieldName, field.getPropertyValue());
                        }
                    }
                }
            }

        }
        return super.checkForAdditionalFields(fieldValues);
    }
}
