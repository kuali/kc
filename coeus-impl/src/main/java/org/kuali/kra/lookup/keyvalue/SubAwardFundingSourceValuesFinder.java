/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
import java.util.List;

import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.subaward.bo.SubAwardFundingSource;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;

public class SubAwardFundingSourceValuesFinder extends FormViewAwareUifKeyValuesFinderBase {
    
    @Override
    public List<KeyValue> getKeyValues() {
        SubAwardDocument doc = (SubAwardDocument)getDocument();
        StringBuffer fundingValues = new StringBuffer();
        Long subawardID = doc.getSubAward().getSubAwardId();
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        Collection<SubAwardFundingSource> fundingSource = (Collection<SubAwardFundingSource>) KcServiceLocator
                .getService(BusinessObjectService.class).findAll(SubAwardFundingSource.class);
        for (SubAwardFundingSource subAwardFunding : fundingSource) {
            if (subAwardFunding.getSubAwardId().equals(subawardID)) {
                fundingValues.append(subAwardFunding.getAward().getAwardNumber());
                keyValues.add(new ConcreteKeyValue(subAwardFunding.getSubAwardFundingSourceId().toString(),"Award:"+subAwardFunding.getAward().getAwardNumber()));
            }
        }
        if(fundingValues.length() == 0){
            keyValues.add(0, new ConcreteKeyValue("", "No Funding Source has been added to this Subaward"));
        }
        return keyValues;
    }

}
