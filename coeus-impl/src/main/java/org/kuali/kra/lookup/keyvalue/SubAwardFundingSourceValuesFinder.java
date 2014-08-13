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
package org.kuali.kra.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.subaward.bo.SubAwardFundingSource;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

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
