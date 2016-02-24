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
package org.kuali.kra.iacuc;

import org.kuali.coeus.sys.framework.keyvalue.PrefixValuesFinder;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.*;

public class IacucLocationNameValuesFinder extends UifKeyValuesFinderBase {


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
