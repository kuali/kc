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
package org.kuali.kra.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.subaward.bo.SubAwardReportType;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

public class SubAwardReportTypeValuesFinder extends UifKeyValuesFinderBase {
    
    @Override
    public List<KeyValue> getKeyValues() {
        KeyValuesService keyValuesService = (KeyValuesService) KcServiceLocator.getService("keyValuesService");
        Collection SubAwardReportType = keyValuesService.findAllOrderBy(SubAwardReportType.class,"sortId",true);
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        
        for (Iterator iter = SubAwardReportType.iterator(); iter.hasNext();) {
            SubAwardReportType reportTypes = (SubAwardReportType) iter.next();
            keyValues.add(new ConcreteKeyValue(reportTypes.getSubAwardReportTypeCode().toString(), reportTypes.getDescription()));                            
        }       
        return keyValues;
    }

}
