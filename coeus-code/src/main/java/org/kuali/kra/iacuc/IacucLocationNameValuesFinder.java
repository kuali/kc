/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.iacuc;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.lookup.keyvalue.PrefixValuesFinder;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.*;

public class IacucLocationNameValuesFinder extends UifKeyValuesFinderBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8545179201741577722L;
    private Integer locationTypeCode;

    /**
     * Constructs the list of Iacuc Location Names. Each entry in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * location id and the "value" is the textual description that is viewed by a user. The list is obtained from the IACUC_LOCATION_NAME
     * database table via the "KeyValuesService".
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types. The first entry is always &lt;"", "select:"&gt;.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        Map<String, Object> filterValues = new HashMap<String, Object>();
        filterValues.put("locationTypeCode", getLocationTypeCode());
        Collection<IacucLocationName> iacucLocationNames = getKeyValuesService().findMatching(IacucLocationName.class,
                filterValues);
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(0, new ConcreteKeyValue(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));
        for (Iterator<IacucLocationName> iter = iacucLocationNames.iterator(); iter.hasNext();) {
            IacucLocationName iacucLocationName = (IacucLocationName) iter.next();
            keyValues.add(new ConcreteKeyValue(iacucLocationName.getLocationId().toString(),
                    iacucLocationName.getLocationName()));
        }
        return keyValues;
    }
    
    protected KeyValuesService getKeyValuesService() {
        return (KeyValuesService) KcServiceLocator.getService("keyValuesService");
    }

    public Integer getLocationTypeCode() {
        return locationTypeCode;
    }

    public void setLocationTypeCode(Integer locationTypeCode) {
        this.locationTypeCode = locationTypeCode;
    }

}
